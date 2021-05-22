package application;

import java.util.HashMap;
import java.util.Map;

public class Atm {
	
	private static final int ATM_LIMIT = 30;
	private static final HashMap<String, Double> LIMIT = 
			new HashMap<String, Double>(Map.of("RON", 10000.0, "$", 1000.0, "€", 1000.0));

	private Client client;
	private HandlerClientAccount handlerClientAccount;
	private HashMap<String, Double> sold;
	private HandlerDataBase handlerDataBase;
	private HandlerExchange handlerExchange;

	public Atm() {
		sold = new HashMap<String, Double>(Map.of("RON", 100000.0, "$", 10000.0, "€", 10000.0));
		handlerDataBase = new HandlerDataBase();
		handlerExchange = new HandlerExchange();
		handlerClientAccount = new HandlerClientAccount();
	}
	
	public boolean isClientIdValid(String clientId) {
		if (clientId.length() != 3)
			return false;
		return handlerDataBase.serchClient(clientId);
	}
	
	public boolean isClientPinValid(String clientPin) {
		if (clientPin.length() != 4)
			return false;
		
		return clientPin.equals(client.getPin());
	}
	
	public boolean checkIfPinIsReverse(String clientPin) {
		
		String reverseClientPin = (new StringBuilder(client.getPin()).reverse()).toString();
		
		if (clientPin.equals(reverseClientPin))
			return true;
		return false;
	}
	
	public boolean blockClient(String clientPin) {
		if (checkIfPinIsReverse(clientPin)) {
			client.setBlockedAccount(true);
			return true;
		}
		return false;
	}

	public void CompleteClientProfile() {
		client = new Client();
		handlerDataBase.CompleteClientProfile(client);
	}

	public String sold() {
		return client.getSold() + " " + client.getCoin();
	}

	public int withdraw(double value, String coin) {

		value = getValueConverted(value, coin); 
		
		if (!isSoldSufficient(value, coin))
			return -1;
		
		if (isValueLow(value, coin))
			return -2;
		
		updateClientSold(value, coin);
		updateSold(value, coin);
		
		return 0;
	}
	
	private boolean isValueLow(double value, String coin) {
		
		double limit = handlerExchange.getValueConverted(ATM_LIMIT, coin, "RON");
		
		if (value < limit)
			return true;
		
		return false;
	}
	
	private boolean isSoldSufficient(double value, String coin) {
		
		if (client.getSold() < value)
			return false;
		
		if (value > sold.get(coin) - LIMIT.get(coin))
			return false;
		
		if (sold.get(coin) < LIMIT.get(coin) && (value > (0.1 * sold.get(coin))))
			return false;
		
		return true;
	}

	private void updateSold(double value, String coin) {
		sold.put(coin, sold.get(coin) - value);
	}
	
	private void updateClientSold(double value, String coin) {
		
		client.setSold(client.getSold() - value);
		
		if (!isClientBank()) {
			client.setSold(client.getSold() - (0.1 * value));
			updateSold((0.1 * value), coin);
		}
	}

	public void add(double value, String coin) {

		value = getValueConverted(value, coin);

		client.setSold(value + client.getSold());
	}

	private Double getValueConverted(double value, String coin) {
		if (!client.getCoin().equals(coin))
			value = handlerExchange.getValueConverted(value, client.getCoin(), coin);
		return value;
	}

	public boolean isClientBank() {
		return client.getClientBank();
	}
	
	public void exchange() {
		// TODO Auto-generated method stub

	}

	public boolean isAdmin() { 
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isClientBlocked() {
		return client.getBlockAccount();
	}

}
