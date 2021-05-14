package application;

public class Administrator {
	private String id;
	private String pin;
	
	Administrator() {
		id = "***";
		pin = "0000";
	}
	
	public void addMoneyATM(Atm atm, String type, int value) {
		
	}
	
	public void withdrawMoneyATM(Atm atm, String type, int value) {
		
	}
	
	public void unlockAccount() {
		
	}
	
	public boolean checkPin(String pin) {
		//daca e in oglinda blocheaza cardul
		return false;
	}
	
	public void trace() {
		
	}
}
