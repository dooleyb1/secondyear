package cs.tcd.ie;

import java.net.DatagramPacket;

public interface PacketContent {
	
	public static final byte HEADER_LENGTH = 14;
	public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte SEQ_NUMBER_LENGTH = 1;
	public static final byte FLAG_LENGTH = 1;
	
	
	public String toString();
	public DatagramPacket toDatagramPacket();
}
