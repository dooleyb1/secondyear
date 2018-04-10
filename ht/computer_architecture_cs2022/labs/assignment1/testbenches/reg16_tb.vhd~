library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity reg16_tb is
--  Port ( );
end reg16_tb;

architecture Behavioral of reg16_tb is
    -- declare component to test
    
    component reg16 is
        Port ( 
        	load : in std_logic;
            Clk : in std_logic;   
            D : in std_logic_vector(15 downto 0);
            Q : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputa    
    signal load : std_logic := '0';   
    signal Clk : std_logic := '0';
    signal D : std_logic_vector(15 downto 0):= x"0000";
    
    --outputs
    signal Q : std_logic_vector(15 downto 0):= x"0000";
        
    -- Clock period
    constant clk_period : time := 10 ns;
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: reg16
    Port Map(
        load => load,
        Clk => Clk,
        D => D,
        Q => Q
    );
    
-- Clock 
clk_process :process
begin
         clk <= '0';
         wait for clk_period/2;
         clk <= '1';
         wait for clk_period/2;
     
end process;

--Register    
simulation_process :process
begin
        --check that when load is 0 output stays the same (0x0000)
        D <= x"000A";
        load <= '0';
        wait for 5ns;
        
        --check that when load is 1 output changes to 0x000A
        load <= '1';
        wait for 5ns;

        --check that when load is 0 remains as 0x000A
        D <= x"00FF";
        load <= '0';
        wait for 5ns;
        
        --check that when load is 1 output changes to 0x00FF
        load <= '1';
        wait for 5ns;

end process;
    
end Behavioral;
