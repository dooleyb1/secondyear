import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Brandon Dooley
 *  @version HT 2018
 *  
 *  
 *  Find below the performance analysis of each respective algorithm using the supplied text files as described
 *  per CS2010 HT Assignment 1
 *  
 *  
 *  					| Insert (ns)	| Quick (ns)	| Merge (ns)	| Shell (ns)	|Selection (ns)	| Bubble (ns)	|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  10 Random			|	2254	  	|	2904		|	6928		|	3024		|				|				|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  100 Random			|	33136		| 	14086		|	80953		|	22249		|				|				|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Random			|	1082535		|	223910		|	180424		|	351685		|				|				|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Few Unique		|	616409		|	79167		|	141096		|	361519		|				|				|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Nearly Ordered	|	117106		|	9633		|	133719		|	355053		|				|				|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Reverse Order	|	14199		|	5648		|	12593		|	20574		|				|				|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Sorted			|	664			|	5609		|	11326		|	22303		|				|				|
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

        int n = a.length;

		//Iterate through input array
		for(int i=0; i<n; i++)
		{		
			double temp = a[i];
			//Compare a[i] with a[i-1]
			int j = i-1;
			
			//Iterate through all possible comparisons and swap accordingly	
			while(j>=0 && a[j] > temp)
			{
				a[j+1] = a[j];
				j = j-1;
			}
			a[j+1] = temp;
		}
		return a;
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
     * Implemented using https://www.geeksforgeeks.org/merge-sort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
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

    /**
     * Sorts an array of doubles using Shell Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/shellsort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
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

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * Implemented using https://www.geeksforgeeks.org/selection-sort/
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
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

    public static double[] populateArray(File file, int n) throws FileNotFoundException {
    	
    	double[] result = new double[n];
    	Scanner in = new Scanner(file);
    	
    	for(int i=0; i<result.length; i++) {
    		result[i] = in.nextDouble();
    	}
    	
    	//Uncomment for printing 
    	/*
    	for (double d : result) {
            System.out.println(d); 
        }
        */
    	
    	in.close();
    	return result;
    }
    
    /*
     * Calculates the total of 3 seperate runs of insertion sort on a given array.
     */
    public static long insertionSortTimer(double[] a) {
    	
    	long startTime, endTime, performanceTime;
    	long total = 0;
    	
    	//Run experiment three times
    	for(int i=0; i<3; i++) {
    		//Create duplicate of array, since sorts in place
    		double[] x = new double[a.length];
    		System.arraycopy(a, 0, x, 0, a.length);
    		
    		//Time start clock
    		startTime = 0;
    		startTime = System.nanoTime();
    		
    		x = SortComparison.insertionSort(a);
        	
        	//Time end clock
        	endTime = 0;
        	endTime = System.nanoTime();
        	
        	//p(t) = endTime - startTime
        	performanceTime = endTime - startTime;
        	
        	total+= performanceTime;
    	}
    	return total;
    }
    
    /*
     * Calculates the total of 3 seperate runs of quick sort on a given array.
     */
    public static long quickSortTimer(double[] a) {
    	
    	long startTime, endTime, performanceTime;
    	long total = 0;
    	
    	//Run experiment three times
    	for(int i=0; i<3; i++) {
    		//Create duplicate of array, since sorts in place
    		double[] x = new double[a.length];
    		System.arraycopy(a, 0, x, 0, a.length);
    		
    		//Time start clock
    		startTime = 0;
    		startTime = System.nanoTime();
    		
    		x = SortComparison.quickSort(a);
        	
        	//Time end clock
        	endTime = 0;
        	endTime = System.nanoTime();
        	
        	//p(t) = endTime - startTime
        	performanceTime = endTime - startTime;
        	
        	total+= performanceTime;
    	}
    	return total;
    }
    
    /*
     * Calculates the total of 3 seperate runs of merge sort on a given array.
     */
    public static long mergeSortTimer(double[] a) {
    	
    	long startTime, endTime, performanceTime;
    	long total = 0;
    	
    	//Run experiment three times
    	for(int i=0; i<3; i++) {
    		//Create duplicate of array, since sorts in place
    		double[] x = new double[a.length];
    		System.arraycopy(a, 0, x, 0, a.length);
    		
    		//Time start clock
    		startTime = 0;
    		startTime = System.nanoTime();
    		
    		x = SortComparison.mergeSort(a);
        	
        	//Time end clock
        	endTime = 0;
        	endTime = System.nanoTime();
        	
        	//p(t) = endTime - startTime
        	performanceTime = endTime - startTime;
        	
        	total+= performanceTime;
    	}
    	return total;
    }

    /*
     * Calculates the total of 3 seperate runs of shell sort on a given array.
     */
    public static long shellSortTimer(double[] a) {
    	
    	long startTime, endTime, performanceTime;
    	long total = 0;
    	
    	//Run experiment three times
    	for(int i=0; i<3; i++) {
    		//Create duplicate of array, since sorts in place
    		double[] x = new double[a.length];
    		System.arraycopy(a, 0, x, 0, a.length);
    		
    		//Time start clock
    		startTime = 0;
    		startTime = System.nanoTime();
    		
    		x = SortComparison.shellSort(a);
        	
        	//Time end clock
        	endTime = 0;
        	endTime = System.nanoTime();
        	
        	//p(t) = endTime - startTime
        	performanceTime = endTime - startTime;
        	
        	total+= performanceTime;
    	}
    	return total;
    }

    /*
     * Calculates the total of 3 seperate runs of selection sort on a given array.
     */
    public static long selectionSortTimer(double[] a) {
    	
    	long startTime, endTime, performanceTime;
    	long total = 0;
    	
    	//Run experiment three times
    	for(int i=0; i<3; i++) {
    		//Create duplicate of array, since sorts in place
    		double[] x = new double[a.length];
    		System.arraycopy(a, 0, x, 0, a.length);
    		
    		//Time start clock
    		startTime = 0;
    		startTime = System.nanoTime();
    		
    		x = SortComparison.selectionSort(a);
        	
        	//Time end clock
        	endTime = 0;
        	endTime = System.nanoTime();
        	
        	//p(t) = endTime - startTime
        	performanceTime = endTime - startTime;
        	
        	total+= performanceTime;
    	}
    	return total;
    }

    /*
     * Calculates the total of 3 seperate runs of bubble sort on a given array.
     */
    public static long bubbleSortTimer(double[] a) {
    	
    	long startTime, endTime, performanceTime;
    	long total = 0;
    	
    	//Run experiment three times
    	for(int i=0; i<3; i++) {
    		//Create duplicate of array, since sorts in place
    		double[] x = new double[a.length];
    		System.arraycopy(a, 0, x, 0, a.length);
    		
    		//Time start clock
    		startTime = 0;
    		startTime = System.nanoTime();
    		
    		x = SortComparison.bubbleSort(a);
        	
        	//Time end clock
        	endTime = 0;
        	endTime = System.nanoTime();
        	
        	//p(t) = endTime - startTime
        	performanceTime = endTime - startTime;
        	
        	total+= performanceTime;
    	}
    	return total;
    }
    
    public static long avgTimeFor(double[] a, int sortMethod) {
    	
    	
    	long avg = 0;
    	long total = 0;
    	
    	switch(sortMethod) {
    	case 0:
    		total = insertionSortTimer(a);
    		break;
    	case 1:
    		total = quickSortTimer(a);
    		break;
    	case 2:
    		total = mergeSortTimer(a);
    		break;
    	case 3:
    		total = shellSortTimer(a);
    		break;
    	case 4:
    		total = selectionSortTimer(a);
    		break;
    	case 5:
    		total = bubbleSortTimer(a);
    		break;
    	default:
    		total = 0;
    		break;
    	}
    	
    	
    	avg = total / 3;
    	return avg;
    }
    
    
