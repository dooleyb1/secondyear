library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity programme_cpu is
    Port (
        clk : in std_logic;
        reset: in std_logic;
        reg0: out std_logic_vector(15 downto 0);
        reg1: out std_logic_vector(15 downto 0);
        reg2: out std_logic_vector(15 downto 0);
        reg3: out std_logic_vector(15 downto 0);
        reg4: out std_logic_vector(15 downto 0);
        reg5: out std_logic_vector(15 downto 0);
        reg6: out std_logic_vector(15 downto 0);
        reg7: out std_logic_vector(15 downto 0)
    );
end programme_cpu;

architecture Behavioral of programme_cpu is
    
	-- Datapath
    component datapath
        Port(
            data_in : in std_logic_vector(15 downto 0);
                
            PC_in : in std_logic_vector(15 downto 0);
                
            SB: in std_logic_vector(2 downto 0);            
                
            MB : in std_logic;        
            MD : in std_logic;
            MM : in std_logic;
                
            Dsel : in std_logic_vector(2 downto 0);
            Asel : in std_logic_vector(2 downto 0);
            Bsel : in std_logic_vector(2 downto 0);
        
                
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
                
            Vflag : out std_logic;
            Cflag : out std_logic;
            Nflag : out std_logic;
            Zflag : out std_logic
        );
    end component;
    
	-- Microprogrammed Control
    component micro_control
        Port(
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
    
            -- TB to Register File (Temp B)
            TB : out std_logic;
            -- TA to Register File (Temp A)
            TA : out std_logic;
            -- TD to Register File (Temp D)
            TD : out std_logic;
    
            --DR to Register File (OR'd with TD)
            DR : out std_logic_vector(2 downto 0);
            --SA to Register File (OR'd with TA)
            SA : out std_logic_vector(2 downto 0);
            --SB to Register File (OR'd with TB)
            SB : out std_logic_vector(2 downto 0);
    
            -- PC_OUT to Mux M
            PCout : out std_logic_vector(15 downto 0)
        );
    end component;
    
    component memory
        port (
            address : in std_logic_vector(15 downto 0);
            data_in : in std_logic_vector(15 downto 0);
            Clk : in std_logic;
            MW : in std_logic;
            data_out : out std_logic_vector(15 downto 0)
        );
    end component;

    signal bus_a_adr_out : std_logic_vector(15 downto 0);
    signal bus_b_data_out : std_logic_vector(15 downto 0);
    
    signal VFlag : std_logic;
    signal CFlag : std_logic;
    signal NFlag : std_logic;
    signal ZFlag : std_logic;
    
    signal PC : std_logic_vector(15 downto 0);
    
    signal DR, SA, SB : std_logic_vector(2 downto 0);
    signal TD, TA, TB : std_logic;
    signal MB, MM, MW, MD : std_logic;
    
    signal FS : std_logic_vector(4 downto 0);
    
    signal RW : std_logic;
    
    signal memOut : std_logic_vector(15 downto 0);
    signal pcOut : std_logic_vector(15 downto 0);
    
    signal reg0out : std_logic_vector(15 downto 0); --reg0
    signal reg1out : std_logic_vector(15 downto 0); --reg1
    signal reg2out : std_logic_vector(15 downto 0); --reg2
    signal reg3out : std_logic_vector(15 downto 0); --reg3
    signal reg4out : std_logic_vector(15 downto 0); --reg4
    signal reg5out : std_logic_vector(15 downto 0); --reg5
    signal reg6out : std_logic_vector(15 downto 0); --reg6
    signal reg7out : std_logic_vector(15 downto 0); --reg7


begin

    MC : micro_control Port Map(
            
            Vflag => VFlag,
            Cflag => CFlag,
            Nflag => NFlag,
            Zflag => Zflag,
            
            memory_instruction => memOut,
            
            clk => clk,
            reset => reset,
            
            MW => MW,
            MM => MM,
            MD => MD,
            MB => MB,
            
            FS => FS,
            RW => RW,
            
            TD => TD,
            TA => TA,
            TB => TB,
            
            DR => DR,
            SA => SA,
            SB => SB,
            
            PCout => pcOut
        );

    DP : Datapath Port Map(
            
            data_in => memOut,
            PC_in => pcOut,
       
            SB => SB,
            
            MB => MB,
            MD => MD,
            MM => MM,
           
            DSel(2 downto 0) => DR,
            DSel(3) => TD,
            ASel(2 downto 0) => SA,
            ASel(3) => TA,
            BSel(2 downto 0) => SB,
            BSel(3) => TB,
            
            FS => FS,
            RW => RW,
            Clk => clk,
            
            bus_a_adr_out => bus_a_adr_out,
            bus_b_data_out => bus_b_data_out,
            
            reg_0_data_out => reg0out,
            reg_1_data_out => reg1out,
            reg_2_data_out => reg2out,
            reg_3_data_out => reg3out,
            reg_4_data_out => reg4out,
            reg_5_data_out => reg5out,
            reg_6_data_out => reg6out,
            reg_7_data_out => reg7out,
            
            VFlag => VFlag,
            CFlag => CFlag,
            NFlag => NFlag,
            ZFlag => ZFlag
        );
        
    mem : memory Port Map(
            address => bus_a_adr_out,
            data_in => bus_b_data_out,
            clk => clk,
            MW => MW,
            data_out => memOut
        );

end Behavioral;