package application.console;

import java.util.Scanner;

import application.atm.Atm;

public class HandlerAdminOption {
	private Atm atm;

	private Scanner scanner = new Scanner(System.in);
	
	private String commandName;
	private String commandArg1;
	private String commandArg2;
	private String commandArg3;

	public HandlerAdminOption(Atm atm) {
		this.atm = atm;
	}

	public void run() {
		String command;

		while (true) {
			System.out.print("> ");
			command = scanner.nextLine();
			splitCommnad(command);
			if (isLogOut(commandName))
				return;
			chooseCommand(commandName);
		}
	}

	private void splitCommnad(String s) {
		String [] args = s.split("_");
		
		commandName = args[1].trim();
		
		if (args.length >= 3)
			commandArg1 = args[2].trim();
		
		if (args.length >= 4) {
			commandArg2 = args[3].trim();
			commandArg3 = args[4].trim();
		}
	
	}
	
	private boolean isLogOut(String commandName) {
		
		if (commandName.equals("LOGOUT"))
			return true;
		return false;
	}

	private void chooseCommand(String commandName) {
		switch (commandName) {
		case "UNLOCK":
			unlock();
			break;
		case "TRACE":
			trace();
			break;
		case "ADD":
			add();
			break;
		case "WITHDRAW":
			withdraw();
			break;
		default:
			break;
		}
	}
	
	private void unlock() {
		atm.unlockClientAccount(commandArg1);
	}

	private void add() {
		atm.updateSold(Double.parseDouble(commandArg2), commandArg3);
	}

	private void withdraw() {
		atm.updateSold((-1 * Double.parseDouble(commandArg2)), commandArg3);
	}
	
	private void trace() {
		System.out.println(atm.getTraceClient(commandArg1));
	}
}
