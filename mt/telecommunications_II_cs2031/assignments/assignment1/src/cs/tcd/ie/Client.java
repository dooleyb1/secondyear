
package cs.tcd.ie;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import tcdIO.*;

/* MIT License
 *
 * Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

/**
 *
 * Client class
 * 
 * An instance accepts user input 
 *
 */
public class Client extends Node {
	static final int DEFAULT_DST_PORT = 50000;
	static final int DEFAULT_GATEWAY_PORT = 40000;
	static final int TIMER_VALUE = 10000;
	static final String DEFAULT_DST_NODE = "localhost";	
	byte[] seqNumber;
	DatagramPacket previousPacket;
	
	//Flag = 0 if coming from client, flag = 1 if coming from server
	byte[] flag; 
	
	Terminal terminal;
	//Now becomes gateway address
	InetSocketAddress gatewayAddress;
	int sourcePortNumber;
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
			this.sourcePortNumber = srcPort;
			//Creates socket at srcPort
			socket= new DatagramSocket(sourcePortNumber);
			//Sets timer mechanism
			socket.setSoTimeout(TIMER_VALUE);
			this.seqNumber = ByteBuffer.allocate(4).putInt(0).array();
			this.flag = ByteBuffer.allocate(4).putInt(0).array();
			previousPacket = null;
			
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
		terminal.println("Respone recieved from gateway...");
		
