import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2018
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
    	
    	sortedArray = SortComparison.quickSort(a);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	sortedArray = SortComparison.mergeSort(a);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	sortedArray = SortComparison.shellSort(a);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	sortedArray = SortComparison.selectionSort(a);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	sortedArray = SortComparison.bubbleSort(a);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray, sortedArray, 0);
    	
    	//Testing on an array of one element
    	double b[] = {104.0};
    	double expectedArray2[] = {104.0}; 
    	double sortedArray2[] = new double[b.length];
    	
    	sortedArray2 = SortComparison.insertionSort(b);
    	assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	sortedArray2 = SortComparison.quickSort(b);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	sortedArray2 = SortComparison.mergeSort(b);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	sortedArray2 = SortComparison.shellSort(b);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	sortedArray2 = SortComparison.selectionSort(b);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	sortedArray2 = SortComparison.bubbleSort(b);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray2, sortedArray2, 0);
    	
    	//Testing on an array of two elements
    	double c[] = {104.0, -1230.293};
    	double expectedArray3[] = {-1230.293, 104.0}; 
    	double sortedArray3[] = new double[c.length];
    	
    	sortedArray3 = SortComparison.insertionSort(c);
    	assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	sortedArray3 = SortComparison.quickSort(c);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	sortedArray3 = SortComparison.mergeSort(c);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	sortedArray3 = SortComparison.shellSort(c);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	sortedArray3 = SortComparison.selectionSort(c);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	sortedArray3 = SortComparison.bubbleSort(c);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray3, sortedArray3, 0);
    	
    	//Testing on an array of three elements
    	double d[] = {104.0, -1230.293, 37261.348};
    	double expectedArray4[] = {-1230.293, 104.0, 37261.348}; 
    	double sortedArray4[] = new double[d.length];
    	
    	sortedArray4 = SortComparison.insertionSort(d);
    	assertArrayEquals("Checking insertionSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    	sortedArray4 = SortComparison.quickSort(d);
    	assertArrayEquals("Checking quickSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    	sortedArray4 = SortComparison.mergeSort(d);
    	assertArrayEquals("Checking mergeSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    	sortedArray4 = SortComparison.shellSort(d);
    	assertArrayEquals("Checking shellSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    	sortedArray4 = SortComparison.selectionSort(d);
    	assertArrayEquals("Checking selectionSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    	sortedArray4 = SortComparison.bubbleSort(d);
    	assertArrayEquals("Checking bubbleSort on a test array, returned array should equal expected array", expectedArray4, sortedArray4, 0);
    	
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
        //TODO: implement this method
    }

}

