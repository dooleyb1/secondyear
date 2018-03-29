import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

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

	static EdgeWeightedDiGraph graph;

	CompetitionDijkstra(String filename, int sA, int sB, int sC) throws FileNotFoundException {
		
		graph = new EdgeWeightedDiGraph(filename);
		int slowestSpeed = Math.min(Math.min(sA,sB),sC);

		double maxDist = 0.0;
		
		//Re-run Dijkstra accounting for every node being source
		for(int i = 0; i < graph.V(); i++)
		{
			System.out.println("\n------------------------------------------");
			System.out.println("Re-routing with Source = [Node " + i + "]....");
			System.out.println("------------------------------------------");
			DijkstraSP routedGraph = new DijkstraSP(graph, i);
			
			//Calculate longest distance for current routedGraph (source = i)
			for(int j = 0; j < graph.V(); j++) {
				
				//If there exists a path within the graph to node j
				if(routedGraph.hasPathTo(j)) {
					System.out.println("Distance from [Node " + i + "] to [Node " + j + "] : " + routedGraph.distTo(j));
					//And if the distance to node j is more than current max dist
					if(maxDist < routedGraph.distTo(j)) {

						maxDist = routedGraph.distTo(j);
						//System.out.println(maxDist);
					}
				}	
			}

			System.out.println("\nCurrent Max Distance Found = " + maxDist);
		}

		System.out.println("------------------------------------------");
		System.out.println("Calculating time required for show...\n");
		
		int time = timeRequiredforCompetition(maxDist, slowestSpeed);
		System.out.println("\nTime required for show: "+ time +"min");

	}

	private class EdgeWeightedDiGraph {

		private final int V; 
		private int E; 

		private Bag<DirectedEdge>[] edgesAdjacentTo; 


		private EdgeWeightedDiGraph(String filename) throws FileNotFoundException {
			
			File file = new File(filename);
			Scanner in = new Scanner(file);
			
			V = in.nextInt();
			E = in.nextInt();
			
			int currentEdges = E;

			edgesAdjacentTo = (Bag<DirectedEdge>[]) new Bag[V];
			
			//Create empty adjacency matrix to house adjacent edges
			for (int v = 0; v < V; v++)
				edgesAdjacentTo[v] = new Bag<DirectedEdge>();

			//Extract all edges from input file
			for (int i = 0; i < currentEdges; i++) {
				
				int v = in.nextInt();
				int w = in.nextInt();
				double weight = in.nextDouble();

				addEdge(new DirectedEdge(v, w, weight));
			}
		}

		public int V() {
			return V;
		}

		public int E() {
			return E;
		}

		public void addEdge(DirectedEdge e) {
			
			int v = e.from();
			int w = e.to();

			//Add directed edge e to v's adjacent edges
			edgesAdjacentTo[v].add(e);

			E++;
		}

		public Iterable<DirectedEdge> edgesAdjacentTo(int v) {
			return edgesAdjacentTo[v];
		}

		public Iterable<DirectedEdge> edges() {
			
			//Create empty Bag to house edges
			Bag<DirectedEdge> list = new Bag<DirectedEdge>();
			
			//Iterate over all vertices
			for (int v = 0; v < V; v++) {

				for (DirectedEdge e : edgesAdjacentTo(v)) {
					list.add(e);
				}
			}
			return list;
		}
	}

	private class DirectedEdge {

		private final int v;
		private final int w;
		private final double weight;

		public DirectedEdge(int v, int w, double weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		public int from() {
			return v;
		}

		public int to() {
			return w;
		}

		public double weight() {
			return weight;
		}
	}

	private class Bag<Item> implements Iterable<Item> {
		
		private Node<Item> first;
		private int n;

		private class Node<Item> {
			private Item item;
			private Node<Item> next;
		}

		private Bag() {
			first = null;
			n = 0;
		}

		public boolean isEmpty() {
			return first == null;
		}

		public int size() {
			return n;
		}

		//Appends an Item to the top of the Bag
		public void add(Item item) {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			n++;
		}

		public Iterator<Item> iterator() {
			return new ListIterator<Item>(first);
		}

		private class ListIterator<Item> implements Iterator<Item> {
			private Node<Item> current;

			public ListIterator(Node<Item> first) {
				current = first;
			}

			public boolean hasNext() {
				return current != null;
			}

			public Item next() {
				if (!hasNext())
					throw new NoSuchElementException();
				Item item = current.item;
				current = current.next;
				return item;
			}
		}
	}

	private class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
		
		private int maxN;
		private int n; 

		private int[] pq; 
		private int[] qp; 

		private Key[] keys;

		public IndexMinPQ(int maxN) {

			this.maxN = maxN;
			n = 0;
			keys = (Key[]) new Comparable[maxN + 1]; 
			
			pq = new int[maxN + 1];
			qp = new int[maxN + 1]; 
			
			//Initialise all indices in pq to -1
			for (int i = 0; i <= maxN; i++)
				qp[i] = -1;
		}

		public boolean isEmpty() {
			return n == 0;
		}

		public boolean contains(int i) {
			return qp[i] != -1;
		}

		public int size() {
			return n;
		}

		public void insert(int i, Key key) {
	
			n++;
			
			qp[i] = n;
			pq[n] = i;
			
			keys[i] = key;
			swim(n);
		}

		public int delMin() {
			
			int min = pq[1];
			exch(1, n--);
			sink(1);
			assert min == pq[n + 1];
			qp[min] = -1; 
			keys[min] = null; // to help with garbage collection
			pq[n + 1] = -1; // not needed
			return min;
		}



		public void decreaseKey(int i, Key key) {
	
			keys[i] = key;
			swim(qp[i]);
		}


		private boolean greater(int i, int j) {
			return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
		}

		private void exch(int i, int j) {
			int swap = pq[i];
			pq[i] = pq[j];
			pq[j] = swap;
			qp[pq[i]] = i;
			qp[pq[j]] = j;
		}

		private void swim(int k) {
			while (k > 1 && greater(k / 2, k)) {
				exch(k, k / 2);
				k = k / 2;
			}
		}

		private void sink(int k) {
			while (2 * k <= n) {
				int j = 2 * k;
				if (j < n && greater(j, j + 1))
					j++;
				if (!greater(k, j))
					break;
				exch(k, j);
				k = j;
			}
		}

		public Iterator<Integer> iterator() {
			return new HeapIterator();
		}

		private class HeapIterator implements Iterator<Integer> {
			
			private IndexMinPQ<Key> copy;

			
			public HeapIterator() {
				copy = new IndexMinPQ<Key>(pq.length - 1);
				for (int i = 1; i <= n; i++)
					copy.insert(pq[i], keys[pq[i]]);
			}

			public boolean hasNext() {
				return !copy.isEmpty();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			public Integer next() {
				if (!hasNext())
					throw new NoSuchElementException();
				return copy.delMin();
			}
		}
	}

	// DijkstraSP Class from textbook
	private class DijkstraSP {
		
		private double[] distTo; 
		private DirectedEdge[] edgeTo; 
		private IndexMinPQ<Double> priorityQueue; 


		public DijkstraSP(EdgeWeightedDiGraph graph, int source) {

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
	
	public int timeRequiredforCompetition(double distance, int speed) {
		
		System.out.println("\nTime = Distance / Speed");

		System.out.println("     = " + distance + "km / " + speed + "m/m");
		
		double time = (1000*distance)/speed;
		System.out.println("     = " + time + "min");
	
		
		return (int) Math.ceil(time);
	}

	public static void main(String[] args) throws FileNotFoundException {
		CompetitionDijkstra comp = new CompetitionDijkstra("txt_files/tinyEWD.txt", 50, 75, 100);

	}

}