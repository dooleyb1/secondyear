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
use IEEE.std_logic_1164.ALL;

entity mux2_8bit is
	Port(
		Sel : in std_logic;
		In0 : in std_logic_vector(7 downto 0);
		In1 : in std_logic_vector(7 downto 0);
		mux_out : out std_logic_vector(7 downto 0)
	);
end mux2_8bit;

architecture Behavioral of mux2_8bit is

begin

	mux_out <= 	In0 after 1ns when Sel = '0' else
				In1 after 1ns when Sel = '1' else
				x"00" after 1ns;

end Behavioral;
