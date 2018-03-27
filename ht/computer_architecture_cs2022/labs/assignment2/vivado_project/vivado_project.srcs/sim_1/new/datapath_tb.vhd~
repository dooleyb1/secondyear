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
			constant_in : in std_logic_vector(15 downto 0);
			a_address : in std_logic_vector(2 downto 0);
			b_address : in std_logic_vector(2 downto 0);
			d_address : in std_logic_vector(2 downto 0);
			FS : in std_logic_vector(4 downto 0);
			mb_select : in std_logic;
			md_select : in std_logic;
			write : in std_logic;
			v_out : out std_logic;
			c_out : out std_logic;
			n_out : out std_logic;
			z_out : out std_logic;
			bus_a_adr_out : out std_logic_vector(15 downto 0);
			bus_b_data_out : out std_logic_vector(15 downto 0);
			reg_0_data_out : out std_logic_vector(15 downto 0);
		    reg_1_data_out : out std_logic_vector(15 downto 0);
		    reg_2_data_out : out std_logic_vector(15 downto 0);
		    reg_3_data_out : out std_logic_vector(15 downto 0);
		    reg_4_data_out : out std_logic_vector(15 downto 0);
		    reg_5_data_out : out std_logic_vector(15 downto 0);
		    reg_6_data_out : out std_logic_vector(15 downto 0);
		    reg_7_data_out : out std_logic_vector(15 downto 0);
		    f_data_out : out std_logic_vector(15 downto 0)
		);             
    end component;
    
    
    -- signals for tests (initialise to 0)
    --inputs 
    signal data_in : std_logic_vector(15 downto 0) := x"0000";
    signal constant_in : std_logic_vector(15 downto 0) := x"0000";
    signal a_address : std_logic_vector(2 downto 0) := "000";
    signal b_address : std_logic_vector(2 downto 0) := "000";
    signal d_address : std_logic_vector(2 downto 0) := "000";
    signal FS : std_logic_vector(4 downto 0) := "00000";
    signal write : std_logic := '0';
    signal mb_select : std_logic := '0'; 
    signal md_select : std_logic := '0'; 
    
    --outputs
    signal v_out : std_logic := '0'; 
    signal c_out : std_logic := '0'; 
    signal n_out : std_logic := '0'; 
    signal z_out : std_logic := '0'; 
    signal bus_a_adr_out : std_logic_vector(15 downto 0) := x"0000";
    signal bus_b_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_0_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_1_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_2_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_3_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_4_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_5_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_6_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal reg_7_data_out : std_logic_vector(15 downto 0) := x"0000";
    signal f_data_out : std_logic_vector(15 downto 0) := x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: datapath
    Port Map(
        data_in => data_in,
		constant_in => constant_in,
		a_address => a_address,
		b_address => b_address,
		d_address => d_address,
		FS => FS,
		write => write,
		v_out => v_out,
		c_out => c_out,
		n_out => n_out,
		z_out => z_out,
		mb_select => mb_select,
		md_select => md_select,
		bus_a_adr_out => bus_a_adr_out,
		bus_b_data_out => bus_b_data_out,
		reg_0_data_out => reg_0_data_out,
		reg_1_data_out => reg_1_data_out,
		reg_2_data_out => reg_2_data_out,
		reg_3_data_out => reg_3_data_out,
		reg_4_data_out => reg_4_data_out,
		reg_5_data_out => reg_5_data_out,
		reg_6_data_out => reg_6_data_out,
		reg_7_data_out => reg_7_data_out,	
		f_data_out => f_data_out
    );
    
