package readsafefile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.FetchData;
import data.ManageFiles;
import database.Sqlite;
import gui.AttemptsProgress;

public class SafeFileManager {
	
	private String[] lengthArr = {"TINY", "SHORT", "MEDIUM", "LONG", "XL", "PLATFORMER", "N/A"};
	public Map<String, String> lengthComp = new HashMap<>();
	private Sqlite database = new Sqlite("levels");
	private ReadAttemptsFromXML read = new ReadAttemptsFromXML();
	
	public void DecryptSafeFile() throws IOException {
		DecryptXOR.decryptAndWriteFiles();
	}
	
	public void ReadIndexAttempts() throws IOException {
		
		read.readAttempts();
		
		AttemptsProgress prog = new AttemptsProgress();
		prog.build();
		
		database.queryData("levels");
		FetchData fetch = new FetchData();
		try {
			fetch.getGithubString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("size lengthMap: " + read.getLengthMap().size());
		
		lengthComp.putAll(read.lengthMap);
		
		
		
		String attempts;
		String percent;
		String length;
		for(int i = 0; i < database.getLevelID().size(); i++) {
			attempts = read.attempts.get(database.getLevelID().get(i));
			percent = read.newbestMap.get(database.getLevelID().get(i));
			length = read.lengthMap.get(database.getLevelID().get(i));
			
			if(attempts == null) {
				attempts = 0 + "";
			}
			if(percent == null) {
				percent = 0 + "";
			}
			if(length == null) {
				length = 6 + "";
			}
			
			prog.update(database.getLevelname().get(i), Integer.parseInt(attempts), Integer.parseInt(percent), i);
			if(!database.getLocked().get(i)) {
				database.modifyData(database.getLevelname().get(i), Boolean.parseBoolean(database.getCompleted().get(i)), Integer.parseInt(attempts), database.getLocked().get(i), percent, lengthArr[Integer.parseInt(length)]);
				}
			}
		prog.close();
	}

}
