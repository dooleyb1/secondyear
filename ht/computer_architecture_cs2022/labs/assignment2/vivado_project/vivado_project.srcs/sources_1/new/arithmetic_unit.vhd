library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity arithmetic_unit is
    Port ( 
       A: in std_logic;
       B: in std_logic;
       Cin: in std_logic;
       Cout: out std_logic;
       S0: in std_logic;
       S1: in std_logic;
       Z: out std_logic
    );
end arithmetic_unit;

architecture Behavioral of arithmetic_unit is

    component full_adder
        Port ( 
            A: in std_logic;
            B: in std_logic;
            Cin: in std_logic;
            Cout: out std_logic;
            S: out std_logic
        );
    end component;
    
    signal newInput : std_logic;

begin
    
    newInput <= (B and S0) or ((not B) and S1);
    
    AC: full_adder 
    	Port map (
    		A => A,
    		B => newInput,
    		Cin => Cin,
    		Cout => Cout,
    		S => Z
    	);
    
end Behavioral;
