-- MIT License
--
-- Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in
-- all copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
-- THE SOFTWARE.
--

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity function_unit_tb is
--  Port ( );
end function_unit_tb;

architecture Behavioral of function_unit_tb is
    -- declare component to test
    component function_unit is
        Port (
            A : in std_logic_vector(15 downto 0);
            B : in std_logic_vector(15 downto 0);
            FS : in std_logic_vector(4 downto 0);
            V : out std_logic;
            C : out std_logic;
            N : out std_logic;
            Z : out std_logic;    
            F : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal A : std_logic_vector(15 downto 0) := x"0000";
    signal B : std_logic_vector(15 downto 0) := x"0000";
    signal FS : std_logic_vector(4 downto 0) := "00000";
    
    --outputs
    signal F : std_logic_vector(15 downto 0) := x"0000";  
    signal V : std_logic := '0';
    signal C : std_logic := '0';
    signal N : std_logic := '0';
    signal Z : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: function_unit
    Port Map(
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
    	
        --Test FS = 00000 (F=A)= 0x0001 **
        FS <= "00000";
       	A <= x"0001";
       	B <= x"0041";
        wait for 16ns;
        
        --Test FS = 00001 (F=A+1) = 0x0007 **
        FS <= "00001";
       	A <= x"0006";
       	B <= x"0027";
        wait for 16ns;
        
       --Test FS = 00010 (F=A+B) = 0x0005   **
      	FS <= "00010";
       	A <= x"0002";
       	B <= x"0003";
        wait for 16ns;
        
       --Test FS = 00011 (F=A+B+1) = 0x000A    **
       	FS <= "00011";
       	A <= x"0007";
       	B <= x"0002";
        wait for 16ns;
        
       --Test FS = 00100 (F=A+B') = 0x0003    **
       	FS <= "00100";
       	A <= x"0001";
       	B <= x"FFFD";
        wait for 16ns;
        
        --Test FS = 00101 (F=A+B'+1) = 0x0004    **
       	FS <= "00101";
       	A <= x"0001";
       	B <= x"FFFD";
        wait for 16ns;
        
        --Test FS = 00101 (F=A-1) = 0x0002    **
       	FS <= "00110";
       	A <= x"0003";
       	B <= x"0128";
        wait for 16ns;
        
        --Test FS = 00111 (F=A) = 0x00F7    **
       	FS <= "00111";
       	A <= x"00F7";
       	B <= x"0128";
        wait for 16ns;
        
        --**LOGIC**--
        --Test FS = 01000 (F=A^B)= 0x0429    **
        FS <= "01000";
       	A <= x"0569";
       	B <= x"0EB9";
        wait for 16ns;
        
        --Test FS = 01010 (F=AorB) = 0xFF7F    **
        FS <= "01010";
       	A <= x"FE63";
       	B <= x"013D";
        wait for 16ns;
        
       --Test FS = 01100 (F=AxorB) = 0xD736   **
      	FS <= "01100";
       	A <= x"B59D";
       	B <= x"62AB";
        wait for 16ns;
        
       --Test FS = 01110 (F=A') = 0xFFF4    **
       	FS <= "01110";
       	A <= x"000B";
       	B <= x"0002";
        wait for 16ns;
        
    	--**SHIFTER TESTS**--
        
        --Test FS = 10000 (F=B)= 0x0041    **
        FS <= "10000";
        A <= x"0001";
        B <= x"0041";
        wait for 16ns;
        
        --Test FS = 10100 (F= sr B) = 0x0013    **
        FS <= "10100";
        A <= x"0006";
        B <= x"0027";
        wait for 16ns;
        
       --Test FS = 11000 (F= sl B) = 0xC556    **
        FS <= "11000";
        A <= x"0002";
        B <= x"62AB";
        wait for 16ns;        
     wait;
end process;
    
end Behavioral;
