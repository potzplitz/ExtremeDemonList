package preload;

import java.io.File;

import javax.swing.JOptionPane;

import data.FetchData;
import data.ManageFiles;
import settingsfunctions.MigrateData;

public class PreChecks {
	
	public void check() {
		File file = new File("C:\\ExtremeDemonList\\levels");
		File[] list = file.listFiles();
		
		FetchData data = new FetchData();
		
		System.out.println(data.allLevels().size() - 10);
		System.out.println(list.length);
		
		if(list.length >= data.allLevels().size() - 10) {
			JOptionPane.showMessageDialog(null, "Deine Daten wurden noch nicht migriert. Dies erfolgt nun und kann etwas Zeit in Anspruch nehmen. Die Liste startet sich danach automatisch.", "Migration", JOptionPane.INFORMATION_MESSAGE);
			MigrateData migration = new MigrateData();
			migration.migrateData();
		}
	}

}
