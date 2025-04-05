import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;


enum menuOptions {
}

public class BankGui  {
	
	
	// Class Members 
	
	Bank bank ;
	JFrame jf ;
	JPanel mainMenu,accountMenu,userEntry ;
	JButton toMainMenu2,toMainMenu1,toSubMenu1,createAccount,loginAccount,deposit,withdraw,credit;
	JTextField entryLine ; 
	JLabel mainHeaderLabel,accountHeaderLabel,entryLabel,entryOperation,balanceLabel;
	CardLayout cardLayout ;
	Account loggedAccount ;
	
	// utility
	Scanner scan = new Scanner(System.in);
	

	public BankGui() {
		
		// create a bank object
		// a menu for bank functions and info displays (main menu) 
		// a menu for account functions and info displays (account menu)
		
		bank = new Bank();
		
		
		// ---------------------------bank frame---------------------------
		
		jf = new JFrame("BANK");
		jf.setSize(400,300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new CardLayout()); // to create sub menu
		jf.setVisible(true);
		
		
		
		
		// ---------------------------mainMenu panel---------------------------
		mainMenu = new JPanel();
		mainMenu.setLayout(new GridBagLayout());
		
		//layout declaration
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // Spacing
		
		//mainMenu header label
		mainHeaderLabel = new JLabel("Main Menu");
		mainHeaderLabel.setVisible(true);
		gbc.gridx = 2;
		gbc.gridy = 0;
		mainMenu.add(mainHeaderLabel,gbc);
		

	
        
        //create account button
        createAccount= new JButton("Create Account");
        createAccount.addActionListener( new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		createAccount();
        	}
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainMenu.add(createAccount, gbc);
        
        
        //loginAccount button
        loginAccount = new JButton("User Login");
        loginAccount.addActionListener( new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		loginAccount();
        	}
        });
        gbc.gridx = 3;
        gbc.gridy = 1;
        mainMenu.add(loginAccount,gbc);
        
		
		// ---------------------------accountMenu panel---------------------------
        accountMenu = new JPanel();
        accountMenu.setLayout(new GridBagLayout());
		
        
        //sub menu header
        accountHeaderLabel = new JLabel();
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        accountMenu.add(accountHeaderLabel ,gbc);
        

        
        //main menu button 2 
        toMainMenu1 = new JButton("->main menu");
        toMainMenu1.addActionListener( new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(jf.getContentPane(),"Main");
        	}
        });
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        accountMenu.add(toMainMenu1,gbc);
        

        //account balance
        balanceLabel = new JLabel("Balance: 0 $");
        
        gbc.gridx = 3;
        gbc.gridy = 1;
        accountMenu.add(balanceLabel);

        
        
        
        //deposit button
        deposit = new JButton("Deposit");
        deposit.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(jf.getContentPane(),"Entry");
        		
        		toMainMenu2.setVisible(false);
        		entryOperation.setText("Deposit");
        		
        		entryLine.setVisible(true);
        		entryLabel.setText("Please enter the deposit amount--> ");
        	}
        });
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        accountMenu.add(deposit,gbc);
        
        //withdraw button
        withdraw = new JButton("Withdraw");
        withdraw.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(jf.getContentPane(),"Entry");
        		entryOperation.setText("Withdraw");
        		toMainMenu2.setVisible(false);
        		entryLine.setVisible(true);
        		entryLabel.setText("Please enter the withdraw amount--> ");
        	}
        });
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        accountMenu.add(withdraw,gbc);
        
        //credit button
        credit = new JButton("user credit");
        credit.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(jf.getContentPane(),"Entry");
        		entryOperation.setText("Credit");
        		
        		toMainMenu2.setVisible(false);
        		entryLine.setVisible(true);
        		entryLabel.setText("Please enter the credit amount--> ");
        	}
        });
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        accountMenu.add(credit,gbc);
        
        

        


        
        
        // ---------------------------userEntry panel---------------------------
        userEntry = new JPanel();
        userEntry.setLayout(new GridBagLayout());
        
        //entry operation header login or create account
        entryOperation = new JLabel();


        //text to navigate user
        entryLabel = new JLabel("Enter->  ");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        userEntry.add(entryLabel,gbc);
        
        
        // main menu button 2
        toMainMenu2 = new JButton("-->main menu");
        toMainMenu2.addActionListener( new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(jf.getContentPane(),"Main");
        	}
        });
        //
        gbc.gridx = 2;
        gbc.gridy = 2;
        userEntry.add(toMainMenu2,gbc);
        
        //	user entryLine
        // different behaviors for create and login operations 
        entryLine = new JTextField(10);
        entryLine.addActionListener(new ActionListener() {
			
			int step = 1;
			String userName;
			String userId;
			String password;
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if(entryOperation.getText().equals("Account_Creation")) {

					if(bank.isFull()) {
						entryLine.setVisible(false);
						entryLabel.setText("Unfortunately the bank has full capacity.");
						return;
						
					}

					if(step == 1) {
						userName = entryLine.getText();
						entryLine.setText("");
						entryLabel.setText("Please create your user Id --> ");
						step++;
					} else if ( step == 2) {
						userId = entryLine.getText();
						entryLine.setText("");
						entryLabel.setText("Please create your password --> ");
						step++;
					} else if( step == 3) {
						password = entryLine.getText();
						entryLine.setText("");      
						if (bank.createAccount(userName, userId, password))	{
							entryLabel.setText("Account has been created succesfully :>");
						} else {
							entryLabel.setText("Please choose a unique id !");
						}
						toMainMenu2.setVisible(true);
						entryLine.setVisible(false);;
						step=1; // reset step 
					} else {
						entryLine.setText("");
					}	
				}//end account creation implementation
				// login operation implementation
				else if(entryOperation.getText().equals("Login_Account")) {

					if(step  == 1) {
						userId = entryLine.getText();
						entryLine.setText("");
						entryLabel.setText("Please enter your password --> ");
						step++;
					} else if (step == 2) {
						password = entryLine.getText();
						entryLine.setText("");
						try {
							loggedAccount = bank.login(userId, password);
							System.out.println(loggedAccount.toString());;
							accountMenuManager(loggedAccount);
						} catch (IllegalArgumentException e1) {
							entryLabel.setText(e1.getMessage());
						}
			
						entryLine.setText("");
						entryLine.setVisible(false);
						step=1;
					} else {
						entryLine.setText("");
					}	
					

				}// end login operation 
				// account in operations deposit ,withdraw, credit
				else if(entryOperation.getText().equals("Deposit")) {

					if(step == 1) {
						double enteredMoney = Double.parseDouble(entryLine.getText());
						loggedAccount.deposit(enteredMoney);
						balanceLabel.setText("Balance: "+loggedAccount.getBalance()+" $");
						entryLabel.setText(enteredMoney +"$ added to your account.");
						entryLine.setText("");
						entryLine.setVisible(false);
						toSubMenu1.setVisible(true);
						step=1;
						
					} else {
						entryLine.setText("");
					}	
				}//end of deposit operation
				// withdraw operation
				else if(entryOperation.getText().equals("Withdraw")) {
					if(step == 1) {
						double enteredMoney = Double.parseDouble(entryLine.getText());
						if(loggedAccount.withdraw(enteredMoney)) {
							balanceLabel.setText("Balance: "+loggedAccount.getBalance()+" $");
							entryLabel.setText(enteredMoney +"$ withdrawn from your account.");
						}else {
							entryLabel.setText("Your account has not enough money!");
						}
						entryLine.setText("");
						entryLine.setVisible(false);
						toSubMenu1.setVisible(true);
						step=1;
					}else {
						entryLine.setText("");
					}
				}//end of withdrawn operation
				// credit operation
				else if(entryOperation.getText().equals("Credit")) {
					if(step == 1) {
						double enteredCredit = Double.parseDouble(entryLine.getText());
						if(loggedAccount.credit(enteredCredit)) {
							balanceLabel.setText("Balance: "+loggedAccount.getBalance()+" $");
							entryLabel.setText(enteredCredit +"$ credit used.");
						}else {
							if(loggedAccount.getBalance()<0) {
								entryLabel.setText("Your account is overdrawn !");
							}else {
								entryLabel.setText("Maximum amount of credit available is "+ loggedAccount.getmaxCreditVal()+" $");
							}
						}
						entryLine.setText("");
						entryLine.setVisible(false);
						toSubMenu1.setVisible(true);
						step=1;
					}else {
						entryLine.setText("");
					}
				}
				
			}
        	
        });

        gbc.gridx = 2;
        gbc.gridy = 0;
        userEntry.add(entryLine,gbc);
        
        
        

        
        // to SubMenu button 1
        toSubMenu1 = new JButton("-->sub menu");
        toSubMenu1.addActionListener( new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cardLayout.show(jf.getContentPane(),"Sub");
        	}
        });
        //
        gbc.gridx = 2;
        gbc.gridy = 3;
        toSubMenu1.setVisible(true);
        userEntry.add(toSubMenu1,gbc);
        
        

        
        
        
        // adding panels to the frame
        jf.add(mainMenu, "Main");
        jf.add(accountMenu, "Sub");
		jf.add(userEntry, "Entry");
		
		
		// give the frames layout control to the cardLayout
		cardLayout = (CardLayout) jf.getContentPane().getLayout();
		
		
		
		// associate button inputs with related bank functions and account functions
		// in order to enter a account first should try to access it from bank 
		
	}
	
	//   DATA FİELD END
	
	// METHOD FİELD
	
	public void createAccount() {
		cardLayout.show(jf.getContentPane(),"Entry");
		
		entryOperation.setText("Account_Creation");
		
		entryLine.setVisible(true);
		toSubMenu1.setVisible(false);
		entryLabel.setText("Please create your username --> ");
	}

	
	
	public void loginAccount() {
		cardLayout.show(jf.getContentPane(),"Entry");
		entryOperation.setText("Login_Account");
	
		entryLine.setVisible(true);
		entryLabel.setText("Please enter your id --> ");
	}
	
	public void accountMenuManager(Account loggedAccount) {
		cardLayout.show(jf.getContentPane(),"Sub");
		accountHeaderLabel.setText("Welcome "+loggedAccount.getUserName());
		balanceLabel.setText("Balance: "+loggedAccount.getBalance()+"");
		
	}
	// functions




}
