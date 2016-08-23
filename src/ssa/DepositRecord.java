package ssa;

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
public class DepositRecord extends TransactionRecord {
	
	public DepositRecord(int accountId, double amount, boolean wasCompleted) {
		super(accountId,amount,wasCompleted);
		this.type = "Deposit";
	}
}
