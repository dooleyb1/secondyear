library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity logic_unit_tb is
--  Port ( );
end logic_unit_tb;

architecture Behavioral of logic_unit_tb is
    -- declare component to test
    component logic_unit is
        Port ( 
		    S0: in std_logic;
		    S1: in std_logic;
		    A: in std_logic;
		    B: in std_logic;
		    G: out std_logic
		);
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs
    signal A : std_logic := '0';
    signal B : std_logic := '0';
    signal S0 : std_logic := '0'; 
    signal S1 : std_logic := '0'; 
        
    --outputs
    signal G : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: logic_unit
    Port Map(
        S0 => S0,
        S1 => S1,
        A => A,
        B => B,
        G => G
    );
    
simulation_process :process
begin
        --Test F= A and B -> G = '1'
       	A <= '1';
       	B <= '1';
       	S0 <= '0';
       	S1 <= '0';
        wait for 2ns;
        
        --Test F= A or B -> G = '1'
        A <= '1';
        B <= '0';
        S0 <= '1';
        S1 <= '0';
        wait for 2ns;
        
        --Test F= A xor B -> G = '1'
        A <= '0';
        B <= '1';
        S0 <= '0';
        S1 <= '1';
        wait for 2ns;
        
        --Test F= not A -> G = '0'
        A <= '1';
        B <= '0';
        S0 <= '1';
        S1 <= '1';
        wait for 2ns;
        
     end process;
    
end Behavioral;
