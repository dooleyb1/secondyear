/**
 * 
 */
package cs.tcd.ie;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

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
	byte[] seqNumber;
	
	//Flag = 0 if coming from client, flag = 1 if coming from server
	byte[] flag; 
	
	Terminal terminal;
	//Now becomes gateway address
	InetSocketAddress gatewayAddress;
	
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
			this.seqNumber = ByteBuffer.allocate(4).putInt(0).array();
			this.flag = ByteBuffer.allocate(4).putInt(0).array();
			
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	
	/**
	 * Assume that incoming packets contain a String and print the string.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public synchronized void onReceipt(DatagramPacket packet) throws IOException, InterruptedException {
		StringContent content= new StringContent(packet);
		terminal.println("Respone recieved from gateway...");
		
		//Process response to see if ACK or NAK
		if(content.string != "NAK"){
			this.carryOn(packet);
		}
		
		else{
			
		}
		this.notify();
	}

	/**
	 * carryOn Method - Allows system to continue communication if correct ACK recieved from Gateway
	 * @throws IOException 
	 * @throws InterruptedException 
	 * 
	 * @throws Exception
	 */
	public synchronized void carryOn(DatagramPacket packet) throws IOException, InterruptedException{
		byte[] newPayload = null;
		byte[] newBuffer = null;
		byte[] newFlag = new byte[PacketContent.FLAG_LENGTH];
		byte[] oldBuffer = null;
		byte[] headerData = new byte[PacketContent.HEADER_LENGTH];
		
		newPayload = (terminal.readString("New string to send: ")).getBytes();
		oldBuffer = packet.getData();
		newFlag = ByteBuffer.allocate(4).putInt(0).array();
		
		//Creates a buffer to contain the information
		newBuffer= new byte[PacketContent.HEADER_LENGTH + newPayload.length];
		//Transfer old header data from old buffer
		System.arraycopy(oldBuffer, 0, headerData, 0, PacketContent.HEADER_LENGTH);
		System.arraycopy(headerData, 0, newBuffer, 0 , PacketContent.HEADER_LENGTH);
		//Transfer in new payload
		System.arraycopy(newPayload, 0, newBuffer, PacketContent.HEADER_LENGTH, newPayload.length);
		
		//Reset flag to 0 
		System.arraycopy(newFlag, 0, newBuffer, (PacketContent.HEADER_LENGTH-PacketContent.FLAG_LENGTH), PacketContent.FLAG_LENGTH);
		
		terminal.println("\nSending new packet to gateway...");
		packet= new DatagramPacket(newBuffer, newBuffer.length, gatewayAddress);
		socket.send(packet);
		terminal.println("Packet sent to gateway\n");
		this.wait();
	}
	/**
	 * Sender Method
	 * 
	 */
	/**
	 * @throws Exception
	 */
	public synchronized void start() throws Exception {
		DatagramPacket packet= null;

		byte[] payload= null;
		byte[] dstAddress = null;
		byte[] srcAddress = null;
		byte[] sequenceNum = null;
		byte[] flag = null;
		byte[] buffer= null;
		
			dstAddress = new byte[PacketContent.DST_ADDRESS_LENGTH];
			srcAddress = new byte[PacketContent.SRC_ADDRESS_LENGTH];
			sequenceNum = this.seqNumber;
			flag = this.flag;
		
			//Reads and sorts the relevant information into byte arrays
			payload = (terminal.readString("String to send: ")).getBytes();
			int dst = terminal.readInt("Destination address: ");
			dstAddress = ByteBuffer.allocate(6).putInt(dst).array();
			System.out.print(ByteBuffer.wrap(dstAddress).getInt());
			srcAddress = ByteBuffer.allocate(6).putInt(DEFAULT_SRC_PORT).array();
			
			//Creates a buffer to contain the information
			buffer= new byte[PacketContent.HEADER_LENGTH + payload.length];
			
			//Encloses the above information into a buffer containing an array of bytes
			System.arraycopy(dstAddress, 0, buffer, 0, dstAddress.length);
			System.arraycopy(srcAddress, 0, buffer, dstAddress.length, srcAddress.length);
			System.arraycopy(sequenceNum, 0, buffer, (dstAddress.length+srcAddress.length), PacketContent.SEQ_NUMBER_LENGTH);
			System.arraycopy(flag, 0, buffer, (dstAddress.length+srcAddress.length+PacketContent.SEQ_NUMBER_LENGTH), PacketContent.FLAG_LENGTH);
			System.arraycopy(payload, 0, buffer, (dstAddress.length+srcAddress.length+PacketContent.SEQ_NUMBER_LENGTH+PacketContent.FLAG_LENGTH), payload.length);
			
			terminal.println("\nSending packet to gateway...");
			packet= new DatagramPacket(buffer, buffer.length, gatewayAddress);
			socket.send(packet);
			terminal.println("Packet sent to gateway\n");
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
			(new Client(terminal, DEFAULT_DST_NODE, DEFAULT_SRC_PORT, DEFAULT_GATEWAY_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
