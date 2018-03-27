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

public class Dijkstra2 {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */

    public static int N;
    public static int S;

    public int speedA;
    public int speedB;
    public int speedC;

    public static Graph competitionGraph;

    Dijkstra2 (String filename, int sA, int sB, int sC){

        //All in m/minute
        speedA = sA; 
        speedB = sB;
        speedC = sC;

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

        int slowestSpeed = this.speedA;
        System.out.println("Slowest speed is Speed A = " + slowestSpeed);

        if(slowestSpeed > this.speedB){
            slowestSpeed = speedB;
            System.out.println("Slowest speed is now Speed B = " + slowestSpeed);
        }

        if(slowestSpeed > speedC){
            slowestSpeed = speedC;
            System.out.println("Slowest speed is now Speed C = " + slowestSpeed);
        }

        double longestTime = 0.0;
        double longestDistance;
        double time;
        Graph tempGraph;

        //Iterate over every node within the graph
        Iterator<Node> iter = competitionGraph.nodes.iterator();

        while (iter.hasNext()) {
            Node node = iter.next();

            System.out.println("Adjusting graph for Source Node = " + node.name + ".....");

            //Set the node as the source witih the graph, re-adjust distances using Dijkstras
            tempGraph = calculateShortestPathFromSource(competitionGraph, node.name);
            tempGraph.printGraph();
            longestDistance = competitionGraph.getLongestDistance();
            System.out.println("\n\nLongest distance from source (Node " + node.name + ") = " + longestDistance);

            //Time (minutes) = Distance/Speed
            time = (longestDistance * 1000)/slowestSpeed;
            System.out.println("Time for when source node is Node " + node.name + " => " + time);

            if(time > longestTime)
                longestTime = time;
        }

        System.out.println("\n\nOverall longest time is : " + longestTime + " m/minute");



        return -1;
    }

    /* Reads a .txt file and populates a graph with nodes accordingly*/
    private void readFile(String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

            //Read total number of vertices and edges
        N = scanner.nextInt();
        S = scanner.nextInt();
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

            //House node1 and node2 in a Node object
            Node a = new Node(Integer.toString(nodeA_ID));
            Node b = new Node(Integer.toString(nodeB_ID));

            //If graph already contains nodeA, update it with new distances
            if(competitionGraph.contains(a)){
                //If b is already a member node, pass actual node of B
                if(competitionGraph.contains(b)){

                    Node actualB = competitionGraph.getNode(b.name);
                    competitionGraph.updateNode(a, actualB, distance);
                }

                //If b doesn't already exist, pass new b
                else
                    competitionGraph.updateNode(a, b, distance);
            }

            //Otherwise just add it to graph
            else{
                a.addDestination(b, distance);
                competitionGraph.addNode(a);
            }

            //If graph doesn't already contain v2, add it
            if(!competitionGraph.contains(b))
                competitionGraph.addNode(b);
        }

