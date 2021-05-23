package application.atm;

import application.user.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class HandlerClient {
	private static final String clientsHistoryFileName = "ClientsBankHistory";
	private static final String othersHistoryFileName = "OthersBankHistory";
	
	private Client client;
	private History history;
	private HandlerDataBase handlerDataBase;


	public HandlerClient() {
		history = new History(clientsHistoryFileName, othersHistoryFileName);
		handlerDataBase = new HandlerDataBase();
	}

	protected boolean isClientIdValid(String clientId) {
		if (clientId.length() != 3)
			return false;
		return handlerDataBase.serchClient(clientId);
	}

	protected void initClient() {
		client = new Client();
		handlerDataBase.completeClientProfile(client);
	}

	protected boolean checkIfPinIsReverse(String clientPin) {
		String reverseClientPin = (new StringBuilder(client.getPin()).reverse()).toString();

		if (clientPin.equals(reverseClientPin))
			return true;
	
		return false;
	}
	
	protected boolean isClientPinValid(String clientPin) {
		initClient();
		
		if (clientPin.length() != 4)
			return false;

		if (clientPin.equals(client.getPin())) {
			return true;
		}

		return false;
	}

	protected boolean blockClient(String clientPin) {
		if (checkIfPinIsReverse(clientPin)) {
			client.setBlockedAccount(true);
			history.update("ID: " + client.getId() + "\t" + "ACTION: blocked account \t"
					+ "TIME: " + LocalDateTime.now(), client.getClientBank());
			handlerDataBase.updateClientProfile(client);
			
			return true;
		}
		
		history.update("ID: " + client.getId() + "\t" + "ACTION: login \t" 
				+ "TIME: " + LocalDateTime.now(), client.getClientBank());
		
		return false;
	}

	protected String sold() {
		history.update("ID: " + client.getId() + "\t" + "ACTION: sold \t" 
				+ "TIME: " + LocalDateTime.now(),  client.getClientBank());
		return client.getSold() + " " + client.getCoin();
	}

	protected boolean needToConvert(String coin) {
		return !client.getCoin().equals(coin);
	}
	
	protected boolean isSoldSufficient(double value, String coin) {
		if (client.getSold() < value)
			return false;
		return true;
	}

	protected void withdraw(double value) {
		client.setSold(client.getSold() - value);

		if (!client.getClientBank()) {
			client.setSold(client.getSold() - (0.1 * value));
		}

		history.update("ID: " + client.getId() + "\t" + "ACTION: withdraw \t" + value 
				+ "\t TIME: " + LocalDateTime.now(), client.getClientBank());
	}

	protected void add(double value) {
		client.setSold(value + client.getSold());
		history.update("ID: " + client.getId() + "\t" + "ACTION: add \t" + value 
				+ "\t TIME: " + LocalDateTime.now(), client.getClientBank());
	}


	protected void exchange(double value, String coin, String coinOfExchange) {
		history.update("ID: " + client.getId() + "\t" + "ACTION: exchange " + value + " " 
				+ coin + " in " + coinOfExchange+ "\t TIME: " + LocalDateTime.now(), client.getClientBank());
	}
 
	protected void logOut() {
		history.update("ID: " + client.getId() + "\t" + "ACTION: logout \t" 
				+ "TIME: " + LocalDateTime.now(), client.getClientBank());
		handlerDataBase.updateClientProfile(client);
	}

	protected boolean isClientBank() {
		return client.getClientBank();
	}

	protected String getCoin() {
		return client.getCoin();
	}
	
	protected boolean isClientBlocked() {
		return client.getBlockAccount();
	}
	
	private String computeClientHistory(String clientId, String fileName) {
		StringBuilder clientHistory = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String idLine = line.split("\t")[0].split(" ")[1].trim();
				if (clientId.equals(idLine)) {
					clientHistory.append(line + "\n");
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.out.println("Error reading");
		}
		
		return clientHistory.toString();
	}
	
	protected String getClientHistory(String clientId) {
		String fileName = clientsHistoryFileName;
		
		handlerDataBase.serchClient(clientId);

		if (!handlerDataBase.isClientBank())
			fileName = othersHistoryFileName;
		
		return computeClientHistory(clientId, fileName);
	}

	public void unlockClientAccount(String clientId) {
		handlerDataBase.serchClient(clientId);
		
		initClient();
		
		client.setBlockedAccount(false);
		
		handlerDataBase.updateClientProfile(client);
	}
}
