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

entity datapath is
	Port (
		data_in : in std_logic_vector(15 downto 0);
		
		PC_in : in std_logic_vector(15 downto 0);
		
        SB: in std_logic_vector(2 downto 0);	        
		
		MB : in std_logic;        
        MD : in std_logic;
        MM : in std_logic;
        
        Dsel : in std_logic_vector(3 downto 0);
        Asel : in std_logic_vector(3 downto 0);
        Bsel : in std_logic_vector(3 downto 0);

        
		FS : in std_logic_vector(4 downto 0);
		RW : in std_logic;
		Clk : in std_logic;

		adr_out : out std_logic_vector(15 downto 0);
		data_out : out std_logic_vector(15 downto 0);
		
		reg_0_data_out : out std_logic_vector(15 downto 0);
		reg_1_data_out : out std_logic_vector(15 downto 0);
		reg_2_data_out : out std_logic_vector(15 downto 0);
		reg_3_data_out : out std_logic_vector(15 downto 0);
		reg_4_data_out : out std_logic_vector(15 downto 0);
		reg_5_data_out : out std_logic_vector(15 downto 0);
		reg_6_data_out : out std_logic_vector(15 downto 0);
		reg_7_data_out : out std_logic_vector(15 downto 0);
		reg_8_data_out : out std_logic_vector(15 downto 0);
		
		Vflag : out std_logic;
		Cflag : out std_logic;
		Nflag : out std_logic;
		Zflag : out std_logic
	);
end datapath;

architecture Behavioral of datapath is
-- components

	-- 16 Bit Register File
	component register_file
		Port(
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
	end component;
	
	-- ALU + Shifter Function Unit
    component function_unit
        Port(
            A : in std_logic_vector(15 downto 0);
            B : in std_logic_vector(15 downto 0);
            
            FS : in std_logic_vector(4 downto 0);
            
            V : out std_logic;
            C : out std_logic;
            N : out std_logic;
            Z : out std_logic;    
            
            F : out std_logic_vector(15 downto 0)
        );
    end component;
	
	-- 2 to 1 line multiplexer
	component mux2_16bit
		Port(
			In0 : in std_logic_vector(15 downto 0);
			In1 : in std_logic_vector(15 downto 0);
			s : in std_logic;
			Z : out std_logic_vector(15 downto 0)
		);
	end component;
	
	--Zero Fill
	component Zero_fill
        Port ( 
            SB : in std_logic_vector(2 downto 0);
            zeroFill : out std_logic_vector(15 downto 0)
        );
        end component;
	
	-- signals
	signal Data, BOut, ABus, BBus, Fsig, ConIn : std_logic_vector(15 downto 0);
	
	begin
	-- port Maps ;-)
	
	-- Register File
	reg_file: register_file Port Map(
		AA => Asel,	
		BA => Bsel,
		DA => Dsel,
		
		RW => RW,
		Clk => Clk,
		
		data => Data,
		
		a_out => ABus,
		b_out => BOut,
		
		reg0out => reg_0_data_out,
        reg1out => reg_1_data_out,
        reg2out => reg_2_data_out,
        reg3out => reg_3_data_out,
        reg4out => reg_4_data_out,
        reg5out => reg_5_data_out,
        reg6out => reg_6_data_out,
        reg7out => reg_7_data_out,
        reg8out => reg_8_data_out
	);
	
	-- Function Unit
    funct_unit: function_unit Port Map(
        A => ABus,
        B => BBus,
        
        FS => FS,
        
        V => Vflag,
        C => Cflag,
        N => Nflag,
        Z => Zflag,    
        
        F => Fsig
    );
	
	-- MUX B
	mux_b: mux2_16bit Port Map(
		In0 => BOut,
		In1 => ConIn,
		s => MB,
		Z => BBus
	);

	-- MUX D
	mux_d: mux2_16bit Port Map(
		In0 => Fsig,
		In1 => data_in,
		s => MD,
		Z => Data
	);
	
	-- MUX M
    mux_m: mux2_16bit Port Map(
        In0 => ABus,
        In1 => PC_in,
        s => MM,
        Z => adr_out
    );
    
    --Zero Fill
    z_fill: Zero_fill Port Map(
         SB => SB,
         zeroFill => ConIn
    ); 
    
    data_out <= BBus;
	 
end Behavioral;
