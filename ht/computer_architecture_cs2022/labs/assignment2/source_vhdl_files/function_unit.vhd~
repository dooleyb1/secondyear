library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity function_unit is
Port ( A : in std_logic_vector(15 downto 0);
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
	COMPONENT 16_bit_ALU
	PORT(
		A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit ALU
   		B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit ALU
   		ALUctrl: in std_logic_vector(4 downto 0);  -- ALUctrl control input of the 16-bit ALU 
	   	ALUOUT: out std_logic_vector(15 downto 0); -- 16-bit data output of the 16-bit ALU 
	   	V : out std_logic;                         -- Overflow Flag out
	   	C : out std_logic;                         -- Carry Flag out
	   	N : out std_logic;                         -- Negative Flag out
	   	Z : out std_logic
		);
	END COMPONENT;
	
	
	-- 16bit Shifter
	COMPONENT 16_bit_shifter
	PORT(
		B : in STD_LOGIC_VECTOR (15 downto 0);
		Hsel : in STD_LOGIC_VECTOR (1 downto 0);
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
	signal vflag, cflag, nflag, zflag, Ll, Lr, MDsel  : std_logic;
	signal ABUS, BBUS, G, H,  : std_logic_vector(15 downto 0);
	signal Hsel : std_logic_vector(3 downto 0);
	signal Gsel : std_logic_vector(1 downto 0);
		
	begin
	-- port maps ;-)
	
	-- Arithmetic Logic Unit (ALU)
	alu: 16_bit_ALU PORT MAP(
		A => ABUS,
		B => BBUS,
		ALUctrl => Gsel,
		ALUOUT => G,
		V => vlag,
		C => cflag,
		N => nflag, 
		Z => zflag,
	);
	
	-- 16 Bit Shifter
	shifter: 16_bit_shifter PORT MAP(
		B => BBUS,
		HSEL => Hsel,
		Lr => Lr,
		Ll => Ll,
		H => H
	);
	
	-- 2 to 1 mux
	MUXF: mux2_16bit PORT MAP(
		In0 => G,
		In1 => H,
		s => MDsel,
		Z => reg2_q
	);

end Behavioral;
