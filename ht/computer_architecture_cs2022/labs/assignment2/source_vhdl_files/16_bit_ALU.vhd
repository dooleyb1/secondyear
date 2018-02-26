library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- 16-bit ALU 
entity 16_bit_ALU is
 port (
   A: in std_logic_vector(15 downto 0);       -- A data input of the 16-bit ALU
   B: in std_logic_vector(15 downto 0);       -- B data input of the 16-bit ALU
   ALUctrl: in std_logic_vector(3 downto 0);  -- ALUctrl control input of the 16-bit ALU 
   ALUOUT: out std_logic_vector(15 downto 0); -- 16-bit data output of the 16-bit ALU 
   V : out std_logic;                         -- Overflow Flag out
   C : out std_logic;                         -- Carry Flag out
   N : out std_logic;                         -- Negative Flag out
   Z : out std_logic                          -- Zero Flag out
   );
end 16_bit_ALU;

architecture Behavioral of 16_bit_ALU is

--16 bit ripple adder component declaration
component 16_bit_ripple_adder
Port ( input1 : in STD_LOGIC_VECTOR (15 downto 0);
input2 : in STD_LOGIC_VECTOR (15 downto 0);
Cin : in STD_LOGIC;
S : out STD_LOGIC_VECTOR (15 downto 0);
Cout : out STD_LOGIC);
end component;


signal BBUS_not: std_logic_vector(15 downto 0);
signal tmp_out1: std_logic_vector(15 downto 0);
signal tmp_out2: std_logic_vector(15 downto 0);
signal tmp_out3: std_logic_vector(15 downto 0);
signal tmp_out4: std_logic_vector(15 downto 0);
signal tmp: std_logic_vector(15 downto 0);

begin
-- instantiate Verilog N-bit Adder in VHDL code 

-- ripple adder 1 (A+1)
	u1_16_bit_ripple_adder: 16_bit_ripple_adder PORT MAP(
		input1 => ABUS,
		input2 => x"0000",
		Cin => x"0001",
		S => tmp_out_1,
		Cout => C
	);

-- ripple adder 2 (ABUS + BBUS)
	u2_16_bit_ripple_adder: 16_bit_ripple_adder PORT MAP(
		input1 => ABUS,
		input2 => BBUS,
		Cin => x"0000",
		S => tmp_out_2,
		Cout => C
	);
	
-- ripple adder 3 (ABUS + BBUS + 1)
	u3_16_bit_ripple_adder: 16_bit_ripple_adder PORT MAP(
		input1 => ABUS,
		input2 => BBUS,
		Cin => x"0001",
		S => tmp_out_3,
		Cout => C
	);
	
-- ripple adder 4 (ABUS + BBUS')
	u4_16_bit_ripple_adder: 16_bit_ripple_adder PORT MAP(
		input1 => ABUS,
		input2 => BBUS_not,
		Cin => x"0000",
		S => tmp_out_4,
		Cout => C
	);
	
-- ripple adder 5 (ABUS + BBUS' + 1)
	u5_16_bit_ripple_adder: 16_bit_ripple_adder PORT MAP(
		input1 => ABUS,
		input2 => BBUS_not,
		Cin => x"0001",
		S => tmp_out_5,
		Cout => C
	);
	
-- ripple adder 6 (ABUS -1)
	u6_16_bit_ripple_adder: 16_bit_ripple_adder PORT MAP(
		input1 => ABUS,
		input2 => x"FFFF",
		Cin => x"0000",
		S => tmp_out_6,
		Cout => C
		
	);
	
-- (~BBUS)
	BBUS_not <= not BBUS;
	
	 
-- Other instructions of the 16-bit ALU in VHDL 
process(ALUctrl,ABUS,BBUS,tmp_out1,tmp)
begin 
case(ALUctrl) is
 when "0000" =>  ALUOUT <= ABUS;	  		-- G = A
 when "0001" =>  ALUOUT <= tmp_out_1;		-- G = A+1 
 when "0010" =>  ALUOUT <= tmp_out_2;	 	-- G = A+B
 when "0011" =>  ALUOUT <= tmp_out_3;		-- G = A+B+1 
 when "0100" =>  ALUOUT <= tmp_out_4;	 	-- G = A+B'
 when "0101" =>  ALUOUT <= tmp_out_5; 		-- G = A+B'+1
 when "0110" =>  ALUOUT <= tmp_out_6; 		-- G = A-1
 when "0111" =>  ALUOUT <= ABUS;	 		-- G = A
 when "1000" =>  ALUOUT <= A and B; 		-- G = A and B
 when "1010" =>  ALUOUT <= A or B; 			-- G = A or B
 when "1100" =>  ALUOUT <= A xor B; 		-- G = A xor B
 when "1110" =>  ALUOUT <= not A;	 		-- G = A'
 when others => ALUOUT <= ABUS; 
 end case;
 
 --Handle Flags
 
 --V = Cout xor Cin
 V = C xor ALUctrl(0)
 
 --If ALUOUT is 0000...0 then set zero flag
 if ALUOUT = x"0000" then
 	Z <= 1;
 
 if ALUOUT(17) = 1 then
 	N <= 1;
 	
end process;

end Behavioral;
