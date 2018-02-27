----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/14/2018 12:22:16 PM
-- Design Name: 
-- Module Name: register_file_tb - Behavioral
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

entity register_file_tb is
--  Port ( );
end register_file_tb;

architecture Behavioral of register_file_tb is
    -- declare component to test
    component register_file is
        Port ( a_sel: in std_logic_vector(2 downto 0);	
		b_sel : in std_logic_vector(2 downto 0);
		d_sel : in std_logic_vector(2 downto 0);
		load : in std_logic;
		data : in std_logic_vector(15 downto 0);
		a_out : out std_logic_vector(15 downto 0)
		b_out : out std_logic_vector(15 downto 0));
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal a_sel : std_logic := '000';
        signal b_sel : std_logic := '000';
        signal d_sel : std_logic := '000';
        
        signal load : std_logic := '0';
        
        signal data : std_logic_vector(15 downto 0):= x"0000";
        signal a_out : std_logic_vector(15 downto 0):= x"0000";
        signal b_out : std_logic_vector(15 downto 0):= x"0000";
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: register_file
    Port map(
        a_sel => a_sel,
        b_sel => b_sel,
        d_sel => d_sel,
        load => load,
        data => data,
        a_out => a_out,
        b_out => b_out
    );
    
    simulation_process :process
    begin
        --Aout should be 0x000A and Bout should be 0x000A
        a_sel <= '000'
        b_sel <= '000'
        d_sel <= '000'
        load <= '1'
        data <= x"000A";
        wait for 10ns;
        
        --Aout should be 0x000A and Bout should be 0x000B
        a_sel <= '000'
        b_sel <= '001'
        d_sel <= '001'
        load <= '1'
        data <= x"000B";
        wait for 10ns;
    
       --Aout should be 0x000A and Bout should be 0x000C
        a_sel <= '000'
        b_sel <= '010'
        d_sel <= '000'
        load <= '1'
        data <= x"000C";
        wait for 10ns;

        --Aout should be 0x000A and Bout should be 0x000D
        a_sel <= '000'
        b_sel <= '011'
        d_sel <= '000'
        load <= '1'
        data <= x"000D";
        wait for 10ns;
        
        --Aout should be 0x000A and Bout should be 0x000E
        a_sel <= '000'
        b_sel <= '100'
        d_sel <= '000'
        load <= '1'
        data <= x"000E";
        wait for 10ns;

        --Aout should be 0x000A and Bout should be 0x000A
        a_sel <= '000'
        b_sel <= '101'
        d_sel <= '000'
        load <= '1'
        data <= x"000F";
        wait for 10ns;
        
        --Aout should be 0x000A and Bout should be 0x00AA
        a_sel <= '000'
        b_sel <= '110'
        d_sel <= '000'
        load <= '1'
        data <= x"00AA";
        wait for 10ns;
        
        --Aout should be 0x000A and Bout should be 0x00BB
        a_sel <= '000'
        b_sel <= '111'
        d_sel <= '000'
        load <= '1'
        data <= x"00BB";
        wait for 10ns;
        
     end process;
    
end Behavioral;
