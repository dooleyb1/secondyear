/**
 * 
 */
package cs.tcd.ie;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import tcdIO.*;

/**
 *
 * Client class
 * 
 * An instance accepts user input 
 *
 */
public class EndUser extends Node {
	
	//Flag = 0 if coming from client, flag = 1 if coming from router
	byte[] flag; 
	
	Terminal terminal;
	InetSocketAddress nextRouterAddress;
	int sourcePortNumber;
	int connectedRouterPort;
	
	/**
	 * Constructor
	 * 	 
	 * Attempts to create socket at given port and create an InetSocketAddress for the destinations
	 */
	EndUser(Terminal terminal, String host, int srcPort, int routerPort) {
		try {
			this.terminal= terminal;
			this.sourcePortNumber = srcPort;
			this.connectedRouterPort = routerPort;
			
			//Creates socket at srcPort (EndUser)
			socket= new DatagramSocket(sourcePortNumber);
			
			//Set flag =0, coming from EndUser (entering network)
			this.flag = ByteBuffer.allocate(4).putInt(0).array();
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	/**
	 * Assume that incoming packets contain a String and print the string.
	 * @throws Exception 
	 */
	public synchronized void onReceipt(DatagramPacket packet) throws Exception {
		StringContent content= new StringContent(packet);
		terminal.println("Packet received from router...");
		
		//TODO - Write Method here
		//this.printPacketDetails(packet);
		this.notify();
	}
	
	
	/**
	 * Sender Method - Sends packet from EndUser to connectedRouter
	 * 
	 */
	/**
	 * @throws Exception
	 */
	public synchronized void start() throws Exception, SocketTimeoutException {

		DatagramPacket packet = null;

		byte[] dstAddress = null;
		byte[] srcAddress = null;
		byte[] hopCount = null;

		byte[] payload = null;
		byte[] buffer = null;

		dstAddress = new byte[PacketContent.DST_ADDRESS_LENGTH];
		srcAddress = new byte[PacketContent.SRC_ADDRESS_LENGTH];
		hopCount = new byte[PacketContent.HOP_COUNT_LENGTH];

		// Reads and sorts the relevant information into byte arrays
		int dst = terminal.readInt("Destination address of end user you would like the packet to be sent to: ");
		payload = (terminal.readString("String to send: ")).getBytes();
		dstAddress = ByteBuffer.allocate(PacketContent.DST_ADDRESS_LENGTH).putInt(dst).array();
		System.out.print(ByteBuffer.wrap(dstAddress).getInt());
		srcAddress = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(this.sourcePortNumber).array();
		hopCount = ByteBuffer.allocate(PacketContent.HOP_COUNT_LENGTH).putInt(0).array();

		// Creates a buffer to contain the information
		buffer = new byte[PacketContent.HEADER_LENGTH + payload.length];

		// Encloses the above information into a buffer containing an array of bytes
		System.arraycopy(dstAddress, 0, buffer, 0, dstAddress.length);
		System.arraycopy(srcAddress, 0, buffer, dstAddress.length, srcAddress.length);
		System.arraycopy(hopCount, 0, buffer, (dstAddress.length + srcAddress.length), hopCount.length);
		System.arraycopy(payload, 0, buffer, PacketContent.HEADER_LENGTH, payload.length);
		

		terminal.println("\nSending packet to router at : " + this.connectedRouterPort + "...");
		packet = new DatagramPacket(buffer, buffer.length);
		packet.setPort(this.connectedRouterPort);
		socket.send(packet);
		terminal.println("Packet sent to router\n");

		this.wait();
	}
	
	/**
	 * Test method
	 * 
	 * Sends a packet to a given address
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("End-User");		
			int endUserPortNumber = terminal.readInt("End-User port number: ");
			int routerPortNumber = terminal.readInt("Connected router port number: ");
			(new EndUser(terminal, DEFAULT_DST_NODE, endUserPortNumber, routerPortNumber)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
