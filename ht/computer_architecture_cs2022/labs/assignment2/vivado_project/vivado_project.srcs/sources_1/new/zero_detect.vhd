library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity zero_detect is
port ( 
I : in std_logic_vector(15 downto 0);
O : out std_logic);
end zero_detect;

architecture Behavioral of zero_detect is
begin
O <= ((not I(0)) and (not I(1)) and
          (not I(2)) and (not I(3)) and
          (not I(4)) and (not I(5)) and
          (not I(5)) and (not I(6)) and
          (not I(7)) and (not I(8)) and 
          (not I(9)) and (not I(10)) and
          (not I(11)) and (not I(12)) and
          (not I(13)) and (not I(14)) and (not I(15)));
end Behavioral;
