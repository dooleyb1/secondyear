library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity microprogrammed_control_tb is
--  Port ( );
end microprogrammed_control_tb;

architecture Behavioral of microprogrammed_control_tb is

    -- declare component to test
    component microprogrammed_control is
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
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal Vflag, Cflag, Nflag, Zflag : std_logic := '0';
    signal memory_instruction : std_logic_vector(15 downto 0) := x"0000";
    signal Clk, reset : std_logic := '0';
    
    --outputs
    signal MW, MM, MD, MB  : std_logic := '0';
    signal FS : std_logic_vector(4 downto 0) := "00000";
    signal RW : std_logic := '0';
    
    signal TB, TA, TD  : std_logic := '0';
    signal DR, SA, SB  : std_logic_vector(2 downto 0) := "000";
    signal PCout : std_logic_vector(15 downto 0) := x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: microprogrammed_control
    Port Map(
        Vflag => Vflag,
        Cflag => Cflag,
        Nflag => Nflag,
        Zflag => Zflag,
        
        memory_instruction => memory_instruction,
        
        Clk => Clk, 
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
        
        PCout => PCout
    );
    
simulation_process :process
begin
              
        memory_instruction <= x"0045";
        Clk <= '1';
        wait for 10ns;
        
        
        
     end process;
    
end Behavioral;
