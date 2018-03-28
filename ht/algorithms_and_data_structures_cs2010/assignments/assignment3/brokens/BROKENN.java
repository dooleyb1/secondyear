import java.io.*;
import java.util.*;

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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */

    CompetitionDijkstra (String filename, int sA, int sB, int sC){

        Scanner scanner = new Scanner(new File(filename)); 
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(scanner);

        int source = 0;

        //Runs shortes path algorithm
        Dijkstras sp = new Dijkstras(G, source);

        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }

    public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
        private int maxN;        // maximum number of elements on PQ
        private int n;           // number of elements on PQ
        private int[] pq;        // binary heap using 1-based indexing
        private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
        private Key[] keys;      // keys[i] = priority of i

        public IndexMinPQ(int maxN) {
            if (maxN < 0) throw new IllegalArgumentException();
            this.maxN = maxN;
            n = 0;
            keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
            pq   = new int[maxN + 1];
            qp   = new int[maxN + 1];                   // make this of length maxN??
            for (int i = 0; i <= maxN; i++)
                qp[i] = -1;
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public boolean contains(int i) {
            if (i < 0 || i >= maxN) throw new IllegalArgumentException();
            return qp[i] != -1;
        }

       
        public int size() {
            return n;
        }

        public void insert(int i, Key key) {
            if (i < 0 || i >= maxN) throw new IllegalArgumentException();
            if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
            n++;
            qp[i] = n;
            pq[n] = i;
            keys[i] = key;
            swim(n);
        }
    
        public int minIndex() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            return pq[1];
        }

        public Key minKey() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            return keys[pq[1]];
        }

        public int delMin() {
            if (n == 0) throw new NoSuchElementException("Priority queue underflow");
            int min = pq[1];
            exch(1, n--);
            sink(1);
            assert min == pq[n+1];
            qp[min] = -1;        // delete
            keys[min] = null;    // to help with garbage collection
            pq[n+1] = -1;        // not needed
            return min;
        }

        public Key keyOf(int i) {
            if (i < 0 || i >= maxN) throw new IllegalArgumentException();
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            else return keys[i];
        }

        public void changeKey(int i, Key key) {
            if (i < 0 || i >= maxN) throw new IllegalArgumentException();
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            keys[i] = key;
            swim(qp[i]);
            sink(qp[i]);
        }

        @Deprecated
        public void change(int i, Key key) {
            changeKey(i, key);
        }

        public void decreaseKey(int i, Key key) {
            if (i < 0 || i >= maxN) throw new IllegalArgumentException();
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            if (keys[i].compareTo(key) <= 0)
                throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
            keys[i] = key;
            swim(qp[i]);
        }

        public void increaseKey(int i, Key key) {
            if (i < 0 || i >= maxN) throw new IllegalArgumentException();
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
            if (keys[i].compareTo(key) >= 0)
                throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
            keys[i] = key;
            sink(qp[i]);
        }

        public void delete(int i) {
            if (i < 0 || i >= maxN) throw new IllegalArgumentException();
            if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
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
            while (k > 1 && greater(k/2, k)) {
                exch(k, k/2);
                k = k/2;
            }
        }

        private void sink(int k) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && greater(j, j+1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }

        public Iterator<Integer> iterator() { return new HeapIterator(); }

        private class HeapIterator implements Iterator<Integer> {
            // create a new pq
            private IndexMinPQ<Key> copy;

            // add all elements to copy of heap
            // takes linear time since already in heap order so no keys move
            public HeapIterator() {
                copy = new IndexMinPQ<Key>(pq.length - 1);
                for (int i = 1; i <= n; i++)
                    copy.insert(pq[i], keys[pq[i]]);
            }

            public boolean hasNext()  { return !copy.isEmpty();                     }
            public void remove()      { throw new UnsupportedOperationException();  }

            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return copy.delMin();
            }
        }
    }

    private class Dijkstras{

        //distanceTo[x] = distance of shortest source -> x path
        private double[] distanceTo; 
        //edgeTo[x] = last edge on shortest source -> x path
        private DirectedEdge[] edgeTo;
        //Priority Queue of Vertices
        private IndexMinPQ<Double> pq;

        public Dijkstras(EdgeWeightedDigraph G, int s) {
            
            for (DirectedEdge e : G.edges()) {
                if (e.weight() < 0)
                    throw new IllegalArgumentException("edge " + e + " has negative weight");
            }

            distTo = new double[G.V()];
            edgeTo = new DirectedEdge[G.V()];

            validateVertex(s);

            for (int v = 0; v < G.V(); v++)
                distTo[v] = Double.POSITIVE_INFINITY;
            
            distTo[s] = 0.0;

            // relax vertices in order of distance from s
            pq = new IndexMinPQ<Double>(G.V());
            pq.insert(s, distTo[s]);
            
            while (!pq.isEmpty()) {
                int v = pq.delMin();
                
                for (DirectedEdge e : G.adj(v))
                    relax(e);
            }

            // check optimality conditions
            assert check(G, s);
        }

        // relax edge e and update pq if changed
        private void relax(DirectedEdge e) {
            
            int v = e.from(), w = e.to();
            
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }

        public double distTo(int v) {
            
            validateVertex(v);
            return distTo[v];
        }

        public boolean hasPathTo(int v) {
            validateVertex(v);
            return distTo[v] < Double.POSITIVE_INFINITY;
        }

        public Iterable<DirectedEdge> pathTo(int v) {
            
            validateVertex(v);
            
            if (!hasPathTo(v)) 
                return null;
            
            Stack<DirectedEdge> path = new Stack<DirectedEdge>();
            
            for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
                path.push(e);
            }
            return path;
        }

        private boolean check(EdgeWeightedDigraph G, int s) {

            // check that edge weights are nonnegative
            for (DirectedEdge e : G.edges()) {
                if (e.weight() < 0) {
                    System.err.println("negative edge weight detected");
                    return false;
                }
            }

            // check that distTo[v] and edgeTo[v] are consistent
            if (distTo[s] != 0.0 || edgeTo[s] != null) {
                System.err.println("distTo[s] and edgeTo[s] inconsistent");
                return false;
            }
            for (int v = 0; v < G.V(); v++) {
                if (v == s) continue;
                if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                    System.err.println("distTo[] and edgeTo[] inconsistent");
                    return false;
                }
            }

            // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    int w = e.to();
                    if (distTo[v] + e.weight() < distTo[w]) {
                        System.err.println("edge " + e + " not relaxed");
                        return false;
                    }
                }
            }

            // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
            for (int w = 0; w < G.V(); w++) {
                if (edgeTo[w] == null) continue;
                DirectedEdge e = edgeTo[w];
                int v = e.from();
                if (w != e.to()) return false;
                if (distTo[v] + e.weight() != distTo[w]) {
                    System.err.println("edge " + e + " on shortest path not tight");
                    return false;
                }
            }
            return true;
        }

        private void validateVertex(int v) {
            int V = distTo.length;
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }


    public class EdgeWeightedDigraph{

        private static final String NEWLINE = System.getProperty("line.seperator");

        //Number of Vertices and Edges respectively
        private int V;
        private int E;

        //adj[v] = adjacency list for vertex v
        private Bag<DirectedEgde>[] adj;
        //indegree[v] = indegree of vertex v
        private int[] indegree;

        public EdgeWeightedDigraph(Scanner scanner) {
            
            this.V = scanner.nextInt();
            this.E = scanner.nextInt();
            
            for (int i = 0; i < E; i++) {
                
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                
                validateVertex(v);
                validateVertex(w);
                
                double weight = scanner.nextDouble();
                addEdge(new DirectedEdge(v, w, weight));
            }
        }

        private class DirectedEdge{

            private final int v;
            private final int w;
            private final double weight;

            public DirectedEdge(int v, int w, double weight) {

                if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
                if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
                if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");

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

            
            public Bag() {
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

            public Iterator<Item> iterator()  {
                return new ListIterator<Item>(first);  
            }

            private class ListIterator<Item> implements Iterator<Item> {
                private Node<Item> current;

                public ListIterator(Node<Item> first) {
                    current = first;
                }

                public boolean hasNext()  { return current != null;                     }
                public void remove()      { throw new UnsupportedOperationException();  }

                public Item next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Item item = current.item;
                    current = current.next; 
                    return item;
                }
            }
        }

        private class DirectedEdge{

            private final int v;
            private final int w;
            private final double weight;

            public DirectedEdge(int v, int w, double weight) {
            
                if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
                if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
                if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
                
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

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        private void validateVertex(int v) {    
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }

        public void addEdge(DirectedEdge e) {
            
            int v = e.from();
            int w = e.to();
            
            validateVertex(v);
            validateVertex(w);
            adj[v].add(e);
            indegree[w]++;
            E++;
        }

        public Iterable<DirectedEdge> adj(int v) {
            validateVertex(v);
            return adj[v];
        }

        public int outdegree(int v) {
            validateVertex(v);
            return adj[v].size();
        }

        public int indegree(int v) {
            validateVertex(v);
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


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {

        CompetitionDijkstra test = new DijkstraVogella("txt_files/tinyEWD.txt", 50, 75, 100);
        //test.readFile("txt_files/tinyEWD.txt");
    }

}