// ----------------------------------------------------------
/**
 * Sorts an array of integers according to BubbleSort.
 *
 * @param a: An UNSORTED array of doubles.
 * @return after the method returns, the array must be in ascending sorted order.
 *
 * ----------------------------------------------------------
 * Approximate Mathematical Performance:
 * -------------------------------------

 *
 *  Performance: Best Case - O(n)
 *  			       Worst Case - O(n^2)
 *
 *
 */
static double [] bubbleSort (double a[]){

     int n = a.length;
     int i, j;
     double tmp;
     boolean swapped;

     //Increment over entire array
     for(i = 0; i < n; i++) {

         swapped = false;
         for(j = 0; j < n-i-1; j++) {

             //Swap a[j] with a[j+1] if larger
             if(a[j] > a[j+1]) {
                 tmp = a[j];
                 a[j] = a[j+1];
                 a[j+1] = tmp;
                 swapped = true;
             }
         }

         //If no elements swapped during inner loop, break
         if(!swapped)
             break;
     }
     return a;

}
