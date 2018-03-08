
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity alu_16bit_tb is
--  Port ( );
end alu_16bit_tb;

architecture Behavioral of alu_16bit_tb is
    -- declare component to test
    component alu_16bit is
        Port ( 
		    A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit alu
		    B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit alu
		    Gsel: in std_logic_vector(3 downto 0);     -- GSel control input of the 16-bit alu 
		    F: out std_logic_vector(15 downto 0);      -- 16-bit data result of the 16-bit alu 
		    V : out std_logic;                         -- Overflow Flag out
		    C : out std_logic;                         -- Carry Flag out
		    N : out std_logic;                         -- Negative Flag out
		    Z : out std_logic
		);
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs
    signal A : std_logic_vector(15 downto 0) := x"0000";
    signal B : std_logic_vector(15 downto 0) := x"0000";
    signal Gsel : std_logic_vector(3 downto 0) := "0000";
        
    --outputs
    signal F : std_logic_vector(15 downto 0) := x"0000";
    signal V : std_logic := '0';
    signal C : std_logic := '0';
    signal N : std_logic := '0';
    signal Z : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: alu_16bit
		Port Map(
		    A => A,
		    B => B,
		    Gsel => Gsel,
		    F => F,
		    V => V,
		    C => C,
		    N => N,
		    Z => Z
		);

simulation_process :process
begin
    --Test all of the Opcodes for an ALU
    
    --**ARITHMETIC UNIT TESTS**--
    
    --Test ALUctrl = 0000 (F=A)= 0x0001 **
    Gsel <= "0000";
    A <= x"0001";
    B <= x"0041";
    wait for 16ns;
    
    --Test Gsel = 0001 (F=A+1) = 0x0007 **
    Gsel <= "0001";
    A <= x"0006";
    B <= x"0027";
    wait for 16ns;
    
   --Test Gsel = 0010 (F=A+B) = 0x0005 **
   Gsel <= "0010";
   A <= x"0002";
   B <= x"0003";
   wait for 16ns;
    
   --Test Gsel = 0011 (F=A+B+1) = 0x000A **
   Gsel <= "0011";
   A <= x"0007";
   B <= x"0002";
   wait for 16ns;
    
   --Test Gsel = 0100 (F=A+B') = 0x0003 **
   Gsel <= "0100";
   A <= x"0001";
   B <= x"FFFD";
   wait for 16ns;
    
   --Test Gsel = 0101 (F=A+B'+1) = 0x0004 **
   Gsel <= "0101";
   A <= x"0001";
   B <= x"FFFD";
   wait for 16ns;
    
   --Test Gsel = 0101 (F=A-1) = 0x0002 **
   Gsel <= "0110";
   A <= x"0003";
   B <= x"0128";
   wait for 16ns;
    
   --Test Gsel = 0111 (F=A) = 0x00F7 **
   Gsel <= "0111";
   A <= x"00F7";
   B <= x"0128";
   wait for 16ns;
    
  --**LOGIC**--
  --Test Gsel = 1000 (F=A^B)= 0x0429 **
  Gsel <= "1000";
  A <= x"0569";
  B <= x"0EB9";
  wait for 16ns;
    
  --Test Gsel = 1010 (F=AorB) = 0xFF7F **
  Gsel <= "1010";
  A <= x"FE63";
  B <= x"013D";
  wait for 16ns;
    
  --Test Gsel = 1100 (F=AxorB) = 0xD736 **
  Gsel <= "1100";
  A <= x"B59D";
  B <= x"62AB";
  wait for 16ns;
    
  --Test Gsel = 1110 (F=A') = 0xFFF4 **
  Gsel <= "1110";
  A <= x"000B";
  B <= x"0002";
  wait for 16ns;
    
  --**FLAG TESTS**--
    
  --Test V Flag
    
  -- 0xFFFF + 0x8000 = 0x7FFF -> V = 1 **
  Gsel <= "0010";
  A <= x"FFFF";
  B <= x"8000";  
  wait for 16ns;
    
  -- 0xF000 + 0x00BC = 0xF0BC -> V = 0 **
  Gsel <= "0010";
  A <= x"F000";
  B <= x"00BC";  
  wait for 16ns; 
    
  --Test C Flag--
    
  -- 0xF000 + 0x8000 = 0x7000 -> C = 1 **
  Gsel <= "0010";
  A <= x"F000";
  B <= x"8000";  
  wait for 16ns;
    
  -- 0x1405 + 0x00BC = 0x14C1 -> C = 0 **
  Gsel <= "0010";
  A <= x"1405";
  B <= x"00BC";  
  wait for 16ns;
  
 -- Test N Flag --

  -- 0xF405 + 0x00BC = 0xF4C1 -> N = 1 **
  Gsel <= "0010";
  A <= x"F405";
  B <= x"00BC";  
  wait for 16ns; 
  
  -- 0x1405 + 0x00BC = 0x14C1 -> N = 0 **
  Gsel <= "0010";
  A <= x"1405";
  B <= x"00BC";  
  wait for 16ns;
  
 -- Test Z Flag --
 
   -- 0x0000 + 0x0000 = 0x0000 -> Z = 1 **
   Gsel <= "0010";
   A <= x"0000";
   B <= x"0000";  
   wait for 16ns; 
   
   -- 0x1405 + 0x00BC = 0x14C1 -> Z = 0 **
   Gsel <= "0010";
   A <= x"1405";
   B <= x"00BC";  
   wait for 16ns;
       
end process;
    
end Behavioral;
