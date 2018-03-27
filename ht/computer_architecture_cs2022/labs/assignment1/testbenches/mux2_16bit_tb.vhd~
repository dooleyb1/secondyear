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

entity mux2_16bit_tb is
--  Port ( );
end mux2_16bit_tb;

architecture Behavioral of mux2_16bit_tb is
    -- declare component to test
    component mux2_16bit is
        Port ( In0 : in std_logic_vector(15 downto 0);
            In1 : in std_logic_vector(15 downto 0);
            s : in std_logic;
            Z : out std_logic_vector(15 downto 0));
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal In0 : std_logic_vector(15 downto 0):= x"0000";
        signal In1 : std_logic_vector(15 downto 0):= x"0000";
        signal s : std_logic := '0';
        signal Z : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux2_16bit
    Port map(
        In0 => In0,
        In1 => In1,
        s => s,
        Z => Z
    );
    
    simulation_process :process
    begin
        
        In0 <= x"00FF";
        In1 <= x"00AA";
        
        --Select line 0 (data input) and send 0x00FF to output line Z
        s <= '0';
        wait for 5ns;
        
        --Select line 1 (register data) and send 0x00AA to output line Z
        s <= '1';
        wait for 5ns;
     
     end process;
    
end Behavioral;
