import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;
import java.util.Iterator;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

/* MIT License
 *
 * Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

 /* Adapted from Sedgeick & Wayne - https://algs4.cs.princeton.edu/home/ */

/*
    1. Justify the choice of the data structures used in CompetitionDijkstra and CompetitionFloydWarshall

        a) CompetitionDijkstra:

            -In Dijkstra's shortest path algorithm only one node is considered to be the source node and
            the shortest paths to all other nodes are found with respect to that node. Since Dijkstra's 
            shortest path algorithm requires a graphs Nodes and Vertices to contain both
            edge weightings and directions I decided to make use of Sedgewick and Waynes data structure
            which represents an Edge-Weighted Directional Graph. This can be found within the
            EdgeWeightedDiGraph class. This class effeciently and easily allows us to access the adjacency
            matrick of vertices by using another data structure by Sedewick & Wayne which is the Bag 
            of Directed Edges data structure. 

        b) CompetitionFloydWarshall

            -Floyd-Warhsall's algorithm is used when any of the nodes can be the source so you need to find
            the shortest path from all nodes to every other node. Floyd-Warshall's shortest path algorithm 
            is quite similar to Dijkstra's however it allows for negative weighted edges whereas Dijkstra's does not.
            To accomodate for this we use a different data structure which is of the form of an Adjacency Matrix 
            containing the optimal edge to take between node x and node y. This is then used to populate a
            two dimensional array containing the best routes between all nodes.

    2. a) Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms
        in the given problem.

    
            - With respect to the given problem Dijkstra's shortest path algorithm is the less ideal of the
            two algorithms to use in theory as it is designed for finding the shortest path from one source node.
            This means that Dijkstra's algorithm has to be performed V times for V being the amount of nodes
            within the graph. Without the use of a min-priority queue Dijkstra's runs in O(V^2) or when using
            a min-priority queue O(E + V log V) - E bein the number of edges in the graph.
            
            Whereas by using Floyd-Warshall, you compare all possible paths through the graph between each 
            pair of vertices. This is much more ideal for the current problem at hand where we need to know
            the longest path from any two vertices in order to compute the minimum time needed for the competition
            to run. However, the Floyd-Warshall algorithm runs in O(V^3).

        b) Also explain how would their relative performance be affected by the density of the graph. Which would you choose in which set of circumstances and why?

            - Within densly populated graphs the Floyd-Warshall algorithm is the more ideal of the two. Whereas
            if a graph is sparsely populated yet connected, Dijkstra's shortest path algorithm is a better choice.
            This is since the running time of Dijkstra being repeated is O(E*V + V^2 log V) which is significantly 
            better than the O(V^3) run time of Floyd-Warshall when E is smaller than V^2. 

            -Densly Populated : Floyd-Warshall
            -Sparsly Populated (Non-Negative Edges) : Dijkstra's 
 */

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