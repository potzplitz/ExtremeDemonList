package settingsfunctions;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

public class WriteSettings {

	public void write() throws IOException {
JSONObject writeSettings = new JSONObject();
		
		
		// Settings in JSON - Format bringen
		writeSettings.put("newSystem", true);
		
		
		// FileWriter instanz erstellen
		FileWriter writer = new FileWriter("C:\\ExtremeDemonList\\config\\config.json");
		
		System.out.println("Write " + writeSettings.toString());
		
		// JSON in txt dokument schreiben
		writer.write(writeSettings.toString());
		
		writer.flush();
		writer.close();
	}

}
