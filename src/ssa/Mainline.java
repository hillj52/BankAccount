package ssa;

public class Mainline {
	public static void main(String[] args) {

		Savings s1 = new Savings();
		s1.setInterestRate(.015);
		s1.deposit(500);
		s1.setMinBalance(500);
		s1.calcDepositInterest(4);
		System.out.println(s1);
		s1.calcDepositInterest(4);
		System.out.println(s1);
		s1.calcDepositInterest(4);
		System.out.println(s1);
	}
}
