
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity control_memory is
    Port ( 
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
        NA : out std_logic_vector(7 downto 0);
        
        -- IN_CAR input from CAR
        IN_CAR : in std_logic_vector(7 downto 0)
       );
end control_memory;

-- ** MW ** --
---------------
-- Select control bit for Memory M which 
-- determines whether to write a value to
-- memory or to read from memory


-- ** MM ** --
---------------
-- Select bit for Mux M which controls whether
-- to take the address from the program counter (PC)
-- or to take the address from Bus A


-- ** RW ** --
---------------
-- Enable fed to the Register File which controls
-- whether to load a selected register with the
-- data fed to it by the decoder


-- ** MD ** --
---------------
-- Select bit for Mux D which controls whether
-- to take the result data from the function unit
-- or to take the data output from the Memory M


-- ** FS ** --
---------------
-- Control for the Function Unit, defines what function
-- to perform on the input data, see previous project
-- for more information


-- ** MB ** --
---------------
-- Select bit for Mux B which controls whether
-- to take the current value off the BBUS from 
-- the register file or to take the constant SB


-- ** TB ** --
---------------
-- Select bit for Register File B address
-- used to choose a BBUS register


-- ** TA ** --
---------------
-- Select bit for Register File A address
-- used to choose a ABUS register


-- ** TD ** --
---------------
-- Select bit for Register File D address
-- used to choose a Data register


-- ** PL ** --
---------------
-- Control bit for Program Counter (PC)
-- if high -> load program


-- ** PI ** --
---------------
-- Control bit for Program Counter (PC)
-- if high -> increment program counter


-- ** IL ** --
---------------
-- Control bit for Instruction Register unit (IR)
-- if high -> instruction load


-- ** MC ** --
---------------
-- Select bit for Mux C which controls whether
-- to take the opcode passed from the Instruction
-- Register (IR) or to take the value of Next Address (NA)
-- output from the Control Memory 


-- ** MS (3 bit) ** --
---------------
-- Select bit for Mux S which controls which flag
-- value to select and pass to the CAR


-- ** NA ** --
---------------
-- Value output from the Control Memory which 
-- indicates the Next Address (NA) which 
-- is then fed to MUX C

-- ** IN_CAR ** --
-------------------
-- Value received from the CAR which determines
-- what instruction (index) to access within 
-- the Control Memory


architecture Behavioral of control_memory is

type mem_array is array(0 to 255) of std_logic_vector(27 downto 0);

begin
memory_m: process(IN_CAR)
variable control_mem : mem_array:=(

    -- 0
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 16
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000",
    
    -- 32
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 48
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000",

    -- 64
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 80
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000",
    
    -- 96
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 112
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000",
    
    --  128
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 144
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000",
    
    -- 160
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 176
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000",

    -- 192
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 208
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000",
    
    -- 224
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 

    -- 240
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000", 
    X"00000000", X"00000000", X"00000000", X"00000000");

    variable addr : integer;
    variable control_out : std_logic_vector(27 downto 0);

begin
    addr := conv_integer(IN_CAR);
    control_out := control_mem(addr);
   
    MW <= control_out(0);
    MM <= control_out(1);
    RW <= control_out(2);
    MD <= control_out(3);
    FS <= control_out(8 downto 4);
    MB <= control_out(9);
    TB <= control_out(10);
    TA <= control_out(11);
    TD <= control_out(12);
    PL <= control_out(13);
    PI <= control_out(14);
    IL <= control_out(15);
    MC <= control_out(16);
    MS <= control_out(19 downto 17);
    NA <= control_out(27 downto 20);
    end process;

end Behavioral;
