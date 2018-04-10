-- MIT License
--
-- Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in
-- all copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
-- THE SOFTWARE.
--

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity control_memory is
    Port ( 
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

    -- Module 0
    x"C020306" , x"C02400E", x"C02018C", X"C02018C", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module 1
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000",
    
    -- Module 2
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module 3
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000",

    -- Module 4
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module 5
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000",
    
    -- Module 6
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module 7
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000",
    
    --  Module 8
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module 9
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000",
    
    -- Module A
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module B
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000",

    -- Module C
    x"C12C002" , x"0030000", X"0000000", X"0000000", 
    --Fetches the instruction next in memory and 
    --Execute what is now in the instruction reg and go to opcode specified in instruction
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module D
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000",
    
    -- Module E
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 

    -- Module F
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000", 
    X"0000000", X"0000000", X"0000000", X"0000000");

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
