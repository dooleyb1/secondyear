library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity microprogrammed_control is
	Port (
		Vflag : in std_logic;
		Cflag : in std_logic;
		Nflag : in std_logic;
		Zflag : in std_logic;
		
		memory_instruction : in std_logic_vector(15 downto 0);
		
		Clk : in std_logic;
		reset : in std_logic;
		
		-- MW to Memory
		MW : out std_logic;
		-- MM to Mux M
		MM : out std_logic;
		-- MD to Mux D
		MD : out std_logic;
		-- MB to Mux B
		MB : out std_logic;

		-- FS to Function Unit
		FS : out std_logic_vector(4 downto 0);
		-- RW to Register File (Read/Write)
		RW : out std_logic;

		-- TD to Register File (Temp D)
		TD : out std_logic;
	    -- TA to Register File (Temp A)
        TA : out std_logic;
		-- TB to Register File (Temp B)
        TB : out std_logic;

		--DR to Register File (OR'd with TD)
		DR : out std_logic_vector(2 downto 0);
		--SA to Register File (OR'd with TA)
		SA : out std_logic_vector(2 downto 0);
		--SB to Register File (OR'd with TB)
		SB : out std_logic_vector(2 downto 0);

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
		    A : in std_logic;
			B : in std_logic_vector(7 downto 0);
			RESET : in std_logic;
			CLK : in std_logic;
		    Z : out std_logic_vector(7 downto 0)
		);
	end component;
	

    --MUX C (2 to 1)
    component mux2_8bit
        Port(
            In0 : in std_logic_vector(7 downto 0);
            In1 : in std_logic_vector(7 downto 0);
            Sel : in std_logic;
            mux_out : out std_logic_vector(7 downto 0)
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
	
	--MUX S (8 to 1)
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
	
	-- Program Counter (PC)
	component PC
		Port(
			PC_IN : in std_logic_vector(15 downto 0);
		    PL : in std_logic;
		    PI : in std_logic;
		    Clk : in std_logic;
		    RESET : in std_logic;
		    PC_OUT : out std_logic_vector(15 downto 0)
		);
	end component;
	
	--Extend
	component Extend
        Port ( 
            DR_SA_SB : in std_logic_vector(8 downto 0);
            Ext : out std_logic_vector(15 downto 0)
        );
    end component;

	-- signals
	signal PL, PI, IL, MC, MUXS_OUT, notC, notZ : std_logic;
    signal MS, DR_PC, SA_PC, SB_PC : std_logic_vector(2 downto 0);
    signal NA, IN_CAR, CON_IN, MUXC_OUT : std_logic_vector(7 downto 0);
    signal Opcode : std_logic_vector(6 downto 0);
    signal PCin : std_logic_vector(15 downto 0);
		
	begin
	-- port Maps ;-)

	-- Control Memory (256 x 28)
	control_mem: control_memory Port Map(
		IN_CAR => CON_IN,	
		MW => MW,
		MM => MM,
		RW => RW,
		MD => MD,
		FS => FS,
		MB => MB,
		TB => TB,
		TA => TA,
		TD => TD,
		PL => PL,
		PI => PI,
		IL => IL,
		MC => MC,
		MS => MS,
		NA => NA
	);	
	
    -- Control Access Register (CAR)
    CA_R: CAR Port Map(
        A => MUXS_out,
        B => MUXC_out,
        RESET => reset,
        CLK => Clk,
        Z => CON_IN
    );

	-- MUX C
	MUXC: mux2_8bit Port Map(
		In0 => NA,
		In1(0) => Opcode(0),
        In1(1) => Opcode(1),
        In1(2) => Opcode(2),
        In1(3) => Opcode(3),
        In1(4) => Opcode(4),
        In1(5) => Opcode(5),
        In1(6) => Opcode(6),
        In1(7) => '0',
		Sel => MC,
		mux_out => MUXC_out
	);
	
	-- Instruction Register (Overall System)
    I_R : IR Port Map(
        IR_IN => memory_instruction,
        IL => IL,
        OPCODE => Opcode,
        CLK => Clk,
        DR => DR_PC,
        SA => SA_PC,
        SB => SB_PC
    );

	-- MUX S
    MUXS : mux8_1bit Port Map(
        In0 => '0',
        In1 => '1',
        In2 => CFlag,
        In3 => VFlag,
        In4 => ZFlag,
        In5 => NFlag,
        In6 => notC,
        In7 => notZ,
        S0 => MS(0),
        S1 => MS(1),
        S2 => MS(2),
        Z => MUXS_out
    );

	--Extend
	ext0: Extend Port Map(
        DR_SA_SB(2 downto 0) => SB_PC,
        DR_SA_SB(5 downto 3) => SA_PC,
        DR_SA_SB(8 downto 6) => DR_PC,
        Ext => PCout
    );

	-- Program Counter (PC)
	P_C : PC Port Map(
		PC_IN => PCin,
		PL => PL,
		PI => PI,
		RESET => reset,
		Clk => Clk,
		PC_OUT => PCout
	);	
	
	DR <= DR_PC;
	SA <= SA_PC;
	SB <= SB_PC;
	 
end Behavioral;
