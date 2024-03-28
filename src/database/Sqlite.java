package database;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.FetchData;

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
    private ArrayList<Integer> attempts = new ArrayList<Integer>();
    private ArrayList<Boolean> locked = new ArrayList<Boolean>(); // New column

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
    
    public ArrayList<Integer> getAttempts() {
        return attempts;
    }

    public ArrayList<Boolean> getLocked() {
        return locked;
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
    
    public void createNewDatabase(String dbname) { // erstellt eine neue datenbank
        url = "jdbc:sqlite:C:\\ExtremeDemonList\\database\\sqlite\\";
        url += dbname + ".db";
        filename = dbname;
        try (Connection conn = DriverManager.getConnection(url)) {
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
                + "    attempts INTEGER NOT NULL,\n"
                + "    completed BOOLEAN NOT NULL,\n"
                + "    locked BOOLEAN NOT NULL\n" // Neue Spalte
                + ");";

        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertData(String tablename, Integer attempts, int placement, String levelname, String levelnameRaw, int levelid, String author, String creators, String verifier, String verificationLink, int percenttoqualify, String records, boolean completed, boolean locked) {
        String sql = "INSERT INTO " + tablename + " (placement, levelname, levelnameRaw, levelID, author, creators, verifier, verificationLink, percentToQualify, records, attempts, completed, locked) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        System.out.println("tablename: " + levelname);
        
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
            pstmt.setInt(11, attempts);
            pstmt.setBoolean(12, completed);
            pstmt.setBoolean(13, locked);
            pstmt.executeUpdate();
          
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }


    
    public void queryData(String tablename) {
        
        String sql = "SELECT levelname, levelNameRaw, levelID, author, creators, verifier, verificationLink, percentToQualify, attempts, completed, records, locked FROM " + tablename;
        
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
                        attempts.add(rs.getInt("attempts"));
                        locked.add(rs.getBoolean("locked")); // Get the value of the new column
                    }
        
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void sortData(String tablename) throws SQLException {
        FetchData data = new FetchData();

        ArrayList<String> levelnamelocal = new ArrayList<String>();
        ArrayList<String> levelIDlocal = new ArrayList<String>();
        ArrayList<String> authorlocal = new ArrayList<String>();
        ArrayList<String> creatorslocal = new ArrayList<String>();
        ArrayList<String> verifierlocal = new ArrayList<String>();
        ArrayList<String> verificationLinklocal = new ArrayList<String>();
        ArrayList<String> percenttoqualifylocal = new ArrayList<String>();
        ArrayList<String> recordslocal = new ArrayList<String>();
        ArrayList<String> completedlocal = new ArrayList<String>();
        ArrayList<String> rawLevelNameslocal = new ArrayList<String>();
        ArrayList<Integer> attemptsLocal = new ArrayList<Integer>();
        ArrayList<Boolean> lockedLocal = new ArrayList<Boolean>();

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Erstelle eine neue Tabelle mit einem temporären Namen
            String tempTableName = tablename + "_temp";
            createNewTable(tempTableName);

            // Holen Sie sich die maximale Platzierung in der Tabelle
            int maxPlacement = 0;
            String getMaxPlacementQuery = "SELECT MAX(placement) AS maxPlacement FROM " + tablename;
            ResultSet maxPlacementResult = stmt.executeQuery(getMaxPlacementQuery);
            if (maxPlacementResult.next()) {
                maxPlacement = maxPlacementResult.getInt("maxPlacement");
            }

            // Durchlaufen Sie die Platzierungen und holen Sie die Daten entsprechend
            for (int i = 1; i <= maxPlacement; i++) {
                String sql = "SELECT * FROM " + tablename + " WHERE placement = " + i;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    levelnamelocal.add(rs.getString("levelname"));
                    levelIDlocal.add(rs.getInt("levelID") + "");
                    authorlocal.add(rs.getString("author"));
                    creatorslocal.add(rs.getString("creators"));
                    verifierlocal.add(rs.getString("verifier"));
                    verificationLinklocal.add(rs.getString("verificationLink"));
                    percenttoqualifylocal.add(rs.getInt("percentToQualify") + "");
                    completedlocal.add(rs.getBoolean("completed") + "");
                    recordslocal.add(rs.getString("records"));
                    rawLevelNameslocal.add(rs.getString("levelNameRaw"));
                    attemptsLocal.add(rs.getInt("attempts"));
                    lockedLocal.add(rs.getBoolean("locked"));
                }
            }

            // Lösche die alte Tabelle
            stmt.executeUpdate("DROP TABLE IF EXISTS " + tablename);

            // Erstelle eine neue Tabelle mit dem ursprünglichen Namen
            createNewTable(tablename);

            // Füge Daten in die neue Tabelle ein
            String insert = "INSERT INTO " + tablename + " (placement, levelname, levelnameRaw, levelID, author, creators, verifier, verificationLink, percentToQualify, records, attempts, completed, locked) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insert)) {
                for (int i = 0; i < levelnamelocal.size(); i++) {
                    pstmt.setInt(1, i + 1);
                    pstmt.setString(2, levelnamelocal.get(i));
                    pstmt.setString(3, rawLevelNameslocal.get(data.allLevels().indexOf(rawLevelNameslocal.get(i))));
                    pstmt.setInt(4, Integer.parseInt(levelIDlocal.get(i)));
                    pstmt.setString(5, authorlocal.get(i));
                    pstmt.setString(6, creatorslocal.get(i));
                    pstmt.setString(7, verifierlocal.get(i));
                    pstmt.setString(8, verificationLinklocal.get(i));
                    pstmt.setInt(9, Integer.parseInt(percenttoqualifylocal.get(i)));
                    pstmt.setString(10, recordslocal.get(i));
                    pstmt.setInt(11, attemptsLocal.get(i));
                    pstmt.setBoolean(12, false);
                    pstmt.setBoolean(13, lockedLocal.get(i)); // Insert value of locked column
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyData(String levelname, boolean completedStatus, int attempts, boolean lock) {
        String sql = "UPDATE levels SET completed = ?, attempts = ?, locked = ? WHERE levelname = ?";
        
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, completedStatus);
            pstmt.setInt(2, attempts);
            pstmt.setBoolean(3, lock); // Korrigierte Reihenfolge
            pstmt.setString(4, levelname); // Korrigierte Reihenfolge
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("Data not found for levelname: " + levelname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
