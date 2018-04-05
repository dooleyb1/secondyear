library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity control_memory_tb is
--  Port ( );
end control_memory_tb;

architecture Behavioral of control_memory_tb is

    -- declare component to test
    component control_memory is
        Port ( 
        -- IN_CAR input from CAR
        IN_CAR : in std_logic_vector(7 downto 0);
    
        -- MW to Memory
        MW : out std_logic;
        -- MM to Mux M
        MM : out std_logic;
        -- MD to Mux D
        MD : out std_logic;
        -- MB to Mux B
        MB : out std_logic;
        -- MC to Mux C
        MC : out std_logic;
        
        -- MS to Mux S
        MS : out std_logic_vector(2 downto 0);
        
        -- RW to Register File (Read/Write)
        RW : out std_logic;
        -- FS to Function Unit
        FS : out std_logic_vector(4 downto 0);
        
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
        

        -- NA to Mux C (Next Address)
        NA : out std_logic_vector(7 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal IN_CAR : std_logic_vector(7 downto 0) := x"00";
    
    --outputs
    signal MW, MM, MD, MB, MC  : std_logic := '0';
    signal MS : std_logic_vector(2 downto 0) := "000";
    signal TB, TA, TD  : std_logic := '0';
    signal PI, PL, IL  : std_logic := '0';
    signal RW : std_logic := '0';
    signal FS : std_logic_vector(4 downto 0) := "00000";
    signal NA : std_logic_vector(7 downto 0) := x"00";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: control_memory
    Port Map(
        IN_CAR => IN_CAR,
        
        MW => MW,
        MM => MM,
        MD => MD,
        MB => MB,
        MC => MC,
        MS => MS,
        
        TB => TB,
        TA => TA,
        TD => TD,
        
        PI => PI,
        PL => PL, 
        IL => IL,
        
        RW => RW,
        FS => FS,
        NA => NA
    );
    
simulation_process :process
begin
        --Read Value at address 1 (0xC020306)
        ------------------------------------------------------------------------------
        --    NA        MS   MC  IL  PI  PL  TD  TA  TB  MB     FS    MD  RW  MM  MW
        -- |1100 0000 | 001 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 1 0000 | 0 | 1 | 1 | 0
        ------------------------------------------------------------------------------
        -- NA = 1100 0000 (C0)
        -- MS = 001 
        -- MC = 0
        -- IL = 0
        -- PI = 0 
        -- PL = 0
        -- TD = 0
        -- TA = 0
        -- TB = 0
        -- MB = 1
        -- FS = 1 0000
        -- MD = 0
        -- RW = 1
        -- MM = 0
        -- MW = 0      
        IN_CAR <= "00000000";
        wait for 10ns;
        IN_CAR <= "00000001";
        wait for 10ns;
        IN_CAR <= "00000010";
        wait for 10ns;
        IN_CAR <= "00000011";
        wait for 10ns;
        IN_CAR <= "00000100";
        wait for 10ns;
        
        
        
     end process;
    
end Behavioral;
