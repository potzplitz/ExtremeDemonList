package main;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import data.FetchData;
import data.ManageFiles;
import filestructure.CreateFileStructure;
import gui.LoadMenu;

public class Main {
	
	public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException { 


		LoadMenu load = new LoadMenu();
		load.onLoad();
		
		load.updateBar("Dateistruktur wird gebaut...");
		
		CreateFileStructure struct = new CreateFileStructure();
		struct.create();
		
		load.updateBar("Updatedaten werden heruntergeladen...");
		
		FetchData fetch = new FetchData();
		fetch.getGithubString();
		
		load.updateBar("Liste wird auf Updates geprüft...");
		
		ManageFiles manager = new ManageFiles();
		manager.compareArrays();

		load.updateBar("Ladevorgang abgeschlossen");
		load.close();
	}

}
