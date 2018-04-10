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

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity register_file is
Port ( src_s0 : in std_logic;
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
	reg7 : out std_logic_vector(15 downto 0));
end register_file;

architecture Behavioral of register_file is
-- components

	-- 4 bit Register for register file
	COMPONENT reg16
	PORT(
		D : IN std_logic_vector(15 downto 0);
		load : IN std_logic;
		Clk : IN std_logic;
		Q : OUT std_logic_vector(15 downto 0)
		);
	END COMPONENT;
	
	
	-- 3 to 8 Decoder
	COMPONENT decoder_3to8
	PORT(
		A0 : IN std_logic;
		A1 : IN std_logic;
		A2 : IN std_logic;
		Q0 : OUT std_logic;
		Q1 : OUT std_logic;
		Q2 : OUT std_logic;
		Q3 : OUT std_logic;
		Q4 : OUT std_logic;
		Q5 : OUT std_logic;
		Q6 : OUT std_logic;
		Q7 : OUT std_logic
		);
	END COMPONENT;
	
	-- 2 to 1 line multiplexer
	COMPONENT mux2_16bit
	PORT(
		In0 : IN std_logic_vector(15 downto 0);
		In1 : IN std_logic_vector(15 downto 0);
		s : IN std_logic;
		Z : OUT std_logic_vector(15 downto 0)
		);
	END COMPONENT;
	
	
	-- 4 to 1 line multiplexer
	COMPONENT mux8_16bit
	PORT(
		In0 : IN std_logic_vector(15 downto 0);
		In1 : IN std_logic_vector(15 downto 0);
		In2 : IN std_logic_vector(15 downto 0);
		In3 : IN std_logic_vector(15 downto 0);
		In4 : IN std_logic_vector(15 downto 0);
		In5 : IN std_logic_vector(15 downto 0);
		In6 : IN std_logic_vector(15 downto 0);
		In7 : IN std_logic_vector(15 downto 0);
		S0 : IN std_logic;
		S1 : IN std_logic;
		S2 : IN std_logic;
		Z : OUT std_logic_vector(15 downto 0)
		);
	END COMPONENT;
	
	-- signals
	signal load_reg0, load_reg1, load_reg2, load_reg3, load_reg4, load_reg5,
		load_reg6, load_reg7  : std_logic;
	signal reg0_q, reg1_q, reg2_q, reg3_q, reg4_q, reg5_q, reg6_q,
		reg7_q, data_src_mux_out, src_reg : std_logic_vector(15 downto 0);
		
	begin
	-- port maps ;-)
	
	-- register 0
	reg00: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg0,
		Clk => Clk,
		Q => reg0_q
	);
	
	-- register 1
	reg01: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg1,
		Clk => Clk,
		Q => reg1_q
	);
	
	-- register 2
	reg02: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg2,
		Clk => Clk,
		Q => reg2_q
	);
	
	-- register 3
	reg03: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg3,
		Clk => Clk,
		Q => reg3_q
	);
	
	-- register 4
	reg04: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg4,
		Clk => Clk,
		Q => reg4_q
	);
	
	-- register 5
	reg05: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg5,
		Clk => Clk,
		Q => reg5_q
	);
	
	-- register 6
	reg06: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg6,
		Clk => Clk,
		Q => reg6_q
	);
	
	-- register 7
	reg07: reg16 PORT MAP(
		D => data_src_mux_out,
		load => load_reg7,
		Clk => Clk,
		Q => reg7_q
	);
	
	-- Destination register decoder
	des_decoder_3to8: decoder_3to8 PORT MAP(
		A0 => des_A0,
		A1 => des_A1,
		A2 => des_A2,
		Q0 => load_reg0,
		Q1 => load_reg1,
		Q2 => load_reg2,
		Q3 => load_reg3,
		Q4 => load_reg4,
		Q5 => load_reg5,
		Q6 => load_reg6,
		Q7 => load_reg7
	);
	
	-- 2 to 1 Data source multiplexer
	data_src_mux2_16bit: mux2_16bit PORT MAP(
		In0 => data,
		In1 => src_reg,
		s => data_src,
		Z => data_src_mux_out
	);
	
	-- 8 to 1 source register multiplexer
	Inst_mux8_16bit: mux8_16bit PORT MAP(
		In0 => reg0_q,
		In1 => reg1_q,
		In2 => reg2_q,
		In3 => reg3_q,
		In4 => reg4_q,
		In5 => reg5_q,
		In6 => reg6_q,
		In7 => reg7_q,
		S0 => src_s0,
		S1 => src_s1,
		S2 => src_s2,
		Z => src_reg
	);
	
	reg0 <= reg0_q;
	reg1 <= reg1_q;
	reg2 <= reg2_q;
	reg3 <= reg3_q;
	reg4 <= reg4_q;
	reg5 <= reg5_q;
	reg6 <= reg6_q;
	reg7 <= reg7_q;
end Behavioral;
