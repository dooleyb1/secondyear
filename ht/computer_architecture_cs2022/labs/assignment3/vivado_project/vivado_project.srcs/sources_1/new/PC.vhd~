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

entity PC is
 Port ( 
      PC_IN : in std_logic_vector(15 downto 0);
      PL : in std_logic;
      PI : in std_logic;
      RESET : in std_logic;
      Clk : in std_logic;
      PC_OUT : out std_logic_vector(15 downto 0)
     );
end PC;

architecture Behavioral of PC is

begin

    process (RESET, PL, PI, Clk)
    variable pc : std_logic_vector(15 downto 0);
    variable temp_pc : integer;
    variable temp_inc_pc : std_logic_vector(15 downto 0);
    
    begin
        if(reset = '1' and clk = '1') then pc := x"0000";
        elsif(PL = '1' and clk = '1') then
            pc := pc + PC_IN;
        elsif(PI = '1' and clk = '1') then
            temp_pc := conv_integer(pc);
            temp_pc := temp_pc + conv_integer(1);
            temp_inc_pc := conv_std_logic_vector(temp_pc, 16);
            pc := temp_inc_pc;
        end if;
        PC_OUT <= pc after 10ns;
    end process;
end Behavioral;
