-- MIT License
--
-- Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
--
-- Permission is hereby granted, free of charge, to any person obtaining a copy
-- of this software and associated documentation files (the "Software"), to deal
-- in the Software without restriction, including without limitation the rights
-- to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
-- copies of the Software, and to permit persons to whom the Software is
-- furnished to do so, subject to the following conditions:
--
-- The above copyright notice and this permission notice shall be included in
-- all copies or substantial portions of the Software.
--
-- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
-- IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
-- FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
-- AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
-- LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
-- OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
-- THE SOFTWARE.
--

-- Binary Multiplier with n = 4: VHDL Description
-- See Figures 8-6 and 8-7 for block diagram and ASM Chart
-- in Mano and Kime

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity binary_multiplier is
	Port (
		CLK : in std_logic;
		RESET : in std_logic;

		G : in std_logic;

		LOADB : in std_logic;
		LOADQ : in std_logic;

		MULT_IN : in std_logic_vector(3 downto 0);

		MULT_OUT : out std_logic_vector(7 downto 0);
	);
end binary_multiplier;

architecture Behavioral of binary_multiplier is

	type state_type is (IDLE, MUL0, MUL1);

	signal state : state_type;
	signal next_state : state_type;

	signal A : std_logic_vector(3 downto 0);
	signal B : std_logic_vector(3 downto 0);
	signal Q : std_logic_vector(3 downto 0);

	signal P : std_logic_vector(1 downto 0);

	signal C : std_logic;
	signal Z : std_logic;

begin

	Z <= P(1) NOR P(0);
	MULT_OUT <= A & Q;


	state_register: process(CLK, RESET)
	begin

			if(RESET = '1') then
				state <= IDLE;

			elsif(CLKevent and CLK='1') then
				state <= next_state;

			end if;
	end process;

	next_state_func: process(G, Z, state)
	begin

			case state is

				when IDLE =>
						if(G = '1') then
							next_state <= MUL0;
						else
							next_state <= IDLE;
						end if;

				when MUL0 =>
						next_state <= MUL1;

				when MUL1 =>
						if Z = '1' then
							next_state <= IDLE;
						else
							next_state <= MUL0;
						end if;

			end case;
	end process;

	datapath_func: process(CLK)
	variable CA : std_logic_vector(4 downto 0);
	begin

		if(CLKevent and CLK = '1') then

			if LOADB = '1' then
					B <= MULT_IN;
			end if;

			if LOADQ = '1' then
					Q <= MULT_IN;
			end if;

			case state is

				when IDLE =>

						if(G = '1') then
							C <= '0';
							A <= "0000";
							P <= "11"

						end if;

				when MUL0 =>

						if Q(0) = '1' then
							CA := ('0' & A) + ('0' & B);
						else
							CA := C & A;

						end if;

						C <= CA(4);
						A <= CA(3 downto 0);

				when MUL1 =>
						C <= '0';
						A <= C & A(3 downto 1);
						Q <= A(0) & Q(3 downto 1);
						P <= P - "01";

			end case;
		end if;
	end process;

end Behavioral;
