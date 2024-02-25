package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class FetchData {
	
	private ArrayList<String> levels = new ArrayList<String>();
	
	public void getGithubString() throws IOException {
		 String link = "https://raw.githubusercontent.com/All-Rated-Extreme-Demon-List/AREDL/main/data/_list.json";
	        

	        URL url = new URL(link);

	        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
	        	
	            String line;
	            StringBuilder sb = new StringBuilder();

	            while ((line = in.readLine()) != null) {
	      
	                String trimmedLine = line.trim().replace(",", "").replace("\"", "");
	                
	                if (!trimmedLine.equals("[") && !trimmedLine.equals("]")) {
	                   levels.add(trimmedLine);
	                }
	            }
	        }
	}
	
	public ArrayList<String> allLevels() {
		return levels;
	}
	
}

	
	
