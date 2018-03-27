
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.all;

entity PC is
 Port ( 
      PC_IN : in std_logic_vector(15 downto 0);
      PL : in std_logic;
      PI : in std_logic;
      PC_OUT : out std_logic_vector(15 downto 0)
     );
end PC;

architecture Behavioral of PC is

begin

PC_OUT <= std_logic_vector(to_unsigned(to_integer(unsigned( PC_IN )) + 1, 16)) after 1ns when PI = '1' 
          else PC_IN after 1ns when PI = '0' and PL = '1';


end Behavioral;
