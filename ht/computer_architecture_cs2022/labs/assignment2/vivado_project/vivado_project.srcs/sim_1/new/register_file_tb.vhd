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
		a_out : out std_logic_vector(15 downto 0);
		b_out : out std_logic_vector(15 downto 0);
		reg0out : out std_logic_vector(15 downto 0);
		reg1out : out std_logic_vector(15 downto 0);
		reg2out : out std_logic_vector(15 downto 0);
		reg3out : out std_logic_vector(15 downto 0);
		reg4out : out std_logic_vector(15 downto 0);
		reg5out : out std_logic_vector(15 downto 0);
		reg6out : out std_logic_vector(15 downto 0);
		reg7out : out std_logic_vector(15 downto 0));
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal a_sel : std_logic_vector(2 downto 0) := "000";
        signal b_sel : std_logic_vector(2 downto 0) := "000";
        signal d_sel : std_logic_vector(2 downto 0) := "000";
        
        signal load : std_logic := '0';
        
        signal data : std_logic_vector(15 downto 0):= x"0000";
        signal a_out : std_logic_vector(15 downto 0):= x"0000";
        signal b_out : std_logic_vector(15 downto 0):= x"0000";

        signal reg0out : std_logic_vector(15 downto 0):= x"0000";
        signal reg1out : std_logic_vector(15 downto 0):= x"0000";
        signal reg2out : std_logic_vector(15 downto 0):= x"0000";
        signal reg3out : std_logic_vector(15 downto 0):= x"0000";
        signal reg4out : std_logic_vector(15 downto 0):= x"0000";
        signal reg5out : std_logic_vector(15 downto 0):= x"0000";
        signal reg6out : std_logic_vector(15 downto 0):= x"0000";
        signal reg7out : std_logic_vector(15 downto 0):= x"0000";
                
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
        b_out => b_out,
        reg0out => reg0out,
        reg1out => reg1out,
        reg2out => reg2out,
        reg3out => reg3out,
        reg4out => reg4out,
        reg5out => reg5out,
        reg6out => reg6out,
        reg7out => reg7out        
    );
    
    simulation_process :process
    begin
        --Load all registers with values
        load <= '1';
        
        --reg0
        d_sel <= "000";
        wait for 5ns;
        data <= x"00FA";
        wait for 5ns;
        
        --reg1
        d_sel <= "001";
        wait for 5ns;
        data <= x"00FB";
        wait for 5ns;
        
        --reg2 = 0x00FC    
        d_sel <= "010";
        wait for 5ns;
        data <= x"00FC";
        wait for 5ns;
        
        --reg3
        d_sel <= "011";
        wait for 5ns;
        data <= x"00FD";
        wait for 5ns;

        --reg4
        d_sel <= "100";
        wait for 5ns;
        data <= x"00FE";
        wait for 5ns;
        
        --reg5
        d_sel <= "101";
        wait for 5ns;
        data <= x"00FF";
        wait for 5ns;
        
        --reg6
        d_sel <= "110";
        wait for 5ns;
        data <= x"0FAA";
        wait for 5ns;
        
        --reg7
        d_sel <= "111";
        wait for 5ns;
        data <= x"0FBB";
        wait for 5ns;
        d_sel <= "000";
        
        
        --Aout should be reg0 = 0x00FA and Bout should be reg1 = 0x00FB
        a_sel <= "000";
        b_sel <= "001";
        wait for 5ns;
        
       --Aout should be reg2 = 0x00FC and Bout should be reg3 = 0x00FD
       a_sel <= "010";
       b_sel <= "011";
       wait for 5ns;
    
       --Aout should be reg4 = 0x00FE and Bout should be reg5 = 0x00FF
       a_sel <= "100";
       b_sel <= "101";
       wait for 5ns;

       --Aout should be reg6 = 0x0FAA and Bout should be reg7 = 0x0FBB
       a_sel <= "110";
       b_sel <= "111";
       wait for 5ns;
        
     wait;   
     end process;
    
end Behavioral;
