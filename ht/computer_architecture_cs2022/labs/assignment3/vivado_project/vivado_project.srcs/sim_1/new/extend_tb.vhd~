library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity extend_tb is
--  Port ( );
end extend_tb;

architecture Behavioral of extend_tb is

    -- declare component to test
    component Extend is
        Port ( 
            DR_SA_SB : in std_logic_vector(8 downto 0);
            Ext : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs    
    signal DR_SA_SB : std_logic_vector(8 downto 0):= "000000000";
    
    --outputs
    signal Ext : std_logic_vector(15 downto 0) := x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: Extend
    Port Map(
        DR_SA_SB => DR_SA_SB,
        Ext => Ext
    );
    
simulation_process :process
begin
        
        --Test non zero value
        DR_SA_SB <= "011111010";
        wait for 10ns;

        --Test non zero value
        DR_SA_SB <= "111111111";
        wait for 10ns;        
        
     wait;
     end process;
    
end Behavioral;
