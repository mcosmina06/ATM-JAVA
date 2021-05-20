package application;

import java.util.Scanner;

public class Console {
	private Atm atm;
	private HandlerLogIn handlerLogIn;
	private HandlerClientCommand handlerClientCommand;
	private HandlerAdminCommand handlerAdminCommand;

	Console(Atm atm) {
		this.atm = atm;
	}

	public void run() throws Exception {
		//TODO while pana la stop
		
		login();
		command();
		logout(); 
		
	}

	private void login() throws Exception {
		
		handlerLogIn = new HandlerLogIn(atm);
		handlerLogIn.run();
		
		System.out.println("\nClient ID: " + atm.getClient().getId() + "\tSTATUS: < LOG IN sucessfully! >");
		System.out.println("\t\tWelcome!");
	}

	private void command() {
		
		//if (atm.isAdmin(client)) {
			//handlerAdminCommand = new HandlerAdminCommand(atm);
		//} else {
			handlerClientCommand = new HandlerClientCommand(atm);
			handlerClientCommand.run();
		//}
		
	}
	
	private void logout() {
		System.out.println("\nClient ID: " + atm.getClient().getId() + "\tSTATUS: < LOG OUT sucessfully! >");
	}
	
}

