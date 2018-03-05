----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/14/2018 12:22:16 PM
-- Design Name: 
-- Module Name: mux8_16bit_tb - Behavioral
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

entity mux8_16bit_tb is
--  Port ( );
end mux8_16bit_tb;

architecture Behavioral of mux8_16bit_tb is
    -- declare component to test
    component mux8_16bit is
        Port ( In0, In1, In2, In3, In4, In5, In6, In7 : in std_logic_vector(15 downto 0);
    src : in std_logic_vector(2 downto 0);
    Z : out std_logic_vector(15 downto 0));
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal In0 : std_logic_vector(15 downto 0):= x"0000";
        signal In1 : std_logic_vector(15 downto 0):= x"0000";
        signal In2 : std_logic_vector(15 downto 0):= x"0000";
        signal In3 : std_logic_vector(15 downto 0):= x"0000";
        signal In4 : std_logic_vector(15 downto 0):= x"0000";
        signal In5 : std_logic_vector(15 downto 0):= x"0000";
        signal In6 : std_logic_vector(15 downto 0):= x"0000";
        signal In7 : std_logic_vector(15 downto 0):= x"0000";
        
        signal src : std_logic_vector(2 downto 0) := "000";
        
        signal Z : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux8_16bit
    Port map(
        In0 => In0,
        In1 => In1,
        In2 => In2,
        In3 => In3,
        In4 => In4,
        In5 => In5,
        In6 => In6,
        In7 => In7,
        src => src,
        Z => Z
    );
    
    simulation_process :process
    begin
        In0 <= x"00AA";
        In1 <= x"00BB";
        In2 <= x"00CC";
        In3 <= x"00DD";
        In4 <= x"00EE";
        In5 <= x"00FF";
        In6 <= x"0AAA";
        In7 <= x"0BBB";
        
        --Select line 0 and send 0x00AA to output line Z
        src <= "000";
        wait for 5ns;
        
        --Select line 1 and send 0x00BB to output line Z
        src <= "001";
        wait for 5ns;
        
        --Select line 2 and send 0x00CC to output line Z
        src <= "010";
        wait for 5ns;
        
        --Select line 3 and send 0x00DD to output line Z
        src <= "011";
        wait for 5ns;
 
         --Select line 4 and send 0x00EE to output line Z
        src <= "100";
        wait for 5ns;
        
        --Select line 5 and send 0x00FF to output line Z
        src <= "101";
        wait for 5ns; 
       
        --Select line 6 and send 0x0AAA to output line Z
        src <= "110";
        wait for 5ns;
        
        --Select line 7 and send 0x0BBB to output line Z
        src <= "111";
        wait for 5ns;
     end process;
    
end Behavioral;
