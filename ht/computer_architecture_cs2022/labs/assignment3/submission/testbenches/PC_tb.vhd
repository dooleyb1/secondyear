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

entity PC_tb is
--  Port ( );
end PC_tb;

architecture Behavioral of PC_tb is

    -- declare component to test
    component PC is
        Port ( 
            PC_IN : in std_logic_vector(15 downto 0);
            PL : in std_logic;
            PI : in std_logic;
            RESET : in std_logic;
            Clk : in std_logic;
            PC_OUT : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal PC_IN : std_logic_vector(15 downto 0):= x"0000";
    signal PL : std_logic := '0';
    signal PI : std_logic := '0';
    signal Clk : std_logic := '0';
    signal RESET : std_logic := '0';
    
    --outputs
    signal PC_OUT : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: PC
    Port Map(
        PC_IN => PC_IN,
        PL => PL,
        PI => PI,
        RESET => RESET,
        Clk => Clk,
        PC_OUT => PC_OUT
    );
    
simulation_process :process
begin
        --Test Reset 
        RESET <= '1';
        Clk <= '1';
        wait for 10ns;
        
        RESET <= '0';
        Clk <= '0';
        wait for 10ns;
        
        --Test passing withour increment PC_IN = 0xFFAB -> PC_OUT = 0xFFAB
        PC_IN <= x"FFAB";
        PL <= '1';
        PI <= '0';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
        
        --Test incrementing with load low - PC_IN = 0xFF01 -> PC_OUT = 0xFFAC
        PC_IN <= x"FF01";
        PL <= '0';
        PI <= '1';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
        
        --Test output when both PL and PI are 1
        PC_IN <= x"0003";
        PL <= '1';
        PI <= '1';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
        
        --Test output when both PL and PI are 0
        PC_IN <= x"ABCD";
        PI <= '0';
        PL <= '0';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
        
        --Test reset again
        RESET <= '1';
        Clk <= '1';
        wait for 10ns;
        
     end process;
    
end Behavioral;
