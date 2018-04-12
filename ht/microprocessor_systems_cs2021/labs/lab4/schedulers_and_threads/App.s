
// MIT License
//
// Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
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

	EXPORT	start
start

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

//Thread 0 = LED Pin Rotation
//thread0 initialisation
thread0Start
		LDR	R1,=IO1DIR
		LDR	r2,=0x000f0000							//Select P1.19--P1.16
		STR	r2,[R1]											//Make them outputs
		LDR	R1,=IO1SET
		STR	r2,[R1]											//Set them to turn the LEDs off
		LDR	r2,=IO1CLR

		LDR	R5,=0x00100000							//End when the mask reaches this value

wloop
		LDR	R3,=0x00010000							//Start with P1.16.
floop
		STR	R3,[r2]	   									//Clear the bit -> turn on the LED


		LDR R8,=2000000									//Delay for about 1/2s
dloop0
		SUBS	R8, R8 ,#1
		BNE	dloop0

		STR	R3,[R1]											// set the bit -> turn off the LED
		MOV	R3,R3,LSL #1								// shift up to next bit. P1.16 -> P1.17 etc.
		CMP	R3,R5												// if(newBit>endMask)
		BNE	floop												// resetToFirstBit()
		B	wloop

//Thread 1 = 7 Seg Display Rotation
//thread1 initialisation
thread1Start
		LDR	R1,=IO0DIR
		LDR	R2,=0x0001B780
		STR	R2,[R1]
		LDR	R1,=IO0SET
		STR	R2,[R1]
		LDR	R2,=IO0CLR

		LDR R3, =4
		LDR R4, =table									// lookup table for values
		LDR R5, =0x000FFFF0 						// all leds select

restart
		LDR	R6, =15    									// end index of table
		MUL R7, R6, R3									// offset

while
		CMP R6, #0											// while(count<15)
		BLT restart
		ADD R8, R4, R7									// table + offset
		LDR R8, [R4, R7]								// loadVal()
		STR R5, [R2]										// turn off all led current LED's
		STR R8, [R1]										// turn on bits using table value


		LDR R9,=2000000									//Delay for about 1/2s
dloop
		SUBS	R9, R9 ,#1
		BNE	dloop

		SUB R6, R6, #1									// index --
		SUB R7, R7, #4									// offset --
		B while

stop	B	stop


//THIS IS OUR INTERRUPT HANDLER
	AREA	InterruptStuff, CODE, READONLY


	irqhan
		SUB LR, LR, #4										//Adjust the LR to last location
		STMFD SP!, {R0 - R1}							// preserve R0 and R1 onto syst stack

		LDR R0, =threads
		LDR R1, =threadIndex
		LDR R1, [R1]											// threadIndex
		LSL R1, R1, #2										// offset = threadIndex * 4
		ADD R0, R0, R1										// threadAddress + offset

		LDR R0, [R0]											// R0 now points to memory space of thread stack
		ADD R1, R0, #8										// offset (skips R0 and R1)
		STMEA R1, {R2 - R12, LR}					// store everything from R2-R12 and LR onto thread stack
		LDMFD SP!, {R2 - R3} 							// load the saved registers back (except into R2 and R3 this time)
		STMEA R0, {R2 - R3}								// store these onto the thread stack
																			//current threads registers are now preserved

//GET OLD REGISTERS FROM THE NEXT THREAD
		LDR R0, =threads
		LDR R1, =threadIndex
		LDR R2, =threadNum
		LDR R3, [R1]											// threadIndex
		LDR R2, [R2]											// threadNum
		ADD R3, R3, #1										// threadIndex ++
		CMP R3, R2												// if(threadIndex > threadNum)
		BLT endIterate										//
		LDR R3, =0												//    threadIndex = 0
endIterate
		STR R3, [R1]											// threadIndex.pushToMemory()

//CHANGE POINTER TO THE NEXT STACK
		LSL R3, R3, #2										// offset = threadIndex * 4
		ADD R0, R0, R3										// getNewThreadStackAddress()
		LDR R0, [R0]											// newThreadStackAddress

		LDR R2, =13												// registerCount
		LDR R4, [R0, R2, LSL #2] 					// load the pc from this thread to R4

		LDMFD R0!, {R2 - R3}							// load R2 and R3 off of thread stack
		STMFD SP!, {R2 - R4} 							// preserve R0, R1 and PC on the syst stack
		LDMFD R0!, {R2 - R12} 						// load the saved registers off of the thread stack

//RESET TIMER
		LDR	R0,=T0
		MOV	R1,#TimerResetTimeR0Interrupt
		STR	R1,[R0,#IR]	   								//Remove MR0 interrupt request from timer

		LDR	R0,=VIC
		MOV	R1,#0													//Stop VIC from making interrupt to CPU
		STR	R1,[R0,#VectAddr]							//Reset VIC

 //POP OFF STACK
		LDMFD SP!, {R0 - R1, PC}^ 				// load the rest of the registers and change the program counter

	AREA	Subroutines, CODE, READONLY

	AREA	Stuff, DATA, READWRITE


table DCD 0x00003780,0x00000300,0x00009580,0x00008780,0x0000A300,0x0000A680,0x0000B680,0x00000380,0x0000B780,0x0000A380,0x0000B380,0x0000B600,0x00003480,0x0009700,0x0000B480,0x0000B080


thread0 DCD 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, thread0Start //last element is the pc of the thread
thread1 DCD 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, thread1Start

threadNum DCD 2

threadIndex DCD 0

threads DCD thread0, thread1


	END
