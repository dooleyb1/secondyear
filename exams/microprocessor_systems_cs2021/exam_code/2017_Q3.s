	AREA TopLevelSwi, CODE, READONLY								; Name this block of code.

	EXPORT		SWI_Handler

SWI_Handler
	STMFD	SP!,{R1-R12,LR}													; Store registers.

	LDR		R0,[LR,#-4]															; Calculate address of SWI instruction and load it into r0.
	BIC		R0,R0,#0xFF000000												; Mask off top 8 bits of instruction to give SWI number.

	AND R1, R0, #0x000000FF												; Extract leftmost 8 bits

	AND R2, R0, #0x0000FF00												; Extract middle 8 bits
	LSR R2, R2, #8																; middleBits >> 8

	AND R3, R0, #0x00FF0000												; Extract rightmost 8 bits
	LSR R3, R3, #16																; rightBits >> 16

	ADD R0, R1, R2																; sum = leftBits + middleBits
	ADD R0, R0, R3																; sum = sum + rightBits

	LDMFD		SP!, {R1-R12,PC}^											; Restore registers and return.

	END
