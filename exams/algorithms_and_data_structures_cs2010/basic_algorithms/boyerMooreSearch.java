// ----------------------------------------------------------
/**
 * Boyer-Moore search searches for substrings in a given string.
 * When find a character not in the pattern, can skip up to M
 * characters (so no need to loop through all N characters)
 *
 * @param txt: String to search.
 * @param pat: Pattern to search for.
 * @return i: Index of first occurance, else returns txt.length.
 *
 * ----------------------------------------------------------
 *
 * Approximate Mathematical Performance:
 * -------------------------------------
 *  Using an appropriate cost model, give the performance of your algorithm.
 *  Explain your answer.
 *
 *  Performance: Worst case - M * N
 *
 *
 */
 public static int boyerMooreSearch(String txt, String pat){

    int N = txt.length();
    int M = pat.length();
    int skip;

    //Pre-compute index of rightmose occurence of character c in pattern
    int[] right = new int[M];

    for (int c = 0; c < R; c++)
      right[c] = -1;
    for (int j = 0; j < M; j++)
      right[pat.charAt(j)] = j;


    //Iterate over txt, incrementing by skip
    for(int i = 0; i < N-M; i += skip){
      skip = 0;

      //Iterate backwards over pattern, searching for last letter first
      for(int j = M-1; j >= 0; j--){
        //If letters do not match, update skip
        if(pat.charAt(j) != txt.charAt(i+j)){
          skip = Math.max(1, j=right[txt.charAt(i+j)]);
          break;
        }
      }

      //Match
      if(skip == 0)
        return i;
    }
 }
