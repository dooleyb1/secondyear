
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import java.io.BufferedWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.util.Calendar;


public class TST<Value> {

  /*
   * Bus Service Questions:
   * 1. How many unique destinations is there in the file?
   *    51.

   * 2. Is there a bus going to the destination "SOUTHSIDE"?
   *    No.

   * 3. How many records is there about the buses going to the destination beginning with "DOWN"?
   *    Only destination is 'DOWNTOWN' which has 70 records.
   *
   * Google Books Common Words Questions:
   * 4. How many words is there in the file?
   *    96765

   * 5. What is the frequency of the word "ALGORITHM"?
   *    14433021

   * 6. Is the word "EMOJI" present?
   *    No.

   * 7. IS the word "BLAH" present?
   *    No.
   
   * 8. How many words are there that start with "TEST"?
   *    38
   */

  //Implemented using Sedgewick and Waynes version found here https://algs4.cs.princeton.edu/52trie/TST.java.html
  

  /* A Node in your trie containing a Value val and a pointer to its children */
  private static class TrieNode<Value> {
		    private char c;                        // character
        private TrieNode<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
  }

  /* a pointer to the start of your trie */
  private TrieNode<Value> root = new TrieNode<Value>();
  /* initialise size to 0 */
  private int n = 0;

  //Create empty string symbol table
  public TST(){

  }

  /*
   * Returns the number of words in the trie
   */
  public int size() {
    return n;
  }

  /*
   * returns true if the word is in the trie, false otherwise
   */
  public boolean contains(String key) {
    	if(key == "")
        return false;

      else
        return (get(key) != null);
  }

  /*
   * return the value stored in a node with a given key, returns null if word is not in trie
   */
  public Value get(String key) {
    if(key == "")
      return null;

    TrieNode<Value> x = new TrieNode<Value>();
    x = get(root, key, 0);

    if(x == null)
    	return null;

    else
    	return x.val;
  }

  /*
   * return the subtrie corresponding to given key
   */
  public TrieNode<Value> get(TrieNode<Value> x, String key, int d){
  	if(x == null)
      return null;

    char c;
  	c = key.charAt(d);

  	//If character is less than node x's character, go left
  	if(c<x.c)
  		return get(x.left, key, d);

  	//If character is more than node x's character, go right
  	else if(c>x.c)
  		return get(x.right, key, d);

  	//Otherwise, if d is still within range, go middle
  	else if(d<key.length()-1)
  		return get(x.mid, key, d+1);

  	//Otherwise, at end. Return x
  	else 
  		return x;
  }

  /*
   * stores the Value val in the node with the given key
   */
  public void put(String key, Value val) {

  	if(!contains(key))
  		n++;

  	root = put(root, key, val, 0);
  }

  private TrieNode<Value> put(TrieNode<Value> x, String key, Value val, int d){
  	char c = key.charAt(d);

  	//If null node, create new node for char c 
  	if(x == null){
  		x = new TrieNode<Value>();
  		x.c = c;
  	}

  	//If char is less than char of x, go left
  	if(c<x.c)
  		x.left = put(x.left, key, val, d);

  	//If char is more than char of x, go right
  	else if(c>x.c)
  		x.right = put(x.right, key, val, d);

  	//If d is still within range, go mididle
  	else if(d<key.length() - 1)
  		x.mid = put(x.mid, key, val, d+1);

  	//Otherwise return current node
  	else 
  		x.val = val;

  	return x;
  }

  /*
   * returns the linked list containing all the keys present in the trie
   * that start with the prefix passes as a parameter, sorted in alphabetical order
   */
  public LinkedList<String> keysWithPrefix(String prefix) {

    LinkedList<String> linkedList = new LinkedList<String>();
    TrieNode<Value> x = get(root, prefix, 0);

    //If no keys found, return null LinkedList
    if(x == null)
    	return linkedList;

     //If x contains the prefix, add prefix to linked list
    Value val = get(prefix);

    if(val != null)
      linkedList.add(prefix);


    //Get remaining keys with prefix from x.mid onwards
    getKeysWithPrefix(x.mid, new StringBuilder(prefix), linkedList);

    return linkedList;
  }

  private void getKeysWithPrefix(TrieNode<Value> x, StringBuilder prefix, LinkedList<String> list){
  	if(x == null)
  		return;

  	//Search left for keys with prefix
  	getKeysWithPrefix(x.left, prefix, list);

  	//If node has a value, push prefix+c onto list
  	if(x.val != null)
  		list.add(prefix.toString() + x.c);

  	//Search middle for keys containing prefix+c
  	getKeysWithPrefix(x.mid, prefix.append(x.c), list);

  	//Remove c from end of prefix and search right
  	prefix.deleteCharAt(prefix.length()-1);
  	getKeysWithPrefix(x.right, prefix, list);
  }
}