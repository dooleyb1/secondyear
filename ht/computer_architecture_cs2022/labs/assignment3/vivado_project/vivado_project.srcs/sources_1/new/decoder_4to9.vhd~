library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity decoder_4to9 is
	Port ( 
		des: in std_logic_vector(3 downto 0);
		RW : in std_logic;
		Q0 : out std_logic;
		Q1 : out std_logic;
		Q2 : out std_logic;
		Q3 : out std_logic;
		Q4 : out std_logic;
		Q5 : out std_logic;
		Q6 : out std_logic;
		Q7 : out std_logic;
		Q8 : out std_logic
	);
end decoder_4to9;

architecture Behavioral of decoder_4to9 is
begin
	Q0<= '1' after 1ns when des = "0000" and RW = '1' else '0' after 1ns;
	Q1<= '1' after 1ns when des = "0001" and RW = '1' else '0' after 1ns;
	Q2<= '1' after 1ns when des = "0010" and RW = '1' else '0' after 1ns;
	Q3<= '1' after 1ns when des = "0011" and RW = '1' else '0' after 1ns;
	Q4<= '1' after 1ns when des = "0100" and RW = '1' else '0' after 1ns;
	Q5<= '1' after 1ns when des = "0101" and RW = '1' else '0' after 1ns;
	Q6<= '1' after 1ns when des = "0110" and RW = '1' else '0' after 1ns;
	Q7<= '1' after 1ns when des = "0111" and RW = '1' else '0' after 1ns;
	Q8<= '1' after 1ns when des = "1000" and RW = '1' else '0' after 1ns;
end Behavioral;
