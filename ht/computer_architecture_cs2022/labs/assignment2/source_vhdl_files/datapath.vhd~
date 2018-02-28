library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity datapath is
Port (data_in : in std_logic_vector(15 downto 0);
	constant_in : in std_logic_vector(15 downto 0);
	a_address : in std_logic_vector(2 downto 0);
	b_address : in std_logic_vector(2 downto 0);
	d_address : in std_logic_vector(2 downto 0);
	FS : in std_logic_vector(4 downto 0);
	write : in std_logic;
	v_out : in std_logic;
	c_out : in std_logic;
	n_out : in std_logic;
	z_out : in std_logic;
	mb_select : in std_logic;
	md_select : in std_logic;
	bus_a_adr_out : out std_logic_vector(15 downto 0);
	bus_b_data_out : out std_logic_vector(15 downto 0));
end datapath;

architecture Behavioral of datapath is
-- components

	-- 16 Bit Register File
	COMPONENT register_file
	PORT(
	a_sel: in std_logic_vector(2 downto 0);	
	b_sel : in std_logic_vector(2 downto 0);
	d_sel : in std_logic_vector(2 downto 0);
	load : in std_logic;
	data : in std_logic_vector(15 downto 0);
	a_out : out std_logic_vector(15 downto 0)
	b_out : out std_logic_vector(15 downto 0));
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
	
	-- ALU + Shifter Function Unit
	COMPONENT function_unit
	PORT(
	A : in std_logic_vector(15 downto 0);
	B : in std_logic_vector(15 downto 0);
	FS : in std_logic_vector(4 downto 0);
	V : out std_logic;
	C : out std_logic;
	N : out std_logic;
	Z : out std_logic;	
	F : out std_logic_vector(15 downto 0));
	);
	END COMPONENT
	
	-- signals
	signal a_data, b_data, mb_out, f_out, md_out: std_logic_vector(15 downto 0);
		
	begin
	-- port maps ;-)
	
	-- Register File
	reg_file: register_file PORT MAP(
		a_sel => a_address,	
		b_sel => b_address,
		d_sel => d_address,
		load => write,
		data => md_out,
		a_out => a_data,
		b_out => b_data	
	);
	
	-- MUX B
	mux_b: mux2_16bit PORT MAP(
		In0 => b_data,
		In1 => constant_in,
		s => mb_select,
		Z => mb_out,
	);
	
	-- Function Unit
	function_unit: function_unit PORT MAP(
		A => a_data,
		B => mb_out,
		FS => FS,
		V => v_out,
		C => c_out,
		N => n_out,
		Z => z_out,	
		F => f_out
	);
	
	-- MUX D
	mux_d: mux2_16bit PORT MAP(
		In0 => f_out,
		In1 => data_in,
		s => md_select,
		Z => md_out,
	);
	
	bus_a_adr_out <= a_data;
	bus_b_data_out <= mb_out;
	 
end Behavioral;
