package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HandlerDataBase {
	
	private String [] lastLineRead;
	
	public boolean serchClient(String clientID) {
		try (BufferedReader reader = new BufferedReader(new FileReader("DataBase.txt"))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				lastLineRead = line.split(","); 
				if (clientID.equals(lastLineRead[0]))
					return true;
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.out.println("Error reading");
		}
		
		return false;
	}
	
	public boolean checkPin(String clientPin) {
		return clientPin.equals(lastLineRead[1].trim());
	}

	public void CompleteClientProfile(Client client) { 
		client.setId(lastLineRead[0]);
		client.setPin(lastLineRead[1]);
		client.setSold(Double.parseDouble(lastLineRead[2]));
		client.setCoin(lastLineRead[3]);
		client.setBlockedAccount(Boolean.parseBoolean(lastLineRead[4]));
		client.setClientBank(Boolean.parseBoolean(lastLineRead[5]));
	}
}
