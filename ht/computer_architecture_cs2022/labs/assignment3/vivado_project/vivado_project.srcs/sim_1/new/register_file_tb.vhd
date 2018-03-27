library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity register_file_tb is
--  Port ( );
end register_file_tb;

architecture Behavioral of register_file_tb is

    -- declare component to test
    component register_file is
        Port ( 
		    a_sel: in std_logic_vector(3 downto 0);	
			b_sel : in std_logic_vector(3 downto 0);
			d_sel : in std_logic_vector(3 downto 0);
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
			reg7out : out std_logic_vector(15 downto 0);
			tempout : out std_logic_vector(15 downto 0)
		);
    end component;
    
    -- signals for tests (initialise to 0)
        
        --inputs
        signal a_sel : std_logic_vector(3 downto 0) := "0000";
        signal b_sel : std_logic_vector(3 downto 0) := "0000";
        signal d_sel : std_logic_vector(3 downto 0) := "0000";
        signal load : std_logic := '0';
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
        signal tempout : std_logic_vector(15 downto 0):= x"0000";
                   
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: register_file
    Port Map(
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
        reg7out => reg7out,
        tempout => tempout       
    );
    
simulation_process :process
begin
        --Load all registers with values
        load <= '1';
        
        --reg0
        d_sel <= "0000";
        wait for 1ns;
        data <= x"00FA";
        wait for 1ns;
        
        --reg1
        d_sel <= "0001";
        wait for 1ns;
        data <= x"00FB";
        wait for 1ns;
        
        --reg2 = 0x00FC    
        d_sel <= "0010";
        wait for 1ns;
        data <= x"00FC";
        wait for 1ns;
        
        --reg3
        d_sel <= "0011";
        wait for 1ns;
        data <= x"00FD";
        wait for 1ns;

        --reg4
        d_sel <= "0100";
        wait for 1ns;
        data <= x"00FE";
        wait for 1ns;
        
        --reg5
        d_sel <= "0101";
        wait for 1ns;
        data <= x"00FF";
        wait for 1ns;
        
        --reg6
        d_sel <= "0110";
        wait for 1ns;
        data <= x"0FAA";
        wait for 1ns;
        
        --reg7
        d_sel <= "0111";
        wait for 1ns;
        data <= x"0FBB";
        wait for 1ns;
        
        --reg8 (temp reg)
        d_sel <= "1000";
        wait for 1ns;
        data <= x"0FCC";
        wait for 1ns;
        
        d_sel <= "0000";        
        --Aout should be reg0 = 0x00FA and Bout should be reg1 = 0x00FB
        a_sel <= "0000";
        b_sel <= "0001";
        wait for 1ns;
        
       --Aout should be reg2 = 0x00FC and Bout should be reg3 = 0x00FD
       a_sel <= "0010";
       b_sel <= "0011";
       wait for 1ns;
    
       --Aout should be reg4 = 0x00FE and Bout should be reg5 = 0x00FF
       a_sel <= "0100";
       b_sel <= "0101";
       wait for 1ns;

       --Aout should be reg6 = 0x0FAA and Bout should be reg7 = 0x0FBB
       a_sel <= "0110";
       b_sel <= "0111";
       wait for 1ns;
       
       --Aout should be reg8 (temp reg) = 0x0FCC and Bout should be reg8 (temp reg) = 0x0FCC
       a_sel <= "1000";
       b_sel <= "1000";
       wait for 1ns;       
        
     wait;   
     end process;
    
end Behavioral;
