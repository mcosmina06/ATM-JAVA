package application.console;

import java.util.Scanner;
import application.atm.Atm;

public class HandlerClientOption {

	private Atm atm;
	private Scanner scanner = new Scanner(System.in);

	public HandlerClientOption(Atm atm) {
		this.atm = atm;
	}

	public void run() {
		while (true) {
			displayCommands();
			String command = scanner.nextLine();
			chooseCommand(command);
			if (isClientLogOut()) {
				atm.clientLogOut();
				return;
		
			}
		}
	}

	private void displayCommands() {
		System.out.println("Please choose an option from below list: ");
		for (ClientCommands command: ClientCommands.values()) {
			System.out.println(command);
		}
		System.out.print("\n> ");
	}

	private void chooseCommand(String command) {
		switch (command) {
		case "1":
			sold();
			break;
		case "2":
			withdraw();
			break;
		case "3":
			add();
			break;
		case "4":
			exchange();
			break;
		default:
			break;
		}
	}

	private boolean isClientLogOut() {
		System.out.print("Want to choose another command? Type '1' if yes and '0' otherwise.\n> ");
		String command = scanner.nextLine();
		if (command.equals("0"))
			return true;
		return false;
	}

	private void sold() {
		System.out.println("SOLD: " + atm.sold());
	}

	private void add() {
		String [] command = getInputFromClient();
		atm.add(Double.parseDouble(command[0]), command[1].trim());
		System.out.println("Money added successfully!");
	}

	private void withdraw() {
		if (!isClientBank())
			return;
		String [] command = getInputFromClient();
		int ret = atm.withdraw(Double.parseDouble(command[0]), command[1].trim());
		switch(ret) {
		case 0:
			System.out.println("Money withdrawn successfully!");
			break;
		case -1:
			System.out.println("Insufficient funds!");
			break;
		case -2:
			System.out.println("Value entered is too small!");
			break;
		}

	}

	private String [] getInputFromClient() {
		System.out.print("Enter the value followed by the coin. Coin should be 'RON', '$' or '€' > ");
		return scanner.nextLine().split(" ");
	}

	private boolean isClientBank() {
		if (atm.isClientBank())
			return true;
		System.out.print("You're not a bank client. You will be charged for withdrawals."
				+ "\nType '1' if want to continue, '0' otherwise.> ");
		if (scanner.nextLine().equals("1"))
			return true;
		return false;
	}
	
	private void exchange() {
		String [] command1 = getInputFromClient();
		System.out.print("Enter the coin of exchange. Coin should be 'RON', '$' or '€' > ");
		String command2 = scanner.nextLine();
		int ret = atm.exchange(Double.parseDouble(command1[0]), command1[1].trim(), command2.trim());
		switch(ret) {
		case 0:
			System.out.println("Money exchanged successfully!");
			break;
		case -1:
			System.out.println("Insufficient funds!");
			break;
		case -2:
			System.out.println("Value entered is too small!");
			break;
		}
	}
}
