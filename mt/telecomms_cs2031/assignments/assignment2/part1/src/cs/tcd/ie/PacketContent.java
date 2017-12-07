package cs.tcd.ie;

import java.net.DatagramPacket;

public interface PacketContent {
	
	//Initial Packet Protocol [HEADER = [DST][SRC][HOP_COUNT] | [PAYLOAD]
	public static final byte HEADER_LENGTH = 16;
	public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte HOP_COUNT_LENGTH= 4;
	
	//Router Update Request Packet Protocol = [DESTINATION] | [SOURCE] | [SRC_ROUTER]
	public static final byte ROUTER_UPDATE_REQUEST_PACKET_LENGTH = 18;
	public static final byte ROUTER_ADDRESS_LENGTH = 6;
	
	//Controller Update Respone Packet Protocol = [DESTINATION] | [SOURCE] | [NEXT_HOP_ADDRESS]
	public static final byte ROUTER_UPDATE_RESPONSE_PACKET_LENGTH = 18;	
	public static final byte NEXT_HOP_LENGTH = 6;

	
	public String toString();
	public DatagramPacket toDatagramPacket();
}
