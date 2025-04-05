
public class Account {

	// Account identity data

	private String userId;
	private String userName;
	private String password;

	// Account balance data

	private double balance;
	private double maxCreditVal; // max wealth ever * 10

	
	public Account() {}

	public Account(  String userName,String userId,String password) {
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		balance = 0.0;
		maxCreditVal = 0.0;
	}

	public double getBalance() {
		return balance;
	}
	
	public double getmaxCreditVal() {
		return maxCreditVal;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public double getCase() {
		return balance;
	}

	/**
	 * Withdraw money id account affords it
	 * @param amount of money
	 * @return if account balance has enough money
	 */
	public boolean withdraw(double amount) {
		if (amount <= balance) {
			balance -= amount;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Deposit money to the account balance. if no debt and maxCreditVal = max
	 * wealth * 10
	 * @return true always
	 */
	public boolean deposit(double amount) {
		if ((amount + balance) * 10 >= maxCreditVal && balance >= 0)
			maxCreditVal = (amount+balance) * 10;
		balance += amount;
		return true;
	}

	/**
	 * Gives user the desired amount of credit.
	 * The condition for credit is no depth and no more than maxCreditVal.
	 * Credit doesn't appear in balance since it is instantly received to user.
	 * @param amount of credit
	 * @return true if credit if verified
	 */
	public boolean credit(double amount) {
		if (balance >= 0 && amount <= maxCreditVal) {
			balance -= amount;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "UserId: " + userId +
				"\nUserName: "+ userName +
				"\nPassword: "+ password + "\n";
	}

}
