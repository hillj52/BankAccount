package ssa;

import java.util.ArrayList;

public class Account {

	/*
	 * Used to generate account ID's as needed
	 * increments by 100 every time a new account is created
	 */
	private static int idGenner = 155;
	
	/*
	 * Used to format the output string to 2 decimal places
	 */
	private static final String DOLLAR_FORMAT = "%.2f";
	
	private int id;	//Unique ID created at time of account creation, cannot be changed
	private double balance;
	private String description;
	private TransactionLog log;
	
	public Account(int id, String description) {
		this.setId(id);
		this.setBalance(0);
		this.description = description;
		this.log = TransactionLog.getInstance();
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
		return String.format(DOLLAR_FORMAT,balance);
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
			log.addTransaction(new DepositRecord(id,amount,false));
			return balance;
		}
		balance += amount;
		log.addTransaction(new DepositRecord(id,amount,true));
		return balance;
	}
	
	/*
	 * Checks to ensure the amount to withdraw is positive
	 * then checks to ensure sufficient funds exist in the 
	 * account, if they do it processes the withdraw and
	 * returns the balance of the account after the transaction
	 */
	public double withdraw(double amount) {
		if (amount < 0) {
			log.addTransaction(new WithdrawlRecord(id,amount,false));
			return balance;
		} else if (amount > balance) {
			System.out.println("Failed: Insufficient funds!");
			log.addTransaction(new WithdrawlRecord(id,amount,false));
			return balance;
		} else {
			balance -= amount;
			log.addTransaction(new WithdrawlRecord(id,amount,true));
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
	public double transferTo(Account destination, double amount) {
		if(amount < 0) {
			log.addTransaction(new TransferRecord(id,destination.getId(),amount,false));
			return balance;
		} else if (amount > balance) {
			System.out.println("Failed: Insufficient funds!");
			log.addTransaction(new TransferRecord(id,destination.getId(),amount,false));
			return balance;
		} else {
			balance -= amount;
			destination.xferDeposit(amount);
			log.addTransaction(new TransferRecord(id,destination.getId(),amount,true));
			return balance;
		}
	}
	
	public void transferFrom(Account source, double amount) {
		source.transferTo(this, amount);
	}
	
	/*
	 * Used to facilitate the transfer function
	 */
	private void xferDeposit(double amount) {
		this.balance += amount;
	}
	
	public String print() {
		return this.toString();
	}
	
	public String toString() {
		return "Account " + this.id + " balance is $" + this.getBalanceString();
	}
}

/*
 * Singleton class used for logging all
 * transactions across all accounts,
 * does not perform any part of any transaction
 * simply provides logging functionality
 */
class TransactionLog {

	private static TransactionLog instance = null;
	
	private ArrayList<TransactionRecord> log;
	
	private TransactionLog() {
		log = new ArrayList<TransactionRecord>();
	}
	
	public static TransactionLog getInstance() {
		if (instance == null) {
			instance = new TransactionLog();
		}
		return instance;
	}
	
	public void addTransaction(TransactionRecord t) {
		log.add(t);
	}
	
	public String toString() {
		String sb = "Transaction Log:\n" +
	                "-----------------------\n";
		for (int i=0;i<log.size();i++) {
			sb += log.get(i) + "\n";
		}
		return sb;
	}
}

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
abstract class TransactionRecord {

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

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
class TransferRecord extends TransactionRecord {

	private int destinationId;
	
	public TransferRecord(int sourceId, int destinationId, double amount, boolean wasCompleted) {
		super(sourceId,amount,wasCompleted);
		this.type = "Transfer";
		this.destinationId = destinationId;
	}
	
	public String toString() {
		return super.toString() + "To account: " + destinationId + "\n";
	}
}

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
class WithdrawlRecord extends TransactionRecord {
	
	public WithdrawlRecord(int accountId, double amount, boolean wasCompleted) {
		super(accountId,amount,wasCompleted);
		this.type = "Withdrawl";
	}
}

/*
 * Just a log record of the transaction
 * does not actually perform any part of
 * any transaction.
 * 
 * JUST A LOG RECORD!
 */
class DepositRecord extends TransactionRecord {
	
	public DepositRecord(int accountId, double amount, boolean wasCompleted) {
		super(accountId,amount,wasCompleted);
		this.type = "Deposit";
	}
}
