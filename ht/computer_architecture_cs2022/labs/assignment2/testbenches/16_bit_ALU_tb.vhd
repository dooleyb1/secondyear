----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/14/2018 12:22:16 PM
-- Design Name: 
-- Module Name: reg16_tb - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity 16_bit_ALU_tb is
--  Port ( );
end 16_bit_ALU_tb;

architecture Behavioral of 16_bit_ALU_tb is
    -- declare component to test
    component 16_bit_ALU is
        Port ( A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit ALU
			   B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit ALU
			   FS: in std_logic_vector(4 downto 0);  -- ALUctrl control input of the 16-bit ALU 
			   F: out std_logic_vector(15 downto 0); -- 16-bit data output of the 16-bit ALU 
			   V : out std_logic;                         -- Overflow Flag out
			   C : out std_logic;                         -- Carry Flag out
			   N : out std_logic;                         -- Negative Flag out
			   Z : out std_logic);                        -- Zero Flag out);
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal A : std_logic_vector := x'0000';
        signal B : std_logic_vector := x'0000';
        signal FS : std_logic_vector := '00000';
        signal F : std_logic_vector := x'0000';  
        signal V : std_logic := '0';
        signal C : std_logic := '0';
        signal N : std_logic := '0';
        signal Z : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: 16_bit_ALU
    Port map(
        A => A,
        B => B,
        FS => FS,
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
    	
        --Test FS = 00000 (F=A)= 0x0001
        FS <= '00000'
       	A <= x'0001';
       	B <= x'0041';
        wait for 5ns;
        
        --Test FS = 00001 (F=A+1) = 0x0007
        FS <= '00001'
       	A <= x'0006';
       	B <= x'0027';
        wait for 5ns;
        
       --Test FS = 00010 (F=A+B) = 0x0005
      	FS <= '00010'
       	A <= x'0002';
       	B <= x'0003';
        wait for 5ns;
        
       --Test FS = 00011 (F=A+B+1) = 0x000A
       	FS <= '00011'
       	A <= x'0007';
       	B <= x'0002';
        wait for 5ns;
        
       --Test FS = 00100 (F=A+B') = 0x0003
       	FS <= '00100'
       	A <= x'0001';
       	B <= x'FFFD';
        wait for 5ns;
        
        --Test FS = 00101 (F=A+B'+1) = 0x0004
       	FS <= '00101'
       	A <= x'0001';
       	B <= x'FFFD';
        wait for 5ns;
        
        --Test FS = 00101 (F=A-1) = 0x0002
       	FS <= '00110'
       	A <= x'0003';
       	B <= x'0128';
        wait for 5ns;
        
        --Test FS = 00111 (F=A) = 0x00F7
       	FS <= '00110'
       	A <= x'00F7';
       	B <= x'0128';
        wait for 5ns;
        
        --**LOGIC**--
        --Test FS = 01000 (F=A^B)= 0x0429
        FS <= '01000'
       	A <= x'0569';
       	B <= x'0EB9';
        wait for 5ns;
        
        --Test FS = 01010 (F=AorB) = 0xFF7F
        FS <= '01010'
       	A <= x'FE63';
       	B <= x'013D';
        wait for 5ns;
        
       --Test FS = 01100 (F=AxorB) = 0xD736
      	FS <= '01100'
       	A <= x'B59D';
       	B <= x'62AB';
        wait for 5ns;
        
       --Test FS = 01110 (F=A') = 0xFFF4
       	FS <= '00011'
       	A <= x'000B';
       	B <= x'0002';
        wait for 5ns;

     end process;
    
end Behavioral;
