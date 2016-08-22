package ssa;

public class Transfer extends Transaction {

	private int destinationId;
	
	public Transfer(int sourceId, int destinationId, double amount, boolean wasCompleted) {
		super(sourceId,amount,wasCompleted);
		this.type = "Transfer";
		this.destinationId = destinationId;
	}
	
	public String toString() {
		return super.toString() + "To account: " + destinationId;
	}
}
