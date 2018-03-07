library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity mux2_1bit is
	Port(
			Sel, In0, In1 : in STD_LOGIC;
			mux_out : out STD_LOGIC
		);
end mux2_1bit;

architecture Behavioral of mux2_1bit is

begin
	mux_out <= 	In0 after 1ns when Sel = '0' else
				In1 after 1ns when Sel = '1' else
				'0' after 1ns;

end Behavioral;