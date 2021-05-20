package application;

import java.util.Scanner;

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
			atm.CompleteClientProfile();
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
			System.out.print("Your id could not be identified. It should be 3 digits > ");
		}

		throw new Exception("\nYour id could not be identified 3 times in a row. Instant LOG OUT");
	}

	private void insertPin() throws Exception {

		String clientPin;
		
		System.out.print("Please insert youre pin > ");

		for (int i = 0; i < 3; i++) {
			clientPin = scanner.nextLine();
			if (atm.isClientPinValid(clientPin)) 
				return;
			System.out.print("Wrong pin. Please try again > ");
		}

		throw new Exception("You entered wrong pin 3 time. Instant LOG OUT");
	}
}
