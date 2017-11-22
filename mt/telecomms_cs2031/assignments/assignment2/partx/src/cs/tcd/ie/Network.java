package cs.tcd.ie;

import tcdIO.Terminal;

public class Network {
	
	public static final String DEFAULT_DST_NODE = "localhost";
	public static final int AMOUNT_OF_ROUTERS = 3;
	public static final int ROUTER_1_PORT = 40789;
	public static final int ROUTER_2_PORT = 40790;
	public static final int ROUTER_3_PORT = 40791;
	
	Router router1;
	Router router2;
	Router router3;
	
	
	public Network() throws Exception {

		
		//Create routers based on the networks pre-defined ports
		
		Terminal terminal1 = new Terminal("Router(1)");
		Terminal terminal2 = new Terminal("Router(2)");
		Terminal terminal3 = new Terminal("Router(3)");
		
		(this.router1 = new Router(terminal1, DEFAULT_DST_NODE, ROUTER_1_PORT)).start();
		(this.router2 = new Router(terminal2, DEFAULT_DST_NODE, ROUTER_2_PORT)).start();
		(this.router3 = new Router(terminal3, DEFAULT_DST_NODE, ROUTER_3_PORT)).start();
		
	}
	
	public static void main(String[] args) {
		try {					
			
			Network network = new Network();
			
		} catch(java.lang.Exception e) {e.printStackTrace();}
	}
}
