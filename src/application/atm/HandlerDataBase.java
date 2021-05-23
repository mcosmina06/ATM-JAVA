package application.atm;

import application.user.Client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class HandlerDataBase {
	
	private String [] lastLineRead;
	private String buffer = "";
	
	protected boolean serchClient(String clientID) {
		buffer = "";
		
		try (BufferedReader reader = new BufferedReader(new FileReader("DataBase.txt"))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				lastLineRead = line.split(","); 
				if (clientID.equals(lastLineRead[0]))
					return true;
				buffer += line + "\n";
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.out.println("Error reading");
		}
		
		return false;
	} 
	
	protected void completeClientProfile(Client client) {  
		client.setId(lastLineRead[0].trim());
		client.setPin(lastLineRead[1].trim());
		client.setSold(Double.parseDouble(lastLineRead[2].trim()));
		client.setCoin(lastLineRead[3].trim());
		client.setBlockedAccount(Boolean.parseBoolean(lastLineRead[4].trim()));
		client.setClientBank(Boolean.parseBoolean(lastLineRead[5].trim()));
	}
	
	protected boolean isClientBank() {
		return Boolean.parseBoolean(lastLineRead[5].trim());
	}
	
	protected void updateClientProfile(Client client) {
	   try { 
	         RandomAccessFile raf = new RandomAccessFile("DataBase.txt", "rw");

	         raf.seek(0);
	         raf.writeBytes(buffer);
	         raf.writeBytes(client.getId() + ", " + client.getPin() + ", " + client.getSold() + ", " 
	        		 + client.getCoin() + ", " + client.getBlockAccount() + ", " + client.getClientBank());
	         
	         raf.close();
	         
	      } catch (IOException ex) {
	         ex.printStackTrace();
	      }
	}
}
