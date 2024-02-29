package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import data.FetchData;
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
	public JLabel level = new JLabel("Liste");
	public JCheckBox filtercompleted = new JCheckBox("Nach geschaft filtern");
	public Button copyid = new Button("Level ID kopieren");
	private FetchData fetch = new FetchData();
	private Elements elements = new Elements();
	

	public void build() throws IOException {

		GuiData data = new GuiData();
		data.IndexLevelName();
		data.IndexLevelID();
			
		main.setSize(900, 700);
		main.setLayout(null);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		level.setBounds(10, 10, 200, 30);
		level.setFont(level.getFont().deriveFont(20f));
		
		filtercompleted.setBounds(720, 15, 200, 30);
		
		progress.setBounds(200, 300, 500, 30);
		progress.setStringPainted(true);
		progress.setMaximum(data.getLocalLength());
			
		info.setBounds(380, 270, 300, 30);
	
		currentLevel.setBounds(200, 330, 200, 30);
		
		levelpanel.setBackground(Color.LIGHT_GRAY);
		levelpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		levelpanel.setLayout(new GridLayout(data.getLocalLevels().size(), 1));
		
		copyid.setBounds(10, 50, 100, 30);
		 
	     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	     scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     scroll.setBounds(0, 61, 700, 600);
	     scroll.getVerticalScrollBar().setUnitIncrement(16);
	     scroll.setVisible(false);
	     
	     elements.infopanel().setBounds(700, 61, 184, 600);
	     elements.infopanel().setVisible(false);
	     
	     search.setBounds(1, 1, 700, 60);
	        
	        Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i = 0; i < data.getLocalLevels().size(); i++) {
						 final int index = i;
						progress.setValue(i + 1);
						
						currentLevel.setText(data.getLocalLevels().get(i));
						
						
						
						
						
			        	JPanel contents = new JPanel();
			        	contents.setName(data.getLocalLevels().get(i));
			        	contents.setPreferredSize(new Dimension(600, 50));
			        	contents.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			        	contents.setLayout(null);
			        	
			        	
			        	
			        	JButton completed = new JButton("x");
			        	completed.setBounds(640, 17, 17, 17);
			        	completed.setMargin(new Insets(0,0,0,0));
			        	
			        	JButton uncompleted = new JButton("\u2713");
			        	uncompleted.setBounds(640, 17, 17, 17);
			        	uncompleted.setMargin(new Insets(0,0,0,0));
			        	
			        	File file = new File("C:\\ExtremeDemonList\\completed\\" + fetch.allLevels().get(i)+ ".json");
			        	
			        	
			        	
			        	completed.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
									contents.setBackground(Color.decode("#cbffbf"));
									
									completed.setVisible(false);
									uncompleted.setVisible(true);
									
									try {
										file.createNewFile();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							}
			        		
			        	});
			        	
			        	uncompleted.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
									contents.setBackground(Color.WHITE);
									
									uncompleted.setVisible(false);
									completed.setVisible(true);
									
									file.delete();
							}
			        		
			        	});
			        	
						if(new File("C:\\ExtremeDemonList\\completed\\" + fetch.allLevels().get(i)+ ".json").exists()) {
							contents.setBackground(Color.decode("#cbffbf"));
							uncompleted.setVisible(true);
							completed.setVisible(false);
						}
			        	
			        	contents.addMouseListener(new MouseListener() {
							@Override
							public void mouseClicked(MouseEvent e) {	

								level.setText(data.getLocalLevels().get(index));
								level.setVerticalAlignment(SwingConstants.CENTER);
								
								copyid.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										
										StringSelection stringSelection = new StringSelection(data.getId().get(index));
										Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
										clipboard.setContents(stringSelection, null);	
										
									}
					        		
					        	});
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
			        	levelname.setBounds(290, 10, 300, 30);
			        	
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
	
							}
							@Override
							public void keyReleased(KeyEvent e) {		
							}	
						});
			        	
			        	filtercompleted.addItemListener(new ItemListener() {
			        	    @Override
			        	    public void itemStateChanged(ItemEvent e) {
			        	        if (e.getStateChange() == ItemEvent.SELECTED) {
			        	            if (!contents.getBackground().equals(Color.decode("#cbffbf"))) {
			        	                levelpanel.remove(contents);
			        	            }
			        	        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
			        	            levelpanel.add(contents, 0); // Füge das Element am Anfang hinzu
			        	        }
			        	        levelpanel.repaint();
			        	        levelpanel.revalidate();
			        	    }
			        	});
			        	
			        	JLabel rank = new JLabel("#" + (i + 1));
			        	rank.setBounds(10, 10, 40, 30);
			        	
			        	contents.add(levelname);
			        	contents.add(rank);
			        	contents.add(completed);
			        	contents.add(uncompleted);
			        	levelpanel.add(contents);
			        	
			        }
					scroll.setVisible(true);
					elements.infopanel().setVisible(true);
					progress.setVisible(false);
					info.setVisible(false);
					currentLevel.setVisible(false);
				}
	        	
	        });
	        thread.start();
	    
	    elements.infopanel().add(copyid);
	    elements.infopanel().add(level, SwingConstants.CENTER);
	        
	       
	    main.add(search);
		main.add(currentLevel);
	    main.add(info);
		main.add(scroll);
		main.add(progress);
		main.add(filtercompleted);
		main.add(elements.infopanel());
		main.setVisible(true);
	}
	
}
