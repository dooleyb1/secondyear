package cs.tcd.ie;

import java.net.DatagramPacket;

public interface PacketContent {
	
	public static final byte HEADER_LENGTH = 28;
	public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte SEQ_NUMBER_LENGTH = 4;
	public static final byte FLAG_LENGTH = 4;
	public static final byte RESPONSE_FLAG_LENGTH = 4;
	public static final byte RESPONSE_NUMBER_LENGTH = 4;
	public static final int SEQ_NUMBER_INDEX = (DST_ADDRESS_LENGTH + SRC_ADDRESS_LENGTH) -1;
	
	
	public String toString();
	public DatagramPacket toDatagramPacket();
}
