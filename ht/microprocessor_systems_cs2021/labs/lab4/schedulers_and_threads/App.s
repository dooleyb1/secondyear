; Definitions  -- references to 'UM' are to the User Manual.
; Timer Stuff -- UM, Table 173

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

T0	equ	0xE0004000		; Timer 0 Base Address
T1	equ	0xE0008000

IR	equ	0			; Add this to a timer's base address to get actual register address
TCR	equ	4
MCR	equ	0x14
MR0	equ	0x18

TimerCommandReset	equ	2
TimerCommandRun	equ	1
TimerModeResetAndInterrupt	equ	3
TimerResetTimer0Interrupt	equ	1
TimerResetAllInterrupts	equ	0xFF

; VIC Stuff -- UM, Table 41
VIC	equ	0xFFFFF000		; VIC Base Address
IntEnable	equ	0x10
VectAddr	equ	0x30
VectAddr0	equ	0x100
VectCtrl0	equ	0x200

Timer0ChannelNumber	equ	4	; UM, Table 63
Timer0Mask	equ	1<<Timer0ChannelNumber	; UM, Table 63
IRQslot_en	equ	5		; UM, Table 58

IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C
IO1PIN	EQU	0xE0028010
IO0DIR	EQU	0xE0028008
IO0SET	EQU	0xE0028004
IO0CLR	EQU	0xE002800C


	AREA	InitialisationAndMain, CODE, READONLY
	IMPORT	main

	EXPORT	start
start

