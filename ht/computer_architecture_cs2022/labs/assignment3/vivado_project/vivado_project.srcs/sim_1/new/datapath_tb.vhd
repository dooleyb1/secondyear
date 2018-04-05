library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity datapath_tb is
--  Port ( );
end datapath_tb;

architecture Behavioral of datapath_tb is
    -- declare component to test
    component datapath is
        Port ( 
		data_in : in std_logic_vector(15 downto 0);
        
        PC_in : in std_logic_vector(15 downto 0);          
        
        SB: in std_logic_vector(2 downto 0);
        
        MB : in std_logic;        
        MD : in std_logic;
        MM : in std_logic;
        
        Dsel : in std_logic_vector(3 downto 0);
        Asel : in std_logic_vector(3 downto 0);
        Bsel : in std_logic_vector(3 downto 0);

        
        FS : in std_logic_vector(4 downto 0);
        RW : in std_logic;
        Clk : in std_logic;

        adr_out : out std_logic_vector(15 downto 0);
        data_out : out std_logic_vector(15 downto 0);
        
        reg_0_data_out : out std_logic_vector(15 downto 0);
        reg_1_data_out : out std_logic_vector(15 downto 0);
        reg_2_data_out : out std_logic_vector(15 downto 0);
        reg_3_data_out : out std_logic_vector(15 downto 0);
        reg_4_data_out : out std_logic_vector(15 downto 0);
        reg_5_data_out : out std_logic_vector(15 downto 0);
        reg_6_data_out : out std_logic_vector(15 downto 0);
        reg_7_data_out : out std_logic_vector(15 downto 0);
        reg_8_data_out : out std_logic_vector(15 downto 0);
        
        Vflag : out std_logic;
        Cflag : out std_logic;
        Nflag : out std_logic;
        Zflag : out std_logic
		);             
    end component;
    
    
    -- signals for tests (initialise to 0)
    --inputs 
    signal data_in, PC_in : std_logic_vector(15 downto 0) := x"0000";
    signal Dsel, Asel, Bsel : std_logic_vector(3 downto 0) := "0000";
    signal MB, MD, MM, RW, Clk : std_logic := '0';
    signal SB : std_logic_vector(2 downto 0) := "000";
    signal FS : std_logic_vector(4 downto 0) := "00000";
    
    
    --outputs
    signal Vflag, Cflag, Nflag, Zflag : std_logic := '0';
    signal adr_out : std_logic_vector(15 downto 0) := x"0000";
    signal data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_0_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_1_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_2_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_3_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_4_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_5_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_6_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_7_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_8_data_out : std_logic_vector(15 downto 0) := x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: datapath
    Port Map(
        data_in => data_in,
		
		PC_in => PC_in,

        SB => SB,

		MB => MB,
		MD => MD,
		MM => MM,
		
		Dsel => Dsel,
		Asel => Asel,
		Bsel => Bsel,
		
		FS => FS,
		RW => RW,
		Clk => Clk,
		
		adr_out => adr_out,
		data_out => data_out,
		
		reg_0_data_out => reg_0_data_out,
		reg_1_data_out => reg_1_data_out,
		reg_2_data_out => reg_2_data_out,
		reg_3_data_out => reg_3_data_out,
		reg_4_data_out => reg_4_data_out,
		reg_5_data_out => reg_5_data_out,
		reg_6_data_out => reg_6_data_out,
		reg_7_data_out => reg_7_data_out,	
		reg_8_data_out => reg_8_data_out,
		
		Vflag => Vflag,
		Cflag => Cflag, 
		Nflag => Nflag,
		Zflag => Zflag
    );
    
simulation_process :process
begin
    	--Test for the entire datapath as a whole
        
        ------------------------------------------------------------------
        
        --**LOADING REGISTER TESTS**--
        
        ------------------------------------------------------------------
        
        --Load all registers with values
        RW <= '1';
        MM <= '0';
        MD <= '1';
        MB <= '0';
        
        --reg0
        data_in <= x"00FA";
        Dsel <= "0000";
        Clk <= '1';
        wait for 50ns;
        
        Clk <= '0';
        wait for 50ns;
        
        --reg1
        data_in <= x"00FB";
        Dsel <= "0001";
        Clk <= '1';
        wait for 50ns;
 
        Clk <= '0';
        wait for 50ns;
 
        --reg2
        data_in <= x"00FC";
        Dsel <= "0010";
        Clk <= '1';
        wait for 50ns;
 
        Clk <= '0';
        wait for 50ns;
       
        --reg3
        data_in <= x"00FD";
        Dsel <= "0011";    
        Clk <= '1';
        wait for 50ns;
        
        Clk <= '0';
        wait for 50ns;
        
        --reg4
        data_in <= x"00FE";
        Dsel <= "0100";
        Clk <= '1';
        wait for 50ns;
 
        Clk <= '0';
        wait for 50ns;
 
        --reg5
        data_in <= x"00FF";
        Dsel <= "0101";
        Clk <= '1';
        wait for 50ns;
 
        Clk <= '0';
        wait for 50ns;
   
        --reg6
        data_in <= x"0FAA";
        Dsel <= "0110"; 
        Clk <= '1';
        wait for 50ns;
 
        Clk <= '0';
        wait for 50ns;
 
        --reg7
        data_in <= x"0FAB";
        Dsel <= "0111"; 
        Clk <= '1';
        wait for 50ns;
 
        Clk <= '0';
        wait for 50ns;
        
        --tempReg
        data_in <= x"0FAC";
        Dsel <= "1000";
        Clk <= '1';
        wait for 50ns;
 
        Clk <= '0';
        wait for 50ns;
        
        ------------------------------------------------------------------ 
        
        --**BUS TESTS**--
        
        ------------------------------------------------------------------
        
        --Test ABUS, select reg5 (0x00FF);  **
        Asel <= "0101";
        wait for 50ns;
        
        --Test BBUS, select reg3 (0x00FD); **
        Bsel <= "0011";
        wait for 50ns;   

        --Test PC Load through
        RW <= '0';
        MM <= '1';
        MD <= '1';
        MB <= '0';
        
        PC_in <= x"CCCC";
        wait for 50ns; 
        
        --Test MuxB
        SB <= "101";
        MB <= '1';
        wait for 50ns;
            
     wait;           
     end process;
    
end Behavioral;
