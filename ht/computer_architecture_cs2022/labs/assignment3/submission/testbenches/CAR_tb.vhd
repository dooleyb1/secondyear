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

entity CAR_tb is
--  Port ( );
end CAR_tb;

architecture Behavioral of CAR_tb is

    -- declare component to test
    component CAR is
        Port ( 
            A : in std_logic;
            B : in std_logic_vector(7 downto 0);
            
            RESET : in std_logic;
            CLK : in std_logic;
            
            Z : out std_logic_vector(7 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal A : std_logic := '0';
    signal B : std_logic_vector(7 downto 0):= x"00";
    
    signal RESET : std_logic := '0';
    signal CLK : std_logic := '0';
    
    --outputs
    signal Z : std_logic_vector(7 downto 0):= x"00";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: CAR
    Port Map(
        A => A,
        B => B,
        RESET => RESET,
        CLK => CLK,
        Z => Z
    );
    
simulation_process :process
begin
        --RESET First, reset address is 0xC0
        A <= '0';
        B <= x"00";
        RESET <= '1';
        CLK <= '1';
        wait for 10ns;
        
        CLK <= '0';
        RESET <= '0';
        wait for 10ns;
        
        --Test no passing (FLAGS_IN = 0) Z Should be 0xC0
        A <= '0';
        B <= x"FF";
        CLK <= '1';
        wait for 10ns;
        
        CLK <= '0';
        wait for 10ns;  
        
        --Test CAR_IN (FLAGS_IN = 1) Z should be 0x01
        A <= '1';
        B <= x"01";
        CLK <= '1';
        wait for 10ns;
        
        --Test RESET, Z shoudl be 0xC0 (first instruction in memory)
        A <= '1';
        B <= x"FF";
        RESET <= '1';
        CLK <= '1';
        wait for 10ns;
        
        CLK <= '0';
        wait for 10ns;
     
     end process;
    
end Behavioral;
