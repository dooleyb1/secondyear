package cs.tcd.ie;

import java.net.DatagramPacket;
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

public class UpdateResponsePacket implements PacketContent {
	
	byte[] dst;
	byte[] src;
	byte[] nextHop;
	
	public UpdateResponsePacket(DatagramPacket packet) {
		byte[] buffer= null;
	
		this.dst = new byte[DST_ADDRESS_LENGTH];
		this.src = new byte[SRC_ADDRESS_LENGTH];
		this.nextHop = new byte[NEXT_HOP_LENGTH];
		buffer= packet.getData();
		
		//Router Update Response Packet Protocol = [DESTINATION] | [SOURCE] | [NEXT_HOP_ADDRESS
		
		//Extract dst address
		System.arraycopy(buffer, 0, this.dst, 0, DST_ADDRESS_LENGTH);
		//Extract src address
		System.arraycopy(buffer, DST_ADDRESS_LENGTH, this.src, 0, SRC_ADDRESS_LENGTH);
		//Extract next hop address
		System.arraycopy(buffer, (DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), this.nextHop, 0, NEXT_HOP_LENGTH);
		
	}

	
	public int getDst(){
		return ByteBuffer.wrap(this.dst).getInt();
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
			
			System.arraycopy(this.dst, 0, buffer, 0, DST_ADDRESS_LENGTH);
			System.arraycopy(this.src, 0, buffer, DST_ADDRESS_LENGTH, SRC_ADDRESS_LENGTH);
			System.arraycopy(this.nextHop, 0, buffer, (DST_ADDRESS_LENGTH+SRC_ADDRESS_LENGTH), this.nextHop.length);
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}
