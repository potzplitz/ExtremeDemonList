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
	private ArrayList<String> completed = new ArrayList<String>();
	private ArrayList<String> id = new ArrayList<String>();
	private ArrayList<String> verifier = new ArrayList<String>();
	private ArrayList<String> creator = new ArrayList<String>();
	
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
	
	public ArrayList<String> getCompleted() {
		return completed;
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
	
	

	public void IndexLevelName() throws IOException {
		
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
			
			localLevels.add(obj.getString("name"));
			

			
		}
		
		
		
	}
	
	public void IndexLevelID() throws IOException {
		

		
		File filelength = new File("C:\\ExtremeDemonList\\levels");
		File[] filelengthindex = filelength.listFiles();
		
		String jsonstring;
		
		localLength = filelengthindex.length;
		
		System.out.println(filelengthindex.length);
		
		for(int i = 0; i < filelengthindex.length; i++) { 
			
			
			
			jsonstring = FileUtils.readFileToString(new File("C:\\ExtremeDemonList\\levels\\" + data.allLevels().get(i) + ".json"), StandardCharsets.UTF_8);
			jsonstring = jsonstring.trim().replace("\n", "").replace("\t", "").replace("\\", "");
			
			JSONObject obj = new JSONObject(jsonstring);
			
			id.add(obj.getInt("id") + "");
			

			
		}
		
	}
	
	public void IndexVerifiers() throws IOException {
		
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
			
			verifier.add(obj.getString("verifier"));
			

			
		}
		
		
		
	}
	
	public void IndexCreators() throws IOException {
		
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
			
			creator.add(obj.getString("author"));
			

			
		}
		
		
		
	}

}