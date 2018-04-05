library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Extend is
    Port ( 
        DR_SA_SB : in std_logic_vector(8 downto 0);
        Ext : out std_logic_vector(15 downto 0)
    );
end Extend;

architecture Behavioral of Extend is

    signal ext_sig : std_logic_vector(15 downto 0);

begin

    ext_sig(8 downto 0) <= DR_SA_SB;
    ext_sig(15 downto 9) <= "0000000";
    Ext <= ext_sig;

end Behavioral;