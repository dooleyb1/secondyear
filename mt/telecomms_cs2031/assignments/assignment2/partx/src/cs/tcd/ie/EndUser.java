/**
 * 
 */
package cs.tcd.ie;

import java.net.DatagramSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	boolean isResponse;
	int responseAddr;
	Terminal terminal;
	InetSocketAddress routerAddress;
	InetSocketAddress controllerAddress;
	int sourcePortNumber;
	int connectedRouterPort;
	int connectionCount;
	int[] connections;
	ControllerInformPacket controllerPacket;
	
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
			this.routerAddress = new InetSocketAddress(host, routerPort);
			this.controllerAddress = new InetSocketAddress(host, Node.CONTROLLER_PORT);
			
			this.isResponse = false;
			this.responseAddr = 0;
			
			this.connectionCount = 1;
			this.connections = new int[connectionCount];
			this.connections[0] = this.connectedRouterPort;
			this.controllerPacket = null
					;
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
		terminal.println("\nPacket received from router...");
		
		terminal.println("Packet contents = " + content.string);
		
		String choice = terminal.readString("\nWould you like to send a response? [y/n] ");
		
		if(choice.equalsIgnoreCase("y")) {
			this.isResponse = true;
			this.responseAddr = content.getSource();
			sendMessage();
		}
		
		else {
			terminal.println("Goodbye.");
			this.notify();
		}
		
	
	}
	
	
	/**
	 * Sender Method - Sends packet from EndUser to connectedRouter
	 * 
	 */
	/**
	 * @throws Exception
	 */
	public synchronized void start() throws Exception, SocketTimeoutException {

		//Inform controller of direct connections
		terminal.println("Informing controller of connections...");
		informController();
		
		String choice = terminal.readString("Enter 's' to send a message or 'r' to receive a message: ");
	
		if(choice.equalsIgnoreCase("s"))
			sendMessage();
		
		else
			terminal.println("Waiting for contact at End User (" + this.sourcePortNumber + ")...");
			this.wait();
	}
	
	public synchronized void informController() throws IOException {
		
		this.controllerPacket = new ControllerInformPacket(this.sourcePortNumber, this.connections);
		DatagramPacket packet = controllerPacket.toDatagramPacket();
		packet.setSocketAddress(controllerAddress);
		socket.send(packet);
		
	}
	
	public synchronized void sendMessage() throws IOException {
		
		DatagramPacket packet = null;
		
		byte[] dstAddress = null;
		byte[] srcAddress = null;
		byte[] hopCount = null;
		byte[] payload = null;
		byte[] buffer = null;
		int dst;

		dstAddress = new byte[PacketContent.DST_ADDRESS_LENGTH];
		srcAddress = new byte[PacketContent.SRC_ADDRESS_LENGTH];
		hopCount = new byte[PacketContent.HOP_COUNT_LENGTH];
		
		if(!isResponse) {
		    String dstStr = terminal.readString("Destination address of end user: ");
		    dst = Integer.parseInt ( dstStr );
		}
		
		else
			dst = this.responseAddr;
		
	    dstAddress = ByteBuffer.allocate(PacketContent.DST_ADDRESS_LENGTH).putInt(dst).array();
	    
		payload = (terminal.readString("String to send: ")).getBytes();
		System.out.print(ByteBuffer.wrap(dstAddress).getInt());
		srcAddress = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(this.sourcePortNumber).array();
		hopCount = ByteBuffer.allocate(PacketContent.HOP_COUNT_LENGTH).putInt(0).array();

		// Creates a buffer to contain the information
		buffer = new byte[PacketContent.HEADER_LENGTH + payload.length];

		// Encloses the above information into a buffer containing an array of bytes
		System.arraycopy(dstAddress, 0, buffer, 0, PacketContent.DST_ADDRESS_LENGTH);
		System.arraycopy(srcAddress, 0, buffer, PacketContent.DST_ADDRESS_LENGTH, PacketContent.SRC_ADDRESS_LENGTH);
		System.arraycopy(hopCount, 0, buffer, (PacketContent.DST_ADDRESS_LENGTH + PacketContent.SRC_ADDRESS_LENGTH), PacketContent.HOP_COUNT_LENGTH);
		System.arraycopy(payload, 0, buffer, PacketContent.HEADER_LENGTH, payload.length);
		

		terminal.println("\nSending packet to router at : " + this.connectedRouterPort + "...");
		packet = new DatagramPacket(buffer, buffer.length, this.routerAddress);
		socket.send(packet);
		terminal.println("Packet sent to router\n");
	}
	
	/**
	 * Test method
	 * 
	 * Sends a packet to a given address
	 */
	public static void main(String[] args) {
		try {	
			//Establish end user details
			System.out.print ( "Enter the port for end user to be established on: " );
		    BufferedReader input = new BufferedReader ( new InputStreamReader ( System.in ));
		    String inputString = input.readLine();
		    int endUserPortNumber = Integer.parseInt ( inputString );
		    
		    //Set connected port for end user
		    int routerPortNumber=0;
		    
		    if(endUserPortNumber == END_USER_1_PORT)
		    	routerPortNumber = ROUTER_1_PORT;
		    else
		    	routerPortNumber = ROUTER_8_PORT;
		    	
		    Terminal terminal = new Terminal("End User (" + endUserPortNumber + ")");
			(new EndUser(terminal, DEFAULT_DST_NODE, endUserPortNumber, routerPortNumber)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
