// ----------------------------------------------------------
/**
 * LSD sorts fixed-length strings in ascending order
 *
 * @param a: A array of strings to be sorted.
 * @param W: All strings are of length W.
 *
 * ----------------------------------------------------------
 *
 * Approximate Mathematical Performance:
 * -------------------------------------
 *  Using an appropriate cost model, give the performance of your algorithm.
 *  Explain your answer.
 *
 *  Performance: Worst case - 2W (N + R)
 *
 *
 */
 public static void LSDSort(String[] a, int W){

    //ASCII Radix
    int R = 256;
    int N = a.length;
    String[] aux = new String[N];

    for(int d = W-1; d>=0; d--){

      int[] count = new int[R+1];

      //Increment count of letter at d
      for(int i = 0; i < N; i++)
        count[a[i].charAt(d) + 1]++;

      for(int r = 0; r < R; r++)
        count[r+1] += count[r];

      //Place elements into auxilary array
      for(int i = 0; i < N; i++)
        aux[count[a[i].charAt(d)]++] = a[i];

      //Copy auxilary array back over to originak
      for(int i = 0; i < N; i++)
        a[i] = aux[i];

    }

 }
