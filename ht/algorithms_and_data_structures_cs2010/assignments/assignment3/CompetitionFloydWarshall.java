import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.lang.NullPointerException;
import java.util.Iterator;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall{

    public static AdjMatrixEdgeWeightedDigraph graph;
    public int slowestSpeed;
    public double maxDist;
    public String filename;
    public FloydWarshall shortestPaths;
    public boolean isValidGraph;

    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){

        
        try{
            
            this.filename = filename;
            File file = new File(filename);
            Scanner in = new Scanner(file);
            this.graph = new AdjMatrixEdgeWeightedDigraph(in);
            this.shortestPaths = new FloydWarshall(this.graph);
            this.maxDist = 0.0;
            this.slowestSpeed = Math.min(Math.min(sA,sB),sC);
            this.filename = filename;
        
        } catch (FileNotFoundException | NullPointerException e){
            
            this.filename = null;
            this.graph = null;
            this.isValidGraph = false;
        }

        if(this.shortestPaths != null && this.graph.isValid()){

            this.isValidGraph = true;

            for (int v = 0; v < this.graph.V(); v++) {

                //System.out.println("\n------------------------------------------");
                //System.out.println("Distances From [Node " + v + "]....");
                //System.out.println("------------------------------------------");

                for (int w = 0; w < this.graph.V(); w++) {
                    if (shortestPaths.hasPath(v, w)){
                        //System.out.println("Distance from [Node " + v + "] to [Node " + w + "] : " + shortestPaths.dist(v, w));

                        if(this.maxDist < shortestPaths.dist(v, w)) 
                            this.maxDist = shortestPaths.dist(v, w);
                    }
                }
                //System.out.println("\nCurrent Max Distance Found = " + maxDist);
            }
        }


        //System.out.println("------------------------------------------");
        //System.out.println("Calculating time required for show...\n");

        //int time = timeRequiredforCompetition(maxDist, slowestSpeed);
        //System.out.println("\nTime required for show: "+ time +"min");
    }


    public int timeRequiredforCompetition(){

        if(this.maxDist <= 0.0 || this.slowestSpeed <= 0 || this.filename == null)
            return -1;
        //System.out.println("\nTime = Distance / Speed");

        //System.out.println("     = " + distance + "km / " + speed + "m/m");
        
        double time = (1000*this.maxDist)/this.slowestSpeed;
        //System.out.println("     = " + time + "min");
    
        
        return (int) Math.ceil(time);
    }
}