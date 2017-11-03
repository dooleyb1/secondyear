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
        testResult3 = testDLL.deleteAt(1);
        assertTrue("Checking if last element deleted successfully in a DLL containing 2 elements", testResult3);
        
        boolean testResult15 = false;
        testDLL = new DoublyLinkedList<Integer>();
        testResult3 = testDLL.deleteAt(1);
        assertFalse("Checking if returns false when attempted deletion in empty DLL", testResult15);
        
        boolean testResult10 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testResult10 = testDLL.deleteAt(40);
        assertFalse("Checking if last element deleted successfully in a DLL containing 2 elements", testResult10);
        
        boolean testResult11 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testResult11 = testDLL.deleteAt(-1);
        assertFalse("Checking if last element deleted successfully in a DLL containing 2 elements", testResult11);
        
        boolean testResult12 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testResult12 = testDLL.deleteAt(50);
        assertFalse("Checking if last element deleted successfully in a DLL containing 2 elements", testResult12);
        
        boolean testResult13 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testResult13 = testDLL.deleteAt(-10);
        assertFalse("Checking if last element deleted successfully in a DLL containing 2 elements", testResult13);
        
        boolean testResult4 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testResult4 =testDLL.deleteAt(3);
        assertTrue("Checking if last element deleted successfully in a DLL containing > 2 elements", testResult4);
        
        boolean testResult5 = true;
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        testResult5 =testDLL.deleteAt(2);
        assertTrue("Checking if element in middle of a DLL deleted successfully", testResult5);
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(3,4);
        boolean testResult6 = testDLL.deleteAt(8);
        assertFalse("Checking if function returns false when desired index is out of bounds", testResult6);
        
        boolean testResult16 = false;
        testDLL = new DoublyLinkedList<Integer>();
        testResult3 = testDLL.deleteAt(0);
        assertFalse("Testing deleteAt(0) when this.size == 0", testResult16);
    
    }
    
    //Tests deque() method from DoublyLinkedList.java
    @Test
    public void testDeque()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
    	Integer returnVal;
    	
    	returnVal = null;
        assertEquals("Checking if deque method returns null when called on an empty DLL", returnVal, testDLL.dequeue());
        

        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        returnVal = 1;
        assertEquals("Checking deque method returns 1 when deque is called", returnVal, testDLL.dequeue());
        
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        returnVal = 1;
        assertEquals("Checking deque method returns 1 when deque is called, and DLL is now empty", returnVal, testDLL.dequeue());
    
    }
    
  //Tests enqueue() method from DoublyLinkedList.java
    @Test
    public void testEnqueue()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        Integer returnVal;
        
    	testDLL.enqueue(1);
        returnVal = 1;
        assertEquals("Checking if enqueue method successfully inserts 1 to DLL", returnVal, testDLL.dequeue());
        
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(6);
        testDLL.enqueue(1);
        testDLL.enqueue(9);
        testDLL.dequeue();
        returnVal = 1;
        assertEquals("Checking enqueue method successfully inserts 3 elements correctly", returnVal, testDLL.dequeue());
    
    }
    
    //Tests get() method from DoublyLinkedList.java
    @Test
    public void testGet()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        Integer returnVal;
        
    	 testDLL.enqueue(6);
         testDLL.enqueue(1);
         testDLL.enqueue(9);
         returnVal = 9;
        assertEquals("Checking if get method successfully returns index 2 (tail)", returnVal, testDLL.get(2));
        
        testDLL = new DoublyLinkedList<Integer>();
       returnVal = null;
       assertEquals("Checking if get method successfully returns null", returnVal, testDLL.get(-1));
       
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.enqueue(6);
       testDLL.enqueue(1);
       returnVal = null;
       assertEquals("Checking if get method successfully returns null", returnVal, testDLL.get(40));
       
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.enqueue(6);
       testDLL.enqueue(1);
       returnVal = null;
       assertEquals("Checking if get method successfully returns null", returnVal, testDLL.get(50));
       
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.enqueue(6);
       testDLL.enqueue(1);
       returnVal = null;
       assertEquals("Checking if get method successfully returns null", returnVal, testDLL.get(-10));
       
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(6);
       returnVal = 6;
       assertEquals("Checking if get method successfully returns index 0 from 1 element DLL",returnVal, testDLL.get(0));
       
       
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.enqueue(6);
       testDLL.enqueue(1);
       testDLL.enqueue(9);
       returnVal = 6;
      assertEquals("Checking if get method successfully returns index 0 (head)", returnVal, testDLL.get(0));
      
      testDLL = new DoublyLinkedList<Integer>();
      testDLL.enqueue(6);
      testDLL.enqueue(1);
      testDLL.enqueue(9);
      testDLL.enqueue(6);
      testDLL.enqueue(1);
      testDLL.enqueue(9);
      returnVal = 9;
     assertEquals("Checking if get method successfully returns index 2", returnVal, testDLL.get(2));
      
      testDLL = new DoublyLinkedList<Integer>();
      returnVal = null;
      assertEquals("Checking if get method successfully returns null for empty DLL get() call", returnVal, testDLL.get(0));
    
    }
   
    
  //Tests isEmpty() method from DoublyLinkedList.java
    @Test
    public void testIsEmpty()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
    	 testDLL.enqueue(6);
         testDLL.enqueue(1);
         testDLL.enqueue(9);
         boolean testResult1 = testDLL.isEmpty();
        assertFalse("Checking if method successfully returns non-empty result", testResult1);
    
        testDLL = new DoublyLinkedList<Integer>();
        boolean testResult2 = testDLL.isEmpty();
       assertTrue("Checking if method successfully returns empty result", testResult2);
    }
    
  //Tests pop() method from DoublyLinkedList.java
    @Test
    public void testPop()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        Integer expectedResult;
        
    	 testDLL.insertBefore(0,1);
    	 testDLL.insertBefore(1,2);
    	 testDLL.insertBefore(2,3);
    	 expectedResult = 1;
         assertEquals("Checking if method successfully pops 1 from DLL of elements > 2", expectedResult, testDLL.pop());
    
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        expectedResult = 1;
        assertEquals("Checking if method successfully pops 1 from DLL of 2 elements", expectedResult, testDLL.pop());
       
	       testDLL = new DoublyLinkedList<Integer>();
	       testDLL.insertBefore(0,100);
	       testDLL.insertBefore(1,200);
	       testDLL.insertBefore(2,300);
	       expectedResult = 300;
	       testDLL.pop();
	       testDLL.pop();
	       assertEquals("Testing pop on 3 element stack", expectedResult, testDLL.pop());
       
	       testDLL = new DoublyLinkedList<Integer>();
	       testDLL.push(100);
	       testDLL.push(200);
	       testDLL.push(300);
	       expectedResult = 300;
	       assertEquals("Testing pop on 3 element stack", expectedResult, testDLL.pop());

       
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,1);
       expectedResult = 1;
       assertEquals("Checking if method successfully pops 1 from DLL of 1 element", expectedResult, testDLL.pop());
      
      testDLL = new DoublyLinkedList<Integer>();
      expectedResult = null;
      assertEquals("Checking if method successfully returns null for attempted pop of an empty set", expectedResult, testDLL.pop());
    }
    
  //Tests pop() method from DoublyLinkedList.java
    @Test
    public void testPush()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
    	 testDLL.insertBefore(0,1);
    	 testDLL.insertBefore(1,2);
    	 testDLL.insertBefore(2,3);
    	 testDLL.push(9);
        assertEquals("Checking if method successfully pushes 9 onto DLL of elements > 2", "9,1,2,3", testDLL.toString());
    
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.push(9);
       assertEquals("Checking if method successfully pushes 9 onto DLL of 2 elements", "9,1,2", testDLL.toString());
       
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,1);
       testDLL.push(9);
      assertEquals("Checking if method successfully pushes 9 onto DLL of 1 element", "9,1", testDLL.toString());
      
      testDLL = new DoublyLinkedList<Integer>();
      testDLL.push(9);
     assertEquals("Checking if method successfully pushes 9 onto empty DLL","9", testDLL.toString());
    }
    
    //Tests reverse() method from DoublyLinkedList.java
    @Test
    public void testReverse()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
    	 testDLL.insertBefore(0,1);
    	 testDLL.insertBefore(1,2);
    	 testDLL.insertBefore(2,3);
    	 testDLL.insertBefore(3,4);
    	 testDLL.reverse();
        assertEquals("Checking if method successfully reverses DLL '1,2,3,4' to '4,3,2,1'", "4,3,2,1", testDLL.toString());
    
        
    }
    
  //Tests toString() method from DoublyLinkedList.java
    @Test
    public void testToString()
    {
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
       
    	 testDLL.insertBefore(0,1);
    	 testDLL.insertBefore(1,2);
    	 testDLL.insertBefore(2,3);
    	 testDLL.insertBefore(3,4);
        assertEquals("Checking if method successfully converts a DLL of '1,2,3,4' to a string containing '1,2,3,4'", "1,2,3,4", testDLL.toString());
     
    }

}
