package application;

import java.util.Scanner;

public class HandlerAdminCommand {
	private Atm atm;

	private Scanner scanner = new Scanner(System.in);
	
	private String commandName;
	private String commandArg1;
	private String commandArg2;

	public HandlerAdminCommand(Atm atm) {
		this.atm = atm;
	}

	public void run() {
		String command;

		while (true) {
			command = scanner.nextLine();
			splitCommnad(command);
			if (isLogOut(commandName))
				return;
			chooseCommand(command);
		}
	}

	private void splitCommnad(String s) {
		
		commandName = s.split("_")[1];
		commandArg1 = s.split("_")[2];
		if (s.length() == 4)
			commandArg2 = s.split("_")[3];
	
	}
	
	private boolean isLogOut(String commandName) {
		
		if (commandName.equals("LOGOUT"))
			return true;
		return false;
	}

	private void chooseCommand(String commandName) {
		switch (commandName) {
		case "UNLOCK":
			break;
		case "TRACE":
			break;
		case "ADD":
			break;
		case "WITHDRAW":
			break;
		default:
			break;
		}
	}

}
