import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TSTTest {

  @Test
  public void testEmpty(){
    TST<Long> trie = new TST<>();
    assertEquals("size of an empty trie should be 0",0, trie.size());
    assertFalse("searching an empty trie should return false",trie.contains(""));
    assertNull("getting from an empty trie should return null",trie.get(""));
  }

  @Test
  public void testOneElement(){
    TST<Integer> trie = new TST<>();
    trie.put("One", 1);
    assertEquals("Size of trie containing 'One' should be 1",1, trie.size());
    assertTrue("Searching if trie contains 'One' should be true",trie.contains("One"));
    assertFalse("Searching if trie contains 'Hello' should be false",trie.contains("Hello"));
    assertFalse("Searching if trie contains 'Onet' should be false",trie.contains("Onet"));
    assertEquals("Getting value when key 'One' exists",(Integer) 1, trie.get("One"));
    assertNull("Getting value when key 'Onet' doesn't exist", trie.get("Onet"));
    assertNull("Getting value when key 'Hello' doesn't exist", trie.get("Hello"));
  }

  @Test
  public void testTwoElements(){
    TST<Integer> trie = new TST<>();
    trie.put("One", 1);
    trie.put("Two", 5);
    assertEquals("Size of trie containing 'One', 'Two' should be 2",2, trie.size());
    assertTrue("Searching if trie contains 'One' should be true",trie.contains("One"));
    assertTrue("Searching if trie contains 'Two' should be true",trie.contains("Two"));
    assertFalse("Searching if trie contains 'Hello' should be false",trie.contains("Hello"));
    assertFalse("Searching if trie contains 'Onet' should be false",trie.contains("Onet"));
    assertFalse("Searching if trie contains 'Twowhat' should be false",trie.contains("Twowhat"));
    assertEquals("Getting value when key 'Two' exists",(Integer) 5, trie.get("Two"));
    assertNull("Getting value when key 'Onet' doesn't exist", trie.get("Onet"));
    assertNull("Getting value when key 'Twowhatt' doesn't exist", trie.get("Twowhatt"));
    assertNull("Getting value when key 'Hello' doesn't exist", trie.get("Hello"));
  }

  @Test
  public void testMultipleElements(){
    TST<Integer> trie = new TST<>();
    trie.put("One", 1);
    trie.put("Two", 5);
    trie.put("Twofold", 23);
    trie.put("Whatsit", 9);
    trie.put("Xylophone", 63);
    trie.put("Onedance", 20);
    trie.put("One", 28);
    assertEquals("Size of trie - should be 6",6, trie.size());
    assertTrue("Searching if trie contains 'One' should be true",trie.contains("One"));
    assertTrue("Searching if trie contains 'Two' should be true",trie.contains("Two"));
    assertTrue("Searching if trie contains 'Xylophone' should be true",trie.contains("Xylophone"));
    assertTrue("Searching if trie contains 'Xylophone' should be true",trie.contains("Onedance"));
    assertFalse("Searching if trie contains 'Hello' should be false",trie.contains("Hello"));
    assertFalse("Searching if trie contains 'Onet' should be false",trie.contains("Onet"));
    assertFalse("Searching if trie contains 'Twowhat' should be false",trie.contains("Twowhat"));
    assertFalse("Searching if trie contains 'Onedanc' should be false",trie.contains("Onedanc"));
    assertEquals("Getting value when key 'One' exists, multiple times and should be replaced",(Integer) 28, trie.get("One"));
    assertEquals("Getting value when key 'Whatsit' exists",(Integer) 9, trie.get("Whatsit"));
    assertEquals("Getting value when key 'Xylophone' exists",(Integer) 63, trie.get("Xylophone"));
    assertNull("Getting value when key 'Onet' doesn't exist", trie.get("Onet"));
    assertNull("Getting value when key 'Twowhatt' doesn't exist", trie.get("Twowhatt"));
    assertNull("Getting value when key 'Hello' doesn't exist", trie.get("Hello"));
  }
}

