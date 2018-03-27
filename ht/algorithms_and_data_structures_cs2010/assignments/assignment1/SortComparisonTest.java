import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Brandon Dooley
 *  @version HT 2018
 *  
 *  Find below the performance analysis of each respective algorithm using the supplied text files as described
 *  per CS2010 HT Assignment 1
 *  
 *  **These values are an average of 3 tests of each algorithm for each respective input data**
 *  
 *  					| Insert (ns)	| Quick (ns)	| Merge (ns)	| Shell (ns)	|Selection (ns)	| Bubble (ns)	|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  10 Random			|	2254	  	|	2904		|	6928		|	3024		|	3157		|	1953		|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  100 Random			|	33136		| 	14086		|	80953		|	22249		|	134313		|	3179		|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Random			|	1082535		|	223910		|	180424		|	351685		|	3970407		|	32971		|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Few Unique		|	616409		|	79167		|	141096		|	361519		|	2409929		|	2309119		|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Nearly Ordered	|	117106		|	9633		|	133719		|	355053		|	359867		|	34203		|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Reverse Order	|	14199		|	5648		|	12593		|	20574		|	55892		|	3711		|
 *  -----------------------------------------------------------------------------------------------------------------------
 *  1000 Sorted			|	664			|	5609		|	11326		|	22303		|	57809		|	3659		|
 *  
 *  1. Which of the sorting algorithms does the order of input have an impact on? Why?
 *  2. Which algorithm has the biggest difference between the best and worst performance, based on the type of input, for the input of size 1000? Why?
 *  3. Which algorithm has the best/worst scalability, ie, the difference in performance time based on the input size? Please consider only input files
 *     	with random order for this answer.
 *  4. Which algorithm is the fastest for each of the 7 input files
 *  
 *  
 *  1. All of the sorting algorithms are impacted by the order of the input data. Some are impacted more drastically than others
 *  	in various different ways and extremities e.g Selection Sort does not deal with large inputs that are already sorted (57,809ns), whereas 
 *  	Insertion Sort can deal with large sorted inputs quite effeciently (664ns). 
 *  
 *  2. Insertion sort has the largest notable difference between its performance for inputs of size 1,000 based on their format. Insertion sort
 *  	takes a large 1,082,535ns to sort 1,000 inputs of completely random order, whilst only takes 664ns to sort 1,000 inputs that are already sorted.
 *  	This is because if the input is random and unordered Insertion Sort must perform in the worst case O(n^2) comparisons and swaps whereas if the input 
 *  	is already sorted it only needs to perform (n) comaparison and 0 swaps.
 *  
 *  3. Best Case (Bubble Sort) - Bubble Sort is by far the best performer when it comes to scalability of inputs of a random order. It manages to 
 *  	sort 10 random values in 1,953ns whilst also managing to sort 1,000 random values in 32,971ns. When compared to the other algorithms and their scalability 
 *  	performances it is a clear winner.
 *  
 *     Worst Case (Insertion Sort) - As discussed above in part 2, Insertion Sort has the worst performance in terms of scalability when it comes to input data
 *  	sets of a random order. It manages to sort 10 random values in 2,254ns which is a respectable performance when compared to the other algorithms. However
 *  	as the data set is increased in size the performance significantly deteriorates. It sorts the input data set of 1,000 random values in 1,082,535ns which
 *  	is a massive difference from its performance with a data set of size 10.
 *  
 *  4.	10 Random = Bubble Sort (1,953ns)
 *  	100 Random = Bubble Sort (3,179ns)
 *  	1000 Random = Bubble Sort (32,971ns)
 *  	1000 Few Unique = Quick Sort (79,167ns)
 *  	1000 Nearly Ordered = Quick Sort (9,633ns)
 *  	1000 Reverse Order = Bubble Sort (3,711ns)
 *  	1000 Sorted = Insertion Sort (664ns)
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	
    	double a[] = {};
    	double sortedArray[] = new double[a.length];
    	int length;
    	
    	sortedArray = SortComparison.insertionSort(a);
    	length = sortedArray.length;
    	assertEquals("Checking insertionSort on an empty array, returned array should also be empty", length, 0);
    	
    	sortedArray = SortComparison.quickSort(a);
    	length = sortedArray.length;
    	assertEquals("Checking quickSort on an empty array, returned array should also be empty", length, 0);
    	
    	sortedArray = SortComparison.mergeSort(a);
    	length = sortedArray.length;
    	assertEquals("Checking mergeSort on an empty array, returned array should also be empty", length, 0);
    	
    	sortedArray = SortComparison.shellSort(a);
    	length = sortedArray.length;
    	assertEquals("Checking shellSort on an empty array, returned array should also be empty", length, 0);
    	
    	sortedArray = SortComparison.selectionSort(a);
    	length = sortedArray.length;
    	assertEquals("Checking selectionSort on an empty array, returned array should also be empty", length, 0);
    	
    	sortedArray = SortComparison.bubbleSort(a);
    	length = sortedArray.length;
    	assertEquals("Checking bubbleSort on an empty array, returned array should also be empty", length, 0);
    	
    }
    
 // ----------------------------------------------------------
    /**
     * Check that the insertionSort() method works properly
     */
    @Test
    public void testInsertionSort()
    {
    	
    	//Testing on a large array of random order and numbers
    	double a[] = {21.3, 5.6, 1.2, 90.5, 10.1, 60.4, 99.0, 0.2, -1.0, 2.1, 1293.2, 6.3};
    	double expectedArray[] = {-1.0, 0.2, 1.2, 2.1, 5.6, 6.3, 10.1, 21.3, 60.4, 90.5, 99.0, 1293.2}; 
    	double sortedArray[] = new double[a.length];
    	int length;
    	
    	sortedArray = SortComparison.insertionSort(a);
    	assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	//Testing on an array of one element
    	double b[] = {104.0};
    	double expectedArray2[] = {104.0}; 
    	double sortedArray2[] = new double[b.length];
    	
    	sortedArray2 = SortComparison.insertionSort(b);
    	assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	//Testing on an array of two elements
    	double c[] = {104.0, -1230.293};
    	double expectedArray3[] = {-1230.293, 104.0}; 
    	double sortedArray3[] = new double[c.length];
    	
    	sortedArray3 = SortComparison.insertionSort(c);
    	assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	//Testing on an array of three elements
    	double d[] = {104.0, -1230.293, 37261.348};
    	double expectedArray4[] = {-1230.293, 104.0, 37261.348}; 
    	double sortedArray4[] = new double[d.length];
    	
    	sortedArray4 = SortComparison.insertionSort(d);
    	assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    }


 // ----------------------------------------------------------
    /**
     * Check that the quickSort() method works properly
     */
    @Test
    public void testQuickSort()
    {
    	//Testing on a large array of random order and numbers
    	double a[] = {21.3, 5.6, 1.2, 90.5, 10.1, 60.4, 99.0, 0.2, -1.0, 2.1, 1293.2, 6.3};
    	double expectedArray[] = {-1.0, 0.2, 1.2, 2.1, 5.6, 6.3, 10.1, 21.3, 60.4, 90.5, 99.0, 1293.2}; 
    	double sortedArray[] = new double[a.length];
    	
    	sortedArray = SortComparison.quickSort(a);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	//Testing on an array of one element
    	double b[] = {104.0};
    	double expectedArray2[] = {104.0}; 
    	double sortedArray2[] = new double[b.length];
    	

    	sortedArray2 = SortComparison.quickSort(b);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	//Testing on an array of two elements
    	double c[] = {104.0, -1230.293};
    	double expectedArray3[] = {-1230.293, 104.0}; 
    	double sortedArray3[] = new double[c.length];	

    	sortedArray3 = SortComparison.quickSort(c);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	//Testing on an array of three elements
    	double d[] = {104.0, -1230.293, 37261.348};
    	double expectedArray4[] = {-1230.293, 104.0, 37261.348}; 
    	double sortedArray4[] = new double[d.length];
    	
    	sortedArray4 = SortComparison.quickSort(d);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    }
    
	// ----------------------------------------------------------
    /**
     * Check that the mergeSort() method works properly
     */
    @Test
    public void testMergeSort()
    {
    	//Testing on a large array of random order and numbers
    	double a[] = {21.3, 5.6, 1.2, 90.5, 10.1, 60.4, 99.0, 0.2, -1.0, 2.1, 1293.2, 6.3};
    	double expectedArray[] = {-1.0, 0.2, 1.2, 2.1, 5.6, 6.3, 10.1, 21.3, 60.4, 90.5, 99.0, 1293.2}; 
    	double sortedArray[] = new double[a.length];
    	
    	sortedArray = SortComparison.mergeSort(a);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	//Testing on an array of one element
    	double b[] = {104.0};
    	double expectedArray2[] = {104.0}; 
    	double sortedArray2[] = new double[b.length];
    	
    	sortedArray2 = SortComparison.mergeSort(b);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	//Testing on an array of two elements
    	double c[] = {104.0, -1230.293};
    	double expectedArray3[] = {-1230.293, 104.0}; 
    	double sortedArray3[] = new double[c.length];
    	
    	sortedArray3 = SortComparison.mergeSort(c);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	//Testing on an array of three elements
    	double d[] = {104.0, -1230.293, 37261.348};
    	double expectedArray4[] = {-1230.293, 104.0, 37261.348}; 
    	double sortedArray4[] = new double[d.length];
    	
    	sortedArray4 = SortComparison.mergeSort(d);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    }
    
 // ----------------------------------------------------------
    /**
     * Check that the shellSort() method works properly
     */
    @Test
    public void testShellSort()
    {
    	//Testing on a large array of random order and numbers
    	double a[] = {21.3, 5.6, 1.2, 90.5, 10.1, 60.4, 99.0, 0.2, -1.0, 2.1, 1293.2, 6.3};
    	double expectedArray[] = {-1.0, 0.2, 1.2, 2.1, 5.6, 6.3, 10.1, 21.3, 60.4, 90.5, 99.0, 1293.2}; 
    	double sortedArray[] = new double[a.length];
    	
    	sortedArray = SortComparison.shellSort(a);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	//Testing on an array of one element
    	double b[] = {104.0};
    	double expectedArray2[] = {104.0}; 
    	double sortedArray2[] = new double[b.length];
    	
    	sortedArray2 = SortComparison.shellSort(b);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	//Testing on an array of two elements
    	double c[] = {104.0, -1230.293};
    	double expectedArray3[] = {-1230.293, 104.0}; 
    	double sortedArray3[] = new double[c.length];
    	
    	sortedArray3 = SortComparison.shellSort(c);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	//Testing on an array of three elements
    	double d[] = {104.0, -1230.293, 37261.348};
    	double expectedArray4[] = {-1230.293, 104.0, 37261.348}; 
    	double sortedArray4[] = new double[d.length];
    	
    	sortedArray4 = SortComparison.selectionSort(d);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    }
    
 // ----------------------------------------------------------
    /**
     * Check that the selectionSort() method works properly
     */
    @Test
    public void testSelectionSort()
    {
    	//Testing on a large array of random order and numbers
    	double a[] = {21.3, 5.6, 1.2, 90.5, 10.1, 60.4, 99.0, 0.2, -1.0, 2.1, 1293.2, 6.3};
    	double expectedArray[] = {-1.0, 0.2, 1.2, 2.1, 5.6, 6.3, 10.1, 21.3, 60.4, 90.5, 99.0, 1293.2}; 
    	double sortedArray[] = new double[a.length];
    	
    	sortedArray = SortComparison.selectionSort(a);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	//Testing on an array of one element
    	double b[] = {104.0};
    	double expectedArray2[] = {104.0}; 
    	double sortedArray2[] = new double[b.length];
    	
    	sortedArray2 = SortComparison.selectionSort(b);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	//Testing on an array of two elements
    	double c[] = {104.0, -1230.293};
    	double expectedArray3[] = {-1230.293, 104.0}; 
    	double sortedArray3[] = new double[c.length];
    	
    	sortedArray3 = SortComparison.selectionSort(c);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	//Testing on an array of three elements
    	double d[] = {104.0, -1230.293, 37261.348};
    	double expectedArray4[] = {-1230.293, 104.0, 37261.348}; 
    	double sortedArray4[] = new double[d.length];
    	
    	sortedArray4 = SortComparison.shellSort(d);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    }
    
 // ----------------------------------------------------------
    /**
     * Check that the bubbleSort() method works properly
     */
    @Test
    public void testBubbleSort()
    {
    	//Testing on a large array of random order and numbers
    	double a[] = {21.3, 5.6, 1.2, 90.5, 10.1, 60.4, 99.0, 0.2, -1.0, 2.1, 1293.2, 6.3};
    	double expectedArray[] = {-1.0, 0.2, 1.2, 2.1, 5.6, 6.3, 10.1, 21.3, 60.4, 90.5, 99.0, 1293.2}; 
    	double sortedArray[] = new double[a.length];
    	
    	sortedArray = SortComparison.bubbleSort(a);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	//Testing on an array of one element
    	double b[] = {104.0};
    	double expectedArray2[] = {104.0}; 
    	double sortedArray2[] = new double[b.length];
    	
    	sortedArray2 = SortComparison.bubbleSort(b);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	//Testing on an array of two elements
    	double c[] = {104.0, -1230.293};
    	double expectedArray3[] = {-1230.293, 104.0}; 
    	double sortedArray3[] = new double[c.length];
    	
    	sortedArray3 = SortComparison.bubbleSort(c);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	//Testing on an array of three elements
    	double d[] = {104.0, -1230.293, 37261.348};
    	double expectedArray4[] = {-1230.293, 104.0, 37261.348}; 
    	double sortedArray4[] = new double[d.length];
    	
    	sortedArray4 = SortComparison.bubbleSort(d);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     * @throws FileNotFoundException 
     *
     */
    public static void main(String[] args) throws FileNotFoundException
    {
	
    }

}

