package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import tcdIO.Terminal;

public class Gateway extends Node {
	
	static final int DEFAULT_GATEWAY_PORT = 40000;
	static final int DEFAULT_CLIENT_PORT = 40789;
	static final int DEFAULT_SERVER_PORT = 50000;
	static final String DEFAULT_SERVER_NODE = "localhost";	
	static final String DEFAULT_CLIENT_NODE = "localhost";
	
	Terminal terminal;
	InetSocketAddress serverAddress;
	InetSocketAddress clientAddress;
	
	/*
	 * 
	 */
	Gateway(Terminal terminal, String serverHost, int serverPort, String clientHost, int clientPort, int gatewayPort) {
		try {
			this.terminal= terminal;
			
			//Creates gateway socket at default gateway port
			socket= new DatagramSocket(gatewayPort);
			
			//Creates both a server & a client address
			serverAddress = new InetSocketAddress(serverHost, serverPort);
			clientAddress = new InetSocketAddress(clientHost, clientPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Gateway");
			(new Gateway(terminal, DEFAULT_SERVER_NODE, DEFAULT_SERVER_PORT, DEFAULT_CLIENT_NODE, DEFAULT_CLIENT_PORT, DEFAULT_GATEWAY_PORT)).start();
			
		} catch(java.lang.Exception e) {e.printStackTrace();}

	}
	
	public synchronized void start() throws Exception {
		terminal.println("Waiting for contact at gateway...");
		this.wait();
	}

	public void onReceipt(DatagramPacket packet) {
		try {
			
			StringContent content = new StringContent(packet);
			terminal.println(content.toString());
			
			//If the packet comes from the client, send to server
			if(packet.getSocketAddress() == clientAddress)
			{
				terminal.println("Packet recieved at Gateway");
				terminal.println("Sending packet to Server...");
				
				packet.setSocketAddress(serverAddress);
				socket.send(packet);
				terminal.println("Packet sent to server");
				this.wait();
			}
			
			//If the packet comes from the server, send ACK to client
			else if(packet.getSocketAddress() == serverAddress)
			{
				terminal.println("ACK recieved at Gateway");
				terminal.println("Sending ACK to Client...");
				
				packet.setSocketAddress(clientAddress);
				socket.send(packet);
				terminal.println("ACK sent to client");
				this.wait();
			}
			
			else
			{
				terminal.println("Unknown source");
			}
			

			
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
