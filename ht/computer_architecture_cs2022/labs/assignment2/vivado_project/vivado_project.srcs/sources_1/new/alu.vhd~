library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity ALU is
  Port ( 
    Cin : in std_logic;
    A: in std_logic;
    B: in std_logic;
    S0: in std_logic;
    S1: in std_logic;
    S2: in std_logic;
    G: out std_logic;
    Cout : out std_logic
  );
end ALU;

architecture Behavioral of ALU is

    component logic_unit
        Port ( 
            S0: in std_logic;
            S1: in std_logic;
            A: in std_logic;
            B: in std_logic;
            G: out std_logic
        );
    end component;

    component arithmetic_circuit
        Port ( 
           A: in std_logic;
           B: in std_logic;
           Cin: in std_logic;
           Cout: out std_logic;
           S0: in std_logic;
           S1: in std_logic;
           Z: out std_logic
        );
    end component;
    
    component mux2_1
        Port (
            In0 : in std_logic;
            In1 : in std_logic;
            s : in std_logic;
            Z : out std_logic
        );
    end component;

    signal adder_out, logic_out : std_logic;

begin
    LU: logic_unit 
        port map(
            S0 => S0, 
            S1 => S1, 
            A => A, 
            B => B, 
            G => logic_out
        );
        
    AD: arithmetic_circuit 
        port map(
            A => A, 
            B => B,
            Cin => Cin,
            Cout => Cout, 
            S0 => S0,
            S1 => S1,
            Z => adder_out
        );
        
    MUX: mux2_1 
        port map(
            In0 => adder_out, 
            In1 => logic_out, 
            s => S2, 
            Z => G
        ); 
        
end Behavioral;
