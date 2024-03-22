package api;

import jdash.client.GDClient;
import jdash.common.entity.GDLevel;

public class GetApiData {

	public String getGDSongID(int id) {
		
		GDClient client = GDClient.create();
		
		GDLevel level = client.findLevelById(id).block();
		
		String songID = level.song().get().downloadUrl() + "";
		songID = songID.substring(songID.indexOf("[") + 1, songID.length() - 1);
		
		return songID;
	}

}
