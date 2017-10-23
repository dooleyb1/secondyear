import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    //Tests deleteAt() method from DoublyLinkedList.java
    @Test
    public void testDeleteAt()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        
        boolean testResult1 = true;
        testResult1 = testDLL.deleteAt(0);
        assertTrue("Checking if element at index 0 deleted successfully in a one element DLL", testResult1);
        
        boolean testResult2 = true;
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testResult2 = testDLL.deleteAt(0);
        assertTrue("Checking if element at index 0 deleted successfully in a DLL containing > 1 elements", testResult2);
        
        boolean testResult3 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.deleteAt(1);
        assertTrue("Checking if last element deleted successfully in a DLL containing 2 elements", testResult3);
        
        boolean testResult4 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.deleteAt(3);
        assertTrue("Checking if last element deleted successfully in a DLL containing > 2 elements", testResult4);
        
        boolean testResult5 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.deleteAt(2);
        assertTrue("Checking if element in middle of a DLL deleted successfully", testResult5);
        
        boolean testResult6 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testDLL.deleteAt(8);
        assertFalse("Checking if function returns false when desired index is out of bounds", testResult6);
    
    }
    
    //Tests deque() method from DoublyLinkedList.java
    @Test
    public void testDeque()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
        boolean testResult1 = (testDLL.dequeue()==null);
        assertTrue("Checking if deque method returns null when called on an empty DLL", testResult1);
        
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        boolean testResult2 = (testDLL.dequeue()==1);
        assertTrue("Checking deque method returns 1 when deque is called", testResult2);
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        boolean testResult3 = (testDLL.dequeue()==1);
        assertTrue("Checking deque method returns 1 when deque is called, and DLL is now empty", testResult3);
    
    }
    
  //Tests enqueue() method from DoublyLinkedList.java
    @Test
    public void testEnqueue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
    	testDLL.enqueue(1);
        boolean testResult1 = (testDLL.dequeue()==1);
        assertTrue("Checking if enqueue method successfully inserts 1 to DLL", testResult1);
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(6);
        testDLL.enqueue(1);
        testDLL.enqueue(9);
        testDLL.dequeue();
        boolean testResult2 = (testDLL.dequeue()==1);
        assertTrue("Checking enqueue method successfully inserts 3 elements correctly", testResult2);
    
    }
    
    //Tests get() method from DoublyLinkedList.java
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
    	 testDLL.enqueue(6);
         testDLL.enqueue(1);
         testDLL.enqueue(9);
        boolean testResult1 = (testDLL.get(2)==9);
        assertTrue("Checking if get method successfully returns index 2 (tail)", testResult1);
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(6);
       boolean testResult2 = (testDLL.get(0)==6);
       assertTrue("Checking if get method successfully returns index 0 from 1 element DLL", testResult2);
       
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.enqueue(6);
       testDLL.enqueue(1);
       testDLL.enqueue(9);
      boolean testResult3 = (testDLL.get(0)==6);
      assertTrue("Checking if get method successfully returns index 0 (head)", testResult3);
      
      testDLL = new DoublyLinkedList<Integer>();
      boolean testResult4 = (testDLL.get(0)==null);
      assertTrue("Checking if get method successfully returns null for empty DLL get() call", testResult4);
    
    }

}