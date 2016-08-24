package ssa;

public class Savings extends Account {
	
	private double monthlyInterestRate;
	private double minBalance;
	private double interestAccrued;
	
	public Savings(int id, String description) {
		super(id,description);
		this.setInterestRate(.015);
		this.setMinBalance(0);
		this.setInterestAccrued(0);
	}
	
	public Savings(String description) {
		this(idGenner++,description);
	}
	
	public Savings() {
		this(idGenner++,"");
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
	
	public void setMinBalance(double minBalance) {
		if (minBalance >= 0) {
			this.minBalance = minBalance;
		}
	}
	
	public double getMinBalance() {
		return this.minBalance;
	}
	
	private void setInterestAccrued(double amount) {
		if (amount >= 0) {
			this.interestAccrued = amount;
		}
	}
	
	public double getInterestAccrued() {
		return this.interestAccrued;
	}
	
	public String getInterestAccruedString() {
		return String.format(DOLLAR_FORMAT,this.getInterestAccrued());
	}
	
	public double calcDepositInterest(int months) {
		if (months > 0 && this.getBalance() >= this.getMinBalance()) {
			double interestEarned = this.getMonthlyInterestRate() * months * this.getBalance();
			this.deposit(interestEarned);
			this.setInterestAccrued(this.getInterestAccrued()+interestEarned);
			return interestEarned;
		}
		return 0;
	}
	
	public String printInterestRate() {
		return this.getInterestRate()*100 + "%";
	}
	
	public String print() {
		return this.toString();
	}
	
	public String toString() {
		return "Savings " + super.toString() + ", Interest Rate is " + this.printInterestRate()
		+ ", Total interest accrued is " + this.getInterestAccruedString();
	}
}
