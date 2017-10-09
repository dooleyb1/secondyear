// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * Experimental Performance:
     * -------------------------
     *  Write the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.392
     *  2000              2.898
     *  4000              22.862	
     *  5000			  42.798
     *
     *  Assuming that the running time of your algorithm is of the form aN^b,
     *  estimate 'b' and 'a' by fitting a line to the experimental points:
     *
     *  b = 2.93297
     *  a = 6.228 * 10^-10 
     *
     *  What running time do you predict using your results for input size 5000?
     *  What is the actual running time you get with such an input?
     *  What is the error in percentage?
     *
     *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
     *
     *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
     *  ------------------------------------------------------------------------------------------------
     *  5000              43.98622157                         42.798                           2.701
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: O(n^3)
     *
     *  Explanation: This algorithm has an order of growth of n^3. The program contains three loops iterating 
     *  			 over every element in every array and comparing it to every element in all the other arrays. 
     *  			 Therefore if there is n=100 elements in total, there will be 100^3 array accesses. Therefore 
     *  			 this program does not scale well, as can be seen in the above results.q
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
    	int count = 0;
    	for(int i=0; i<a1.length;i++){
    		for(int k=0;k<a2.length;k++){
    			for(int j=0;j<a3.length;j++){
    				
    				if((a1[i]*(-1)) + (a2[k]*(2)) + (a3[j]*(-1)) == 0){
    					count++;
    				}
    			}
    		}
    	}
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     * Experimental Performance:
     * -------------------------
     *  Measure the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.102
     *  2000              0.233
     *  4000              0.919
     *  5000              1.514
     *
     *
     *  Compare Implementations:
     *  ------------------------
     *  Show the sppedup achieved by this method, using the times you got from your experiments.
     *
     *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
     *  ---------------------------------------------------------------------------------
     *  1000              3.843
     *  2000              12.438
     *  4000              24.877
     *  5000              28.268
     *
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: O(n^2 * logn)
     *
     *  Explanation: Sorting array a3 has an order of growth of O(n^2) time as explained earlier on. This is 
     *  			 then followed by a binary search in a3 for T-(a2[j] + a1[i]) which has an order of growth 
     *  			 of O(logn). So the overall order of growth of the program is therefore O(n^2 * logn)
     *
     *
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
      //Sorting the first array
      sort(a1);
      int count = 0;
      int key;
      
      //Looping through arrays a2 and a3 - O(n^2)
      for(int i=0;i<a2.length;i++){
    	  for(int j=0;j<a3.length;j++){
    		  //Binary search for x1 = 2*x2 -1*x3- O(logN)
    		  
    		  key = (2*a2[i] - a3[j]);
    		  if(binarySearch(a1,key)){
    			  count++;
    		  }
    	  }
      }
      
      return count;
    }

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
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: Best Case - O(n)
     *  			 Worst Case - O(n^2)
     *
     *  Explanation: Best Case - The best case scenario is that the given array is already sorted and therefore the only efforts made by the algorithm is comparisons
     *  						 which can be considered a cost of 1 then the best case scenario is n actions.
     *  			
     *  			 Worst Case - The worst case scenario is that the given array is an array that has been sorted in reverse order. This will entail the
     *  						  maximum number of array access' and swaps in order for the array to be arranged accordingly. Since each element will need to be
     *  						  compared to every other element and moved accordingly it will result in a quadratic running time of n^2.
     *
     */
    static void sort(int[] a)
    {
      //TODO: implement this method
    	// Code taken from : http://www.java2novice.com/java-sorting-algorithms/insertion-sort/
    	int temp;
        for (int i = 1; i < a.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(a[j] < a[j-1]){
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }

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
      //TODO: implement this method
      //Code sampled from : http://www.java2novice.com/java-search-algorithms/binary-search/
    	int startIndex = 0;
        int endIndex = a.length - 1;
        while (startIndex <= endIndex) {
            int midPoint = (startIndex+endIndex)/2;
            if (x == a[midPoint]) {
                return true;
            }
            if (x < a[midPoint]) {
                endIndex = midPoint-1;
            } else {
                startIndex = midPoint+1;
            }
        }
        return false;
    }

}