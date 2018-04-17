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
	Port ( 
		DA: in std_logic_vector(3 downto 0);	
		AA : in std_logic_vector(3 downto 0);
		BA : in std_logic_vector(3 downto 0);
		
		Clk : in std_logic;
		RW : in std_logic;
		
		data : in std_logic_vector(15 downto 0);
		
		a_out : out std_logic_vector(15 downto 0);
		b_out : out std_logic_vector(15 downto 0);
		
		reg0out : out std_logic_vector(15 downto 0);
		reg1out : out std_logic_vector(15 downto 0);
		reg2out : out std_logic_vector(15 downto 0);
		reg3out : out std_logic_vector(15 downto 0);
		reg4out : out std_logic_vector(15 downto 0);
		reg5out : out std_logic_vector(15 downto 0);
		reg6out : out std_logic_vector(15 downto 0);
		reg7out : out std_logic_vector(15 downto 0);
		reg8out : out std_logic_vector(15 downto 0)
	);
end register_file;

architecture Behavioral of register_file is
-- components

	-- 8 bit Register for register file
	component reg16
		Port(
			D : in std_logic_vector(15 downto 0);
			load : in std_logic;
			Clk : in std_logic;
			Q : out std_logic_vector(15 downto 0)
		);
	end component;
	
	
	-- 4 to 9 Decoder (D Address Select)
	component decoder_4to9
		Port(
			des : in std_logic_vector(3 downto 0);
			RW : in std_logic;
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
	
	
	-- 9 to 1 line multiplexer (ABUS, BBUS)
	component mux9_16bit
		Port(
			In0 : in std_logic_vector(15 downto 0);
			In1 : in std_logic_vector(15 downto 0);
			In2 : in std_logic_vector(15 downto 0);
			In3 : in std_logic_vector(15 downto 0);
			In4 : in std_logic_vector(15 downto 0);
			In5 : in std_logic_vector(15 downto 0);
			In6 : in std_logic_vector(15 downto 0);
			In7 : in std_logic_vector(15 downto 0);
			In8 : in std_logic_vector(15 downto 0);
			src : in std_logic_vector(3 downto 0);
			Z : out std_logic_vector(15 downto 0)
		);
	end component;
	
	-- signals
	-- select signals
	signal 	d_out0, d_out1, d_out2, d_out3, d_out4, d_out5, d_out6 ,d_out7, d_out8  : std_logic;
	--register data signals
	signal reg0_out, reg1_out, reg2_out, reg3_out, reg4_out, reg5_out,
			 reg6_out, reg7_out, reg8_out : std_logic_vector(15 downto 0);
		
	begin
	-- port maps ;-)
	
	-- register 0
	reg00: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out0,
		Q => reg0_out
	);
	
	-- register 1
	reg01: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out1,
		Q => reg1_out
	);
	
	-- register 2
	reg02: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out2,
		Q => reg2_out
	);
	
	-- register 3
	reg03: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out3,
		Q => reg3_out
	);
	
	-- register 4
	reg04: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out4,
		Q => reg4_out
	);
	
	-- register 5
	reg05: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out5,
		Q => reg5_out
	);
	
	-- register 6
	reg06: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out6,
		Q => reg6_out
	);
	
	-- register 7
	reg07: reg16 Port Map(
		D => data,
		Clk => Clk,
		load => d_out7,
		Q => reg7_out
	);
	
	-- register temp
    reg08: reg16 Port Map(
        D => data,
        Clk => Clk,
        load => d_out8,
        Q => reg8_out
    );	
	
	-- Destination register decoder (D decoder)
	des_decoder_4to9: decoder_4to9 Port Map(
		des => DA,
		RW => RW,
		Q0 => d_out0,
		Q1 => d_out1,
		Q2 => d_out2,
		Q3 => d_out3,
		Q4 => d_out4,
		Q5 => d_out5,
		Q6 => d_out6,
		Q7 => d_out7,
		Q8 => d_out8
	);
	
	-- 9 to 1 source register multiplexer (ABUS mux)
	A_mux9_16bit: mux9_16bit Port Map(
		In0 => reg0_out,
		In1 => reg1_out,
		In2 => reg2_out,
		In3 => reg3_out,
		In4 => reg4_out,
		In5 => reg5_out,
		In6 => reg6_out,
		In7 => reg7_out,
		In8 => reg8_out,
		src => AA,
		Z => a_out
	);
	
	-- 9 to 1 source register multiplexer (BBUS mux)
	B_mux9_16bit: mux9_16bit Port Map(
		In0 => reg0_out,
		In1 => reg1_out,
		In2 => reg2_out,
		In3 => reg3_out,
		In4 => reg4_out,
		In5 => reg5_out,
		In6 => reg6_out,
		In7 => reg7_out,
		In8 => reg8_out,
		src => BA,
		Z => b_out
	);
	
	reg0out <= reg0_out;
    reg1out <= reg1_out;
    reg2out <= reg2_out;
    reg3out <= reg3_out;
    reg4out <= reg4_out;
    reg5out <= reg5_out;
    reg6out <= reg6_out;
    reg7out <= reg7_out;
    reg8out <= reg8_out;
	
	
end Behavioral;