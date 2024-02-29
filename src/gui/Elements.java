package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class Elements {
	
	private int lock = 0;
	
	private JPanel panel = new JPanel();
	
	public JPanel infopanel() {
		
		if(lock == 0) {
			lock = 1;
			
			System.out.println("sdfadsf");
			
			
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setLayout(null);
			
			
		}
		
		return panel;
		
	}

}
