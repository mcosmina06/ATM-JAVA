package application;

import application.atm.Atm;
import application.console.Console;

public class Main {

	public static void main(String[] args) {
		Atm atm = new Atm();
		Console console = new Console(atm); 
		try {
			console.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
