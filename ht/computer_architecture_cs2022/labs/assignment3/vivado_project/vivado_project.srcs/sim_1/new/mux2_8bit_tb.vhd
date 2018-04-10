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

entity mux2_8bit_tb is
--  Port ( );
end mux2_8bit_tb;

architecture Behavioral of mux2_8bit_tb is

    -- declare component to test
    component mux2_8bit is
        Port ( In0 : in std_logic_vector(7 downto 0);
            In1 : in std_logic_vector(7 downto 0);
            Sel : in std_logic;
            mux_out : out std_logic_vector(7 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal In0 : std_logic_vector(7 downto 0):= x"00";
    signal In1 : std_logic_vector(7 downto 0):= x"00";
    signal Sel : std_logic := '0';
    
    --outputs
    signal mux_out : std_logic_vector(7 downto 0):= x"00";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux2_8bit
    Port Map(
        In0 => In0,
        In1 => In1,
        Sel => Sel,
        mux_out => mux_out
    );
    
simulation_process :process
begin
        
        In0 <= x"FF";
        In1 <= x"AA";
        
        --Select line 0 (data input) and send 0x00FF to output line Z
        Sel <= '0';
        wait for 1ns;
        
        --Select line 1 (register data) and send 0x00AA to output line Z
        Sel <= '1';
        wait for 1ns;
     
     end process;
    
end Behavioral;
