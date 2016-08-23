package ssa;

import java.util.ArrayList;

/*
 * Singleton class used for logging all
 * transactions across all accounts,
 * does not perform any part of any transaction
 * simply provides logging functionality
 */
public class TransactionLog {

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
