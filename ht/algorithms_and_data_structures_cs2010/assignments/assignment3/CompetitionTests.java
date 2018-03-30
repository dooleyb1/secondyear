import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.io.FileNotFoundException;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/tinyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, dijk.slowestSpeed);
        assertEquals("Graph should have 8 vertices",8, dijk.graph.V());
    }

    @Test 
    public void testDijkTinyEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/tinyEWD.txt", 50, 75, 100);

    	assertEquals("Longest distance should be 1.86km", 1.86, dijk.maxDist, 0.001);
        assertEquals("Competition time should be 38 mins", 38, dijk.timeRequiredforCompetition(dijk.maxDist, dijk.slowestSpeed));
    }

    @Test 
    public void testDijk1000EWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/1000EWD.txt", 50, 75, 100);

    	assertEquals("Graph should have 1000 vertices", 1000, dijk.graph.V());
    	assertEquals("Longest distance should be 1.39863", 1.39863, dijk.maxDist, 0.001);
        assertEquals("Competition time should be 28 mins", 28, dijk.timeRequiredforCompetition(dijk.maxDist, dijk.slowestSpeed));
    }

    @Test
    public void testDijkEmptyEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/emptyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, dijk.slowestSpeed);
        assertEquals("Graph should have 0 vertices",0, dijk.graph.V());
        assertEquals("Longest distance should be 0.0km", 0, dijk.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition(dijk.maxDist, dijk.slowestSpeed));
    }

    @Test
    public void testDijkInvalidEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/invalidEWD.txt", 50, 75, 100);

    	assertFalse("Graph should be invalid",dijk.isValidGraph);
    }

    @Test
    public void testDijkValidEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/tinyEWD.txt", 50, 75, 100);

    	assertTrue("Graph should be valid",dijk.isValidGraph);
    }

    @Test
    public void testDijkSingleNodeEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/singleNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 1 vertice",1, dijk.graph.V());
        assertEquals("Graph should have 0 edges",0, dijk.graph.E());
        assertEquals("Longest distance should be 0.0km", 0, dijk.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, dijk.timeRequiredforCompetition(dijk.maxDist, dijk.slowestSpeed));
    }

    @Test
    public void testDijkDualNodeEWD() throws FileNotFoundException{

    	CompetitionDijkstra dijk = new CompetitionDijkstra("txt_files/dualNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 2 vertices",2, dijk.graph.V());
        assertEquals("Longest distance should be 9.1km", 9.1, dijk.maxDist, 0.001);
		assertEquals("Competition time should be 182 mins", 182, dijk.timeRequiredforCompetition(dijk.maxDist, dijk.slowestSpeed));
    }

    //------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------

    @Test
    public void testFWConstructor() throws FileNotFoundException{
        
        CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/tinyEWD.txt", 50, 75, 100);

    	assertEquals("Slowest speed should be 50", 50, floyd.slowestSpeed);
        assertEquals("Graph should have 8 vertices",8, floyd.graph.V());
    }

    @Test 
    public void testFWTinyEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/tinyEWD.txt", 50, 75, 100);

    	assertEquals("Longest distance should be 1.86km", 1.86, floyd.maxDist, 0.001);
        assertEquals("Competition time should be 38 mins", 38, floyd.timeRequiredforCompetition(floyd.maxDist, floyd.slowestSpeed));
    }

    @Test 
    public void testFW1000EWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/1000EWD.txt", 50, 75, 100);

    	assertEquals("Graph should have 1000 vertices", 1000, floyd.graph.V());
    	assertEquals("Longest distance should be 1.39863", 1.39863, floyd.maxDist, 0.001);
        assertEquals("Competition time should be 28 mins", 28, floyd.timeRequiredforCompetition(floyd.maxDist, floyd.slowestSpeed));
    }

    @Test
    public void testFWEmptyEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/emptyEWD.txt", 50, 75, 100);

        assertEquals("Slowest speed should be 50", 50, floyd.slowestSpeed);
        assertEquals("Graph should have 0 vertices",0, floyd.graph.V());
        assertEquals("Longest distance should be 0.0km", 0, floyd.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition(floyd.maxDist, floyd.slowestSpeed));
    }

    @Test
    public void testFWInvalidEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/invalidEWD.txt", 50, 75, 100);

    	assertFalse("Graph should be invalid",floyd.isValidGraph);
    }

    @Test
    public void testFWValidEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/tinyEWD.txt", 50, 75, 100);

    	assertTrue("Graph should be valid",floyd.isValidGraph);
    }

    @Test
    public void testFWSingleNodeEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/singleNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 1 vertice",1, floyd.graph.V());
        assertEquals("Graph should have 0 edges",0, floyd.graph.E());
        assertEquals("Longest distance should be 0.0km", 0, floyd.maxDist, 0.001);
        assertEquals("Competition time should be -1 mins", -1, floyd.timeRequiredforCompetition(floyd.maxDist, floyd.slowestSpeed));
    }

    @Test
    public void testFWDualNodeEWD() throws FileNotFoundException{

    	CompetitionFloydWarshall floyd = new CompetitionFloydWarshall("txt_files/dualNodeEWD.txt", 50, 75, 100);

        assertEquals("Graph should have 2 vertices",2, floyd.graph.V());
        assertEquals("Longest distance should be 9.1km", 9.1, floyd.maxDist, 0.001);
		assertEquals("Competition time should be 182 mins", 182, floyd.timeRequiredforCompetition(floyd.maxDist, floyd.slowestSpeed));
    }
    //TODO - more tests
    
}