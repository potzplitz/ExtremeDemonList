package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Sqlite {
	
	private static String url = "jdbc:sqlite:C:\\ExtremeDemonList\\database\\sqlite\\";
	private static String filename;
	
	public Sqlite(String dbname) { // setzt variablen
		 url += dbname + ".db";
		 filename = dbname + "";
		 
	}
	
	public boolean exists() { // überprüft, ob datenbank existiert
		
		File file = new File("C:\\ExtremeDemonList\\database\\sqlite\\" + filename + ".db");
		if(file.exists()) {
			return true; // ja
		} else {
			return false; // nein
		}	
	}
	
	public void createNewDatabase() { // erstellt eine neue datenbank
		 try (Connection conn = DriverManager.getConnection(url)) {
		DatabaseMetaData meta = conn.getMetaData();
		 
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
	}
}
