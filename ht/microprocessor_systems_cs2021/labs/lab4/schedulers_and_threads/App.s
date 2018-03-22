; Definitions  -- references to 'UM' are to the User Manual.

; Timer Stuff -- UM, Table 173

T0	equ	0xE0004000								; Timer 0 Base Address
T1	equ	0xE0008000								; Timer 1 Base Address

IR	equ	0										; Add this to a timer's base address to get actual register address
TCR	equ	4 										; Timer Command Reset Register offset 
MCR	equ	0x14 									; Timer Mode Reset and Interrupt Offset
MR0	equ	0x18 									; Match Register (counter) offset

TimerCommandReset			equ	2				; Reset timer 
TimerCommandRun				equ	1		    	; Run timer
TimerModeResetAndInterrupt	equ	3				; Reset timer mode and interrupt
TimerResetTimer0Interrupt	equ	1 				; Reset timer 0 and interrupt
TimerResetAllInterrupts		equ	0xFF 			; Reset all timer interrupts

; VIC Stuff -- UM, Table 41
VIC	equ	0xFFFFF000								; VIC Base Address
IntEnable	equ	0x10 							; Interrupt Enable
VectAddr	equ	0x30 							;
VectAddr0	equ	0x100 							; Vectored Interrupt 0
VectCtrl0	equ	0x200 							; Vectored Interrupt Control 0

Timer0ChannelNumber	equ	4						; UM, Table 63
Timer0Mask			equ	1<<Timer0ChannelNumber	; UM, Table 63
IRQslot_en			equ	5						; UM, Table 58

;LED Pin Rotation Labels
IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C
IO1PIN	EQU	0xE0028010

;7Seg Rotation Labels
IODIR0	EQU	0xE0028008
IOSET0	EQU	0xE0028004
IOCLR0	EQU	0xE002800C
IOPIN0  EQU 0xE0028000
PINSEL0 EQU 0xE002C000

	AREA	InitialisationAndMain, CODE, READONLY
	IMPORT	main

; (c) Mike Brady, 2014â€“2016.

	EXPORT	start
start

; LED Pin Rotation Initialisation
	ldr	r1,=IO1DIR			
	ldr	r2,=0x000f0000							;select P1.19--P1.16
	str	r2,[r1]									;make them outputs
	ldr	r6,=IO1SET								;R6 = Set
	str	r2,[r6]									;set them to turn the LEDs off
	ldr	r7,=IO1CLR								;R7 = Clearr

	ldr	r5,=0x00100000							; end when the mask reaches this value
	ldr	r3,=0x00010000							; start with P1.16.
	str	r3,[r7]	   								; clear the bit -> turn on the LED

; 7Seg Rotation Initialisation
	ldr r0,=PINSEL0
	ldr r1,=0x00000000
	str r1,[R0]									;Select port 0 as GPIO mode
	ldr r0,=IODIR0
	ldr r1,=0X0000FF00							;Mask to select P.08 as start pin of output
	str r1, [R0]			



