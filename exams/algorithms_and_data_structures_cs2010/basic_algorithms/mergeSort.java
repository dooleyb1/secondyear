// ----------------------------------------------------------
/**
 * Sorts an array of integers according to MergeSort.
 *
 * @param a: An UNSORTED array of doubles.
 * @return after the method returns, the array must be in ascending sorted order.
 *
 * ----------------------------------------------------------
 * Approximate Mathematical Performance:
 * -------------------------------------

 *
 *  Performance: Best Case - O(n log n)
 *  			       Worst Case - O(n log n)
 *
 *
 */
static double [] mergeSort (double a[]){

     double tmp[] = new double[a.length];

     mSort(a, tmp,  0,  a.length - 1);
     return a;

}

/**
 * Performs mergesort recursively within the given indices low and high
 * @param a: An unsorted array of doubles
 * @param tmp: Temp array to be used in the mergesort.
 * @param low: Lower index
 * @param high: Higher index
 */
private static void mSort(double a[], double tmp[], int low, int high) {

    if(low < high)
    {
        int middle = (low + high) / 2;
        mSort(a, tmp, low, middle);
        mSort(a, tmp, middle + 1, high);
        merge(a, tmp, low, middle + 1, high);
    }

}

/**
 * Combines recursive mergesorts into the one larger array
 * Algorithm implemented using http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
 * @param a: An unsorted array of doubles.
 * @param tmp: Temp array to be used in the mergesort.
 * @param low: Index of lower element.
 * @param middle: Index of middle element.
 * @param high: Index of higher element.
 */
private static void merge(double a[], double tmp[], int left, int right, int rightEnd )
{
    int leftEnd = right - 1;
    int k = left;
    int num = rightEnd - left + 1;

    while(left <= leftEnd && right <= rightEnd)
        if(a[left] <= a[right])
            tmp[k++] = a[left++];
        else
            tmp[k++] = a[right++];

    while(left <= leftEnd)    // Copy rest of first half
        tmp[k++] = a[left++];

    while(right <= rightEnd)  // Copy rest of right half
        tmp[k++] = a[right++];

    // Copy tmp back
    for(int i = 0; i < num; i++, rightEnd--)
        a[rightEnd] = tmp[rightEnd];
}
