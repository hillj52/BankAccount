package ssa;

public class Mainline {
	public static void main(String[] args) {

		Savings s1 = new Savings();
		s1.setInterestRate(.03);
		s1.deposit(500);
		s1.calcDepositInterest(4);
		System.out.println(s1);
	}
}
