package cs.tcd.ie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import tcdIO.Terminal;

public class Router extends Node {
		
	Terminal terminal;
	int routerPort;
	DatagramPacket messagePacket;
	
	InetSocketAddress controllerAddress;
	int connectionCount;
	int[] connections;
	ControllerInformPacket controllerPacket;
	
	/*
	 * Hashmap to reference node names (for printing use)
	 */
	HashMap<Integer, String> nodeNames;
	
	/*
	 * routingMap == Hashmap to store routing table within 
	 * - <Integer> = destination address
	 * - <RoutingElementKey> = object that holds hopCount (cost) and nextHop for each respective destination address;
	 */
	HashMap<Integer, RoutingElementKey> routingMap;
	
	/*
	 * 
	 */
	Router(Terminal terminal, int routerPort) {
		try {
			this.terminal= terminal;
			this.routerPort = routerPort;
			this.messagePacket = null;
			this.routingMap = new HashMap<Integer, RoutingElementKey>();
			this.nodeNames = new HashMap<Integer, String>();
			
			this.controllerAddress = new InetSocketAddress(Node.DEFAULT_DST_NODE, Node.CONTROLLER_PORT);
			this.connectionCount = 0;
			
			//Creates router socket at defined address
			socket= new DatagramSocket(routerPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	public synchronized void start() throws Exception {
		terminal.println("Initialising routing map at router (" + this.routerPort + ")...");
		this.initialiseRoutingMap();
		
		terminal.println("Filling in node names in map...");
		this.addNodeNames();
		
		terminal.println("Printing routing map at router (" + this.routerPort + ")...\n");
		this.printRoutingMap();
		
		//Inform controller of direct connections
		terminal.println("\nInforming controller of connections...");
		informController();
		
		terminal.println("\nWaiting for contact at router(" + this.routerPort + ")...");
		this.wait();
	}

	public void addNodeNames() {
		//Handle network end users names and addresses
				this.nodeNames.put(NET_18_PORT, "(NET 18)");
				this.nodeNames.put(NET_15_PORT, "(NET 15)");
				this.nodeNames.put(NET_28_PORT, "(NET 28)");
				this.nodeNames.put(NET_7_PORT, "(NET 7)");
				this.nodeNames.put(NET_5_PORT, "(NET 5)");
				this.nodeNames.put(NET_11_PORT, "(NET 11)");
				this.nodeNames.put(NET_21_PORT, "(NET 21)");
				this.nodeNames.put(NET_2_PORT, "(NET 2)");
				this.nodeNames.put(NET_10_PORT, "(NET 10)");
				
				//Handle network router names and addresses
				this.nodeNames.put(ROUTER_A_PORT, "(ROUTER A)");
				this.nodeNames.put(ROUTER_B_PORT, "(ROUTER B)");
				this.nodeNames.put(ROUTER_C_PORT, "(ROUTER C)");
				this.nodeNames.put(ROUTER_D_PORT, "(ROUTER D)");
				this.nodeNames.put(ROUTER_E_PORT, "(ROUTER E)");
				this.nodeNames.put(ROUTER_F_PORT, "(ROUTER F)");
				
				this.nodeNames.put(0, "(NULL)");
	}
	public synchronized void informController() throws IOException {
		
		initialiseConnections();
		this.controllerPacket = new ControllerInformPacket(this.routerPort, this.connections);
		DatagramPacket packet = controllerPacket.toDatagramPacket();
		packet.setSocketAddress(controllerAddress);
		socket.send(packet);
		terminal.println("Controller informed of connections...");
	}
	
	
	/*
	 * Initialises the routing map's of each respective router
	 */
	public void initialiseRoutingMap() {
				
		//Initialise the distanceMap table for the router
		switch(this.routerPort) {
			//For router A do this
			case ROUTER_A_PORT:
				this.routingMap.put(NET_18_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_B_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_C_PORT, new RoutingElementKey(0,0));
				break;
			//For router B do this
			case ROUTER_B_PORT:
				this.routingMap.put(NET_15_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(NET_28_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_A_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_D_PORT, new RoutingElementKey(0,0));
				break;
			//For router 3 do this
			case ROUTER_C_PORT:
				this.routingMap.put(NET_10_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_A_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_D_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_E_PORT, new RoutingElementKey(0,0));
				break;
			case ROUTER_D_PORT:
				this.routingMap.put(NET_7_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_C_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_B_PORT, new RoutingElementKey(0,0));
				break;
			case ROUTER_E_PORT:
				this.routingMap.put(NET_2_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(NET_21_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_C_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_F_PORT, new RoutingElementKey(0,0));
				break;
			case ROUTER_F_PORT:
				this.routingMap.put(NET_11_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(NET_5_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(ROUTER_E_PORT, new RoutingElementKey(0,0));
				break;
		}
	}
	
	/*
	 * Hard codes local connections for each router node
	 */
	public void initialiseConnections() {
		
		//Initialise the connections for the router
		switch (this.routerPort) {
		// For router A do this
		case ROUTER_A_PORT:
			this.connectionCount = 3;
			this.connections = new int[connectionCount];
			this.connections[0] = NET_18_PORT;
			this.connections[1] = ROUTER_B_PORT;
			this.connections[2] = ROUTER_C_PORT;
			break;
			// For router B do this
		case ROUTER_B_PORT:
			this.connectionCount = 4;
			this.connections = new int[connectionCount];
			this.connections[0] = NET_15_PORT;
			this.connections[1] = NET_28_PORT;
			this.connections[2] = ROUTER_A_PORT;
			this.connections[3] = ROUTER_D_PORT;
			break;
			// For router C do this
		case ROUTER_C_PORT:
			this.connectionCount = 4;
			this.connections = new int[connectionCount];
			this.connections[0] = NET_10_PORT;
			this.connections[1] = ROUTER_A_PORT;
			this.connections[2] = ROUTER_D_PORT;
			this.connections[3] = ROUTER_E_PORT;
			break;
			// For router D do this
		case ROUTER_D_PORT:
			this.connectionCount = 3;
			this.connections = new int[connectionCount];
			this.connections[0] = NET_7_PORT;
			this.connections[1] = ROUTER_B_PORT;
			this.connections[2] = ROUTER_C_PORT;
			break;
			// For router E do this
		case ROUTER_E_PORT:
			this.connectionCount = 4;
			this.connections = new int[connectionCount];
			this.connections[0] = NET_2_PORT;
			this.connections[1] = NET_21_PORT;
			this.connections[2] = ROUTER_C_PORT;
			this.connections[3] = ROUTER_F_PORT;
			break;
			// For router F do this
		case ROUTER_F_PORT:
			this.connectionCount = 3;
			this.connections = new int[connectionCount];
			this.connections[0] = NET_11_PORT;
			this.connections[1] = NET_5_PORT;
			this.connections[2] = ROUTER_E_PORT;
			break;
		}
		terminal.println("Connections initialised...");
	}
	
	
	public void printRoutingMap() {
		RoutingElementKey routingKey;
		int destination;
		int nextHop;
		int hopCount;
		
		terminal.println("\n***Routing Map for Router" + this.nodeNames.get(this.routerPort) + "***\n");
		terminal.println("Destination   |    NextHop    |   HopCount" );
		terminal.println("-------------------------------------------------------");
		
		for(Entry<Integer, RoutingElementKey> entry : this.routingMap.entrySet()) {
		    
			destination = entry.getKey();
		    routingKey = entry.getValue();
		    nextHop = routingKey.nextDest;
		    hopCount = routingKey.hopCount;
		    
		    terminal.println("" + this.nodeNames.get(destination) + "       |       " + this.nodeNames.get(nextHop) + "              |         " + hopCount);
		}
	}
	
	public void onReceipt(DatagramPacket packet) {
		try {
			
			terminal.println("\nPacket recieved at router (" + this.routerPort + ")...");
			
			//If packet is from controller, process flow update
			if(packet.getPort() == CONTROLLER_PORT) {
				terminal.println("\nPacket came from controller...");
				processControllerUpdate(packet);
				terminal.println("\nWaiting for contact at router(" + this.routerPort + ")...");
			}
			
			//If packet is not from controller, continue with flow
			else {
				this.messagePacket = packet;
				continueTransmission(packet);
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	/*
	 * continueTransmission()
	 */
	public void continueTransmission(DatagramPacket packet) throws IOException, InterruptedException {
		
		StringContent content = new StringContent(packet);
		
		//If router has routing knowledge of how to get to destination, send packet to next router
		if(this.routingMap.get(content.getDestination()).nextDest != 0){
			terminal.println("Router knows how to get to destination...");
			RoutingElementKey key = routingMap.get(content.getDestination());
			int nextHop = key.nextDest;
			
			//Set dst port of packet to that of the next router
			InetSocketAddress nextAddr = new InetSocketAddress(Node.DEFAULT_DST_NODE, nextHop);
			packet.setSocketAddress(nextAddr);
			socket.send(packet);
			
			//If next hop is an end user do this 
			if(nextHop == NET_18_PORT || nextHop == NET_15_PORT || nextHop == NET_28_PORT || nextHop == NET_7_PORT ||
					nextHop == NET_5_PORT || nextHop == NET_11_PORT || nextHop == NET_21_PORT || nextHop == NET_2_PORT ||nextHop == NET_10_PORT)
				terminal.println("\nPacket sent to end user(" + nextHop + ")...");
			
			else
			terminal.println("\nPacket sent to next router(" + nextHop + ")...");
			terminal.println("\nWaiting for contact at router(" + this.routerPort + ")...");
		}	
	}
	
	/*
	 * Processes an update packet received from controller (updates tables etc)
	 * 
	 * @param packet - Update packet from controller
	 * @returns void 
	 */
	public void processControllerUpdate(DatagramPacket packet) throws InterruptedException, IOException{
		
		NodeInformPacket content = new NodeInformPacket(packet);
		terminal.println("Processing controller update...");
		
		//Destination is the end goal of the packet
		int dst = content.getDestinationAddress();
		//Extract next hop 
		int nextHop = content.getNextHopAddress();
		//Extract hop count
		int hopCount = content.getHopCount();
		RoutingElementKey key = new RoutingElementKey(hopCount,nextHop);
		
		this.routingMap.put(dst, key);
		terminal.println("Controller update processed successfully...");
		terminal.println("Updating maps...\n");
		printRoutingMap();
	}

	/*
	 * Creates controller at defined port
	 */
	public static void main(String[] args) {
		try {
			//Read port number from  console
			System.out.print ( "Enter the port for router to be established on: " );
		    BufferedReader input = new BufferedReader ( new InputStreamReader ( System.in ));
		    String inputString = input.readLine();
		    int routerPortNumber = Integer.parseInt ( inputString );
		    
		    //Create router at defined port number
		    Terminal terminal = new Terminal("Router (" + routerPortNumber + ")");
			(new Router(terminal, routerPortNumber)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}

