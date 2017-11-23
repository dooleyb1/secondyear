package cs.tcd.ie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
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
			ControllerContent content = new ControllerContent(packet);		
		}
		catch(Exception e) {e.printStackTrace();}
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

