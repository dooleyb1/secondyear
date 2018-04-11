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

entity shifter_tb is
--  Port ( );
end shifter_tb;

architecture Behavioral of shifter_tb is

    -- declare component to test
    component shifter is
        Port ( 
		    In0 : in std_logic;
			In1 : in std_logic;
			In2 : in std_logic;
			s : in std_logic_vector(1 downto 0);
			Z : out std_logic
		);
    end component;
    
    -- signals for tests (initialise to 0)
       
    --inputs    
    signal In0 : std_logic := '0';
    signal In1 : std_logic := '0';
    signal In2 : std_logic := '0';
    signal s : std_logic_vector(1 downto 0) := "00"; 
    
    --outputs 
    signal Z : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: shifter
    Port Map(
        In0 => In0,
        In1 => In1,
        In2 => In2,
        s => s,
        Z => Z
    );
    
simulation_process :process
begin
        --Check that select 0 selects line 0 (1)
       	In0 <= '1';
       	In1 <= '0';
       	In2 <= '1';
       	s <= "00";
        wait for 1ns;
        
        --Check that select 1 selects line 1 (1)
       	s <= "01";
        wait for 1ns;
        
        ---Check that select 2 selects line 2 (1)
       	s <= "10";
        wait for 1ns;

     end process;
    
end Behavioral;
