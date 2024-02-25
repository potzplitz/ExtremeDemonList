package filestructure;

import java.io.File;

public class CreateFileStructure {
	
	public void create() {
		
		File file = new File("C:\\ExtremeDemonList\\levels");
		
		if(!file.isDirectory()) {
			file.mkdir();
		}
		
	}

}
