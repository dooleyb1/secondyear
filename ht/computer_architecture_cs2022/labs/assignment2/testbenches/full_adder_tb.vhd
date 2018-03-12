library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity full_adder_tb is
--  Port ( );
end full_adder_tb;

architecture Behavioral of full_adder_tb is
    -- declare component to test
    component full_adder is
        Port ( 
		    A : in STD_LOGIC;
			B : in STD_LOGIC;
			Cin : in STD_LOGIC;
			S : out STD_LOGIC;
			Cout : out STD_LOGIC
		);
    end component;
    
    -- signals for tests (initialise to 0)
     
    --inputs    
    signal A : std_logic := '0';
    signal B : std_logic := '0';
    signal Cin : std_logic := '0';
    
    --outputs
    signal S : std_logic := '0';  
    signal Cout : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: full_adder
    Port Map(
        A => A,
        B => B,
        Cin => Cin,
        S => S,
        Cout => Cout
    );
    
simulation_process :process
begin
        --Check that 0+0+ Cin(1)... S=1, Cout=0 **
        wait for 1ns;
       	A <= '0';
       	B <= '0';
       	Cin <= '1';
        wait for 1ns;
        
        --Check that 0+1+ Cin(0)... S=1, Cout=0 **
       	A <= '0';
       	B <= '1';
       	Cin <= '0';
        wait for 1ns;
        
       --Check that 0+1+ Cin(1)... S=0, Cout=1 **
       	A <= '0';
       	B <= '1';
       	Cin <= '1';
        wait for 1ns;
        
        --Check that 1+0+ Cin(0)... S=1, Cout=0 **
       	A <= '1';
       	B <= '0';
       	Cin <= '0';
        wait for 1ns;
        
        --Check that 1+0+ Cin(1)... S=0, Cout=1 **
       	A <= '1';
       	B <= '0';
       	Cin <= '1';
        wait for 1ns;
        
        --Check that 1+1+ Cin(0)... S=0, Cout=1 **
       	A <= '1';
       	B <= '1';
       	Cin <= '0';
        wait for 1ns;
        
        --Check that 1+1+ Cin(1)... S=1, Cout=1 **
       	A <= '1';
       	B <= '1';
       	Cin <= '1';
        wait for 1ns;

     end process;
    
end Behavioral;
