package ssa;

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
public class WithdrawlRecord extends TransactionRecord {
	
	public WithdrawlRecord(int accountId, double amount, boolean wasCompleted) {
		super(accountId,amount,wasCompleted);
		this.type = "Withdrawl";
	}
}
