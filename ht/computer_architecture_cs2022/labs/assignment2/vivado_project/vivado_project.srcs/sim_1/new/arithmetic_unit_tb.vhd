library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity arithmetic_unit_tb is
--  Port ( );
end arithmetic_unit_tb;

architecture Behavioral of arithmetic_unit_tb is
    -- declare component to test
    component arithmetic_unit is
        Port ( 
		    A: in std_logic;
		    B: in std_logic;
		    Cin: in std_logic;
		    Cout: out std_logic;
		    S0: in std_logic;
		    S1: in std_logic;
		    Z: out std_logic
		);
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs
    signal A : std_logic := '0';
    signal B : std_logic := '0';
    signal Cin : std_logic := '0'; 
    signal S0 : std_logic := '0'; 
    signal S1 : std_logic := '0'; 
        
     --outputs
     signal Cout : std_logic := '0';
     signal Z : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: arithmetic_unit
    Port Map(
        A => A,
        B => B,
        Cin => Cin,
        S0 => S0,
        S1 => S1,
        Cout => Cout,
        Z => Z
    );
    
simulation_process :process
begin
        --Test F=A -> Z = '1', Cout = '0' **
       	A <= '1';
       	B <= '0';
       	Cin <= '0';
       	S0 <= '0';
       	S1 <= '0';
        wait for 2ns;
        
        --Test F=A+1 -> Z = '0', Cout = '1' **
        A <= '1';
        B <= '0';
        Cin <= '1';
        S0 <= '0';
        S1 <= '0';
        wait for 2ns;
        
        --Test F=A+B -> Z = '0', Cout = '1' **
        A <= '1';
        B <= '1';
        Cin <= '0';
        S0 <= '1';
        S1 <= '0';
        wait for 2ns;
        
        --Test F=A+B+1 -> Z = '1', Cout = '1' **
        A <= '1';
        B <= '1';
        Cin <= '1';
        S0 <= '1';
        S1 <= '0';
        wait for 2ns;
        
        --Test F=A+B' -> Z = '1', Cout = '0' **
        A <= '1';
        B <= '1';
        Cin <= '0';
        S0 <= '0';
        S1 <= '1';
        wait for 2ns;
        
        --Test F=A+B'+1 -> Z = '0', Cout = '1'  **
        A <= '1';
        B <= '1';
        Cin <= '1';
        S0 <= '0';
        S1 <= '1';
        wait for 2ns;

        --Test F=A-1 -> Z = '0', Cout = '1' **
        A <= '1';
        B <= '0';
        Cin <= '0';
        S0 <= '1';
        S1 <= '1';
        wait for 2ns;
        
        --Test F=A -> Z = '1', Cout = '1' **
        A <= '1';
        B <= '0';
        Cin <= '1';
        S0 <= '1';
        S1 <= '1';
        wait for 2ns;
        
        --Test all 0's -> Z = '0', Cout = '0' **
        A <= '0';
        B <= '0';
        Cin <= '0';
        S0 <= '0';
        S1 <= '0';
        wait for 2ns;
        
     end process;
    
end Behavioral;
