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

entity 16_bit_shifter_tb is
--  Port ( );
end 16_bit_shifter_tb;

architecture Behavioral of 16_bit_shifter_tb is
    -- declare component to test
    component 16_bit_shifter is
        Port ( B : in STD_LOGIC_VECTOR (15 downto 0);
		FS : in STD_LOGIC_VECTOR (4 downto 0);
		Lr : in STD_LOGIC;
		Ll : in STD_LOGIC;
		H : out STD_LOGIC_VECTOR (15 downto 0));
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal B : std_logic_vector := x'0000';
        signal FS : std_logic_vector := '00000';
        signal Lr : std_logic := '0';
        signal Ll : std_logic := '0'; 
        signal H : std_logic_vector := x'0000';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: 16_bit_shifter
    Port map(
        B => B,
        FS => FS,
        Lr => Lr,
        Ll => Ll,
        H => H
    );
    
    simulation_process :process
    begin
        --Check that incorrect FS code doesn't perform shift
       	B <= x'0FF0';
       	FS <= x'01100';
        wait for 5ns;
        
        --Check that LSL works correctly... 0x0FF0 >> = 0x7F8
       	FS <= '10100';
        wait for 5ns;
        
        --Check that LSR works correctly... 0x7F8 << = 0x0FF0
       	FS <= '11000';
        wait for 5ns;
        
        --Check that LSR works correctly... 0x0FF0 << = 0x1FE0
       	FS <= '11000';
        wait for 5ns;

     end process;
    
end Behavioral;
