library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity logic_unit is
    Port ( 
        S0: in std_logic;
        S1: in std_logic;
        A: in std_logic;
        B: in std_logic;
        G: out std_logic
    );
end logic_unit;

architecture Behavioral of logic_unit is

begin
    G <= (A and B) after 1ns when S1='0' and S0='0' else
    (A or B) after 1ns when S1='0' and S0='1' else
    (A xor B) after 1ns when S1='1' and S0='0' else
    (not A) after 1ns when S1='1' and S0='1'; 
end Behavioral;
