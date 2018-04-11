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

entity memory_tb is
--  Port ( );
end memory_tb;

architecture Behavioral of memory_tb is

    -- declare component to test
    component memory is
        Port ( 
            address : in std_logic_vector(15 downto 0);
            data_in : in std_logic_vector(15 downto 0);
            Clk : in std_logic;
            MW : in std_logic;
            data_out : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal address : std_logic_vector(15 downto 0) := x"0000";
    signal data_in : std_logic_vector(15 downto 0) := x"0000";
    
    signal Clk : std_logic := '0';
    signal MW : std_logic := '0';
    
    --outputs
    signal data_out : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: memory
    Port Map(
        address => address,
        data_in => data_in,
        Clk => Clk,
        MW => MW,
        data_out => data_out
    );
    
simulation_process :process
begin
        --Read Value at address 2 (0x0045)
        address <= x"0002";
        data_in <= x"0000";
        MW <= '0';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
        
        
        --Write to address F 
        address <= x"00FF";
        data_in <= x"ABCD";
        MW <= '1';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns; 
        
        --Read Value at address 9 (0x0020)
        address <= x"0009";
        data_in <= x"0000";
        MW <= '0';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns; 
        
        --Read Value just written to address F (0xABCD)
        address <= x"00FF";
        data_in <= x"0000";
        MW <= '0';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
                
     end process;
    
end Behavioral;
