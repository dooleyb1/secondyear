import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class KMPSearch {

  /*
   * Bus Service Questions:
   *
   * 1. How many total vehicles is there information on?
   *    //TODO
   *
   * 2. Does the file contain information about the vehicle number 16555?
   *    //TODO
   *
   * 3. Locate the first record about a bus heading to HAMPTON PARK
   *    //TODO
   *
   * 4. Does the file contain information about the vehicle number 9043409?
   *    //TODO
   */

   /*
    * Code partially taken from https://algs4.cs.princeton.edu/53substring/KMP.java.html
    * and https://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/ 
    * however, rewritten as to gain an undertsanding of what was going on.
    *
  /

   /*
    * The method checks whether a pattern pat occurs at least once in String txt.
    *
    */

  public static int[][] dfa;       // the KMP automoton

  public static boolean contains(String txt, String pat) {
    //If pattern is found in string, return true
    if(searchFirst(txt, pat) != -1)
      return true;

    else
    return false;
  }

  /*
   * This method creates a dfa which is then used to process the pattern string.
   * Standard radix of 256 
   */
  public static void createDFA(String pat){

        int R = 256;

        // build DFA from pattern
        int m = pat.length();
        dfa = new int[R][m]; 
        dfa[pat.charAt(0)][0] = 1; 
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) 
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases. 
            dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
            x = dfa[pat.charAt(j)][x];     // Update restart state. 
        } 
  }

  /*
   * The method returns the index of the first ocurrence of a pattern pat in String txt.
   * It should return -1 if the pat is not present
   */
  public static int searchFirst(String txt, String pat) {
    //First create dfa
    if(txt.length() == 0 || pat.length() == 0)
    	return -1;

    createDFA(pat);

    // simulate operation of DFA on text
        int m = pat.length();
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) return i - m;    // found
        return -1;                    // not found
  }

  /*
   * The method returns the number of non-overlapping occurences of a pattern pat in String txt.
   */
  public static int searchAll(String txt, String pat) {
  	if(txt.length() == 0 || pat.length() == 0)
    	return 0;

    //First create dfa
    createDFA(pat);
    String tmp = txt;
    int count = 0;
    int result;
    boolean carryOn = true;

    //Recursively search for string, slicing 
    while(carryOn){
    	result = searchFirst(tmp, pat);

    	//If pattern found in text continue
    	if(result != -1){
    		count++;
    		//**
    		tmp = tmp.substring(result+pat.length());
    	}
    	else
    		carryOn = false;
    }

    if(count!=0)
    	return count;

    else 
    	return count;
  }
}