package cs.tcd.ie;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

public class StringContent implements PacketContent {
	String string;
	byte[] destination;
	byte[] source;
	byte[] sequenceNum;
	byte[] flag;
	
	
	public StringContent(DatagramPacket packet) {
		byte[] payload= null;
		byte[] buffer= null;
		
		
		buffer= packet.getData();
		payload= new byte[packet.getLength()-HEADER_LENGTH];
		destination = new byte[DST_ADDRESS_LENGTH];
		source = new byte[SRC_ADDRESS_LENGTH];
		sequenceNum = new byte[SEQ_NUMBER_LENGTH];
		flag = new byte[FLAG_LENGTH];
		
		//Extract payload
		System.arraycopy(buffer, HEADER_LENGTH, payload, 0, packet.getLength()-HEADER_LENGTH);
		//Extract destination address
		System.arraycopy(buffer, 0, destination, 0, DST_ADDRESS_LENGTH);
		//Extract source address
		System.arraycopy(buffer, DST_ADDRESS_LENGTH, source, 0, SRC_ADDRESS_LENGTH);
		//Extract sequence number
		System.arraycopy(buffer, (DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), this.sequenceNum, 0, SEQ_NUMBER_LENGTH);
		//Extract flag
		System.arraycopy(buffer, (DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH+SEQ_NUMBER_LENGTH), flag, 0, FLAG_LENGTH);
		
		string = new String(payload);
	}
	
	public StringContent(String string) {
		this.string = string;
	}
	
	public String toString() {
		return string;
	}
	
	public int getDestination(){
		return ByteBuffer.wrap(destination).getInt();
	}
	
	public int getSource(){
		return ByteBuffer.wrap(source).getInt();
	}
	
	public int getSequnceNumber(){
		return ByteBuffer.wrap(this.sequenceNum).getInt();
	}

	public int getFlag(){
		return ByteBuffer.wrap(this.flag).getInt();
	}
	
	public void changeFlag(){
		if(this.getFlag() == 0)
			//Change flag to 1
			this.flag = ByteBuffer.allocate(4).putInt(1).array();
		
		else
			//Change flag to 0
			this.flag = ByteBuffer.allocate(4).putInt(0).array();
	}
	
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;
		byte[] buffer= null;
		byte[] payload= null;
		byte[] header= null;

		try {
			payload= string.getBytes();
			header= new byte[HEADER_LENGTH];
			buffer= new byte[header.length+payload.length];
			
			//Update
			this.changeFlag();
			
			//Encloses given payload in the buffer with the other relevant info
			System.arraycopy(payload, 0, buffer, HEADER_LENGTH, payload.length);
			System.arraycopy(destination, 0, buffer, 0, destination.length);
			System.arraycopy(source, 0, buffer, source.length, source.length);
			System.arraycopy(sequenceNum, 0, buffer, (destination.length+source.length), SEQ_NUMBER_LENGTH);
			System.arraycopy(flag, 0, buffer, (destination.length+source.length+SEQ_NUMBER_LENGTH), FLAG_LENGTH);
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}
