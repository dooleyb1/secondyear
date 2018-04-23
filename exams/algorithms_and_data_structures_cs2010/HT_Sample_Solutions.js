--------------------------------------------------------------------------
** Question 1 ** - Sorting Algorithms
--------------------------------------------------------------------------

/*  a) Explain brute-force approach to algorithm design and give a well-known
 *      example of a brute-force algorithm.
 */

  /*
   * Selection Sort is an example of a brute-force sorting algorithm.
   *
   * Selection Sort iterates over the array, finding the smallest element
   * and swapping it with the element in the first index, then the second
   * smallest element with the second index and so on.
   *
   * Worst Case - O(n^2)
   */
  public static int[] selectionSort(int[] a){

    int n = a.length();
    int temp;

    //Iterate over array
    for(int i=0; i<n-1; i++){
      //Find index of min element
      int min = i;
      for(int j=i; j<n-1; j++){
        if(a[j] < a[min])
          min = j;
      }

      //Swap the found min with the a[i] element
      temp = a[i];
      a[i] = a[min];
      a[min] = temp;
    }
    return a;
  }

  /*  b) List 4 algorithms you could use to sort a number of objects in
   *     ascending order. Compare the approaches with respect to their best
   *     case and worst case running time.
   */

   1) Insertion Sort - Small arrays, in (almost) order.
   2) Quick Sort - General purpose, when space is tight.
   3) Merge Sort - General purpose, stable.
   4) Radix Sort - When the range of elements is small.

   1) Insertion Sort - Best = O(n)
                       Worst = O(n^2)

   2) Quick Sort -     Best = O(n log n)
                       Worst = O(n^2)

   3) Merge Sort -     Best = O(n log n)
                       Worst = O(n^2)

   4) Radix Sort -     Best = O(nk)
                       Worst = O(nk)

   /*  c) Briefly explain in English how each of the sorting algorithms
    *     studied work.
    */


    ------------------------------------------------------------------------
    1) Merge Sort:
    ------------------------------------------------------------------------
       This is a divide and conquer method. The array divides
       itself in half and calls merge sort on recursively on the new array,
       each individual array is then sorted and merged back into one array.

       Best - O(n log n)
       Worst - O(n^2)
       Space (Worst) - O(n)

   ------------------------------------------------------------------------
   2) Quick Sort:
   ------------------------------------------------------------------------
      This is a divide and conquer method. The array picks a pivot element,
      and then repeatedly partitions the array around this pivot element.
      Each sub-section of the array a new pivot is picked. This is repeated
      until all sub-sections contain one element, which is then the ordered
      array.

      Best - O(n log n)
      Worst - O(n^2)
      Space (Worst) - O(log(n))


    ------------------------------------------------------------------------
    3) Insertion Sort:
    ------------------------------------------------------------------------
       Loop over each index of the array, each iteration fixing the next
       element into its correct place in the array of previously sorted
       elements. Repeat this until there are no elements left to be checked.

       Best - O(n)
       Worst - O(n^2)
       Space (Worst) - O(1)

     ------------------------------------------------------------------------
     4) Selection Sort:
     ------------------------------------------------------------------------
        Selection sort involves repeatedly fining the minimum element from
        an unsorted part and putting it at the beggining of the sorted array.
        The algorithm maintains two arrays, the sorted sub array and the
        unsorted sub array.

        Best - O(n^2)
        Worst - O(n^2)
        Space (Worst) - O(1)

      ------------------------------------------------------------------------
      5) Bubble Sort:
      ------------------------------------------------------------------------
         Bubble sort involves repeatedly sorting adjacent elements, two at
         a time. This is performed over multiple passes of the array. The
         algorithm is complete when a full pass is complete and no swaps
         are made.

         Best - O(n)
         Worst - O(n^2)
         Space (Worst) - O(1)

 /*  d) Explain what is meant by a stable sorting algorithm. Provide an
  *     example of a stable algorithm and a non-stable algorithm.
  */

  1) Stable Algorithm:

    A sorting algorithm is said to be stable if, when two elements of
    the same value appear in an array, they maintain their respective order
    even after sorting has occurred.

    Examples :  Insertion Sort, Merge Sort, Bubble Sort

  2) Unstable Algorithm:

     A sorting algorithm is said to be unstable if, when two elements of
     the same value appear in a Sort, Bubble Sort
                      n array, they DO NOT maintain their respective
     order after sorting has occurred.

     Examples : Heap Sort, Quick Sort

--------------------------------------------------------------------------
** Question 2 ** - String Algorithms
--------------------------------------------------------------------------

/*  a) Explain in English or write code how key-index counting algorithm works
 */

 Key-Index counting is based on comparing the keys between a specific range.
 It works by counting the number of objects that have the same key value.
 It then uses this count array ti perform some arithmetic to work out the
 correct position for the elements.

 For simplicity, consider the data in the range 0 to 9.
 Input data: 1, 4, 1, 2, 7, 5, 2

1) Take a count array to store the count of each unique object.

  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  2  0   1  1  0  1  0  0

