package application;

public class Console {
	private Atm atm;
	private HandlerLogIn handlerLogIn;
	private HandlerClientOption handlerClientOption;
	private HandlerAdminCommand handlerAdminCommand;

	Console(Atm atm) {
		this.atm = atm;
		handlerLogIn = new HandlerLogIn(atm);
	}

	public void run() throws Exception {
		//TODO while pana la stop

		try {
			login();
			command();
			logout(); 
		} catch (Exception e) {
			System.out.println("\n" + e + "\n");
			return;
		}
	}

	private void  login() throws Exception {

		handlerLogIn.run();

		isClientBlocked();

		System.out.println("\nSTATUS: < LOG IN sucessfully! >");
		System.out.println("\t\tWelcome!"); 

	}

	private void command() {

		//if (atm.isAdmin(client)) {
		//handlerAdminCommand = new HandlerAdminCommand(atm);
		//} else {
		handlerClientOption = new HandlerClientOption(atm);
		handlerClientOption.run();
		//}

	}

	private void logout() {
		System.out.println("\nSTATUS: < LOG OUT sucessfully! >");
	}

	private void isClientBlocked() throws Exception {

		if (atm.isClientBlocked()) { 
			throw new Exception("\nSTATUS: < Account is BLOCKED! >\nPlease contact administrator for more details.");
		}
	}
}

