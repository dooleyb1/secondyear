package osp.Threads;
import java.util.Vector;
import java.util.Enumeration;
import osp.Utilities.*;
import osp.IFLModules.*;
import osp.Tasks.*;
import osp.EventEngine.*;
import osp.Hardware.*;
import osp.Devices.*;
import osp.Memory.*;
import osp.Resources.*;

/**
   This class is responsible for actions related to threads, including
   creating, killing, dispatching, resuming, and suspending threads.

   @OSPProject Threads
*/
public class ThreadCB extends IflThreadCB 
{
    private static GenericList readyQueue;


    /**
       The thread constructor. Must call 

       	   super();

       as its first statement.

       @OSPProject Threads
    */
    public ThreadCB()
    {
	super();
    }


    /**
       This method will be called once at the beginning of the
       simulation. The student can set up static variables here.
       
       @OSPProject Threads
    */
    public static void init()
    {
	readyQueue = new GenericList();
    }

    /** 
        Sets up a new thread and adds it to the given task. 
        The method must set the ready status 
        and attempt to add thread to task. If the latter fails 
        because there are already too many threads in this task, 
        so does this method, otherwise, the thread is appended 
        to the ready queue and dispatch() is called.

	The priority of the thread can be set using the getPriority/setPriority
	methods. However, OSP itself doesn't care what the actual value of
	the priority is. These methods are just provided in case priority
	scheduling is required.

	@return thread or null

        @OSPProject Threads
    */
    static public ThreadCB do_create(TaskCB task)
    {
	if (task == null) {
	    dispatch();
	    return null;
	}
		
	// Can we add a new thread to this task?
	if (task.getThreadCount() >= MaxThreadsPerTask) {		   
	    MyOut.print("osp.Threads.ThreadCB",
			"Failed to create new thread "
			+ " -- maximum number of threads for "
			+ task + " reached");
	    dispatch();
	    return null;
	}

	ThreadCB newThread = new ThreadCB();
	MyOut.print("osp.Threads.ThreadCB", "Created "+newThread);
			
	// Setup the new thread.
	newThread.setPriority(task.getPriority());
	newThread.setStatus(ThreadReady);

	newThread.setTask(task);
		
	// Add the new thread to the task.
	if (task.addThread(newThread) != SUCCESS) {
	    MyOut.print("osp.Threads.ThreadCB",
			"Could not add thread "+ newThread+" to task "+task);
	    dispatch();
	    return null;
	}
		
	readyQueue.append(newThread);
	
	MyOut.print("osp.Threads.ThreadCB",
		    "Successfully added "+newThread+" to "+task);
		
	dispatch();

	return newThread;
    }

    /** 
	Kills the specified thread. 

	The status must be set to ThreadKill, the thread must be
	removed from the task's list of threads and its pending IORBs
	must be purged from all device queues.
        
	If some thread was on the ready queue, it must removed, if the 
	thread was running, the processor becomes idle, and dispatch() 
	must be called to resume a waiting thread.
	
	@OSPProject Threads
    */
    public void do_kill()
    {
        MyOut.print(this, "Entering do_kill(" +this + ")");

        TaskCB task = getTask();

	switch (getStatus()) {
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
	    MyOut.print(this,
			"Could not remove thread "+ this+" from task "+task);
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
	    MyOut.print(this,
			"After destroying " + this + ": " + this.getTask() 
			+ " has no threads left; destroying the task");
	    this.getTask().kill();
	}
    }


