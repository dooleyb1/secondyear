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

entity alu_16bit_tb is
--  Port ( );
end alu_16bit_tb;

architecture Behavioral of alu_16bit_tb is
    -- declare component to test
    component alu_16bit is
        Port ( A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit ALU
			   B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit ALU
			   ALUctrl: in std_logic_vector(4 downto 0);       -- ALUctrl control input of the 16-bit ALU 
			   F: out std_logic_vector(15 downto 0);      -- 16-bit data output of the 16-bit ALU 
			   V : out std_logic;                         -- Overflow Flag out
			   C : out std_logic);                         -- Carry Flag out
    end component;
    
        -- signals for tests (initialise to 0)
        
        --inputs
        signal A : std_logic_vector(15 downto 0) := x"0000";
        signal B : std_logic_vector(15 downto 0) := x"0000";
        signal ALUctrl : std_logic_vector(4 downto 0) := "00000";
        
        --outputs
        signal F : std_logic_vector(15 downto 0) := x"0000";  
        signal Vflag : std_logic;
        signal Cflag : std_logic; 
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: alu_16bit
    Port map(
        A => A,
        B => B,
        ALUctrl => ALUctrl,
        F => F,
        V => Vflag,
        C => Cflag
    );
    
    simulation_process :process
    begin
    	--Test all of the Opcodes for an ALU
    	
    	--**ARITHMETIC UNIT TESTS**--
    	
        --Test ALUctrl = 00000 (F=A)= 0x0001
        ALUctrl <= "00000";
       	A <= x"0001";
       	B <= x"0041";
        wait for 80ns;
        
        --Test ALUctrl = 00001 (F=A+1) = 0x0007
        ALUctrl <= "00001";
       	A <= x"0006";
       	B <= x"0027";
        wait for 80ns;
        
       --Test ALUctrl = 00010 (F=A+B) = 0x0005 **
      	ALUctrl <= "00010";
       	A <= x"0002";
       	B <= x"0003";
        wait for 80ns;
        
       --Test ALUctrl = 00011 (F=A+B+1) = 0x000A **
       	ALUctrl <= "00011";
       	A <= x"0007";
       	B <= x"0002";
        wait for 80ns;
        
       --Test ALUctrl = 00100 (F=A+B') = 0x0003
       	ALUctrl <= "00100";
       	A <= x"0001";
       	B <= x"FFFD";
        wait for 80ns;
        
        --Test ALUctrl = 00101 (F=A+B'+1) = 0x0004
       	ALUctrl <= "00101";
       	A <= x"0001";
       	B <= x"FFFD";
        wait for 80ns;
        
        --Test ALUctrl = 00101 (F=A-1) = 0x0002
       	ALUctrl <= "00110";
       	A <= x"0003";
       	B <= x"0128";
        wait for 80ns;
        
        --Test ALUctrl = 00111 (F=A) = 0x00F7
       	ALUctrl <= "00111";
       	A <= x"00F7";
       	B <= x"0128";
        wait for 80ns;
        
        --**LOGIC**--
        --Test ALUctrl = 01000 (F=A^B)= 0x0429 **
        ALUctrl <= "01000";
       	A <= x"0569";
       	B <= x"0EB9";
        wait for 80ns;
        
        --Test ALUctrl = 01010 (F=AorB) = 0xFF7F **
        ALUctrl <= "01010";
       	A <= x"FE63";
       	B <= x"013D";
        wait for 80ns;
        
       --Test ALUctrl = 01100 (F=AxorB) = 0xD736 **
      	ALUctrl <= "01100";
       	A <= x"B59D";
       	B <= x"62AB";
        wait for 80ns;
        
       --Test ALUctrl = 01110 (F=A') = 0xFFF4 **
       	ALUctrl <= "01110";
       	A <= x"000B";
       	B <= x"0002";
        wait for 80ns;
        
        --**FLAG TESTS**--
        
        --Test V Flag
        
        -- 0xFFFF + 0x8000 = 0x7FFF -> V = 1 **
        ALUctrl <= "00010";
        A <= x"FFFF";
        B <= x"8000";  
        wait for 80ns;
        
        -- 0xF000 + 0x00BC = 0xF0BC -> V = 0 **
        ALUctrl <= "00010";
        A <= x"F000";
        B <= x"00BC";  
        wait for 80ns; 
        
        --Test C Flag--
        
        -- 0xF000 + 0x8000 = 0x7000 -> C = 1 **
        ALUctrl <= "00010";
        A <= x"F000";
        B <= x"8000";  
        wait for 80ns;
        
        -- 0x1405 + 0x00BC = 0x14C1 -> C = 0 **
        ALUctrl <= "00010";
        A <= x"1405";
        B <= x"00BC";  
        wait for 80ns;
        
     end process;
    
end Behavioral;
