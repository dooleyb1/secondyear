library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux2_8bit_tb is
--  Port ( );
end mux2_8bit_tb;

architecture Behavioral of mux2_8bit_tb is

    -- declare component to test
    component mux2_8bit is
        Port ( In0 : in std_logic_vector(7 downto 0);
            In1 : in std_logic_vector(7 downto 0);
            Sel : in std_logic;
            mux_out : out std_logic_vector(7 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal In0 : std_logic_vector(7 downto 0):= x"00";
    signal In1 : std_logic_vector(7 downto 0):= x"00";
    signal Sel : std_logic := '0';
    
    --outputs
    signal mux_out : std_logic_vector(7 downto 0):= x"00";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: mux2_8bit
    Port Map(
        In0 => In0,
        In1 => In1,
        Sel => Sel,
        mux_out => mux_out
    );
    
simulation_process :process
begin
        
        In0 <= x"FF";
        In1 <= x"AA";
        
        --Select line 0 (data input) and send 0x00FF to output line Z
        Sel <= '0';
        wait for 1ns;
        
        --Select line 1 (register data) and send 0x00AA to output line Z
        Sel <= '1';
        wait for 1ns;
     
     end process;
    
end Behavioral;
