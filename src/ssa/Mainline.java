package ssa;

public class Mainline {
	public static void main(String[] args) {

		Savings s1 = new Savings();
		s1.deposit(100);
		s1.withdraw(300);
		s1.deposit(900);
		s1.calcDepositInterest(12);
		System.out.println(s1);
	}
}
