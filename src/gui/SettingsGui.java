package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;

import settingsfunctions.DeleteDatabase;
import settingsfunctions.MigrateData;
import settingsfunctions.WriteSettings;

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
