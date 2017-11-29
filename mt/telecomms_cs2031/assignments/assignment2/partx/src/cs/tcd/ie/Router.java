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
	boolean onHold;
	DatagramPacket messagePacket;
	
	InetSocketAddress controllerAddress;
	int connectionCount;
	int[] connections;
	ControllerInformPacket controllerPacket;
	
	/*
	 * routingMap == Hashmap to store routing table within 
	 * - <Integer> = destination address
	 * - <RoutingElementKey> = object that holds hopCount (cost) and nextHop for each respective destination address;
	 */
	HashMap<Integer, RoutingElementKey> routingMap;
	
	HashMap<Integer, Integer> distanceMap;
	
	/*
	 * 
	 */
	Router(Terminal terminal, int routerPort) {
		try {
			this.terminal= terminal;
			this.routerPort = routerPort;
			this.onHold = false;
			this.messagePacket = null;
			this.routingMap = new HashMap<Integer, RoutingElementKey>();
			this.distanceMap = new HashMap<Integer, Integer>();
			
			this.controllerAddress = new InetSocketAddress(Node.DEFAULT_DST_NODE, Node.CONTROLLER_PORT);
			this.connectionCount = 0;
			this.connections = null;
			
			//Creates router socket at defined address
			socket= new DatagramSocket(routerPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	public synchronized void start() throws Exception {
		terminal.println("Initialising distance map at router (" + this.routerPort + ")...");
		this.initialiseDistanceMap();
		terminal.println("Sucess\n");
		
		terminal.println("Initialising routing map at router (" + this.routerPort + ")...");
		this.initialiseRoutingMap();
		terminal.println("Sucess\n");
		
		terminal.println("Printing maps at router (" + this.routerPort + ")...\n");
		this.printMaps();
		
		//Inform controller of direct connections
		terminal.println("Informing controller of connections...");
		informController();
		
		terminal.println("\nWaiting for contact at router(" + this.routerPort + ")...");
		this.wait();
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
	 * Initialises the distanceMap's of each respective router (distance from A-B)
	 */
	public void initialiseDistanceMap() {
		
		//Initialise the distanceMap table for the router
		switch(this.routerPort) {
			case ROUTER_1_PORT:
				this.distanceMap.put(ROUTER_2_PORT, 0);
				this.distanceMap.put(ROUTER_3_PORT, 0);
				this.distanceMap.put(END_USER_1_PORT, 0);
				this.distanceMap.put(END_USER_2_PORT, 0);
			case ROUTER_2_PORT:
				this.distanceMap.put(ROUTER_1_PORT, 0);
				this.distanceMap.put(ROUTER_3_PORT, 0);
				this.distanceMap.put(END_USER_1_PORT, 0);
				this.distanceMap.put(END_USER_2_PORT, 0);
			case ROUTER_3_PORT:
				this.distanceMap.put(ROUTER_1_PORT, 0);
				this.distanceMap.put(ROUTER_2_PORT, 0);
				this.distanceMap.put(END_USER_1_PORT, 0);
				this.distanceMap.put(END_USER_2_PORT, 0);
		}
	}
	
	/*
	 * Initialises the routing map's of each respective router
	 */
	public void initialiseRoutingMap() {
				
		//Initialise the distanceMap table for the router
		switch(this.routerPort) {
			//For router 1 do this
			case ROUTER_1_PORT:
				this.routingMap.put(END_USER_1_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(END_USER_2_PORT, new RoutingElementKey(0,0));
			//For router 2 do this
			case ROUTER_2_PORT:
				this.routingMap.put(END_USER_1_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(END_USER_2_PORT, new RoutingElementKey(0,0));
			//For router 3 do this
			case ROUTER_3_PORT:
				this.routingMap.put(END_USER_1_PORT, new RoutingElementKey(0,0));
				this.routingMap.put(END_USER_2_PORT, new RoutingElementKey(0,0));
		}
	}
	
	public void initialiseConnections() {
		
		this.connectionCount = 2;
		this.connections = new int[connectionCount];
		this.connections[1] = END_USER_1_PORT;
		this.connections[2] = ROUTER_2_PORT;
		
		//Initialise the connections for the router
		switch (this.routerPort) {
		// For router 1 do this
		case ROUTER_1_PORT:
			this.connectionCount = 2;
			this.connections = new int[connectionCount];
			this.connections[1] = END_USER_1_PORT;
			this.connections[2] = ROUTER_2_PORT;
			// For router 2 do this
		case ROUTER_2_PORT:
			this.connectionCount = 2;
			this.connections = new int[connectionCount];
			this.connections[1] = ROUTER_1_PORT;
			this.connections[2] = ROUTER_3_PORT;
			// For router 3 do this
		case ROUTER_3_PORT:
			this.connectionCount = 2;
			this.connections = new int[connectionCount];
			this.connections[1] = END_USER_2_PORT;
			this.connections[2] = ROUTER_2_PORT;
		}
		terminal.println("Connections initialised...");
	}
	
	/*
	 * Prints both the distanceMap and the routingMap
	 */
	public void printMaps() {
		this.printDistanceMap();
		this.printRoutingMap();
	}
	
	public void printDistanceMap() {
		int address;
		int distance;
		
		terminal.println("***Distance Map for Router(" + this.routerPort + ")***\n");
		terminal.println("Address   |    Distance ");
		terminal.println("-----------------------------------------------");
		
		for(Entry<Integer, Integer> entry : this.distanceMap.entrySet()) {
		    
			address = entry.getKey();
		    distance = entry.getValue();
		    
		    terminal.println("" + address + "       |       " + distance);
		}
	}
	
	public void printRoutingMap() {
		RoutingElementKey routingKey;
		int address;
		int hopCount;
		int nextHop;
		
		terminal.println("\n***Routing Map for Router(" + this.routerPort + ")***\n");
		terminal.println("Address   |    HopCount    |   NextHop" );
		terminal.println("-------------------------------------------------------");
		
		for(Entry<Integer, RoutingElementKey> entry : this.routingMap.entrySet()) {
		    
			address = entry.getKey();
		    routingKey = entry.getValue();
		    hopCount = routingKey.hopCount;
		    nextHop = routingKey.nextDest;
		    
		    terminal.println("" + address + "       |       " + hopCount + "              |         " + nextHop);
		}
	}
	
	public void onReceipt(DatagramPacket packet) {
		try {
			
			terminal.println("\nPacket recieved at router (" + this.routerPort + ")...");
			
			//If packet is from controller, process flow update
			if(packet.getPort() == CONTROLLER_PORT) {
				terminal.println("\nPacket came from controller...");
				processControllerUpdate(packet);
				
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
			content.incrementHopCount();
			RoutingElementKey key = routingMap.get(content.getDestination());
			int nextHop = key.nextDest;
			
			//Set dst port of packet to that of the next router
			DatagramPacket updatedPacket = content.toDatagramPacket();
			InetSocketAddress nextAddr = new InetSocketAddress(Node.DEFAULT_DST_NODE, nextHop);
			updatedPacket.setSocketAddress(nextAddr);
			socket.send(updatedPacket);
			
			if(nextHop == Node.END_USER_1_PORT || nextHop == Node.END_USER_2_PORT)
				terminal.println("\nPacket sent to end user(" + nextHop + ")...");
			
			else
			terminal.println("\nPacket sent to next router(" + nextHop + ")...");
			terminal.println("\nWaiting for contact at router(" + this.routerPort + ")...");
		}
		
		//If routing knowledge unknown, contact controller for update
		else {
			this.onHold = true;
			getRoutingPath(packet);
		}	
	}
	/*
	 * Contacts controller to find out routing path for the new packet
	 *  
	 * @param packet - packet received from end user
	 * @returns void
	 */
	public void getRoutingPath(DatagramPacket packet) throws IOException, InterruptedException {
		
		terminal.println("Contacting controller to get flow updat e for new packet...");
		StringContent packetContent = new StringContent(packet);
		
		//Needs to find out how to get from src to dest
		int dst = packetContent.getDestination();
		int src = packetContent.getSource();
		int router = this.routerPort;
		
		UpdateRequestContent request = new UpdateRequestContent(dst,src,router);
		DatagramPacket requestPacket = request.toDatagramPacket();
		InetSocketAddress controllerAddress = new InetSocketAddress(Node.DEFAULT_DST_NODE, Node.CONTROLLER_PORT);
		requestPacket.setSocketAddress(controllerAddress);
		socket.send(requestPacket);
		terminal.println("Update request sent to controller...");
		terminal.println("Waiting for update from controller...");
	}
	
	/*
	 * Processes an update packet received from controller (updates tables etc)
	 * 
	 * @param packet - Update packet from controller
	 * @returns void 
	 */
	public void processControllerUpdate(DatagramPacket packet) throws InterruptedException, IOException{
		
		UpdateResponseContent content = new UpdateResponseContent(packet);
		terminal.println("Processing controller update...");
		
		//Destination is the end goal of the packet
		int dst = content.getDst();
		//For current router to get to this dst the next hop is newNextHop
		int newNextHop = content.getNextHop();
		
		RoutingElementKey key = this.routingMap.get(dst);
		terminal.println("Previous next hop for dst address(" +dst+ ") was : " + key.nextDest);
		key.nextDest = newNextHop;
		terminal.println("New next hop for dst address(" +dst+ ") is : " + newNextHop);
		
		this.routingMap.put(dst, key);
		terminal.println("Controller update processed successfully...");
		terminal.println("Updating maps...\n");
		printRoutingMap();
		
		
		//If router was originally waiting for controller update, continue with flow of original message packet
		if(this.onHold)
		{
			this.onHold = false;
			continueTransmission(this.messagePacket);
		}
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

