// ----------------------------------------------------------
/**
 * Sorts an array of integers according to QuickSort.
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
static double [] quickSort (double a[]){

    int low = 0;
    int high = a.length-1;

    qSort(a, low, high);
    return a;
}

/**
 * This method implements quickSort recursively for a given array of doubles.
 * @param a: Unsorted array of doubles.
 * @param low: Low index.
 * @param high: High index.
 */
private static void qSort(double a[], int low, int high) {

    if (low >= high)
        return;

    //Find pivot value
    int middle = low + (high - low) / 2;
    double pivot = a[middle];

    //Sort values < pivot to left and > pivot to right
    int i = low;
    int j = high;

    while(i<=j) {
        //Find low value !< pivot
        while (a[i] < pivot) {
            i++;
        }
        //Find high value !> pivot
        while(a[j] > pivot) {
            j--;
        }

        if(i <= j) {
            double tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i++;
            j--;
        }
    }

    //Recursively quicksort two sub parts
    if(low < j)
        qSort(a, low, j);

    if(high > i)
        qSort(a, i, high);

}
