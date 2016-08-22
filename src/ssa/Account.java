package ssa;

public class Account {

	/*
	 * Used to generate account ID's as needed
	 * increments by 100 every time a new account is created
	 */
	private static int idGenner = 100;
	
	private int id;	//Unique ID created at time of account creation, cannot be changed
	private double balance;
	private String description;
	private TransactionLog log;
	
	public Account(double balance, String description, TransactionLog log) {
		this.id = idGenner;
		idGenner += 100; //Increment the idGenner for the next instance of class
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
	
	/*
	 * Returns the balance as a String rounded to 2 decimal places
	 */
	public String getBalanceString() {
		return format(balance);
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * Returns the amount input as a String rounded to 2 decimal places
	 */
	private String format(double d) {
		return String.format("%.2f", d);
	}
	
	/*
	 * Checks to ensure the amount deposited is positive
	 * then completes the transaction and returns the new
	 * account balance after processing the deposit
	 */
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
	
	/*
	 * Checks to ensure the amount to withdraw is positive
	 * then checks to ensure sufficient funds exist in the 
	 * account, if they do it processes the withdraw and
	 * returns the balance of the account after the transaction
	 */
	public double withdraw(double amount) {
		System.out.println("Attempting to withdraw $" + format(amount) + " from account number " + id);
		if (amount < 0) {
			System.out.println("Failed: Withdrawl must be positive amount");
			log.addTransaction(new Withdrawl(id,amount,false));
			return balance;
		} else if (amount > balance) {
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
	
	/*
	 * Checks to ensure the amount to transfer is positive
	 * then checks to ensure sufficient funds exist, then
	 * processes a transfer of funds from the current account
	 * to the destination account, returning the balance of 
	 * the first account after the transfer is complete
	 */
	public double transferTo(double amount,Account destination) {
		System.out.println("Attempting to transfer $" + format(amount) + 
				" to account number " + destination.getId());
		if(amount < 0) {
			System.out.println("Failed: Transfer amount must be positive");
			log.addTransaction(new Transfer(id,destination.getId(),amount,false));
			return balance;
		} else if (amount > balance) {
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
	
	/*
	 * Used to facilitate the transfer function
	 */
	private void xferDeposit(double amount) {
		balance += amount;
	}
	
	public String toString() {
		return "Account Id: " + id +"\nDescription: "
				+ description + "\nBalance: $" + getBalanceString() + "\n";
	}
}
