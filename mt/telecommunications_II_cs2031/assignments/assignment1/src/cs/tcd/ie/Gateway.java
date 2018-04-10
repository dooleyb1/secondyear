package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;

import tcdIO.Terminal;

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

public class Gateway extends Node {
	
	static final int DEFAULT_GATEWAY_PORT = 40000;
	static final int DEFAULT_SERVER_PORT = 50000;
	static final String DEFAULT_DST_NODE = "localhost";	
	
	Terminal terminal;
	
	/*
	 * 
	 */
	Gateway(Terminal terminal, String gatewayHost, int gatewayPort) {
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
			(new Gateway(terminal, DEFAULT_DST_NODE, DEFAULT_GATEWAY_PORT)).start();
			
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
				terminal.println("\n------------------------>");
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
				terminal.println("\n<------------------------");
			}
			
			else
			{
				terminal.println("Unknown source");
			}
			

			
		}
		catch(Exception e) {e.printStackTrace();}
	}

}
