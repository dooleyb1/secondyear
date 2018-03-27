library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity decoder_4to9_tb is
--  Port ( );
end decoder_4to9_tb;

architecture Behavioral of decoder_4to9_tb is
    -- declare component to test
    component decoder_4to9 is
        Port ( 
            des : in std_logic_vector(3 downto 0);  
            Q0 : out std_logic;
            Q1 : out std_logic;
            Q2 : out std_logic;
            Q3 : out std_logic;
            Q4 : out std_logic;
            Q5 : out std_logic;
            Q6 : out std_logic;
            Q7 : out std_logic;
            Q8 : out std_logic
        );
    end component;
    
    -- signals for tests (initialise to 0)
      
    --inputs    
    signal des : std_logic_vector(3 downto 0) := "0000";
    
    --outputs    
    signal Q0 : std_logic := '0';
    signal Q1 : std_logic := '0';
    signal Q2 : std_logic := '0';
    signal Q3 : std_logic := '0';
    signal Q4 : std_logic := '0';
    signal Q5 : std_logic := '0';
    signal Q6 : std_logic := '0';
    signal Q7 : std_logic := '0';
    signal Q8 : std_logic := '0';
        
begin
    -- instantiate component for test, connect ports to internal signals
    UUT: decoder_4to9
    Port Map(
       	des => des,
        Q0 => Q0,
        Q1 => Q1,
        Q2 => Q2,
        Q3 => Q3,
        Q4 => Q4,
        Q5 => Q5,
        Q6 => Q6,
        Q7 => Q7,
        Q8 => Q8
    );

    
simulation_process :process
begin
        --Select line 0 (Q0 should be high/1)
        des <= "0000";
        wait for 1ns;
        
        --Select line 1 (Q1 should be high/1)
        des <= "0001";
        wait for 1ns;
        
        --Select line 2 (Q2 should be high/1)
        des <= "0010";
        wait for 1ns;
        
        --Select line 3 (Q3 should be high/1)
        des <= "0011";
        wait for 1ns;
        
        --Select line 4 (Q4 should be high/1)
        des <= "0100";
        wait for 1ns;
        
        --Select line 5 (Q5 should be high/1)
        des <= "0101";
        wait for 1ns;
        
        --Select line 6 (Q6 should be high/1)
        des <= "0110";
        wait for 1ns;
        
        --Select line 7 (Q7 should be high/1)
        des <= "0111";
        wait for 1ns;

        --Select line 8 (Q8 should be high/1)
        des <= "1000";
        wait for 1ns;
        
     end process;
    
end Behavioral;
