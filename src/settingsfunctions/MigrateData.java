package settingsfunctions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import database.DatabaseManager;

public class MigrateData {
	
	public void migrateData() {
		
		System.out.println("daten werden migriert");
		
		DatabaseManager manager = new DatabaseManager();
		manager.migrateData();
		
		
		System.out.println("daten fertig migriert. alte datenbank wird gelöscht");
	
		Path directory = Paths.get("C:\\ExtremeDemonList\\levels");
		try {
			Files.walk(directory)
            .filter(Files::isRegularFile) // Filtere nur reguläre Dateien
            .forEach(file -> {
                try {
                    Files.deleteIfExists(file); // Lösche die Datei
                    System.out.println("Datei gelöscht: " + file);
                } catch (IOException e) {
                    System.err.println("Fehler beim Löschen der Datei " + file + ": " + e.getMessage());
                }
            });
			
			Files.deleteIfExists(directory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
