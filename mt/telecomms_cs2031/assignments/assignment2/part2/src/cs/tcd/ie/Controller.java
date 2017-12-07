package cs.tcd.ie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

import tcdIO.Terminal;

public class Controller extends Node {
	
	Terminal terminal;
	int controllerPort;
	boolean allInfoReceived;
	/*
	 * Hashmap to reference node names
	 */
	HashMap<Integer, String> nodeNames;
	
	/*
	 * Hashmap to store routing table within 
	 * - <Destination> = routeID#
	 * - HashMap<Node, NextHop> = maps sequential flow for each router for unique routeID
	 */
	HashMap<Integer, HashMap<Integer,Integer>> routingMap;
	
	/*
	 * Hashmap to store network connections for each node
	 * - <Integer> = node address
	 * - ArrayList<Integer> = nodes connections
	 */
	HashMap<Integer, ArrayList<Integer>> networkConnections;
	ArrayList<Integer> shortestPath;
	ArrayList<Integer> endUsers;
	ArrayList<Integer> routers;
	int nodeCount;
	
	/*
	 * 
	 */
	Controller(Terminal terminal, int controllerPort) {
		try {
			//Initialise variables
			this.terminal= terminal;
			this.controllerPort = controllerPort;
			this.allInfoReceived = false;
			this.nodeCount = 0;
			
			//Initialise maps & array lists
			this.routingMap = new HashMap<Integer, HashMap<Integer,Integer>>();
			this.networkConnections = new HashMap<Integer, ArrayList<Integer>>();
			this.nodeNames = new HashMap<Integer, String>();
			this.endUsers = new ArrayList<Integer>();
			this.routers = new ArrayList<Integer>();
			this.shortestPath = new ArrayList<Integer>();
			fillNodeNames();
			
			//Creates router socket at defined address
			socket= new DatagramSocket(controllerPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	public synchronized void start() throws Exception {
		terminal.println("Controller initialised at (" + this.controllerPort + ")...");
		
		terminal.println("Waiting for contact at controller...");
		
		this.wait();
	}
	
	/*
	 * Method that does Distance Vector Routing calculation. Calculates the next hop for each node when packet is to be sent.
	 */
	public void doDistanceVector() throws IOException {
		//Calculate route from each end user to end user
		ArrayList<Integer> otherEndUsers;
		ArrayList<Integer> path;
		terminal.println("Finding shortest path between end users in network...\n");
		InetSocketAddress nodeAddress;
		//For each end user, find routes to each other end user
		for(int src : this.endUsers) {
			otherEndUsers = new ArrayList<Integer>();
			path = new ArrayList<Integer>();
			//Get all other end users in the network (routes need to be established for each)
			for(int otherUser : this.endUsers) {
				if(otherUser != src)
					otherEndUsers.add(otherUser);
			}
			//For each possible other end user node, find quickest route
			for(int dst : otherEndUsers) {
				terminal.println("Path from " + src + nodeNames.get(src) + " to "+ dst + nodeNames.get(dst) + " is as follows:");
				path = getQuickestRouteBetween(src,dst);
				//When route found, inform affected nodes of their respective next hop
				int i = 0;
				for(int node : path) {
					//If node is not an end user, do this (don't inform end users as only one connection - that to router)
					if(node!=END_USER_1_PORT && node!=END_USER_2_PORT) {
						int nodesNextHop = path.get(i+1);
						int hopCount = path.size()-(i+1);
						i++;
						terminal.println("\nNodeInformPacket: "
								+ "\nNode = " + node + nodeNames.get(node) 
								+ "\nDst = " + dst + nodeNames.get(dst)
								+ "\nNextHop = " +nodesNextHop + nodeNames.get(nodesNextHop)
								+ "\nHopCount = " + hopCount);	
						NodeInformPacket informPacket = new NodeInformPacket(node,dst,nodesNextHop,hopCount);
						DatagramPacket packet = informPacket.toDatagramPacket();
						nodeAddress = new InetSocketAddress(Node.DEFAULT_DST_NODE, node);
						packet.setSocketAddress(nodeAddress);
						socket.send(packet);
					}
					else
						i++;
				}
				
				terminal.println("\n");
			}
				
		}
	}
	
	/*
	 * Gets quickest route between node x and node y using Breadth-First-Search (BFS) 
	 * Inspiration from (https://codereview.stackexchange.com/questions/84717/shortest-path-using-breadth-first-search)
	 * 
	 * @param src	source node address
	 * @param dst	destination node address
	 * 
	 * @returns route	HashMap representing quickest route for each node in route HashMap<Node><NextHop>
	 */
	public ArrayList<Integer> getQuickestRouteBetween(int src, int dst){
		ArrayList<Integer> path = new ArrayList<Integer>();
		this.shortestPath.clear();
		
		 // If the source is the same as destination, I'm done.
        if (src == dst) {
            path.add(src);
            return path;
        }
        
        // A queue to store the un-visited nodes.
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

        // A queue to store the visited nodes.
        ArrayDeque<Integer> visited = new ArrayDeque<Integer>();
        
        queue.offer(src);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            visited.offer(vertex);

            //Get connections for current vertex
            ArrayList<Integer> neighboursList = this.networkConnections.get(vertex);
            int index = 0;
            int neighboursSize = neighboursList.size();
            while (index != neighboursSize) {
                int neighbour = neighboursList.get(index);

                path.add(neighbour);
                path.add(vertex);

                if (neighbour == dst) {
                    return processPath(src, dst, path);
                } else {
                    if (!visited.contains(neighbour)) {
                        queue.offer(neighbour);
                    }
                }
                index++;
            }
        }
        return null;
    }
		
	
	/**
     * Adds the nodes involved in the shortest path.
     *
     * @param src         The source node.
     * @param destination The destination node.
     * @param path        The path that has nodes and their neighbours.
     * @return The shortest path.
     */
    private ArrayList<Integer> processPath(int src, int destination,
                                                 ArrayList<Integer> path) {

    	
        // Finds out where the destination node directly comes from.
        int index = path.indexOf(destination);
        Integer source = path.get(index + 1);

        // Adds the destination node to the shortestPath.
        this.shortestPath.add(0, destination);

        if (source.equals(src)) {
            // The original source node is found.
            shortestPath.add(0, src);
            return this.shortestPath;
        } else {
            // We find where the source node of the destination node
            // comes from.
            // We then set the source node to be the destination node.
            return processPath(src, source, path);
        }
    }
    
	/*
	 * Populates endUsers and routers ArrayLists, also populates nodeNames HashMap with respective names for each node
	 */
	public void fillNodeNames() {
		//Handle network end users names and addresses
		this.nodeNames.put(END_USER_1_PORT, "(END USER 1)");
		this.endUsers.add(END_USER_1_PORT);
		this.nodeNames.put(END_USER_2_PORT, "(END USER 2)");
		this.endUsers.add(END_USER_2_PORT);
		
		//Handle network router names and addresses
		this.nodeNames.put(ROUTER_1_PORT, "(ROUTER 1)");
		this.routers.add(ROUTER_1_PORT);
		this.nodeNames.put(ROUTER_2_PORT, "(ROUTER 2)");
		this.routers.add(ROUTER_2_PORT);
		this.nodeNames.put(ROUTER_3_PORT, "(ROUTER 3)");
		this.routers.add(ROUTER_3_PORT);
		this.nodeNames.put(ROUTER_4_PORT, "(ROUTER 4)");
		this.routers.add(ROUTER_4_PORT);
		this.nodeNames.put(ROUTER_5_PORT, "(ROUTER 5)");
		this.routers.add(ROUTER_5_PORT);
		this.nodeNames.put(ROUTER_6_PORT, "(ROUTER 6)");
		this.routers.add(ROUTER_6_PORT);
		this.nodeNames.put(ROUTER_7_PORT, "(ROUTER 7)");
		this.routers.add(ROUTER_7_PORT);
		this.nodeNames.put(ROUTER_8_PORT, "(ROUTER 8)");
		this.routers.add(ROUTER_8_PORT);
	}
	
	public void hardCodeRoutingMap() {
		
		//Sequence Map for routeID = 1 (E1 to E2)
		//Defines the next hop for each router for sequence
		HashMap<Integer, Integer> sequenceMap1 = new HashMap<Integer, Integer>();
		
		//sequenceMap.put(ROUTER,NEXTHOP)
		sequenceMap1.put(ROUTER_1_PORT, ROUTER_2_PORT);
		sequenceMap1.put(ROUTER_2_PORT, ROUTER_3_PORT);
		sequenceMap1.put(ROUTER_3_PORT, END_USER_2_PORT);
		
		// Sequence Map for routeID = 1 (E1 to E2)
		// Defines the next hop for each router for sequence
		HashMap<Integer, Integer> sequenceMap2 = new HashMap<Integer, Integer>();
		
		//sequenceMap.put(ROUTER,NEXTHOP)
		sequenceMap2.put(ROUTER_1_PORT, END_USER_1_PORT);
		sequenceMap2.put(ROUTER_2_PORT, ROUTER_1_PORT);
		sequenceMap2.put(ROUTER_3_PORT, ROUTER_2_PORT);		
		
		this.routingMap.put(ROUTE_ID_1, sequenceMap1);
		this.routingMap.put(ROUTE_ID_2, sequenceMap2);
	}
	
	public void printRoutingMap() {
		int routerAddress;;
		int nextHop;
		
		terminal.println("\n***Routing Map for Route ID#1 (E1 to E2)***\n");
		terminal.println("Router Address   |   NextHop" );
		terminal.println("-------------------------------------------------------");
		
		for(Entry<Integer, Integer> entry : this.routingMap.get(ROUTE_ID_1).entrySet()) {
		    
			routerAddress = entry.getKey();
			nextHop = entry.getValue();
		     
		    terminal.println("" + routerAddress + "               |       " + nextHop);
		}
		
		terminal.println("\n***Routing Map for Route ID#2 (E2 to E1)***\n");
		terminal.println("Router Address   |   NextHop" );
		terminal.println("-------------------------------------------------------");
		
		for(Entry<Integer, Integer> entry : this.routingMap.get(ROUTE_ID_2).entrySet()) {
		    
			routerAddress = entry.getKey();
			nextHop = entry.getValue();
		     
		    terminal.println("" + routerAddress + "               |       " + nextHop);
		}
	}
	
	public void printNetworkConnections() {
		int nodeAddress;
		ArrayList<Integer> connections;
		
		terminal.println("Node Address           |   Connections" );
		terminal.println("---------------------------------------------");
		
		for (Entry<Integer, ArrayList<Integer>> entry : this.networkConnections.entrySet()) {

			nodeAddress = entry.getKey();
			connections = entry.getValue();
			terminal.println("" + nodeAddress + " " + nodeNames.get(nodeAddress) + "    |       ");
			
			for(Integer i : connections)
				terminal.println("                                |       " + i + " " + nodeNames.get(i));
			
			terminal.println("-----------------------------------------------");
		}
		
	}

	
	public void onReceipt(DatagramPacket packet) {
		try {
			
			terminal.println("\nPacket recieved at controller (" + this.controllerPort + ")...\n");
			
			//Check flag to see what type of packet it is 
			byte[] buffer = null;
			buffer = packet.getData();
			byte[] flagBuf = new byte[PacketContent.FLAG_LENGTH];
			System.arraycopy(buffer, 0, flagBuf, 0, PacketContent.FLAG_LENGTH);
			int flag = ByteBuffer.wrap(flagBuf).getInt();
			
			//Process flag
			switch(flag) {
			//If flag = 1, packet is from router seeking flow update
			case 1:
				terminal.println("Packet is from router seeking flow update...");
				//handleUpdateRequest();
				break;
			//If flag = 2, packet is from a node informing of connections
			case 2:
				terminal.println("Packet is from node informing of connections...");
				handleConnectionInform(packet);
				printNetworkConnections();
				if(this.nodeCount == NETWORK_NODE_COUNT) {
					doDistanceVector();
				}
				break;
			default:
				terminal.println("Unknown flag...");
				break;
			}
		
			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	/*
	 * Handles a connection inform  packet from a node in the network
	 * 
	 * @param packet : packet containing connection information for a given node 
	 */
	public void handleConnectionInform(DatagramPacket packet) {
		terminal.println("Handling connection inform packet...");
		ControllerInformPacket info = new ControllerInformPacket(packet);
		int nodeAddress = info.getSource();
		int[] nodeConnections = info.getConnectionAddresses();
		ArrayList<Integer> connections = new ArrayList<Integer>();
		
		//Iterate through connections storing them to arraylist
		for(int i=0;i<nodeConnections.length;i++) {
			connections.add(nodeConnections[i]);
		}
		
		//Store connections for given node
		this.networkConnections.put(nodeAddress, connections);
		
		this.nodeCount++;
		terminal.println("Node count = " + this.nodeCount);
		terminal.println("Network connections updated...\n");
	}
	
	

	public static void main(String[] args) {
		try {
  		    //Create router at defined port number
		    Terminal terminal= new Terminal("Controller");
			(new Controller(terminal, CONTROLLER_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}

