package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.HashMap;
import tcdIO.Terminal;

public class Server extends Node {
	static final int DEFAULT_PORT = 50000;
	Terminal terminal;
	HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
	
	/*
	 * 
	 */
	Server(Terminal terminal, int port) {
		try {
			this.terminal= terminal;
			socket= new DatagramSocket(port);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	/**
	 * Assume that incoming packets contain a String and print the string.
	 */
	public synchronized void onReceipt(DatagramPacket packet) {
		try {
			StringContent recievedPacket = new StringContent(packet);
			
			String recievedSequenceNumber = Integer.toString(recievedPacket.getSequnceNumber());
			terminal.println("Packet recieved at server:\n");
			terminal.println("Sequence number of packet = " + recievedSequenceNumber);
			Integer sourceAddress = (Integer) recievedPacket.getSource();
			
			//If first communication
			if(recievedPacket.getSequnceNumber() == 0) {
				
				//Set expected sequence num for client address to 0 
				hmap.put(sourceAddress, 0);
			}
			
			//Verify if sequence number is expected sequence number
			if(recievedPacket.getSequnceNumber() == (int) hmap.get(sourceAddress)){

				terminal.println("Correct expected sequence number.\n");
				terminal.println("Contents of packet = '" + recievedPacket.toString() + "'\n");
				
				//Update expected sequence number and add to hmap
				this.updateExpectedSequenceNumber(hmap, sourceAddress);
				
				StringContent response = recievedPacket;
				//Indicate ACK
				response.setResponseFlagPositive();
				response.incrementResponseNumber();
				DatagramPacket responsePacket = response.toDatagramPacket();
				responsePacket.setSocketAddress(packet.getSocketAddress());
				
				terminal.println("Sending acknowledgement response to gateway ('ACK" + response.getResponseNumber() +"')...");
				socket.send(responsePacket);
			}
			
			else{
				terminal.println("Incorrect sequence number.");
				terminal.println("Contents of packet = '" + recievedPacket.toString() + "'\n");
				terminal.println("Expected sequence number '" + hmap.get(sourceAddress) + "'.");
				terminal.println("Recieved sequence number '" + recievedPacket.getSequnceNumber() + "'.");
				
				StringContent response = recievedPacket;;
				//Indicate NAK
				response.setResponseFlagNegative();
				DatagramPacket responsePacket = response.toDatagramPacket();
				responsePacket.setSocketAddress(packet.getSocketAddress());
				
				terminal.println("Sending negative acknowledgement response to gateway ('NAK" + response.getResponseNumber() +"')...");
				socket.send(responsePacket);
			}
			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public Integer getExpectedSequenceNumber(HashMap<Integer, Integer> hmap, Integer address){
		return hmap.get(address);
	}

	public void updateExpectedSequenceNumber(HashMap<Integer, Integer> hmap, Integer address){
		Integer temp = hmap.get(address);
		temp++;
		hmap.put(address, temp);
	}

	
	public synchronized void start() throws Exception {
		terminal.println("Waiting for contact at server...");
		this.wait();
	}
	
	/*
	 * 
	 */
	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Server");
			(new Server(terminal, DEFAULT_PORT)).start();
			terminal.println("Program completed");
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
