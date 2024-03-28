package readsafefile;

import java.io.IOException;

import data.FetchData;
import database.Sqlite;
import gui.AttemptsProgress;

public class SafeFileManager {
	
	public void DecryptSafeFile() throws IOException {
		DecryptXOR dec = new DecryptXOR();
		dec.decryptAndWriteFiles();
	}
	
	public void ReadIndexAttempts() throws IOException {
		
		AttemptsProgress prog = new AttemptsProgress();
		prog.build();
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				

		
		Sqlite database = new Sqlite("levels");
		database.queryData("levels");
		FetchData fetch = new FetchData();
		try {
			fetch.getGithubString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ReadAttemptsFromXML read = new ReadAttemptsFromXML();
		
		String attempts;
		
		for(int i = 0; i < fetch.allLevels().size(); i++) {
			
			attempts = read.getAttempts(database.getLevelID().get(i));
			
			prog.update(database.getLevelname().get(i), Integer.parseInt(attempts), 1, i);
			database.modifyData(database.getLevelname().get(i), Boolean.parseBoolean(database.getCompleted().get(i)), Integer.parseInt(attempts));
		}	
	}
		
	});
	thread.start();
	}
}