    /** Suspends the thread that is currenly on the processor on the 
        specified event. 

        Note that the thread being suspended doesn't need to be
        running. It can also be waiting for completion of a pagefault
        and be suspended on the IORB that is bringing the page in.
	
	Thread's status must be changed to ThreadWaiting or higher,
        the processor set to idle, the thread must be in the right
        waiting queue, and dispatch() must be called to give CPU
        control to some other thread.

	@param event - event on which to suspend this thread.

        @OSPProject Threads
    */
    public void do_suspend(Event event)
    {
	int oldStatus = this.getStatus();
        MyOut.print(this, "Entering suspend(" + this + "," + event + ")");

	// Note: "this" might not be the running thread, because we
	// might be suspending a thread that is in the middle of a system call
	// (e.g., a pagefaulted thread after swapout or a thread that 
	// is ready for I/O after a pagefault caused by lock()
	ThreadCB runningThread=null;
	TaskCB runningTask=null;
	try {
	    runningTask = MMU.getPTBR().getTask();
	    runningThread = runningTask.getCurrentThread();
	} catch(NullPointerException e){}

	// Note: we may be suspending not the running thread, so
	// we must check if "this" equals runningThread
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

    
    /** Resumes the thread.
        
	Only a thread with the status ThreadWaiting or higher
	can be resumed.  The status must be set to ThreadReady or
	decremented, respectively.
	A ready thread should be placed on the ready queue.
	
	@OSPProject Threads
    */
    public void do_resume()
    {
        if(getStatus() < ThreadWaiting) {
	    MyOut.print(this,
			"Attempt to resume "
			+ this + ", which wasn't waiting");
	    return;
	}

        MyOut.print(this, "Resuming " + this);

        // Set thread's status.
	if (getStatus() == ThreadWaiting) {
	    setStatus(ThreadReady);
	} else if (getStatus() > ThreadWaiting)
	    setStatus(getStatus()-1);

        // Put the thread on the ready queue, if appropriate
	if (getStatus() == ThreadReady)
	    readyQueue.append(this);

	dispatch();
    }
    

    /** 
        Selects a thread from the run queue and dispatches it. 

        If there is just one theread ready to run, reschedule the thread 
        currently on the processor.

        In addition to setting the correct thread status it must
        update the PTBR.
	
	@return SUCCESS or FAILURE

        @OSPProject Threads
    */
    public static int do_dispatch()
    {
        ThreadCB threadToDispatch=null;
        ThreadCB runningThread=null;
	TaskCB runningTask=null;
	try {
	    runningTask = MMU.getPTBR().getTask();
	    runningThread = runningTask.getCurrentThread();
	} catch(NullPointerException e) {}

        // If necessary, remove current thread from processor and 
        // reschedule it.
        if(runningThread != null) {
	    MyOut.print("osp.Threads.ThreadCB",
			"Preempting currently running " + runningThread);

	    runningTask.setCurrentThread(null);

	    MMU.setPTBR(null);

	    runningThread.setStatus(ThreadReady);
	    readyQueue.append(runningThread);
	}

        // Select thread from ready queue.
        threadToDispatch = (ThreadCB)readyQueue.removeHead();
        if(threadToDispatch == null) {
	    MyOut.print("osp.Threads.ThreadCB",
			"Can't find suitable thread to dispatch");
	    MMU.setPTBR(null);
	    return FAILURE;
	}

	// Put the thread on the processor.
	MMU.setPTBR(threadToDispatch.getTask().getPageTable());

	// set thread to dispatch as the current thread of its task
        threadToDispatch.getTask().setCurrentThread(threadToDispatch);

	// Set thread's status.
	threadToDispatch.setStatus(ThreadRunning);
            
        MyOut.print("osp.Threads.ThreadCB",
		    "Dispatching " + threadToDispatch);

	HTimer.set(150);

        return SUCCESS;
    }


    /**
       Called by OSP after printing an error message. The student can
       insert code here to print various tables and data structures in
       their state just after the error happened.  The body can be
       left empty, if this feature is not used.

       @OSPProject Threads
    */
    public static void atError()
    {
	// any code
    }

    /** Called by OSP after printing a warning message. The student
        can insert code here to print various tables and data
        structures in their state just after the warning happened.
        The body can be left empty, if this feature is not used.
       
        @OSPProject Threads
     */
    public static void atWarning()
    {
	// any code
    }
}


