package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class SearchEngine {
	
	MainGUI gui = new MainGUI();
	
	JTextField searchbar = gui.search;
	
	public void Filter(String query) {
		System.out.println(query);
	}

}
