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

entity zero_detect_tb is
--  Port ( );
end zero_detect_tb;

architecture Behavioral of zero_detect_tb is

    -- declare component to test
    component zero_detect is
        Port ( 
            I : in std_logic_vector(15 downto 0);
            O : out std_logic
        );
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs    
    signal I : std_logic_vector(15 downto 0):= x"0000";
    
    --outputs
    signal O : std_logic := '0';
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: zero_detect
    Port Map(
        I => I,
        O => O
    );
    
simulation_process :process
begin
        
        --Test non zero value
        I <= x"00FF";
        wait for 1ns;
        
        --Test zero value
        I <= x"0000";
        wait for 1ns;
        
        --Test non zero value
        I <= x"AFFF";
        wait for 1ns;
        
        --Test zero value
        I <= x"0000";
        wait for 1ns;
        
     wait;
     end process;
    
end Behavioral;
