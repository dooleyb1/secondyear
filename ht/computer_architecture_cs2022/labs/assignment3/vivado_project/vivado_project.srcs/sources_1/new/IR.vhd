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


entity IR is

Port (
       IR_IN : in std_logic_vector(15 downto 0);
       IL : in std_logic;
       CLK : in std_logic;
       OPCODE : out std_logic_vector(6 downto 0);
       DR : out std_logic_vector(2 downto 0);
       SA : out std_logic_vector(2 downto 0);
       SB : out std_logic_vector(2 downto 0)
      );
end IR;

architecture Behavioral of IR is

signal IR_temp : std_logic_vector(15 downto 0);

begin

IR_temp <= IR_IN after 1ns when IL = '1' 
           else IR_temp after 1ns;

OPCODE <= IR_temp(15 downto 9) when CLK = '1';
DR <= IR_temp(8 downto 6) when CLK = '1';
SA <= IR_temp(5 downto 3) when CLK = '1';
SB <= IR_temp(2 downto 0) when CLK = '1';

end Behavioral;
