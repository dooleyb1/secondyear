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
	des_A0 : in std_logic;
	des_A1 : in std_logic;
	Clk : in std_logic;
	data_src : in std_logic;
	data : in std_logic_vector(3 downto 0);
	reg0 : out std_logic_vector(3 downto 0);
	reg1 : out std_logic_vector(3 downto 0);
	reg2 : out std_logic_vector(3 downto 0);
	reg3 : out std_logic_vector(3 downto 0));
end register_file;

architecture Behavioral of register_file is
-- components

	-- 4 bit Register for register file
	COMPONENT reg4
	PORT(
		D : IN std_logic_vector(3 downto 0);
		load : IN std_logic;
		Clk : IN std_logic;
		Q : OUT std_logic_vector(3 downto 0)
		);
	END COMPONENT;
	
	
	-- 2 to 4 Decoder
	COMPONENT decoder_2to4
	PORT(
		A0 : IN std_logic;
		A1 : IN std_logic;
		Q0 : OUT std_logic;
		Q1 : OUT std_logic;
		Q2 : OUT std_logic;
		Q3 : OUT std_logic
		);
	END COMPONENT;
	
	-- 2 to 1 line multiplexer
	COMPONENT mux2_4bit
	PORT(
		In0 : IN std_logic_vector(3 downto 0);
		In1 : IN std_logic_vector(3 downto 0);
		s : IN std_logic;
		Z : OUT std_logic_vector(3 downto 0)
		);
	END COMPONENT;
	
	
	-- 2 to 4 Decoder
	COMPONENT decoder_2to4
	PORT(
		A0 : IN std_logic;
		A1 : IN std_logic;
		Q0 : OUT std_logic;
		Q1 : OUT std_logic;
		Q2 : OUT std_logic;
		Q3 : OUT std_logic
		);
	END COMPONENT;
	
	-- 2 to 1 line multiplexer
	COMPONENT mux2_4bit
	PORT(
		In0 : IN std_logic_vector(3 downto 0);
		In1 : IN std_logic_vector(3 downto 0);
		s : IN std_logic;
		Z : OUT std_logic_vector(3 downto 0)
		);
	END COMPONENT;
	
	-- 4 to 1 line multiplexer
	COMPONENT mux4_4bit
	PORT(
		In0 : IN std_logic_vector(3 downto 0);
		In1 : IN std_logic_vector(3 downto 0);
		In2 : IN std_logic_vector(3 downto 0);
		In3 : IN std_logic_vector(3 downto 0);
		S0 : IN std_logic;
		S1 : IN std_logic;
		Z : OUT std_logic_vector(3 downto 0)
		);
	END COMPONENT;
	
	-- signals
	signal load_reg0, load_reg1, load_reg2, load_reg3 : std_logic;
	signal reg0_q, reg1_q, reg2_q, reg3_q,
		data_src_mux_out, src_reg : std_logic_vector(3 downto 0);
		
	begin
	-- port maps ;-)
	
	-- register 0
	reg00: reg4 PORT MAP(
		D => data_src_mux_out,
		load => load_reg0,
		Clk => Clk,
		Q => reg0_q
	);
	
	-- register 1
	reg01: reg4 PORT MAP(
		D => data_src_mux_out,
		load => load_reg1,
		Clk => Clk,
		Q => reg1_q
	);
	
	-- register 2
	reg02: reg4 PORT MAP(
		D => data_src_mux_out,
		load => load_reg2,
		Clk => Clk,
		Q => reg2_q
	);
	
	-- register 3
	reg03: reg4 PORT MAP(
		D => data_src_mux_out,
		load => load_reg3,
		Clk => Clk,
		Q => reg3_q
	);
	
	-- Destination register decoder
	des_decoder_2to4: decoder_2to4 PORT MAP(
		A0 => des_A0,
		A1 => des_A1,
		Q0 => load_reg0,
		Q1 => load_reg1,
		Q2 => load_reg2,
		Q3 => load_reg3
	);
	
	-- 2 to 1 Data source multiplexer
	data_src_mux2_4bit: mux2_4bit PORT MAP(
		In0 => data,
		In1 => src_reg,
		s => data_src,
		Z => data_src_mux_out
	);
	
	-- 4 to 1 source register multiplexer
	Inst_mux4_4bit: mux4_4bit PORT MAP(
		In0 => reg0_q,
		In1 => reg1_q,
		In2 => reg2_q,
		In3 => reg3_q,
		S0 => src_s0,
		S1 => src_s1,
		Z => src_reg
	);
	
	reg0 <= reg0_q;
	reg1 <= reg1_q;
	reg2 <= reg2_q;
	reg3 <= reg3_q;
end Behavioral;
