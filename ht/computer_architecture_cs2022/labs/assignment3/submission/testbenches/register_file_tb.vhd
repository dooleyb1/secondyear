-- MIT License
--
-- Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in
-- all copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
-- THE SOFTWARE.
--

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity register_file_tb is
--  Port ( );
end register_file_tb;

architecture Behavioral of register_file_tb is

    -- declare component to test
    component register_file is
        Port ( 
		    DA: in std_logic_vector(3 downto 0);	
			AA : in std_logic_vector(3 downto 0);
			BA : in std_logic_vector(3 downto 0);
			
			Clk : in std_logic;
			RW : in std_logic;
			
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
			reg7out : out std_logic_vector(15 downto 0);
			reg8out : out std_logic_vector(15 downto 0)
		);
    end component;
    
    -- signals for tests (initialise to 0)
        
        --inputs
        signal AA : std_logic_vector(3 downto 0) := "0000";
        signal BA : std_logic_vector(3 downto 0) := "0000";
        signal DA : std_logic_vector(3 downto 0) := "0000";
        
        signal Clk : std_logic := '0';
        signal RW : std_logic := '0';
        
        signal data : std_logic_vector(15 downto 0):= x"0000";
        
        --outputs
        signal a_out : std_logic_vector(15 downto 0):= x"0000";
        signal b_out : std_logic_vector(15 downto 0):= x"0000";

		--register values out
        signal reg0out : std_logic_vector(15 downto 0):= x"0000";
        signal reg1out : std_logic_vector(15 downto 0):= x"0000";
        signal reg2out : std_logic_vector(15 downto 0):= x"0000";
        signal reg3out : std_logic_vector(15 downto 0):= x"0000";
        signal reg4out : std_logic_vector(15 downto 0):= x"0000";
        signal reg5out : std_logic_vector(15 downto 0):= x"0000";
        signal reg6out : std_logic_vector(15 downto 0):= x"0000";
        signal reg7out : std_logic_vector(15 downto 0):= x"0000";
        signal reg8out : std_logic_vector(15 downto 0):= x"0000";
                   
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: register_file
    Port Map(
        DA => DA,
        AA => AA,
        BA => BA,
        
        Clk => Clk,
        RW => RW,
        
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
        reg7out => reg7out,
        reg8out => reg8out       
    );
    
simulation_process :process
begin
        --Load all registers with values
        RW <= '1';
        
        --reg0
        data <= x"00FA";
        DA <= "0000";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg1
        data <= x"00FB";
        DA <= "0001";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg2
        data <= x"00FC";
        DA <= "0010";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg3
        data <= x"00FD";
        DA <= "0011";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg4
        data <= x"00FE";
        DA <= "0100";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg5
        data <= x"00FF";
        DA <= "0101";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg6
        data <= x"0FAA";
        DA <= "0110";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg7
        data <= x"0FBB";
        DA <= "0111";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
        
        --reg7
        data <= x"0FCC";
        DA <= "1000";
        wait for 10ns;
        Clk <= '1';
        wait for 10ns;
        Clk <= '0';
      
        --Aout should be reg0 = 0x00FA and Bout should be reg1 = 0x00FB
        AA <= "0000";
        BA <= "0001";
        wait for 10ns;
        
       --Aout should be reg2 = 0x00FC and Bout should be reg3 = 0x00FD
       AA <= "0010";
       BA <= "0011";
       wait for 10ns;
    
       --Aout should be reg4 = 0x00FE and Bout should be reg5 = 0x00FF
       AA <= "0100";
       BA <= "0101";
       wait for 10ns;

       --Aout should be reg6 = 0x0FAA and Bout should be reg7 = 0x0FBB
       AA <= "0110";
       BA <= "0111";
       wait for 10ns;
       
       --Aout should be reg8 (temp reg) = 0x0FAB and Bout should be reg8 (temp reg) = 0x0FAB
       AA <= "1000";
       BA <= "1000";
       wait for 10ns;       
        
     wait;   
     end process;
    
end Behavioral;
