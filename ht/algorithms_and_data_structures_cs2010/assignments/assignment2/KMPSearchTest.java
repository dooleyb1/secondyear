import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/* MIT License
 *
 * Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

 /* Adapted from Sedgeick & Wayne - https://algs4.cs.princeton.edu/home/ */

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
    assertFalse("Checking boolean result if pattern is contained in text",KMPSearch.contains(txt,"needwhsu"));
  }

  @Test
  public void testDoesNotContain(){
    String pat = "youcantfindme";
    String txt = "haystackneedlesgahsasneedlenaaghdhasudasdneeeedlewqhdqhwneedle";

    assertEquals("Checking index result if pattern is not contained in text", -1,KMPSearch.searchFirst(txt,pat));
    assertEquals("Checking occurances count if pattern is not contained in text",0,KMPSearch.searchAll(txt,pat));
    assertFalse("Checking boolean result if pattern is not contained in text",KMPSearch.contains(txt,pat));
  }
}

