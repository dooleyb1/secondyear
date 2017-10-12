package tcd.cs2031;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiverProcess {

	/**
	 * The method opens a socket on a given port and hostname - currently, this is fixed to
	 * port 12345 and the local host. It attempts to receive a packet of a certain size,
	 * retrieves the payload of the packet and attempts to interpret the first object in the 
	 * payload as a string. 
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		final int RECV_PORT = 12345;
		final int MTU = 1500;
		
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;
		
		ObjectInputStream ostream;
		ByteArrayInputStream bstream;
		byte[] buffer;

		try {
			System.out.println("ReceiverProcess - Program start");

			// extract destination from arguments
			address= InetAddress.getLocalHost(); // InetAddress.getByName(args[0]);
			port= RECV_PORT;                     // Integer.parseInt(args[1]);
			
			// create buffer for data, packet and socket
			buffer= new byte[MTU];
			packet= new DatagramPacket(buffer, buffer.length);
			socket= new DatagramSocket(port, address);
	
			// attempt to receive packet
			System.out.println("Trying to receive");
			socket.receive(packet);

			// extract data from packet
			buffer= packet.getData();
			bstream= new ByteArrayInputStream(buffer);
			ostream= new ObjectInputStream(bstream);
			
			// print data and end of program
			System.out.println("Data: " + ostream.readUTF());
			System.out.println("ReceiverProcess - Program end");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}