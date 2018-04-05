library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Zero_fill is
    Port ( 
        SB : in std_logic_vector(2 downto 0);
        zeroFill : out std_logic_vector(15 downto 0)
    );
end Zero_fill;


architecture Behavioral of Zero_fill is
    
    signal zf : std_logic_vector(15 downto 0);

begin
    
    zf(2 downto 0) <= SB;
    zf(15 downto 3) <= "0000000000000";
    
    zeroFill <= zf;

end Behavioral;