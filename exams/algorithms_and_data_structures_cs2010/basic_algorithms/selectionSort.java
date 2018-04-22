// ----------------------------------------------------------
/**
 * Sorts an array of integers according to SelectionSort.
 *
 * @param a: An UNSORTED array of doubles.
 * @return after the method returns, the array must be in ascending sorted order.
 *
 * ----------------------------------------------------------
 * Approximate Mathematical Performance:
 * -------------------------------------

 *
 *  Performance: Best Case - O(n^2)
 *  			       Worst Case - O(n^2)
 *
 *
 */
static double [] selectionSort (double a[]){

    int n = a.length;
    double tmp;

    //Increment the unsorted array index by one
     for(int i = 0; i < n-1; i++) {
         //Find index of min element
         int min = i;
         for(int j = i+1; j < n; j++) {
             if(a[j] < a[min])
                 min = j;
         }

         //Swap the found min with the element a[i]
         tmp = a[min];
         a[min] = a[i];
         a[i] = tmp;
     }
     return a;
