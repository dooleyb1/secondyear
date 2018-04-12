	AREA	AsmTemplate, CODE, READONLY
	IMPORT	main

//sample program makes the 4 LEDs P1.16, P1.17, P1.18, P1.19 go on and off in sequence
//(c) Mike Brady, 2011.

EXPORT	start
start

//Memory Locations & Values(Pre-Defined)
IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C


			LDR R1,=IO1DIR								//Outputs location

			LDR R2,=0x000F0000						//Select P1.19--P1.16 using mask
			STR R2,[R1]										//Make them outputs

			LDR R1,=IO1SET								//R1 = LED OFF
			STR R2,[R1]										//Turn off all LEDs

			LDR R2,=IO1CLR								//R2 = LED ON

			LDR R5,=0x00100000						//endMask

wloop	LDR	R3,=0x00010000						//firstPin = P1 (using mask)
floop	STR	R3,[R2]	   								//Turn LED on by storing pin into IO1CLR

			LDR R4,=2000000								//Delay for about 1/2s
dloop	SUBS	R4, R4 ,#1
			BNE	dloop

			STR	R3,[R1]										//Turn off LED by storing pin into R1
			MOV	R3,R3,LSL #1							//Shift up to next bit. P1.16 -> P1.17 etc.

			CMP	R3,R5											//If nextBit > endMask
			BNE	floop											//Reset to P1
			B	wloop												//Else continue
stop	B	stop

	END
