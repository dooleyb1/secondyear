library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity mux9_16bit is
	Port (
		In0 : in std_logic_vector(15 downto 0);
		In1 : in std_logic_vector(15 downto 0);
		In2 : in std_logic_vector(15 downto 0);
		In3 : in std_logic_vector(15 downto 0); 
		In4 : in std_logic_vector(15 downto 0); 
		In5 : in std_logic_vector(15 downto 0); 
		In6 : in std_logic_vector(15 downto 0); 
		In7 : in std_logic_vector(15 downto 0);
		In8 : in std_logic_vector(15 downto 0);
		src : in std_logic_vector(3 downto 0);
		Z : out std_logic_vector(15 downto 0)
	);
end mux9_16bit;

architecture Behavioral of mux9_16bit is
begin
	Z <= In0 after 1ns when src="0000" else
		 In1 after 1ns when src="0001" else
		 In2 after 1ns when src="0010" else
		 In3 after 1ns when src="0011" else
		 In4 after 1ns when src="0100" else
		 In5 after 1ns when src="0101" else
		 In6 after 1ns when src="0110" else
		 In7 after 1ns when src="0111" else
	     In8 after 1ns when src ="1000" else
	     x"0000" after 1ns;
end Behavioral;
