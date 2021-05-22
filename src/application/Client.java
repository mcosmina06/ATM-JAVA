package application;

class Client {
	private String id;
	private String pin;
	private double sold;
	private String coin;
	private boolean blockedAccount;
	private boolean clientBank;

	public void setId(String id) {
		this.id = id;
	}

	public void setPin(String pin) {
		this.pin = pin; 
	}
	
	public void setSold(double sold) {
		this.sold = sold;
	}
	
	public void setCoin(String coin) {
		this.coin = coin;
	}
	
	public void setBlockedAccount(boolean blockedAccount) {
		this.blockedAccount = blockedAccount;
	}
	
	public void setClientBank(boolean clientBank) {
		this.clientBank = clientBank;
	}
	
	public String getId() {
		return id;
	}

	public String getPin() {
		return pin;
	}
	
	public double getSold() {
		return sold;
	}

	public String getCoin() {
		return coin;
	}

	public boolean getBlockAccount() {
		return blockedAccount;
	} 

	public boolean getClientBank() {
		return clientBank;
	}
	
	@Override
	public String toString() {
		return "Client with id " + id + " and curren sold " + sold + " " + coin;
	}
}