package gui;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import readsafefile.SafeFileManager;
import settingsfunctions.ReadAttempts;

public class SettingsGui {
	
	public void showSettings() {
		JFrame settings = new JFrame("Einstellungen");
		settings.setLayout(null);
		settings.setResizable(false);
		settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settings.setSize(500, 500);
		settings.setVisible(true);
		
		int val = 120;
		int valold = 20;
		
		StringBuilder str = new StringBuilder();
		
		int jbuffer = 0;

		for(int i = 0; i <= 3; i++) {
			for(int j = 0; j <= 100; j++) {
			str.append("_");
			jbuffer = j;
			}	
				JLabel separator = new JLabel(str.toString());
				separator.setBounds(0, valold, 500, 30);
				separator.setVisible(true);
				settings.add(separator);
		
				valold += val;

				str.delete(0, jbuffer);	
		}
		
		JLabel datenbank = new JLabel("Spielstand");
		datenbank.setBounds(1, 10, 100, 30);
		datenbank.setFont(new Font(null, Font.BOLD, 16));
		datenbank.setVisible(true);
		
		JLabel data = new JLabel("Datenbank");
		data.setBounds(1, 130, 100, 30);
		data.setFont(new Font(null, Font.BOLD, 16));
		
		Button refresh = new Button("Speicherstand neu laden");
		refresh.setBounds(250, 86, 150, 30);
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SafeFileManager mgr = new SafeFileManager();
				try {
					mgr.DecryptSafeFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		Button button = new Button("Attempts Indexieren");
		button.setBounds(60, 86, 150, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ReadAttempts atts = new ReadAttempts();
				try {
					atts.readAttempts();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		Button lengthReq = new Button("Länge der Level abfragen");
		
		Button delete = new Button("Datenbank löschen");
		delete.setBounds(150, 206, 150, 30);
		
		settings.add(button);
		settings.add(datenbank);
		settings.add(refresh);
		settings.add(data);
		settings.add(delete);
		
	}

}
