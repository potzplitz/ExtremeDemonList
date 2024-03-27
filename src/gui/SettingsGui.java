package gui;

import javax.swing.JFrame;

public class SettingsGui {
	
	public void showSettings() {
		JFrame settings = new JFrame("Einstellungen");
		settings.setLayout(null);
		settings.setResizable(false);
		settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settings.setSize(500, 500);
		settings.setVisible(true);
		
		
	}

}
