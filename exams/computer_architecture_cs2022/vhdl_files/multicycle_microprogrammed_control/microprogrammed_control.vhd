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

entity microprogrammed_control is
    Port ( 
        Vflag : in std_logic;
        Cflag : in std_logic;
        Nflag : in std_logic; 
        Zflag : in std_logic;
        
        instruction: in std_logic_vector(15 downto 0);
        clk : in std_logic;
        reset: in std_logic;
        
        PCout : out std_logic_vector(15 downto 0);
        
        TD : out std_logic;
        TA : out std_logic;
        TB : out std_logic;
        
        MB : out std_logic;
        FS : out std_logic_vector(4 downto 0);
        MD : out std_logic;
        RW : out std_logic;
        MM : out std_logic;
        MW : out std_logic;
        
        --for testing
        PL : out std_logic;
        PI : out std_logic;
        IL : out std_logic;
        MC : out std_logic;
        MS : out std_logic_vector(2 downto 0);
        NA : out std_logic_vector(7 downto 0);
        
        DR : out std_logic_vector(2 downto 0);
        SA : out std_logic_vector(2 downto 0);
        SB : out std_logic_vector(2 downto 0)
    );
end microprogrammed_control;

architecture Behavioral of microprogrammed_control is
    component control_memory
        Port ( 
        MW: out std_logic;
        MM: out std_logic;
        RW: out std_logic;
        MD: out std_logic;
        FS: out std_logic_vector(4 downto 0);
        MB: out std_logic;
        TB: out std_logic;
        TA: out std_logic;
        TD: out std_logic;
        
        PL: out std_logic;
        PI: out std_logic;
        IL: out std_logic;
        MC: out std_logic;
        MS: out std_logic_vector(2 downto 0);
        NA: out std_logic_vector(7 downto 0);
        
        IN_CAR: in std_logic_vector(7 downto 0)
      );
    end component;
    
    component CAR
        port (
            A : in std_logic;
            CLK : in std_logic;
            RESET : in std_logic;
            B : in std_logic_vector(7 downto 0);
            Z : out std_logic_vector(7 downto 0)
        );
    end component;
    
    component mux2_8bit
        port (
            In0 : in std_logic_vector(7 downto 0);
            In1 : in std_logic_vector(7 downto 0);
            Sel : in std_logic;
            mux_out : out std_logic_vector(7 downto 0)
        );
    end component;
    
    component IR
        Port ( 
            IR_IN : in std_logic_vector(15 downto 0);
            IL : in std_logic;
            CLK : in std_logic;
            OPCODE : out std_logic_vector(6 downto 0);
            DR : out std_logic_vector(2 downto 0);
            SA : out std_logic_vector(2 downto 0);
            SB : out std_logic_vector(2 downto 0)
        );
    end component;
    
    component mux8_1bit
    	Port ( 
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
    
    component PC
        port (
            PC_IN : in std_logic_vector(15 downto 0);
            PL : in std_logic;
            PI : in std_logic;
            Clk : in std_logic;
            RESET : in std_logic;
            PC_OUT : out std_logic_vector(15 downto 0)
        );
    end component;
    
    component Extend
        Port ( 
            DR_SB : in std_logic_vector(5 downto 0);
            Ext : out std_logic_vector(15 downto 0)
        );
    end component;
    
    signal PL_out, PI_out, IL_out, MC_out : std_logic;
    signal MS_out : std_logic_vector(2 downto 0);
    signal NA_out : std_logic_vector(7 downto 0);
    
    
 
    signal MUXS_OUT : std_logic; 
    signal notC : std_logic; 
    signal notZ : std_logic;
    signal DR_PC : std_logic_vector(2 downto 0); 
    signal SA_PC : std_logic_vector(2 downto 0);
    signal SB_PC : std_logic_vector(2 downto 0);
    signal IN_CAR : std_logic_vector(7 downto 0);
    signal MUXC_OUT : std_logic_vector(7 downto 0);
    signal Opcode : std_logic_vector(6 downto 0);
    signal PCin : std_logic_vector(15 downto 0);

begin

    control_memory0: control_memory
        port map (
            IN_CAR => IN_CAR,
            MW => MW,
            MM => MM,
            RW => RW,
            MD => MD,
            FS => FS,
            MB => MB,
            TB => TB,
            TA => TA,
            TD => TD,
            
            PL => PL_out,
            PI => PI_out,
            IL => IL_out,
            MC => MC_out,
            MS => MS_out,
            NA => NA_out
        );
    
    car0: CAR
        port map (
            A => MUXS_OUT,
            B => MUXC_OUT,
            RESET => reset,
            CLK => clk,
            Z => IN_CAR
        );
    
    muxc: mux2_8bit
        port map (
            In1(0) => Opcode(0),
            In1(1) => Opcode(1),
            In1(2) => Opcode(2),
            In1(3) => Opcode(3),
            In1(4) => Opcode(4),
            In1(5) => Opcode(5),
            In1(6) => Opcode(6),
            In1(7) => '0',
            In0 => NA_out,
            Sel => MC_out,
            mux_out => MUXC_OUT
        );

    ir0: IR
        port map (
            IR_IN => instruction,
            IL => IL_out,
            CLK => clk,
            OPCODE => Opcode,
            DR => DR_PC,
            SA => SA_PC,
            SB => SB_PC
        );
        
    muxs: mux8_1bit
        port map(
            In0 => '0',
            In1 => '1',
            In2 => CFlag,
            In3 => VFlag,
            In4 => ZFlag,
            In5 => NFlag,
            In6 => notC,
            In7 => notZ,
            S0 => MS_out(0),
            S1 => MS_out(1),
            S2 => MS_out(2),
            z => MUXS_OUT
        );
        
    ext0: Extend
        port map(
            DR_SB(2 downto 0) => SB_PC,
            DR_SB(5 downto 3) => DR_PC,
            Ext => PCin
        );
 
    pc0: PC
        port map(
          PC_IN =>  PCin,
          PL => PL_out,
          PI => PI_out,
          RESET => reset,
          Clk => clk,
          PC_OUT => PCout
        );

    PL <= PL_out;
    PI <= PI_out;
    IL <= IL_out;
    MC <= MC_out;
    MS <= MS_out;
    NA <= NA_out;
    DR <= DR_PC;
    SA <= SA_PC;
    SB <= SB_PC;

end Behavioral;
