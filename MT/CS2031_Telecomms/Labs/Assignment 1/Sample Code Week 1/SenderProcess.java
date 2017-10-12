package tcd.cs2031;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SenderProcess {

	/**
	 * This method converts a string into a byte array, attaches the byte array as
	 * payload to a datagram packet and sends the packet to a given hostname and 
	 * port number; currently, the name is fixed to the local host and to port
	 * number is fixed to 12345.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final int DEST_PORT = 12345;
		
		DatagramPacket packet;
		DatagramSocket socket;
		InetAddress address;
		int port;
		
		ObjectOutputStream ostream;
		ByteArrayOutputStream bstream;
		byte[] buffer;

		try {
			System.out.println("SenderProcess - Program start");
	
			// extract destination from arguments
			address= InetAddress.getLocalHost();   // InetAddress.getByName(args[0]);
			port= DEST_PORT;                       // Integer.parseInt(args[1]);
			
			// convert string "Hello World" to byte array
			bstream= new ByteArrayOutputStream();
			ostream= new ObjectOutputStream(bstream);
			ostream.writeUTF("Hello World");
			ostream.flush();
			buffer= bstream.toByteArray();
			
			// create packet addressed to destination
			packet= new DatagramPacket(buffer, buffer.length, 
					address, port);
		
			// create socket and send packet
			socket= new DatagramSocket();
			socket.send(packet);

			System.out.println("SenderProcess - Program end");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}