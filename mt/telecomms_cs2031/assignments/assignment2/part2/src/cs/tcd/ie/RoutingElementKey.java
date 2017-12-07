package cs.tcd.ie;

public class RoutingElementKey {
	
	int hopCount;
	int nextDest;
	
	
	public RoutingElementKey(int hopCount, int nextDest) {
	    this.hopCount=hopCount;
	    this.nextDest = nextDest;
	  }
	
	public void setNextHop(int nextHop) {
		this.nextDest = nextHop;
	}
}
	