package application.atm;

import java.util.HashMap;
import java.util.Map;

public class Atm {

	private static final int ATM_LIMIT = 30;
	private static final String ADMIN_ID = "admin";
	private static final String ADMIN_PIN = "admin";
	private static final HashMap<String, Double> LIMIT = 
			new HashMap<String, Double>(Map.of("RON", 10000.0, "$", 1000.0, "€", 1000.0));

	private boolean admin;
	private HandlerClient handlerClient;
	private HashMap<String, Double> sold;
	private HandlerExchange handlerExchange;

	public Atm() {
		admin = false;
		sold = new HashMap<String, Double>(Map.of("RON", 100000.0, "$", 10000.0, "€", 10000.0));
		handlerExchange = new HandlerExchange();
		handlerClient = new HandlerClient();
	}

	public boolean isClientIdValid(String clientId) {
		if (clientId.equals(ADMIN_ID))
			return true;
		return handlerClient.isClientIdValid(clientId);
	}

	public boolean isClientPinValid(String clientPin) {
		if (clientPin.equals(ADMIN_PIN)) {
			admin = true;
			return true;
		}
		return handlerClient.isClientPinValid(clientPin);
	}

	public boolean blockClient(String clientPin) {
		if (admin)
			return false;
		return handlerClient.blockClient(clientPin);
	}

	public String sold() {
		return handlerClient.sold();
	}

	public int withdraw(double value, String coin) {
		double convertedValue = value;

		if (!handlerClient.needToConvert(coin))
			convertedValue = handlerExchange.getValueConverted(value, handlerClient.getCoin(), coin);

		if (!handlerClient.isSoldSufficient(convertedValue, coin) || !isSoldSufficient(value, coin))
			return -1;

		if (isValueLow(value, coin))
			return -2;

		handlerClient.withdraw(convertedValue);
		updateSold((-1 * value), coin);

		return 0;
	}

	private boolean isValueLow(double value, String coin) {
		double limit = handlerExchange.getValueConverted(ATM_LIMIT, coin, "RON");

		if (value < limit)
			return true;

		return false;
	}

	private boolean isSoldSufficient(double value, String coin) {
		if (value > sold.get(coin) - LIMIT.get(coin))
			return false;

		if (sold.get(coin) < LIMIT.get(coin) && (value > (0.1 * sold.get(coin))))
			return false;

		return true;
	}

	public void updateSold(double value, String coin) {
		sold.put(coin, sold.get(coin) + value);
	}

	public void add(double value, String coin) {
		if (handlerClient.needToConvert(coin))
			value = handlerExchange.getValueConverted(value, handlerClient.getCoin(), coin);
		handlerClient.add(value);
	}

	public boolean isClientBank() {
		return handlerClient.isClientBank();
	}

	public int exchange(double value, String coin, String coinOfExchange) {
		value = handlerExchange.getValueConverted(value, coinOfExchange, coin);

		if (!isSoldSufficient(value, coinOfExchange))
			return -1;

		if (isValueLow(value, coinOfExchange))
			return -2;

		updateSold((-1 * value), coin);
		updateSold(value, coinOfExchange);

		handlerClient.exchange(value, coin, coinOfExchange);

		return 0;
	}

	public boolean isClientBlocked() {
		return handlerClient.isClientBlocked();
	}

	public void clientLogOut() {
		handlerClient.logOut();	
	}

	public boolean adminHasLogedIn() {
		return admin;
	}

	public String getTraceClient(String clientId) {
		return handlerClient.getClientHistory(clientId);
	}

	public void unlockClientAccount(String clientId) {
		handlerClient.unlockClientAccount(clientId);
	}
}
