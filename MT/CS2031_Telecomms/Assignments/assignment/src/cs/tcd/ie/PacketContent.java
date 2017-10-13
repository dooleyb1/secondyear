package cs.tcd.ie;

import java.net.DatagramPacket;

public interface PacketContent {
	
	public static byte HEADERLENGTH = 10;
	public static byte SEQUENCE_NUMBER_LENGTH = 3;
	
	public String toString();
	public DatagramPacket toDatagramPacket();
}
