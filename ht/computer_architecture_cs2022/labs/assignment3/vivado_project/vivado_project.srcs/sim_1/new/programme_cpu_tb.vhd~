library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity programme_cpu_tb is
--  Port ( );
end programme_cpu_tb;

architecture Behavioral of programme_cpu_tb is

    component programme_cpu is
        Port (
            clk : in std_logic;
            reset: in std_logic;
            reg0: out std_logic_vector(15 downto 0);
            reg1: out std_logic_vector(15 downto 0);
            reg2: out std_logic_vector(15 downto 0);
            reg3: out std_logic_vector(15 downto 0);
            reg4: out std_logic_vector(15 downto 0);
            reg5: out std_logic_vector(15 downto 0);
            reg6: out std_logic_vector(15 downto 0);
            reg7: out std_logic_vector(15 downto 0)
        );
    end component;

    signal clk, reset : std_logic := '0';
    signal reg0out, reg1out, reg2out, reg3out, reg4out, reg5out, reg6out, reg7out : std_logic_vector(15 downto 0);

    constant clk_period : time := 40ns;
    constant cycles : integer := 0;
    constant max_cycles : integer := 1000;

begin

    UTT: programme_cpu
        Port Map (
            clk => clk,
            reset => reset,
            reg0 => reg0out,
            reg1 => reg1out,
            reg2 => reg2out,
            reg3 => reg3out,
            reg4 => reg4out,
            reg5 => reg5out,
            reg6 => reg6out,
            reg7 => reg7out
        );

process
    begin
        reset <= '1';
        clk <= '1';
        wait for 50 ns;
        reset <= '0';
        clk <= '0';
        while cycles < max_cycles loop
            clk <= not clk;
            wait for clk_period/2;
        end loop;
        wait;
end process;

end Behavioral;
