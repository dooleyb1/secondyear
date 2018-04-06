library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity reg16_tb is
--  Port ( );
end reg16_tb;

architecture Behavioral of reg16_tb is

    -- declare component to test
    component reg16 is
        Port ( 
        	D : in std_logic_vector(15 downto 0);
			load : in std_logic;
			Clk : in std_logic;
			Q : out std_logic_vector(15 downto 0)
		);
    end component;
    
    -- signals for tests (initialise to 0)
     
    --inputs    
    signal D : std_logic_vector(15 downto 0):= x"0000";
    signal Clk : std_logic := '0';  
    signal load : std_logic := '0';  
    
    --outputs
    signal Q : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: reg16
    Port Map(
        D => D,
        Clk => Clk,
        load => load,
        Q => Q
    );
    
simulation_process :process
begin
        --check that when load is 0 output stays the same (0x0000)
        D <= x"000A";
        load <= '0';
        Clk <= '0';
        wait for 10ns;
        
        --check that when load is 1 & clk is 0 output stays the same
        load<= '1';
        wait for 10ns;
        
        --check that when clk is 1 and load is 1 output changes to 0x000A
        Clk <= '1';
        wait for 10ns;

        --check that when load is 0 remains as 0x000A
        D <= x"00FF";
        load <= '0';
        Clk <= '0';
        wait for 10ns;
        
        --check that when load is 1 output changes to 0x00FF
        load <= '1';
        Clk <= '1';
        wait for 10ns;

     end process;
    
end Behavioral;
