package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GuiData {
	
	private ArrayList<String> localLevels = new ArrayList<String>();
	private ArrayList<String> qualification = new ArrayList<String>();
	private ArrayList<String> id = new ArrayList<String>();
	private ArrayList<String> verifier = new ArrayList<String>();
	private ArrayList<String> creator = new ArrayList<String>();
	private ArrayList<String> ytlink = new ArrayList<String>();
	public ArrayList<String> completed = new ArrayList<String>();
	
	private FetchData data = new FetchData();

	public void setId(ArrayList<String> id) {
		this.id = id;
	}

	private int localLength;
	
	public int getLocalLength() {
		return localLength;
	}

	public ArrayList<String> getLocalLevels() {
		return localLevels;
	}
	
	public ArrayList<String> getId() {
		return id;
	}
	
	public ArrayList<String> getVerifier() {
		return verifier;
	}
	
	public ArrayList<String> getCreator() {
		return creator;
	}
	
	public ArrayList<String> getQualification() {
		return qualification;
	}
	
	public ArrayList<String> getYoutubeLink() {
		return ytlink;
	}
	
	

	public void IndexData() throws IOException {
		
		FetchData data = new FetchData();
		
		File filelength = new File("C:\\ExtremeDemonList\\levels");
		File[] filelengthindex = filelength.listFiles();
		
		String jsonstring;
		
		localLength = filelengthindex.length;
		
		System.out.println(filelengthindex.length);
		
		for(int i = 0; i < filelengthindex.length; i++) { 
			
			jsonstring = FileUtils.readFileToString(new File("C:\\ExtremeDemonList\\levels\\" + data.allLevels().get(i) + ".json"), StandardCharsets.UTF_8);
			jsonstring = jsonstring.trim().replace("\n", "").replace("\t", "").replace("\\", "");
			
			JSONObject obj = new JSONObject(jsonstring);
			JSONArray recordsArray = obj.getJSONArray("records");
			
			localLevels.add(obj.getString("name"));
			id.add(obj.getInt("id") + "");
			verifier.add(obj.getString("verifier"));
			creator.add(obj.getString("author"));
			qualification.add(obj.getInt("percentToQualify") + "");
			ytlink.add(obj.getString("verification") + "");
			
			
		}
	}	
	
	public static ArrayList<String> allVictors(String levelname) throws IOException {
        ArrayList<String> completed = new ArrayList<>();

        // JSON-Datei einlesen
        String jsonContent = new String(Files.readAllBytes(Paths.get("C:\\ExtremeDemonList\\levels\\" + levelname + ".json")));
        JSONObject jsonObject = new JSONObject(jsonContent);

        // Victors extrahieren
        JSONArray recordsArray = jsonObject.getJSONArray("records");
        for (int i = 0; i < recordsArray.length(); i++) {
            JSONObject record = recordsArray.getJSONObject(i);
            int percent = record.getInt("percent");
            if (percent == 100) {
                completed.add(record.getString("user"));
            }
        }

        return completed;
    }
	
}
