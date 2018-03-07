library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
 
entity full_adder is
	Port ( 
		A : in std_logic;
		B : in std_logic;
		Cin : in std_logic;
		S : out std_logic;
		Cout : out std_logic
	);
end full_adder;
 
architecture Behavioral of full_adder is
    
    signal S0, S1, S2 : std_logic;
    
begin
	 --Break up into sub-sections
	 S0 <= (A xor B) after 1ns;
	 S1 <= (Cin and S0) after 1ns;
	 S2 <= (A and B) after 1ns;
	 S <= (S0 xor Cin) after 1ns;
	 Cout <= (S1 or S2) after 1ns;

end Behavioral;
