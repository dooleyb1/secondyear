// ----------------------------------------------------------
/**
 * Sorts an array of integers according to ShellSort.
 *
 * @param a: An UNSORTED array of doubles.
 * @return after the method returns, the array must be in ascending sorted order.
 *
 * ----------------------------------------------------------
 * Approximate Mathematical Performance:
 * -------------------------------------

 *
 *  Performance: Best Case - O(n log n)
 *  			       Worst Case - O(n^2)
 *
 *
 */
static double [] shellSort (double a[]){

     int n = a.length;

     //Start with a gap of a.length/2, keep reducing by half
     for(int gap = n/2; gap > 0; gap /= 2) {

         //Perform insertion sort within the gaps
         for(int i = gap; i < n; i += 1) {

             //Store a[i] into tmp
             double tmp = a[i];

             //Shift earlier gap-sorted elements up until a[i] position is found
             int j;
             for(j=i; j>=gap && a[j-gap] > tmp; j -= gap) {

                 a[j] = a[j-gap];

             }

             //Place tmp in its correct location
             a[j] = tmp;
         }
     }
     return a;
}
