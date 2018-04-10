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
	
	//Initial Packet Protocol [HEADER = [DST][SRC][HOP_COUNT]] | [PAYLOAD]
	public static final byte HEADER_LENGTH = 16;
	public static final byte DST_ADDRESS_LENGTH = 6;
	public static final byte SRC_ADDRESS_LENGTH = 6;
	public static final byte HOP_COUNT_LENGTH= 4;
	
	//Router Update Request Packet Protocol = [DESTINATION] | [SOURCE] | [SRC_ROUTER]
	public static final byte ROUTER_UPDATE_REQUEST_PACKET_LENGTH = 18;
	public static final byte ROUTER_ADDRESS_LENGTH = 6;
	
	//Controller Update Response Packet Protocol = [DESTINATION] | [NODE_ADDR] | [NEXT_HOP_ADDRESS]
	public static final byte ROUTER_UPDATE_RESPONSE_PACKET_LENGTH = 18;	
	public static final byte NEXT_HOP_LENGTH = 6;

	
	public String toString();
	public DatagramPacket toDatagramPacket();
}
