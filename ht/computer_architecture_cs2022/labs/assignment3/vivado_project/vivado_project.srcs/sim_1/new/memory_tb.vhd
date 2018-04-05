library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity memory_tb is
--  Port ( );
end memory_tb;

architecture Behavioral of memory_tb is

    -- declare component to test
    component memory is
        Port ( 
            address : in std_logic_vector(15 downto 0);
            data_in : in std_logic_vector(15 downto 0);
            Clk : in std_logic;
            MW : in std_logic;
            data_out : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal address : std_logic_vector(15 downto 0) := x"0000";
    signal data_in : std_logic_vector(15 downto 0) := x"0000";
    
    signal Clk : std_logic := '0';
    signal MW : std_logic := '0';
    
    --outputs
    signal data_out : std_logic_vector(15 downto 0):= x"0000";
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: memory
    Port Map(
        address => address,
        data_in => data_in,
        Clk => Clk,
        MW => MW,
        data_out => data_out
    );
    
simulation_process :process
begin
        --Read Value at address 2 (0x0045)
        address <= x"0002";
        data_in <= x"0000";
        MW <= '0';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
        
        --Write to address F 
        address <= x"0002";
        data_in <= x"ABCD";
        MW <= '1';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;  
        
        --Read Value just written to address F (0xABCD)
        address <= x"0002";
        data_in <= x"0000";
        MW <= '0';
        Clk <= '1';
        wait for 10ns;
        
        Clk <= '0';
        wait for 10ns;
        
     end process;
    
end Behavioral;
