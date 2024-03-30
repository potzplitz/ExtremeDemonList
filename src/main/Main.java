package main;

import java.io.IOException;
import java.util.zip.DataFormatException;

import data.FetchData;
import data.ManageFiles;
import database.DatabaseManager;
import database.Sqlite;
import filestructure.CreateFileStructure;
import gui.LoadMenu;
import preload.PreChecks;
import readsafefile.DecryptXOR;
import readsafefile.ReadAttemptsFromXML;
import readsafefile.SafeFileManager;
import settingsfunctions.LoadSettings;

public class Main {
	
	public static void main(String[] args) throws IOException, DataFormatException { 

		LoadMenu load = new LoadMenu();
		load.onLoad();
		
		load.updateBar("Dateistruktur wird gebaut...");
		
		CreateFileStructure struct = new CreateFileStructure();
		struct.create();
		
		load.updateBar("Konfigurationsdatei wird gelesen...");
		
		LoadSettings settings = new LoadSettings();
		settings.load();
		
		load.updateBar("Updatedaten werden heruntergeladen...");
		
		FetchData fetch = new FetchData();
		fetch.getGithubString();
		
		load.updateBar("Daten werden überprüft...");
		
		Sqlite sql = new Sqlite("levels");
		sql.checkColumns("levels");
		
		PreChecks check = new PreChecks();
		check.check();
		
		load.updateBar("Einträge werden Indexiert...");
		
		ManageFiles manager = new ManageFiles();
		manager.compareArrays(settings.isOldsystem());
		
		load.updateBar("Datenbank wird gestartet...");
		DatabaseManager data = new DatabaseManager();		
		data.manage();

		load.updateBar("Ladevorgang abgeschlossen");
		load.close();
	}

}
