import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class KMPSearchTest {

  @Test
  public void testEmpty(){
    assertEquals("Empty text or pattern is invalid",-1,KMPSearch.searchFirst("",""));
    assertEquals("Empty text or pattern is invalid",0,KMPSearch.searchAll("",""));
    assertFalse("Empty text or pattern is invalid",KMPSearch.contains("",""));
  }

  // ----------------------------------------------------------
  /**
   *  Main Method.
   *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
   * @throws FileNotFoundException 
   *
   */
   public static void main(String[] args)
   {
   		System.out.println("Hello");
		String pat = args[0];
        String txt = args[1];

        System.out.println("Pattern entered = " + pat);
        System.out.println("Text entered = " + txt);
    }
}

