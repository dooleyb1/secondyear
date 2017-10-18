import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    //Tests binarySeach method from Collinear.java
    @Test
    public void testBinarySearch()
    {
    	int[] testArray = { 1, 5, 29, 9, 7, 4 };
    	Collinear.sort(testArray);
    	boolean testResult1 = false;
    	boolean testResult2 = true;
    	testResult1 = Collinear.binarySearch(testArray, 4);
    	testResult2 = Collinear.binarySearch(testArray, 3);
    	
    	assertTrue("Should find 4", testResult1);
    	assertFalse("Shouldn't find 3", testResult2);
    
    }
    
    //Tests countCollinear method from Collinear.java
    @Test
    public void testCountCollinear()
    {
    	//Testing 3 arrays that contain 1 set of collinear points
    	int[] a1 = { 1, 9, 292, 15, 190, 3};
    	int[] a2 = { 89, 2, 73, 26, 600 };
    	int[] a3 = { 102, 91, 22, 42, 87, 3};
    	
    	int expectedResult = 1;
    	
    	assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinear(a1, a2, a3));
    	
    	//Testing 3 arrays that contain 4 sets of collinear points
    	int[] a4 = { 1, 9, 2, 15, 190, 3};
    	int[] a5 = { 89, 2, 16, 4, 6 };
    	int[] a6 = { 102, 6, 17, 42, 8, 3};
    	
    	int expectedResult2 = 4;
    	
    	assertEquals("countCollinear(" + Arrays.toString(a4) + "," + Arrays.toString(a5) + "," + Arrays.toString(a6) + ")", expectedResult2, Collinear.countCollinear(a4, a5, a6));
    	
    	//Testing 3 arrays that contain 0 sets of collinear points
    	int[] a7 = { 100, 9, 8, 17, 193, 21};
    	int[] a8 = { 89, 29, 16, 37, 691 };
    	int[] a9 = { 102, 712, 309, 412, 87, 3};
    	
    	int expectedResult3 = 0;
    	
    	assertEquals("countCollinear(" + Arrays.toString(a7) + "," + Arrays.toString(a8) + "," + Arrays.toString(a9) + ")", expectedResult3, Collinear.countCollinear(a7, a8, a9));
    }
    
    //Testing the countCollinearFast method from Collinear.java
    @Test
    public void testCountCollinearFast()
    {
    	//Testing 3 arrays that contain 1 set of collinear points
    	int[] a1 = { 1, 9, 2, 15, 190, 3};
    	int[] a2 = { 89, 2, 73, 2790, 61 };
    	int[] a3 = { 102, 91, 22, 42, 895, 3};
    	
    	int expectedResult = 1;
    	
    	assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    	
    	//Testing 3 arrays that contain 4 sets of collinear points
    	int[] a4 = { 1, 9, 2, 15, 190, 3};
    	int[] a5 = { 89, 2, 16, 4, 6 };
    	int[] a6 = { 102, 6, 17, 42, 8, 3};
    	
    	int expectedResult2 = 4;
    	
    	assertEquals("countCollinearFast(" + Arrays.toString(a4) + "," + Arrays.toString(a5) + "," + Arrays.toString(a6) + ")", expectedResult2, Collinear.countCollinearFast(a4, a5, a6));
    	
    	//Testing 3 arrays that contain 0 sets of collinear points
    	int[] a7 = { 100, 9, 8, 17, 193, 21};
    	int[] a8 = { 89, 29, 16, 37, 691 };
    	int[] a9 = { 102, 712, 309, 412, 87, 3};
    	
    	int expectedResult3 = 0;
    	
    	assertEquals("countCollinearFast(" + Arrays.toString(a7) + "," + Arrays.toString(a8) + "," + Arrays.toString(a9) + ")", expectedResult3, Collinear.countCollinearFast(a7, a8, a9));
    }
    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  You should read the lecture notes and/or book to understand how to correctly implement the main methods.
     *  You can use any of the provided classes to read from files, and time your code.
     * @throws FileNotFoundException 
     *
     */
     public static void main(String[] args) throws FileNotFoundException
     {
    	 int [] a1 = new int [5000];
    	 int [] a2 = new int [5000];
    	 int [] a3 = new int [5000];
    	 int i;
    	 
    	 Scanner scanner = new Scanner(new File("r05000-1.txt"));
    	 i = 0;
    	 while(scanner.hasNextInt()){
    	    a1[i++] = scanner.nextInt();
    	 }
    	   
    	 scanner = new Scanner(new File("r05000-2.txt"));
    	 i = 0;
    	 while(scanner.hasNextInt()){
    	    a2[i++] = scanner.nextInt();
    	 }
    	   
    	 scanner = new Scanner(new File("r05000-3.txt"));
       	 i = 0;
       	 while(scanner.hasNextInt()){
       	    a3[i++] = scanner.nextInt();
       	 }
       	    
    	 scanner.close();
    	 Stopwatch stopwatch = new Stopwatch();
    	 Collinear.countCollinearFast(a1, a2, a3);
    	 System.out.println(Double.toString(stopwatch.elapsedTime()));
    	 
    	 
    	 
     }
     }
