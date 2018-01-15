package cs.tcd.ie;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.CountDownLatch;

public abstract class Node {
	static final int PACKETSIZE = 65536;

	static final String DEFAULT_DST_NODE = "localhost";
	
	static final int NETWORK_NODE_COUNT = 15;
	
	static final int NET_18_PORT = 40700;
	static final int NET_15_PORT = 40701;
	static final int NET_28_PORT = 40702;
	static final int NET_7_PORT = 40703;
	static final int NET_5_PORT = 40704;
	static final int NET_11_PORT = 40705;
	static final int NET_21_PORT = 40706;
	static final int NET_2_PORT = 40707;
	static final int NET_10_PORT = 40708;
	
	public static final int ROUTER_A_PORT = 40789;
	public static final int ROUTER_B_PORT= 40790;
	public static final int ROUTER_C_PORT = 40791;
	public static final int ROUTER_D_PORT = 40792;
	public static final int ROUTER_E_PORT = 40793;
	public static final int ROUTER_F_PORT = 40794;
	
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