		//If ACK
		if(content.getResponseFlag()==1){
			this.carryOn(packet);
		}
		//If NAK re-send packet
		else{
			this.resendPacket(packet);
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
	public synchronized void carryOn(DatagramPacket packet) throws IOException, InterruptedException, SocketTimeoutException{
		try {
			byte[] newPayload = null;
			byte[] newBuffer = null;
			byte[] newFlag = new byte[PacketContent.FLAG_LENGTH];
			byte[] oldBuffer = null;
			byte[] headerData = new byte[PacketContent.HEADER_LENGTH];
			
			newPayload = (terminal.readString("New string to send: ")).getBytes();
			oldBuffer = packet.getData();
			//Reset flag
			newFlag = ByteBuffer.allocate(4).putInt(0).array();
			
			//Update sequence number
			int sequenceNumber = ByteBuffer.wrap(this.seqNumber).getInt();
			sequenceNumber++;
			this.seqNumber = ByteBuffer.allocate(4).putInt(sequenceNumber).array();
			
			//Creates a buffer to contain the information
			newBuffer= new byte[PacketContent.HEADER_LENGTH + newPayload.length];
			//Transfer old header data from old buffer
			System.arraycopy(oldBuffer, 0, headerData, 0, PacketContent.HEADER_LENGTH);
			System.arraycopy(headerData, 0, newBuffer, 0 , PacketContent.HEADER_LENGTH);
			//Transfer in new payload
			System.arraycopy(newPayload, 0, newBuffer, PacketContent.HEADER_LENGTH, newPayload.length);
			
			//Tranfer in updated flag
			System.arraycopy(newFlag, 0, newBuffer, 
					(PacketContent.HEADER_LENGTH-PacketContent.FLAG_LENGTH-PacketContent.RESPONSE_FLAG_LENGTH-PacketContent.RESPONSE_NUMBER_LENGTH),
					PacketContent.FLAG_LENGTH);
			
			//Transfer in new sequence number
			System.arraycopy(this.seqNumber, 0, newBuffer, (PacketContent.DST_ADDRESS_LENGTH + PacketContent.SRC_ADDRESS_LENGTH), PacketContent.SEQ_NUMBER_LENGTH);
			
			terminal.println("\nSending new packet to gateway...");
			packet= new DatagramPacket(newBuffer, newBuffer.length, gatewayAddress);
			socket.send(packet);
			terminal.println("New packet sent to gateway\n");
		}
		catch (SocketTimeoutException s) {
			terminal.println("Socket time-out. Re-sending packet...");
	        resendPacket(packet);
	      }
	}
	
	/**
	 * resendPacket Method - Allows system to re-send packet if NAK is received
	 * @throws IOException 
	 * @throws InterruptedException 
	 * 
	 * @throws Exception
	 */
	public synchronized void resendPacket(DatagramPacket packet) throws IOException, InterruptedException, SocketTimeoutException{
		try {
			byte[] newFlag = new byte[PacketContent.FLAG_LENGTH];
			byte[] newResponseFlag = new byte[PacketContent.RESPONSE_FLAG_LENGTH];
			byte[] oldBuffer = null;
			
			terminal.println("Generating packet to re-send...");
			oldBuffer = packet.getData();
			//Reset flag
			newFlag = ByteBuffer.allocate(4).putInt(0).array();
			//Reset response flag
			newFlag = ByteBuffer.allocate(4).putInt(1).array();
			
			//Transfer new flag to header data from old buffer
			System.arraycopy(newFlag, 0, oldBuffer, PacketContent.HEADER_LENGTH-(PacketContent.FLAG_LENGTH + PacketContent.RESPONSE_FLAG_LENGTH+
					PacketContent.RESPONSE_NUMBER_LENGTH), PacketContent.FLAG_LENGTH);
			//Transfer new responeFlag to header data from old buffer
			System.arraycopy(newResponseFlag, 0, oldBuffer, PacketContent.HEADER_LENGTH-(PacketContent.RESPONSE_FLAG_LENGTH+ PacketContent.RESPONSE_NUMBER_LENGTH)
			, PacketContent.RESPONSE_FLAG_LENGTH);
			
			terminal.println("\nRe-sending packet to gateway...");
			packet= new DatagramPacket(oldBuffer, oldBuffer.length, gatewayAddress);
			socket.send(packet);
			terminal.println("Packet re-sent to gateway\n");
		}
		catch (SocketTimeoutException s) {
			terminal.println("Socket time-out. Re-sending packet...");
	        resendPacket(packet);
	      }
	}
	/**
	 * Sender Method
	 * 
	 */
	/**
	 * @throws Exception
	 */
	public synchronized void start() throws Exception, SocketTimeoutException {
		try{
			DatagramPacket packet= null;
	
			byte[] payload= null;
			byte[] dstAddress = null;
			byte[] srcAddress = null;
			byte[] sequenceNum = null;
			byte[] flag = null;
			byte[] responseFlag = null;
			byte[] responseNumber = null;
			byte[] buffer= null;
			
			dstAddress = new byte[PacketContent.DST_ADDRESS_LENGTH];
			srcAddress = new byte[PacketContent.SRC_ADDRESS_LENGTH];
			responseFlag = new byte[PacketContent.RESPONSE_FLAG_LENGTH];
			responseNumber = new byte[PacketContent.RESPONSE_NUMBER_LENGTH];
			sequenceNum = this.seqNumber;
			flag = this.flag;
			
			//Reads and sorts the relevant information into byte arrays
			int dst = terminal.readInt("Destination address: ");
			payload = (terminal.readString("String to send: ")).getBytes();
			dstAddress = ByteBuffer.allocate(PacketContent.DST_ADDRESS_LENGTH).putInt(dst).array();
			//Response flag = 1 for ACK, 0 for NAK
			responseFlag = ByteBuffer.allocate(PacketContent.RESPONSE_FLAG_LENGTH).putInt(1).array();
			//Initialise response number at 0
			responseNumber = ByteBuffer.allocate(PacketContent.RESPONSE_NUMBER_LENGTH).putInt(0).array();
			System.out.print(ByteBuffer.wrap(dstAddress).getInt());
			srcAddress = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(this.sourcePortNumber).array();
				
			//Creates a buffer to contain the information
			buffer= new byte[PacketContent.HEADER_LENGTH + payload.length];
				
			//Encloses the above information into a buffer containing an array of bytes
			System.arraycopy(dstAddress, 0, buffer, 0, dstAddress.length);
			System.arraycopy(srcAddress, 0, buffer, dstAddress.length, srcAddress.length);	
			System.arraycopy(sequenceNum, 0, buffer, (dstAddress.length+srcAddress.length), PacketContent.SEQ_NUMBER_LENGTH);
			System.arraycopy(flag, 0, buffer, (dstAddress.length+srcAddress.length+PacketContent.SEQ_NUMBER_LENGTH), PacketContent.FLAG_LENGTH);
			System.arraycopy(responseFlag, 0, buffer, (PacketContent.HEADER_LENGTH-(PacketContent.RESPONSE_FLAG_LENGTH+PacketContent.RESPONSE_NUMBER_LENGTH)),
						PacketContent.RESPONSE_FLAG_LENGTH);
			System.arraycopy(responseNumber, 0, buffer, PacketContent.HEADER_LENGTH-PacketContent.RESPONSE_NUMBER_LENGTH, PacketContent.RESPONSE_NUMBER_LENGTH);
			System.arraycopy(payload, 0, buffer, PacketContent.HEADER_LENGTH, payload.length);
				
			terminal.println("\nSending packet to gateway...");
			packet= new DatagramPacket(buffer, buffer.length, gatewayAddress);
			this.previousPacket = packet;
			socket.send(packet);
			terminal.println("Packet sent to gateway\n");
				
				
			this.wait();
		}catch (SocketTimeoutException s) {
			terminal.println("Socket time-out. Re-sending packet...");
	        resendPacket(this.previousPacket);
	      }		
	}
	
	/**
	 * Test method
	 * 
	 * Sends a packet to a given address
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Client");		
			int clientPortNumber = terminal.readInt("Client port number: ");
			(new Client(terminal, DEFAULT_DST_NODE, clientPortNumber, DEFAULT_GATEWAY_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
