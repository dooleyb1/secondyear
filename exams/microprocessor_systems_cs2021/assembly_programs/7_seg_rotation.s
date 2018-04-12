	AREA	AsmTemplate, CODE, READONLY
	IMPORT	main

; MIT License
;
; Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
;
;  Permission is hereby granted, free of charge, to any person obtaining a copy
;  of this software and associated documentation files (the "Software"), to deal
;  in the Software without restriction, including without limitation the rights
;  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;  copies of the Software, and to permit persons to whom the Software is
;  furnished to do so, subject to the following conditions:
;
;  The above copyright notice and this permission notice shall be included in
;  all copies or substantial portions of the Software.
;
;  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
;  THE SOFTWARE.
;
;

	EXPORT	start
start

//Labels & Values
IODIR0	EQU	0xE0028008
IOSET0	EQU	0xE0028004
IOCLR0	EQU	0xE002800C
IOPIN0  EQU 0xE0028000
PINSEL0 EQU 0xE002C000

		LDR R0,=PINSEL0									//R0 = P0 Selection P0-PX

		LDR R1,=0x00000000
		STR R1,[R0]											//Select port 0 as GPIO mode

		LDR R0,=IODIR0									//Outputs
		LDR R1,=0X0000FF00							//PinMask to select P.08 as start pin of output
		STR R1, [R0]										//Outputs = PinMask

		LDR R4, =array									//Hex codes array
		LDR R5, =arrayN									//Hex codes array size
		LDR R5, [R5]

reset
		LDR R6, =0											//Counter

while
		CMP R6, R5											//while(counter<=arraySize)
		BGE reset

		LDR R2,=IOCLR0									//R2 = LED ON
		LDR R3, [R4, R6, LSL #2]				//value = valAt(array.startAddress+offset)
		STR R3,[R2]											//Display Value

delay
		LDR R4,=2000000									//Delay for about 1/2s
dloop	SUBS	R8, R8 ,#1
		BNE	dloop

		LDR R2,=IOSET0
		STR R3, [R2]										//Turn off LEDs
		ADD R6, R6, #1									//counter++
		B while


stop	B	stop

	AREA	TestArray, DATA, READWRITE

; Array Size
arrayN	DCD	16

; Array Elements
array	DCD	0x00003F00,0x00000600,0x00005B00,0x00004F00,0x00006600,0x00006D00,0x00007D00,0x00000700,0x00007F00,0x00006F00,0x00007700,0x00007C00,0x00003900,0x00005E00,0x00007900, 0x00007100

	END
