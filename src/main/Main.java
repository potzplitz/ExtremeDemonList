package main;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import api.GetApiData;
import data.FetchData;
import data.ManageFiles;
import database.DatabaseManager;
import filestructure.CreateFileStructure;
import gui.LoadMenu;
import settingsfunctions.LoadSettings;

public class Main {
	
	public static void main(String[] args) throws IOException { 

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
