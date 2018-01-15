import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  TODO
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
	//~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
    	new BST<Integer, Integer>();
    }
    
  //~ isEmpty() ........................................................
    @Test
    public void testIsEmpty()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	boolean result1 = bst.isEmpty();
    	assertTrue("Testing isEmpty() method on an empty BST", result1);
    	
    	bst.put(1, 10);
    	boolean result2 = bst.isEmpty();
    	assertFalse("Testing isEmpty() method on a non-empty BST", result2);
    }

    
  //~ size() ........................................................
    @Test
    public void testSize()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	int result1 = bst.size();
    	assertEquals("Testing size() on a empty bst", 0, result1);
    	
    	bst.put(1, 10);
    	bst.put(4, 19);
    	int result2 = bst.size();
    	assertEquals("Testing isEmpty() method on a non-empty BST", 2, result2);
    }
    
    
  //~ contains() ........................................................
    @Test
    public void testContains()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	boolean result1 = bst.contains(6);
    	assertFalse("Testing contains() on an empty bst",result1);
    	
    	bst.put(8, 10);
    	boolean result2 = bst.contains(8);
    	assertTrue("Testing contains(8) method on a bst containing one key - 8", result2);
    	
    	bst.put(4, 8);
    	bst.put(12, 6);
    	boolean result3 = bst.contains(4);
    	assertTrue("Testing contains(4) method on a bst containing one keys; 8,4,10 (left branch)", result3);
    	
    	boolean result4 = bst.contains(12);
    	assertTrue("Testing contains(12) method on a bst containing one keys; 8,4,10 (right branch)", result4);
    	
    	boolean result5 = bst.contains(60);
    	assertFalse("Testing contains(60) method on a bst containing one keys; 8,4,10 (left branch)", result5);
    	
    }
    
    //~ get() ........................................................
    @Test
    public void testGet()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	Integer result1 = bst.get(7);
    	assertEquals("Testing get() on an empty bst", null, result1);
    	
    	bst.put(8, 10);
    	Integer result2 = bst.get(8);
    	assertEquals("Testing get(8) method on a bst containing one key - 8", (Integer) 10, result2);
    	
    	bst.put(4, 8);
    	bst.put(12, 6);
    	Integer result3 = bst.get(4);
    	assertEquals("Testing get(4) method on a bst containing one keys; 8,4,10 (left branch)", (Integer) 8, result3);
    	
    	Integer result4 = bst.get(12);
    	assertEquals("Testing get(12) method on a bst containing one keys; 8,4,10 (right branch)", (Integer) 6, result4);
    	
    	Integer result5 = bst.get(60);
    	assertEquals("Testing get(60) method on a bst containing one keys; 8,4,10 (left branch)", null, result5);
    }
    
  //~ get() ........................................................
    @Test
    public void testPut()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	bst.put(4, 8);
    	bst.put(4, null);
    	boolean result1 = bst.contains(4);
    	assertFalse("Testing put(Key, null) on a bst of 1 element, should delete element", result1);
    	
    	bst = new BST<Integer, Integer>();
    	bst.put(4, 8);
    	boolean result2 = bst.contains(4);
    	assertTrue("Testing put(4, 8) on an empty bst", result2);
    	
    	bst.put(12, 6);
    	boolean result3 = bst.contains(12);
    	assertTrue("Testing put(12, 6) on a 1 element bst (right branch insertion test)", result3);
    	
    	bst.put(1, 6);
    	boolean result4 = bst.contains(1);
    	assertTrue("Testing put(1, 6) on a 2 element bst (left branch insertion test)", result3);
    	
    	
    	bst.put(12, 60);
    	Integer result5 = bst.get(12);
    	assertEquals("Testing put(12,60) method on a bst containing the key 12 already (override test)", (Integer) 60, result5);
    	
    }
    
  //~ get() ........................................................
    @Test
    public void testHeight()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	Integer result1 = bst.height();
    	Integer comparisonInt = -1;
    	assertEquals("Testing height() on empty bst", comparisonInt, result1);
    	
    	bst.put(5, 1);
    	bst.put(6, 1);
    	bst.put(4, 1);
        Integer result2 = bst.height();
        assertEquals("Testing height() on perfect triangle bst of 3 elements", (Integer) 1, result2);
        
        bst.put(3, 1);
        Integer result3 = bst.height();
        assertEquals("Testing height() on bst containing keys placed in order (5,6,4,3) - left branch height test", (Integer) 2, result3);
    	
        bst.put(7, 1);
        bst.put(8, 1);
        Integer result4 = bst.height();
        assertEquals("Testing height() on bst containing keys placed in order (5,6,4,3,7,8) - right branch height test", (Integer) 3, result4);
    }
    
    
    //~ median() ........................................................
    @Test
    public void testMedian()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	Integer result1 = bst.median();
    	assertEquals("Testing median() on empty bst", null, result1);
    	
    	bst.put(5, 1);
    	Integer result2 = bst.median();
    	assertEquals("Testing median() on bst containing 1 element", (Integer) 5, result2);
    	
    	bst.put(4, 1);
    	Integer result3 = bst.median();
    	assertEquals("Testing median() on bst containing 2 elements", (Integer) 4, result3);
    	
    	bst.put(3,1);
    	bst.put(8,1);
    	bst.put(2,1);
    	Integer result4 = bst.median();
    	assertEquals("Testing median() on bst containing 5 elements", (Integer) 4,result4);
    }
    
  //~ getKeyAt() ........................................................
    @Test
    public void testGetKeyAt()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	Integer result0 = bst.getKeyAt(6);
    	assertEquals("Testing getKeyAt(6) on empty bst", null, result0);
    	
    	bst.put(6, 1);
    	Integer result1 = bst.getKeyAt(0);
        assertEquals("Testing getKeyAt(0) on 1 element bst - key 6", (Integer) 6, result1);
        
        bst.put(7, 1);
    	Integer result2 = bst.getKeyAt(1);
        assertEquals("Testing getKeyAt(1) on two element bst - keys 6,7", (Integer) 7, result2);
        
        bst.put(2, 1);
    	Integer result3 = bst.getKeyAt(1);
        assertEquals("Testing getKeyAt(1) on three element bst - keys 2,6,7", (Integer) 6, result3); 
    }
    
    //~ printKeysInOrder() ........................................................
    @Test
    public void testPrintKeysInOrder()
    {
    	BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	String result1 = bst.printKeysInOrder();
    	assertEquals("Testing printKeysInOrder() on an empty bst", "()", result1);
    	
    	bst.put(6,1);
    	String result2 = bst.printKeysInOrder();
    	assertEquals("Testing printKeysInOrder() on a 1 element bst - key 6", "(()6())", result2);
    	
    	bst.put(4, 1);
    	String result3 = bst.printKeysInOrder();
    	assertEquals("Testing printKeysInOrder() on a 2 element bst - keys 6,4", "((()4())6())", result3);
    	
    	bst.put(8, 1);
    	String result4 = bst.printKeysInOrder();
    	assertEquals("Testing printKeysInOrder() on a 3 element bst - keys 6,4,8", "((()4())6(()8()))", result4);
    }
       
    
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
	@Test
	public void testPrettyPrint() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking pretty printing of empty tree", "-null\n", bst.prettyPrintKeys());

		// -7
		// |-3
		// | |-1
		// | | |-null
		bst.put(7, 7); // | | -2
		bst.put(8, 8); // | | |-null
		bst.put(3, 3); // | | -null
		bst.put(1, 1); // | -6
		bst.put(2, 2); // | |-4
		bst.put(6, 6); // | | |-null
		bst.put(4, 4); // | | -5
		bst.put(5, 5); // | | |-null
						// | | -null
						// | -null
						// -8
						// |-null
						// -null

		String result = "-7\n" + " |-3\n" + " | |-1\n" + " | | |-null\n" + " | |  -2\n" + " | |   |-null\n"
				+ " | |    -null\n" + " |  -6\n" + " |   |-4\n" + " |   | |-null\n" + " |   |  -5\n"
				+ " |   |   |-null\n" + " |   |    -null\n" + " |    -null\n" + "  -8\n" + "   |-null\n"
				+ "    -null\n";
		assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
	}

	/**
	 * <p>
	 * Test {@link BST#delete(Comparable)}.
	 * </p>
	 */
	@Test
	public void testDelete() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(7, 7); // _7_
		bst.put(8, 8); // / \
		bst.put(3, 3); // _3_ 8
		bst.put(1, 1); // / \
		bst.put(2, 2); // 1 6
		bst.put(6, 6); // \ /
		bst.put(4, 4); // 2 4
		bst.put(5, 5); // \
						// 5

		assertEquals("Checking order of constructed tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
				bst.printKeysInOrder());

		bst.delete(9);
		assertEquals("Deleting non-existent key", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting node with single child", "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

		bst.delete(3);
		assertEquals("Deleting node with two children", "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
	}
	
	//~ floor() ........................................................
    @Test
    public void testFloor()
    {
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 assertEquals("Testing floor() method on an empty bst", null, bst.getNodeKey(bst.floor(7)));
    	
    	 bst.put(6,1);
    	 bst.put(1, 2);
    	 bst.put(3,2);
    	 bst.put(9, 6);
    	 assertEquals("Testing floor() method on an a 4 element bst - value is element of tree", (Integer) 6, bst.getNodeKey(bst.floor(6)));
    	 assertEquals("Testing floor() method on an a 4 element bst - value is not an element of tree", (Integer) 9, bst.getNodeKey(bst.floor(10)));
    	 bst.delete(3);
    	 assertEquals("Testing floor() method on an a 3 element bst - value less than key", (Integer) 1, bst.getNodeKey(bst.floor(3)));
    }
}
