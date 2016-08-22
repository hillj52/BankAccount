package ssa;

import java.util.ArrayList;

public class TransactionLog {

	private ArrayList<Transaction> log;
	
	public TransactionLog() {
		log = new ArrayList<Transaction>();
	}
	
	public void addTransaction(Transaction t) {
		log.add(t);
	}
	
	public String toString() {
		String sb = "";
		for (int i=0;i<log.size();i++) {
			sb += log.get(i) + "\n";
		}
		return sb;
	}
}
