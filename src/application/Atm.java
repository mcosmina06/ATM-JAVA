package application;

public class Atm {
	private static final String BANK_NAME = "MY_BANK";
	
	private int RON = 100000;
	private int EURO = 10000;
	private int DOLAR = 10000;
	
	private String atmPassword = "0000";
	private HandlerDataBase handlerDataBase;
	
	private Client client;
	
	public Atm() {
		handlerDataBase = new HandlerDataBase();
	}
	
	public boolean checkAdminPassword(String password) {
		return atmPassword.equals(password);
	}

	public boolean isClientIdValid(String clientId) {
		if (clientId.length() != 3)
			return false;
		return handlerDataBase.serchClient(clientId);
	}

	public boolean isClientPinValid(String clientPin) {
		if (clientPin.length() != 4)
			return false;
		return handlerDataBase.checkPin(clientPin);
	}
	
	public void CompleteClientProfile() {
		client = new Client();
		handlerDataBase.CompleteClientProfile(client);
	}
	
	public void sold() {
		System.out.println("SOLD: " + client.getSold() + " " + client.getCoin());
	}

	public void withdraw() {
		// TODO Auto-generated method stub
		
	}

	public void add() {
		// TODO Auto-generated method stub
		
	}

	public void exchange() {
		// TODO Auto-generated method stub
		
	}

	public boolean isAdmin() { 
		// TODO Auto-generated method stub
		return false;
	}
	
	public Client getClient() {
		return client;
	}
	
}
