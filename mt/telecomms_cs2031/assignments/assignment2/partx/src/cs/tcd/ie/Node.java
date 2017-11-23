package cs.tcd.ie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.CountDownLatch;

public abstract class Node {
	static final int PACKETSIZE = 65536;

	static final String DEFAULT_DST_NODE = "localhost";
	
	static final int END_USER_1_PORT = 40700;
	static final int END_USER_2_PORT = 40701;
	
	public static final int ROUTER_1_PORT = 40789;
	public static final int ROUTER_2_PORT= 40790;
	public static final int ROUTER_3_PORT = 40791;
	
	public static final int ROUTE_ID_1 = 1; //ROUTE_ID_1 is when E1 sends packet to E2
	public static final int ROUTE_ID_2 = 2; //ROUTE_ID_2 is when E2 sends packet to E1
	
	public static final int CONTROLLER_PORT = 50000;

	DatagramSocket socket;
	Listener listener;
	CountDownLatch latch;
	
	Node() {
		latch= new CountDownLatch(1);
		listener= new Listener();
		listener.setDaemon(true);
		listener.start();
	}
	
	
	public abstract void onReceipt(DatagramPacket packet) throws IOException, InterruptedException, Exception;
	
	/**
	 *
	 * Listener thread
	 * 
	 * Listens for incoming packets on a datagram socket and informs registered receivers about incoming packets.
	 */
	class Listener extends Thread {
		
		/*
		 *  Telling the listener that the socket has been initialized 
		 */
		public void go() {
			latch.countDown();
		}
		
		/*
		 * Listen for incoming packets and inform receivers
		 */
		public void run() {
			try {
				latch.await();
				// Endless loop: attempt to receive packet, notify receivers, etc
				while(true) {
					DatagramPacket packet = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
					socket.receive(packet);

					onReceipt(packet);
				}
			} catch (Exception e) {if (!(e instanceof SocketException)) e.printStackTrace();}
		}
	}
}
