library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Extend is
    Port ( 
        DR_SB : in std_logic_vector(5 downto 0);
        Ext : out std_logic_vector(15 downto 0)
    );
end Extend;

architecture Behavioral of Extend is

    signal ext_sig : std_logic_vector(15 downto 0);

begin

    ext_sig(5 downto 0) <= DR_SB;
    ext_sig(15 downto 6) <= "0000000000" when DR_SB(5) = '0' else "1111111111";
    Ext <= ext_sig;

end Behavioral;