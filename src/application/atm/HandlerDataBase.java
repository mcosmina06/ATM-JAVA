package application.atm;

import application.user.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class HandlerDataBase {
	
	private int indexClientLine;
	private String [] clientLine;
	private ArrayList<String> buffer = new ArrayList<String>();
	private DecimalFormat df = new DecimalFormat("#.00");
	
	protected boolean serchClient(String clientId) {
		ReadFile(clientId);
		
		if (clientLine != null)
			return true;
		
		return false;
	} 
	
	private void ReadFile(String clientId) {
		clientLine = null;
		indexClientLine = 0;
		
		try (BufferedReader reader = new BufferedReader(new FileReader("DataBase.txt"))) {
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				if (clientId.equals(line.split(",")[0].trim())) {
					clientLine = line.split(",");
					indexClientLine = buffer.size();
				}
				
				buffer.add(line + "\n");
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.out.println("Error reading");
		}
		
	}
	
	protected void completeClientProfile(Client client) {  
		client.setId(clientLine[0].trim());
		client.setPin(clientLine[1].trim());
		client.setSold(Double.parseDouble(clientLine[2].trim()));
		client.setCoin(clientLine[3].trim());
		client.setBlockedAccount(Boolean.parseBoolean(clientLine[4].trim()));
		client.setClientBank(Boolean.parseBoolean(clientLine[5].trim()));
	}
	
	protected boolean isClientBank() {
		return Boolean.parseBoolean(clientLine[5].trim());
	}
	
	protected void updateClientProfile(Client client) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("DataBase.txt", false))) {
			
			String updatedClientInfo = client.getId() + ", " + client.getPin() + ", " + df.format(client.getSold()) + ", " 
	        		 + client.getCoin() + ", " + client.getBlockAccount() + ", " + client.getClientBank() + "\n";
			
			buffer.set(indexClientLine, updatedClientInfo);
			
			for (String line: buffer)
				writer.write(line);
			
			writer.close();
		} catch (IOException e2) {
			System.out.println("Error at writing in history");
		}
	}
	
}
