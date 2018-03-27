
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.all;


entity CAR is
 Port ( 
       --Either Next Address (NA) or Opcode from IR
       CAR_IN : in std_logic_vector(7 downto 0);
       FLAGS_IN : in std_logic;
       CAR_OUT : out std_logic_vector(7 downto 0)
      );
end CAR;

architecture Behavioral of CAR is

begin

CAR_OUT <= std_logic_vector(to_unsigned(to_integer(unsigned( CAR_IN )) + 1, 8)) after 1ns when FLAGS_IN = '1' 
           else CAR_IN after 1ns when FLAGS_IN = '0';

end Behavioral;
