library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.std_logic_ARITH.ALL;
use IEEE.std_logic_UNSIGNED.ALL;
 
entity shifter_16bit is
	Port (
		B : in std_logic_vector (15 downto 0);
		FS : in std_logic_vector (4 downto 0);
		Lr : in std_logic;
		Ll : in std_logic;
		H : out std_logic_vector (15 downto 0)
	);
end shifter_16bit;
 
architecture Behavioral of shifter_16bit is
 
	-- Shifter VHDL Code Component Decalaration
	component shifter
		Port ( 
			In0 : in std_logic;
			In1 : in std_logic;
			In2 : in std_logic;
			s : in std_logic_vector(1 downto 0);
			Z : out std_logic
		);
	end component;

	signal Hsel : std_logic_vector(1 downto 0); 
 
begin

	Hsel<= "00" when FS = "10000" else
		   "01" when FS = "10100" else 
		   "10" when FS = "11000" else 
		   "11" after 1ns; 
	 
	-- Port Mapping Shifter 16 times
	S0: shifter Port Map(
			In0 => B(0),
			In1 => B(1),
			In2 => Ll,
			s => Hsel,
			Z => H(0)
		);
		
	S1: shifter Port Map(
			In0 => B(1),
			In1 => B(2),
			In2 => B(0),
			s => Hsel,
			Z => H(1)
		);
		
	S2: shifter Port Map(
			In0 => B(2),
			In1 => B(3),
			In2 => B(1),
			s => Hsel,
			Z => H(2)
		);
		
	S3: shifter Port Map(
			In0 => B(3),
			In1 => B(4),
			In2 => B(2),
			s => Hsel,
			Z => H(3)
		);

	S4: shifter Port Map(
			In0 => B(4),
			In1 => B(5),
			In2 => B(3),
			s => Hsel,
			Z => H(4)
		);
		
	S5: shifter Port Map(
			In0 => B(5),
			In1 => B(6),
			In2 => B(4),
			s => Hsel,
			Z => H(5)
		);
		
	S6: shifter Port Map(
			In0 => B(6),
			In1 => B(7),
			In2 => B(5),
			s => Hsel,
			Z => H(6)
		);
		
	S7: shifter Port Map(
			In0 => B(7),
			In1 => B(8),
			In2 => B(6),
			s => Hsel,
			Z => H(7)
		);
		
	S8: shifter Port Map(
			In0 => B(8),
			In1 => B(9),
			In2 => B(7),
			s => Hsel,
			Z => H(8)
		);
		
	S9: shifter Port Map(
			In0 => B(9),
			In1 => B(10),
			In2 => B(8),
			s => Hsel,
			Z => H(9)
		);

	S10: shifter Port Map(
			In0 => B(10),
			In1 => B(11),
			In2 => B(9),
			s => Hsel,
			Z => H(10)
		);
		
	S11: shifter Port Map(
			In0 => B(11),
			In1 => B(12),
			In2 => B(10),
			s => Hsel,
			Z => H(11)
		);
		
	S12: shifter Port Map(
			In0 => B(12),
			In1 => B(13),
			In2 => B(11),
			s => Hsel,
			Z => H(12)
		);
	 
	S13: shifter Port Map(
			In0 => B(13),
			In1 => B(14),
			In2 => B(12),
			s => Hsel,
			Z => H(13)
		);
		
	S14: shifter Port Map(
			In0 => B(14),
			In1 => B(15),
			In2 => B(13),
			s => Hsel,
			Z => H(14)
		);
		
	S15: shifter Port Map(
			In0 => B(15),
			In1 => Lr,
			In2 => B(14),
			s => Hsel,
			Z => H(15)
		);
	 
end Behavioral;
