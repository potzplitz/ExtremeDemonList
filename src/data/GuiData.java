package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class GuiData {
	
	private ArrayList<String> localLevels = new ArrayList<String>();
	private int localLength;
	
	public int getLocalLength() {
		return localLength;
	}

	public ArrayList<String> getLocalLevels() {
		return localLevels;
	}

	public void IndexLevelName() throws IOException {
		
		
		File filelength = new File("C:\\ExtremeDemonList\\levels");
		File[] filelengthindex = filelength.listFiles();
		
		String jsonstring;
		
		localLength = filelengthindex.length;
		
		System.out.println(filelengthindex.length);
		
		for(int i = 0; i < filelengthindex.length; i++) { 
			jsonstring = FileUtils.readFileToString(new File("C:\\ExtremeDemonList\\levels\\" + filelengthindex[i].getName()), StandardCharsets.UTF_8);
			jsonstring = jsonstring.trim().replace("\n", "").replace("\t", "").replace("\\", "");
			
			JSONObject obj = new JSONObject(jsonstring);
			
			localLevels.add(obj.getString("name"));
			
		}
	}

}
