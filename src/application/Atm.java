package application;

public class Atm {
	private int RON = 100000;
	private int EURO = 10000;
	private int DOLAR = 10000;
	
	private String atmPassword = "0000";
	
	public boolean checkAdminPassword(String password) {
		return atmPassword.equals(password);
	}
	
	
}
