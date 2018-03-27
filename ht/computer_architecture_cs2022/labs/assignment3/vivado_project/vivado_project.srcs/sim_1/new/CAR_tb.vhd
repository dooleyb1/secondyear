library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity CAR_tb is
--  Port ( );
end CAR_tb;

architecture Behavioral of CAR_tb is

    -- declare component to test
    component CAR is
        Port ( 
            CAR_IN : in std_logic_vector(7 downto 0);
            FLAGS_IN : in std_logic;
            CAR_OUT : out std_logic_vector(7 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal CAR_IN : std_logic_vector(7 downto 0):= x"00";
    signal FLAGS_IN : std_logic := '1';
    
    --outputs
    signal CAR_OUT : std_logic_vector(7 downto 0):= x"00";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: CAR
    Port Map(
        CAR_IN => CAR_IN,
        FLAGS_IN => FLAGS_IN,
        CAR_OUT => CAR_OUT
    );
    
simulation_process :process
begin
        
        --Test passing withour increment (FLAGS_IN = 0)
        CAR_IN <= x"FF";
        FLAGS_IN <= '0';
        wait for 1ns;
        
        --Test incrementing CAR_IN (FLAGS_IN = 1)
        CAR_IN <= x"01";
        FLAGS_IN <= '1';
        wait for 1ns;
     
     end process;
    
end Behavioral;