; Initialise the VIC
		LDR	R0,=VIC									; looking at you, VIC!

		LDR	R1,=irqhan
		STR	R1,[R0,#VectAddr0] 						; associate our interrupt handler with Vectored Interrupt 0

		MOV	R1,#Timer0ChannelNumber+(1<<IRQslot_en)
		STR	R1,[R0,#VectCtrl0] 						; make Timer 0 interrupts the source of Vectored Interrupt 0

		MOV	R1,#Timer0Mask
		STR	R1,[R0,#IntEnable]						; enable Timer 0 interrupts to be recognised by the VIC

		MOV	R1,#0
		STR	R1,[R0,#VectAddr]   					; remove any pending interrupt (may not be needed)

; Initialise Timer 0
		LDR	R0,=T0									; looking at you, Timer 0!

		MOV	R1,#TimerCommandReset
		STR	R1,[R0,#TCR]

		MOV	R1,#TimerResetAllInterrupts
		STR R1,[R0,#IR]

		LDR	R1,=(14745600/200)-1	 				; 5 ms = 1/200 second
		STR	R1,[R0,#MR0]

		MOV	R1,#TimerModeResetAndInterrupt
		STR	R1,[R0,#MCR]

		MOV	R1,#TimerCommandRun
		STR	R1,[R0,#TCR]


;thread0 initialisation
thread0Start
		LDR	R1,=IO1DIR
		LDR	r2,=0x000f0000							; select P1.19--P1.16
		STR	r2,[R1]									; make them outputs
		LDR	R1,=IO1SET
		STR	r2,[R1]									; set them to turn the LEDs off
		LDR	r2,=IO1CLR

		LDR	R5,=0x00100000							; end when the mask reaches this value
	
wloop	LDR	R3,=0x00010000							; start with P1.16.
floop	STR	R3,[r2]	   								; clear the bit -> turn on the LED


;delay for about a half second
delay0											
		LDR	R8,=0x2000000
dloop0	subs	R8,R8,#1
		BNE	dloop0

		STR	R3,[R1]									; set the bit -> turn off the LED
		MOV	R3,R3,LSL #1							; shift up to next bit. P1.16 -> P1.17 etc.
		CMP	R3,R5									; if(newBit>endMask)
		BNE	floop									; resetToFirstBit()
		B	wloop


;thread1 initialisation
thread1Start
		LDR	R1,=IO0DIR
		LDR	R2,=0x0001B780
		STR	R2,[R1]		
		LDR	R1,=IO0SET
		STR	R2,[R1]		
		LDR	R2,=IO0CLR

		LDR R3, =4
		LDR R4, =table								; lookup table for values
		LDR R5, =0x000FFFF0 						; all leds select
restart
		LDR	R6, =15    								; end index of table
		MUL R7, R6, R3								; offset

while
		CMP R6, #0									; while(count<15)
		BLT restart
		ADD R8, R4, R7								; table + offsey
		LDR R8, [R4, R7]							; loadVal()
		STR R5, [R2]								; turn off all led current LED's
		STR R8, [R1]								; turn on bits using table value

;delay for about a half second
		LDR	R9, =2000000
dloop	SUBS	R9, R9, #1
		BNE	dloop
		
		SUB R6, R6, #1								; index --
		SUB R7, R7, #4								; offset --
		B while

stop	B	stop  	



	AREA	InterruptStuff, CODE, READONLY
		
irqhan	SUB	LR,LR,#4								; LR adjustment
		STMFD SP!, {R0 - R1}						; preserve R0 and R1 onto syst stack					
		
		LDR R0, =threads								
		LDR R1, =threadIndex
		LDR R1, [R1]								; threadIndex
		LSL R1, R1, #2								; offset = threadIndex * 4
		ADD R0, R0, R1								; threadAddress + offset
		
		LDR R0, [R0]								; R0 now points to memory space of thread stack
		ADD R1, R0, #8								; offset (skips R0 and R1)
		STMEA R1, {R2 - R12, LR}					; store everything from R2-R12 and LR onto thread stack
		LDMFD SP!, {R2 - R3} 						; load the saved registers back (except into R2 and R3 this time)
		STMEA R0, {R2 - R3}							; store these onto the thread stack
													;current threads registers are now preserved

;get previous registers from next thread
		LDR R0, =threads								
		LDR R1, =threadIndex
		LDR R2, =threadNum
		LDR R3, [R1]								; threadIndex
		LDR R2, [R2]								; threadNum
		ADD R3, R3, #1								; threadIndex ++
		CMP R3, R2									; if(threadIndex > threadNum)
		BLT endIterate								;	
		LDR R3, =0									;    threadIndex = 0
endIterate
		STR R3, [R1]								; threadIndex.pushToMemory()

;changes to the next stack pointer 
		LSL R3, R3, #2								; offset = threadIndex * 4
		ADD R0, R0, R3								; getNewThreadStackAddress()
		LDR R0, [R0]								; newThreadStackAddress
		
		LDR R2, =13									; registerCount
		LDR R4, [R0, R2, LSL #2] 					; load the pc from this thread to R4
		
		LDMFD R0!, {R2 - R3}						; load R2 and R3 off of thread stack
		STMFD SP!, {R2 - R4} 						; preserve R0, R1 and PC on the syst stack
		LDMFD R0!, {R2 - R12} 						; load the saved registers off of the thread stack
	
;this is where we stop the timer from making the interrupt request to the VIC
		LDR	R0,=T0
		MOV	R1,#TimerResetTimer0Interrupt
		STR	R1,[R0,#IR]	   							; remove MR0 interrupt request from timer

;here we stop the VIC from making the interrupt request to the CPU:
		LDR	R0,=VIC
		MOV	R1,#0
		STR	R1,[R0,#VectAddr]						; reset VIC
		
		LDMFD SP!, {R0 - R1, PC}^ 					; load the rest of the registers and change the program counter

	AREA	Subroutines, CODE, READONLY

	AREA	Stuff, DATA, READWRITE
	
	
table DCD 0x00003780,0x00000300,0x00009580,0x00008780,0x0000A300,0x0000A680,0x0000B680,0x00000380,0x0000B780,0x0000A380,0x0000B380,0x0000B600,0x00003480,0x0009700,0x0000B480,0x0000B080	

	
thread0 DCD 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, thread0Start ;last element is the pc of the thread
thread1 DCD 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, thread1Start 

threadNum DCD 2
	
threadIndex DCD 0	
	
threads DCD thread0, thread1

	
	END