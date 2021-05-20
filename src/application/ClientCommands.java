package application;

public enum ClientCommands {
	SOLD(1), WITHDRAW(2), ADD(3), EXCHANGE(4);
	
	private int id;
	
	ClientCommands(int id) {
		this.id = id;
	}
	
	public String toString() {
		return this.id + ".\t" + super.toString().toLowerCase();
	}
}
