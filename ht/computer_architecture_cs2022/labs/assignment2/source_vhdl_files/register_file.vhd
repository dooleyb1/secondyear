library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity register_file is
Port ( a_sel: in std_logic_vector(2 downto 0);	
	b_sel : in std_logic_vector(2 downto 0);
	d_sel : in std_logic_vector(2 downto 0);
	load : in std_logic;
	data : in std_logic_vector(15 downto 0);
	a_out : out std_logic_vector(15 downto 0)
	b_out : out std_logic_vector(15 downto 0));
end register_file;

architecture Behavioral of register_file is
-- components

	-- 8 bit Register for register file
	COMPONENT reg16
	PORT(
		D : IN std_logic_vector(15 downto 0);
		load : IN std_logic;
		Q : OUT std_logic_vector(15 downto 0)
		);
	END COMPONENT;
	
	
	-- 3 to 8 Decoder (D Address Select)
	COMPONENT decoder_3to8
	PORT(
		des : IN std_logic_vector(2 downto 0);
		Q0 : OUT std_logic;
		Q1 : OUT std_logic;
		Q2 : OUT std_logic;
		Q3 : OUT std_logic;
		Q4 : OUT std_logic;
		Q5 : OUT std_logic;
		Q6 : OUT std_logic;
		Q7 : OUT std_logic
		);
	END COMPONENT;
	
	
	-- 8 to 1 line multiplexer (ABUS, BBUS)
	COMPONENT mux8_16bit
	PORT(
		In0 : IN std_logic_vector(15 downto 0);
		In1 : IN std_logic_vector(15 downto 0);
		In2 : IN std_logic_vector(15 downto 0);
		In3 : IN std_logic_vector(15 downto 0);
		In4 : IN std_logic_vector(15 downto 0);
		In5 : IN std_logic_vector(15 downto 0);
		In6 : IN std_logic_vector(15 downto 0);
		In7 : IN std_logic_vector(15 downto 0);
		src : IN std_logic_vector(2 downto 0);
		Z : OUT std_logic_vector(15 downto 0)
		);
	END COMPONENT;
	
	-- signals
	signal 	d_out0, d_out1, d_out2, d_out3, d_out4, d_out5, d_out6 ,d_out7   : std_logic;
	signal reg0_out, reg1_out, reg2_out, reg3_out, reg4_out, reg5_out, reg6_out, reg7_out : std_logic_vector(15 downto 0);
		
	begin
	-- port maps ;-)
	
	-- register 0
	reg00: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out0,
		Q => reg0_out
	);
	
	-- register 1
	reg01: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out1,
		Q => reg1_out
	);
	
	-- register 2
	reg02: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out2,
		Q => reg2_out
	);
	
	-- register 3
	reg03: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out3,
		Q => reg3_out
	);
	
	-- register 4
	reg04: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out4,
		Q => reg4_out
	);
	
	-- register 5
	reg05: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out5,
		Q => reg5_out
	);
	
	-- register 6
	reg06: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out6,
		Q => reg6_out
	);
	
	-- register 7
	reg07: reg16 PORT MAP(
		D => data,
		load => load,
		d_sel => d_out7,
		Q => reg7_out
	);
	
	-- Destination register decoder (D decoder)
	des_decoder_3to8: decoder_3to8 PORT MAP(
		des => d_sel,
		Q0 => d_out0,
		Q1 => d_out1,
		Q2 => d_out2,
		Q3 => d_out3,
		Q4 => d_out4,
		Q5 => d_out5,
		Q6 => d_out6,
		Q7 => d_out7
	);
	
	-- 8 to 1 source register multiplexer (ABUS mux)
	A_mux8_16bit: mux8_16bit PORT MAP(
		In0 => reg0_out,
		In1 => reg1_out,
		In2 => reg2_out,
		In3 => reg3_out,
		In4 => reg4_out,
		In5 => reg5_out,
		In6 => reg6_out,
		In7 => reg7_out,
		src => a_sel,
		Z => a_out
	);
	
	-- 8 to 1 source register multiplexer (BBUS mux)
	B_mux8_16bit: mux8_16bit PORT MAP(
		In0 => reg0_out,
		In1 => reg1_out,
		In2 => reg2_out,
		In3 => reg3_out,
		In4 => reg4_out,
		In5 => reg5_out,
		In6 => reg6_out,
		In7 => reg7_out,
		src => b_sel,
		Z => b_out
	);
	
	
end Behavioral;
