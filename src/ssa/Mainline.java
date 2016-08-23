package ssa;

public class Mainline {
	public static void main(String[] args) {
		Account checking1 = new Account();
		checking1.setDescription("My personal checking account");
		checking1.deposit(800.00);
		System.out.println(checking1.print());
		checking1.deposit(300.00);
		System.out.println(checking1.print());
		checking1.withdraw(699.99);
		System.out.println(checking1.print());
		checking1.deposit(149.99);
		System.out.println(checking1.print());
		checking1.withdraw(950.00); // this should fail!
		System.out.println(checking1.print());
		checking1.withdraw(200.00);
		System.out.println(checking1.print()); // balance should be 350
		//
		Account savings1 = new Account("My personal savings account");
		savings1.deposit(1000.00);
		System.out.println(savings1.print());
		savings1.deposit(400.00);
		System.out.println(savings1.print());
		savings1.withdraw(750.00);
		System.out.println(savings1.print());
		savings1.withdraw(250.00);
		System.out.println(savings1.print());
		savings1.deposit(650.00);
		System.out.println(savings1.print()); // balance should be 1050.00
		// the transfer
		checking1.transferFrom(savings1, 100.00); // transfer 100 from savings to checking
		// another account
		Account checking2 = new Account("My slush fund checking account");
		checking2.deposit(300.00);
		System.out.println(checking2.print());
		checking2.withdraw(150.00);
		System.out.println(checking2.print());
		checking2.deposit(250.00);
		System.out.println(checking2.print());

		// more to come. Will be updated during the lab...*

		// print out all account balances
		System.out.println(checking1.print());
		System.out.println(checking2.print());
		System.out.println(savings1.print());
		
		TransactionLog tl = TransactionLog.getInstance();
		System.out.println(tl);
	}
}
