package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class GuiData {
	
	private ArrayList<String> localLevels = new ArrayList<String>();
	private ArrayList<String> qualification = new ArrayList<String>();
	private ArrayList<String> id = new ArrayList<String>();
	private ArrayList<String> verifier = new ArrayList<String>();
	private ArrayList<String> creator = new ArrayList<String>();
	private ArrayList<String> ytlink = new ArrayList<String>();
	private ArrayList<String> creators = new ArrayList<String>();
	private ArrayList<String> victors = new ArrayList<String>();
	private ArrayList<Integer> attempts = new ArrayList<Integer>();
	public ArrayList<String> completed = new ArrayList<String>();

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
	
	public ArrayList<String> getCreators() {
		return creators;
	}
	
	public ArrayList<Integer> getAttempts() {
		return attempts;
	}
	
	public ArrayList<String> getVictors() {
		return victors;
	}
	
	public void IndexData(ArrayList<String> migrate) throws IOException {
		File filelength = new File("C:\\ExtremeDemonList\\levels");
		File[] filelengthindex = filelength.listFiles();
		
		String jsonstring;
		
		localLength = filelengthindex.length;
		
		for(int i = 0; i < migrate.size(); i++) { 
			
			jsonstring = FileUtils.readFileToString(new File("C:\\ExtremeDemonList\\levels\\" + migrate.get(i)), StandardCharsets.UTF_8);
			jsonstring = jsonstring.trim().replace("\n", "").replace("\t", "").replace("\\", "");
			
			JSONObject obj = new JSONObject(jsonstring);
			
			localLevels.add(obj.getString("name"));
			id.add(obj.getInt("id") + "");
			
			try {
	            verifier.add(obj.getString("verifier"));
	            creator.add(obj.getString("author")); 
	        } catch (JSONException e) {
	            verifier.add(Long.toString(obj.getLong("verifier")));
	            creator.add(Long.toString(obj.getLong("author"))); 
	        }
			
			qualification.add(obj.getInt("percentToQualify") + "");
			ytlink.add(obj.getString("verification") + "");
			creators.add(obj.getJSONArray("creators") + "");
			victors.add(obj.getJSONArray("records") + "");
			attempts.add(0);
			
			
		}
		
	}
	
}