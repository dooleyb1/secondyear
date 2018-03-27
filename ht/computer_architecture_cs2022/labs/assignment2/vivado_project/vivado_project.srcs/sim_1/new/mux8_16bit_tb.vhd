library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux8_16bit_tb is
--  Port ( );
end mux8_16bit_tb;

architecture Behavioral of mux8_16bit_tb is
    -- declare component to test
    component mux8_16bit is
        Port ( 
		    In0 : in std_logic_vector(15 downto 0); 
		    In1 : in std_logic_vector(15 downto 0); 
		    In2 : in std_logic_vector(15 downto 0); 
		    In3 : in std_logic_vector(15 downto 0); 
		    In4 : in std_logic_vector(15 downto 0); 
		    In5 : in std_logic_vector(15 downto 0); 
		    In6 : in std_logic_vector(15 downto 0); 
		    In7 : in std_logic_vector(15 downto 0);
			src : in std_logic_vector(2 downto 0);
			Z : out std_logic_vector(15 downto 0)
		);
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs    
    signal In0 : std_logic_vector(15 downto 0):= x"0000";
    signal In1 : std_logic_vector(15 downto 0):= x"0000";
    signal In2 : std_logic_vector(15 downto 0):= x"0000";
    signal In3 : std_logic_vector(15 downto 0):= x"0000";
    signal In4 : std_logic_vector(15 downto 0):= x"0000";
    signal In5 : std_logic_vector(15 downto 0):= x"0000";
    signal In6 : std_logic_vector(15 downto 0):= x"0000";
    signal In7 : std_logic_vector(15 downto 0):= x"0000";    
    signal src : std_logic_vector(2 downto 0) := "000";
    
    --outputs    
    signal Z : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux8_16bit
    Port Map(
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
		--Give inputs unique values
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
        wait for 1ns;
        
        --Select line 1 and send 0x00BB to output line Z
        src <= "001";
        wait for 1ns;
        
        --Select line 2 and send 0x00CC to output line Z
        src <= "010";
        wait for 1ns;
        
        --Select line 3 and send 0x00DD to output line Z
        src <= "011";
        wait for 1ns;
 
         --Select line 4 and send 0x00EE to output line Z
        src <= "100";
        wait for 1ns;
        
        --Select line 5 and send 0x00FF to output line Z
        src <= "101";
        wait for 1ns; 
       
        --Select line 6 and send 0x0AAA to output line Z
        src <= "110";
        wait for 1ns;
        
        --Select line 7 and send 0x0BBB to output line Z
        src <= "111";
        wait for 1ns;
     end process;
    
end Behavioral;
