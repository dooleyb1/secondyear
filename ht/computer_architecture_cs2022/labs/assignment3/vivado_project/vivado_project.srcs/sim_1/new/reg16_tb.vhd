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

entity reg16_tb is
--  Port ( );
end reg16_tb;

architecture Behavioral of reg16_tb is

    -- declare component to test
    component reg16 is
        Port ( 
        	D : in std_logic_vector(15 downto 0);
			load : in std_logic;
			Clk : in std_logic;
			Q : out std_logic_vector(15 downto 0)
		);
    end component;
    
    -- signals for tests (initialise to 0)
     
    --inputs    
    signal D : std_logic_vector(15 downto 0):= x"0000";
    signal Clk : std_logic := '0';  
    signal load : std_logic := '0';  
    
    --outputs
    signal Q : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: reg16
    Port Map(
        D => D,
        Clk => Clk,
        load => load,
        Q => Q
    );
    
simulation_process :process
begin
        --check that when load is 0 output stays the same (0x0000)
        D <= x"000A";
        load <= '0';
        Clk <= '0';
        wait for 10ns;
        
        --check that when load is 1 & clk is 0 output stays the same
        load<= '1';
        wait for 10ns;
        
        --check that when clk is 1 and load is 1 output changes to 0x000A
        Clk <= '1';
        wait for 10ns;

        --check that when load is 0 remains as 0x000A
        D <= x"00FF";
        load <= '0';
        Clk <= '0';
        wait for 10ns;
        
        --check that when load is 1 output changes to 0x00FF
        load <= '1';
        Clk <= '1';
        wait for 10ns;

     end process;
    
end Behavioral;
