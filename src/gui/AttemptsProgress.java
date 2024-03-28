package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.FetchData;
import data.ManageFiles;

public class AttemptsProgress {
	
	JProgressBar bar = new JProgressBar();
	 JTextArea area = new JTextArea();
	JScrollPane scroll = new JScrollPane(area);
	JFrame main = new JFrame("Updater");
	
	
	public void build() {
		
		
        main.setSize(400, 300);
        main.setLayout(null); 
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JLabel info = new JLabel("Speicherstand wird gelesen...");
        info.setBounds(120, 1, 500, 30);
        
       
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
        
        main.add(info);
        main.add(scroll);
        main.add(bar);
        main.setVisible(true);
		
	}
	
	public void update(String level, int attempts, int selection, int index) {
		bar.setValue(index);
		area.append(level + " >>> " + attempts + " Attempts\n");
		area.setCaretPosition(area.getDocument().getLength());
	}
	
	public void close() {
		JOptionPane.showMessageDialog(null, "Attempts wurden erfolgreich übertragen.", "Fertig", JOptionPane.INFORMATION_MESSAGE);
		main.dispose();
	}

}
