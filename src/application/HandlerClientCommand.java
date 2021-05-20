package application;

import java.util.Scanner;

public class HandlerClientCommand {

	private Atm atm;

	private Scanner scanner = new Scanner(System.in);

	public HandlerClientCommand(Atm atm) {
		this.atm = atm;
	}

	public void run() {
		
		while (true) {
			displayCommands();
			
			String command = scanner.nextLine();
			chooseCommand(command);
			
			if (isClientLogOut())
				return;
		}

	}

	private void displayCommands() {

		System.out.println("Please choose an action from below list: ");

		for (ClientCommands command: ClientCommands.values()) {
			System.out.println(command);
		}
		
		System.out.print("\n> ");
	}

	private void chooseCommand(String command) {

		switch (command) {
		case "1":
			atm.sold();
			break;
		case "2":
			atm.withdraw();
			break;
		case "3":
			atm.add();
			break;
		case "4":
			atm.exchange();
			break;
		default:
			break;
		}
	}
	
	private boolean isClientLogOut() {
		
		System.out.println("Want to choose another command? Type '1' if yes and '0' if no\n> ");
		
		String command = scanner.nextLine();
		
		if (command.equals("0"))
			return true;
		return false;
	}

}
