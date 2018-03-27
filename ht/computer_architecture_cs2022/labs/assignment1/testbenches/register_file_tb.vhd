library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity register_file_tb is
--  Port ( );
end register_file_tb;

architecture Behavioral of register_file_tb is
    -- declare component to test
    
    component register_file is
        Port ( 
        	src_s0 : in std_logic;
            src_s1 : in std_logic;
            src_s2 : in std_logic;    
            des_A0 : in std_logic;
            des_A1 : in std_logic;
            des_A2 : in std_logic;
            Clk : in std_logic;
            data_src : in std_logic;
            data : in std_logic_vector(15 downto 0);
            reg0 : out std_logic_vector(15 downto 0);
            reg1 : out std_logic_vector(15 downto 0);
            reg2 : out std_logic_vector(15 downto 0);
            reg3 : out std_logic_vector(15 downto 0);
            reg4 : out std_logic_vector(15 downto 0);
            reg5 : out std_logic_vector(15 downto 0);
            reg6 : out std_logic_vector(15 downto 0);
            reg7 : out std_logic_vector(15 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal src_s0 : std_logic := '0';
    signal src_s1 : std_logic := '0';
    signal src_s2 : std_logic := '0';
        
    signal des_A0 : std_logic := '0';
    signal des_A1 : std_logic := '0';
    signal des_A2 : std_logic := '0'; 
        
    signal Clk : std_logic := '0';
    signal data_src : std_logic := '0';
    
    --outputs
    signal data : std_logic_vector(15 downto 0):= x"0000";
        
    signal reg0 : std_logic_vector(15 downto 0):= x"0000";
    signal reg1 : std_logic_vector(15 downto 0):= x"0000";
    signal reg2 : std_logic_vector(15 downto 0):= x"0000";
    signal reg3 : std_logic_vector(15 downto 0):= x"0000";
    signal reg4 : std_logic_vector(15 downto 0):= x"0000";
    signal reg5 : std_logic_vector(15 downto 0):= x"0000";
    signal reg6 : std_logic_vector(15 downto 0):= x"0000";
    signal reg7 : std_logic_vector(15 downto 0):= x"0000";
        
    -- Clock period
    constant clk_period : time := 10 ns;

begin

    -- instantiate component for test, connect ports to internal signals
    UUT: register_file
    Port Map(
        src_s0 => src_s0,
        src_s1 => src_s1,
        src_s2 => src_s2,
        des_A0 => des_A0,
        des_A1 => des_A1,
        des_A2 => des_A2,
        Clk => Clk,
        data_src => data_src,
        data => data,
        reg0 => reg0,
        reg1 => reg1,
        reg2 => reg2,
        reg3 => reg3,
        reg4 => reg4,
        reg5 => reg5,
        reg6 => reg6,
        reg7 => reg7
    );
    
-- Clock 
clk_process :process
begin
         clk <= '0';
         wait for clk_period/2;
         clk <= '1';
         wait for clk_period/2;
         
end process;
    
simulation_process :process
begin
        
        --load 0x000A into reg0
        des_A0 <= '0';
        des_A1 <= '0';
        des_A2 <= '0';
        data_src <= '0';
        data <= x"000A";
        wait for 10ns;
        
        --load 0x000B into reg1
        des_A0 <= '0';
        des_A1 <= '0';
        des_A2 <= '1';
        data_src <= '0';
        data <= x"000B";
        wait for 10ns;
    
        --load 0x000C into reg2
        des_A0 <= '0';
        des_A1 <= '1';
        des_A2 <= '0';
        data_src <= '0';
        data <= x"000C";
        wait for 10ns;

        --load 0x000D into reg3
        des_A0 <= '0';
        des_A1 <= '1';
        des_A2 <= '1';
        data_src <= '0';
        data <= x"000D";
        wait for 10ns;
        
        --load 0x000E into reg4
        des_A0 <= '1';
        des_A1 <= '0';
        des_A2 <= '0';
        data_src <= '0';
        data <= x"000E";
        wait for 10ns;

        --load 0x000F into reg5
        des_A0 <= '1';
        des_A1 <= '0';
        des_A2 <= '1';
        data_src <= '0';
        data <= x"000F";
        wait for 10ns;
        
        --load 0x00AA into reg6
        des_A0 <= '1';
        des_A1 <= '1';
        des_A2 <= '0';
        data_src <= '0';
        data <= x"00AA";
        wait for 10ns;
        
         --load 0x00AB into reg7
        des_A0 <= '1';
        des_A1 <= '1';
        des_A2 <= '1';
        data_src <= '0';
        data <= x"00AB";
        wait for 10ns;
        
        --transfer data from reg1 (0x000B) to reg 6 (0x00AA)
        src_s0 <= '0';
        src_s1 <= '0';
        src_s2 <= '1';
        wait for 10ns;
        des_A0 <= '1';
        des_A1 <= '1';
        des_A2 <= '0';
        data_src <= '1';
        wait for 10ns;
        
end process;
    
end Behavioral;
