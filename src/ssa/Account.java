package ssa;

public class Account {

	private static int idGenner = 100;
	
	private int id;
	private double balance;
	private String description;
	private TransactionLog log;
	
	public Account(double balance, String description, TransactionLog log) {
		this.id = idGenner;
		idGenner += 100;
		this.balance = balance;
		this.description = description;
		this.log = log;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public String getBalanceString() {
		return format(balance);
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	private String format(double d) {
		return String.format("%.2f", d);
	}
	
	public double deposit(double amount) {
		System.out.println("Depositing $" + format(amount) + " in account number " + id);
		if (amount < 0) {
			log.addTransaction(new Deposit(id,amount,false));
			return balance;
		}
		balance += amount;
		log.addTransaction(new Deposit(id,amount,true));
		return balance;
	}
	
	public double withdraw(double amount) {
		System.out.println("Attempting to withdraw $" + format(amount) + " from account number " + id);
		if (amount < 0) {
			System.out.println("Failed: Withdrawl must be positive amount");
			log.addTransaction(new Withdrawl(id,amount,false));
			return balance;
		}
		if (amount > balance) {
			System.out.println("Failed: Insufficient funds! Account only has $" + 
					getBalanceString() + " available for withdrawl");
			log.addTransaction(new Withdrawl(id,amount,false));
			return balance;
		} else {
			System.out.println("Withdrawl succesful");
			balance -= amount;
			log.addTransaction(new Withdrawl(id,amount,true));
			return balance;
		}
	}
	
	public double transferTo(double amount,Account destination) {
		System.out.println("Attempting to transfer $" + format(amount) + 
				" to account number " + destination.getId());
		if(amount < 0) {
			System.out.println("Failed: Transfer amount must be positive");
			log.addTransaction(new Transfer(id,destination.getId(),amount,false));
			return balance;
		}
		if (amount > balance) {
			System.out.println("Failed: Insufficient funds! Account only has $" + 
					getBalanceString() + " available for transfer");
			log.addTransaction(new Transfer(id,destination.getId(),amount,false));
			return balance;
		} else {
			balance -= amount;
			destination.xferDeposit(amount);
			System.out.println("Transfer succesful");
			log.addTransaction(new Transfer(id,destination.getId(),amount,true));
			return balance;
		}
	}
	
	private void xferDeposit(double amount) {
		balance += amount;
	}
	
	public String toString() {
		return "Account Id: " + id +"\nDescription: "
				+ description + "\nBalance: $" + getBalanceString() + "\n";
	}
}
