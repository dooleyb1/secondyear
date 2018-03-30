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
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

    public static AdjMatrixEdgeWeightedDigraph graph;
    public int slowestSpeed;
    public double maxDist;
    public boolean isValidGraph;

    CompetitionFloydWarshall (String filename, int sA, int sB, int sC) throws FileNotFoundException{

        this.graph = new AdjMatrixEdgeWeightedDigraph(filename);
        FloydWarshall shortestPaths = new FloydWarshall(this.graph);
        
        this.maxDist = 0.0;
        this.slowestSpeed = Math.min(Math.min(sA,sB),sC);
        
        if(this.graph.isValid()){

            this.isValidGraph = true;

            for (int v = 0; v < this.graph.V(); v++) {
                
                //System.out.println("\n------------------------------------------");
                //System.out.println("Distances From [Node " + v + "]....");
                //System.out.println("------------------------------------------");

                for (int w = 0; w < this.graph.V(); w++) {
                    if (shortestPaths.hasPath(v, w)){
                        //System.out.println("Distance from [Node " + v + "] to [Node " + w + "] : " + shortestPaths.dist(v, w));
                        
                        if(maxDist < shortestPaths.dist(v, w)) 
                            maxDist = shortestPaths.dist(v, w);
                    }
                    
                    else
                     System.out.println("  Inf ");
                }
                //System.out.println("\nCurrent Max Distance Found = " + maxDist);
            }
        }

        else
            this.isValidGraph = false;

        //System.out.println("------------------------------------------");
        //System.out.println("Calculating time required for show...\n");

        //int time = timeRequiredforCompetition(maxDist, slowestSpeed);
        //System.out.println("\nTime required for show: "+ time +"min");
    }


    public int timeRequiredforCompetition(double distance, int speed){

        if(distance <= 0.0 || speed <= 0)
            return -1;
        //System.out.println("\nTime = Distance / Speed");

        //System.out.println("     = " + distance + "km / " + speed + "m/m");
        
        double time = (1000*distance)/speed;
        //System.out.println("     = " + time + "min");
    
        
        return (int) Math.ceil(time);
    }

    public class AdjMatrixEdgeWeightedDigraph {

        private final int V;
        private int E;
        private DirectedEdge[][] edgeFromTo;
        private boolean isValid;

        public AdjMatrixEdgeWeightedDigraph(String filename) throws FileNotFoundException {
            
            File file = new File(filename);
            Scanner in = new Scanner(file);

            this.V = in.nextInt();
            this.E = in.nextInt();
            this.isValid = true;

            this.edgeFromTo = new DirectedEdge[V][V];
            
            int currentEdges = E;

            for (int i = 0; i < currentEdges; i++) {
                
                int v = in.nextInt();
                int w = in.nextInt();
                double weight = in.nextDouble();
                
                if( v >= 0 && w >= 0){

                    if (v == w) 
                        addEdge(new DirectedEdge(v, w, Math.abs(weight)));
                    
                    else addEdge(new DirectedEdge(v, w, weight));
                }

                else
                    this.isValid = false;
            }
        }

        public boolean isValid() {
            if(this.isValid)
                return true;
            else
                return false;
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

            if (edgeFromTo[v][w] == null) {
                E++;
                edgeFromTo[v][w] = e;
            }
        }

        public Iterable<DirectedEdge> edgeFromTo(int v) {
            return new AdjIterator(v);
        }

        // support iteration over graph vertices
        private class AdjIterator implements Iterator<DirectedEdge>, Iterable<DirectedEdge> {
            
            private int v;
            private int w = 0;

            public AdjIterator(int v) {
                this.v = v;
            }

            public Iterator<DirectedEdge> iterator() {
                return this;
            }

            public boolean hasNext() {
                while (w < V) {
                    if (edgeFromTo[v][w] != null) return true;
                    w++;
                }
                return false;
            }

            public DirectedEdge next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return edgeFromTo[v][w++];
            }
        }
    }

    public class FloydWarshall {

        private boolean hasNegativeCycle;
        private double[][] distTo;
        private DirectedEdge[][] edgeTo;

        public FloydWarshall(AdjMatrixEdgeWeightedDigraph G) {
            
            int V = G.V();
            distTo = new double[V][V];
            edgeTo = new DirectedEdge[V][V];

            // initialize distances to infinity
            for (int v = 0; v < V; v++) {
                for (int w = 0; w < V; w++) {
                    distTo[v][w] = Double.POSITIVE_INFINITY;
                }
            }

            // initialize distances using edge-weighted digraph's
            for (int v = 0; v < G.V(); v++) {
                
                for (DirectedEdge e : G.edgeFromTo(v)) {
                    distTo[e.from()][e.to()] = e.weight();
                    edgeTo[e.from()][e.to()] = e;
                }
                
                // in case of self-loops
                if (distTo[v][v] >= 0.0) {
                    distTo[v][v] = 0.0;
                    edgeTo[v][v] = null;
                }
            }

            // Floyd-Warshall updates
            for (int i = 0; i < V; i++) {
                // compute shortest paths using only 0, 1, ..., i as intermediate vertices
                for (int v = 0; v < V; v++) {
                    
                    if (edgeTo[v][i] == null) continue;  // optimization
                    
                    for (int w = 0; w < V; w++) {
                        
                        if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
                            distTo[v][w] = distTo[v][i] + distTo[i][w];
                            edgeTo[v][w] = edgeTo[i][w];
                        }
                    }
                    
                    // check for negative cycle
                    if (distTo[v][v] < 0.0) {
                        hasNegativeCycle = true;
                        return;
                    }
                }
            }
        }

        public boolean hasNegativeCycle() {
            return hasNegativeCycle;
        }

        public Iterable<DirectedEdge> negativeCycle() {
            
            for (int v = 0; v < distTo.length; v++) {
                
                // negative cycle in v's predecessor graph
                if (distTo[v][v] < 0.0) {
                   
                    int V = edgeTo.length;
                    EdgeWeightedDiGraph spt = new EdgeWeightedDiGraph(V);
                   
                    for (int w = 0; w < V; w++)
                        if (edgeTo[v][w] != null)
                            spt.addEdge(edgeTo[v][w]);
                    
                    EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
                    return finder.cycle();
                }
            }
            return null;
        }

        public boolean hasPath(int s, int t) {
            return distTo[s][t] < Double.POSITIVE_INFINITY;
        }

        public double dist(int s, int t) {

            if (hasNegativeCycle())
                throw new UnsupportedOperationException("Negative cost cycle exists");
            return distTo[s][t];
        }

        public Iterable<DirectedEdge> path(int s, int t) {

            if (hasNegativeCycle())
                throw new UnsupportedOperationException("Negative cost cycle exists");
            
            if (!hasPath(s, t)) 
                return null;
            
            Stack<DirectedEdge> path = new Stack<DirectedEdge>();
            
            for (DirectedEdge e = edgeTo[s][t]; e != null; e = edgeTo[s][e.from()]) {
                path.push(e);
            }

            return path;
        }
    }

    public class DirectedEdge {

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

    public class Bag<Item> implements Iterable<Item> {
        
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

    public class EdgeWeightedDirectedCycle {
        
        private boolean[] marked;            
        private DirectedEdge[] edgeTo;        
        private boolean[] onStack;            
        private Stack<DirectedEdge> cycle;    

        public EdgeWeightedDirectedCycle(EdgeWeightedDiGraph G) {
            
            marked  = new boolean[G.V()];
            onStack = new boolean[G.V()];
            edgeTo  = new DirectedEdge[G.V()];
            
            for (int v = 0; v < G.V(); v++)
                if (!marked[v]) dfs(G, v);

        }

        private void dfs(EdgeWeightedDiGraph G, int v) {
            
            onStack[v] = true;
            marked[v] = true;
            
            for (DirectedEdge e : G.edgesAdjacentTo(v)) {
                int w = e.to();

                if (cycle != null) 
                    return;

                else if (!marked[w]) {
                    edgeTo[w] = e;
                    dfs(G, w);
                }

                else if (onStack[w]) {
                    cycle = new Stack<DirectedEdge>();

                    DirectedEdge f = e;
                    while (f.from() != w) {
                        cycle.push(f);
                        f = edgeTo[f.from()];
                    }
                    cycle.push(f);

                    return;
                }
            }

            onStack[v] = false;
        }

        public boolean hasCycle() {
            return cycle != null;
        }

        public Iterable<DirectedEdge> cycle() {
            return cycle;
        }
    }

    private class EdgeWeightedDiGraph {

        private final int V; 
        private int E; 

        private Bag<DirectedEdge>[] edgesAdjacentTo; 


        private EdgeWeightedDiGraph(int V) {
        
            this.V = V;
            this.E = 0;
            edgesAdjacentTo = (Bag<DirectedEdge>[]) new Bag[V];
            
            for (int v = 0; v < V; v++)
                edgesAdjacentTo[v] = new Bag<DirectedEdge>();
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

    public static void main(String[] args) throws FileNotFoundException {
        //CompetitionFloydWarshall comp = new CompetitionFloydWarshall("txt_files/tinyEWD.txt", 50, 75, 100);

    }
}