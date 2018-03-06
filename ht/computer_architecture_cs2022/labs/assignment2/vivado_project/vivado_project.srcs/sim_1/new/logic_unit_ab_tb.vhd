----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 03/06/2018 08:08:16 PM
-- Design Name: 
-- Module Name: logic_unit_ab_tb - Behavioral
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

entity logic_unit_ab_tb is
--  Port ( );
end logic_unit_ab_tb;

architecture Behavioral of logic_unit_ab_tb is

    -- declare component to test
    component logic_unit_ab is
        Port(
                a_logic_in: in STD_LOGIC_VECTOR(15 downto 0);
                b_logic_in : in STD_LOGIC_VECTOR(15 downto 0);
                select_in : in STD_LOGIC_VECTOR(1 downto 0);
                output_ab : out STD_LOGIC_VECTOR(15 downto 0)
            );
    end component;

    -- signals for tests (initialise to 0)
        
        signal a_logic_in : std_logic_vector(15 downto 0) := x"0000";
        signal b_logic_in : std_logic_vector(15 downto 0) := x"0000";
        signal select_in : std_logic_vector(1 downto 0) := "00";
        signal output_ab : std_logic_vector(15 downto 0) := x"0000";
        
begin
    
    -- instantiate component for test, connect ports to internal signals
    UUT: logic_unit_ab
    Port map(
        a_logic_in => a_logic_in,
        b_logic_in => b_logic_in,
        select_in => select_in,
        output_ab => output_ab
    );

    simulation_process :process
    begin
        
        a_logic_in <= x"0002";
        b_logic_in <= x"0003";
        
        --Test ALUctrl = 01000 (F=A^B)= 0x0429
        a_logic_in <= x"0569";
        b_logic_in <= x"0EB9";
        select_in <= "00";
        wait for 5ns;
        
        --Test ALUctrl = 01010 (F=AorB) = 0xFF7F
       	a_logic_in <= x"FE63";
        b_logic_in <= x"013D";
        select_in <= "01";
        wait for 5ns;
        
        --Test ALUctrl = 01100 (F=AxorB) = 0xD736
        a_logic_in <= x"B59D";
        b_logic_in <= x"62AB";
        select_in <= "10";
        wait for 5ns;
        
        --Test ALUctrl = 01110 (F=A') = 0xFFF4
        a_logic_in <= x"000B";
        b_logic_in <= x"62AB";
        select_in <= "11";
        wait for 5ns;
     
     end process;
end Behavioral;
