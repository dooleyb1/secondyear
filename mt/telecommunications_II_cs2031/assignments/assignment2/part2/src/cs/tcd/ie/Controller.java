package cs.tcd.ie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
				terminal.println("Shortest path from " + src + nodeNames.get(src) + " to "+ dst + nodeNames.get(dst) + " is as follows:");
				path = getQuickestRouteBetween(src,dst);
				//When route found, inform affected nodes of their respective next hop
				int i = 0;
				for(int node : path) {
					//If node is not an end user, do this (don't inform end users as only one connection - that to router)
					if(!endUsers.contains(node)) {
						int nodesNextHop = path.get(i+1);
						int hopCount = path.size()-(i+1);
						i++;
						terminal.println(node + nodeNames.get(node));	
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
		this.nodeNames.put(NET_18_PORT, "(NET 18)");
		this.endUsers.add(NET_18_PORT);
		this.nodeNames.put(NET_15_PORT, "(NET 15)");
		this.endUsers.add(NET_15_PORT);
		this.nodeNames.put(NET_28_PORT, "(NET 28)");
		this.endUsers.add(NET_28_PORT);
		this.nodeNames.put(NET_7_PORT, "(NET 7)");
		this.endUsers.add(NET_7_PORT);
		this.nodeNames.put(NET_5_PORT, "(NET 5)");
		this.endUsers.add(NET_5_PORT);
		this.nodeNames.put(NET_11_PORT, "(NET 11)");
		this.endUsers.add(NET_11_PORT);
		this.nodeNames.put(NET_21_PORT, "(NET 21)");
		this.endUsers.add(NET_21_PORT);
		this.nodeNames.put(NET_2_PORT, "(NET 2)");
		this.endUsers.add(NET_2_PORT);
		this.nodeNames.put(NET_10_PORT, "(NET 10)");
		this.endUsers.add(NET_10_PORT);
		
		//Handle network router names and addresses
		this.nodeNames.put(ROUTER_A_PORT, "(ROUTER A)");
		this.routers.add(ROUTER_A_PORT);
		this.nodeNames.put(ROUTER_B_PORT, "(ROUTER B)");
		this.routers.add(ROUTER_B_PORT);
		this.nodeNames.put(ROUTER_C_PORT, "(ROUTER C)");
		this.routers.add(ROUTER_C_PORT);
		this.nodeNames.put(ROUTER_D_PORT, "(ROUTER D)");
		this.routers.add(ROUTER_D_PORT);
		this.nodeNames.put(ROUTER_E_PORT, "(ROUTER E)");
		this.routers.add(ROUTER_E_PORT);
		this.nodeNames.put(ROUTER_F_PORT, "(ROUTER F)");
		this.routers.add(ROUTER_F_PORT);
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

