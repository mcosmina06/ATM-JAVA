package application;

class Client {
	private String id;
	private String pin;
	private boolean blockAccount = false;
	private boolean clientBank;

	Client(String id, String pin) {
		this.id = id;
		this.pin = pin;
	}

	public String getId() {
		return id;
	}

	public String getPin() {
		return pin;
	}

	public boolean isBlockAccount() {
		return blockAccount;
	}

	public void setBlockAccount(boolean blockAccount) {
		this.blockAccount = blockAccount;
	}

	public boolean isClientBank() {
		return clientBank;
	}

	public void setClientBank(boolean clientBank) {
		this.clientBank = clientBank;
	}

}