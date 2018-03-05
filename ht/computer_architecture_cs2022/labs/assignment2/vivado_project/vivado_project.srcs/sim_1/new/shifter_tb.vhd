----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/14/2018 12:22:16 PM
-- Design Name: 
-- Module Name: reg16_tb - Behavioral
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

entity shifter_tb is
--  Port ( );
end shifter_tb;

architecture Behavioral of shifter_tb is
    -- declare component to test
    component shifter is
        Port ( In0 : in std_logic;
		In1 : in std_logic;
		In2 : in std_logic;
		s : in std_logic_vector(1 downto 0);
		Z : out std_logic);
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal In0 : std_logic := '0';
        signal In1 : std_logic := '0';
        signal In2 : std_logic := '0';
        signal s : std_logic_vector(1 downto 0) := "00";  
        signal Z : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: shifter
    Port map(
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
        wait for 5ns;
        
        --Check that select 1 selects line 1 (1)
       	s <= "01";
        wait for 5ns;
        
        ---Check that select 2 selects line 2 (1)
       	s <= "10";
        wait for 5ns;

     end process;
    
end Behavioral;
