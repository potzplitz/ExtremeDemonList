package data;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadLevels {
	
	private ManageFiles data = new ManageFiles();
	
	
	public void download() throws IOException {
		
		String fileURL;
		
		for(int i = 0; i < data.missingLevels().size(); i++) {
			
			System.out.println("downloading " + data.missingLevels().get(i));
			fileURL = "https://raw.githubusercontent.com/All-Rated-Extreme-Demon-List/AREDL/main/data/" + data.missingLevels().get(i) + ".json";
			
		try (BufferedInputStream in = new BufferedInputStream(new URL(fileURL).openStream());
				
	             FileOutputStream fileOutputStream = new FileOutputStream("C:\\ExtremeDemonList\\levels\\" + data.missingLevels().get(i) + ".json")) {
			
	            byte dataBuffer[] = new byte[1024];
	            int bytesRead;
	            
	            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
	            	
	                fileOutputStream.write(dataBuffer, 0, bytesRead);
	                
	            }
	        } catch (IOException e) {
	            // Handle exceptions
	            e.printStackTrace();
	        }
		}
	}

}
