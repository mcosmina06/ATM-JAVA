package application;

import java.util.Scanner;

public class Console {
	Scanner scanner = new Scanner(System.in);
	
	public void displayCommands() {
		
		for (Commands command: Commands.values()) {
			System.out.println(command);
		}
	}
	
	public Client login() {
		
		System.out.print("Please type youre id > ");
		String clientId = scanner.nextLine();
		
		System.out.print("Please insert youre pin > ");
		String pinId = scanner.nextLine();
		
		//check client pin
		
		return new Client(clientId, pinId);
	}
	
	public void run() {
		Pair<String, String> client = login();
		System.out.println("Client with id: " + client.getValue() + " has login...");
		displayCommands();

	}
}
