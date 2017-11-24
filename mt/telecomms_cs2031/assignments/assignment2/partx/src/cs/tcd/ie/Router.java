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
			
			this.routingMap = new HashMap<Integer, RoutingElementKey>();
			this.distanceMap = new HashMap<Integer, Integer>();
			
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
		
		terminal.println("\nWaiting for contact at router(" + this.routerPort + ")...");
		this.wait();
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
			case ROUTER_2_PORT:
				this.distanceMap.put(ROUTER_1_PORT, 0);
				this.distanceMap.put(ROUTER_3_PORT, 0);
			case ROUTER_3_PORT:
				this.distanceMap.put(ROUTER_1_PORT, 0);
				this.distanceMap.put(ROUTER_2_PORT, 0);
		}
	}
	
	/*
	 * Initialises the routing map's of each respective router
	 */
	public void initialiseRoutingMap() {
		
		//hopCount = 0 & nextDest = 0 for all routing map initally
		RoutingElementKey initialR = new RoutingElementKey(0,0);
		
		//Initialise the distanceMap table for the router
		switch(this.routerPort) {
			//For router 1 do this
			case ROUTER_1_PORT:
				this.routingMap.put(ROUTER_2_PORT, initialR);
				this.routingMap.put(ROUTER_3_PORT, initialR);
			//For router 2 do this
			case ROUTER_2_PORT:
				this.routingMap.put(ROUTER_1_PORT, initialR);
				this.routingMap.put(ROUTER_3_PORT, initialR);
			//For router 3 do this
			case ROUTER_3_PORT:
				this.routingMap.put(ROUTER_1_PORT, initialR);
				this.routingMap.put(ROUTER_2_PORT, initialR);
		}
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
			StringContent content = new StringContent(packet);
			
			//If packet is from controller, process flow update
			if(content.getSource() == CONTROLLER_PORT) {
				terminal.println("\nPacket received from Controller...");
				terminal.println("Processing controller update...\n");
				processControllerUpdate(packet);
				continueTransmission(content, packet);
			}
			
			//If packet is not from controller, continue with flow
			else {
				continueTransmission(content, packet);
			}
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	/*
	 * continueTransmission()
	 */
	public void continueTransmission(StringContent content, DatagramPacket packet) throws IOException {
		
		//If router has routing knowledge of how to get to destination, send packet to next router
		if(this.routingMap.containsKey(content.getDestination())){
			content.incrementHopCount();
			RoutingElementKey key = routingMap.get(content.getDestination());
			int nextHop = key.nextDest;
			
			//Set dst port of packet to that of the next router
			DatagramPacket updatedPacket = content.toDatagramPacket();
			updatedPacket.setPort(nextHop);
			
			socket.send(updatedPacket);
			terminal.println("\nPacket sent to next router(" + nextHop + ")...");	
		}
		
		//If routing knowledge unknown, contact controller for update
		else {
			getRoutingPath(packet);
		}	
	}
	/*
	 * Contacts controller to find out routing path for the new packet
	 *  
	 * @param packet - packet received from end user
	 * @returns void
	 */
	public void getRoutingPath(DatagramPacket packet) throws IOException {
		
		terminal.println("Contacting controller to get flow update for new packet...");
		StringContent packetContent = new StringContent(packet);
		int dst = packetContent.getDestination();
		int src = packetContent.getSource();
		int router = this.routerPort;
		
		UpdateRequestContent request = new UpdateRequestContent(dst,src,router);
		DatagramPacket requestPacket = request.toDatagramPacket();
		requestPacket.setPort(CONTROLLER_PORT);
		socket.send(requestPacket);
	}
	
	/*
	 * Processes an update packet received from controller (updates tables etc)
	 * 
	 * @param packet - Update packet from controller
	 * @returns void 
	 */
	public void processControllerUpdate(DatagramPacket packet){
		
		UpdateResponseContent content = new UpdateResponseContent(packet);
		int dst = content.getDst();
		int newNextHop = content.getNextHop();
		
		RoutingElementKey key = this.routingMap.get(dst);
		key.setNextHop(newNextHop);
		
		this.routingMap.put(dst, key);
		terminal.println("Controller update processed successfully...");
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
		    Terminal terminal= new Terminal("Router");
			(new Router(terminal, routerPortNumber)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}

