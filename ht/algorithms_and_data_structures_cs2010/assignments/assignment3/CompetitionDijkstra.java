import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	public EdgeWeightedDiGraph graph;
	public int slowestSpeed;
	public double maxDist;
	public String filename;
	public boolean isValidGraph;


	CompetitionDijkstra(String filename, int sA, int sB, int sC) throws FileNotFoundException {
		
		try{
			
			this.filename = filename;
			File file = new File(filename);
			Scanner in = new Scanner(file);
			this.graph = new EdgeWeightedDiGraph(in);
		
		} catch (FileNotFoundException e){
			
			this.graph = null;
			this.isValidGraph = false;	
		}


		this.slowestSpeed = Math.min(Math.min(sA,sB),sC);
		this.filename = filename;

		maxDist = 0.0;

			//Re-run Dijkstra accounting for every node being source (ONLY IF VALID GRAPH)
		if(this.graph != null && this.graph.isValid()){

			this.isValidGraph = true;

			for(int i = 0; i < graph.V(); i++) {

					//System.out.println("\n------------------------------------------");
					//System.out.println("Re-routing with Source = [Node " + i + "]....");
					//System.out.println("------------------------------------------");
				DijkstraSP routedGraph = new DijkstraSP(graph, i);

					//Calculate longest distance for current routedGraph (source = i)
				for(int j = 0; j < graph.V(); j++) {

						//If there exists a path within the graph to node j
					if(routedGraph.hasPathTo(j)) {
							//System.out.println("Distance from [Node " + i + "] to [Node " + j + "] : " + routedGraph.distTo(j));
							//And if the distance to node j is more than current max dist
						if(this.maxDist < routedGraph.distTo(j)) {

							this.maxDist = routedGraph.distTo(j);
								//System.out.println(maxDist);
						}
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

	public int timeRequiredforCompetition() {
		
		if(this.maxDist <= 0.0 || this.slowestSpeed <= 0 || this.filename == null)
			return -1;

		//System.out.println("\nTime = Distance / Speed");

		//System.out.println("     = " + distance + "km / " + speed + "m/m");
		
		double time = (1000*this.maxDist)/this.slowestSpeed;
		//System.out.println("     = " + time + "min");
	
		
		return (int) Math.ceil(time);
	}
}