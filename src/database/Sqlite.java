package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.FetchData;
import gui.LoadingStatus;

public class Sqlite {

    private static String url = "jdbc:sqlite:C:\\ExtremeDemonList\\database\\sqlite\\";
    private static String filename;
    
    private LoadingStatus status;

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
    private ArrayList<String> pbarr = new ArrayList<String>();
    private ArrayList<String> levelLength = new ArrayList<String>();

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

    public ArrayList<String> getPbarr() {
        return pbarr;
    }
    
    public ArrayList<String> getLevelLength() {
    	return levelLength;
    }

    public Sqlite(String dbname) { // setzt variablen
        url = "jdbc:sqlite:C:\\ExtremeDemonList\\database\\sqlite\\";
        url += dbname + ".db";
        filename = dbname + "";
    }

    public boolean exists() { // überprüft, ob datenbank existiert

        File file = new File("C:\\ExtremeDemonList\\database\\sqlite\\" + filename + ".db");
        return file.exists();
    }

    public void createNewDatabase(String dbname) { // erstellt eine neue datenbank
        url = "jdbc:sqlite:C:\\ExtremeDemonList\\database\\sqlite\\";
        url += dbname + ".db";
        filename = dbname;
        try (Connection conn = DriverManager.getConnection(url)) {
        } catch (SQLException e) {
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
                + "    locked BOOLEAN NOT NULL,\n" // Neue Spalte
                + "    personalBest STRING NOT NULL,\n"
                + "    levelLength String NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertData(String tablename, Integer attempts, int placement, String levelname, String levelnameRaw, int levelid, String author, String creators, String verifier, String verificationLink, int percenttoqualify, String records, boolean completed, boolean locked, String pb, String levelLength) {
        String sql = "INSERT INTO " + tablename + " (placement, levelname, levelnameRaw, levelID, author, creators, verifier, verificationLink, percentToQualify, records, attempts, completed, locked, personalBest, levelLength) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        System.out.println("tablename: " + levelname);
        
        if(pb == null) {
        	pb = "";
        }

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
            pstmt.setString(14, pb);
            pstmt.setString(15, levelLength);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void queryData(String tablename) {
    	
    	FetchData fetch = new FetchData();
    	
    	for(int i = 0; i < fetch.allLevels().size(); i++) {

    		String sql = "SELECT * FROM " + tablename + " WHERE levelNameRaw = '" + fetch.allLevels().get(i) + "'";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
                pbarr.add(rs.getString("personalBest"));
                levelLength.add(rs.getString("levelLength"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

    public void sortData(String tablename) throws SQLException {
    	FetchData data = new FetchData();
    	
status = LoadingStatus.getInstance(); // Holen der Singleton-Instanz
        
        status.initialize();
    	

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
        ArrayList<String> pblocal = new ArrayList<String>();
        ArrayList<String> levelLengthlocal = new ArrayList<String>();

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

            int index = 0;
            
            status.changeState("Datenbank wird sortiert...", maxPlacement);
            
            // Durchlaufen Sie die Platzierungen und holen Sie die Daten entsprechend
            for (int i = 1; i <= maxPlacement; i++) {
                String sql = "SELECT * FROM " + tablename + " WHERE placement = " + i;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                	index++;
                	status.update(rs.getString("levelname"), index);
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
                    pblocal.add(rs.getString("personalBest"));
                    levelLengthlocal.add(rs.getString("levelLength"));
                }
            }

            // Lösche die alte Tabelle
            stmt.executeUpdate("DROP TABLE IF EXISTS " + tablename);

            // Erstelle eine neue Tabelle mit dem ursprünglichen Namen
            createNewTable(tablename);

            status.changeState("Daten werden in neue Tabelle migriert...", levelnamelocal.size());
            
            // Füge Daten in die neue Tabelle ein
            String insert = "INSERT INTO " + tablename + " (placement, levelname, levelnameRaw, levelID, author, creators, verifier, verificationLink, percentToQualify, records, attempts, completed, locked, personalBest, levelLength) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insert)) {
            	for (int i = 0; i < levelnamelocal.size(); i++) {
            		status.update(levelnamelocal.get(i), i);
            	    if (i < rawLevelNameslocal.size()) { // Überprüfen, ob der Index im Array rawLevelNameslocal gültig ist
            	        String currentRawLevelName = rawLevelNameslocal.get(i);
            	        if (data.allLevels().indexOf(currentRawLevelName) == -1) {
            	            // Das Level wurde entfernt, also sollte auch der Eintrag in der lokalen Datenbank entfernt werden
            	            removeEntry("levels", currentRawLevelName);
            	        } else {
            	            // Das Level existiert noch, füge es in die neue Tabelle ein
            	            pstmt.setInt(1, i + 1);
            	            pstmt.setString(2, levelnamelocal.get(i));
            	            pstmt.setString(3, currentRawLevelName);
            	            pstmt.setInt(4, Integer.parseInt(levelIDlocal.get(i)));
            	            pstmt.setString(5, authorlocal.get(i));
            	            pstmt.setString(6, creatorslocal.get(i));
            	            pstmt.setString(7, verifierlocal.get(i));
            	            pstmt.setString(8, verificationLinklocal.get(i));
            	            pstmt.setInt(9, Integer.parseInt(percenttoqualifylocal.get(i)));
            	            pstmt.setString(10, recordslocal.get(i));
            	            pstmt.setInt(11, attemptsLocal.get(i));
            	            pstmt.setBoolean(12, Boolean.parseBoolean(completedlocal.get(i)));
            	            pstmt.setBoolean(13, lockedLocal.get(i)); // Insert value of locked column
            	            pstmt.setString(14, pblocal.get(i));
            	            pstmt.setString(15, levelLengthlocal.get(i));
            	            pstmt.executeUpdate();
            	        }
            	    } else {
            	        // Index ist außerhalb des gültigen Bereichs, es gibt kein entsprechendes Level in rawLevelNameslocal
            	        // Das könnte bedeuten, dass das Level entfernt wurde, also sollte dieser Fall behandelt werden
            	        // Hier könntest du entscheiden, was in diesem Fall passieren soll, z.B. einen Log-Eintrag erstellen
            	        // oder den Code entsprechend anpassen
            	    }
            	}

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        status.dispose();
    }
    
    public void removeEntry(String tablename, String rawLevelName) throws SQLException {
        String sql = "DELETE FROM " + tablename + " WHERE levelNameRaw = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();

            System.out.println("Entry with rawLevelName '" + rawLevelName + "' removed from " + tablename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyData(String levelname, boolean completedStatus, int attempts, boolean lock, String percent, String levelLength) {
        String sql = "UPDATE levels SET completed = ?, attempts = ?, locked = ?, personalBest = ?, levelLength = ? WHERE levelname = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setBoolean(1, completedStatus);
        	pstmt.setInt(2, attempts);
        	pstmt.setBoolean(3, lock); // Korrigierte Reihenfolge
        	pstmt.setString(4, percent);
        	pstmt.setString(5, levelLength);
        	pstmt.setString(6, levelname); // Korrigierte Reihenfolge
            
           

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

    public void checkColumns(String tablename) {
        String[] spalten = {"placement", "levelname", "levelnameRaw", "levelID", "author", "creators", "verifier", "verificationLink", "percentToQualify", "records", "attempts", "completed", "locked", "personalBest", "levelLength"};

        try (Connection connection = DriverManager.getConnection(url)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet;

            int missing = 0;

            for (String spalte : spalten) {
                resultSet = metaData.getColumns(null, null, tablename, spalte);

                if (!resultSet.next()) {
                    System.out.println("Spalte " + spalte + " existiert nicht. Eine neue Spalte wird erstellt.");
                    createNewColumn(tablename, spalte);
                    missing++;
                }
            }

            if (missing > 0) {
                moveDataToNewDatabase(tablename);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createNewColumn(String tablename, String columnName) {
        String sql = "ALTER TABLE " + tablename + " ADD COLUMN " + columnName + " TEXT NOT NULL";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void moveDataToNewDatabase(String tablename) {
        // Neuen Tabellennamen für die Kopie
        String newTableName = tablename + "_new";

        // Erstelle die neue Tabelle
        createNewTable(newTableName);

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // SQL-Abfrage, um Daten von der alten Tabelle in die neue Tabelle zu kopieren
            String copyDataQuery = "INSERT INTO " + newTableName + " SELECT * FROM " + tablename;

            // Führe die SQL-Abfrage aus
            stmt.executeUpdate(copyDataQuery);

            // Lösche die alte Tabelle
            String dropOldTableQuery = "DROP TABLE IF EXISTS " + tablename;
            stmt.executeUpdate(dropOldTableQuery);

            // Benenne die neue Tabelle um, um das "_new" zu entfernen
            String renameTableQuery = "ALTER TABLE " + newTableName + " RENAME TO " + tablename;
            stmt.executeUpdate(renameTableQuery);

            System.out.println("Daten wurden erfolgreich von der alten Tabelle in die neue Tabelle kopiert.");
            System.out.println("Die alte Tabelle wurde gelöscht und die neue Tabelle umbenannt.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
