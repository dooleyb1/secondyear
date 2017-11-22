package cs.tcd.ie;

import java.net.DatagramPacket;

public interface PacketContent {
	
	public static final byte HEADER_LENGTH = 16;
	public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte HOP_COUNT_LENGTH= 4;
	
	public String toString();
	public DatagramPacket toDatagramPacket();
}
