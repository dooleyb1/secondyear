library IEEE;
use IEEE.std_logic_1164.ALL;

entity mux2_8bit is
	Port(
		Sel : in std_logic;
		In0 : in std_logic_vector(7 downto 0);
		In1 : in std_logic_vector(7 downto 0);
		mux_out : out std_logic_vector(7 downto 0)
	);
end mux2_8bit;

architecture Behavioral of mux2_8bit is

begin

	mux_out <= 	In0 after 1ns when Sel = '0' else
				In1 after 1ns when Sel = '1' else
				x"00" after 1ns;

end Behavioral;
