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
    public void testDijkstraConstructor() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, dijk.slowestSpeed);
        assertEquals("Graph should have 8 vertices",8, dijk.graph.V());
    }

    @Test
    public void testNegativeCycleEWD() throws FileNotFoundException{

        boolean thrown = false;

        try{
            CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("negativeCycleEWD.txt", 50, 75, 100);
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }

        assertTrue("Graph should have negative cycle", thrown);
    }



    @Test
    public void testEdgeWeightedDiGraph() throws FileNotFoundException{

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

    }

    @Test 
    public void testEdgeWeightedDirectedCycle() throws FileNotFoundException{

        File file = new File("tinyEWD.txt");
        Scanner in = new Scanner(file);
        EdgeWeightedDiGraph g = new EdgeWeightedDiGraph(in);

        EdgeWeightedDirectedCycle cycle = new EdgeWeightedDirectedCycle(g);
        Iterable<DirectedEdge> x = cycle.cycle();
        assertTrue("Graph should have a cycle", cycle.hasCycle());

        file = new File("singleNodeEWD.txt");
        in = new Scanner(file);
        EdgeWeightedDiGraph h = new EdgeWeightedDiGraph(in);

        EdgeWeightedDirectedCycle cycle2 = new EdgeWeightedDirectedCycle(h);
        Iterable<DirectedEdge> xy = cycle2.cycle();
        assertFalse("Graph should not have a cycle", cycle2.hasCycle());
    }

    @Test
    public void testDijkstraFileNotFound() throws FileNotFoundException{

        CompetitionDijkstra dijk = new CompetitionDijkstra("imnotafile.txt", 50, 75, 100);

        assertNull("Graph should be set to null", dijk.graph);
        assertFalse("Should be an invalid graph", dijk.isValidGraph);
    }

    @Test
    public void testDijkstraNegativeSpeed() throws FileNotFoundException{

        CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", -1, 60, 100);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition());
    }

    @Test 
    public void testDijkTinyEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);

    	assertEquals("Longest distance should be 1.86km", 1.86, dijk.maxDist, 0.001);
        assertEquals("Competition time should be 38 mins", 38, dijk.timeRequiredforCompetition());
    }

    @Test 
    public void testDijk1000EWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("1000EWD.txt", 50, 75, 100);

    	assertEquals("Graph should have 1000 vertices", 1000, dijk.graph.V());
    	assertEquals("Longest distance should be 1.39863", 1.39863, dijk.maxDist, 0.001);
        assertEquals("Competition time should be 28 mins", 28, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testDijkEmptyEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("emptyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, dijk.slowestSpeed);
        assertEquals("Graph should have 0 vertices",0, dijk.graph.V());
        assertEquals("Longest distance should be 0.0km", 0, dijk.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testDijkInvalidEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("invalidEWD.txt", 50, 75, 100);

    	assertFalse("Graph should be invalid",dijk.isValidGraph);
    }

    @Test
    public void testDijkValidEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("tinyEWD.txt", 50, 75, 100);

    	assertTrue("Graph should be valid",dijk.isValidGraph);
    }

    @Test
    public void testDijkSingleNodeEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("singleNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 1 vertice",1, dijk.graph.V());
        assertEquals("Graph should have 0 edges",0, dijk.graph.E());
        assertEquals("Longest distance should be 0.0km", 0, dijk.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testDijkDualNodeEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("dualNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 2 vertices",2, dijk.graph.V());
        assertEquals("Longest distance should be 9.1km", 9.1, dijk.maxDist, 0.001);
		assertEquals("Competition time should be 182 mins", 182, dijk.timeRequiredforCompetition());
    }

    @Test
    public void testAdjMatrixEdgeWeightedDigraph() throws FileNotFoundException{

        File file = new File("tinyEWD.txt");
        Scanner in = new Scanner(file);
        AdjMatrixEdgeWeightedDigraph g = new AdjMatrixEdgeWeightedDigraph(in);
        FloydWarshall floyd = new FloydWarshall(g);

        Iterable<DirectedEdge> x = floyd.path(0,4);
    }

    //------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------

    @Test
    public void testFWConstructor() throws FileNotFoundException{
        
        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);

    	assertEquals("Slowest speed should be 50", 50, floyd.slowestSpeed);
        assertEquals("Graph should have 8 vertices",8, floyd.graph.V());
    }

    @Test
    public void testFWFileNotFound() throws FileNotFoundException{

        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("imnotafile.txt", 50, 75, 100);

        assertNull("Graph should be set to null", floyd.graph);
        assertFalse("Should be an invalid graph", floyd.isValidGraph);
    }

    @Test
    public void testFWNegativeSpeed() throws FileNotFoundException{

        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", -1, 60, 100);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition());
    }

    @Test 
    public void testFWTinyEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);

    	assertEquals("Longest distance should be 1.86km", 1.86, floyd.maxDist, 0.001);
        assertEquals("Competition time should be 38 mins", 38, floyd.timeRequiredforCompetition());
    }

    @Test 
    public void testFW1000EWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("1000EWD.txt", 50, 75, 100);

    	assertEquals("Graph should have 1000 vertices", 1000, floyd.graph.V());
    	assertEquals("Longest distance should be 1.39863", 1.39863, floyd.maxDist, 0.001);
        assertEquals("Competition time should be 28 mins", 28, floyd.timeRequiredforCompetition());
    }

    @Test
    public void testFWEmptyEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("emptyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, floyd.slowestSpeed);
        assertEquals("Graph should have 0 vertices",0, floyd.graph.V());
        assertEquals("Longest distance should be 0.0km", 0, floyd.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition());
    }

    @Test
    public void testFWInvalidEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("invalidEWD.txt", 50, 75, 100);

    	assertFalse("Graph should be invalid",floyd.isValidGraph);
    }

    @Test
    public void testFWValidEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 100);

    	assertTrue("Graph should be valid",floyd.isValidGraph);
    }

    @Test
    public void testFWSingleNodeEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("singleNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 1 vertice",1, floyd.graph.V());
        assertEquals("Graph should have 0 edges",0, floyd.graph.E());
        assertEquals("Longest distance should be 0.0km", 0, floyd.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition());
    }

    @Test
    public void testFWDualNodeEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("dualNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 2 vertices",2, floyd.graph.V());
        assertEquals("Longest distance should be 9.1km", 9.1, floyd.maxDist, 0.001);
		assertEquals("Competition time should be 182 mins", 182, floyd.timeRequiredforCompetition());
    }
    //TODO - more tests
    
}