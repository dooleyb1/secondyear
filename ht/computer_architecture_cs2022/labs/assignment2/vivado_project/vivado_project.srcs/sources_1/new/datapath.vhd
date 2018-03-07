library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity datapath is
	Port (
		data_in : in std_logic_vector(15 downto 0);
		constant_in : in std_logic_vector(15 downto 0);
		a_address : in std_logic_vector(2 downto 0);
		b_address : in std_logic_vector(2 downto 0);
		d_address : in std_logic_vector(2 downto 0);
		FS : in std_logic_vector(4 downto 0);
		write : in std_logic;
		v_out : out std_logic;
		c_out : out std_logic;
		n_out : out std_logic;
		z_out : out std_logic;
		mb_select : in std_logic;
		md_select : in std_logic;
		bus_a_adr_out : out std_logic_vector(15 downto 0);
		bus_b_data_out : out std_logic_vector(15 downto 0);
		f_data_out : out std_logic_vector(15 downto 0)
		--reg_0_data_out : out std_logic_vector(15 downto 0);
		--reg_1_data_out : out std_logic_vector(15 downto 0);
		--reg_2_data_out : out std_logic_vector(15 downto 0);
		--reg_3_data_out : out std_logic_vector(15 downto 0);
		--reg_4_data_out : out std_logic_vector(15 downto 0);
		--reg_5_data_out : out std_logic_vector(15 downto 0);
		--reg_6_data_out : out std_logic_vector(15 downto 0);
		--reg_7_data_out : out std_logic_vector(15 downto 0)
	);
end datapath;

architecture Behavioral of datapath is
-- components

	-- 16 Bit Register File
	component register_file
		Port(
			a_sel: in std_logic_vector(2 downto 0);	
			b_sel : in std_logic_vector(2 downto 0);
			d_sel : in std_logic_vector(2 downto 0);
			load : in std_logic;
			data : in std_logic_vector(15 downto 0);
			a_out : out std_logic_vector(15 downto 0);
			b_out : out std_logic_vector(15 downto 0);
			reg0out : out std_logic_vector(15 downto 0);
			reg1out : out std_logic_vector(15 downto 0);
			reg2out : out std_logic_vector(15 downto 0);
			reg3out : out std_logic_vector(15 downto 0);
			reg4out : out std_logic_vector(15 downto 0);
			reg5out : out std_logic_vector(15 downto 0);
			reg6out : out std_logic_vector(15 downto 0);
			reg7out : out std_logic_vector(15 downto 0)
		);
	end component;
	
	-- 2 to 1 line multiplexer
	component mux2_16bit
		Port(
			In0 : in std_logic_vector(15 downto 0);
			In1 : in std_logic_vector(15 downto 0);
			s : in std_logic;
			Z : out std_logic_vector(15 downto 0)
		);
	end component;
	
	-- ALU + Shifter Function Unit
	component function_unit
		Port(
			A : in std_logic_vector(15 downto 0);
			B : in std_logic_vector(15 downto 0);
			FS : in std_logic_vector(4 downto 0);
			V : out std_logic;
			C : out std_logic;
			N : out std_logic;
			Z : out std_logic;	
			F : out std_logic_vector(15 downto 0)
		);
	end component;
	
	-- signals
	signal a_data, b_data, mb_out, f_out, md_out, reg0out, reg1out, reg2out, reg3out, reg4out, 
	           reg5out, reg6out, reg7out: std_logic_vector(15 downto 0);
		
	begin
	-- port Maps ;-)
	
	-- Register File
	reg_file: register_file Port Map(
		a_sel => a_address,	
		b_sel => b_address,
		d_sel => d_address,
		load => write,
		data => md_out,
		a_out => a_data,
		b_out => b_data,
		reg0out => reg0out,
        reg1out => reg1out,
        reg2out => reg2out,
        reg3out => reg3out,
        reg4out => reg4out,
        reg5out => reg5out,
        reg6out => reg6out,
        reg7out => reg7out 
	);
	
	-- MUX B
	mux_b: mux2_16bit Port Map(
		In0 => b_data,
		In1 => constant_in,
		s => mb_select,
		Z => mb_out
	);
	
	-- Function Unit
	funct_unit: function_unit Port Map(
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
	mux_d: mux2_16bit Port Map(
		In0 => f_out,
		In1 => data_in,
		s => md_select,
		Z => md_out
	);
	
	bus_a_adr_out <= a_data;
	bus_b_data_out <= mb_out;
	f_data_out <= f_out;
	
	reg_0_data_out <= reg0out;
	reg_1_data_out <= reg1out;
	reg_2_data_out <= reg2out;
	reg_3_data_out <= reg3out;
	reg_4_data_out <= reg4out;
	reg_5_data_out <= reg5out;
	reg_6_data_out <= reg6out;
	reg_7_data_out <= reg7out;
	 
end Behavioral;
