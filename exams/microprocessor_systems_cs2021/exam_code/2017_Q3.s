
	AREA TopLevelSwi, CODE, READONLY								// Name this block of code.

	EXPORT		SWI_Handler

/* Software Interrupt Handler Subroutine */

/* SWI Instruction = [0000][0000][00000000 00000000 00000000]
											COND   X		    SWI Number (24 bits)

		This subroutine extracts SWI Number and treats it as
		3 x 8 bit values respectively. Subroutine returns
		the sum of the 3 values in R0

 */

SWI_Handler
	STMFD	SP!,{R1-R12,LR}													// Store registers.

	LDR		R0,[LR,#-4]															// Calculate address of SWI instruction and load it into r0.
	BIC		R0,R0,#0xFF000000												// Mask off top 8 bits of instruction to give SWI number.

	AND R1, R0, #0x000000FF												// Extract leftmost 8 bits

	AND R2, R0, #0x0000FF00												// Extract middle 8 bits
	LSR R2, R2, #8																// middleBits >> 8

	AND R3, R0, #0x00FF0000												// Extract rightmost 8 bits
	LSR R3, R3, #16																// rightBits >> 16

	ADD R0, R1, R2																// sum = leftBits + middleBits
	ADD R0, R0, R3																// sum = sum + rightBits

	LDMFD		SP!, {R1-R12,PC}^											// Restore registers and return.

	END
