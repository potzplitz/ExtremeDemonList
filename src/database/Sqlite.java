package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Sqlite {
	
	private static String url = "jdbc:sqlite:C:\\ExtremeDemonList\\database\\sqlite\\";
	private static String filename;
	
	private ArrayList<String> levelname = new ArrayList<String>();
	private ArrayList<String> levelID = new ArrayList<String>();
	private ArrayList<String> author = new ArrayList<String>();
	private ArrayList<String> creators = new ArrayList<String>();
	private ArrayList<String> verifier = new ArrayList<String>();
	private ArrayList<String> verificationLink = new ArrayList<String>();
	private ArrayList<String> percenttoqualify = new ArrayList<String>();
	private ArrayList<String> records = new ArrayList<String>();
	private ArrayList<String> completed = new ArrayList<String>();
	private ArrayList<String> rawLevelNames = new ArrayList<String>();

	
	public ArrayList<String> getLevelname() {
		return levelname;
	}

	public ArrayList<String> getLevelID() {
		return levelID;
	}

	public ArrayList<String> getAuthor() {
		return author;
	}

	public ArrayList<String> getCreators() {
		return creators;
	}

	public ArrayList<String> getVerifier() {
		return verifier;
	}

	public ArrayList<String> getVerificationLink() {
		return verificationLink;
	}

	public ArrayList<String> getPercenttoqualify() {
		return percenttoqualify;
	}

	public ArrayList<String> getRecords() {
		return records;
	}

	public ArrayList<String> getCompleted() {
		return completed;
	}

	public ArrayList<String> getRawLevelNames() {
		return rawLevelNames;
	}

	public Sqlite(String dbname) { // setzt variablen
		url = "jdbc:sqlite:C:\\ExtremeDemonList\\database\\sqlite\\";
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
	
	public void createNewTable(String tablename) {
		String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (\n"
	            + "    id INTEGER PRIMARY KEY,\n"
				+ "    placement INTEGER NOT NULL,\n"
	            + "    levelname TEXT NOT NULL,\n"
	            + "    levelnameRaw TEXT NOT NULL,\n" // Hinzugefügt
	            + "    levelID INTEGER NOT NULL,\n"
	            + "    author TEXT NOT NULL,\n"
	            + "    creators TEXT NOT NULL,\n"
	            + "    verifier TEXT NOT NULL,\n"
	            + "    verificationLink TEXT NOT NULL,\n"
	            + "    percentToQualify INTEGER NOT NULL,\n"
	            + "    records TEXT NOT NULL,\n"
	            + "    completed BOOLEAN NOT NULL\n"
	            + ");";


		
		try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void insertData(String tablename, int placement, String levelname, String levelnameRaw, int levelid, String author, String creators, String verifier, String verificationLink, int percenttoqualify, String records, boolean completed) {
	    String sql = "INSERT INTO " + tablename + " (placement, levelname, levelnameRaw, levelID, author, creators, verifier, verificationLink, percentToQualify, records, completed) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = DriverManager.getConnection(url);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	pstmt.setInt(1, placement);
	        pstmt.setString(2, levelname);
	        pstmt.setString(3, levelnameRaw);
	        pstmt.setInt(4, levelid);
	        pstmt.setString(5, author);
	        pstmt.setString(6, creators);
	        pstmt.setString(7, verifier);
	        pstmt.setString(8, verificationLink);
	        pstmt.setInt(9, percenttoqualify);
	        pstmt.setString(10, records);
	        pstmt.setBoolean(11, completed);
	        pstmt.executeUpdate();
	      
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }
	}


	
	public void queryData(String tablename) {
		
		String sql = "SELECT levelname, levelNameRaw, levelID, author, creators, verifier, verificationLink, percentToQualify, completed, records FROM " + tablename;
		
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	            while (rs.next()) {
	                levelname.add(rs.getString("levelname"));
	                levelID.add(rs.getInt("levelID") + "");
	                author.add(rs.getString("author"));
	                creators.add(rs.getString("creators"));
	                verifier.add(rs.getString("verifier"));
	                verificationLink.add(rs.getString("verificationLink"));
	                percenttoqualify.add(rs.getInt("percentToQualify") + "");
	                completed.add(rs.getBoolean("completed") + "");
	                records.add(rs.getString("records"));
	                rawLevelNames.add(rs.getString("levelNameRaw"));
	                
	            }
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void sortData(String tablename) {
	    String sql = "SELECT * FROM " + tablename + " ORDER BY placement ASC";

	    try (Connection conn = DriverManager.getConnection(url);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        // Leere die ArrayLists, bevor du die sortierten Daten hinzufügst
	        levelname.clear();
	        levelID.clear();
	        author.clear();
	        creators.clear();
	        verifier.clear();
	        verificationLink.clear();
	        percenttoqualify.clear();
	        completed.clear();
	        records.clear();
	        rawLevelNames.clear();

	        // Loop durch das Ergebnis der sortierten Abfrage und füge die Daten in die entsprechenden ArrayLists ein
	        while (rs.next()) {
	            levelname.add(rs.getString("levelname"));
	            levelID.add(rs.getInt("levelID") + "");
	            author.add(rs.getString("author"));
	            creators.add(rs.getString("creators"));
	            verifier.add(rs.getString("verifier"));
	            verificationLink.add(rs.getString("verificationLink"));
	            percenttoqualify.add(rs.getInt("percentToQualify") + "");
	            completed.add(rs.getBoolean("completed") + "");
	            records.add(rs.getString("records"));
	            rawLevelNames.add(rs.getString("levelNameRaw"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
