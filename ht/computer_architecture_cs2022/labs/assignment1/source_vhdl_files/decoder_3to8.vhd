library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity decoder_3to8 is
	Port ( 
		A0 : in std_logic;
		A1 : in std_logic;
		A2: in std_logic;
		Q0 : out std_logic;
		Q1 : out std_logic;
		Q2 : out std_logic;
		Q3 : out std_logic;
		Q4 : out std_logic;
		Q5 : out std_logic;
		Q6 : out std_logic;
		Q7 : out std_logic
	);
end decoder_3to8;

architecture Behavioral of decoder_3to8 is
begin

	Q0<= ((not A0) and (not A1) and (not A2)) after 5 ns;
	Q1<= ((not A0) and (not A1) and A2) after 5 ns;
	Q2<= ((not A0) and A1 and (not A2)) after 5 ns;
	Q3<= ((not A0) and A1 and A2) after 5 ns;
	Q4<= (A0 and (not A1) and (not A2)) after 5 ns;
	Q5<= (A0 and (not A1) and A2) after 5 ns;
	Q6<= (A0 and A1 and (not A2)) after 5 ns;
	Q7<= (A0 and A1 and A2) after 5 ns;

end Behavioral;
