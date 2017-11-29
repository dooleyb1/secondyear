package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class ControllerInformPacket implements PacketContent {
	
	byte[] src;
	byte[] connectionCount;
	byte[] flag;
	int[] connectionAddresses;
	
	
	public ControllerInformPacket(DatagramPacket packet) {
		byte[] buffer= null;
	
		this.src = new byte[SRC_ADDRESS_LENGTH];
		this.flag = new byte[FLAG_LENGTH];
		this.connectionCount = null;
		buffer= packet.getData();
		
		//ControllerInformPacket Protocol = [FLAG] | [SOURCE_ADDRESS] | [CONNECTION_COUNT (N)] | [CONNECTION_ADDRESS_1] | ... | [CONNECTION_N]
		
		//Extract flag 
		System.arraycopy(buffer, 0, this.flag, 0, FLAG_LENGTH);
		//Extract source address
		System.arraycopy(buffer, FLAG_LENGTH, this.src, 0, SRC_ADDRESS_LENGTH);
		//Extract connection count
		System.arraycopy(buffer, SRC_ADDRESS_LENGTH+FLAG_LENGTH, this.connectionCount, 0, CONNECTION_COUNT_LENGTH);
		this.connectionAddresses = new int[ByteBuffer.wrap(this.connectionCount).getInt()];
		
		//Extract connections
		int connectionAddress;
		byte[] tmp = new byte[CONNECTION_ADDRESS_LENGTH];
		int index = SRC_ADDRESS_LENGTH+CONNECTION_COUNT_LENGTH+FLAG_LENGTH;
		
		for(int i=0; i<ByteBuffer.wrap(this.connectionCount).getInt();i++) {
			 System.arraycopy(buffer, index, tmp, 0, CONNECTION_ADDRESS_LENGTH);
			 index += CONNECTION_ADDRESS_LENGTH;
			 connectionAddress = ByteBuffer.wrap(tmp).getInt();
			 this.connectionAddresses[i] = connectionAddress; 
		}
	}
	
	public ControllerInformPacket(int src, int[] connections) {
	
		this.src = new byte[SRC_ADDRESS_LENGTH];
		this.flag = new byte[FLAG_LENGTH];
		//Flag = 2 for ControllerInformPacket
		this.flag = ByteBuffer.allocate(PacketContent.FLAG_LENGTH).putInt(2).array();
		
		this.connectionCount = new byte[CONNECTION_COUNT_LENGTH];
		this.connectionAddresses = new int[connections.length];
		
		//Router Update Packet Protocol = [DESTINATION][SOURCE][SRC_ROUTER]
		this.src = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(src).array();
		this.connectionAddresses = connections;
	}
	
	
	public int[] getConnectionAddresses(){
		return this.connectionAddresses;
	}
	
	public int getSource(){
		return ByteBuffer.wrap(this.src).getInt();
	}
	
	public int getFlag() {
		return ByteBuffer.wrap(this.flag).getInt();
	}
	
	public int getConnectionCount(){
		return ByteBuffer.wrap(this.connectionCount).getInt();
	}
	
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;
		byte[] buffer= null;

		try {
			buffer= new byte[CONTROLLER_INFORM_PACKET_LENGTH];
			
			System.arraycopy(this.flag, 0, buffer, 0, FLAG_LENGTH);
			System.arraycopy(this.src, 0, buffer, FLAG_LENGTH, SRC_ADDRESS_LENGTH);
			System.arraycopy(connectionCount, 0, buffer, SRC_ADDRESS_LENGTH+FLAG_LENGTH, CONNECTION_COUNT_LENGTH);
			
			//Insert connections into buffer
			int connectionAddress;
			byte[] tmp = new byte[CONNECTION_ADDRESS_LENGTH];
			int index = SRC_ADDRESS_LENGTH+CONNECTION_COUNT_LENGTH+FLAG_LENGTH;
			
			for(int i=0; i<ByteBuffer.wrap(this.connectionCount).getInt();i++) {
				 connectionAddress = this.connectionAddresses[i];
				 tmp = ByteBuffer.allocate(PacketContent.CONNECTION_ADDRESS_LENGTH).putInt(connectionAddress).array();
				 System.arraycopy(tmp, 0, buffer, index, CONNECTION_ADDRESS_LENGTH);
				 index += CONNECTION_ADDRESS_LENGTH; 
			}
		
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}