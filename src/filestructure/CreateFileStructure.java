package filestructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFileStructure {
	
	public void create() {
		
		File file = new File("C:\\ExtremeDemonList\\levels");
		File file2 = new File("C:\\ExtremeDemonList\\completed");
		File file3 = new File("C:\\ExtremeDemonList\\database\\sqlite");
		File file4 = new File("C:\\ExtremeDemonList\\songs");
		File file5 = new File("C:\\ExtremeDemonList\\config");
		
		if(!file.isDirectory()) {
			file.mkdirs();
		}
		
		if(!file2.isDirectory()) {
			file2.mkdirs();
		}
		
		if(!file3.exists()) {
			file3.mkdirs();
		}
		
		if(!file4.exists()) {
			file4.mkdirs();
		}
		
		if(!file5.exists()) {
			file5.mkdirs();
			File config = new File("C:\\ExtremeDemonList\\config\\config.json");
			if(!config.exists()) {
				try {
					config.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				FileWriter writer = new FileWriter("C:\\ExtremeDemonList\\config\\config.json");
				writer.write("{\"newSystem\":true}");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
