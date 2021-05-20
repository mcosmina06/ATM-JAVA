package application;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Atm atm = new Atm();
		Console console = new Console(atm); 
		// new HandlerClientCommand().run();
		try {
			console.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
