import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FloydWarshall {

        public boolean hasNegativeCycle;
        public double[][] distTo;
        public DirectedEdge[][] edgeTo;

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