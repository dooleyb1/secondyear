package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import tcdIO.Terminal;

public class Server extends Node {
	static final int DEFAULT_PORT = 50000;

	Terminal terminal;
	
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
	public void onReceipt(DatagramPacket packet) {
		try {
			StringContent recievedPacket = new StringContent(packet);
			
			String sequenceNumber = Integer.toString(recievedPacket.getSequnceNumber());
			terminal.println("Packet recieved at server:");
			terminal.println("Sequence number of packet = " + sequenceNumber);
			terminal.println("Contents of packet = " + recievedPacket.toString());
			
			String responseString = ("ACK" + sequenceNumber);
			StringContent response = recievedPacket;
			response.setString(responseString);
			DatagramPacket responsePacket = response.toDatagramPacket();
			responsePacket.setSocketAddress(packet.getSocketAddress());
			
			terminal.println("Sending acknowledgement response to gateway...");
			socket.send(responsePacket);
		}
		catch(Exception e) {e.printStackTrace();}
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
