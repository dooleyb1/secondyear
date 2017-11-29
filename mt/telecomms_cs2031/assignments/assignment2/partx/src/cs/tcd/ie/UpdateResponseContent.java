package cs.tcd.ie;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

public class UpdateResponseContent implements PacketContent {
	
	byte[] dst;
	byte[] src;
	byte[] nextHop;
	byte[] flag;
	
	public UpdateResponseContent(DatagramPacket packet) {
		byte[] buffer= null;
	
		this.dst = new byte[DST_ADDRESS_LENGTH];
		this.src = new byte[SRC_ADDRESS_LENGTH];
		this.nextHop = new byte[NEXT_HOP_LENGTH];
		this.flag = new byte[FLAG_LENGTH];
		buffer= packet.getData();
		
		//Controller Update Response Packet Protocol = [FLAG] | [DESTINATION] | [SOURCE] | [NEXT_HOP_ADDRESS]
		
		//Extract flag 
		System.arraycopy(buffer, 0, this.flag, 0, FLAG_LENGTH);
		//Extract dst address
		System.arraycopy(buffer, FLAG_LENGTH, this.dst, 0, DST_ADDRESS_LENGTH);
		//Extract src address
		System.arraycopy(buffer,(DST_ADDRESS_LENGTH+FLAG_LENGTH), this.src, 0, SRC_ADDRESS_LENGTH);
		//Extract next hop address
		System.arraycopy(buffer, (FLAG_LENGTH+DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), this.nextHop, 0, NEXT_HOP_LENGTH);
		
	}

	
	public int getDst(){
		return ByteBuffer.wrap(this.dst).getInt();
	}
	
	public int getFlag(){
		return ByteBuffer.wrap(this.flag).getInt();
	}
	
	public int getSrc(){
		return ByteBuffer.wrap(this.src).getInt();
	}
	
	public int getNextHop(){
		return ByteBuffer.wrap(this.nextHop).getInt();
	}
	
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;
		byte[] buffer= null;

		try {
			buffer= new byte[ROUTER_UPDATE_RESPONSE_PACKET_LENGTH];
			
			System.arraycopy(this.flag, 0, buffer, 0, FLAG_LENGTH);
			System.arraycopy(this.dst, 0, buffer, FLAG_LENGTH, DST_ADDRESS_LENGTH);
			System.arraycopy(this.src, 0, buffer, (FLAG_LENGTH+DST_ADDRESS_LENGTH), SRC_ADDRESS_LENGTH);
			System.arraycopy(this.nextHop, 0, buffer, (FLAG_LENGTH+DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), this.nextHop.length);
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}
