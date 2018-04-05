library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity microprogrammed_control_tb is
--  Port ( );
end microprogrammed_control_tb;

architecture Behavioral of microprogrammed_control_tb is

    component microprogrammed_control
        Port ( 
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

    signal Vflag, Cflag, Nflag, Zflag : std_logic;
    signal instruction : std_logic_vector(15 downto 0);
    signal clk : std_logic;
    signal reset : std_logic;
    signal PC : std_logic_vector(15 downto 0);
    signal TD : std_logic;
    signal TA : std_logic;
    signal TB : std_logic; 
    signal MB : std_logic;
    signal FS : std_logic_vector(4 downto 0);
    signal MD : std_logic;
    signal RW : std_logic;
    signal MM : std_logic;
    signal MW : std_logic;
    
    signal PL : std_logic;
    signal PI : std_logic;
    signal IL : std_logic;
    signal MC : std_logic;
    signal MS : std_logic_vector(2 downto 0);
    signal NA : std_logic_vector(7 downto 0);
    
    signal DR : std_logic_vector(2 downto 0);
    signal SA : std_logic_vector(2 downto 0);
    signal SB : std_logic_vector(2 downto 0);

begin

    UTT: microprogrammed_control
        port map (
            Vflag => Vflag,
            Cflag => Cflag,
            Nflag => Nflag,
            Zflag => Zflag,
            instruction => instruction,
            clk => clk,
            reset => reset,
            PCout => PC,
            TD => TD,
            TA => TA,
            TB => TB,
            MB => MB,
            FS => FS,
            MD => MD,
            RW => RW,
            MM => MM,
            MW => MW,
            
            PL => PL,
            PI => PI,
            IL => IL,
            MC => MC,
            MS => MS,
            NA => NA,

            DR => DR,
            SA => SA,
            SB => SB
        );

process
    begin
        Vflag <= '0';
        Cflag <= '0';
        Nflag <= '0';
        Zflag <= '0';
        
        clk <= '1';
        reset <= '1';
        wait for 50 ns;
        clk <= '0';
        reset <= '0';
        wait for 50ns;
        
        instruction <= x"0000";
        
        clk <= '1';
        wait for 50ns;
        clk <= '0';
        wait for 50ns;
        

        clk <= '1';
        wait for 50ns;
        clk <= '0';
        wait for 50ns;


        clk <= '1';
        wait for 50ns;
        clk <= '0';
        wait for 50ns;
        
        instruction <= x"0400";
        
        clk <= '1';
        wait for 50ns;
        clk <= '0';
        wait for 50ns;
        
        clk <= '1';
        wait for 50ns;
        clk <= '0';
        wait for 50ns;
        
        clk <= '1';
        wait for 50ns;
        clk <= '0';
        wait for 50ns;    
end process;

end Behavioral;
