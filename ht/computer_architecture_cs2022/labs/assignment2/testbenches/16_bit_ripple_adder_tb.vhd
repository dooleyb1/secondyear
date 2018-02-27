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

entity 16_bit_ripple_adder_tb is
--  Port ( );
end 16_bit_ripple_adder_tb;

architecture Behavioral of 16_bit_ripple_adder_tb is
    -- declare component to test
    component 16_bit_ripple_adder is
        Port ( A : in STD_LOGIC_VECTOR (15 downto 0);
		B : in STD_LOGIC_VECTOR (15 downto 0);
		Cin : in STD_LOGIC;
		S : out STD_LOGIC_VECTOR (15 downto 0);
		Cout : out STD_LOGIC
		V : out STD_lOGIC);
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal A : std_logic := '0';
        signal B : std_logic := '0';
        signal Cin : std_logic := '0';
        signal S : std_logic_vector := '00';  
        signal Cout : std_logic := '0'
        signal V : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: 16_bit_ripple_adder
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
        --Check that 0x0001 + 0x0001 + Cin(0) = 0x0002
       	A <= x'0001';
       	B <= x'0001';
       	Cin <= '0';
        wait for 5ns;
        
        --Check that 0x0001 + 0x0001 + Cin(1) = 0x0003
       	A <= x'0001';
       	B <= x'0001';
       	Cin <= '1';
        wait for 5ns;
        
       --Check that 0x0FF0 + 0xABCD + Cin(0) = 0xBBBD
       	A <= x'0FF0';
       	B <= x'ABCD';
       	Cin <= '0';
        wait for 5ns;
        
       --Check that 0x0FF0 + 0xABCD + Cin(1) = 0xBBBE
       	A <= x'0FF0';
       	B <= x'ABCD';
       	Cin <= '1';
        wait for 5ns;
        
        --Check that 0x7FFF + 0x0001 + Cin(0) = 0x8000 (OVERFLOW SET)
       	A <= x'7FFF';
       	B <= x'0001';
       	Cin <= '0';
        wait for 5ns;
        

     end process;
    
end Behavioral;
