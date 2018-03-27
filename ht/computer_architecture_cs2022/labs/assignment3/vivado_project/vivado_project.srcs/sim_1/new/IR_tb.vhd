library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity IR_tb is
--  Port ( );
end IR_tb;

architecture Behavioral of IR_tb is

    -- declare component to test
    component IR is
        Port ( 
            IR_IN : in std_logic_vector(15 downto 0);
            IL : in std_logic;
            OPCODE : out std_logic_vector(6 downto 0);
            DR : out std_logic_vector(2 downto 0);
            SA : out std_logic_vector(2 downto 0);
            SB : out std_logic_vector(2 downto 0)
        );
    end component;
    
    -- signals for tests (initialise to 0)
    
    --inputs    
    signal IR_IN : std_logic_vector(15 downto 0):= x"0000";
    signal IL : std_logic := '0';
    
    --outputs
    signal OPCODE : std_logic_vector(6 downto 0):= "0000000";
    signal DR : std_logic_vector(2 downto 0):= "000";
    signal SA : std_logic_vector(2 downto 0):= "000";
    signal SB : std_logic_vector(2 downto 0):= "000";        

begin

    -- instantiate component for test, connect ports to internal signals
    UUT: IR
    Port Map(
        IR_IN => IR_IN,
        IL => IL,
        OPCODE => OPCODE,
        DR => DR,
        SA => SA,
        SB => SB
    );
    
simulation_process :process
begin
        
        --Test loading random IR_IN (0xFFAB)
        -----------------------------------
        --OPCODE = 1111 111 
        --DR = 110
        --SA = 101
        --SB = 011
        
        IR_IN <= x"FFAB";
        IL <= '1';
        wait for 1ns;
        
        --Test loading blank IR_IN (0x0000)
        -----------------------------------
        --OPCODE = 0000 000 
        --DR = 000
        --SA = 000
        --SB = 000
        
        IR_IN <= x"0000";
        IL <= '1';
        wait for 1ns;        
           
        --Test IL low (IL = 0) Shouldn't change outputs
        -----------------------------------
        --OPCODE = 0000 000 
        --DR = 000
        --SA = 000
        --SB = 000
        
        IR_IN <= x"FCDA";
        IL <= '0';
        wait for 1ns;           
           
     end process;
    
end Behavioral;
