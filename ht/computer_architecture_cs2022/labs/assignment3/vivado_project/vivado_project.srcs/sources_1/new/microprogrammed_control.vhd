library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity microprogrammed_control is
	Port (
	    --Flags Vector [V,C,N,Z]
		Vflag : in std_logic;
		Cflag : in std_logic;
		Nflag : in std_logic;
		Zflag : in std_logic;
		
		memory_instruction : in std_logic_vector(15 downto 0);
		
		Clk : in std_logic;
		reset : in std_logic;
		
		-- MW to Memory
		MWout : out std_logic;
		-- MM to Mux M
		MMout : out std_logic;
		-- MD to Mux D
		MDout : out std_logic;
		-- MB to Mux B
		MBout : out std_logic;

		-- FS to Function Unit
		FSout : out std_logic_vector(4 downto 0);
		-- RW to Register File (Read/Write)
		RWout : out std_logic;

		-- TB to Register File (Temp B)
		TBout : out std_logic;
		-- TA to Register File (Temp A)
		TAout : out std_logic;
		-- TD to Register File (Temp D)
		TDout : out std_logic;

		--DR to Register File (OR'd with TD)
		DRout : out std_logic_vector(2 downto 0);
		--SA to Register File (OR'd with TA)
		SAout : out std_logic_vector(2 downto 0);
		--SB to Register File (OR'd with TB)
		SBout : out std_logic_vector(2 downto 0);

		-- PC_OUT to Mux M
		PCout : out std_logic_vector(15 downto 0)
	);
end microprogrammed_control;

architecture Behavioral of microprogrammed_control is
-- components

	-- Control Memory (256 x 28)
	component control_memory
		Port(
			-- IN_CAR input from CAR
			IN_CAR : in std_logic_vector(7 downto 0);	
				
			-- MW to Memory
			MW : out std_logic;
			-- MM to Mux M
			MM : out std_logic;
			-- RW to Register File (Read/Write)
			RW : out std_logic;
			-- MD to Mux D
			MD : out std_logic;
			-- FS to Function Unit
			FS : out std_logic_vector(4 downto 0);
			-- MB to Mux B
			MB : out std_logic;
			-- TB to Register File (Temp B)
			TB : out std_logic;
			-- TA to Register File (Temp A)
			TA : out std_logic;
			-- TD to Register File (Temp D)
			TD : out std_logic;
			-- PL to Program Counter 
			PL : out std_logic;
			-- PI to Program Counter
			PI : out std_logic;
			-- IL to Instruction Register (IR)
			IL : out std_logic;
			-- MC to Mux C
			MC : out std_logic;
			-- MS to Mux S
			MS : out std_logic_vector(2 downto 0);
			-- NA to Mux C (Next Address)
			NA : out std_logic_vector(7 downto 0)
		);
	end component;
	
	-- Control Access Register (CAR)
	component CAR
		Port(
			CAR_IN : in std_logic_vector(7 downto 0);
			RESET : in std_logic;
			CLK : in std_logic;
		    FLAGS_IN : in std_logic;
		    CAR_OUT : out std_logic_vector(7 downto 0)
		);
	end component;
	
	-- Instruction Register (IR)
	component IR
		Port(
			IR_IN : in std_logic_vector(15 downto 0);
		    IL : in std_logic;
		    CLK : in std_logic;
		    OPCODE : out std_logic_vector(6 downto 0);
            DR : out std_logic_vector(2 downto 0);
	        SA : out std_logic_vector(2 downto 0);
	        SB : out std_logic_vector(2 downto 0)
		);
	end component;
	
	-- Program Counter (PC)
	component PC
		Port(
			PC_IN : in std_logic_vector(15 downto 0);
			Clk : in std_logic;
		    PL : in std_logic;
		    PI : in std_logic;
		    PC_OUT : out std_logic_vector(15 downto 0)
		);
	end component;
	
	--Extend
	component Extend
        Port ( 
            DR_SB : in std_logic_vector(5 downto 0);
            Ext : out std_logic_vector(15 downto 0)
        );
    end component;

	--MUX S (8 to 1)
	--Flags Vector [V,C,N,Z]
	component mux8_1bit
		Port(
			In0 : in std_logic;
			In1 : in std_logic;
			In2 : in std_logic;
			In3 : in std_logic;
			In4 : in std_logic;
			In5 : in std_logic;
			In6 : in std_logic;
			In7 : in std_logic;
			S0 : in std_logic;
			S1 : in std_logic;
			S2 : in std_logic;
			Z : out std_logic
		);
	end component;

	--MUX C (2 to 1)
	component mux2_8bit
		Port(
			Sel : in std_logic;
			In0 : in std_logic_vector(7 downto 0);
			In1 : in std_logic_vector(7 downto 0);
			mux_out : out std_logic_vector(7 downto 0)
		);
	end component;

	-- signals
	signal PC_in, PC_out: std_logic_vector(15 downto 0);
	signal NA_out, MUXC_out, CAR_out, extended_IR : std_logic_vector(7 downto 0);
	signal IR_OPCODE_out : std_logic_vector(6 downto 0); 
	signal FS_out : std_logic_vector(4 downto 0);
	signal DR_out, SA_out, SB_out, MS_out : std_logic_vector(2 downto 0);
	signal IL_out, MC_out, MUXS_out, MW_out, MM_out, RW_out, MD_out, MB_out, TB_out, TA_out, TD_out, PL_out, PI_out,
	           not_Z, not_C : std_logic;
		
	begin
	-- port Maps ;-)
	
	-- Program Counter (PC)
	P_C : PC Port Map(
		PC_IN => PC_in,
		PL => PL_out,
		PI => PI_out,
		Clk => Clk,
		PC_OUT => PC_out
	);	

	-- Instruction Register (Overall System)
	I_R : IR Port Map(
		IR_IN => memory_instruction,
		IL => IL_out,
		OPCODE => IR_OPCODE_out,
		CLK => Clk,
		DR => DR_out,
		SA => SA_out,
		SB => SB_out
	);
	
	-- MUX C
	MUXC: mux2_8bit Port Map(
		In0 => NA_out,
		In1(0) => IR_OPCODE_out(0),
        In1(1) => IR_OPCODE_out(1),
        In1(2) => IR_OPCODE_out(2),
        In1(3) => IR_OPCODE_out(3),
        In1(4) => IR_OPCODE_out(4),
        In1(5) => IR_OPCODE_out(5),
        In1(6) => IR_OPCODE_out(6),
        In1(7) => '0',
		Sel => MC_out,
		mux_out => MUXC_out
	);
	
	-- Control Access Register (CAR)
	CA_R: CAR Port Map(
		CAR_IN => MUXC_out,
		FLAGS_IN => MUXS_out,
		RESET => reset,
		CLK => Clk,
		CAR_OUT => CAR_out
	);

	-- MUX S
    --Flags Vector [0,1,2,3]
    --Flags Vector [C,V,Z,N]
    MUXS : mux8_1bit Port Map(
        In0 => '0',
        In1 => '1',
        In2 => CFlag,
        In3 => VFlag,
        In4 => ZFlag,
        In5 => NFlag,
        In6 => not_C,
        In7 => not_Z,
        S0 => MS_out(0),
        S1 => MS_out(1),
        S2 => MS_out(2),
        Z => MUXS_out
    );
	
	-- Control Memory (256 x 28)
	control_mem: control_memory Port Map(
		IN_CAR => CAR_out,	
		MW => MW_out,
		MM => MM_out,
		RW => RW_out,
		MD => MD_out,
		FS => FS_out,
		MB => MB_out,
		TB => TB_out,
		TA => TA_out,
		TD => TD_out,
		PL => PL_out,
		PI => PI_out,
		IL => IL_out,
		MC => MC_out,
		MS => MS_out,
		NA => NA_out
	);
	
	--Extend
	ext0: Extend Port Map(
        DR_SB(2 downto 0) => SB_out,
        DR_SB(5 downto 3) => DR_out,
        Ext => PC_out
    );
	 
	not_Z <= not Vflag;
	not_C <= not Nflag;
	 
	MWout <= MW_out;
    MMout <= MM_out;
    MBout <= MB_out;
    MDout <= MD_out;
    
    RWout <= RW_out;
    FSout <= FS_out;
    
    TBout <= TB_out;
    TAout <= TA_out;
    TDout <= TD_out;
	 
	DRout <= DR_out;
	SAout <= SA_out;
	SBout <= SB_out;
	
	PCout <= PC_out;
	 
end Behavioral;
