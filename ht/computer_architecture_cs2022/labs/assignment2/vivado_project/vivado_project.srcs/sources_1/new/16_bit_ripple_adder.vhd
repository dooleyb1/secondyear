library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
 
entity ripple_adder_16bit is
Port ( A : in STD_LOGIC_VECTOR (15 downto 0);
B : in STD_LOGIC_VECTOR (15 downto 0);
Cin : in STD_LOGIC;
S : out STD_LOGIC_VECTOR (15 downto 0);
Cout : out STD_LOGIC;
V : out STD_lOGIC);
end ripple_adder_16bit;
 
architecture Behavioral of ripple_adder_16bit is
 
-- Full Adder VHDL Code Component Decalaration
component full_adder
Port ( A : in STD_LOGIC;
B : in STD_LOGIC;
Cin : in STD_LOGIC;
S : out STD_LOGIC;
Cout : out STD_LOGIC);
end component;
 
-- Intermediate Carry declaration
signal c0, c1,c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, C_out : std_logic;
 
begin
 
-- Port Mapping Full Adder 16 times
FA0: full_adder port map(
    A => A(0),
    B => B(0), 
    Cin => Cin, 
    S => S(0), 
    Cout => c0);
    
FA1: full_adder port map(
        A => A(1),
        B => B(1), 
        Cin => c0, 
        S => S(1), 
        Cout => c1);

FA2: full_adder port map(
        A => A(2),
        B => B(2), 
        Cin => c1, 
        S => S(2), 
        Cout => c2);

FA3: full_adder port map(
        A => A(3),
        B => B(3), 
        Cin => c2, 
        S => S(3), 
        Cout => c3);    
    
FA4: full_adder port map(
        A => A(4),
        B => B(4), 
        Cin => c3, 
        S => S(4), 
        Cout => c4);
        
FA5: full_adder port map(
        A => A(5),
        B => B(5), 
        Cin => c4, 
        S => S(5), 
        Cout => c5);
                
FA6: full_adder port map(
        A => A(6),
        B => B(6), 
        Cin => c5, 
        S => S(6), 
        Cout => c6);
            
FA7: full_adder port map(
        A => A(7),
        B => B(7), 
        Cin => c6, 
        S => S(7), 
        Cout => c7);
        
FA8: full_adder port map(
        A => A(8),
        B => B(8), 
        Cin => c7, 
        S => S(8), 
        Cout => c8);
        
FA9: full_adder port map(
        A => A(9),
        B => B(9), 
        Cin => c8, 
        S => S(9), 
        Cout => c9);    
            
FA10: full_adder port map(
        A => A(10),
        B => B(10), 
        Cin => c9, 
        S => S(10), 
        Cout => c10);
                
FA11: full_adder port map(
        A => A(11),
        B => B(11), 
        Cin => c10, 
        S => S(11), 
        Cout => c11);

FA12: full_adder port map(
        A => A(12),
        B => B(12), 
        Cin => c11, 
        S => S(12), 
        Cout => c12);
        
FA13: full_adder port map(
        A => A(13),
        B => B(13), 
        Cin => c12, 
        S => S(13), 
        Cout => c13);    
            
FA14: full_adder port map(
         A => A(14),
         B => B(14), 
         Cin => c13, 
         S => S(14), 
         Cout => c14);
                
FA15: full_adder port map(
         A => A(15),
         B => B(15), 
         Cin => c14, 
         S => S(15), 
         Cout => c15);
         
--Carry Flag 
C_out <= c15;
Cout <= C_out;
    
--Overflow Flag
V <= (C_out xor c14);
 
end Behavioral;
