package cs.tcd.ie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import tcdIO.Terminal;

public class Controller extends Node {
	
	Terminal terminal;
	int controllerPort;

	
	/*
	 * Hashmap to store routing table within 
	 * - <Integer> = routeID#
	 * - HashMap<Integer, Integer> = maps sequential flow for each router for unique routeID
	 */
	HashMap<Integer, HashMap<Integer,Integer>> routingMap;
	
	
	/*
	 * 
	 */
	Controller(Terminal terminal, int controllerPort) {
		try {
			this.terminal= terminal;
			this.controllerPort = controllerPort;
			
			this.routingMap = new HashMap<Integer, HashMap<Integer,Integer>>();
			
			//Creates router socket at defined address
			socket= new DatagramSocket(controllerPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	public synchronized void start() throws Exception {
		terminal.println("Controller initialised at (" + this.controllerPort + ")...");
		terminal.println("Hard coding network routing map...\n");
		this.hardCodeRoutingMap();
		
		this.printRoutingMap();
		this.wait();
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

	
	public void onReceipt(DatagramPacket packet) {
		try {
			
			terminal.println("\nPacket recieved at controller (" + this.controllerPort + ")");
			UpdateRequestContent content = new UpdateRequestContent(packet);	
			
			//If the controller recieves a packet, it is from a router seeking a routing flow for a given packet
			int src = content.getSource();
			int dst = content.getDestination();
			
			int routeID = getRouteID(dst, src);
			
			if(routeID != 0) 
				updateAllRouters(routeID);
			
			else 
				throw new Exception();
			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	/*
	 * Updates all routers in the network with the flow for routeID#
	 * 
	 * @param routeID - routeID#
	 * @returns void
	 */
	public void updateAllRouters(int routeID) throws IOException{
		
		int routerAddress;
		int nextHop;
		int src = getSource(routeID);
		int dst = getDestination(routeID);
		
		//Iterates through all routers in network informing them of the new flow
		for (Entry<Integer, Integer> entry : this.routingMap.get(routeID).entrySet()) {

			routerAddress = entry.getKey();
			nextHop = entry.getValue();

			informRouter(routerAddress, nextHop, src, dst);
		}
	}
	
	/*
	 * Informs given router of its nextHop for current flow
	 * 
	 * @param routerAddress - router to inform 
	 * @param nextHop - next hop destination to inform router of
	 */
	public void informRouter(int routerAddress, int nextHop, int src, int dst) throws IOException {
		
		//Create buffers
		byte[] buffer = new byte[PacketContent.ROUTER_UPDATE_RESPONSE_PACKET_LENGTH];
		byte[] srcBuf = new byte[PacketContent.SRC_ADDRESS_LENGTH];
		byte[] dstBuf = new byte[PacketContent.DST_ADDRESS_LENGTH];
		byte[] nextHopBuf = new byte[PacketContent.NEXT_HOP_LENGTH];
		
		//Insert function arguments into buffers
		dstBuf = ByteBuffer.allocate(PacketContent.DST_ADDRESS_LENGTH).putInt(dst).array();
		srcBuf = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(src).array();
		nextHopBuf = ByteBuffer.allocate(PacketContent.NEXT_HOP_LENGTH).putInt(nextHop).array();
		
		// Enclose the above information into a buffer containing an array of bytes
		System.arraycopy(dstBuf, 0, buffer, 0, dstBuf.length);
		System.arraycopy(srcBuf, 0, buffer, dstBuf.length, srcBuf.length);
		System.arraycopy(nextHopBuf, 0, buffer, (dstBuf.length+srcBuf.length), nextHopBuf.length);
		
		//Create update response packet to be sent to router
		InetSocketAddress routerAddr = new InetSocketAddress(Node.DEFAULT_DST_NODE, routerAddress);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, routerAddr);
		socket.send(packet);
		terminal.println("Router(" + routerAddress + ") informed of new flow...\n");
	}
	
	/*
	 *  Gets the routeID following a router update request
	 * 
	 * @param dst - Destination of packet (end user address)
	 * @param src - Source of packet (end user address)
	 * @return returns routeID# if successful, 0 if unsuccessful
	 */
	public int getRouteID(int dst, int src) {
		
		switch(src) {
		//If src = E1 && dst = E2 -> ROUTE ID#1
		case END_USER_1_PORT:
			if(dst == END_USER_2_PORT)
				return ROUTE_ID_1;
			else 
				return 0;
			
		case END_USER_2_PORT:
			if(dst == END_USER_1_PORT) 
				return ROUTE_ID_2;
			else 
				return 0;
		default:
			return 0;
		}
	}
	
	/*
	 *  Gets the destination address for given routeID
	 * 
	 * @param routeID - ID# of Route
	 * @return returns dstAddress if successful, 0 if unsuccessful
	 */
	public int getDestination(int routeID) {
		
		switch(routeID) {
		case ROUTE_ID_1:
			return END_USER_2_PORT;
		case ROUTE_ID_2:
			return END_USER_1_PORT;
		default:
			return 0;
		}
	}
	
	/*
	 *  Gets the source address for given routeID
	 * 
	 * @param routeID - ID# of Route
	 * @return returns srcAddress if successful, 0 if unsuccessful
	 */
	public int getSource(int routeID) {
		
		switch(routeID) {
		case ROUTE_ID_1:
			return END_USER_1_PORT;
		case ROUTE_ID_2:
			return END_USER_2_PORT;
		default:
			return 0;
		}
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

