library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity zero_detect_tb is
--  Port ( );
end zero_detect_tb;

architecture Behavioral of zero_detect_tb is

    -- declare component to test
    component zero_detect is
        Port ( 
            I : in std_logic_vector(15 downto 0);
            O : out std_logic
        );
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs    
    signal I : std_logic_vector(15 downto 0):= x"0000";
    
    --outputs
    signal O : std_logic := '0';
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: zero_detect
    Port Map(
        I => I,
        O => O
    );
    
simulation_process :process
begin
        
        --Test non zero value
        I <= x"00FF";
        wait for 1ns;
        
        --Test zero value
        I <= x"0000";
        wait for 1ns;
        
        --Test non zero value
        I <= x"AFFF";
        wait for 1ns;
        
        --Test zero value
        I <= x"0000";
        wait for 1ns;
        
     wait;
     end process;
    
end Behavioral;
