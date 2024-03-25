package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import database.Sqlite;
import gui.MainGUI;

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
			
			for(int i = 0; i < fetch.allLevels().size(); i++) {
			    if(!database.getRawLevelNames().contains(fetch.allLevels().get(i))) {
			    	missinglevels.add(fetch.allLevels().get(i));
			    	missing++;		 
			    }
			}
			
			if(missing > 0) {
				gui.show(missinglevels, missing);
			} else {
				MainGUI gui = new MainGUI();
				gui.build();
			}

		}
}
