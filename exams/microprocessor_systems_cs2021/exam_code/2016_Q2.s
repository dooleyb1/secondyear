
#define MODE_USR        0x10   /* Never use this one, as there is no way back! */
#define MODE_FIQ        0x11   /* banked r8-r14 */
#define MODE_IRQ        0x12
#define MODE_SVC        0x13
#define MODE_MON        0x16
#define MODE_ABT        0x17
#define MODE_UND        0x1B
#define MODE_SYS        0x1F   /* Same as user... */

// Enter User Mode and set its Stack Pointer
      MSR   CPSR_c, #MODE_USR
      MOV   SP, R0

// Alternative solution if #MODE_USR is undefined

      MRS   R0, CPSR	       // load CPSR into register
  	  BIC   R0, R0, #0x1F 	 // clear the mode field
  	  ORR   R0, R0, #0x10 	 // set bits for user mode
  	  MSR   CPSR_c, R0

/*
  Note that CPSR_c is used instead of CPSR in the MSR instruction,
  to avoid altering the condition code flags.
*/
