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

/* How to read file...
 *
 * Line 1 = Total Number of Intersections (Vertices)  - integer
 * Line 2 = Total Number of Streets (Edges) - integer
 * 
 * Line 3,4...k = Each line is a street (E) connecting two intersections (V)
 *          
 *      int v1 = line.nextInteger()            //Intersection (Vertice) One
 *      int v1 = line.nextInteger()            //Intersection (Vertice) One
 *      double dist = line.nextDouble()        //Street (Edge) Length
 */

public class CompetitionDijkstra {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */

    public static int N;
    public static int S;
    public static Graph competitionGraph;

    CompetitionDijkstra (String filename, int sA, int sB, int sC){

        //All in m/minute
        int speedA = sA; 
        int speedB = sB;
        int speedC = sC;

        //Total number of intersections (vertices)
        N = 0;

        //Total number of streets (edges)
        S = 0;

        competitionGraph = new Graph();

    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

    private void readFile(String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

            //Read total number of vertices and edges
        N = scanner.nextInt();
        S = scanner.nextInt();
        System.out.println("\nN = " + N);
        System.out.println("S = " + S);
        System.out.println("--------------------------------------");

        while(scanner.hasNext()){

            //Read in node info from text file
            int v1 = scanner.nextInt();
            //System.out.println("\nv1 = " + v1);
            int v2 = scanner.nextInt();
            //System.out.println("v2 = " + v2);
            double distance = scanner.nextDouble();
            //System.out.println("Dist(v1 -> v2) = " + distance + "\n");

            //Create node objects to house v1 and v2
            Node a = new Node(Integer.toString(v1));
            Node b = new Node(Integer.toString(v2));

            //Link v1 to v2 with respective distance
            a.addDestination(b, distance);

            //If graph already contains v1, update it with new distances
            if(competitionGraph.contains(a))
                competitionGraph.updateNode(a, b, distance);
            
            //Otherwise just add it to graph
            else{
                competitionGraph.addNode(a);
            }

            //If graph doesn't already contain v2, add it
            if(!competitionGraph.contains(b))
                competitionGraph.addNode(b);
        }

        //competitionGraph.printAdjacentNodes();
        Node testNode = competitionGraph.getNode("1");
        competitionGraph = calculateShortestPathFromSource(competitionGraph, testNode);
        //competitionGraph.printNodes();
        //competitionGraph.printDistances();
        //competitionGraph.printShortestDistanceFrom("0");
    }

    /* Private class to represent a graph as a set of nodes (Vertices i.e Intersections)*/
    private class Graph {
 
        private Set<Node> nodes = new HashSet<>();
         
        public void addNode(Node nodeA) {
            nodes.add(nodeA);
        }

        /* Adds a new destination and distance to existing node v1*/
        public void updateNode(Node a, Node b, double dist){

            //Find Node object for v1 and add new destination
            for(Node node : nodes){
                if(node.name.equals(a.name)){
                    //Update existing node with a new destination 
                    node.addDestination(b, dist);
                    return;
                }
            }

        }

        public void printAdjacentNodes(){

            for(Node node : this.nodes)
                node.printAdjacentNodes();

        }

        public void printShortestDistanceFrom(String nodeID){

            for(Node node : this.nodes){
                if(node.name.equals(nodeID)){
                    System.out.println("Calculating shortest distances from Node " + nodeID + "to all nodes...");
                    competitionGraph = calculateShortestPathFromSource(competitionGraph, node);
                }

                for(Node memberNode : competitionGraph.nodes){
                    System.out.println("\nDistance from ( " + nodeID + " -> " + memberNode.name +") == " + memberNode.distance);
                }
                return;
            }
        }

        public void printDistances(){
            for(Node node : this.nodes)
                System.out.println("Node " + node.name + " Distance = " + node.distance);
        }

        public Node getNode(String nodeID){
            for(Node node : this.nodes){
                if(node.name.equals(nodeID))
                    return node;
            }
            return null;
        }

        /* Returns whether or not the graph already contains the current node */
        public boolean contains(Node inputNode) {

            for(Node node : nodes){
                if(node.name.equals(inputNode.name))
                    return true;
            }
            return false;

        }  

        public void printNodes(){
            for(Node node : nodes){
                System.out.println(node.name);
            }
        } 
    }

    /* Private class to represent each individual node (Vertice i.e Intersection)*/
    private class Node {
     
        public String name;
         
        private List<Node> shortestPath = new LinkedList<>();
         
        public double distance = Double.POSITIVE_INFINITY;
        
        //Connected intersections and their respective distances 
        public Map<Node, Double> adjacentNodes = new HashMap<>();
     
        public void addDestination(Node destination, double distance) {
            adjacentNodes.put(destination, distance);
        }

        public String getShortestPathString(){

            String result = "[";

            for(Node node : this.shortestPath){
                result += ("Node " + node.name + ",");
            }

            result += "]";
            return result;
        }

        public void printAdjacentNodes(){

            System.out.println("\n---------------------------------------------------");
            System.out.println("Adjacent Nodes for Node " + this.name);
            System.out.println("---------------------------------------------------\n");

            for(Map.Entry<Node, Double> entry : this.adjacentNodes.entrySet()){
                System.out.println("\n(" + this.name + " -> " + entry.getKey().name + ") == " + entry.getValue() + "\n");
            }
            System.out.println("---------------------------------------------------");
        }

        public void printShortestDistances(){

            System.out.println("\n---------------------------------------------------");
            System.out.println("Shortest Distances from Node " + this.name + " to Adjacent Nodes");
            System.out.println("---------------------------------------------------");

            for(Map.Entry<Node, Double> entry : adjacentNodes.entrySet()){
                System.out.println("\n(" + this.name + " -> " + entry.getKey().name + ") == " + entry.getValue());
            }

        }
      
        public Node(String name) {
            this.name = name;
        }

        public double getDistance(){
            return this.distance;
        }

        public Map<Node, Double> getAdjacentNodes(){
            return this.adjacentNodes;
        }

        public void setDistance(double dist){
            this.distance = dist;
        }

        public List<Node> getShortestPath(){
            return this.shortestPath;
        }

        public void setShortestPath(List<Node> newPath){
            this.shortestPath = newPath;
        }
    }

    /* Calculates shortest paths around the graph from Node source to all other vertices*/
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        
        source.setDistance(0.0);
        //graph.printAdjacentNodes();
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        
        System.out.println("Source Node = " + source.name);

        //First examine source node
        unsettledNodes.add(source);
        System.out.println("Added node " + source.name + " to unsettledNodes...\n");
     
        //While there are still nodes to examine
        while (unsettledNodes.size() != 0) {
            
            //Get node with shortest distance in unsettledNodes
            System.out.println("Selecting node with lowest distance from unsettledNodes...\n");
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            System.out.println("Node selected is Node " + currentNode.name);

            //Remove this node from unsettledNodes as it will now be settled
            unsettledNodes.remove(currentNode);
            System.out.println("Removing Node " + currentNode.name + " from unsettledNodes, will now attempt to settle...\n");
            currentNode.printAdjacentNodes();

            //Check this nodes adjacent nodes
            Map<Node, Double> adjacentNodesMap = currentNode.getAdjacentNodes();
            for (Map.Entry<Node, Double> adjacencyPair : adjacentNodesMap.entrySet()) {
                System.out.println("Getting adjacent node to Node " + currentNode.name);
                Node adjacentNode = adjacencyPair.getKey();
                double edgeWeight = adjacencyPair.getValue();
                System.out.println("Adjacent node selected is Node " + adjacentNode.name);

                //If this adjacent node hasn't already been settled
                if (!settledNodes.contains(adjacentNode)) {
                    System.out.println("Node " + adjacentNode.name + " has not been settled, running tests...\n");
                    System.out.println("\n--------------------------------------------");
                    //Find min dist from current node to adjacent node
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    System.out.println("Adding Node " + adjacentNode.name + " to unsettledNodes queue...\n");
                    Node x = graph.getNode(adjacentNode.name);
                    unsettledNodes.add(x);
                }
            }
            System.out.println("EXITING LARGE FOR LOOP!!!! All adjacent pairs to node " + currentNode.name + " settled\n");
            
            //This node has now been settled, do the rest now

            settledNodes.add(currentNode);
        }
        System.out.println("All nodes successfully settled");
        return graph;
    }

