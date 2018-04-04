library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity datapath is
	Port (
		data_in : in std_logic_vector(15 downto 0);
		
		PC_in : in std_logic_vector(15 downto 0);
		
        SB: in std_logic_vector(2 downto 0);	        
		
		MB : in std_logic;        
        MD : in std_logic;
        MM : in std_logic;
        
        DA : in std_logic_vector(2 downto 0);
        AA : in std_logic_vector(2 downto 0);
        BA : in std_logic_vector(2 downto 0);
        
        TD : in std_logic;
        TA : in std_logic;
        TB : in std_logic;
        
		FS : in std_logic_vector(4 downto 0);
		RW : in std_logic;
		Clk : in std_logic;

		bus_a_adr_out : out std_logic_vector(15 downto 0);
		bus_b_data_out : out std_logic_vector(15 downto 0);
		f_data_out : out std_logic_vector(15 downto 0);
		
		reg_0_data_out : out std_logic_vector(15 downto 0);
		reg_1_data_out : out std_logic_vector(15 downto 0);
		reg_2_data_out : out std_logic_vector(15 downto 0);
		reg_3_data_out : out std_logic_vector(15 downto 0);
		reg_4_data_out : out std_logic_vector(15 downto 0);
		reg_5_data_out : out std_logic_vector(15 downto 0);
		reg_6_data_out : out std_logic_vector(15 downto 0);
		reg_7_data_out : out std_logic_vector(15 downto 0);
		
		Vflag : out std_logic_vector;
		Cflag : out std_logic_vector;
		Nflag : out std_logic_vector;
		Zflag : out std_logic_vector
	);
end datapath;

architecture Behavioral of datapath is
-- components

	-- 16 Bit Register File
	component register_file
		Port(
			DA: in std_logic_vector(3 downto 0);	
			AA : in std_logic_vector(3 downto 0);
			BA : in std_logic_vector(3 downto 0);
			
			Clk : in std_logic;
			RW : in std_logic;
			
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
			reg7out : out std_logic_vector(15 downto 0);
			tempout : out std_logic_vector(15 downto 0)
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
	
	--Zero Fill
	component Zero_fill
        Port ( 
            SB : in std_logic_vector(2 downto 0);
            zeroFill : out std_logic_vector(15 downto 0)
        );
        end component;
	
	-- signals
	signal a_data, b_data, mb_out, f_out, md_out, mm_out, reg0out, reg1out, reg2out, reg3out, reg4out, 
	           reg5out, reg6out, reg7out, tempout, conIn: std_logic_vector(15 downto 0);
	
	signal z_temp, c_temp, n_temp, v_temp : std_logic;
	
	begin
	-- port Maps ;-)
	
	-- Register File
	reg_file: register_file Port Map(
		AA => AA,	
		BA => BA,
		DA => DA,
		
		RW => RW,
		Clk => Clk,
		
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
        reg7out => reg7out,
        tempout => tempout 
	);
	
	-- MUX B
	mux_b: mux2_16bit Port Map(
		In0 => b_data,
		In1 => conIn,
		s => MB,
		Z => mb_out
	);
	
	-- Function Unit
	funct_unit: function_unit Port Map(
		A => a_data,
		B => mb_out,
		
		FS => FS,
		
		V => v_temp,
		C => c_temp,
		N => n_temp,
		Z => z_temp,	
		
		F => f_out
	);
	
	-- MUX D
	mux_d: mux2_16bit Port Map(
		In0 => f_out,
		In1 => data_in,
		s => MD,
		Z => md_out
	);
	
	-- MUX M
    mux_m: mux2_16bit Port Map(
        In0 => a_data,
        In1 => PC_in,
        s => MM,
        Z => mm_out
    );
    
    --Zero Fill
    z_fill: Zero_fill Port Map(
         SB => SB,
         zeroFill => ConIn
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
