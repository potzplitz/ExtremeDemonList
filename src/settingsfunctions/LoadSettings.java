package settingsfunctions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class LoadSettings {
	
	private boolean oldsystem;
	
	public void load() throws IOException {
		
		if(new File("C:\\AnimeWatchList\\config\\config.json").exists()) {

			String configjson = Files.readAllLines(Paths.get("C:\\ExtremeDemonList\\config\\config.json")).get(0);

			JSONObject obj = new JSONObject(configjson);

			oldsystem = obj.getBoolean("newSystem");

		} else {
			
			System.out.println("config not found");
			
		}
	}

	public boolean isOldsystem() {
		return oldsystem;
	}

}
