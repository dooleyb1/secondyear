library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity PC is
 Port ( 
      PC_IN : in std_logic_vector(15 downto 0);
      PL : in std_logic;
      PI : in std_logic;
      RESET : in std_logic;
      Clk : in std_logic;
      PC_OUT : out std_logic_vector(15 downto 0)
     );
end PC;

architecture Behavioral of PC is

begin

    process (RESET, PL, PI, Clk)
    variable pc : std_logic_vector(15 downto 0);
    variable temp_pc : integer;
    variable temp_inc_pc : std_logic_vector(15 downto 0);
    
    begin
        if(reset = '1' and clk = '1') then pc := x"0000";
        elsif(PL = '1' and clk = '1') then
            pc := pc + PC_IN;
        elsif(PI = '1' and clk = '1') then
            temp_pc := conv_integer(pc);
            temp_pc := temp_pc + conv_integer(1);
            temp_inc_pc := conv_std_logic_vector(temp_pc, 16);
            pc := temp_inc_pc;
        end if;
        PC_OUT <= pc after 10ns;
    end process;
end Behavioral;