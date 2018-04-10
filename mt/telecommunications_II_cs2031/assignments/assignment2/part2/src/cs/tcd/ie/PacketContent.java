package cs.tcd.ie;

import java.net.DatagramPacket;

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
