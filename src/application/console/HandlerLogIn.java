package application.console;

import java.util.Scanner;
import application.atm.Atm;

public class HandlerLogIn {
	Scanner scanner = new Scanner(System.in);
	
	Atm atm;
	
	HandlerLogIn(Atm atm) {
		this.atm = atm;
	}
	
	public void run() throws Exception {

		try {
			insertId();
			insertPin();
			return; 
		} catch (Exception e) {
			throw e;
		}
	}

	private void insertId() throws Exception {

		String clientId;

		System.out.print("Please type youre id > ");

		for (int i = 0; i < 3; i++) { 
			clientId = scanner.nextLine();
			if (atm.isClientIdValid(clientId)) 
				return;
			System.out.print("Your id could not be identified. It should be 3 digits.\nTry again > ");
		}
		
		throw new Exception("\nSTATUS: < Instant LOG OUT! >\nYour id could not be identified 3 times in a row.");
	}

	private void insertPin() throws Exception {
		
		System.out.print("Please insert youre pin > ");

		for (int i = 0; i < 3; i++) {
			String clientPin = scanner.nextLine();
			try {
				if (isClientPinValid(clientPin))
					return;
				System.out.print("Wrong pin. Please try again > ");
			} catch (Exception e) {
				throw e;
			}
			
		}
		
		throw new Exception("\nSTATUS: < Instant LOG OUT! > \nYou entered wrong pin 3 times in a row.");
	}
	
	private boolean isClientPinValid(String clientPin) throws Exception {
		
		if (atm.isClientPinValid(clientPin)) 
			return true;
		
		if (atm.blockClient(clientPin))
			throw new Exception("\nSTATUS: < BLOCK ACCOUNT! >\nYour account was blocked. Contact Administrator for more details.");
		
		return false;
	}
}
