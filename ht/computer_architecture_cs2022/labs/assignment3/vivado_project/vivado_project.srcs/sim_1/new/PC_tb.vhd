library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity PC_tb is
--  Port ( );
end PC_tb;

architecture Behavioral of PC_tb is

    -- declare component to test
    component PC is
        Port ( 
            PC_IN : in std_logic_vector(15 downto 0);
            PL : in std_logic;
            PI : in std_logic;
            PC_OUT : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal PC_IN : std_logic_vector(15 downto 0):= x"0000";
    signal PL : std_logic := '0';
    signal PI : std_logic := '0';
    
    --outputs
    signal PC_OUT : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: PC
    Port Map(
        PC_IN => PC_IN,
        PL => PL,
        PI => PI,
        PC_OUT => PC_OUT
    );
    
simulation_process :process
begin
        
        --Test passing withour increment PC_IN = 0xFFAB -> PC_OUT = 0xFFAB
        PC_IN <= x"FFAB";
        PL <= '1';
        wait for 1ns;
        
        --Test incrementing PC_IN .... PC_IN = 0xFF01 -> PC_OUT = 0xFF02
        PC_IN <= x"FF01";
        PI <= '1';
        wait for 1ns;
        
        --Test output when both PL and PI are 0
        PC_IN <= x"ABCD";
        PI <= '0';
        PL <= '0';
        wait for 1ns;
        
     end process;
    
end Behavioral;