simulation_process :process
begin
    	--Test for the entire datapath as a whole
        
        ------------------------------------------------------------------
        
        --**LOADING REGISTER TESTS**--
        
        ------------------------------------------------------------------
        
        --Load all registers with values
        
        --reg0
        write <= '0';
        data_in <= x"00FA";
        md_select <= '1';
        wait for 5ns;
        d_address <= "000";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0';
        
        --reg1
        data_in <= x"00FB";
        md_select <= '1';
        wait for 5ns;
        d_address <= "001";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0';        
 
        --reg2
        data_in <= x"00FC";
        md_select <= '1';
        wait for 5ns;
        d_address <= "010";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0';
        
        --reg3
        data_in <= x"00FD";
        md_select <= '1';
        wait for 5ns;
        d_address <= "011";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0';
        
        --reg4
        data_in <= x"00FE";
        md_select <= '1';
        wait for 5ns;
        d_address <= "100";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0';
        
        --reg5
        data_in <= x"00FF";
        md_select <= '1';
        wait for 5ns;
        d_address <= "101";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0';
 
        --reg6
        data_in <= x"0FAA";
        md_select <= '1';
        wait for 5ns;
        d_address <= "110";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0'; 
        
        --reg7
        data_in <= x"0FBB";
        wait for 5ns;
        d_address <= "111";
        wait for 5ns;        
        write <= '1';
        wait for 5ns;
        write <= '0'; 
        
        ------------------------------------------------------------------ 
        
        --**BUS TESTS**--
        
        ------------------------------------------------------------------
        
        --Test ABUS, select reg5 (0x00FF);  **
        a_address <= "101";
        wait for 5ns;
        
        --Test BBUS, select reg3 (0x00FD); **
        b_address <= "011";
        wait for 5ns;   
        
        --Select B as source
        mb_select <= '0';
        
        ------------------------------------------------------------------
        
        --**ARITHMETIC UNIT TESTS**--
        
        ------------------------------------------------------------------
        
        --Test FS = 00000 (F = A) = reg5 = 0x00FF; **
        wait for 5ns;
        FS <= "00000";
        wait for 80ns;
        
        --Test FS = 00001 (F = A+1) = reg5+1 = 0x00FF + 0x0001 = 0x0100    **
        wait for 5ns;
        FS <= "00001";
        wait for 80ns;

        --Test FS = 00010 (F = A+B) = reg5+reg3 = 0x00FF + 0x00FD = 0x01FC   **
        wait for 5ns;
        FS <= "00010";
        wait for 80ns;        

        --Test FS = 00011 (F = A+B+1) = reg5+reg3+1 = 0x00FF + 0x00FD + 0x0001= 0x01FD   **
        wait for 5ns;
        FS <= "00011";
        wait for 80ns;
      
        --Test FS = 00100 (F = A+B') = reg5+reg3' = 0x00FF + 0x00FD' (0xFF02) = 0x0001    **
        wait for 5ns;
        FS <= "00100";
        wait for 80ns;
        
        --Test FS = 00101 (F = A+B'+1) = reg5+reg3' = 0x00FF + 0x00FD' (0xFF02) + 1 = 0x0002   **
        wait for 5ns;
        FS <= "00101";
        wait for 80ns;
        
        --Test FS = 00110 (F = A-1) = reg5-1 = 0x00FF - 0x0001 = 0x00FE   **
        wait for 5ns;
        FS <= "00110";
        wait for 80ns;

        --Test FS = 00111 (F = A) = reg5 = 0x00FF   **
        wait for 5ns;
        FS <= "00111";
        wait for 80ns;        

        --**LOGIC UNIT TESTS**--
        
        --Test FS = 01000 (F = A and B) = reg5 AND reg3 = 0x00FF and 0x00FD = 0x00FD    **
        wait for 5ns;
        FS <= "01000";
        wait for 80ns;
      
        --Test FS = 01010 (F = A or B) = reg5 OR reg3 = 0x00FF or 0x00FD = 0x00FF    **
        wait for 5ns;
        FS <= "01010";
        wait for 80ns;
        
        --Test FS = 01100 (F = A xor B) = reg5 XOR reg3 = 0x00FF xor 0x00FD = 0x0002    **
        wait for 5ns;
        FS <= "01100";
        wait for 80ns;
        
        --Test FS = 01110 (F = not A) = NOT reg5= not 0x00FF = 0xFF00    **
        wait for 5ns;
        FS <= "01110";
        wait for 80ns;
        
        ------------------------------------------------------------------
        
        --**SHIFTER TESTS**--
        
        ------------------------------------------------------------------
        
        --Test FS = 10000 (F = B) = reg3 = 0x00FD    **
        wait for 5ns;
        FS <= "10000";
        wait for 80ns;
      
        --Test FS = 10100 (F = sr B) = >> reg3 = >> 0x00FD = 0x007E    **
        wait for 5ns;
        FS <= "10100";
        wait for 80ns;
        
        --Test FS = 11000 (F = sl B) = << reg3 = << 0x00FD = 0x01FA    **
        wait for 5ns;
        FS <= "11000";
        wait for 80ns;   
        
        ------------------------------------------------------------------
        
        --**FLAG TESTS**--
        
        ------------------------------------------------------------------  
          
        --Test V Flag
          
        -- 0xFFFF + 0x8000 = 0x7FFF -> V = 1 **
        
        -- Load reg1 with 0xFFFF
        data_in <= x"FFFF";
        md_select <= '1';
        wait for 5ns;
        d_address <= "001";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0'; 
        
        -- Load reg2 with 0xFFFF
        data_in <= x"8000";
        md_select <= '1';
        wait for 5ns;
        d_address <= "010";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0'; 
        
        --Pass values to ABUS and BBUS
        a_address <= "001";
        b_address <= "010";
        wait for 5ns;
        
        --Pass B onto function unit
        mb_select <= '0';
        wait for 5ns;
        
        --F = A+B -> 0xFFFF + 0x8000 = 0x7FFF -> V = 1 **    
        FS <= "00010"; 
        wait for 80ns;
 
         --Test C Flag
          
        -- -- 0xF000 + 0x8000 = 0x7000 -> C = 1 **
        
        -- Load reg1 with 0xF000
        data_in <= x"F000";
        md_select <= '1';
        wait for 5ns;
        d_address <= "001";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0'; 
        
        -- Load reg2 with 0x8000
        data_in <= x"8000";
        md_select <= '1';
        wait for 5ns;
        d_address <= "010";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0'; 
        
        --Pass values to ABUS and BBUS
        a_address <= "001";
        b_address <= "010";
        wait for 5ns;
        
        --Pass B onto function unit
        mb_select <= '0';
        wait for 5ns;
        
        --F = A+B -> 0xF000 + 0x8000 = 0x7000 -> C = 1 **    
        FS <= "00010"; 
        wait for 80ns;
 
       --Test N Flag
         
       -- 0xF405 + 0x00BC = 0xF4C1 -> N = 1 **
       
       -- Load reg1 with 0xF405
       data_in <= x"F405";
       md_select <= '1';
       wait for 5ns;
       d_address <= "001";
       wait for 5ns;
       write <= '1';
       wait for 5ns;
       write <= '0'; 
       
       -- Load reg2 with 0x00BC
       data_in <= x"00BC";
       md_select <= '1';
       wait for 5ns;
       d_address <= "010";
       wait for 5ns;
       write <= '1';
       wait for 5ns;
       write <= '0'; 
       
       --Pass values to ABUS and BBUS
       a_address <= "001";
       b_address <= "010";
       wait for 5ns;
       
       --Pass B onto function unit
       mb_select <= '0';
       wait for 5ns;
       
       --F = A+B -> 0xF405 + 0x00BC = 0xF4C1 -> N = 1 **  
       FS <= "00010"; 
       wait for 80ns;

       --Test Z Flag
         
       -- 0x0000 + 0x0000 = 0x0000 -> Z = 1 **
       
       -- Load reg1 with 0x0000
       data_in <= x"0000";
       md_select <= '1';
       wait for 5ns;
       d_address <= "001";
       wait for 5ns;
       write <= '1';
       wait for 5ns;
       write <= '0'; 
       
       -- Load reg2 with 0x0000
       data_in <= x"0000";
       md_select <= '1';
       wait for 5ns;
       d_address <= "010";
       wait for 5ns;
       write <= '1';
       wait for 5ns;
       write <= '0'; 
       
       --Pass values to ABUS and BBUS
       a_address <= "001";
       b_address <= "010";
       wait for 5ns;
       
       --Pass B onto function unit
       mb_select <= '0';
       wait for 5ns;
       
       --F = A+B -> 0x0000 + 0x0000 = 0x0000 -> Z = 1 **
       FS <= "00010"; 
       wait for 80ns;
        
        --** REGISTER TRANSFER TEST **-- 
        
        --Transfer contents of A to reg4
        wait for 5ns;
        --Select reg0 as A
        a_address <= "000";
        wait for 5ns;
        --F=A (0x00FA)
        FS <= "00000";
        wait for 80ns;
        --Select F=A as data source
        md_select <= '0';
        wait for 5ns;
        --Select reg4("100") as data destination
        d_address <= "100";
        wait for 5ns;
        write <= '1';
        wait for 5ns;
        write <= '0';
        
        ------------------------------------------------------------------
        
        --** ADDITION WITH CONSTANT TEST **--
        
        ------------------------------------------------------------------
        
        --Pass constant 0x0F72 through MUXB --> B= 0x07F2
        constant_in <= x"0F72";
        mb_select <= '1';
        wait for 5ns;
        --Change FS = A + B --> 0x00FA + 0x0F72 = 0x106C
        FS <= "00010";
        wait for 80ns;
        
     wait;           
     end process;
    
end Behavioral;
