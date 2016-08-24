package ssa;

public class Savings extends Account {
	
	private double monthlyInterestRate;
	
	public Savings(int id, String description) {
		super(id,description);
		this.setInterestRate(.015);
	}
	
	public Savings(String description) {
		super(description);
		this.setInterestRate(.015);
	}
	
	public Savings() {
		super();
		this.setInterestRate(.015);
	}
	
	public double getInterestRate() {
		return this.monthlyInterestRate * 12;
	}
	
	public void setInterestRate(double interestRate) {
		if (interestRate >= 0) {
			this.monthlyInterestRate = interestRate / 12;
		}
	}
	
	private double getMonthlyInterestRate() {
		return this.monthlyInterestRate;
	}
	
	public double calcDepositInterest(int months) {
		double interestEarned = this.getMonthlyInterestRate() * months * super.getBalance();
		super.deposit(interestEarned);
		return interestEarned;
	}
	
	public String printInterestRate() {
		return this.getInterestRate()*100 + "%";
	}
	
	public String print() {
		return this.toString();
	}
	
	public String toString() {
		return "Savings " + super.toString();
	}
}
