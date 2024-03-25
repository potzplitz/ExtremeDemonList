package data;

import java.util.ArrayList;

public class SortDatabase {
	
	public void sort() {
		FetchData data = new FetchData();
		
		
		String[][] levels = {};
		
		
		for(int i = 0; i < data.allLevels().size(); i++) {
			levels[i][0] = i + "";
			levels[i][1] = data.allLevels().get(i);
		}
		
		
	}

}
