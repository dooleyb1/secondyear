library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity reg16 is
port ( D : in std_logic_vector(15 downto 0);
load : in std_logic;
d_sel : in std_logic
Q : out std_logic_vector(15 downto 0));
end reg16;

architecture Behavioral of reg16 is
begin
if load='1' and d_sel='1' then
Q<=D after 5 ns;
end if;
end Behavioral;
