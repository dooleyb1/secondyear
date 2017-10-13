/**
 * 
 */
package cs.tcd.ie;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

import tcdIO.*;

/**
 *
 * Client class
 * 
 * An instance accepts user input 
 *
 */
public class Client extends Node {
	static final int DEFAULT_SRC_PORT = 40789;
	static final int DEFAULT_DST_PORT = 50000;
	static final int DEFAULT_GATEWAY_PORT = 40000;
	static final String DEFAULT_DST_NODE = "localhost";	
	static final String DEFAULT_GATEWAY_NODE = "localhost";
	
	Terminal terminal;
	//Now becomes gateway address
	InetSocketAddress gatewayAddress;
	byte sequenceNumber = 0;
	
	/**
	 * Constructor
	 * 	 
	 * Attempts to create socket at given port and create an InetSocketAddress for the destinations
	 */
	Client(Terminal terminal, String gatewayHost, int srcPort, int gatewayPort) {
		try {
			this.terminal= terminal;
			
			//Creates address for gateway
			gatewayAddress = new InetSocketAddress(gatewayHost, gatewayPort);
			
			//Creates socket at srcPort
			socket= new DatagramSocket(srcPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	/**
	 * Assume that incoming packets contain a String and print the string.
	 */
	public synchronized void onReceipt(DatagramPacket packet) {
		StringContent content= new StringContent(packet);
		this.notify();
		terminal.println(content.toString());
	}

	
	/**
	 * Sender Method
	 * 
	 */
	public synchronized void start() throws Exception {
		DatagramPacket packet= null;

		byte[] payload= null;
		byte[] header= null;
		byte[] buffer= null;
		
			payload= (terminal.readString("String to send: ")).getBytes();

			header= new byte[PacketContent.HEADERLENGTH];

			buffer= new byte[header.length + payload.length];
			System.arraycopy(header, 0, buffer, 0, header.length);
			System.arraycopy(payload, 0, buffer, header.length, payload.length);
			
			terminal.println("Sending packet to gateway...");
			packet= new DatagramPacket(buffer, buffer.length, gatewayAddress);
			socket.send(packet);
			terminal.println("Packet sent to gateway");
			this.wait();
	}


	/**
	 * Test method
	 * 
	 * Sends a packet to a given address
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Client");		
			(new Client(terminal, DEFAULT_GATEWAY_NODE, DEFAULT_SRC_PORT, DEFAULT_GATEWAY_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
