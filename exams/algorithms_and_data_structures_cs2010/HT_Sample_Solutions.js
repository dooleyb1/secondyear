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
     the same value appear in an array, they DO NOT maintain their respective
     order after sorting has occurred.

     Examples : Heap Sort, Quick Sort

--------------------------------------------------------------------------
** Question 2 ** - Binary Trees
--------------------------------------------------------------------------

--------------------------------------------------------------------------
** Question 3 ** - Shortest Paths
--------------------------------------------------------------------------

------------------------------------------------------------------------------
** Question 4 ** - Shortest Paths
------------------------------------------------------------------------------
