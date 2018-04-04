import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor(){

    	CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, dijk.slowestSpeed);
        assertEquals("Graph should have 8 vertices",8, dijk.graph.V());
    }

    @Test
    public void testNegativeCycleEWD() {

        boolean thrown = false;

        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("negativeCycleEWD.txt", 50, 75, 100);

        assertTrue("Graph should have negative cycle", floyd.shortestPaths.hasNegativeCycle());
    }



    @Test
    public void testEdgeWeightedDiGraph() {

        try{
            File file = new File("tinyEWD.txt");
            Scanner in = new Scanner(file);
            EdgeWeightedDiGraph g = new EdgeWeightedDiGraph(in);

            DijkstraSP dijk = new DijkstraSP(g, 0);
            Iterator<Integer> it = dijk.priorityQueue.iterator();
            int x;

            if(it.hasNext())
                x = it.next();

            Iterable<DirectedEdge> it2 = dijk.pathTo(3);
            Iterable<DirectedEdge> itFalse = dijk.pathTo(2);
            Iterable<DirectedEdge> edges = g.edges();
        
        } catch(FileNotFoundException e){

        }

    }

    @Test
    public void solvingHerTests(){

        //DIJKSTRA CAN'T TAKE NEGATIVE WEIGHTS??
        CompetitionDijkstra d = new CompetitionDijkstra("input-I.txt", 4, 7, 1);
        //assertEquals("Testing input-I.txt for Dijk", 10000, d.timeRequiredforCompetition());

        CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-I.txt", 4, 7, 1);
        assertEquals("Testing input-I.txt for Floyd", 10000, f.timeRequiredforCompetition());

        d = new CompetitionDijkstra("input-F.txt", 50,80,60);
        assertEquals("Testing input-F.txt for Dijk", -1, d.timeRequiredforCompetition());

        f = new CompetitionFloydWarshall("input-F.txt", 50,80,60);
        assertEquals("Testing input-F.txt for Floyd", -1, f.timeRequiredforCompetition());

        d = new CompetitionDijkstra("input-C.txt", 50,100,100);
        assertEquals("Testing input-C.txt for Dijk", -1, d.timeRequiredforCompetition());

        f = new CompetitionFloydWarshall("input-C.txt", 50,100,100);
        assertEquals("Testing input-C.txt for Floyd", -1, f.timeRequiredforCompetition());

        d = new CompetitionDijkstra("input-I.txt", 3233,7,2368726);
        //assertEquals("Testing input-I.txt for Dijk", 1429, d.timeRequiredforCompetition());

        f = new CompetitionFloydWarshall("input-I.txt", 3233,7,2368726);
        assertEquals("Testing input-I.txt for Floyd", 1429, f.timeRequiredforCompetition());

        d = new CompetitionDijkstra("input-N.txt", 7333,74444,117171);
        //assertEquals("Testing input-N.txt for Dijk", 1, d.timeRequiredforCompetition());

        f = new CompetitionFloydWarshall("input-N.txt", 7333,74444,117171);
        assertEquals("Testing input-N.txt for Floyd", 1, f.timeRequiredforCompetition());

    }

    @Test
    public void testInputI(){

        CompetitionDijkstra dijk = new CompetitionDijkstra("input-I.txt", 4,7,1);
        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("input-I.txt", 4,7,1);

        //assertEquals("Testing input-I.txt for Floyd", 10000, floyd.timeRequiredforCompetition());
        //assertEquals("Testing input-I.txt for Dijk", 10000, dijk.timeRequiredforCompetition());
    }



    @Test
    public void testInputA(){

        CompetitionDijkstra dijk = new CompetitionDijkstra("input-A.txt", 60,50,75);
        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("input-A.txt", 60,50,75);

        assertEquals("Testing input-A.txt", -1, floyd.timeRequiredforCompetition());
        assertEquals("Testing input-A.txt", -1, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testInputF(){

        CompetitionDijkstra dijk = new CompetitionDijkstra("input-F.txt", 50,80,60);

        assertEquals("Testing input-F.txt for Dijk", -1, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testDijkstraFileNotFound() {

        CompetitionDijkstra dijk = new CompetitionDijkstra("imnotafile.txt", 50, 75, 100);

        assertNull("Graph should be set to null", dijk.graph);
        assertFalse("Should be an invalid graph", dijk.isValidGraph);
    }

    @Test
    public void testDijkstraNegativeSpeed() {

        CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", -1, 60, 100);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition());
    }

    @Test 
    public void testDijkTinyEWD() {

    	CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);

    	assertEquals("Longest distance should be 1.86km", 1.86, dijk.maxDist, 0.001);
        assertEquals("Competition time should be 38 mins", 38, dijk.timeRequiredforCompetition());
    }

    @Test 
    public void testDijk1000EWD() {

    	CompetitionDijkstra dijk = new CompetitionDijkstra("1000EWD.txt", 50, 75, 100);

    	assertEquals("Graph should have 1000 vertices", 1000, dijk.graph.V());
    	assertEquals("Longest distance should be 1.39863", 1.39863, dijk.maxDist, 0.001);
        assertEquals("Competition time should be 28 mins", 28, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testDijkEmptyEWD() {

    	CompetitionDijkstra dijk = new CompetitionDijkstra("emptyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, dijk.slowestSpeed);
        assertEquals("Graph should have 0 vertices",0, dijk.graph.V());
        assertEquals("Longest distance should be 0.0km", 0, dijk.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testDijkInvalidEWD() {

    	CompetitionDijkstra dijk = new CompetitionDijkstra("invalidEWD.txt", 50, 75, 100);

    	assertFalse("Graph should be invalid",dijk.isValidGraph);
    }

    @Test
    public void testDijkValidEWD() {

    	CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);

    	assertTrue("Graph should be valid",dijk.isValidGraph);
    }

    @Test
    public void testDijkSingleNodeEWD() {

    	CompetitionDijkstra dijk = new CompetitionDijkstra("singleNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 1 vertice",1, dijk.graph.V());
        assertEquals("Graph should have 0 edges",0, dijk.graph.E());
        assertEquals("Longest distance should be 0.0km", 0, dijk.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testAdjMatrixEdgeWeightedDigraph() {

        try{
            File file = new File("tinyEWD.txt");
            Scanner in = new Scanner(file);
            AdjMatrixEdgeWeightedDigraph g = new AdjMatrixEdgeWeightedDigraph(in);
            FloydWarshall floyd = new FloydWarshall(g);

            Iterable<DirectedEdge> x = floyd.path(0,4);
        } catch (FileNotFoundException e){

        }
    }

    //------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------

    @Test
    public void testFWConstructor() {
        
        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);

    	assertEquals("Slowest speed should be 50", 50, floyd.slowestSpeed);
        assertEquals("Graph should have 8 vertices",8, floyd.graph.V());
    }

    @Test
    public void testFWFileNotFound() {

        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("imnotafile.txt", 50, 75, 100);

        assertNull("Graph should be set to null", floyd.graph);
        assertFalse("Should be an invalid graph", floyd.isValidGraph);
    }

    @Test
    public void testFWNegativeSpeed() {

        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", -1, 60, 100);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition());
    }

    @Test 
    public void testFWTinyEWD() {

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);

    	assertEquals("Longest distance should be 1.86km", 1.86, floyd.maxDist, 0.001);
        assertEquals("Competition time should be 38 mins", 38, floyd.timeRequiredforCompetition());
    }

    @Test 
    public void testFW1000EWD() {

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("1000EWD.txt", 50, 75, 100);

    	assertEquals("Graph should have 1000 vertices", 1000, floyd.graph.V());
    	assertEquals("Longest distance should be 1.39863", 1.39863, floyd.maxDist, 0.001);
        assertEquals("Competition time should be 28 mins", 28, floyd.timeRequiredforCompetition());
    }

    @Test
    public void testFWEmptyEWD() {

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("emptyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, floyd.slowestSpeed);
        assertEquals("Graph should have 0 vertices",0, floyd.graph.V());
        assertEquals("Longest distance should be 0.0km", 0, floyd.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition());
    }

    @Test
    public void testFWInvalidEWD() {

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("invalidEWD.txt", 50, 75, 100);

    	assertFalse("Graph should be invalid",floyd.isValidGraph);
    }

    @Test
    public void testFWValidEWD() {

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);

    	assertTrue("Graph should be valid",floyd.isValidGraph);
    }

    @Test
    public void testFWSingleNodeEWD() {

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("singleNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 1 vertice",1, floyd.graph.V());
        assertEquals("Graph should have 0 edges",0, floyd.graph.E());
        assertEquals("Longest distance should be 0.0km", 0, floyd.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition());
    }

    //TODO - more tests
    
}