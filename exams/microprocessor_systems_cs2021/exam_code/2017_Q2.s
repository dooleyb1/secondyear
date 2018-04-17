//Button Handler Subroutine

//Inputs :
//Outputs: R0 – Bit Number of switch being pressed if any (-1 if none or more than one).

buttonHandler

	STMFD SP!,{R1-R4,LR}		//Preserve registers on the stack

	LDR R0, =KEYS						//R0 = Interface Address
	LDRB R1, [R0]						//R1 = Interface Bytes (Switches connected to bit 0,1,2,3)

	LDR R2, =0							//count
	LDR R3, =0							//switchOnCount
	LDR R4, =0							//switchOnID

floop
	CMP R2, #3
	BGE exitLoop
	LDR R0, = 0x01					//Switch Mask
	AND R0, R0, R1					//Extract Bit 0 (Switch X)

	CMP R0, #1							//If switch.isOff()
	BEQ switchOn
	ADD R3, R3, #1					//switchOnCount++
	MOV R4, R2              //switchOnID = count

	ADD R2, R2, #1					//count++
	LSR R1, R1, #1					//Interface Bytes >>

switchOn
	ADD R2, R2, #1					//count++
	LSR R1, R1, #1					//Interface Bytes >>

exitLoop
	CMP R3, #1							//if switchOnCount != 1
	BNE negativeReturn
	MOV R0, R4							//R0 = switchOnID
	B end

negativeReturn
	LDR R0, =0xFFFFFFFF			//R0 = -1
end
	LDMFD SP!,{R1-R4,LR}		
