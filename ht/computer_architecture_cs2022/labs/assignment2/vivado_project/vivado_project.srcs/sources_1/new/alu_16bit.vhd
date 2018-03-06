library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- 16-bit ALU 
entity alu_16bit is
 port (
   A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit ALU
   B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit ALU
   ALUctrl: in std_logic_vector(4 downto 0);  -- ALUctrl control input of the 16-bit ALU 
   F: out std_logic_vector(15 downto 0);      -- 16-bit data output of the 16-bit ALU 
   V : out std_logic;                         -- Overflow Flag out
   C : out std_logic                          -- Carry Flag out
   );
end alu_16bit;

architecture Behavioral of alu_16bit is

--16 bit ripple adder component declaration
component ripple_adder_16bit
Port (
    A : in STD_LOGIC_VECTOR (15 downto 0);
    B : in STD_LOGIC_VECTOR (15 downto 0);
    Cin : in STD_LOGIC;
    S : out STD_LOGIC_VECTOR (15 downto 0);
    Cout : out STD_LOGIC;
    V : out STD_LOGIC
    );
end component;

--Logic unit for and or xor not
component logic_unit_ab
Port(
	a_logic_in: in STD_LOGIC_VECTOR(15 downto 0);
	b_logic_in : in STD_LOGIC_VECTOR(15 downto 0);
	select_in : in STD_LOGIC_VECTOR(1 downto 0);
	output_ab : out STD_LOGIC_VECTOR(15 downto 0)
	);
end component;

--Logic unit b
component logic_unit_b is
Port(
	B : in STD_LOGIC_VECTOR(15 downto 0);
	S_in : in STD_LOGIC_VECTOR(1 downto 0);
	Y_out : out STD_LOGIC_VECTOR(15 downto 0)
	);
end component;

--Mux for end result
component mux2_16bit is
Port ( 
    In0 : in std_logic_vector(15 downto 0);
    In1 : in std_logic_vector(15 downto 0);
    s : in std_logic;
    Z : out std_logic_vector(15 downto 0));
end component;

--Initialise signals
signal logic_out, logic_out_ab, ripple_adder_out : STD_LOGIC_VECTOR(15 downto 0);

begin

	ripple_adder: ripple_adder_16bit PORT MAP(
			A => A,
			B => B,
			Cin => ALUctrl(0),
			Cout => C,
			V => V,
			S => ripple_adder_out
	);

    logic_unit_ab00: logic_unit_ab PORT MAP(
			a_logic_in => A,
			b_logic_in => B,
			select_in => ALUctrl(2 downto 1),
			output_ab => logic_out_ab
	);
	
	logic_unit_b00 : logic_unit_b PORT MAP(
			B => B,
			S_in => ALUctrl(2 downto 1),
			Y_out => logic_out
	);
	
	mux_2_16bit00: mux2_16bit PORT MAP(
			In0 => ripple_adder_out,
			In1 => logic_out_ab,
			s => ALUctrl(3),
			Z => F
	);
end Behavioral;
