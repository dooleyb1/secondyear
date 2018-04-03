import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

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