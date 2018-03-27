	AREA	AsmTemplate, CODE, READONLY
	IMPORT	main

; sample program makes the 4 LEDs P1.16, P1.17, P1.18, P1.19 go on and off in sequence
; (c) Mike Brady, 2011.

	EXPORT	start
start

IODIR0	EQU	0xE0028008
IOSET0	EQU	0xE0028004
IOCLR0	EQU	0xE002800C
IOPIN0  EQU 0xE0028000
PINSEL0 EQU 0xE002C000

	LDR R0,=PINSEL0
	LDR R1,=0x00000000
	STR R1,[R0]				;Select port 0 as GPIO mode
	LDR R0,=IODIR0
	LDR R1,=0X0000FF00		;Mask to select P.08 as start pin of output
	STR R1, [R0]			
	
	LDR R4, =array			;array.address
	LDR R5, =arrayN
	LDR R5, [R5]			;arraySize
reset	
	LDR R6, =0				;counter

while
	CMP R6, R5				;while(counter<=arraySize)
	BGE reset
	
	LDR R2,=IOSET0			;Set address
	LDR R3, [R4, R6, LSL #2];value = valAt(array.startAddress+offset)
	STR R3,[R2]				;Make pin = value

delay
	;delay for about a half second
	ldr	R8,=0x2000000
dloop	subs	R8,R8,#1
	bne	dloop
	
	LDR R2,=IOCLR0			;Clear address
	STR R3, [R2]			;Clear pins
	ADD R6, R6, #1			;counter++
	B while
	
	
	;ldr	r1,=IO1DIR
	;ldr	r2,=0x000f0000	;select P1.19--P1.16
	;str	r2,[r1]		;make them outputs
	;ldr	r1,=IO1SET
	;str	r2,[r1]		;set them to turn the LEDs off
	;ldr	r2,=IO1CLR
; r1 points to the SET register
; r2 points to the CLEAR register

	;ldr	r5,=0x00100000	; end when the mask reaches this value
;wloop	ldr	r3,=0x00010000	; start with P1.16.
;floop	str	r3,[r2]	   	; clear the bit -> turn on the LED

;delay for about a half second
	;ldr	r4,=2000000
;dloop	subs	r4,r4,#1
	;bne	dloop

	;str	r3,[r1]		;set the bit -> turn off the LED
	;mov	r3,r3,lsl #1	;shift up to next bit. P1.16 -> P1.17 etc.
	;cmp	r3,r5
	;bne	floop
	;b	wloop
stop	B	stop

	AREA	TestArray, DATA, READWRITE

; Array Size
arrayN	DCD	16

; Array Elements
array	DCD	0x00003F00,0x00000600,0x00005B00,0x00004F00,0x00006600,0x00006D00,0x00007D00,0x00000700,0x00007F00,0x00006F00,0x00007700,0x00007C00,0x00003900,0x00005E00,0x00007900, 0x00007100

	END