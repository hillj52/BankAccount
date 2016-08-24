package ssa;

public class Account {

	/*
	 * Used to generate account ID's as needed
	 * increments by 100 every time a new account is created
	 */
	protected static int idGenner = 155;
	
	/*
	 * Used to format the output string to 2 decimal places
	 */
	protected static final String DOLLAR_FORMAT = "%.2f";
	
	private int id;	//Unique ID created at time of account creation, cannot be changed
	private double balance;
	private String description;
	
	public Account(int id, String description) {
		this.setId(id);
		this.setBalance(0);
		this.description = description;
	}
	
	public Account(String description) {
		this(idGenner++,description);
	}
	
	public Account() {
		this (idGenner++,"");
	}
	
	public int getId() {
		return this.id;
	}
	
	private void setId(int id) {
		if (id > 0) {
			this.id = id;
		} else {
			this.id = idGenner;
		}
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	private void setBalance(double balance) {
		if (balance >= 0)
			this.balance = balance;
	}
	
	/*
	 * Returns the balance as a String rounded to 2 decimal places
	 */
	public String getBalanceString() {
		return String.format(DOLLAR_FORMAT,this.getBalance());
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * Checks to ensure the amount deposited is positive
	 * then completes the transaction and returns the new
	 * account balance after processing the deposit
	 */
	public double deposit(double amount) {
		if (amount < 0) {
			return this.getBalance();
		}
		this.setBalance(this.getBalance() + amount);
		return this.getBalance();
	}
	
	/*
	 * Checks to ensure the amount to withdraw is positive
	 * then checks to ensure sufficient funds exist in the 
	 * account, if they do it processes the withdraw and
	 * returns the balance of the account after the transaction
	 */
	public double withdraw(double amount) {
		if (amount < 0) {
			return this.getBalance();
		} else if (amount > this.getBalance()) {
			System.out.println("Failed: Insufficient funds!");
			return this.getBalance();
		} else {
			this.setBalance(this.getBalance() - amount);
			return this.getBalance();
		}
	}
	
	/*
	 * Checks to ensure the amount to transfer is positive
	 * then checks to ensure sufficient funds exist, then
	 * processes a transfer of funds from the current account
	 * to the destination account, returning the balance of 
	 * the first account after the transfer is complete
	 */
	public double transferTo(Account destination, double amount) {
		if(amount < 0) {
			return this.getBalance();
		} else if (amount > this.getBalance()) {
			System.out.println("Failed: Insufficient funds!");
			return this.getBalance();
		} else {
			this.withdraw(amount);
			destination.deposit(amount);
			return this.getBalance();
		}
	}
	
	public void transferFrom(Account source, double amount) {
		source.transferTo(this, amount);
	}
	
	public String print() {
		return this.toString();
	}
	
	public String toString() {
		return "Account " + this.id + " balance is $" + this.getBalanceString();
	}
}
