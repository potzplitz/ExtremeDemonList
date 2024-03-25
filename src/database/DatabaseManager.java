package database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import data.FetchData;
import data.GuiData;
import data.GuiData_Deprecated;
import gui.MainGUI;
import gui.MainGUI_Deprecated;
import settingsfunctions.LoadSettings;

public class DatabaseManager {
	
	public void manage() {
		Sqlite createLevelDB = new Sqlite("levels");
		if(createLevelDB.exists()) {
			System.out.println(true);
		} else {
			System.out.println(false);
			createLevelDB.createNewDatabase();
		}
		
		createLevelDB.createNewTable("levels");
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
			System.out.println(fetch.allLevels().get(i));
			if(rawLevels.indexOf(fetch.allLevels().get(i) + ".json") != -1) {
				levels.add(rawLevels.get(rawLevels.indexOf(fetch.allLevels().get(i) + ".json")));	
			}
				
		}
		
		GuiData data = new GuiData();
		try {
			data.IndexData(levels);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fetch.getGithubString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		for (int i = 0; i < data.getLocalLevels().size(); i++) {
			
			System.out.println(levels.get(i) + " hat den index " +  levels.indexOf(levels.get(i)));
			
			database.insertData(
				    "levels", // Tabellenname
				    fetch.allLevels().indexOf(levels.get(i).replace(".json", "")) + 1,
				    data.getLocalLevels().get(i), // Level-Name
				    fetch.allLevels().get(i), // Level-Name-Raw (oder entsprechender Wert aus fetch.allLevels())
				    Integer.parseInt(data.getId().get(i)), // ID
				    data.getCreator().get(i), // Ersteller
				    data.getCreators().get(i), // Ersteller
				    data.getVerifier().get(i), // Überprüfer
				    data.getYoutubeLink().get(i), // YouTube-Link
				    Integer.parseInt(data.getQualification().get(i)), // Qualifikation
				    data.getVictors().get(i), // Sieger
				    false
				);

		}
		
		database.sortData("levels");
		
	}
	
	public void queryData(String tablename) {
		Sqlite database = new Sqlite("levels");
		database.queryData(tablename);
	}
	
}
