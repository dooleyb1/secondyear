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

   @Test
  public void testContains(){
    String pat = "needle";
    String txt = "haystackneedlesgahsasneedlenaaghdhasudasdneeeedlewqhdqhwneedle";
    assertEquals("Checking index result if pattern is contained in text", 8,KMPSearch.searchFirst(txt,pat));
    assertEquals("Checking occurances count if pattern is contained in text",3,KMPSearch.searchAll(txt,pat));
    assertFalse("Checking boolean result if pattern is contained in text",!KMPSearch.contains(txt,pat));
  }

  @Test
  public void testDoesNotContain(){
    String pat = "youcantfindme";
    String txt = "haystackneedlesgahsasneedlenaaghdhasudasdneeeedlewqhdqhwneedle";

    assertEquals("Checking index result if pattern is not contained in text", -1,KMPSearch.searchFirst(txt,pat));
    assertEquals("Checking occurances count if pattern is not contained in text",0,KMPSearch.searchAll(txt,pat));
    assertFalse("Checking boolean result if pattern is not contained in text",KMPSearch.contains(txt,pat));
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
   }
}

