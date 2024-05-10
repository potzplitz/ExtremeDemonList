package data;

import api.GetApiData;
import database.Sqlite;

public class RequestLevelLength {
	
	GetApiData data = new GetApiData();
	FetchData fetch = new FetchData();
	Sqlite sql = new Sqlite("levels");

	public void request() {
		
		sql.queryData("levels");
		
		Thread thread = new Thread(new Runnable( ) {

			@Override
			public void run() {
				
				for(int i = 0; i < ManageFiles.getMissinglevels().size(); i++) {	
					sql.modifyData(sql.getLevelname().get(sql.getRawLevelNames().indexOf(sql.getRawLevelNames().get(i))), Boolean.parseBoolean(sql.getCompleted().get(i).toString()), sql.getAttempts().get(i).intValue(), sql.getLocked().get(i).booleanValue(), sql.getPbarr().get(i), "" + data.getLevelLength(Integer.parseInt(sql.getLevelID().get(sql.getRawLevelNames().indexOf(ManageFiles.getMissinglevels().get(i))))));
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		thread.start();
		
		
	}
	
}
