
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity logic_unit_b is
	Port(
		B : in STD_LOGIC_VECTOR(15 downto 0);
		S_in : in STD_LOGIC_VECTOR(1 downto 0);
		Y_out : out STD_LOGIC_VECTOR(15 downto 0)
	);
end logic_unit_b;

    architecture Behavioral of logic_unit_b is
	
	--mux 2-1 component
	Component mux2_1bit
	Port(
		Sel, In0, In1 : in STD_LOGIC;
        mux_out : out STD_LOGIC
	);
	End Component;

begin
	mux00: mux2_1bit PORT MAP(
		Sel => B(0),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(0)
	);
	
	mux01: mux2_1bit PORT MAP(
		Sel => B(1),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(1)
	);
	
	mux02: mux2_1bit PORT MAP(
		Sel => B(2),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(2)
	);
	
	mux03: mux2_1bit PORT MAP(
		Sel => B(3),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(3)
	);
	
	mux04: mux2_1bit PORT MAP(
		Sel => B(4),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(4)
	);
	
	mux05: mux2_1bit PORT MAP(
		Sel => B(5),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(5)
	);
	
	mux06: mux2_1bit PORT MAP(
		Sel => B(6),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(6)
	);
	
	mux07: mux2_1bit PORT MAP(
		Sel => B(7),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(7)
	);
	
	mux08: mux2_1bit PORT MAP(
		Sel => B(8),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(8)
	);
	
	mux09: mux2_1bit PORT MAP(
		Sel => B(9),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(9)
	);
	
	mux10: mux2_1bit PORT MAP(
		Sel => B(10),
		In0 => S_in(0),
		In1 => S_in(1),
		mux_out => Y_out(10)
	);

end Behavioral;
