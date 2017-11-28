package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class UpdateRequestContent implements PacketContent {
	
	byte[] destination;
	byte[] source;
	byte[] router;
	
	
	public UpdateRequestContent(DatagramPacket packet) {
		byte[] buffer= null;
	
		this.destination = new byte[DST_ADDRESS_LENGTH];
		this.source = new byte[SRC_ADDRESS_LENGTH];
		this.router = new byte[ROUTER_ADDRESS_LENGTH];
		buffer= packet.getData();
		
		//Router Update Packet Protocol = [DESTINATION][SOURCE][SRC_ROUTER]
		
		//Extract destination address
		System.arraycopy(buffer, 0, this.destination, 0, DST_ADDRESS_LENGTH);
		//Extract source address
		System.arraycopy(buffer, DST_ADDRESS_LENGTH, this.source, 0, SRC_ADDRESS_LENGTH);
		//Extract hop count
		System.arraycopy(buffer, (DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), this.router, 0, ROUTER_ADDRESS_LENGTH);
	}
	
	public UpdateRequestContent(int dst, int src, int router) {
	
		this.destination = new byte[DST_ADDRESS_LENGTH];
		this.source = new byte[SRC_ADDRESS_LENGTH];
		this.router = new byte[ROUTER_ADDRESS_LENGTH];
		
		//Router Update Packet Protocol = [DESTINATION][SOURCE][SRC_ROUTER]
		this.destination = ByteBuffer.allocate(PacketContent.DST_ADDRESS_LENGTH).putInt(dst).array();
		this.source = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(src).array();
		this.router = ByteBuffer.allocate(PacketContent.ROUTER_ADDRESS_LENGTH).putInt(src).array();
	}
	
	
	public int getDestination(){
		return ByteBuffer.wrap(this.destination).getInt();
	}
	
	public int getSource(){
		return ByteBuffer.wrap(this.source).getInt();
	}
	
	public int getRouter(){
		return ByteBuffer.wrap(this.router).getInt();
	}
	
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;
		byte[] buffer= null;

		try {
			buffer= new byte[ROUTER_UPDATE_REQUEST_PACKET_LENGTH];
			
			System.arraycopy(this.destination, 0, buffer, 0, this.destination.length);
			System.arraycopy(this.source, 0, buffer, this.destination.length, this.source.length);
			System.arraycopy(this.router, 0, buffer, (this.source.length+this.destination.length), this.router.length);
		
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}
