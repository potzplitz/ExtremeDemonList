package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.DownloadLevels;

public class MissingLevels {
	
	public void show(ArrayList<String> missinglevels, int missing) {
		
		JFrame main = new JFrame("Liste nicht aktuell");
        main.setSize(400, 360);
        main.setLayout(null); 
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel missingLevelsLabel = new JLabel("Für die Liste ist ein Update verfügbar.");
        missingLevelsLabel.setBounds(80, 1, 380, 20); 
        
        JLabel infoLabel = new JLabel("Folgende " + missing + " Level sind nicht vorhanden: ");
        infoLabel.setBounds(1, 40, 380, 20); 
        
        JLabel separator = new JLabel("____________________________________________________________________________________________________________________");
        separator.setBounds(0, 260, 500, 30);
        
        JLabel question = new JLabel("Möchten Sie die Liste aktualisieren?");
        question.setBounds(90, 255, 500, 30);
        
        Button yes = new Button("Ja");
        yes.setBounds(72, 285, 100, 30);
        
        Button no = new Button("Nein");
        no.setBounds(200, 285, 100, 30);
        
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(1, 60, 383, 201);
        
        
        
        for(int i = 0; i < missinglevels.size(); i++) {
        	area.append(missinglevels.get(i) + "\n");
        }
        
        yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.dispose();
				DownloadLevels download = new DownloadLevels();
				try {
					download.download();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}        	
        });
        
        no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.dispose();
				MainGUI gui = new MainGUI();
				try {
					gui.build();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        });
        
		
        main.add(missingLevelsLabel);
        main.add(infoLabel);
        main.add(scroll);
        main.add(question);
        main.add(yes);
        main.add(no);
        main.add(separator);
        
        main.setVisible(true);
		
		
	}

}
