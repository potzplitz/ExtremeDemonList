package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.FetchData;
import data.ManageFiles;
import database.Sqlite;

public class AttemptsProgress {
	
	JProgressBar bar = new JProgressBar();
	 JTextArea area = new JTextArea();
	JScrollPane scroll = new JScrollPane(area);
	JFrame main = new JFrame("Updater");
	JLabel zeit = new JLabel("Verstrichene Zeit: ");
	
	private float timer;
	private float finishedtime;
	private boolean running = false;
	
	
	public void build() {
		
		running = true;
		
		
        main.setSize(400, 300);
        main.setLayout(null); 
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        
        zeit.setBounds(90, 1, 500, 30);
        
       
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        
        
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(1, 60, 383, 201);
        
        FetchData data = new FetchData();
        
        bar.setBounds(1, 29, 382, 30);
        bar.setMinimum(0);
        bar.setMaximum(data.allLevels().size() - 1);
        bar.setStringPainted(true);
        
        main.add(zeit);
        main.add(scroll);
        main.add(bar);
        main.setVisible(true);
        
        area.append("Speicherstand wird eingelesen...");
		
	}
	
	public void update(String level, int attempts, int percent, int index) {
		bar.setValue(index);
		area.append(index + "| " + level + " >>> " + attempts + " Attempts, Progress: " + percent + "%\n");
		area.setCaretPosition(area.getDocument().getLength());
		timer = 0;
	}
	
	public void close() {
		running = false;
		JOptionPane.showMessageDialog(null, "Attempts wurden erfolgreich übertragen.", "Fertig", JOptionPane.INFORMATION_MESSAGE);
		main.dispose();
	}

}
