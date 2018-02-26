library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity mux4_16bit is
port ( In0 : in std_logic_vector(15 downto 0);
In1 : in std_logic_vector(15 downto 0);
In2: in std_logic_vector(15 downto 0);
In3: in std_logic_vector(15 downto 0);
s : in std_logic_vecot(1 downto 0);
Z : out std_logic_vector(15 downto 0));
end mux4_16bit;

architecture Behavioral of mux4_16bit is
begin
Z <= In0 after 5 ns when S='00' else
In1 after 5 ns when S='01' else
In2 after 5 ns when S='10' else
In3 after 5 ns when S='11' else
"0000000000000000" after 5 ns;
end Behavioral;
