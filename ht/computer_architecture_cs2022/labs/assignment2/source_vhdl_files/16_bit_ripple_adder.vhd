library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
 
entity 16_bit_ripple_adder is
Port ( A : in STD_LOGIC_VECTOR (15 downto 0);
B : in STD_LOGIC_VECTOR (15 downto 0);
Cin : in STD_LOGIC;
S : out STD_LOGIC_VECTOR (15 downto 0);
Cout : out STD_LOGIC);
end 16_bit_ripple_adder;
 
architecture Behavioral of 16_bit_ripple_adder is
 
-- Full Adder VHDL Code Component Decalaration
component full_adder
Port ( A : in STD_LOGIC;
B : in STD_LOGIC;
Cin : in STD_LOGIC;
S : out STD_LOGIC;
Cout : out STD_LOGIC);
end component;
 
-- Intermediate Carry declaration
signal c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17: STD_LOGIC;
 
begin
 
-- Port Mapping Full Adder 4 times
FA1: full_adder_vhdl_code port map( A(0), B(0), Cin, S(0), c1);
FA2: full_adder_vhdl_code port map( A(1), B(1), c1, S(1), c2);
FA3: full_adder_vhdl_code port map( A(2), B(2), c2, S(2), c3);
FA4: full_adder_vhdl_code port map( A(3), B(3), c3, S(3), c4);
FA5: full_adder_vhdl_code port map( A(4), B(4), c4, S(4), c5);
FA6: full_adder_vhdl_code port map( A(5), B(5), c5, S(5), c6);
FA7: full_adder_vhdl_code port map( A(6), B(6), c6, S(6), c7);
FA8: full_adder_vhdl_code port map( A(7), B(7), c7, S(7), c8);
FA9: full_adder_vhdl_code port map( A(8), B(8), c8, S(8), c9);
FA10: full_adder_vhdl_code port map( A(9), B(9), c9, S(9), c10);
FA11: full_adder_vhdl_code port map( A(10), B(10), c10, S(10), c11);
FA12: full_adder_vhdl_code port map( A(11), B(11), c11, S(11), c12);
FA13: full_adder_vhdl_code port map( A(12), B(12), c12, S(12), c13);
FA14: full_adder_vhdl_code port map( A(13), B(13), c13, S(13), c14);
FA15: full_adder_vhdl_code port map( A(14), B(14), c14, S(14), c15);
FA16: full_adder_vhdl_code port map( A(15), B(15), c15, S(15), c16);
FA17: full_adder_vhdl_code port map( A(16), B(16), c16, S(16), c17);
FA18: full_adder_vhdl_code port map( A(17), B(17), c17, S(17), Cout);
 
end Behavioral;
