
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity IR is

Port (
       IR_IN : in std_logic_vector(15 downto 0);
       IL : in std_logic;
       CLK : in std_logic;
       OPCODE : out std_logic_vector(6 downto 0);
       DR : out std_logic_vector(2 downto 0);
       SA : out std_logic_vector(2 downto 0);
       SB : out std_logic_vector(2 downto 0)
      );
end IR;

architecture Behavioral of IR is

signal IR_temp : std_logic_vector(15 downto 0);

begin

IR_temp <= IR_IN after 1ns when IL = '1' 
           else IR_temp after 1ns;

OPCODE <= IR_temp(15 downto 9) when CLK = '1';
DR <= IR_temp(8 downto 6) when CLK = '1';
SA <= IR_temp(5 downto 3) when CLK = '1';
SB <= IR_temp(2 downto 0) when CLK = '1';

end Behavioral;
