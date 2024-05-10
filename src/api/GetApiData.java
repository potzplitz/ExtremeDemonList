package api;

import jdash.client.GDClient;
import jdash.common.entity.GDLevel;

public class GetApiData {

	public String getSongURL(int id) {
		
		GDClient client = GDClient.create();
		GDLevel level = client.findLevelById(id).block();
		
		String url = level.song().get().downloadUrl() + "";
		url = url.substring(url.indexOf("[") + 1, url.length() - 1);
		
		return url;
	}
	
	public String getSongID(int id) {
		GDClient client = GDClient.create();
		GDLevel level = client.findLevelById(id).block();
		
		String songID = level.songId().get() + "";
		songID = songID.substring(songID.indexOf("[") + 1, songID.length() - 1);
		
		return songID;
	}
	
	private static int lock = 0;
	 GDClient client = null;
	 GDLevel level = null;
	
	public String getLevelLength(int id) {
		if(lock == 0) {
			client = GDClient.create();	
		}
		lock = 1;
		
		level = client.findLevelById(id).block();
		
		String length = level.length() + "";
		
		return length;
		
	}

}
