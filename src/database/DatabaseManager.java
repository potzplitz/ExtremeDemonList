package database;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import data.FetchData;
import data.GuiData;
import settingsfunctions.LoadSettings;

public class DatabaseManager {
	
	public void manage() {
		Sqlite createLevelDB = new Sqlite("levels");
		if(createLevelDB.exists()) {
		} else {
			createLevelDB.createNewDatabase("levels");
		}
		createLevelDB.createNewTable("levels");
		
		createLevelDB.checkColumns("levels");
		
	}
	
	public void migrateData() {
		Sqlite database = new Sqlite("levels");
		FetchData fetch = new FetchData();
		
		LoadSettings settings = new LoadSettings();
		try {
			settings.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<String> levels = new ArrayList<String>();
		ArrayList<String> rawLevels = new ArrayList<String>();
		
		File file = new File("C:\\ExtremeDemonList\\levels");
		File[] listLevels = file.listFiles();
		
		for(int i = 0; i < listLevels.length; i++) {
			rawLevels.add(listLevels[i].getName());
		}
		
		for(int i = 0; i < fetch.allLevels().size(); i++) {
			if(rawLevels.indexOf(fetch.allLevels().get(i) + ".json") != -1) {
				levels.add(rawLevels.get(rawLevels.indexOf(fetch.allLevels().get(i) + ".json")));	
			}
				
		}
		
		GuiData data = new GuiData();
	
		try {
			data.IndexData(levels);
			fetch.getGithubString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(data.getLocalLevels().size() + "  ====== lovallevelsize");
		
		for (int i = 0; i < data.getLocalLevels().size(); i++) {
			
			System.out.println(data.getLocalLevels().get(i));

			database.insertData(
				    "levels", // Tabellenname
				    data.getAttempts().get(i),
				    fetch.allLevels().indexOf(levels.get(i).replace(".json", "")) + 1,
				    data.getLocalLevels().get(i), // Level-Name
				    levels.get(i).replace(".json", ""), // Level-Name-Raw (oder entsprechender Wert aus fetch.allLevels())
				    Integer.parseInt(data.getId().get(i)), // ID
				    data.getCreator().get(i), // Ersteller
				    data.getCreators().get(i), // Ersteller
				    data.getVerifier().get(i), // Überprüfer
				    data.getYoutubeLink().get(i), // YouTube-Link
				    Integer.parseInt(data.getQualification().get(i)), // Qualifikation
				    data.getVictors().get(i), // Sieger
				    false,
				    false,
				    ""
				);
		}
		
		try {
			database.sortData("levels");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void queryData(String tablename) {
		Sqlite database = new Sqlite("levels");
		database.queryData(tablename);
	}
	
}
