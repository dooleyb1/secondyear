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

	// EdgeWeightedDiGraph class from Algorithms textbook
	private class EdgeWeightedDiGraph {
		private final String NEWLINE = System.getProperty("line.separator");

		private final int V; // number of vertices in this digraph
		private int E; 
		private Bag<DirectedEdge>[] adj; 
		private int[] indegree; 


		private EdgeWeightedDiGraph(String filename) throws FileNotFoundException {
			File file = new File(filename);
			Scanner in = new Scanner(file);
			
			V = in.nextInt();
			E = in.nextInt();
			
			int currentEdges = E;
			
			adj = (Bag<DirectedEdge>[]) new Bag[V];
			this.indegree = new int[V];
			
			for (int v = 0; v < V; v++)
				adj[v] = new Bag<DirectedEdge>();
			// System.out.print(adj.length);
			// System.out.print("\n"+E);

			for (int i = 0; i < currentEdges; i++) {
				
				int v = in.nextInt();
				int w = in.nextInt();
				// System.out.println("\n"+E);

				double weight = in.nextDouble();
				// System.out.print("\n"+weight);

				addEdge(new DirectedEdge(v, w, weight));
				// System.out.print("\n\nDone: "+i);

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
			// System.out.print("\n\nFrom: "+e.from());

			int w = e.to();
			// System.out.print("\n\nTo: "+e.to());

			adj[v].add(e);
			indegree[w]++;
			E++;
		}

		public Iterable<DirectedEdge> adj(int v) {
			return adj[v];
		}

		public int outdegree(int v) {
			return adj[v].size();
		}

		public int indegree(int v) {
			return indegree[v];
		}

		public Iterable<DirectedEdge> edges() {
			Bag<DirectedEdge> list = new Bag<DirectedEdge>();
			for (int v = 0; v < V; v++) {
				for (DirectedEdge e : adj(v)) {
					list.add(e);
				}
			}
			return list;
		}

		public String toString() {
			
			StringBuilder s = new StringBuilder();
			s.append(V + " " + E + NEWLINE);
			
			for (int v = 0; v < V; v++) {
				s.append(v + ": ");
				for (DirectedEdge e : adj[v]) {
					s.append(e + "  ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
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

		public String toString() {
			return v + "->" + w + " " + String.format("%5.2f", weight);
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

			public void remove() {
				throw new UnsupportedOperationException();
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

		public int minIndex() {
			return pq[1];
		}

		public Key minKey() {
			return keys[pq[1]];
		}

		public int delMin() {
			
			int min = pq[1];
			exch(1, n--);
			sink(1);
			assert min == pq[n + 1];
			qp[min] = -1; // delete
			keys[min] = null; // to help with garbage collection
			pq[n + 1] = -1; // not needed
			return min;
		}

		public Key keyOf(int i) {
			return keys[i];
		}

		public void changeKey(int i, Key key) {
			
			keys[i] = key;
			swim(qp[i]);
			sink(qp[i]);
		}


		public void decreaseKey(int i, Key key) {
	
			keys[i] = key;
			swim(qp[i]);
		}

		public void increaseKey(int i, Key key) {
			
			keys[i] = key;
			sink(qp[i]);
		}

		public void delete(int i) {
			
			int index = qp[i];
			exch(index, n--);
			swim(index);
			sink(index);
			keys[i] = null;
			qp[i] = -1;
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
		private IndexMinPQ<Double> pq; 


		public DijkstraSP(EdgeWeightedDiGraph G, int s) {

			distTo = new double[G.V()];
			edgeTo = new DirectedEdge[G.V()];


			for (int v = 0; v < G.V(); v++)
				distTo[v] = Double.POSITIVE_INFINITY;

			distTo[s] = 0.0;

			pq = new IndexMinPQ<Double>(G.V());
			pq.insert(s, distTo[s]);
			
			while (!pq.isEmpty()) {
				int v = pq.delMin();
				for (DirectedEdge e : G.adj(v))
					relax(e);
			}

		}

		private void relax(DirectedEdge e) {
			int v = e.from(), w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.decreaseKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
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
			for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
				path.push(e);
			}
			return path;
		}
	}

	// AssignmentSolution begins here
	static EdgeWeightedDiGraph ewdGraph;

	CompetitionDijkstra(String filename, int sA, int sB, int sC) throws FileNotFoundException {
		
		ewdGraph = new EdgeWeightedDiGraph(filename);
		int slowestSpeed = Math.min(Math.min(sA,sB),sC);
//		System.out.println("Slowest speed is: " + slowestSpeed+"\n");
		
//		int s = 0;
//		double longestDistance = 0;
//		int count = 0;
		DijkstraSP sp = new DijkstraSP(ewdGraph, 0);
		double maxDist = 0 ;
		
		for( int i=0;i<ewdGraph.V();i++)
		{
			DijkstraSP map = new DijkstraSP(ewdGraph, i);
			//printLongest(i, map, ewdGraph);
			for(int j=0;j<ewdGraph.V();j++) {
				if(map.hasPathTo(j)) {
					if(maxDist<map.distTo(j)) {
						maxDist = map.distTo(j);
						//System.out.println(maxDist);
					}
				}
					
			}
		}
		int time = timeRequiredforCompetition(maxDist, slowestSpeed);
		System.out.println("Time required for show: "+time +"min");

	}
	
	/*Method just for testing
	 *print the longest distance from a given vertex*/
	public void printLongest(int source, DijkstraSP map, EdgeWeightedDiGraph graph) {
		double maxDistance = 0;
		for (int i = 0 ; i < ewdGraph.V();i++) {
			if (map.hasPathTo(i)){
				if (maxDistance<map.distTo(i))
					maxDistance = map.distTo(i);
					
			}
		}
		System.out.print("Max distance when source vertex is " + source + ": "+ maxDistance+"\n\n");
	}
	/**
	 * @return int: minimum minutes that will pass before the three contestants
	 *         can meet
	 */
	public int timeRequiredforCompetition(double distance, int speed) {
		return (int)((1000*distance)/speed);
	}

	public static void main(String[] args) throws FileNotFoundException {
		CompetitionDijkstra comp = new CompetitionDijkstra("txt_files/1000EWD.txt", 50, 75, 100);

	}

}