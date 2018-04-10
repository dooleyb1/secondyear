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

entity full_adder_tb is
--  Port ( );
end full_adder_tb;

architecture Behavioral of full_adder_tb is
    -- declare component to test
    component full_adder is
        Port ( 
		    A : in STD_LOGIC;
			B : in STD_LOGIC;
			Cin : in STD_LOGIC;
			S : out STD_LOGIC;
			Cout : out STD_LOGIC
		);
    end component;
    
    -- signals for tests (initialise to 0)
     
    --inputs    
    signal A : std_logic := '0';
    signal B : std_logic := '0';
    signal Cin : std_logic := '0';
    
    --outputs
    signal S : std_logic := '0';  
    signal Cout : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: full_adder
    Port Map(
        A => A,
        B => B,
        Cin => Cin,
        S => S,
        Cout => Cout
    );
    
simulation_process :process
begin
        --Check that 0+0+ Cin(1)... S=1, Cout=0 **
        wait for 1ns;
       	A <= '0';
       	B <= '0';
       	Cin <= '1';
        wait for 1ns;
        
        --Check that 0+1+ Cin(0)... S=1, Cout=0 **
       	A <= '0';
       	B <= '1';
       	Cin <= '0';
        wait for 1ns;
        
       --Check that 0+1+ Cin(1)... S=0, Cout=1 **
       	A <= '0';
       	B <= '1';
       	Cin <= '1';
        wait for 1ns;
        
        --Check that 1+0+ Cin(0)... S=1, Cout=0 **
       	A <= '1';
       	B <= '0';
       	Cin <= '0';
        wait for 1ns;
        
        --Check that 1+0+ Cin(1)... S=0, Cout=1 **
       	A <= '1';
       	B <= '0';
       	Cin <= '1';
        wait for 1ns;
        
        --Check that 1+1+ Cin(0)... S=0, Cout=1 **
       	A <= '1';
       	B <= '1';
       	Cin <= '0';
        wait for 1ns;
        
        --Check that 1+1+ Cin(1)... S=1, Cout=1 **
       	A <= '1';
       	B <= '1';
       	Cin <= '1';
        wait for 1ns;

     end process;
    
end Behavioral;
