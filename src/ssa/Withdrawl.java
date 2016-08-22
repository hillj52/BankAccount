package ssa;

public class Withdrawl extends Transaction {
	
	public Withdrawl(int accountId, double amount, boolean wasCompleted) {
		super(accountId,amount,wasCompleted);
		this.type = "Withdrawl";
	}
}
