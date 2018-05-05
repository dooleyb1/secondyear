// ----------------------------------------------------------
/**
 * Sorts an array of integers according to InsertionSort.
 * This method is static, thus it can be called as Collinear.sort(a)
 * @param a: An UNSORTED array of integers.
 * @return after the method returns, the array must be in ascending sorted order.
 *
 * ----------------------------------------------------------
 *
 * Approximate Mathematical Performance:
 * -------------------------------------
 *
 *  Performance: Best Case - O(n)
 *  			       Worst Case - O(n^2)
 *
 *
 */
static void insertionSort(int[] a)
{
    // Code taken from : http://www.java2novice.com/java-sorting-algorithms/insertion-sort/
    int temp;

    //Iterate over array
    for (int i = 1; i < a.length; i++) {

        //Iterate backwards from current index
        for(int j = i ; j > 0 ; j--){

            //If current element is less than current element -1, swap
            if(a[j] < a[j-1]){
                temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }
    }
}
