library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity mux8_1bit is
	Port (
		In0 : in std_logic;
		In1 : in std_logic;
		In2 : in std_logic;
		In3 : in std_logic; 
		In4 : in std_logic; 
		In5 : in std_logic; 
		In6 : in std_logic; 
		In7 : in std_logic;
		src : in std_logic_vector(2 downto 0);
		Z : out std_logic
	);
end mux8_16bit;

architecture Behavioral of mux8_16bit is
begin
	Z <= In0 after 1ns when src="000" else
		 In1 after 1ns when src="001" else
		 In2 after 1ns when src="010" else
		 In3 after 1ns when src="011" else
		 In4 after 1ns when src="100" else
		 In5 after 1ns when src="101" else
		 In6 after 1ns when src="110" else
		 In7 after 1ns when src="111" else
	     '0' after 1ns;
end Behavioral;
