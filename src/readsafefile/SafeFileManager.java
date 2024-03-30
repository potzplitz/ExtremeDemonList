package readsafefile;

import java.io.IOException;

import data.FetchData;
import database.Sqlite;
import gui.AttemptsProgress;

public class SafeFileManager {
	
	public void DecryptSafeFile() throws IOException {
		DecryptXOR.decryptAndWriteFiles();
	}
	
	public void ReadIndexAttempts() throws IOException {
		
		AttemptsProgress prog = new AttemptsProgress();
		prog.build();
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
		Sqlite database = new Sqlite("levels");
		database.queryData("levels");
		FetchData fetch = new FetchData();
		try {
			fetch.getGithubString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ReadAttemptsFromXML read = new ReadAttemptsFromXML();
		
		read.readAttempts();
		
		String attempts;
		String percent;
		for(int i = 0; i < database.getLevelID().size(); i++) {
			attempts = read.attempts.get(database.getLevelID().get(i));
			percent = read.newbestMap.get(database.getLevelID().get(i));
			if(attempts == null) {
				attempts = 0 + "";
			}
			if(percent == null) {
				percent = 0 + "";
			}
			prog.update(database.getLevelname().get(i), Integer.parseInt(attempts), Integer.parseInt(percent), i);
			if(!database.getLocked().get(i)) {
				database.modifyData(database.getLevelname().get(i), Boolean.parseBoolean(database.getCompleted().get(i)), Integer.parseInt(attempts), database.getLocked().get(i), percent);
				}
			}
		prog.close();
		 }	
	 });
	thread.start();
	}
}
