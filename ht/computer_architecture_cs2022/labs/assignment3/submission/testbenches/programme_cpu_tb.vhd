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

entity programme_cpu_tb is
--  Port ( );
end programme_cpu_tb;

architecture Behavioral of programme_cpu_tb is

    component programme_cpu is
        Port (
            clk : in std_logic;
            reset: in std_logic;
            reg0: out std_logic_vector(15 downto 0);
            reg1: out std_logic_vector(15 downto 0);
            reg2: out std_logic_vector(15 downto 0);
            reg3: out std_logic_vector(15 downto 0);
            reg4: out std_logic_vector(15 downto 0);
            reg5: out std_logic_vector(15 downto 0);
            reg6: out std_logic_vector(15 downto 0);
            reg7: out std_logic_vector(15 downto 0)
        );
    end component;

    signal clk, reset : std_logic := '0';
    signal reg0out, reg1out, reg2out, reg3out, reg4out, reg5out, reg6out, reg7out : std_logic_vector(15 downto 0);

    constant clk_period : time := 40ns;
    constant cycles : integer := 0;
    constant max_cycles : integer := 1000;

begin

    UTT: programme_cpu
        Port Map (
            clk => clk,
            reset => reset,
            reg0 => reg0out,
            reg1 => reg1out,
            reg2 => reg2out,
            reg3 => reg3out,
            reg4 => reg4out,
            reg5 => reg5out,
            reg6 => reg6out,
            reg7 => reg7out
        );

process
    begin
        reset <= '1';
        clk <= '1';
        wait for 50 ns;
        reset <= '0';
        clk <= '0';
        while cycles < max_cycles loop
            clk <= not clk;
            wait for clk_period/2;
        end loop;
        wait;
end process;

end Behavioral;