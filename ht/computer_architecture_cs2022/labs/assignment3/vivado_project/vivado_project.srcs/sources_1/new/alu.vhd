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

entity alu is
  Port ( 
    Cin : in std_logic;
    A: in std_logic;
    B: in std_logic;
    S0: in std_logic;
    S1: in std_logic;
    S2: in std_logic;
    G: out std_logic;
    Cout : out std_logic
  );
end alu;

architecture Behavioral of alu is

    component logic_unit
        Port ( 
            S0: in std_logic;
            S1: in std_logic;
            A: in std_logic;
            B: in std_logic;
            G: out std_logic
        );
    end component;

    component arithmetic_unit
        Port ( 
           A: in std_logic;
           B: in std_logic;
           Cin: in std_logic;
           Cout: out std_logic;
           S0: in std_logic;
           S1: in std_logic;
           Z: out std_logic
        );
    end component;
    
    component mux2_1bit
        Port (
            In0 : in std_logic;
            In1 : in std_logic;
            Sel : in std_logic;
            mux_out : out std_logic
        );
    end component;

    signal adder_out, logic_out : std_logic;

begin
    LU: logic_unit 
        Port map(
            S0 => S0, 
            S1 => S1, 
            A => A, 
            B => B, 
            G => logic_out
        );
        
    AU: arithmetic_unit 
        Port map(
            A => A, 
            B => B,
            Cin => Cin,
            Cout => Cout, 
            S0 => S0,
            S1 => S1,
            Z => adder_out
        );
        
    MUX: mux2_1bit 
        Port map(
            In0 => adder_out, 
            In1 => logic_out, 
            Sel => S2, 
            mux_out => G
        ); 
        
end Behavioral;
