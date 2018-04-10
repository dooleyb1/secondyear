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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity arithmetic_unit is
    Port ( 
       A: in std_logic;
       B: in std_logic;
       Cin: in std_logic;
       Cout: out std_logic;
       S0: in std_logic;
       S1: in std_logic;
       Z: out std_logic
    );
end arithmetic_unit;

architecture Behavioral of arithmetic_unit is

    component full_adder
        Port ( 
            A: in std_logic;
            B: in std_logic;
            Cin: in std_logic;
            Cout: out std_logic;
            S: out std_logic
        );
    end component;
    
    signal newInput : std_logic;

begin
    
    newInput <= (B and S0) or ((not B) and S1);
    
    AC: full_adder 
    	Port map (
    		A => A,
    		B => newInput,
    		Cin => Cin,
    		Cout => Cout,
    		S => Z
    	);
    
end Behavioral;
