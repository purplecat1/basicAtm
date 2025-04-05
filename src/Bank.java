
public class Bank {

	private final int maxAccountSize = 5;
	private int accountCounter = 0;
	Account[] signedAccounts = new Account[maxAccountSize];

	/**
	 * Creates new account if user id is unique.
	 * 
	 * @param new  user id
	 * @param new  password
	 * @param user name
	 * @return true if account is created
	 */
	public boolean createAccount(String userName, String userId, String password) {
		if (accountCounter >= maxAccountSize) return false;
		for (int i = 0; i < accountCounter; i++) {
			if (userId.equals(signedAccounts[i].getUserId())) {
				return false;
			}
		}
		Account newAccount = new Account(userName, userId, password);
		signedAccounts[accountCounter] = newAccount;
		accountCounter++;
		return true;

	}

	/**
	 * Checks the access to account
	 * 
	 * @param user     id
	 * @param password
	 * @throws IllegalArguementException
	 * @return account object if verified
	 */
	public Account login(String userId, String password) {

		for (int i = 0; i < accountCounter; i++) {
			if (userId.equals(signedAccounts[i].getUserId())) {				
				if (password.equals(signedAccounts[i].getPassword())) {
					return signedAccounts[i];
				}
			}
		}
		throw new IllegalArgumentException("Username/password invalid!\ntry again.");

	}
	
	public boolean isFull() {
		return (accountCounter == maxAccountSize );
	}

}
