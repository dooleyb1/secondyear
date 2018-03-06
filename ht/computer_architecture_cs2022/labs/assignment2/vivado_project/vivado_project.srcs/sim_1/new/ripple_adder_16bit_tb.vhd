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

entity ripple_adder_16bit_tb is
--  Port ( );
end ripple_adder_16bit_tb;

architecture Behavioral of ripple_adder_16bit_tb is
    -- declare component to test
    component ripple_adder_16bit is
        Port ( A : in STD_LOGIC_VECTOR (15 downto 0);
		B : in STD_LOGIC_VECTOR (15 downto 0);
		Cin : in STD_LOGIC;
		S : out STD_LOGIC_VECTOR (15 downto 0);
		Cout : out STD_LOGIC;
		V : out STD_lOGIC);
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal A : std_logic_vector(15 downto 0) := x"0000";
        signal B : std_logic_vector(15 downto 0) := x"0000";
        signal Cin : std_logic := '0';
        
        signal S : std_logic_vector(15 downto 0) := x"0000";  
        signal Cout : std_logic;
        signal V : std_logic;  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: ripple_adder_16bit
    Port map(
        A => A,
        B => B,
        Cin => Cin,
        S => S,
        Cout => Cout,
        V => V
    );
    
    simulation_process :process
    begin
    
            A <= x"AAAA";
            B <= x"FBAA";
            Cin <= '1';
            wait for 80ns;
            
            --Test Carry Flag
            A <= x"FFFF";
            B <= x"0000";
            Cin <= '1';  
            wait for 80ns;
            
            --Test Overflow
            A <= x"FFFF";
            B <= x"8000";
            Cin <= '0';
        
        wait;
     end process;
    
end Behavioral;
