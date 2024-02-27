package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import data.GuiData;

public class MainGUI {
	
	public JFrame main = new JFrame();

	public void build() throws IOException {

		GuiData data = new GuiData();
		data.IndexLevelName();
		
		
		main.setSize(900, 700);
		main.setLayout(null);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JProgressBar progress = new JProgressBar();
		progress.setBounds(200, 300, 500, 30);
		progress.setStringPainted(true);
		progress.setMaximum(data.getLocalLength());
		
		JLabel info = new JLabel("Die Liste wird geladen");
		info.setBounds(380, 270, 300, 30);
		
		JLabel currentLevel = new JLabel();
		currentLevel.setBounds(200, 330, 200, 30);
		
		JPanel levelpanel = new JPanel();
		levelpanel.setBackground(Color.LIGHT_GRAY);
		levelpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		levelpanel.setLayout(new GridLayout(data.getLocalLevels().size(), 1));
		
		 JScrollPane scroll = new JScrollPane(levelpanel);
	     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	     scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     scroll.setBounds(0, 61, 700, 600);
	     scroll.setVisible(false);
	        
	        Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i = 0; i < data.getLocalLevels().size(); i++) {
						progress.setValue(i + 1);
						
						currentLevel.setText(data.getLocalLevels().get(i));
						
			        	JPanel contents = new JPanel();
			        	contents.setPreferredSize(new Dimension(600, 50));
			        	contents.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			        	JLabel levelname = new JLabel(data.getLocalLevels().get(i));
			        	
			        	contents.add(levelname);
			        	levelpanel.add(contents);
			        	
			        }
					scroll.setVisible(true);
					progress.setVisible(false);
					info.setVisible(false);
					currentLevel.setVisible(false);
				}
	        	
	        });
	        thread.start();
	        
		main.add(currentLevel);
	    main.add(info);
		main.add(scroll);
		main.add(progress);
		main.setVisible(true);
	}
	
}
