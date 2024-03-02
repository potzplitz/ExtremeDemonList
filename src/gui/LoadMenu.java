package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

public class LoadMenu {
	
	public JProgressBar load = new JProgressBar();
	JFrame loadwindow = new JFrame("Datenbank wird gelesen...");
	JLabel loading = new JLabel("Liste wird geladen...");
	
	public void onLoad() {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		load.setMinimum(0);
		load.setMaximum(4);
		
		
		load.setBounds(1, 178, 398, 20);
	
		
		loadwindow.setSize(400, 200);
		loadwindow.setUndecorated(true);
		loadwindow.setResizable(false);
		loadwindow.setLocation(dim.width/2-loadwindow.getSize().width/2, dim.height/2-loadwindow.getSize().height/2);
		loadwindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		loadwindow.setLayout(null);
		loadwindow.setVisible(true);
		
		JLabel label1 = new JLabel("by potzplitz");
		label1.setBounds(168, 80, 200, 30);
		label1.setVisible(true);
		loadwindow.add(label1);
		
		
		loading.setBounds(1, 155, 200, 30);
		loadwindow.add(loading);
		
		JLabel label = new JLabel("Extreme Demon List");
		label.setBounds(125, 20, 300, 100);
		label.setFont(label.getFont().deriveFont(20f));
		label.setVisible(true);
		loadwindow.add(label);
		
		loadwindow.add(load);
		
	}
	
	static int index = 0;
	
	public void updateBar(String state) {
		index++;
		load.setValue(index);
		loading.setText(state);
	}
	
	public void close() {
		loadwindow.dispose();
	}

}
