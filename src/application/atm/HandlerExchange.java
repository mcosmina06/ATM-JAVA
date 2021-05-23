package application.atm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HandlerExchange {
	
	public double getValueConverted(double value, String coinAccount, String coinValue) {

		double conversionRate = getConversionRate(coinAccount, coinValue);

		return value * conversionRate;
	}
	 
	private double getConversionRate(String coinAccount, String coinValue) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("Exchange.txt"))) {
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				if (checkLine(line, coinAccount, coinValue))
					return Double.parseDouble(line.split(" ")[2]);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found!");
		} catch (IOException e) {
			System.out.println("Error reading");
		}
		
		return 0;
	}
	
	private boolean checkLine(String line, String coinAccount, String coinValue) {
		String [] lineArg = line.split(" ");
		
		if (lineArg[0].equals(coinValue) && lineArg[1].equals(coinAccount))
			return true;
		
		return false;
	}
}
