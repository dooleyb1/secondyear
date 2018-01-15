/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Brandon Dooley (dooleyb1@tcd.ie)
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { 
    	return size() == 0; 
    }

    // return number of key-value pairs in BST
    public int size() {
    	return size(root); 
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null)
        	return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { 
    	return get(root, key); 
    }

    private Value get(Node x, Key key) {
        if(x == null) 
        	return null;
        
        int cmp = key.compareTo(x.key);
        
        
        if(cmp < 0) 
        	return get(x.left, key);
        
        else if(cmp > 0)
        	return get(x.right, key);
        
        else              
        	return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
    	
        if (val == null){
        	delete(key); 
        	return; 
        }
        
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
    	
        if (x == null) 
        	return new Node(key, val, 1);
        
        int cmp = key.compareTo(x.key);
        
        //If key < Node.key place node left
        if(cmp < 0) 
        	x.left  = put(x.left,  key, val);
        
        //Else if key > Node.key place node right
        else if(cmp > 0) 
        	x.right = put(x.right, key, val);
        
        //Else set Node.value = val (override value)
        else             
        	x.val   = val;
        
        //Update Node.N
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: O(n), unbalanced tree
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
      
    	if(root == null)
    		return -1;
    	
    	int h = heightOfNode(root);
    	return h;
    }
    

    /**
     * Tree height from given node t, using recursion
     *
     * @param t -  node from which height below is to be calculated
     * @return maxHeight - the highest height below node t
     *
     
     */
    public int heightOfNode(Node t){
    	if(t==null)
    		return -1;
    	
    	int heightLeft = heightOfNode(t.left);
    	int heightRight =  heightOfNode(t.right);
    	
    	int maxHeight;
    	
    	if(heightLeft>=heightRight)
    		maxHeight = heightLeft;
    	
    	else 
    		maxHeight = heightRight;
    	
    	return maxHeight+1;
    	
    }

    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median() {
      if (isEmpty()) 
    	  return null;
     
      else 
    	  return median(root);
    }
    
    private Key median (Node x) {
    	int medianPos = (int) ((size()-1)/2);
        return getKeyAt(medianPos);
    }
    
    public Key getKeyAt(int x) {
        return getKeyAt(root, x);
      }

    private Key getKeyAt(Node node, int x) {
    	if(node == null)
    		return null;
    	
    	//Get number of key-value pairs in left subtree
    	int sizeLeft = size(node.left);
    	
    	//If x is on left of current node
    	if(sizeLeft>x)
    		return getKeyAt(node.left, x);
    	
    	//If x is right of current node... adjusting for keys ignored on left
    	else if(sizeLeft<x)
    		return getKeyAt(node.right, (x-sizeLeft-1));
    	
    	else
    		return node.key;	
    	
    }

    /** Returns the key for a given node */
    public Key getNodeKey(Node x) {
    	if (x==null)
    		return null;
    	else
    		return x.key;
    }
    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
      if (isEmpty()) 
    	  return "()";
      
      else 
    	  return printKeysInOrder(root);
    }
    
    private String printKeysInOrder(Node node) {
    	
    	//Initialise string 
    	String resultString = "";
    	
    	if(node == null)
    		return "()";
    	
    	//Iterate through all subtrees recursively from node printing with the correct parenthesice's
    	else {
    		resultString += "(";
    		resultString += printKeysInOrder(node.left);
    		resultString += node.key;
    		resultString += printKeysInOrder(node.right);
    		resultString += ")";
    	}
    	
    	return resultString;
    	
    }
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
    	 return prettyPrintKeys(root, "");
    }
    
    private String prettyPrintKeys(Node node, String prefix) {
    	String resultString = "";
    	
    	if(node == null) 
    		return (prefix + "-null\n");
    	
    	else {
    		//Print first value of tree/subtree and pretty characters (-)
    		resultString += prefix + "-" + node.val + "\n";
    		//Recursively print left subtree and pretty characters ( |)
    		resultString += prettyPrintKeys(node.left, (prefix+" |"));
    		//Recursively print right subtree and pretty characters
    		resultString += prettyPrintKeys(node.right, (prefix+"  "));
    	}
    	
    	return resultString;
    }

    /**
     * Deteles a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
    	if (isEmpty() || !contains(key)) {
      	  return;
        }
        else
      	  root = delete(root, key);
    }
    
	private Node delete(Node node, Key key) {

		// Create comparison int for input key
		int cmp = key.compareTo(node.key);

		// If key is less than key of current node recursively search left subtree
		if (cmp < 0)
			node.left = delete(node.left, key);

		// If key is more than key of current node recursively search right subtree
		else if (cmp > 0)
			node.right = delete(node.right, key);

		// If key is equal to key of current node, replace with predecessor
		else {
			if (node.left == null)
				return node.right;

			if (node.right == null)
				return node.left;

			// Node to be deleted
			Node temp = node;

			// Set input node to the largest key <= to node to be deleted
			node = floor(temp.left, node.key);

			// Update left pointer
			node.left = deleteMax(temp.left);

			// Update right pointer
			node.right = temp.right;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

	/*
	 * Returns the largest key in the symbol table less than or equal to key
	 */
	public Node floor (Key key) {
		return floor(root, key);
	}

	private Node floor(Node node, Key key)
	{
		if (node == null) 
			return null;
		
		else {
			int cmp = key.compareTo(node.key);
			
			if (cmp == 0)
				return node;
			
			else if (cmp < 0)
				return floor(node.left, key);
	
			else {
				Node temp = floor(node.right, key);
				
				if (temp != null) 
					return temp;
				else 
					return node;
			}
		}
	}
	
	   /**
     * Removes the largest key after a given node
     *
     */
	
    private Node deleteMax(Node node) {
        if (node.right == null) 
        	return node.left;
        
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

}
