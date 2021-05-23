package application.atm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class History {
	
	private File clientsHistory; 
	private File othersHistory;
	
	public History(String clientsHistoryFileName, String othersHistoryFileName){
		this.clientsHistory = new File(clientsHistoryFileName);
		this.othersHistory = new File(othersHistoryFileName);
	}
	
	public void update(String data, boolean isClientBank) {
		File file = getFileToWrite(isClientBank);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.write(data + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("Error at writing in history");
		}
	}
	
	private File getFileToWrite(boolean isClientBank) {
		if (isClientBank)
			return this.clientsHistory;
		return this.othersHistory;
	}

}