; Initialise the VIC
	ldr	r0,=VIC									; looking at you, VIC!

	ldr	r1,=irqhan								; IRQ Handler
	str	r1,[r0,#VectAddr0] 						; associate our interrupt handler with Vectored Interrupt 0

	mov	r1,#Timer0ChannelNumber+(1<<IRQslot_en)
	str	r1,[r0,#VectCtrl0] 						; make Timer 0 interrupts the source of Vectored Interrupt 0

	mov	r1,#Timer0Mask
	str	r1,[r0,#IntEnable]						; enable Timer 0 interrupts to be recognised by the VIC

	mov	r1,#0
	str	r1,[r0,#VectAddr]   					; remove any pending interrupt (may not be needed)

	; Initialise Timer 0
	ldr	r0,=T0									; looking at you, Timer 0!

	mov	r1,#TimerCommandReset			
	str	r1,[r0,#TCR]

	mov	r1,#TimerResetAllInterrupts
	str	r1,[r0,#IR]

	ldr	r1,=(14745600/200)-1	 				; 5 ms = 1/200 second
	str	r1,[r0,#MR0]

	mov	r1,#TimerModeResetAndInterrupt
	str	r1,[r0,#MCR]

	mov	r1,#TimerCommandRun
	str	r1,[r0,#TCR]


;aloop = Pin LED Rotation 
aloop	

	ldr r1, =ticks
	ldr r0, [r1]								; r0 = ticks

	cmp r0, #200								; if(ticks == 200)
	blt aloop									; {
	str	r3,[r6]									;   set the bit -> turn off the LED
	mov	r3,r3,lsl #1							;   shift up to next bit. P1.16 -> P1.17 etc.
	
	cmp	r3,r5									;	if(currentPin == endMask)
	bne skip									;	{
	ldr	r3,=0x00010000							; 		reset to P1.16
												;	}
skip
	str	r3,[r7]		   							;   clear the bit -> turn on the LED
	ldr r0, =0									;   reset ticks
	str r0, [r1]								;   store new ticks val
	b	aloop  									; 	}
												; }
												; main program execution will never drop below the statement above.


;bloop = 7 Seg Display Rotation
bloop

	ldr r8, =array								;array.address
	ldr r9, =arrayN
	ldr r9, [r9]								;arraySize

while
	ldr r1, =counter
	ldr r0, [r1]								
	cmp r0, r9									;while(counter<=arraySize)
	bge reset
	
	ldr r2,=IOSET0								;Set address
	ldr r4, [r8, r0, lsl #2]					;value = valAt(array.startAddress+offset)
	str r4,[r2]									;Make pin = value

delay
	;delay for about a half second
	ldr	r1,=0x2000000
dloop	subs	r1,r1,#1
	bne	dloop
	
	ldr r2,=IOCLR0								;Clear address
	str r4, [r2]								;Clear pins
	add r0, r0, #1								;counter++
	str r0, [r1]								; store counter
	b 	bloop

reset	
	ldr r1, =counter
	ldr r0, =0									;counter = 0
	str r0, [r1]
	b 	bloop


	AREA	InterruptStuff, CODE, READONLY
irqhan	sub	lr,lr,#4

	stmfd	sp!,{r0-r1}							; backup r0 and r1 before messing with them

	ldr r0, = threads							;r0 = array of thread stack pointers
	ldr r1, = threadIndex						
	ldr r1, [r1]								;r1 = threadIndex
	lsl r1, r1, #2								;offset
	add r0, r0, r1								;r0 = space to push current registers

	add r1, r0, #8					
	stmfd r1!,{r2-12, lr}						;push r2-r13 and LR to stack
	
	ldmfd sp!,{r2-r3}							;load back r0 and r1 (into r2 and r3)
	stmfd r0, {r2-r3}							;push r0 and r1 onto thread stack

	;current thread registers are now saved onto the appropriate thread stack

		
	;this is where we stop the timer from making the interrupt request to the VIC
	;i.e. we 'acknowledge' the interrupt

	ldr	r0,=T0
	mov	r1,#TimerResetTimer0Interrupt
	str	r1,[r0,#IR]	   	; remove MR0 interrupt request from timer

	;here we stop the VIC from making the interrupt request to the CPU:
	ldr	r0,=VIC
	mov	r1,#0
	str	r1,[r0,#VectAddr]	; reset VIC

;change into system mode

;go to next thread array and go to beginning
	
	ldr r0, =threads
	ldr r1, =threadIndex
	ldr r2, =threadNum

	ldr r1,[r1]					;threadIndex
	ldr r2,[r2]					;amountOfThreads
	add r1, r1, #1				;threadIndex++

	cmp r1, r2 					;if(threadIndex > amountOfThreads)
	blt skipReset				;		threadIndex = 0
	ldr r1, =0					;

skipReset

;changes to next stack pointer
	
	lsl r1, r1, #2				;offset
	add r0, r0, r1 				;r0 is now the pointer to next thread stack
	mov r1, r0

	ldr r2, =13
	ldr r4, [r0, r2, lsl #2]	;load the pc into r4

	ldmfd r1!, {r2-r3}
	stmfd sp!, {r2-r4}			;stores r0-r1 and the pc

;adjust pointer to thread stack r2

	add r0, r0, #8
	ldmfd r0!, {r2-r12}			;load saved registers off the stack
	ldmfd sp!, {r0-r1, pc}		;load the rest of the registers and change pc 		 

	AREA	Subroutines, CODE, READONLY

	AREA	Stuff, DATA, READWRITE

ticks 			DCD 0
process 		DCD 0
counter 		DCD 0

arrayN	DCD	16
array	DCD	0x00003F00,0x00000600,0x00005B00,0x00004F00,0x00006600,0x00006D00,0x00007D00,0x00000700,0x00007F00,0x00006F00,0x00007700,0x00007C00,0x00003900,0x00005E00,0x00007900, 0x00007100

	
thread0 DCD 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, thread0Start ;last element is the pc of the thread
thread1 DCD 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, thread1Start 

threadNum DCD 2
	
threadIndex DCD 0	
	
threads DCD thread0, thread1