    /* Returns the node with the lowest distance from the unsettledNodes set */
    private static Node getLowestDistanceNode(Set <Node> unsettledNodes) {
        
        //Null initialised lowest distance node
        Node lowestDistanceNode = null;
        double lowestDistance = Double.POSITIVE_INFINITY;
        
        //Iterate over unsettled nodes finding one with lowest distance
        for (Node node: unsettledNodes) {
            
            double nodeDistance = node.getDistance();
            System.out.println("Node distance for Node " + node.name + " is : " + nodeDistance);
            //If nodeDistance is less than current lowest, make this node the new lowest
            if (nodeDistance < lowestDistance) {
                System.out.println("This is less than current lowestDistance : " + lowestDistance);
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
                System.out.println("New lowest distance is now " + lowestDistance + " (Node " + lowestDistanceNode.name +")\n\n");
            }
        }
        
        return lowestDistanceNode;
    }

    /* Compares the actual distance with the newly calculated distance for the new path*/
    private static void calculateMinimumDistance(Node evaluationNode, double edgeWeigh, Node sourceNode) {
        
        //Get source node's distance
        double sourceDistance = sourceNode.getDistance();
        System.out.println("sourceDistance (Node " + sourceNode.name + ".distance) = " + sourceDistance);
        System.out.println("edgeWeight (Node " + evaluationNode.name + ".distance) = " + edgeWeigh);
        System.out.println("\nsourceDistance + edgeWeigh = " + (sourceDistance + edgeWeigh));
        System.out.println("evaluationNode (Node " + evaluationNode.name + ".getDistance) = " + evaluationNode.getDistance());
        
        //If the distance from source + edge is less than evalNodes current distance from source 
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {

            System.out.println("\nsourceDistance + edgeWeigh < evaluationNode.getDistance = true");
            //Set evalNodes new distance to that of src + edge weight
            evaluationNode.setDistance(sourceDistance + edgeWeigh);

            //Get current shortest path and append the new node to it
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            System.out.println("sourceNode (Node " + sourceNode.name + ").getShortestPath() =" + shortestPath);
            shortestPath.add(sourceNode);

            //Set the evaluationNodes shortest path to that of source to x
            System.out.println("\nSetting the new shortest path for Node " + evaluationNode.name + "...");
            evaluationNode.setShortestPath(shortestPath);
            System.out.println("evaluationNode (Node " + evaluationNode.name + ").getShortestPath() =" + evaluationNode.getShortestPathString());
        }
        System.out.println("--------------------------------------------\n\n");
    }

    public static void main(String[] args) throws FileNotFoundException {

        CompetitionDijkstra test = new CompetitionDijkstra("tinyEWD.txt", 0, 0, 0);
        test.readFile("tinyEWD.txt");

    }
}