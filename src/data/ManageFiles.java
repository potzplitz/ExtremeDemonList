package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import database.Sqlite;
import gui.MainGUI;
import gui.MainGUI_Deprecated;

import gui.MissingLevels;

public class ManageFiles {
	
	private FetchData fetch = new FetchData();
	private MissingLevels gui = new MissingLevels();
	private Sqlite database = new Sqlite("levels");
	private static ArrayList<String> missinglevels = new ArrayList<String>(); // fehlende noch zu herunterladende level
	private int missing = 0;
	
	public static ArrayList<String> getMissinglevels() {
		return missinglevels;
	}

	private void feedMissingLevelsArray(String levelname) { // fehlende level werden missinglevels hinzugefügt
		missinglevels.add(levelname);
	}
	
	public void compareArrays(boolean system) throws IOException { // downloadedlevels und onlinelevels werden verglichen und die fehlenden level in missinglevels gepackt

		fetch.getGithubString();
		database.queryData("levels");

		
		if(system) {
			System.out.println(database.getLevelname().size());
			
			int difference = fetch.allLevels().size() - database.getLevelname().size();
			System.out.println(difference);
			
			for(int i = 0; i < database.getLevelname().size(); i++) {
				//System.out.println("fetch.allLevels(): " + fetch.allLevels().get(i) + "\t database.getRawLevelNames(): " + database.getRawLevelNames().get(i));
			    if(!database.getRawLevelNames().contains(fetch.allLevels().get(i))) {
			    	System.out.println(missing);
			    	missing++;
			        missinglevels.add(fetch.allLevels().get(i));
			    }
			}
			
			if(missing > 0) {
				gui.show(missinglevels, missing);
			} else {
				MainGUI gui = new MainGUI();
				gui.build();
			}
			
		} else {
		
		for(int i = 0; i < fetch.allLevels().size(); i++) {
			
			File file = new File("C:\\ExtremeDemonList\\levels\\" + fetch.allLevels().get(i) + ".json");
			
			if(!file.exists()) { // wenn der level lokal nicht vorhanden ist, wird er als fehlend gemeldet.
				System.out.println(fetch.allLevels().get(i) + " fehlt");
				feedMissingLevelsArray(fetch.allLevels().get(i));
				missing++;
			}
		}
		
		if(missing > 0) {
			
			gui.show(missinglevels, missing);
		} else {
			MainGUI_Deprecated gui = new MainGUI_Deprecated();
			gui.build();
		}	
		
		}
	}
}