public static void main(String[] args) throws FileNotFoundException {

    File tenFile = new File("numbers10.txt");
    double[] array10 = populateArray(tenFile, 10);
	
	File hundredFile = new File("numbers100.txt");
	double[] array100 = populateArray(hundredFile, 100);

	File thousandFile = new File("numbers1000.txt");
	double[] array1000 = populateArray(thousandFile, 1000);
	
	File thousandDuplicates = new File("numbers1000Duplicates.txt");
	double[] array1000Duplicates = populateArray(thousandDuplicates, 1000);
    
	File thousandNearlyOrdered = new File("numbersNearlyOrdered1000.txt");
	double[] array1000NearlyOrdered = populateArray(thousandNearlyOrdered, 1000);
	
	File thousandReverse = new File("numbersReverse1000.txt");
	double[] array1000Reverse = populateArray(thousandReverse, 100);
	
	File thousandSorted = new File("numbersSorted1000.txt");
	double[] array1000Sorted = populateArray(thousandSorted, 100);
	
	String a = "insertion sort";
	String b = "quick sort";
	String c = "merge sort";
	String d = "shell sort";
	String e = "selection sort";
	String f = "bubble sort";
	
	int insertion = 0;
	int quick = 1;
	int merge = 2;
	int shell = 3;
	int selection = 4;
	int bubble = 5;
	
	System.out.println("\n\nAvg time for " + a + " with numbers10.txt = " + avgTimeFor(array10, insertion) + " (ns)");
	System.out.println("Avg time for " + a + " with numbers100.txt = " + avgTimeFor(array100, insertion) + " (ns)");
	System.out.println("Avg time for " + a + " with numbers1000.txt = " + avgTimeFor(array1000, insertion) + " (ns)");
	System.out.println("Avg time for " + a + " with numbers1000Duplicates.txt = " + avgTimeFor(array1000Duplicates, insertion) + " (ns)");
	System.out.println("Avg time for " + a + " with numbersNearlyOrdered1000.txt = " + avgTimeFor(array1000NearlyOrdered, insertion) + " (ns)");
	System.out.println("Avg time for " + a + " with numbersReverse1000.txt = " + avgTimeFor(array1000Reverse, insertion) + " (ns)");
	System.out.println("Avg time for " + a + " with numbersSorted1000.txt = " + avgTimeFor(array1000Sorted, insertion) + " (ns)");
	
	System.out.println("\n\nAvg time for " + b + " with numbers10.txt = " + avgTimeFor(array10, quick) + " (ns)");
	System.out.println("Avg time for " + b + " with numbers100.txt = " + avgTimeFor(array100, quick) + " (ns)");
	System.out.println("Avg time for " + b + " with numbers1000.txt = " + avgTimeFor(array1000, quick) + " (ns)");
	System.out.println("Avg time for " + b + " with numbers1000Duplicates.txt = " + avgTimeFor(array1000Duplicates, quick) + " (ns)");
	System.out.println("Avg time for " + b + " with numbersNearlyOrdered1000.txt = " + avgTimeFor(array1000NearlyOrdered, insertion) + " (ns)");
	System.out.println("Avg time for " + b + " with numbersReverse1000.txt = " + avgTimeFor(array1000Reverse, quick) + " (ns)");
	System.out.println("Avg time for " + b + " with numbersSorted1000.txt = " + avgTimeFor(array1000Sorted, quick) + " (ns)");
	
	System.out.println("\n\nAvg time for " + c + " with numbers10.txt = " + avgTimeFor(array10, merge) + " (ns)");
	System.out.println("Avg time for " + c + " with numbers100.txt = " + avgTimeFor(array100, merge) + " (ns)");
	System.out.println("Avg time for " + c + " with numbers1000.txt = " + avgTimeFor(array1000, merge) + " (ns)");
	System.out.println("Avg time for " + c + " with numbers1000Duplicates.txt = " + avgTimeFor(array1000Duplicates, merge) + " (ns)");
	System.out.println("Avg time for " + c + " with numbersNearlyOrdered1000.txt = " + avgTimeFor(array1000NearlyOrdered, merge) + " (ns)");
	System.out.println("Avg time for " + c + " with numbersReverse1000.txt = " + avgTimeFor(array1000Reverse, merge) + " (ns)");
	System.out.println("Avg time for " + c + " with numbersSorted1000.txt = " + avgTimeFor(array1000Sorted, merge) + " (ns)");
	
	System.out.println("\n\nAvg time for " + d + " with numbers10.txt = " + avgTimeFor(array10, shell) + " (ns)");
	System.out.println("Avg time for " + d + " with numbers100.txt = " + avgTimeFor(array100, shell) + " (ns)");
	System.out.println("Avg time for " + d + " with numbers1000.txt = " + avgTimeFor(array1000, shell) + " (ns)");
	System.out.println("Avg time for " + d + " with numbers1000Duplicates.txt = " + avgTimeFor(array1000Duplicates, shell) + " (ns)");
	System.out.println("Avg time for " + d + " with numbersNearlyOrdered1000.txt = " + avgTimeFor(array1000NearlyOrdered, shell) + " (ns)");
	System.out.println("Avg time for " + d + " with numbersReverse1000.txt = " + avgTimeFor(array1000Reverse, shell) + " (ns)");
	System.out.println("Avg time for " + d + " with numbersSorted1000.txt = " + avgTimeFor(array1000Sorted, shell) + " (ns)");
	
	System.out.println("\n\nAvg time for " + e + " with numbers10.txt = " + avgTimeFor(array10, selection) + " (ns)");
	System.out.println("Avg time for " + e + " with numbers100.txt = " + avgTimeFor(array100, selection) + " (ns)");
	System.out.println("Avg time for " + e + " with numbers1000.txt = " + avgTimeFor(array1000, selection) + " (ns)");
	System.out.println("Avg time for " + e + " with numbers1000Duplicates.txt = " + avgTimeFor(array1000Duplicates, selection) + " (ns)");
	System.out.println("Avg time for " + e + " with numbersNearlyOrdered1000.txt = " + avgTimeFor(array1000NearlyOrdered, selection) + " (ns)");
	System.out.println("Avg time for " + e + " with numbersReverse1000.txt = " + avgTimeFor(array1000Reverse, selection) + " (ns)");
	System.out.println("Avg time for " + e + " with numbersSorted1000.txt = " + avgTimeFor(array1000Sorted, selection) + " (ns)");
	
	System.out.println("\n\nAvg time for " + f + " with numbers10.txt = " + avgTimeFor(array10, bubble) + " (ns)");
	System.out.println("Avg time for " + f + " with numbers100.txt = " + avgTimeFor(array100, bubble) + " (ns)");
	System.out.println("Avg time for " + f + " with numbers1000.txt = " + avgTimeFor(array1000, bubble) + " (ns)");
	System.out.println("Avg time for " + f + " with numbers1000Duplicates.txt = " + avgTimeFor(array1000Duplicates, bubble) + " (ns)");
	System.out.println("Avg time for " + f + " with numbersNearlyOrdered1000.txt = " + avgTimeFor(array1000NearlyOrdered, bubble) + " (ns)");
	System.out.println("Avg time for " + f + " with numbersReverse1000.txt = " + avgTimeFor(array1000Reverse, bubble) + " (ns)");
	System.out.println("Avg time for " + f + " with numbersSorted1000.txt = " + avgTimeFor(array1000Sorted, bubble) + " (ns)");
	
	
    }

 }//end class

