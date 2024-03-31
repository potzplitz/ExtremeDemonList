package data;

import java.util.ArrayList;
import java.util.Comparator;

import database.Sqlite;

public class SortData {
	
	private ArrayList<Integer> sorter = new ArrayList<Integer>();
	
	public ArrayList<Integer> getSort() {
		return sorter;
	}
	
	public void sort() {
		
		Sqlite sql = new Sqlite("levels");
		sql.queryData("levels");
		
		for(int i = 0; i < sql.getPbarr().size(); i++) {
			if(sql.getPbarr().get(i) == null) {
			sorter.add(Integer.parseInt("0"));
			} else {
			sorter.add(Integer.parseInt(sql.getPbarr().get(i)));
			}
			
		}
		
		sorter.sort(Comparator.naturalOrder());
		
		System.out.println(sorter.size());
		
	}

}
