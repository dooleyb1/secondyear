// ----------------------------------------------------------
/**
 * Searches for an integer inside an array of integers.
 * This method is static, thus it can be called as Collinear.binarySearch(a,x)
 * @param a: A array of integers SORTED in ascending order.
 * @param x: An integer.
 * @return true if 'x' is contained in 'a'; false otherwise.
 *
 * ----------------------------------------------------------
 *
 * Approximate Mathematical Performance:
 * -------------------------------------
 *  Using an appropriate cost model, give the performance of your algorithm.
 *  Explain your answer.
 *
 *  Performance: Worst case - O(log n)
 *  			 Best case - O(1)
 *
 *  Explanation: Worst case - The worst case scenario is that the binarySearch runs through the division of all index's until it reaches the point when the
 *  						  startIndex == endIndex and no further division can be done. In this case the number will either be found, or the number is not
 *  						  a member of the array. Therefore if the array contains n values, let the amount of division searches (array access') be k, then
 *  						  k = log n.
 *
 *  			 Best case - The best case scenario is that the number is found at the case of the first division search (initial array access) and therefore
 *  						 the cost model can be considered 1.
 *
 */
static boolean binarySearch(int[] a, int x)
{
    //Code sampled from : http://www.java2novice.com/java-search-algorithms/binary-search/
    int startIndex = 0;
    int endIndex = a.length - 1;

    //Iterate from both sides of array
    while (startIndex <= endIndex) {

        int midPoint = (startIndex+endIndex)/2;

        //If element found, return true
        if (x == a[midPoint]) {
            return true;
        }

        //If element < midPoint, new endIndex midPoint-1 (left)
        else if (x < a[midPoint]) {
            endIndex = midPoint-1;
        }

        //Else element > midPoint, new startIndex midPoint+1 (right)
        else {
            startIndex = midPoint+1;
        }
    }
    return false;
}
