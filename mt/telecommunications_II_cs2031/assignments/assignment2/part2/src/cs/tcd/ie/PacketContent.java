package cs.tcd.ie;

import java.net.DatagramPacket;

public interface PacketContent {
	
	//Initial Packet Protocol [HEADER] = [DST][SRC][HOP_COUNT] | [PAYLOAD]
	public static final byte HEADER_LENGTH = 16;
	public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte HOP_COUNT_LENGTH= 4;
	
	//ControllerInformPacket Protocol = [FLAG] | [SOURCE_ADDRESS] | [CONNECTION_COUNT (N)] | [CONNECTION_ADDRESS_1] | ... | [CONNECTION_N] 
	//Flag = 2 for ControllerInformPacket
	//public static final byte CONTROLLER_INFORM_PACKET_LENGTH = 20;
	public static final byte FLAG_LENGTH = 4;
	//public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte CONNECTION_COUNT_LENGTH = 4;
	public static final byte CONNECTION_ADDRESS_LENGTH = 6;
	
	//NodeInform Packet Protocol = [FLAG] | [NODE_ADDRESS] | [DST_ADDRESS] | [NEXT_HOP_ADDRESS] | [HOP_COUNT]
	//Flag = 3 for NodeInform Packet
	public static final byte NODE_INFORM_PACKET_LENGTH = 26;	
	//public static final byte FLAG_LENGTH = 4;
	public static final byte NODE_ADDRESS_LENGTH = 6;
	//public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte NEXT_HOP_LENGTH = 6;
	//public static final byte HOP_COUNT_LENGTH= 4;
	
	public String toString();
	public DatagramPacket toDatagramPacket();
}
