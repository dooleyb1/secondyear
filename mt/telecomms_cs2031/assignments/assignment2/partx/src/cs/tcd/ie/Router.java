package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;

import tcdIO.Terminal;

public class Router extends Node {
	
	
	static final String DEFAULT_DST_NODE = "localhost";	
	Terminal terminal;
	int routerPort;
	
	
	/*
	 * Hashmap to store routing table within 
	 * - <Integer> = destination address
	 * - <RoutingElementKey> = object that holds hopCount and nextHop for each respective destination address;
	 */
	HashMap<Integer, RoutingElementKey> routingMap = new HashMap<Integer, RoutingElementKey>();
	
	/*
	 * 
	 */
	Router(Terminal terminal, String routerHost, int routerPort) {
		try {
			this.terminal= terminal;
			this.routerPort = routerPort;
			//Creates router socket at defined address
			socket= new DatagramSocket(routerPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	public synchronized void start() throws Exception {
		terminal.println("Waiting for contact at router(" + this.routerPort + ")...");
		this.wait();
	}

	public void onReceipt(DatagramPacket packet) {
		try {
			
			terminal.println("\nPacket recieved at router (" + this.routerPort + ")");
			StringContent content = new StringContent(packet);
			content.incrementHopCount();
			int dest = content.getDestination();
			
			//If router has routing knowledge of how to get to destination, send packet to next router
			if(this.routingMap.containsKey(dest))
			{
				RoutingElementKey key = routingMap.get(dest);
				int nextHop = key.outPort;
				
				//Set dst port of packet to that of the next router
				DatagramPacket updatedPacket = content.toDatagramPacket();
				updatedPacket.setPort(nextHop);
				
				socket.send(updatedPacket);
				terminal.println("\nPacket sent to next router(" + nextHop + ")...");
				
			}
			
			

			
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
