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

public class DijkstraMedium {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */

    public static int N;
    public static int S;

    public static int sA;
    public static int sB;
    public static int sC;

    public static String filename;

    DijkstraMedium (String filename, int sA, int sB, int sC){

      sA = sA;
      sB = sB;
      sC = sC;

      filename = filename;
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

     /* Reads a .txt file and populates a graph with nodes accordingly*/
    private void readFile(String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

        //Read total number of vertices and edges
        N = scanner.nextInt();
        S = scanner.nextInt();

        //Create an array of edges to house the streets (edges)
        Edge[] edges = new Edge[this.S];
        int i = 0;

        //System.out.println("\nN = " + N);
        //System.out.println("S = " + S);
        //System.out.println("--------------------------------------");

        while(scanner.hasNext()){

            //Read in node info from text file
            int nodeA_ID = scanner.nextInt();
            ////System.out.println("\nv1 = " + nodeA_ID);
            int nodeB_ID = scanner.nextInt();
            ////System.out.println("v2 = " + nodeB_ID);
            double distance = scanner.nextDouble();
            ////System.out.println("Dist(v1 -> v2) = " + distance + "\n");

            edges[i] = new Edge(nodeA_ID, nodeB_ID, distance);

            i++;
        }
        System.out.println("\nEdges successfully created...");

        System.out.println("Creating Graph to house edges...");
        Graph g = new Graph(edges);
        System.out.println("Successfully created Graph...");
        g.calculateShortestDistances();
        g.printResult(); // let's try it 
    }

    public class Edge {

        private int fromNodeIndex;
        private int toNodeIndex;
        private double length;
        
        public Edge(int fromNodeIndex, int toNodeIndex, double length) {
            this.fromNodeIndex = fromNodeIndex;
            this.toNodeIndex = toNodeIndex;
            this.length = length;
        }
        
        public int getFromNodeIndex() {
            return fromNodeIndex;
        }
        
        public int getToNodeIndex() {
            return toNodeIndex;
        }
        
        public double getLength() {
            return length;
        }
        
        // determines the neighbouring node of a supplied node, based on the two nodes connected by this edge
        public int getNeighbourIndex(int nodeIndex) {
            
            if (this.fromNodeIndex == nodeIndex) 
                return this.toNodeIndex;
             
            else 
              return this.fromNodeIndex;
        }
    }

    public class Node {
        
        private double distanceFromSource = Double.MAX_VALUE;
        private boolean visited;
        private ArrayList<Edge> edges = new ArrayList<Edge>();
        
        public double getDistanceFromSource() {
            return distanceFromSource;
        }
        
        public void setDistanceFromSource(double distanceFromSource) {
            this.distanceFromSource = distanceFromSource;
        }
        
        public boolean isVisited() {
            return visited;
        }
        
        public void setVisited(boolean visited) {
            this.visited = visited;
        }
        
        public ArrayList<Edge> getEdges() {
            return edges;
        }
        
        public void setEdges(ArrayList<Edge> edges) {
            this.edges = edges;
        }
    }

    public class Graph {
        
        private Node[] nodes;
        private int noOfNodes;
        private Edge[] edges;
        private int noOfEdges;
        
        //Graph object constructor
        public Graph(Edge[] edges) {
            
            this.edges = edges;
            this.noOfNodes = calculateNoOfNodes(edges);
            this.nodes = new Node[this.noOfNodes];
            
            for (int n = 0; n < this.noOfNodes; n++) {
                  this.nodes[n] = new Node();
            }
            
            this.noOfEdges = edges.length;
            
            for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
                  this.nodes[edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
                  this.nodes[edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
            }
        }

        private int calculateNoOfNodes(Edge[] edges) {
            
            int noOfNodes = 0;
            
            for (Edge e : edges) {
                if (e.getToNodeIndex() > noOfNodes)
                    noOfNodes = e.getToNodeIndex();
            
                if (e.getFromNodeIndex() > noOfNodes)
                    noOfNodes = e.getFromNodeIndex();
            }

            noOfNodes++;
            return noOfNodes;
        }

        public void calculateShortestDistances() {
            // node 0 as source
            this.nodes[0].setDistanceFromSource(0.0);
            int nextNode = 0;
            
            // visit every node
            for (int i = 0; i < this.nodes.length; i++) {
              
                // loop around the edges of current node
                ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
                
                for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
                
                    int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);
                    // only if not visited
                    if (!this.nodes[neighbourIndex].isVisited()) {
                        double tentative = this.nodes[nextNode].getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();
                      
                        if (tentative < nodes[neighbourIndex].getDistanceFromSource()) {
                            nodes[neighbourIndex].setDistanceFromSource(tentative);
                        }
                    }
                }

                // all neighbours checked so node visited
                nodes[nextNode].setVisited(true);
                // next node must be with shortest distance
                nextNode = getNodeShortestDistanced();
            }
        }

        private int getNodeShortestDistanced() {
            
            int storedNodeIndex = 0;
            double storedDist = Double.MAX_VALUE;
            
            for (int i = 0; i < this.nodes.length; i++) {

                double currentDist = this.nodes[i].getDistanceFromSource();
              
                if (!this.nodes[i].isVisited() && currentDist < storedDist) {
                    storedDist = currentDist;
                    storedNodeIndex = i;
                }
            }

            return storedNodeIndex;
        }

        public void printResult() {
            
            String output = "Number of nodes = " + this.noOfNodes;
            output += "\nNumber of edges = " + this.noOfEdges;
            
            for (int i = 0; i < this.nodes.length; i++) {
                output += ("\nThe shortest distance from node 0 to node " + i + " is " + nodes[i].getDistanceFromSource());
            }
            
            System.out.println(output);
        }

        public Node[] getNodes() {
            return nodes;
        }

        public int getNoOfNodes() {
            return noOfNodes;
        }

        public Edge[] getEdges() {
            return edges;
        }

        public int getNoOfEdges() {
            return noOfEdges;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        DijkstraMedium test = new DijkstraMedium("txt_files/tinyEWD.txt", 50, 75, 100);
        test.readFile("txt_files/tinyEWD.txt");
    }

}