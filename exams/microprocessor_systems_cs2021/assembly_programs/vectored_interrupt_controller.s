// MIT License
//
// Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without reSTRiction, including without limitation the rights
//  to use, copy, modify, merge, publish, diSTRibute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.
//
//


T0	EQU	0xE0004000									//Timer 0 Base Address
T1	EQU	0xE0008000									//Timer 1 Base Address

IR	EQU	0														// Add this to a timer's base address to get actual register address
TCR	EQU	4 													// Timer Command Reset Register offset
MCR	EQU	0x14 												// Timer Mode Reset and Interrupt Offset
MR0	EQU	0x18 												// Match Register (counter) offset

TimerCommandReset			EQU	2					// Reset timer
TimerCommandRun				EQU	1		    	// Run timer
TimerModeResetAndInterrupt	EQU	3		// Reset timer mode and interrupt
TimerResetTimeR0Interrupt	EQU	1 		// Reset timer 0 and interrupt
TimerResetAllInterrupts		EQU	0xFF 	// Reset all timer interrupts

// VIC Stuff -- UM, Table 41
VIC	EQU	0xFFFFF000									// VIC Base Address
IntEnable	EQU	0x10 									// Interrupt Enable
VectAddr	EQU	0x30 									//
VectAddR0	EQU	0x100 								// Vectored Interrupt 0
VectCtrl0	EQU	0x200 								// Vectored Interrupt Control 0

TimeR0ChannelNumber	EQU	4						// UM, Table 63
TimeR0Mask			EQU	1<<TimeR0ChannelNumber	// UM, Table 63
IRQslot_en			EQU	5								// UM, Table 58

//Labels & Values
IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C
IO1PIN	EQU	0xE0028010

	AREA	InitialisationAndMain, CODE, READONLY
	IMPORT	main

// (c) Mike Brady, 2014â€“2016.

	EXPORT	start
start

// initialisation code
	LDR R1,=IO1DIR
	LDR	R2,=0x000f0000								//select P1.19--P1.16
	STR	R2,[R1]												//make them outputs
	LDR	R6,=IO1SET										//R6 = LED OFF
	STR	R2,[R6]												//Turn off all LEDS
	LDR	R7,=IO1CLR										//R7 = ON

	LDR	R5,=0x00100000								//endMask
	LDR	R3,=0x00010000								//start with P1.16.
	STR	R3,[R7]	   										//clear the bit -> turn on the LED

	LDR R1, =ticks										//Used to count to a second
	LDR R0, =0
	STR R0, [R1]

// Steps to setup the VIC

//	1. Link our Interrupt Handler (irqhan) with an interrupt (Vectored Interrupt 0)
//  2. Make Timer 0's interrupts the source of Vectored Interrupt 0
//  3. Enable Timer 0's interrupts to be recognised by the VIC
//  4. Clear any pending interrupts (unnecessary)

// Initialise the VIC
	LDR	R0,=VIC												//Looking at you, VIC!

	LDR	R1,=irqhan										//IRQ Handler
	STR	R1,[R0,#VectAddR0] 						//Associate our interrupt handler with Vectored Interrupt 0

	MOV	R1,#TimeR0ChannelNumber+(1<<IRQslot_en)
	STR	R1,[R0,#VectCtrl0] 						//Make Timer 0 interrupts the source of Vectored Interrupt 0

	MOV	R1,#TimeR0Mask
	STR	R1,[R0,#IntEnable]						//Enable Timer 0 interrupts to be recognised by the VIC

	MOV	R1,#0
	STR	R1,[R0,#VectAddr]   					//Remove any pending interrupt (may not be needed)

// Initialise Timer 0
	LDR	R0,=T0												//Looking at you, Timer 0!

	MOV	R1,#TimerCommandReset
	STR	R1,[R0,#TCR]									//Reset the timer

	MOV	R1,#TimerResetAllInterrupts
	STR	R1,[R0,#IR]										//Reset all interrupts from the timer

	LDR	R1,=(14745600/200)-1	 				//5 ms = 1/200 second
	STR	R1,[R0,#MR0]									//Match Register 0 = 5ms

	MOV	R1,#TimerModeResetAndInterrupt
	STR	R1,[R0,#MCR]									//Timer Mode Reset and Interrupt offset

	MOV	R1,#TimerCommandRun
	STR	R1,[R0,#TCR]									//Run timer()

//Mainline
xloop

	LDR R1, =ticks
	LDR R0, [R1]											//R0 = ticks

	CMP R0, #200											//if(ticks == 200)
	BLT xloop													//
	STR	R3,[R6]												//   set the bit -> turn off the LED
	MOV	R3,R3,lsl #1									//   shift up to next bit. P1.16 -> P1.17 etc.

	CMP	R3,R5													//	if(currentPin == endMask)
	BNE skip													//
	LDR	R3,=0x00010000								// 		reset to P1.16

skip
	STR	R3,[R7]		   									//   clear the bit -> turn on the LED
	LDR R0, =0												//   reset ticks
	STR R0, [R1]											//   store new ticks val
	B	xloop

//THIS IS OUR INTERRUPT HANDLER
	AREA	InterruptStuff, CODE, READONLY

irqhan
	SUB LR, LR, #4										//Adjust the LR to last location
	STMFD SP!,{R0-R1,LR}							//Preserve registers on the stack

	LDR R1,=ticks
	LDR R0, [R1]
	ADD R0, R0, #1										//Ticks++
	STR R0, [R1]


	LDR	R0,=T0
	MOV	R1,#TimerResetTimeR0Interrupt
	STR	R1,[R0,#IR]	   								//Remove MR0 interrupt request from timer

	LDR	R0,=VIC
	MOV	R1,#0													//Stop VIC from making interrupt to CPU
	STR	R1,[R0,#VectAddr]							//Reset VIC

	LDMFD SP!,{R0-R1,PC}^							//Load values off stack, LR loaded into PC
																		//And also restoring the CPSR (what the ^ does)

	AREA	Subroutines, CODE, READONLY

	AREA	Stuff, DATA, READWRITE

ticks DCD 0

	END
