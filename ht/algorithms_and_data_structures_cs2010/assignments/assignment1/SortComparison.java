import java.util.Arrays;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2018
 */

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/insertion-sort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){

    	double result[] = new double[a.length];
 		System.arraycopy(a, 0, result, 0, a.length);
        int n = result.length;

		//Iterate through input array
		for(int i=0; i<n; i++)
		{		
			double temp = result[i];
			//Compare a[i] with a[i-1]
			int j = i-1;
			
			//Iterate through all possible comparisons and swap accordingly	
			while(j>=0 && result[j] > temp)
			{
				result[j+1] = result[j];
				j = j-1;
			}
			result[j+1] = temp;
		}
		return result;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Method implemented using https://www.programcreek.com/2012/11/quicksort-array-in-java/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
	

   	    double result[] = new double[a.length];
 		System.arraycopy(a, 0, result, 0, a.length);
    	int low = 0;
    	int high = result.length-1;
    	
    	qSort(result, low, high);
    	return result;
    }
    
    /**
     * This method implements quickSort recursively for a given array of doubles.
     * @param a: Unsorted array of doubles.
     * @param low: Low index.
     * @param high: High index.
     */
    private static void qSort(double a[], int low, int high) {
    	
    	if (a == null || a.length == 0)
			return;
 
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

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] mergeSort (double a[]){

    	 double result[] = new double[a.length];
  		 System.arraycopy(a, 0, result, 0, a.length);
		 int low = 0;
		 int high = result.length - 1;
		 
		 double tmp[] = new double[a.length];
		 
		 mSort(result, tmp, low, high);
		 return result;
	
    }
    
    /**
     * Performs mergesort recursively within the given indices low and high
     * @param a: An unsorted array of doubles
     * @param tmp: Temp array to be used for the mergesort.
     * @param low: Lower index
     * @param high: Higher index
     */
    private static void mSort(double a[], double tmp[], int low, int high) {
    	
    	//If low is smaller than high array is unsorted
		if(low < high) {
			//Get index of middle element
			int middle = low + (high - low) / 2;
			//Sort left array
			mSort(a, tmp, low, middle);
			//Sort right array
			mSort(a, tmp, middle + 1, high);
			//Combine them both
			merge(a, tmp, low, middle, high);
			
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
    private static void merge(double a[], double tmp[], int low, int middle, int high) {
    	
    	 // Copy both parts into the tmp array
        for (int i = low; i <= high; i++) {
            tmp[i] = a[i];
        }
        
        int i = low;
        int j = middle + 1;
        int k = low;
        
    	// Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (tmp[i] <= tmp[j]) {
                a[k] = a[i];
                i++;
            } else {
               a[k] = tmp[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            a[k] = tmp[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.
    	
    }

    /**
     * Sorts an array of doubles using Shell Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/shellsort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] shellSort (double a[]){

    	 double result[] = new double[a.length];
   		 System.arraycopy(a, 0, result, 0, a.length);	
		 int n = a.length;
		 
		 //Start with a gap of a.length/2, keep reducing by half
		 for(int gap = n/2; gap > 0; gap /= 2) {
			 
			 //Perform insertion sort within the gaps 
			 for(int i = gap; i < n; i += 1) {
				
				 //Store a[i] into tmp
				 double tmp = result[i];
				 
				 //Shift earlier gap-sorted elements up until a[i] position is found
				 int j;
				 for(j=i; j>=gap && result[j-gap] > tmp; j -= gap) {
					 
					 result[j] = result[j-gap];
					 
				 }
				 
				 //Place tmp in its correct location
				 result[j] = tmp;
			 }
		 }
		 return result;
    }

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/selection-sort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){

    	double result[] = new double[a.length];
   		System.arraycopy(a, 0, result, 0, a.length); 
    	int n = a.length;
         double tmp;
         //Increment the unsorted array index by one
         for(int i = 0; i < n-1; i++) {
        	 //Find index of min element 
        	 int min = i;
        	 for(int j = i+1; j < n; j++) {
        		 if(result[j] < result[min])
        			 min = j;
        	 }
        	 
        	 //Swap the found min with the element a[i]
        	 tmp = result[min];
        	 result[min] = result[i];
        	 result[i] = tmp;
         }
         return result;

    }

    /**
     * Sorts an array of doubles using Bubble Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/bubble-sort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] bubbleSort (double a[]){
    	
    	 double result[] = new double[a.length];
    	 System.arraycopy(a, 0, result, 0, a.length);
         int n = a.length;
         int i, j;
         double tmp;
         boolean swapped;
         
         //Increment over entire array
         for(i = 0; i < n; i++) {
        	 
        	 swapped = false;
        	 for(j = 0; j < n-i-1; j++) {
        		 
        		 //Swap a[j] with a[j+1] if larger
        		 if(result[j] > result[j+1]) {
        			 tmp = result[j];
        			 result[j] = result[j+1];
        			 result[j+1] = tmp;
        			 swapped = true;
        		 }
        	 }
        	 
        	 //If no elements swapped during inner loop, break
        	 if(!swapped)
        		 break;
         }
         return result;
		 
    }


    public static void main(String[] args) {

    	double jumbledArray[] = {21.3, 5.6, 1.2, 90.5, 10.1, 60.4, 99.0, 0.2, -1.0, 2.1, 1293.2, 6.3};
    	double resultArray[] = new double[jumbledArray.length];
    	
    	System.out.println("Jumbled array is: \n " + Arrays.toString(jumbledArray) + "\n");
    	
    	//double insSortArray[] = SortComparison.insertionSort(jumbledArray);
    	//System.out.println("Insertion sort on above array produces: \n " + Arrays.toString(insSortArray) + "\n");
    	
    	double quickSortArray[] = SortComparison.quickSort(jumbledArray);
    	System.out.println("Quick sort on above array produces: \n " + Arrays.toString(quickSortArray) + "\n");
    	
    	double mergeSortArray[] = SortComparison.mergeSort(jumbledArray);
    	System.out.println("Merge sort on above array produces: \n " + Arrays.toString(mergeSortArray) + "\n");
    	
    	System.out.println("Jumbled array is: \n " + Arrays.toString(jumbledArray) + "\n");
    	
    	//double shellSortArray[] = SortComparison.shellSort(jumbledArray);
    	//System.out.println("Shell sort on above array produces: \n " + Arrays.toString(shellSortArray) + "\n");
    	
    	//double selectionSortArray[] = SortComparison.selectionSort(jumbledArray);
    	//System.out.println("Selection sort on above array produces: \n " + Arrays.toString(selectionSortArray) + "\n");
    	
    	//double bubbleSortArray[]= SortComparison.bubbleSort(jumbledArray);
    	//System.out.println("Bubble sort on above array produces: \n " + Arrays.toString(bubbleSortArray) + "\n");
    	
    	//double x[] = {104.0};
    	//double y[] = SortComparison.insertionSort(x);
    	//System.out.println("One element array is : \n " + Arrays.toString(x) + "\n");
    	//System.out.println("Insertion sort on above array produces: \n " + Arrays.toString(y) + "\n");
    	
    }

 }//end class

