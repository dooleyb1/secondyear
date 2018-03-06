package osp.Threads;
import java.util.Vector;
import java.util.Comparator;
import java.util.Enumeration;
import osp.Utilities.*;
import osp.IFLModules.*;
import osp.Tasks.*;
import osp.EventEngine.*;
import osp.Hardware.*;
import osp.Devices.*;
import osp.Memory.*;
import osp.Resources.*;
import java.util.AbstractQueue;
import java.util.PriorityQueue;

/*
   This class is responsible for actions related to threads, including
   creating, killing, dispatching, resuming, and suspending threads.

   @OSPProject Threads
*/
public class ThreadCB extends IflThreadCB {
    //private static GenericList readyQueue;
	 private static class PriorityComparator implements Comparator<ThreadCB> {

    	@Override
    	public int compare(ThreadCB x, ThreadCB y){

    		if(x.getPriority() < y.getPriority())
    			return 1;

    		if(x.getPriority() > y.getPriority())
    			return -1;
    		
    		return 0;

    	}
    }

    private static Comparator<ThreadCB> comparator = new PriorityComparator();
    private static PriorityQueue<ThreadCB> readyQueue;

    public ThreadCB(){
	
		super();
   
    }

    //Initialises queue and other variables
    public static void init(){

    	//readyQueue = new GenericList();
		readyQueue = new PriorityQueue<>(100, comparator);
   
    }

     
    //Sets up a new thread and adds the given task to it. 
    static public ThreadCB do_create(TaskCB task){
		
		if (task == null){
		    
		    dispatch();
		    return null;

		}
		
		// Can we add a new thread to this task?
		if (task.getThreadCount() >= MaxThreadsPerTask) {		   
		    
		    MyOut.print("osp.Threads.ThreadCB","Failed to create new thread " + " -- maximum number of threads for " + task + " reached");
		    dispatch();
		    return null;
		}

		//Otherwise create new thread
		ThreadCB newThread = new ThreadCB();
		MyOut.print("osp.Threads.ThreadCB", "Created "+newThread);
			
		// Setup the new thread.
		newThread.setPriority(task.getPriority());
		newThread.setStatus(ThreadReady);
		newThread.setTask(task);
		
		// Add the new thread to the task.
		if (task.addThread(newThread) != SUCCESS) {
		    
		    MyOut.print("osp.Threads.ThreadCB","Could not add thread "+ newThread+" to task "+task);
		    dispatch();
		    return null;
		}
		
		//Add this new thread to the queue
		readyQueue.add(newThread);
	
		MyOut.print("osp.Threads.ThreadCB","Successfully added "+newThread+" to "+task);
		
		dispatch();

		return newThread;
    }

    
	//Kills the specified thread.
    public void do_kill()
    {
        MyOut.print(this, "Entering do_kill(" +this + ")");

        TaskCB task = getTask();

        //Get status of thread and act accordingly
		switch (getStatus()){
	        case ThreadReady:
			    // Delete thread from ready queue.
			    readyQueue.remove(this);
		    	break;

			case ThreadRunning:
			    // Remove (preempt) thread from CPU.
			    if(this == MMU.getPTBR().getTask().getCurrentThread()) {

					MMU.getPTBR().getTask().setCurrentThread(null);
			    }
			    break;
			default:
		}       

        // Remove thread from task.
        if(task.removeThread(this) != SUCCESS) {
		    
		    MyOut.print(this,"Could not remove thread "+ this+" from task "+task);
		    return;
		}
    
        MyOut.print(this, this + " is set to be destroyed");

		// Change thread's status.
		setStatus(ThreadKill);

		// We have only one I/O per thread, so we should just
		// cancel it for the corresponding device.
	    for(int i = 0; i < Device.getTableSize(); i++) {

		    MyOut.print(this, "Purging IORBs on Device " + i);
		    Device.get(i).cancelPendingIO(this);
		}

        // release all resources owned by the thread
        ResourceCB.giveupResources(this);

		dispatch();

		if (this.getTask().getThreadCount()==0) {

		    MyOut.print(this,"After destroying " + this + ": " + this.getTask() + " has no threads left; destroying the task");
		    this.getTask().kill();
		
		}
    }


    // Suspends the thread that is currenly on the processor on the specified event. 
    public void do_suspend(Event event)
    {
		int oldStatus = this.getStatus();
	    MyOut.print(this, "Entering suspend(" + this + "," + event + ")");

		// Note: "this" might not be the running thread
		ThreadCB runningThread=null;
		TaskCB runningTask=null;

		try {
		    
		    runningTask = MMU.getPTBR().getTask();
		    runningThread = runningTask.getCurrentThread();

		} catch(NullPointerException e){}

		// Note: we may be suspending not the running thread
		if (this == runningThread)
		    this.getTask().setCurrentThread(null);
		    
		// Set thread's status.
		if (this.getStatus() == ThreadRunning)
		    setStatus(ThreadWaiting);

		else if (this.getStatus() >= ThreadWaiting)
		    setStatus(this.getStatus()+1);
		
		readyQueue.remove(this);
		event.addThread(this);

		// Dispatch a new thread.
		dispatch();
    }

    
    //Resumes the thread - only a thread with the status ThreadWaiting or higher
    public void do_resume()
    {
        if(getStatus() < ThreadWaiting) {
	    	
	    	MyOut.print(this,"Attempt to resume " + this + ", which wasn't waiting");
	    	return;
		}

        MyOut.print(this, "Resuming " + this);

        // Set thread's status.
		if (getStatus() == ThreadWaiting)
	    	setStatus(ThreadReady);

		else if (getStatus() > ThreadWaiting)
	    	setStatus(getStatus()-1);

        // Put the thread on the ready queue, if appropriate
		if (getStatus() == ThreadReady)
	    	readyQueue.add(this);

		dispatch();
    }
    

    //Selects a thread from the run queue and dispatches it
    public static int do_dispatch()
    {
        ThreadCB threadToDispatch=null;
        ThreadCB runningThread=null;
		TaskCB runningTask=null;

		try {

		    runningTask = MMU.getPTBR().getTask();
		    runningThread = runningTask.getCurrentThread();

		} catch(NullPointerException e) {}

        // If necessary, remove current thread from processor and reschedule it.
        if(runningThread != null) {

		    MyOut.print("osp.Threads.ThreadCB","Preempting currently running " + runningThread);

		    //Take thread away from task
		    runningTask.setCurrentThread(null);

		    //Take task off CPU
		    MMU.setPTBR(null);

		    //Set status to ready (but waiting)
		    runningThread.setStatus(ThreadReady);
		    readyQueue.add(runningThread);
		}

        // Select thread from ready queue.
        threadToDispatch = (ThreadCB)readyQueue.poll();

        if(threadToDispatch == null) {

        	//No threads left
		    MyOut.print("osp.Threads.ThreadCB","Can't find suitable thread to dispatch");
		    MMU.setPTBR(null);
		    return FAILURE;
		}

		// Put the thread on the processor.
		MMU.setPTBR(threadToDispatch.getTask().getPageTable());

		// set thread to dispatch as the current thread of its task
	    threadToDispatch.getTask().setCurrentThread(threadToDispatch);

		// Set thread's status.
		threadToDispatch.setStatus(ThreadRunning);
	            
	    MyOut.print("osp.Threads.ThreadCB","Dispatching " + threadToDispatch);

		HTimer.set(150);
		return SUCCESS;
    }


    public static void atError(){
    }

    public static void atWarning(){
    }
}




