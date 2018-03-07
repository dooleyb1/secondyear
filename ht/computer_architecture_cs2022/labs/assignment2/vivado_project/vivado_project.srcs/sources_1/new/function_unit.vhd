library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity function_unit is
Port ( 
	A : in std_logic_vector(15 downto 0);
	B : in std_logic_vector(15 downto 0);
	FS : in std_logic_vector(4 downto 0);
	V : out std_logic;
	C : out std_logic;
	N : out std_logic;
	Z : out std_logic;	
	F : out std_logic_vector(15 downto 0));
end function_unit;

architecture Behavioral of function_unit is
-- components

	-- 16bit Arithmetic Logic Unit (ALU)
	COMPONENT alu_16bit
	PORT(
		A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit ALU
   		B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit ALU
   		Gsel: in std_logic_vector(3 downto 0);     -- FS control input of the 16-bit ALU 
	   	F: out std_logic_vector(15 downto 0);      -- 16-bit data output of the 16-bit ALU 
	   	V : out std_logic;                         -- Overflow Flag out
	   	C : out std_logic;                         -- Carry Flag out
	   	N : out std_logic;                         -- Negative Flag out
	   	Z : out std_logic                          -- Zero Flag Out
		);
	END COMPONENT;
	
	
	-- 16bit Shifter
	COMPONENT shifter_16bit
	PORT(
		B : in STD_LOGIC_VECTOR (15 downto 0);
		FS : in STD_LOGIC_VECTOR (4 downto 0);
		Lr : in STD_LOGIC;
		Ll : in STD_LOGIC;
		H : out STD_LOGIC_VECTOR (15 downto 0)
		);
	END COMPONENT;
	
	-- 2 to 1 line multiplexer
	COMPONENT mux2_16bit
	PORT(
		In0 : IN std_logic_vector(15 downto 0);
		In1 : IN std_logic_vector(15 downto 0);
		s : IN std_logic;
		Z : OUT std_logic_vector(15 downto 0)
		);
	END COMPONENT;
	
	-- signals
	signal ALU_out, shifter_out, mux_out : STD_LOGIC_VECTOR(15 downto 0);
	
	begin
	-- port maps ;-)
	
	-- Arithmetic Logic Unit (ALU)
	alu: alu_16bit PORT MAP(
		A => A,
		B => B,
		Gsel(0) => FS(0),
		Gsel(1) => FS(1),
		Gsel(2) => FS(2),
		Gsel(3) => FS(3),
		F => ALU_out,
		V => V,
		C => C,
		N => N, 
		Z => Z);
	
	-- 16 Bit Shifter
	shifter: shifter_16bit PORT MAP(
		B => B,
		FS => FS,
		Lr => '0',
		Ll => '0',
		H => shifter_out
	);
	
	-- 2 to 1 mux
	MUXF: mux2_16bit PORT MAP(
		In0 => ALU_out,
		In1 => shifter_out,
		s => FS(4),
		Z => F
	);
  

end Behavioral;
