library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity mux2_8bit is
port ( In0 : in std_logic_vector(7 downto 0);
In1 : in std_logic_vector(7 downto 0);
s : in std_logic;
Z : out std_logic_vector(7 downto 0));
end mux2_8bit;

architecture Behavioral of mux2_8bit is
begin
Z <= In0 after 5 ns when S='0' else
In1 after 5 ns when S='1'else
"0000" after 5 ns;
end Behavioral;
