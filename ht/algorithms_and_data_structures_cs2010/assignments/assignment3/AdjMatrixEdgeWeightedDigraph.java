import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixEdgeWeightedDigraph {

        private final int V;
        private int E;
        private DirectedEdge[][] edgeFromTo;
        private boolean isValid;

        public AdjMatrixEdgeWeightedDigraph(Scanner in){

            this.V = in.nextInt();
            this.E = in.nextInt();
            this.isValid = true;

            this.edgeFromTo = new DirectedEdge[V][V];
            
            int currentEdges = E;
            
            if(this.V < 3)
                this.isValid = false;

            if(this.isValid){
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
        public class AdjIterator implements Iterator<DirectedEdge>, Iterable<DirectedEdge> {
            
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

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public DirectedEdge next() {
                return edgeFromTo[v][w++];
            }
        }
    }