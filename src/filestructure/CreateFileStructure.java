package filestructure;

import java.io.File;

public class CreateFileStructure {
	
	public void create() {
		
		File file = new File("C:\\ExtremeDemonList\\levels");
		File file2 = new File("C:\\ExtremeDemonList\\completed");
		
		if(!file.isDirectory()) {
			file.mkdirs();
		}
		
		if(!file2.isDirectory()) {
			file2.mkdirs();
		}
		
	}

}
