package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;

import tcdIO.Terminal;

public class Gateway extends Node {
	
	static final int DEFAULT_GATEWAY_PORT = 40000;
	static final int DEFAULT_CLIENT_PORT = 40789;
	static final int DEFAULT_SERVER_PORT = 50000;
	static final String DEFAULT_DST_NODE = "localhost";	
	
	Terminal terminal;
	
	/*
	 * 
	 */
	Gateway(Terminal terminal, String serverHost, int serverPort, String clientHost, int clientPort, int gatewayPort) {
		try {
			this.terminal= terminal;
			
			//Creates gateway socket at default gateway port (40000)
			socket= new DatagramSocket(gatewayPort);
			listener.go();
		}
		catch(java.lang.Exception e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		try {					
			Terminal terminal= new Terminal("Gateway");
			(new Gateway(terminal, DEFAULT_DST_NODE, DEFAULT_SERVER_PORT, DEFAULT_DST_NODE, DEFAULT_CLIENT_PORT, DEFAULT_GATEWAY_PORT)).start();
			
		} catch(java.lang.Exception e) {e.printStackTrace();}

	}
	
	public synchronized void start() throws Exception {
		terminal.println("Waiting for contact at gateway...");
		this.wait();
	}

	public void onReceipt(DatagramPacket packet) {
		try {
			
			StringContent content = new StringContent(packet);
			
			//If the packet comes from the client, send to server
			if(content.getFlag() == 0)
			{
				content.changeFlag();
				terminal.println("\nPacket recieved at Gateway");
				terminal.println("Sending packet to Server...");
				
				packet.setPort(content.getDestination());
				socket.send(packet);
				terminal.println("\nPacket sent to server");
			}
			
			//If the packet comes from the server, send ACK to client
			else if(content.getFlag() == 1)
			{
				content.changeFlag();
				terminal.println("\nResponse recieved at Gateway");
				terminal.println("Sending response to Client...\n");
				
				packet.setPort(content.getSource());
				socket.send(packet);
				terminal.println("Respone sent to client");
			}
			
			else
			{
				terminal.println("Unknown source");
			}
			

			
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
