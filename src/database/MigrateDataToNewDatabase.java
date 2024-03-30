package database;

public class MigrateDataToNewDatabase {
	
	public void migrate() {
		
		Sqlite sql = new Sqlite("levels");
		sql.queryData("levels");
		
		
		
	}

}

