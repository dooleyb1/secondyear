library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity decoder_2to4 is
Port ( A0 : in std_logic;
A1 : in std_logic;
Q0 : out std_logic;
Q1 : out std_logic;
Q2 : out std_logic;
Q3 : out std_logic);
end decoder_2to4;

architecture Behavioral of decoder_2to4 is
begin
Q0<= ((not A0) and (not A1)) after 5 ns;
Q1<= (A0 and (not A1)) after 5 ns;
Q2<= ((not A0) and A1) after 5 ns;
Q3<= (A0 and A1) after 5 ns;
end Behavioral;
