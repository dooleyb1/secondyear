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
-- 16-bit alu 
entity alu_16bit is
 Port (
	   A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit alu
	   B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit alu
	   Gsel: in std_logic_vector(3 downto 0);  	  -- GSel control input of the 16-bit alu 
	   F: out std_logic_vector(15 downto 0);      -- 16-bit data result of the 16-bit alu 
	   V : out std_logic;                         -- Overflow Flag out
	   C : out std_logic;                         -- Carry Flag out
	   N : out std_logic;						  -- Negative Flag out
	   Z : out std_logic						  -- Zero Flag out
   );
end alu_16bit;

architecture Behavioral of alu_16bit is

	component alu
		Port ( 
			Cin : in std_logic;
			A: in std_logic;
			B: in std_logic;
			S0: in std_logic;
			S1: in std_logic;
			S2: in std_logic;
			G: out std_logic;
			Cout : out std_logic
		);
	end component;

	component zero_detect
		Port ( 
			I: in std_logic_vector(15 downto 0);
			O: out std_logic
		); 
	end component;


	--Initialise signals
	signal C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, Cout, Zout : std_logic;
	signal result : std_logic_vector(15 downto 0);

begin

	--Instantiate 16 x 1-bit Slice alu's
    alu00: alu 
        Port map(
            Cin => Gsel(0), 
            A => A(0), 
            B => B(0), 
            S0 => Gsel(1), 
            S1 => Gsel(2),
            S2 => Gsel(3),
            G => result(0),
            Cout => C1
         );

    alu01: alu 
        Port map(
            Cin => C1, 
            A => A(1), 
            B => B(1), 
            S0 => Gsel(1), 
            S1 => Gsel(2),
            S2 => Gsel(3),
            G => result(1),
            Cout => C2
         );
         
    alu02: alu 
         Port map(
             Cin => C2, 
             A => A(2), 
             B => B(2), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(2),
             Cout => C3
         );
         
    alu03: alu
         Port map(
             Cin => C3, 
             A => A(3), 
             B => B(3), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(3),
             Cout => C4
         );

    alu04: alu
         Port map(
             Cin => C4, 
             A => A(4), 
             B => B(4), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(4),
             Cout => C5
         );

    alu05: alu
         Port map(
             Cin => C5, 
             A => A(5), 
             B => B(5), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(5),
             Cout => C6
         );

    alu06: alu
         Port map(
             Cin => C6, 
             A => A(6), 
             B => B(6), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(6),
             Cout => C7
         );

    alu07: alu
         Port map(
             Cin => C7, 
             A => A(7), 
             B => B(7), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(7),
             Cout => C8
         );

    alu08: alu
         Port map(
             Cin => C8, 
             A => A(8), 
             B => B(8), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(8),
             Cout => C9
         );

    alu09: alu
         Port map(
             Cin => C9, 
             A => A(9), 
             B => B(9), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(9),
             Cout => C10
         );

    alu10: alu
         Port map(
             Cin => C10, 
             A => A(10), 
             B => B(10), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(10),
             Cout => C11
         );

    alu11: alu
         Port map(
             Cin => C11, 
             A => A(11), 
             B => B(11), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(11),
             Cout => C12
         );

    alu12: alu
         Port map(
             Cin => C12, 
             A => A(12), 
             B => B(12), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(12),
             Cout => C13
         );

    alu13: alu
         Port map(
             Cin => C13, 
             A => A(13), 
             B => B(13), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(13),
             Cout => C14
         );

    alu14: alu
         Port map(
             Cin => C14, 
             A => A(14), 
             B => B(14), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(14),
             Cout => C15
         );

    alu15: alu
         Port map(
             Cin => C15, 
             A => A(15), 
             B => B(15), 
             S0 => Gsel(1), 
             S1 => Gsel(2),
             S2 => Gsel(3),
             G => result(15),
             Cout => Cout
         );
    
    F <= result;
    
    z_detect: zero_detect
        Port map (
            I => result,
            O => Zout
        );
         
    V <= (C15 xor Cout) after 1ns;
    C <= Cout after 1ns;
    N <= result(15) after 1ns;
    Z <= Zout after 1ns;
    
end Behavioral;
