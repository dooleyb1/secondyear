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


entity alu_tb is
--  Port ( );
end alu_tb;

architecture Behavioral of alu_tb is
    -- declare component to test
    component alu is
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
    end component;
    
    -- signals for tests (initialise to 0)
        
    --inputs
    signal A : std_logic := '0';
    signal B : std_logic := '0';
    signal Cin : std_logic := '0'; 
    signal S0 : std_logic := '0'; 
    signal S1 : std_logic := '0'; 
    signal S2 : std_logic := '0';
        
    --outputs
    signal Cout : std_logic := '0';
    signal G : std_logic := '0';  
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: alu
    Port Map(
        A => A,
        B => B,
        Cin => Cin,
        S0 => S0,
        S1 => S1,
        S2 => S2,
        Cout => Cout,
        G => G
    );
    
simulation_process :process
begin
        --Test all zeros
       	A <= '0';
       	B <= '0';
       	Cin <= '0';
       	S0 <= '0';
       	S1 <= '0';
        wait for 2ns;
        
        --**ARITHMETIC UNIT TESTS**--
        
        --Test G=A-> G = '1', Cout = '0' **
        A <= '1';
        B <= '0';
        Cin <= '0';
        S0 <= '0';
        S1 <= '0';
        S2 <= '0';
        wait for 2ns;        
 
        --Test G=A+1-> G = '0', Cout = '1' **
        A <= '1';
        B <= '0';
        Cin <= '1';
        S0 <= '0';
        S1 <= '0';
        S2 <= '0';
        wait for 2ns;
        
        --Test G=A+B-> G = '0', Cout = '1' 
        A <= '1';
        B <= '1';
        Cin <= '0';
        S0 <= '1';
        S1 <= '0';
        S2 <= '0';
        wait for 2ns;

        --Test G=A+B+1-> G = '1', Cout = '1' 
        A <= '1';
        B <= '1';
        Cin <= '1';
        S0 <= '1';
        S1 <= '0';
        S2 <= '0';
        wait for 2ns;
        
        --Test G=A+B' -> G = '0', Cout = '1'  
        A <= '1';
        B <= '0';
        Cin <= '0';
        S0 <= '0';
        S1 <= '1';
        S2 <= '0';
        wait for 2ns;        
 
        --Test G=A+B'+1 -> G = '1', Cout = '1' 
        A <= '1';
        B <= '0';
        Cin <= '1';
        S0 <= '0';
        S1 <= '1';
        S2 <= '0';
        wait for 2ns;
        
        --Test G=A-1 -> G = '0', Cout = '1' 
        A <= '1';
        B <= '0';
        Cin <= '0';
        S0 <= '1';
        S1 <= '1';
        S2 <= '0';
        wait for 2ns;
        
        --Test G=A-> G = '0', Cout = '1' 
        A <= '0';
        B <= '0';
        Cin <= '1';
        S0 <= '1';
        S1 <= '1';
        S2 <= '0';
        wait for 2ns;
        
        --**LOGIC TESTS**--
        
        --Test G= A and B -> G = '1', Cout = '0' 
        A <= '1';
        B <= '1';
        Cin <= '0';
        S0 <= '0';
        S1 <= '0';
        S2 <= '1';
        wait for 2ns;        
 
        --Test G=A or B -> G = '1', Cout = '0' 
        A <= '1';
        B <= '0';
        Cin <= '0';
        S0 <= '1';
        S1 <= '0';
        S2 <= '1';
        wait for 2ns;
        
        --Test G=A xor B -> G = '1', Cout = '1' 
        A <= '1';
        B <= '0';
        Cin <= '0';
        S0 <= '0';
        S1 <= '1';
        S2 <= '1';
        wait for 2ns;
       
        --Test G= not A -> G = '0', Cout = '1' *
        A <= '1';
        B <= '0';
        Cin <= '0';
        S0 <= '1';
        S1 <= '1';
        S2 <= '1';
        wait for 2ns;
       
     end process;
    
end Behavioral;
