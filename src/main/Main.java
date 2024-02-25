package main;

import java.io.IOException;

import data.DownloadLevels;
import data.FetchData;
import data.ManageFiles;
import filestructure.CreateFileStructure;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		CreateFileStructure struct = new CreateFileStructure();
		struct.create();
		
		FetchData fetch = new FetchData();
		fetch.getGithubString();
		
		ManageFiles manager = new ManageFiles();
		manager.compareArrays();

		DownloadLevels download = new DownloadLevels();
		download.download();
		
		
	}

}
