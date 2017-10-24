package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import tcdIO.Terminal;

public class Server extends Node {
	static final int DEFAULT_PORT = 50000;
	static int expectedSequenceNumber;

	Terminal terminal;
	
	/*
	 * 
	 */
	Server(Terminal terminal, int port) {
		try {
			this.terminal= terminal;
			this.expectedSequenceNumber = 0;
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
			
			String recievedSequenceNumber = Integer.toString(recievedPacket.getSequnceNumber());
			terminal.println("Packet recieved at server:\n");
			terminal.println("Sequence number of packet = " + recievedSequenceNumber);
			
			//Verify if sequence number is expected sequence number
			if(recievedPacket.getSequnceNumber() == this.getExpectedSequenceNumber()){

				terminal.println("Correct expected sequence number.\n");
				terminal.println("Contents of packet = '" + recievedPacket.toString() + "'\n");
				
				this.updateExpectedSequenceNumber();
				String responseString = ("ACK" + this.getExpectedSequenceNumber());
				StringContent response = recievedPacket;
				response.setString(responseString);
				DatagramPacket responsePacket = response.toDatagramPacket();
				responsePacket.setSocketAddress(packet.getSocketAddress());
				
				terminal.println("Sending acknowledgement response to gateway ('" + responseString +"')...");
				socket.send(responsePacket);
			}
			
			else{
				
				terminal.println("Incorrect sequence number.");
				terminal.println("Expected sequence number '" + this.getExpectedSequenceNumber() + "'.");
				terminal.println("Recieved sequence number '" + recievedPacket.getSequnceNumber() + "'.");
				
				String responseString = ("NAK");
				StringContent response = recievedPacket;
				response.setString(responseString);
				DatagramPacket responsePacket = response.toDatagramPacket();
				responsePacket.setSocketAddress(packet.getSocketAddress());
				
				terminal.println("Sending negative acknowledgement response to gateway ('" + responseString +"')...");
				socket.send(responsePacket);
			}
			
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public int getExpectedSequenceNumber(){
		return expectedSequenceNumber;
	}
	
	public void resetExpectedSequenceNumber(){
		expectedSequenceNumber=0;
	}
	public void updateExpectedSequenceNumber(){
		expectedSequenceNumber++;
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
