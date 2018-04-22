import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

// ----------------------------------------------------------
/**
 * Sorts an edge weighted directed graph using Dijkstra's Shortest Path Algorithm.
 *
 * @param graph: The edge weighted directed graph to be sorted.
 * @param source: Index of source node.
 * @return after the method returns, the dijkstraShortestPath object is the SP.
 *
 * ----------------------------------------------------------
 * Approximate Mathematical Performance:
 * -------------------------------------
 * Without the use of a min-priority queue Dijkstra's runs in O(V^2) or when using
 *	a min-priority queue O(E + V log V) - E bein the number of edges in the graph.
 *
 *  Performance:
 									Worst Case - O(V^2)
 *
 *
 */
public class dijkstraShortestPath {

	public double[] distTo;
	public DirectedEdge[] edgeTo;
	public IndexMinPQ<Double> priorityQueue;

	public dijkstraShortestPath(EdgeWeightedDiGraph graph, int source) {

		distTo = new double[graph.V()];
		edgeTo = new DirectedEdge[graph.V()];

		//Initialise all distances to max value
		for (int v = 0; v < graph.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;

		//Initialise source distance to 0.0
		distTo[source] = 0.0;

		priorityQueue = new IndexMinPQ<Double>(graph.V());
		priorityQueue.insert(source, distTo[source]);

		//While there is a node to settle
		while (!priorityQueue.isEmpty()) {

			//Get node with minimum distance and remove from pq
			int v = priorityQueue.delMin();

			//For every edge related to v
			for (DirectedEdge e : graph.edgesAdjacentTo(v))
				relax(e);
		}

	}

	private void relax(DirectedEdge e) {

		int v = e.from();
		int w = e.to();

		//If new route is lower
		if (distTo[w] > distTo[v] + e.weight()) {

			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;

			//If pq already contains w
			if (priorityQueue.contains(w))
				//Update w with new values
				priorityQueue.decreaseKey(w, distTo[w]);

			else
				priorityQueue.insert(w, distTo[w]);
		}
	}

	public double distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<DirectedEdge> pathTo(int v) {

		if (!hasPathTo(v))
			return null;

		Stack<DirectedEdge> path = new Stack<DirectedEdge>();

		//Iterate over all edges leading to v, add them to stack
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}
}
