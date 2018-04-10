package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/* MIT License
 *
 * Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

public class NodeInformPacket implements PacketContent {
	
	byte[] flag;
	byte[] nodeAddress;
	byte[] dstAddress;
	byte[] nextHopAddress;
	byte[] hopCount;
	
	
	public NodeInformPacket(DatagramPacket packet) {
		byte[] buffer= null;
	
		this.flag = new byte[FLAG_LENGTH];
		this.nodeAddress = new byte[NODE_ADDRESS_LENGTH];
		this.dstAddress = new byte[DST_ADDRESS_LENGTH];
		this.nextHopAddress = new byte[NEXT_HOP_LENGTH];
		this.hopCount = new byte[HOP_COUNT_LENGTH];
		buffer= packet.getData();
		
		//NodeInform Packet Protocol = [FLAG] | [NODE_ADDRESS] | [DST_ADDRESS] | [NEXT_HOP_ADDRESS] | [HOP_COUNT]
		//Flag = 3 for NodeInform Packet
		
		//Extract flag 
		System.arraycopy(buffer, 0, this.flag, 0, FLAG_LENGTH);
		//Extract node address
		System.arraycopy(buffer, FLAG_LENGTH, this.nodeAddress, 0, NODE_ADDRESS_LENGTH);
		//Extract dst address
		System.arraycopy(buffer, FLAG_LENGTH + NODE_ADDRESS_LENGTH, this.dstAddress, 0, DST_ADDRESS_LENGTH);
		//Extract next hop address
		System.arraycopy(buffer, FLAG_LENGTH + NODE_ADDRESS_LENGTH + DST_ADDRESS_LENGTH, this.nextHopAddress, 0, NEXT_HOP_LENGTH);
		//Extract hop count
		System.arraycopy(buffer, FLAG_LENGTH + NODE_ADDRESS_LENGTH + DST_ADDRESS_LENGTH + NEXT_HOP_LENGTH, this.hopCount, 0, HOP_COUNT_LENGTH);
	}
	
	public NodeInformPacket(int nodeAddr, int dstAddr, int nextHopAddr, int hopCount) {
	
		this.flag = new byte[FLAG_LENGTH];
		this.nodeAddress = new byte[NODE_ADDRESS_LENGTH];
		this.dstAddress = new byte[DST_ADDRESS_LENGTH];
		this.nextHopAddress = new byte[NEXT_HOP_LENGTH];
		this.hopCount = new byte[HOP_COUNT_LENGTH];

		//NodeInform Packet Protocol = [FLAG] | [NODE_ADDRESS] | [DST_ADDRESS] | [NEXT_HOP_ADDRESS] | [HOP_COUNT]
		//Flag = 3 for NodeInform Packet
		this.flag = ByteBuffer.allocate(PacketContent.FLAG_LENGTH).putInt(3).array();
		this.nodeAddress = ByteBuffer.allocate(PacketContent.NODE_ADDRESS_LENGTH).putInt(nodeAddr).array();
		this.dstAddress = ByteBuffer.allocate(PacketContent.DST_ADDRESS_LENGTH).putInt(dstAddr).array();
		this.nextHopAddress = ByteBuffer.allocate(PacketContent.NEXT_HOP_LENGTH).putInt(nextHopAddr).array();
		this.hopCount = ByteBuffer.allocate(PacketContent.HOP_COUNT_LENGTH).putInt(hopCount).array();
	}
	
	
	public int getNodeAddress(){
		return ByteBuffer.wrap(this.nodeAddress).getInt();
	}
	
	public int getFlag() {
		return ByteBuffer.wrap(this.flag).getInt();
	}
	
	public int getDestinationAddress() {
		return ByteBuffer.wrap(this.dstAddress).getInt();
	}
	
	public int getNextHopAddress() {
		return ByteBuffer.wrap(this.nextHopAddress).getInt();
	}
	
	public int getHopCount(){
		return ByteBuffer.wrap(this.hopCount).getInt();
	}
	
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;
		byte[] buffer= null;
		try {
			//NodeInform Packet Protocol = [FLAG] | [NODE_ADDRESS] | [DST_ADDRESS] | [NEXT_HOP_ADDRESS] | [HOP_COUNT]
			//Flag = 3 for NodeInform Packet
			buffer= new byte[NODE_INFORM_PACKET_LENGTH];
			
			System.arraycopy(this.flag, 0, buffer, 0, FLAG_LENGTH);
			System.arraycopy(this.nodeAddress, 0, buffer, FLAG_LENGTH, NODE_ADDRESS_LENGTH);
			System.arraycopy(this.dstAddress, 0, buffer, NODE_ADDRESS_LENGTH+FLAG_LENGTH, DST_ADDRESS_LENGTH);
			System.arraycopy(this.nextHopAddress, 0, buffer, NODE_ADDRESS_LENGTH+FLAG_LENGTH+DST_ADDRESS_LENGTH, NEXT_HOP_LENGTH);
			System.arraycopy(this.hopCount, 0, buffer, NODE_ADDRESS_LENGTH+FLAG_LENGTH+DST_ADDRESS_LENGTH+NEXT_HOP_LENGTH, HOP_COUNT_LENGTH);
			
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}