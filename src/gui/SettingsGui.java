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
		
		Button button = new Button("Daten auf neues System migrieren");
		button.setBounds(20, 20, 200, 30);
		
		Button save = new Button("speichern");
		save.setBounds(430, 230, 100, 30);
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WriteSettings write = new WriteSettings();
				try {
					write.write();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MigrateData migrate = new MigrateData();
				migrate.migrateData();
				
			}
			
		});
		
		settings.add(button);
		settings.add(save);
	}

}
