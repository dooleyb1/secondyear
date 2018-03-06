----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/14/2018 12:22:16 PM
-- Design Name: 
-- Module Name: mux2_16bit_tb - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity zero_detect_tb is
--  Port ( );
end zero_detect_tb;

architecture Behavioral of zero_detect_tb is
    -- declare component to test
    component zero_detect is
        Port ( 
            I : in std_logic_vector(15 downto 0);
            O : out std_logic);
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal I : std_logic_vector(15 downto 0):= x"0000";
        signal O : std_logic := '0';
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: zero_detect
    Port map(
        I => I,
        O => O
    );
    
    simulation_process :process
    begin
        
        wait for 10ns;
        --Test non zero value
        I <= x"00FF";
        wait for 5ns;
        
        --Test zero value
        I <= x"0000";
        wait for 5ns;
        
        --Test non zero value
        I <= x"AFFF";
        wait for 5ns;
        
        --Test zero value
        I <= x"0000";
        wait for 5ns;
        
     wait;
     end process;
    
end Behavioral;
