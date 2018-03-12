library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity decoder_3to8_tb is
--  Port ( );
end decoder_3to8_tb;

architecture Behavioral of decoder_3to8_tb is
    -- declare component to test
    component decoder_3to8 is
        Port ( des : in std_logic_vector(2 downto 0);  
            Q0 : out std_logic;
            Q1 : out std_logic;
            Q2 : out std_logic;
            Q3 : out std_logic;
            Q4 : out std_logic;
            Q5 : out std_logic;
            Q6 : out std_logic;
            Q7 : out std_logic
        );
    end component;
    
    -- signals for tests (initialise to 0)
      
    --inputs    
    signal des : std_logic_vector(2 downto 0) := "000";
    
    --outputs    
    signal Q0 : std_logic := '0';
    signal Q1 : std_logic := '0';
    signal Q2 : std_logic := '0';
    signal Q3 : std_logic := '0';
    signal Q4 : std_logic := '0';
    signal Q5 : std_logic := '0';
    signal Q6 : std_logic := '0';
    signal Q7 : std_logic := '0';
        
begin
    -- instantiate component for test, connect ports to internal signals
    UUT: decoder_3to8
    Port Map(
       	des => des,
        Q0 => Q0,
        Q1 => Q1,
        Q2 => Q2,
        Q3 => Q3,
        Q4 => Q4,
        Q5 => Q5,
        Q6 => Q6,
        Q7 => Q7
    );

    
simulation_process :process
begin
        --Select line 0 (Q0 should be high/1)
        des <= "000";
        wait for 1ns;
        
        --Select line 1 (Q1 should be high/1)
        des <= "001";
        wait for 1ns;
        
        --Select line 2 (Q2 should be high/1)
        des <= "010";
        wait for 1ns;
        
        --Select line 3 (Q3 should be high/1)
        des <= "011";
        wait for 1ns;
        
        --Select line 4 (Q4 should be high/1)
        des <= "100";
        wait for 1ns;
        
        --Select line 5 (Q5 should be high/1)
        des <= "101";
        wait for 1ns;
        
        --Select line 6 (Q6 should be high/1)
        des <= "110";
        wait for 1ns;
        
        --Select line 7 (Q7 should be high/1)
        des <= "111";
        wait for 1ns;
        
     end process;
    
end Behavioral;
