package ssa;

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
public abstract class TransactionRecord {

	private static int codeGenner = 1;
	
	private int transactionId;
	private double amount;
	protected String type;
	private int accountId;
	private boolean wasCompleted;
	
	public TransactionRecord(int accountId, double amount, boolean wasCompleted) {
		transactionId = codeGenner++;
		this.amount = amount;
		this.accountId = accountId;
		this.wasCompleted = wasCompleted;
	}
	
	protected String completed() {
		if (wasCompleted) {
			return "Completed";
		} else {
			return "Failed";
		}
	}
	
	public String toString() {
		return "Transaction: " + transactionId + 
				"\nType: " + completed() + " " + type + 
				"\nAmount: " + amount + "\nAccount: " + accountId + "\n";
	}
}
