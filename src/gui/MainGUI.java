package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import data.GuiData;

public class MainGUI {
	
	public JFrame main = new JFrame("Extreme Demon List");
	public JProgressBar progress = new JProgressBar();
	public JLabel info = new JLabel("Die Liste wird geladen");
	public JLabel currentLevel = new JLabel();
	public JPanel levelpanel = new JPanel();
	public JScrollPane scroll = new JScrollPane(levelpanel);
	public JTextField search = new JTextField();
	public JLabel levelname;
	

	public void build() throws IOException {

		GuiData data = new GuiData();
		data.IndexLevelName();
			
		main.setSize(900, 700);
		main.setLayout(null);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		progress.setBounds(200, 300, 500, 30);
		progress.setStringPainted(true);
		progress.setMaximum(data.getLocalLength());
			
		info.setBounds(380, 270, 300, 30);
	
		currentLevel.setBounds(200, 330, 200, 30);
		
		levelpanel.setBackground(Color.LIGHT_GRAY);
		levelpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		levelpanel.setLayout(new GridLayout(data.getLocalLevels().size(), 1));
		
		 
	     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	     scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     scroll.setBounds(0, 61, 700, 600);
	     scroll.getVerticalScrollBar().setUnitIncrement(16);
	     scroll.setVisible(false);
	     
	     search.setBounds(1, 1, 700, 60);
	        
	        Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i = 0; i < data.getLocalLevels().size(); i++) {
						progress.setValue(i + 1);
						
						currentLevel.setText(data.getLocalLevels().get(i));
						
						
						
			        	JPanel contents = new JPanel();
			        	contents.setName(data.getLocalLevels().get(i));
			        	contents.setPreferredSize(new Dimension(600, 50));
			        	contents.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			        	contents.setLayout(null);
			        	
			        	contents.addMouseListener(new MouseListener() {
							@Override
							public void mouseClicked(MouseEvent e) {						
							}
							@Override
							public void mousePressed(MouseEvent e) {						
							}
							@Override
							public void mouseReleased(MouseEvent e) {						
							}
							@Override
							public void mouseEntered(MouseEvent e) {	
								contents.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							}
							@Override
							public void mouseExited(MouseEvent e) {	
								contents.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
							}        		
			        	});
			        	
			        	levelname = new JLabel();
			        	levelname.setText(data.getLocalLevels().get(i));
			        	levelname.setBounds(10, 10, 300, 30);
			        	
			        	search.addKeyListener(new KeyListener() {

							@Override
							public void keyTyped(KeyEvent e) {
								if(!contents.getName().toLowerCase().contains(search.getText().toLowerCase())) {
										levelpanel.remove(contents);
										levelpanel.repaint();
										levelpanel.revalidate();
								} else if(contents.getName().toLowerCase().contains(search.getText().toLowerCase())) {
									levelpanel.add(contents);
									levelpanel.repaint();
									levelpanel.revalidate();
								}
								
							}

							@Override
							public void keyPressed(KeyEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void keyReleased(KeyEvent e) {
								// TODO Auto-generated method stub
								
							}
							
						});

			        	
			        	
			        	JLabel rank = new JLabel("#" + (i + 1));
			        	rank.setBounds(640, 10, 40, 30);
			        	
			        	contents.add(levelname);
			        	contents.add(rank);
			        	levelpanel.add(contents);
			        	
			        }
					scroll.setVisible(true);
					progress.setVisible(false);
					info.setVisible(false);
					currentLevel.setVisible(false);
				}
	        	
	        });
	        thread.start();
	        
	        
	       
	    main.add(search);
		main.add(currentLevel);
	    main.add(info);
		main.add(scroll);
		main.add(progress);
		main.setVisible(true);
	}
	
}