        //competitionGraph = Dijkstra2.calculateShortestPathFromSource(competitionGraph, "0");
        //competitionGraph.printAdjacentNodes();
        //competitionGraph.printDistances();
        System.out.println("\nGraph successfully populated");
    }

    public class Graph {
 
        public Set<Node> nodes;
        
        //Graph constructor
        public Graph(){
            this.nodes = new HashSet<>();
        }

        public void addNode(Node nodeA) {
            nodes.add(nodeA);
        }
 
         //Adds a new destination and distance to existing node v1
        public void updateNode(Node a, Node b, double dist){


            Iterator<Node> iter = this.nodes.iterator();

            while (iter.hasNext()) {
                Node node = iter.next();

                if(node.name.equals(a.name)){
                    node.addDestination(b, dist);
                    return;
                }
            }
        }

        public void printGraph(){

            System.out.println("\n****PRINTING GRAPH*****\n-----------------------");

            Iterator<Node> iter = this.nodes.iterator();

            while(iter.hasNext()){

                Node node = iter.next();

                System.out.println("* [Node " + node.name + "] ->");
                System.out.println("     Distance = " + node.distance);
                System.out.println("     Shortest Path = " + node.getShortestPathString());
            }
        }

        public double getLongestDistance(){

            Iterator<Node> iter = this.nodes.iterator();
            double longestDistance = 0.0;

            while (iter.hasNext()) {

                Node node = iter.next();

                if(node.distance > longestDistance)
                    longestDistance = node.distance;
            }

            return longestDistance;

        }

        public void printAdjacentNodes(){

            Iterator<Node> iter = this.nodes.iterator();

            while (iter.hasNext()) {
             
                Node node = iter.next();
                node.printAdjacentNodes();
           
            }

        }

        public void printDistances(){

            Iterator<Node> iter = this.nodes.iterator();

            while (iter.hasNext()) {
                Node node = iter.next();

                System.out.println("\nDistance for Node " + node.name + " => " + node.distance);
                System.out.println("Path from source => " + node.getShortestPathString());
            }

        }

        public Node getNode(String nodeID){
            
            Iterator<Node> iter = this.nodes.iterator();

            while (iter.hasNext()) {
                
                Node node = iter.next();
                
                if(node.name.equals(nodeID))
                    return node;
            }
            return null;
        }

        /* Returns whether or not the graph already contains the current node */
        public boolean contains(Node inputNode) {

            Iterator<Node> iter = this.nodes.iterator();

            while (iter.hasNext()) {
                
                Node node = iter.next();

                if(node.name.equals(inputNode.name))
                    return true;
            }
            return false;
        } 
    }

    public class Node {
     
        private String name;
        private List<Node> shortestPath;
        private double distance;    
        private Map<Node, Double> adjacentNodes;
      
        //Node Constructor
        public Node(String name) {
            this.name = name;
            this.shortestPath = new LinkedList<>();
            this.distance = Double.MAX_VALUE;
            this.adjacentNodes = new HashMap<>();
        }

        public void addDestination(Node destination, double distance) {
            this.adjacentNodes.put(destination, distance);
        }
     
        public Map<Node,Double> getAdjacentNodes(){
            return this.adjacentNodes;
        }

        public double getDistance(){
            return this.distance;
        } 

        public void setDistance(double newDist){
            this.distance = newDist;
        }

        public List<Node> getShortestPath(){
            return this.shortestPath;
        }

        public void setShortestPath(List<Node> newPath){
            List<Node> copyPath =  (List<Node>) newPath.clone();
            this.shortestPath = newPath;
        }

        public String getShortestPathString(){

            Iterator<Node> iter = this.shortestPath.iterator();
            String res = "[";

            while (iter.hasNext()) {
                Node node = iter.next();

                if(iter.hasNext())
                    res += ("Node " + node.name + "->");

                else 
                    res += ("Node" + node.name + "->Node " + this.name);

            }

            res += "]\n\n";
            return res;
        }

        public void printAdjacentNodes(){

            //System.out.println("\n---------------------------------------------------");
            //System.out.println("Adjacent Nodes for Node " + this.name);
            //System.out.println("---------------------------------------------------\n");

            for(Map.Entry<Node, Double> entry : this.adjacentNodes.entrySet()){
                //System.out.println("\n(" + this.name + " -> " + entry.getKey().name + ") == " + entry.getValue() + "\n");
            }
            //System.out.println("---------------------------------------------------");
        }
    }

    public Node copyNode(Node node){

        Node resNode = new Node(node.name);
        List<Node> shortestPathCopy = (LinkedList<String>) node.getShortestPath().clone();
        double dist = node.getDistance();
        
    }

    public Graph copyGraph(Graph graph){

        Graph resultGraph = new Graph();

        Iterator<Node> iter = graph.nodes.iterator();

        while (iter.hasNext()){

            Node node = iter.next();
            Node copyNode = copyNode(node);

            resultGraph.addNode(copyNode);
        }

        return resultGraph;
    }

    public Graph calculateShortestPathFromSource(Graph graph, String sourceID) {
        
        Graph tempGraph = copyGraph(graph);
        Node source = tempGraph.getNode(sourceID);

        //Set source nodes distance from source to 0
        source.setDistance(0);
     
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
     
        //System.out.println("Source Node = " + source.name);

        //Add source to unsettled nodes i.e examine first
        unsettledNodes.add(source);
        //System.out.println("Added node " + source.name + " to unsettledNodes...\n");

        while (unsettledNodes.size() != 0) {
            
            //System.out.println("****** PRINTING UNSETTLED NODES *******\n");
            printNodeSet(unsettledNodes);

            //Get node with lowest distance from source and examine that first
            //System.out.println("Selecting node with lowest distance from unsettledNodes...\n");
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            //System.out.println("Node selected is Node " + currentNode.name);

            //Remove it from unsettled as we will now settle it
            removeNode(currentNode, unsettledNodes);
            //System.out.println("Removing Node " + currentNode.name + " from unsettledNodes, will now attempt to settle...\n");
            currentNode.printAdjacentNodes();

            //Iterate over all of currentNodes adjacent nodes
            for (Map.Entry<Node, Double> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
                
                //System.out.println("Getting adjacent node to Node " + currentNode.name + "...");
                Node adjacentNode = adjacencyPair.getKey();
                double edgeWeight = adjacencyPair.getValue();
                //System.out.println("Adjacent node selected is Node " + adjacentNode.name + "(edgeWight of " + edgeWeight + ")");


                //If the adjacent node hasn't already been settled
                if (!alreadySettled(adjacentNode, settledNodes)) {

                    //System.out.println("Node " + adjacentNode.name + " has not been settled, running tests...\n");
                    //System.out.println("\n--------------------------------------------");
                    
                    //Find min dist from current node to adjacent node
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);

                    //System.out.println("Adding Node " + adjacentNode.name + " to unsettledNodes queue...\n");
                    unsettledNodes.add(adjacentNode);
                }

                else{
                    //System.out.println("Node is already settled!\n");
                }
            }
            //System.out.println("EXITING LARGE FOR LOOP!!!! All adjacent pairs to node " + currentNode.name + " settled\n");
            settledNodes.add(currentNode);

            //System.out.println("****** PRINTING SETTLED NODES *******\n");
            printNodeSet(settledNodes);

        }
        //System.out.println("All nodes successfully settled");
        return tempGraph;
    }

    private static void printNodeSet(Set<Node> nodeSet){

        Iterator<Node> iter = nodeSet.iterator();
        String res = "[";

        while (iter.hasNext()) {
            Node node = iter.next();

            res += (node.name + ",");
        }

        res += "]\n\n";
        //System.out.println(res);

    }

    private static void removeNode(Node nodeToRemove, Set<Node> unsettledNodes){

        Iterator<Node> iter = unsettledNodes.iterator();

        while (iter.hasNext()) {
            Node node = iter.next();

            if (node.name.equals(nodeToRemove.name))
                iter.remove();
        }

        /* 
        for(Node node : unsettledNodes){
            if(node.name.equals(nodeToRemove.name))
                unsettledNodes.remove(node);
        }
        */
    }

    

    private static boolean alreadySettled(Node testNode, Set<Node> settledNodes){

        for(Node node : settledNodes){
            if(node.name.equals(testNode.name))
                return true;
        }
        return false;

    }

    private static Node getLowestDistanceNode(Set <Node> unsettledNodes) {
        
        //Start with empty node and Double.MaxVal
        Node lowestDistanceNode = null;
        double lowestDistance = Double.MAX_VALUE;
        
        /* Iterate over all current unsettled nodes and return the one with lowest 
         * distance from the source node
         */
        for (Node node: unsettledNodes) {
            
            Node properNodeElement = competitionGraph.getNode(node.name);
            double nodeDistance = properNodeElement.getDistance();
            //System.out.println("Node distance for Node " + properNodeElement.name + " is : " + nodeDistance);

            if (nodeDistance < lowestDistance) {
                //System.out.println("This is less than current lowestDistance : " + lowestDistance);
                lowestDistance = nodeDistance;
                //SEE HERE IF STILL BROKEN (MAYBE COPY OVER ADJACENCY PAIRS)
                lowestDistanceNode = competitionGraph.getNode(node.name);

                //System.out.println("New lowest distance is now " + lowestDistance + " (Node " + lowestDistanceNode.name +")\n\n");
            }
        }

        return lowestDistanceNode;
    }

    /* Calculates the min dist from x (sourceNode) to y (evaluationNode) */
    private static void calculateMinimumDistance(Node evaluationNode, double edgeWeigh, Node sourceNode) {
        
        Node actualSourceNode = competitionGraph.getNode(sourceNode.name);
        double sourceDistance = actualSourceNode.getDistance();
        Node actualEvaluationNode = competitionGraph.getNode(evaluationNode.name);

        //System.out.println("sourceDistance (Node " + actualSourceNode.name + ".distance) = " + sourceDistance);
        //System.out.println("edgeWeight (Node " + actualEvaluationNode.name + ".distance) = " + edgeWeigh);
        //System.out.println("\nsourceDistance + edgeWeigh = " + (sourceDistance + edgeWeigh));
        //System.out.println("evaluationNode (Node " + actualEvaluationNode.name + ".getDistance) = " + evaluationNode.getDistance());

        //If newly explored path is quicker than current known path, overwrite
        if (sourceDistance + edgeWeigh < actualEvaluationNode.getDistance()) {
            
            //System.out.println("\nsourceDistance + edgeWeigh < evaluationNode.getDistance = true");

            actualEvaluationNode.setDistance(sourceDistance + edgeWeigh);

            LinkedList<Node> shortestPath = new LinkedList<>(actualSourceNode.getShortestPath());
            //System.out.println("sourceNode (Node " + actualSourceNode.name + ").getShortestPath() =" + shortestPath);
            shortestPath.add(actualSourceNode);
            
            //System.out.println("\nSetting the new shortest path for Node " + actualEvaluationNode.name + "...");
            actualEvaluationNode.setShortestPath(shortestPath);
            //System.out.println("evaluationNode (Node " + evaluationNode.name + ").getShortestPath() =" + actualEvaluationNode.getShortestPathString());
        }
        //System.out.println("--------------------------------------------\n\n");
    }


    public static void main(String[] args) throws FileNotFoundException {

        Dijkstra2 test = new Dijkstra2("tinyEWD.txt", 75, 75, 100);
        test.readFile("tinyEWD.txt");

        int x = test.timeRequiredforCompetition();
    }
}