package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManageFiles {
	
	private FetchData fetch = new FetchData();
	private static ArrayList<String> missinglevels = new ArrayList<String>(); // fehlende noch zu herunterladende level
	
	
	private void feedMissingLevelsArray(String levelname) { // fehlende level werden missinglevels hinzugefügt
		missinglevels.add(levelname);
	}
	
	
	public void compareArrays() throws IOException { // downloadedlevels und onlinelevels werden verglichen und die fehlenden level in missinglevels gepackt

		fetch.getGithubString();
		
		for(int i = 0; i < fetch.allLevels().size(); i++) {
			
			File file = new File("C:\\ExtremeDemonList\\levels\\" + fetch.allLevels().get(i) + ".json");
			
			if(!file.exists()) { // wenn der level lokal nicht vorhanden ist, wird er als fehlend gemeldet.
				System.out.println(fetch.allLevels().get(i) + " fehlt");
				feedMissingLevelsArray(fetch.allLevels().get(i));
			}
		}
		
	}
	
	public ArrayList<String> missingLevels() { // missinglevels wird zurückgegeben
		return missinglevels;
	}

}
