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

entity programme_cpu is
    Port (
        clk : in std_logic;
        reset: in std_logic;
        reg0: out std_logic_vector(15 downto 0);
        reg1: out std_logic_vector(15 downto 0);
        reg2: out std_logic_vector(15 downto 0);
        reg3: out std_logic_vector(15 downto 0);
        reg4: out std_logic_vector(15 downto 0);
        reg5: out std_logic_vector(15 downto 0);
        reg6: out std_logic_vector(15 downto 0);
        reg7: out std_logic_vector(15 downto 0)
    );
end programme_cpu;

architecture Behavioral of programme_cpu is
    
	-- Datapath
    component datapath
        Port(
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
                
            Vflag : out std_logic;
            Cflag : out std_logic;
            Nflag : out std_logic;
            Zflag : out std_logic
        );
    end component;
    
	-- Microprogrammed Control
    component microprogrammed_control
        Port(
        Vflag, Cflag, Nflag, Zflag : in std_logic;
        
        instruction: in std_logic_vector(15 downto 0);
        clk : in std_logic;
        reset: in std_logic;
        
        PCout : out std_logic_vector(15 downto 0);
        
        TD : out std_logic;
        TA : out std_logic;
        TB : out std_logic;
        
        MB : out std_logic;
        FS : out std_logic_vector(4 downto 0);
        MD : out std_logic;
        RW : out std_logic;
        MM : out std_logic;
        MW : out std_logic;
        
        --for testing
        PL : out std_logic;
        PI : out std_logic;
        IL : out std_logic;
        MC : out std_logic;
        MS : out std_logic_vector(2 downto 0);
        NA : out std_logic_vector(7 downto 0);
        
        DR : out std_logic_vector(2 downto 0);
        SA : out std_logic_vector(2 downto 0);
        SB : out std_logic_vector(2 downto 0)
        );
    end component;
    
    component memory
        port (
            address : in std_logic_vector(15 downto 0);
            data_in : in std_logic_vector(15 downto 0);
            Clk : in std_logic;
            MW : in std_logic;
            data_out : out std_logic_vector(15 downto 0)
        );
    end component;

    signal adr_out : std_logic_vector(15 downto 0);
    signal data_out : std_logic_vector(15 downto 0);
    
    signal VFlag : std_logic;
    signal CFlag : std_logic;
    signal NFlag : std_logic;
    signal ZFlag : std_logic;
    
    signal PC : std_logic_vector(15 downto 0);
    
    signal DR, SA, SB : std_logic_vector(2 downto 0);
    signal TD, TA, TB : std_logic;
    signal MB, MM, MW, MD : std_logic;
    
    signal FS : std_logic_vector(4 downto 0);
    
    signal RW : std_logic;
    
    signal memOut : std_logic_vector(15 downto 0);
    signal pcOut : std_logic_vector(15 downto 0);
    
--    signal reg0out : std_logic_vector(15 downto 0); --reg0
--    signal reg1out : std_logic_vector(15 downto 0); --reg1
--    signal reg2out : std_logic_vector(15 downto 0); --reg2
--    signal reg3out : std_logic_vector(15 downto 0); --reg3
--    signal reg4out : std_logic_vector(15 downto 0); --reg4
--    signal reg5out : std_logic_vector(15 downto 0); --reg5
--    signal reg6out : std_logic_vector(15 downto 0); --reg6
--    signal reg7out : std_logic_vector(15 downto 0); --reg7


begin

    MC : microprogrammed_control Port Map(
            
            Vflag => VFlag,
            Cflag => CFlag,
            Nflag => NFlag,
            Zflag => Zflag,
            
            instruction => memOut,
            clk => clk,
            reset => reset,
            
            PCout => pcOut,
            
            MW => MW,
            MM => MM,
            MD => MD,
            MB => MB,
            
            FS => FS,
            RW => RW,
            
            TD => TD,
            TA => TA,
            TB => TB,
            
            DR => DR,
            SA => SA,
            SB => SB
        );

    DP : datapath Port Map(
            
            data_in => memOut,
            PC_in => pcOut,
       
            SB => SB,
            
            MB => MB,
            MD => MD,
            MM => MM,
           
            ASel(2 downto 0) => SA,
            ASel(3) => TA,
            BSel(2 downto 0) => SB,
            BSel(3) => TB,
            DSel(2 downto 0) => DR,
            DSel(3) => TD,
            
            FS => FS,
            RW => RW,
            Clk => clk,
            
            adr_out => adr_out,
            data_out => data_out,
            
            reg_0_data_out => reg0,
            reg_1_data_out => reg1,
            reg_2_data_out => reg2,
            reg_3_data_out => reg3,
            reg_4_data_out => reg4,
            reg_5_data_out => reg5,
            reg_6_data_out => reg6,
            reg_7_data_out => reg7,
            
            VFlag => VFlag,
            CFlag => CFlag,
            NFlag => NFlag,
            ZFlag => ZFlag
        );
        
    mem : memory Port Map(
            address => adr_out,
            data_in => data_out,
            clk => clk,
            MW => MW,
            data_out => memOut
        );

end Behavioral;
