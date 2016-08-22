package ssa;

public class Mainline {
	public static void main(String[] args) {
		TransactionLog log = new TransactionLog();
		Account checking = new Account(500,"My personal checking account",log);
		Account savings = new Account(1000,"My personal savings account",log);
		
		System.out.println(checking);
		checking.deposit(200);
		System.out.println(checking);
		checking.withdraw(600);
		System.out.println(checking);
		checking.deposit(100);
		System.out.println(checking);
		checking.withdraw(300);
		System.out.println(checking);
		checking.withdraw(200);
		System.out.println(checking);
		
		System.out.println(savings);
		savings.withdraw(750);
		System.out.println(savings);
		savings.withdraw(250);
		System.out.println(savings);
		savings.deposit(200);
		System.out.println(savings);
		
		savings.transferTo(100,checking);
		
		System.out.println(checking);
		System.out.println(savings);
		
		System.out.println(log);
	}
}