2) Modify the count array such that each element at each index stores the
   sum of previous counts.

  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  4  4  5  6  6  7  7  7

  The modified count array indicates the position of each object in
  the output sequence.

3) Output each object from the input sequence followed by decreasing
   its count by 1.

 Process the input data: 1, 4, 1, 2, 7, 5, 2.
 Position of 1 is 2.
 Put data 1 at index 2 in output.
 Decrease count by 1 to place next data 1 at an index 1 smaller than this index.

 /*  b) What is meant by radix sort, give an example of an algorithm that
  *     uses Radix Sort.
  */

  The idea of Radix Sort is to do digit by digit sort starting from least
  significant digit to most significant digit. Radix sort uses counting sort
  as a subroutine to sort.

  Original, unsorted list:
  170, 45, 75, 90, 802, 24, 2, 66

  //Sorting by least significant digit (1s place) gives:
  //[*Notice that we keep 802 before 2, because 802 occurred before 2 in
  // the original list, and similarly for pairs 170 & 90 and 45 & 75.]

  170, 90, 802, 2, 24, 45, 75, 66

  //Sorting by next digit (10s place) gives:
  //[*Notice that 802 again comes before 2 as 802 comes before 2 in
  // the previous list.]

  802, 2, 24, 45, 66, 170, 75, 90

  Sorting by most significant digit (100s place) gives:

  2, 24, 45, 66, 75, 90, 170, 802

  Examples: Most-Significant-Digit (MSD) Radix Sort

  /*  c) What are LSD and MSD, and what is the difference between them? When
   *    would you use each?.
   */

   1) Least Significant Digit (LSD) Radix Sort:

   This is a fast stable sorting algorithm which can be used to sort keys in
   integer representation order. Keys may be a string of characters, or numerical
   digits within a given radix. The processing of keys begins at the least
   significant digit and proceeds to the most significant digit.

   You would use this sort when you want elements to be returned within a
   numerical order e.g 1,2,3,4,5,6

   2) Most Significant Digit (MSD) Radix Sort:

   This is a fast stable sorting algorithm which can be used to sort keys in
   lexicographic order (e.g 1,10,12,2,20,30). Unlike a LSD radix sort, MSD
   DOES NOT preserve the original order of duplicate keys. An MSD sort starts
   processing keys from the most significant digit and proceeds to the least
   significant digit.

   You would use this sort when you want elements to be returned within a
   numerical order e.g 1,2,3,4,5,6

   /*  d) Discuss the performance of LSD compared to the performance of Insert
    *     or merge or quick sort.
    */

    LSD radix sort serves as an alternative high-performance sorting algorithm
    to the likes of heapsort and mergesort that require O(n log n) comparisons.
    Comparison sorts can do no better than O(n log n) execution time but offer
    the flexibility of being able to sort with respect to more complicated
    orderings than a lexicographic one.

    An LSD radix sort operates in O(nw) time, where n is the number of words
    and w is the average key length.


    /*  e) List 4 algorithms you could use to search for a substring in a larger
     *     string. Compare performance in time and space of these algorithms.
     *     Discuss situations where one approach is preffered over another.
     */

     ------------------------------------------------------------------------
     1) Brute Force:
     ------------------------------------------------------------------------
        This method involves iterating over the entire string that is to be
        searched, continuouslly trying to find the needle in the haystack.
        Effecient for small haystacks with small needles. This method involves
        keeping a backup buffer of previously matched characters.

        Worst - O(M * N)
        Space (Worst) - O(M)

      ------------------------------------------------------------------------
      2) Knuth-Morris-Pratt (KMP):
      ------------------------------------------------------------------------
         This method is some-what a brute force method however, it prevents
         the need for a backup buffer by using a Deterministic Final State
         Automaton (DFA). This keeps track of states, and transitions between
         states.

         Worst - O(M + N)
         Space (Worst) - O(R*M)

       ------------------------------------------------------------------------
       3) Boyer-Moore:
       ------------------------------------------------------------------------
          When find a character not in pattern, can skip up to M characters,
          this prevents the need to loop through all N characters. This method
          doesnt look through characters in order, instead it starts from the
          back and looks at the last character in the pattern first and see if
          its a match. Uses a backup buffer.

          Worst - O(M * N)
          Space (Worst) - O(M)

        ------------------------------------------------------------------------
        4) Rabin-Karp:
        ------------------------------------------------------------------------
           Compute a hash of the individual characters of the pattern. Compute
           a hash of every letter within the haystack. If pattern hash = text
           substring hash, check for match.

           Worst - O(7 * N))
           Space (Worst) - O(M)

   /*  f) List 4 algorithms you could use to search for a substring in a larger
    *     string. Compare performance in time and space of these algorithms.
    *     Discuss situations where one approach is preffered over another.
    */

--------------------------------------------------------------------------
** Question 3 ** - Shortest Paths
--------------------------------------------------------------------------

------------------------------------------------------------------------------
** Question 4 ** - Shortest Paths
------------------------------------------------------------------------------
