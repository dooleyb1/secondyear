library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity CAR is
 Port ( 
       --Either Next Address (NA) or Opcode from IR
       A : in std_logic;
       B : in std_logic_vector(7 downto 0);
     
       RESET : in std_logic;
       CLK : in std_logic;
       
       Z : out std_logic_vector(7 downto 0)
      );
end CAR;

architecture Behavioral of CAR is

begin      
    process(RESET, CLK)
    
    variable current : std_logic_vector(7 downto 0);
    variable temp_current : integer;
    variable temp_currentpp : std_logic_vector(7 downto 0);
    begin
        if(RESET = '1') then current := x"C0";
        elsif(A = '1' and CLK = '1') then current := B;
        elsif(A = '0' and CLK = '1') then
--            temp_current := conv_integer(current);
--            temp_current := temp_current + conv_integer(1);
--            temp_currentpp := conv_std_logic_vector(temp_current, 8);
--            current := temp_currentpp;
        end if;
        Z <= current after 10ns;
    end process;
end Behavioral;
