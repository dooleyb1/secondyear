----------------------------------------------------------------------------------
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity reg16_tb is
--  Port ( );
end reg16_tb;

architecture Behavioral of reg16_tb is
    -- declare component to test
    component reg16 is
        Port ( load : in std_logic;
            Clk : in std_logic;   
            D : in std_logic_vector(15 downto 0);
            Q : out std_logic_vector(15 downto 0));
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal load : std_logic := '0';   
        signal Clk : std_logic := '0';
        
        signal D : std_logic_vector(15 downto 0):= x"0000";
        signal Q : std_logic_vector(15 downto 0):= x"0000";
        
         -- Clock period
         constant clk_period : time := 10 ns;
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: reg16
    Port map(
        load => load,
        Clk => Clk,
        D => D,
        Q => Q
    );
    
    -- Clock 
    clk_process :process
    begin
         clk <= '0';
         wait for clk_period/2;
         clk <= '1';
         wait for clk_period/2;
     end process;
    
    simulation_process :process
    begin
        --check that when load is 0 output stays the same (0x0000)
        D <= x"000A";
        load <= '0';
        wait for 5ns;
        
        --check that when load is 1 output changes to 0x000A
        load <= '1';
        wait for 5ns;

        --check that when load is 0 remains as 0x000A
        D <= x"00FF";
        load <= '0';
        wait for 5ns;
        
        --check that when load is 1 output changes to 0x00FF
        load <= '1';
        wait for 5ns;

     end process;
    
end Behavioral;
