-------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity logic_unit_ab is
	Port(
		a_logic_in: in STD_LOGIC_VECTOR(15 downto 0);
		b_logic_in : in STD_LOGIC_VECTOR(15 downto 0);
		select_in : in STD_LOGIC_VECTOR(1 downto 0);
		output_ab : out STD_LOGIC_VECTOR(15 downto 0)
	);
end logic_unit_ab;

architecture Behavioral of logic_unit_ab is

begin
	output_ab <= 	(a_logic_in and b_logic_in) after 1ns when select_in = "00" else     --A and B
					(a_logic_in or b_logic_in) after 1ns when select_in = "01" else      --A or B
					(a_logic_in xor b_logic_in) after 1ns when select_in = "10" else     --A xor B
					(not (a_logic_in)) after 1ns;                                        --not A

end Behavioral;
