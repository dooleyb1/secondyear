library IEEE;
use IEEE.std_logic_1164.ALL;

entity mux2_1bit_tb is
--  Port ( );
end mux2_1bit_tb;

architecture Behavioral of mux2_1bit_tb is
    -- declare component to test
    component mux2_1bit is
        Port(
                Sel, In0, In1 : in std_logic;
                mux_out : out std_logic
        );
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal Sel : std_logic := '0';
        signal In0 : std_logic := '0';
        signal In1 : std_logic := '0';
        signal mux_out : std_logic := '0';
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux2_1bit
    Port Map(
        Sel => Sel,
        In0 => In0,
        In1 => In1,
        mux_out => mux_out
    );
    
simulation_process :process
begin
        
        In0 <= '0';
        In1 <= '1';
        
        --Select line 1 and send '1' to output line mux_out
        Sel <= '0';
        wait for 1ns;
        
        --Select line 0 and send '0' to output line mux_out
        Sel <= '1';
        wait for 1ns;
     
     end process;
    
end Behavioral;
