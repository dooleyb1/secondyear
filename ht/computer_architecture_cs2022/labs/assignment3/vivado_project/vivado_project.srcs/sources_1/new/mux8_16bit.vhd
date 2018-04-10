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

entity mux8_16bit is
	Port (
		In0 : in std_logic_vector(15 downto 0);
		In1 : in std_logic_vector(15 downto 0);
		In2 : in std_logic_vector(15 downto 0);
		In3 : in std_logic_vector(15 downto 0); 
		In4 : in std_logic_vector(15 downto 0); 
		In5 : in std_logic_vector(15 downto 0); 
		In6 : in std_logic_vector(15 downto 0); 
		In7 : in std_logic_vector(15 downto 0);
		src : in std_logic_vector(2 downto 0);
		Z : out std_logic_vector(15 downto 0)
	);
end mux8_16bit;

architecture Behavioral of mux8_16bit is
begin
	Z <= In0 after 5ns when src="000" else
		 In1 after 5ns when src="001" else
		 In2 after 5ns when src="010" else
		 In3 after 5ns when src="011" else
		 In4 after 5ns when src="100" else
		 In5 after 5ns when src="101" else
		 In6 after 5ns when src="110" else
		 In7 after 5ns when src="111" else
	     x"0000" after 5ns;
end Behavioral;
