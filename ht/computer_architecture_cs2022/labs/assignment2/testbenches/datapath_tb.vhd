----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/14/2018 12:22:16 PM
-- Design Name: 
-- Module Name: reg16_tb - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity datapath_tb is
--  Port ( );
end datapath_tb;

architecture Behavioral of datapath_tb is
    -- declare component to test
    component datapath is
        Port ( data_in : in std_logic_vector(15 downto 0);
		constant_in : in std_logic_vector(15 downto 0);
		a_address : in std_logic_vector(2 downto 0);
		b_address : in std_logic_vector(2 downto 0);
		d_address : in std_logic_vector(2 downto 0);
		FS : in std_logic_vector(4 downto 0);
		write : in std_logic;
		v_out : in std_logic;
		c_out : in std_logic;
		n_out : in std_logic;
		z_out : in std_logic;
		mb_select : in std_logic;
		md_select : in std_logic;
		bus_a_adr_out : out std_logic_vector(15 downto 0);
		bus_b_data_out : out std_logic_vector(15 downto 0));             
    end component;
    
    -- signals for tests (initialise to 0)
        
        signal A : std_logic_vector := x'0000';
        signal B : std_logic_vector := x'0000';
        signal FS : std_logic_vector := '00000';
        signal F : std_logic_vector := x'0000';  
        signal V : std_logic := '0';
        signal C : std_logic := '0';
        signal N : std_logic := '0';
        signal Z : std_logic := '0'; 
        
        signal data_in : in std_logic_vector := x'0000';
		signal constant_in : in std_logic_vector := x'0000';
		signal a_address : in std_logic_vector := '000';
		signal b_address : in std_logic_vector := '000';
		signal d_address : in std_logic_vector := '000';
		signal FS : in std_logic_vector := '00000';
		signal write : in std_logic := '0'; 
		signal v_out : in std_logic := '0'; 
		signal c_out : in std_logic := '0'; 
		signal n_out : in std_logic := '0'; 
		signal z_out : in std_logic := '0'; 
		signal mb_select : in std_logic := '0'; 
		signal md_select : in std_logic := '0'; 
		signal bus_a_adr_out : out std_logic_vector := x'0000';
		signal bus_b_data_out : out std_logic_vector := x'0000';
        
begin

    -- instantiate component for test, connect ports to internal signals
    UUT: datapath
    Port map(
        data_in => data_in;
		constant_in => constant_in;
		a_address => a_address;
		b_address => b_address;
		d_address => d_address;
		FS => FS;
		write => write;
		v_out => v_out;
		c_out => c_out;
		n_out => n_out;
		z_out => z_out;
		mb_select => mb_select;
		md_select => md_select;
		bus_a_adr_out => bus_a_adr_out,
		bus_b_data_out => bus_b_data_out
    );
    
    simulation_process :process
    begin
    	--Test for the entire datapath as a whole
    	
        --Test FS = 00000 (F=A)= 0x0001
        FS <= '00000'
       	A <= x'0001';
       	B <= x'0041';
        wait for 5ns;
        
        --Test FS = 00001 (F=A+1) = 0x0007
        FS <= '00001'
       	A <= x'0006';
       	B <= x'0027';
        wait for 5ns;
        
       --Test FS = 00010 (F=A+B) = 0x0005
      	FS <= '00010'
       	A <= x'0002';
       	B <= x'0003';
        wait for 5ns;
        
       --Test FS = 00011 (F=A+B+1) = 0x000A
       	FS <= '00011'
       	A <= x'0007';
       	B <= x'0002';
        wait for 5ns;
        
       --Test FS = 00100 (F=A+B') = 0x0003
       	FS <= '00100'
       	A <= x'0001';
       	B <= x'FFFD';
        wait for 5ns;
        
        --Test FS = 00101 (F=A+B'+1) = 0x0004
       	FS <= '00101'
       	A <= x'0001';
       	B <= x'FFFD';
        wait for 5ns;
        
        --Test FS = 00101 (F=A-1) = 0x0002
       	FS <= '00110'
       	A <= x'0003';
       	B <= x'0128';
        wait for 5ns;
        
        --Test FS = 00111 (F=A) = 0x00F7
       	FS <= '00110'
       	A <= x'00F7';
       	B <= x'0128';
        wait for 5ns;
        
        --**LOGIC**--
        --Test FS = 01000 (F=A^B)= 0x0429
        FS <= '01000'
       	A <= x'0569';
       	B <= x'0EB9';
        wait for 5ns;
        
        --Test FS = 01010 (F=AorB) = 0xFF7F
        FS <= '01010'
       	A <= x'FE63';
       	B <= x'013D';
        wait for 5ns;
        
       --Test FS = 01100 (F=AxorB) = 0xD736
      	FS <= '01100'
       	A <= x'B59D';
       	B <= x'62AB';
        wait for 5ns;
        
       --Test FS = 01110 (F=A') = 0xFFF4
       	FS <= '00011'
       	A <= x'000B';
       	B <= x'0002';
        wait for 5ns;

     end process;
    
end Behavioral;
