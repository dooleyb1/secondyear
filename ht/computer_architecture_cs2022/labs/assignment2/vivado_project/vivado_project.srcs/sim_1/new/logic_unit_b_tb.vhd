LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY logic_unit_b_tb IS
END logic_unit_b_tb;
 
architecture Behavioral of logic_unit_b_tb is
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT logic_unit_b
    PORT(
         B : IN  std_logic_vector(15 downto 0);
         S_in : IN  std_logic_vector(1 downto 0);
         Y_out : OUT  std_logic_vector(15 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal B : std_logic_vector(15 downto 0) := (others => '0');
   signal S_in : std_logic_vector(1 downto 0) := (others => '0');

 	--Outputs
   signal Y_out : std_logic_vector(15 downto 0);
 
begin
 
	-- Instantiate the Unit Under Test (UUT)
   uut: logic_unit_b PORT MAP (
          B => B,
          S_in => S_in,
          Y_out => Y_out
        );

   -- Stimulus process
   stim_proc: process
   begin		
		B <= x"AAAA";
		S_in <= "00";
		
		wait for 5ns;
		S_in <= "01";
		
		wait for 5ns;
		S_in <= "10";
		
		wait for 5ns;
		S_in <= "11";

      wait;
   end process;
 end;