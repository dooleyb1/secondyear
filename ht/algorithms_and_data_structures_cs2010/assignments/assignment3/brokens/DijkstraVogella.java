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

public class DijkstraVogella {

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

    private static List<Vertex> nodes;
    private static List<Edge> edges;
    private static Set<Vertex> settledNodes;
    private static Set<Vertex> unSettledNodes;
    private static Map<Vertex, Vertex> predecessors;
    private static Map<Vertex, Double> distance;

    DijkstraVogella (String filename, int sA, int sB, int sC) throws FileNotFoundException{

      sA = sA;
      sB = sB;
      sC = sC;

      filename = filename;
      readFile(filename);
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

    public void execute(Vertex source) {
        
        //Create empty HashSets and HashMaps to house objects
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Double>();
        predecessors = new HashMap<Vertex, Vertex>();
        
        //Set distance from source to source = 0
        distance.put(source, 0.0);
        unSettledNodes.add(source);
        
        while (unSettledNodes.size() > 0) {
            
            //Get lowest node from unsettledNodes
            Vertex node = getMinimum(unSettledNodes);
            
            settledNodes.add(node);
            unSettledNodes.remove(node);
            
            //Find min distance from node to source
            findMinimalDistances(node);
        }
    }

    public void findMinimalDistances(Vertex node) {
        
        //Get all nodes adjacent to input node
        List<Vertex> adjacentNodes = getNeighbors(node);
        
        //Iterate over all neighbours
        for (Vertex target : adjacentNodes) {

            //If the distance from neighbour to source is lower than currently known route
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                
                //Make this the new distance
                distance.put(target, getShortestDistance(node)+ getDistance(node, target));
                //Update routing path
                predecessors.put(target, node);
                //Add node to unsettled, must be settled then
                unSettledNodes.add(target);
            }
        }
    }

    //Returns the known distance between two nodes
    public double getDistance(Vertex node, Vertex target) {
        
        for (Edge edge : edges) {

            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    //Returns vertex with the minumum distance
    public Vertex getMinimum(Set<Vertex> vertexes) {
        
        Vertex minimum = null;
        

        for (Vertex vertex : vertexes) {
            
            if (minimum == null) 
                minimum = vertex;
            
            else {
                
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                    minimum = vertex;
                
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex destination) {
        
        Double d = distance.get(destination);
        
        if (d == null) 
            return Double.MAX_VALUE;
        
        else 
            return d;
        
    }

    public LinkedList<Vertex> getPath(Vertex target) {
        
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        
        // check if a path exists
        if (predecessors.get(step) == null)
            return null;
        
        path.add(step);
        
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    public List<Vertex> getNeighbors(Vertex node) {
        
        List<Vertex> neighbors = new ArrayList<Vertex>();
        
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

     /* Reads a .txt file and populates a graph with nodes accordingly*/
    private void readFile(String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

        //Read total number of vertices and edges
        N = scanner.nextInt();
        S = scanner.nextInt();

        //Create an array of edges to house the streets (edges)
        edges = new ArrayList<Edge>();
        nodes = new ArrayList<Vertex>();

        int i = 0;

        //System.out.println("\nN = " + N);
        //System.out.println("S = " + S);
        //System.out.println("--------------------------------------");

        //Create all of the edges and nodes respectively
        while(scanner.hasNext()){

            //Read in node info from text file
            int nodeA_ID = scanner.nextInt();
            Vertex from = new Vertex(Integer.toString(nodeA_ID), Integer.toString(nodeA_ID));
            nodes.add(from);
            ////System.out.println("\nv1 = " + nodeA_ID);

            int nodeB_ID = scanner.nextInt();
            Vertex to = new Vertex(Integer.toString(nodeB_ID), Integer.toString(nodeB_ID));
            nodes.add(to);
            ////System.out.println("v2 = " + nodeB_ID);

            double distance = scanner.nextDouble();
            ////System.out.println("Dist(v1 -> v2) = " + distance + "\n");

            String edge_ID = Integer.toString(i);

            Edge edge = new Edge(edge_ID, from, to, distance); 
            edges.add(edge);
        }

        Graph graph = new Graph(nodes, edges);

        //Create a copy of nodes and edges
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());

        Vertex sourceVertex = this.nodes.get(2);

        execute(sourceVertex);
        System.out.println("\nDijkstra's ran for Source Node = [Node " + sourceVertex.getName() + "]...");
        
        for(Vertex v : this.nodes){

            double distFromSource = v.getDistance()
        }

    }

    public class Vertex {
        
        final private String id;
        final private String name;


        public Vertex(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int hashCode() {
            
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            
            if (this == obj)
                return true;
            
            if (obj == null)
                return false;
            
            if (getClass() != obj.getClass())
                return false;
            
            Vertex other = (Vertex) obj;
            
            if (id == null) {
                if (other.id != null)
                    return false;
            } 
            else if (!id.equals(other.id))
                return false;
            
            return true;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public class Edge  {
        
        private final String id;
        private final Vertex source;
        private final Vertex destination;
        private final double weight;

        public Edge(String id, Vertex source, Vertex destination, double weight) {
        
            this.id = id;
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        
        }

        public String getId() {
            return id;
        }

        public Vertex getDestination() {
            return destination;
        }

        public Vertex getSource() {
            return source;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return source + " " + destination;
        }
    }

    public class Graph {

        private final List<Vertex> vertexes;
        private final List<Edge> edges;

        public Graph(List<Vertex> vertexes, List<Edge> edges) {
            this.vertexes = vertexes;
            this.edges = edges;
        }

        public List<Vertex> getVertexes() {
            return vertexes;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        DijkstraVogella test = new DijkstraVogella("txt_files/tinyEWD.txt", 50, 75, 100);
        //test.readFile("txt_files/tinyEWD.txt");
    }

}