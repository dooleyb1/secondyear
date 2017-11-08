import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Brandon Dooley (#16327446)
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int size;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
      size = 0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification:
     *  The worst-case runtime is a constant value O(1) since there is only one operation necessary to verify whether the DLL is empty. If the head is empty
     *  the DLL therefore contains no elements.
     */
    public boolean isEmpty()
    {
      if (this.head == null)
    	   return true;
      
      else 
    	  return false;
    }

    
    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: O(n-1)
     *
     * Justification: Worst case scenario is that the element will have to be placed second last, in this case the program will have
     * 					to iterate through the entire DLL from 0 to n-1 and then place the element accordingly.
     */
    public void insertBefore( int pos, T data ) 
    {
      
      //Adds element to head of the list, updates others
      if(pos<=0 || (pos>1 && this.size == 0)){ 
    	//Instantise new node
      	  DLLNode newNode = new DLLNode(data, null, null);
      	  
      	  //Update pointers of the new node
      	  newNode.next = this.head;
      	  if(this.size!=0)
      		  this.head.prev = newNode;
      	  
    
      	
      	  //Update the head pointer of the DLL
      	  this.head = newNode;
      	  
      	  if(this.size==0){
     		  this.tail=this.head;
     	  }
      	  
      	  //Increments size of the DLL
      	  this.size++;;
      }
      
      //Adds element to the end of the array
      else if(pos==this.size || pos>this.size){
    	//Instantise new node
      	  DLLNode newNode = new DLLNode(data, null, null);
      	  
      	  //Update pointers of the new node
      	  newNode.prev = this.tail;
      	  this.tail.next = newNode;
      	  this.tail = newNode;
      	  
      	  //Increments size of the DLL
      	  this.size++;
      }
      
      else{
    	  
    	  //Create temp node from head pointer
    	  DLLNode tmp = this.head;
    	  
    	  //Instantise new node
      	  DLLNode newNode = new DLLNode(data, null, null);
      	 
    	  //Find element which newNode is to be placed before
    	  for(int i=0;i<pos;i++){
    		 tmp = tmp.next; 
    	  }
    	  
    	  //Update pointers of the newNode
    	  newNode.next = tmp;
    	  newNode.prev = tmp.prev;
    	  
    	  //Update pointers of the affected nodes
    	  tmp.prev.next = newNode;
    	  tmp.prev = newNode;
    	  
    	  
    	  //Updates size of DLL
    	  this.size++;
    	  
      }
      
      return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: O(n-1)
     *
     * Justification: Worst case scenario is that the desired element is the second last element. As a result the program then needs to iterate through the 
     * 					DLL from element 0 to element n-1 (second last) and then return such element.
     *
     * Worst-case precise runtime cost: TODO
     *
     * Justification:
     *  TODO
     */
    public T get(int pos) 
    {
      //Returns data of head element
      if(pos==0 && this.size!=0){
    	 return this.head.data;
      }
      
      //Returns data of tail element
      else if (pos==this.size-1 && this.size!=0){
    	  return this.tail.data;
      }
      
      //Returns data at desired position
      else if(pos>0 && pos<this.size-1){
    	 //Creates temp DLLNode pointing to the head node, to be used to find the data
          DLLNode tmp = this.head;
        	
          //Iterates through list until it gets to node at specified position
          for(int i=0;i<pos;i++){
       		 tmp = tmp.next; 
       	  }
        	
          return tmp.data;
      }
      
      else{
    	  return null;
      }
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: O(n-1)
     *
     * Justification: If deleting an element is considered to be a constant time, then the Worst-case asymptotic runtime cost is O(n-1). This
     * 					would occur if the element to be deleted was the second last element of a DLL. The program would have to iterate 
     * 					through the DLL from 0 to n-1 and then perform the deletion.
     */
    public boolean deleteAt(int pos) 
    {
      //Deleting first element (head)
      if(pos==0){
    	  
    	  //Handles a DLL containing 1 element
    	  if(this.size==1){
    		  this.head = null;
    		  this.tail = null;
    		  this.size--;
    		  return true;
    	  }
    	  
    	  else if(this.size==0){
    		  return false;
    	  }
    	  //Handles a DLL containing elements > 1
    	  else{
    		  //Update prev pointer of element in pos+1
    		  this.head.next.prev = null;
    		  //Update head pointer for the DLL
    		  this.head = this.head.next;
    		  this.size--;
    		  return true;
    	  } 
      }
      
      //Deleting last element of a DLL
      else if(pos == this.size-1 && this.size!=0){
    	  
    	  //Handles a DLL containing only 2 elements
    	  if(this.size == 2){
    		  this.head.next = null;
    		  this.head.prev = null;
    		  this.tail = this.head;
    		  this.size--;
    		  return true;
    	  }
    	  
    	  else{
    		  //Updates prev pointer of emelement in pos-1
    		  this.tail.prev.next = null;
    		  //Updates tail pointer for the DLL
    		  this.tail = this.tail.prev;
    		  this.size--;
    		  return true;
    	  }
      }
      
      //Deleting element at defined position
      else if(pos > 0 && pos <this.size){
    	//Creates temp DLLNode pointing to the head node, to be used to find the data
          DLLNode tmp = this.head;
        	
          //Iterates through list until it gets to node at specified position
          for(int i=0;i<pos;i++){
       		 tmp = tmp.next; 
       	  }
          
          //Updates pointers of pos-1 and pos +1 
          tmp.prev.next = tmp.next;
          tmp.next.prev = tmp.prev;
          this.size--;
    	  return true;
      }
      else{
    	  return false;
      }
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic runtime cost: O(n)
     *
     * Justification: If the operation of performing one element swap is considered to be a constant time, then the worst case runtime 
     * 					for this function depends entirely on the amount of elements in the DLL i.e O(n).
     */
    public void reverse()
    {
    	DLLNode current = this.head;
		DLLNode temp = null;
		
		while(current!=null){
			temp = current.prev;  			 //swap the next and prev pointer
			current.prev = current.next;
			current.next = temp;
			current = current.prev;
		}
		
		temp = this.head;
		this.head = this.tail;
		this.tail = temp;
	
    }


    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification: This operation will have a constant worst-case asymptotic runtime cost since the element is always being placed at the 
     * 					start of the DLL and as a result no iterations/variable outcomes occur.
     */
    public void push(T item) 
    {
    	DLLNode newNode = new DLLNode(item, null, null);
    	 
        //Since at beginning, prev is always null
        newNode.prev = null;
 
        //Link the new node to the old head
        newNode.next = this.head;
 
        //Change prev of old head
        if (this.head != null) {
            this.head.prev = newNode;
        }
        
        //Update new head
        this.head = newNode;
        this.size++;
        
        if(this.size==2)
        	this.tail = newNode.next;
        
        else if(this.size==1)
        	this.tail = this.head;
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification: This operation will have a constant worst-case asymptotic runtime cost since the element is always being popped from 
     * 						the start of the DLL and as a result no iterations/variable outcomes occur.
     */
    public T pop() 
    {
 
    	T data = null;
    	
    	if(this.size!=0){
    		if(this.size == 1){
    			data = this.head.data;
    			this.head = null;
    			this.tail = null;
    		}
    		
    		else{
    			DLLNode newFirstNode = this.head.next;
    			data = this.head.data;
    			this.head = newFirstNode;
    		}
    		this.size--;
    		return data;
    	}
    	return data;

    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification: This operation will have a constant worst-case asymptotic runtime cost since the element is always being placed at the 
     * 					end of the DLL, thus creating a FIFO queue. As a result no iterations/variable outcomes occur.
     */
    public void enqueue(T item) 
    {
    	//Extracts the old tail
    	DLLNode oldLast = this.tail;
    	//Inserts the new tail
        this.tail = new DLLNode(item, oldLast, null);

        //Checks if list was originally empty
        if (this.size == 0) 
        	this.head = this.tail;
        
        else          
        	oldLast.next = this.tail;
        	this.size++;
        
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: O(1)
     *
     * Justification: This operation will have a constant worst-case asymptotic runtime cost since the element is always being returned from
     * 					the beginning of the DLL, thus creating a FIFO queue. As a result no iterations/variable outcomes occur.
     */
    public T dequeue() 
    {
    	T item;
    	
    	//If empty return null
    	if(size==0)
    	  return null;
      
    	else{
    	  //Extract head of DLL
    	  item = this.head.data;
    	  //Adjust new head pointer
    	  this.head = this.head.next;
    	  this.size--;
    	  
    	  //If DLL is now empty set tail == null
    	  if(this.size==0)
    		  this.tail = null;
    	  
    	  return item;
    	}
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}


