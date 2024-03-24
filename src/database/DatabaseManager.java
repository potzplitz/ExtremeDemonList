package database;

public class DatabaseManager {
	
	public void manage() {
		Sqlite sql = new Sqlite("test");
		if(sql.exists()) {
			System.out.println(true);
		} else {
			System.out.println(false);
			sql.createNewDatabase();
		}
	}

}
