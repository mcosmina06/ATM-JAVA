package application;

public enum Commands {
	SOLD(1), WITHDRAW(2), ADD(3), EXCHANGE(4);
	
	private int id;
	
	Commands(int id) {
		this.id = id;
	}
	
	public String toString() {
		return this.id + ".\t" + super.toString().toLowerCase();
	}
}
