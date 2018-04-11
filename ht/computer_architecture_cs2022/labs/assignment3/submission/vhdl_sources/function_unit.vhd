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
use IEEE.std_logic_1164.ALL;
use IEEE.std_logic_ARITH.ALL;
use IEEE.std_logic_UNSIGNED.ALL;

entity function_unit is
	Port ( 
		A : in std_logic_vector(15 downto 0);
		B : in std_logic_vector(15 downto 0);
		FS : in std_logic_vector(4 downto 0);
		V : out std_logic;
		C : out std_logic;
		N : out std_logic;
		Z : out std_logic;	
		F : out std_logic_vector(15 downto 0)
	);
end function_unit;

architecture Behavioral of function_unit is
-- components

	-- 16bit Arithmetic Logic Unit (ALU)
	component alu_16bit
		Port(
			A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit ALU
	   		B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit ALU
	   		Gsel: in std_logic_vector(3 downto 0);     -- FS control input of the 16-bit ALU 
		   	F: out std_logic_vector(15 downto 0);      -- 16-bit data output of the 16-bit ALU 
		   	V : out std_logic;                         -- Overflow Flag out
		   	C : out std_logic;                         -- Carry Flag out
		   	N : out std_logic;                         -- Negative Flag out
		   	Z : out std_logic                          -- Zero Flag Out
		);
	end component;
	
	
	-- 16bit Shifter
	component shifter_16bit
		Port(
			B : in std_logic_vector (15 downto 0);
			FS : in std_logic_vector (4 downto 0);
			Lr : in std_logic;
			Ll : in std_logic;
			H : out std_logic_vector (15 downto 0)
		);
	end component;
	
	-- 2 to 1 line multiplexer
	component mux2_16bit
		Port(
			In0 : IN std_logic_vector(15 downto 0);
			In1 : IN std_logic_vector(15 downto 0);
			s : IN std_logic;
			Z : OUT std_logic_vector(15 downto 0)
		);
	end component;
	
	-- signals
	signal ALU_out, shifter_out, mux_out : std_logic_vector(15 downto 0);
	
begin
	-- Arithmetic Logic Unit (ALU)
	alu: alu_16bit 
	Port Map(
		A => A,
		B => B,
		Gsel(0) => FS(0),
		Gsel(1) => FS(1),
		Gsel(2) => FS(2),
		Gsel(3) => FS(3),
		F => ALU_out,
		V => V,
		C => C,
		N => N, 
		Z => Z
	);
	
	-- 16 Bit Shifter
	shifter: shifter_16bit 
	Port Map(
		B => B,
		FS => FS,
		Lr => '0',
		Ll => '0',
		H => shifter_out
	);
	
	-- 2 to 1 mux
	MUXF: mux2_16bit 
	Port Map(
		In0 => ALU_out,
		In1 => shifter_out,
		s => FS(4),
		Z => F
	);
  

end Behavioral;
