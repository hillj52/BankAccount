package ssa;

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
public class TransferRecord extends TransactionRecord {

	private int destinationId;
	
	public TransferRecord(int sourceId, int destinationId, double amount, boolean wasCompleted) {
		super(sourceId,amount,wasCompleted);
		this.type = "Transfer";
		this.destinationId = destinationId;
	}
	
	public String toString() {
		return super.toString() + "To account: " + destinationId + "\n";
	}
}
