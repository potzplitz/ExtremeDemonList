package settingsfunctions;

import java.io.File;

public class DeleteDatabase {
	
	private void deleter(String database) {
		File filelength = new File("C:\\ExtremeDemonList\\" + database);
		File[] filelengthindex = filelength.listFiles();
		for(File file : filelengthindex) {
			file.delete();
		}
	}
	
	public void deleteCompleted() {
		deleter("completed");
	}
	
	public void deleteUncompleted() {
		deleter("levels");
	}
	
	public void deleteAll() {
		deleteCompleted();
		deleteUncompleted();
	}

}
