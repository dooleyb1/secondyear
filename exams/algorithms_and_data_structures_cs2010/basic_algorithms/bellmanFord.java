// ----------------------------------------------------------
/**
 * Sorts an edge weighted directed graph using Dijkstra's Shortest Path Algorithm.
 *
 * @param graph: The edge weighted directed graph to be sorted.
 * @param source: Index of source node.
 * @return after the method returns, the BellmanFordSP object is the SP.
 *
 * ----------------------------------------------------------
 * Approximate Mathematical Performance:
 * -------------------------------------
 *
 *  Performance:
 *								Worst Case - O(V * E)
 *
 */


public class BellmanFordSP {
   private double[] distTo;               // distTo[v] = distance  of shortest s->v path
   private DirectedEdge[] edgeTo;         // edgeTo[v] = last edge on shortest s->v path
   private boolean[] onQueue;             // onQueue[v] = is v currently on the queue?
   private Queue<Integer> queue;          // queue of vertices to relax
   private int cost;                      // number of calls to relax()
   private Iterable<DirectedEdge> cycle;  // negative cycle (or null if no such cycle)


   public BellmanFordSP(EdgeWeightedDigraph G, int s) {
       distTo  = new double[G.V()];
       edgeTo  = new DirectedEdge[G.V()];
       onQueue = new boolean[G.V()];

       for (int v = 0; v < G.V(); v++)
           distTo[v] = Double.POSITIVE_INFINITY;

       distTo[s] = 0.0;

       // Bellman-Ford algorithm
       queue = new Queue<Integer>();
       queue.enqueue(s);
       onQueue[s] = true;
       while (!queue.isEmpty() && !hasNegativeCycle()) {
           int v = queue.dequeue();
           onQueue[v] = false;
           relax(G, v);
       }

   }

   private void relax(EdgeWeightedDigraph G, int v) {
    for (DirectedEdge e : G.adj(v)) {
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (!onQueue[w]) {
                queue.enqueue(w);
                onQueue[w] = true;
            }
        }
        //If more edges are relaxed than V-1, cycle found, check if negative
        if (cost++ % G.V() == 0) {
            findNegativeCycle();
            if (hasNegativeCycle()) return;  // found a negative cycle
        }
    }
  }

  public boolean hasNegativeCycle() {
    return cycle != null;
  }


  public Iterable<DirectedEdge> negativeCycle() {
      return cycle;
  }

  // by finding a cycle in predecessor graph
  private void findNegativeCycle() {
      int V = edgeTo.length;
      EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
      for (int v = 0; v < V; v++)
          if (edgeTo[v] != null)
              spt.addEdge(edgeTo[v]);

      EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
      cycle = finder.cycle();
  }
}
