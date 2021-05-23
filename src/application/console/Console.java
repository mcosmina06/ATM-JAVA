package application.console;

import java.util.Scanner;

import application.atm.Atm;

public class Console {
	private Atm atm;
	private HandlerLogIn handlerLogIn;
	private HandlerClientOption handlerClientOption;
	private HandlerAdminOption handlerAdminOption;

	public Console(Atm atm) {
		this.atm = atm;
		handlerLogIn = new HandlerLogIn(atm);
	}

	public void run() throws Exception {
		while(true) {
			try {
				login();
				command();
				logout(); 
			} catch (Exception e) {
				System.out.println("\n" + e + "\n");
			}
		}
	}

	private void  login() throws Exception {

		handlerLogIn.run();

		if (!atm.adminHasLogedIn()) 
			isClientBlocked();

		System.out.println("\nSTATUS: < LOG IN sucessfully! >");
		System.out.println("\t\tWelcome!"); 

	}

	private void command() {

		if (atm.adminHasLogedIn()) {
			handlerAdminOption = new HandlerAdminOption(atm);
			handlerAdminOption.run();
		} else {
			handlerClientOption = new HandlerClientOption(atm);
			handlerClientOption.run();
			}

		}

		private void logout() {
			System.out.println("\nSTATUS: < LOG OUT sucessfully! >\n");
		}

		private void isClientBlocked() throws Exception {

			if (atm.isClientBlocked()) { 
				throw new Exception("\nSTATUS: < Account is BLOCKED! >\nPlease contact administrator for more details.");
			}
		}
	}

