package cs.tcd.ie;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

public class StringContent implements PacketContent {
	String string;
	byte[] destination;
	byte[] source;
	byte[] sequenceNum;
	byte[] flag;
	byte[] responseFlag;
	byte[] responseNum;
	
	
	public StringContent(DatagramPacket packet) {
		byte[] payload= null;
		byte[] buffer= null;
		
		
		buffer= packet.getData();
		payload= new byte[packet.getLength()-HEADER_LENGTH];
		destination = new byte[DST_ADDRESS_LENGTH];
		source = new byte[SRC_ADDRESS_LENGTH];
		sequenceNum = new byte[SEQ_NUMBER_LENGTH];
		flag = new byte[FLAG_LENGTH];
		responseFlag = new byte[RESPONSE_FLAG_LENGTH];
		responseNum = new byte[RESPONSE_NUMBER_LENGTH];
		
		//Extract payload
		System.arraycopy(buffer, HEADER_LENGTH, payload, 0, packet.getLength()-HEADER_LENGTH);
		//Extract destination address
		System.arraycopy(buffer, 0, destination, 0, DST_ADDRESS_LENGTH);
		//Extract source address
		System.arraycopy(buffer, DST_ADDRESS_LENGTH, source, 0, SRC_ADDRESS_LENGTH);
		//Extract sequence number
		System.arraycopy(buffer, (DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), sequenceNum, 0, SEQ_NUMBER_LENGTH);
		//Extract flag
		System.arraycopy(buffer, (DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH+SEQ_NUMBER_LENGTH), flag, 0, FLAG_LENGTH);
		//Extract response flag
		System.arraycopy(buffer, (HEADER_LENGTH-RESPONSE_FLAG_LENGTH-RESPONSE_NUMBER_LENGTH), responseFlag, 0, RESPONSE_FLAG_LENGTH);
		//Extract response number
		System.arraycopy(buffer, (HEADER_LENGTH-RESPONSE_NUMBER_LENGTH), responseNum, 0, RESPONSE_NUMBER_LENGTH);
		
		string = new String(payload);
	}
	
	public StringContent(String string) {
		this.string = string;
	}
	
	public String toString() {
		return this.string;
	}
	
	public int getDestination(){
		return ByteBuffer.wrap(this.destination).getInt();
	}
	
	public int getSource(){
		return ByteBuffer.wrap(this.source).getInt();
	}
	
	public int getSequnceNumber(){
		return ByteBuffer.wrap(this.sequenceNum).getInt();
	}
	
	public int getResponseNumber(){
		return ByteBuffer.wrap(this.responseNum).getInt();
	}

	public int getFlag(){
		return ByteBuffer.wrap(this.flag).getInt();
	}
	
	public int getResponseFlag(){
		return ByteBuffer.wrap(this.responseFlag).getInt();
	}
	
	public void setResponseFlagPositive(){
			this.responseFlag = ByteBuffer.allocate(4).putInt(1).array();
	}
	
	public void setResponseFlagNegative(){
	
			this.responseFlag = ByteBuffer.allocate(4).putInt(0).array();
	}
	
	public void incrementResponseNumber(){
			int x = this.getResponseNumber();
			x++;
			this.responseNum = ByteBuffer.allocate(4).putInt(x).array();
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
			System.arraycopy(responseFlag, 0, buffer, (destination.length+source.length+SEQ_NUMBER_LENGTH+FLAG_LENGTH), RESPONSE_FLAG_LENGTH);
			System.arraycopy(responseNum, 0, buffer, (destination.length+source.length+SEQ_NUMBER_LENGTH+FLAG_LENGTH+RESPONSE_FLAG_LENGTH), RESPONSE_NUMBER_LENGTH);
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}
