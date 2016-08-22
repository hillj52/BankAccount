package ssa;

public class Deposit extends Transaction {
	
	public Deposit(int accountId, double amount, boolean wasCompleted) {
		super(accountId,amount,wasCompleted);
		this.type = "Deposit";
	}
}
