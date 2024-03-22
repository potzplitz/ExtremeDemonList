package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
	public JLabel creator = new JLabel("Creator");
	public JLabel separator = new JLabel("_______________________________________________________________________________________________________________");
	public JLabel separator2 = new JLabel("_______________________________________________________________________________________________________________");
	public JLabel separator3 = new JLabel("_______________________________________________________________________________________________________________");
	public JLabel verifier = new JLabel("Verifier");
	public JLabel victorcount = new JLabel("Anzahl Victors");
	public JLabel victor = new JLabel("Victors: ");
	public JLabel idshow = new JLabel("ID");
	public JLabel qualify = new JLabel("Qualifikation");
	public JPanel recordspanel = new JPanel();
	public JScrollPane records = new JScrollPane(recordspanel);
	public JCheckBox filtercompleted = new JCheckBox("Nach geschafft filtern");
	public Button copyid = new Button("Level ID kopieren");
	public Button showinfos = new Button("Mehr Infos anzeigen");
	public JButton settings = new JButton("⚙");
	GridLayout gridLayout = new GridLayout(3, 1);
	private String[] showing = {"Alle anzeigen", "Top 3", "Top 50", "Top 150", "Top 200"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JComboBox show = new JComboBox(showing);
	private FetchData fetch = new FetchData();
	private Elements elements = new Elements();
	
	private int completedcount = 0;
	 
	public void build() throws IOException {
		GuiData data = new GuiData();
		data.IndexData();
		
		gridLayout.setRows(data.getLocalLevels().size());
			
		main.setSize(900, 700);
		main.setLayout(null);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		level.setBounds(10, 10, 200, 30);
		level.setFont(level.getFont().deriveFont(15f));

		filtercompleted.setBounds(710, 15, 200, 30);
		
		progress.setBounds(200, 300, 500, 30);
		progress.setStringPainted(true);
		progress.setMaximum(data.getLocalLength());
			
		info.setBounds(380, 270, 300, 30);
	
		currentLevel.setBounds(200, 330, 200, 30);
		
		levelpanel.setBackground(Color.LIGHT_GRAY);
		levelpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		levelpanel.setLayout(gridLayout);

		victorcount.setBounds(10, 130, 164, 30);
		
		idshow.setBounds(10, 150, 164, 30);
		
		copyid.setBounds(10, 50, 164, 30);
		
		qualify.setBounds(10, 170, 164, 30);
		
		settings.setBounds(1, 1, 60, 60);
		settings.setFont(settings.getFont().deriveFont(30f));
		settings.setBackground(Color.LIGHT_GRAY);
		
		victor.setBounds(1, 276, 164, 30);
		records.getVerticalScrollBar().setUnitIncrement(16);
		
		showinfos.setBounds(12, 227, 160, 30);
		 
	     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	     scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     scroll.setBounds(0, 61, 700, 600);
	     scroll.getVerticalScrollBar().setUnitIncrement(16);
	     scroll.setVisible(false);
	     
	     elements.infopanel().setBounds(700, 61, 184, 600);
	     elements.infopanel().setVisible(false);
	     
	     separator.setBounds(0, 70, 300, 30);
	     separator2.setBounds(0 ,178, 400, 30);
	     separator3.setBounds(0, 263, 300, 30);
	     
	     creator.setBounds(10, 90, 164, 30);
	     
	     verifier.setBounds(10, 110, 164, 30);
	     
	     search.setBounds(60, 1, 440, 60);
	     
	     show.setBounds(500, 1, 200, 60);
	     
	     recordspanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        
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
							completedcount++;
						}
			        	
			        	contents.addMouseListener(new MouseListener() {
							@Override
							public void mouseClicked(MouseEvent e) {	
								showinfos.addActionListener(new ActionListener() {
								    @Override
								    public void actionPerformed(ActionEvent e) {
								        String url = data.getYoutubeLink().get(index);
								        VerifyInfo ver = VerifyInfo.getInstance();
								        ver.showInfo(url, Integer.parseInt(data.getId().get(index)));
								    }
								});

								level.setText(data.getLocalLevels().get(index));
								verifier.setText("Verifier: " + data.getVerifier().get(index));
								creator.setText("Creator: " + data.getCreator().get(index));
								idshow.setText("ID: " + data.getId().get(index));
								qualify.setText("Qualifikation: " + data.getQualification().get(index) + "%");
								level.setVerticalAlignment(SwingConstants.CENTER);
								
								
								FetchData fetchData = new FetchData();
								
								try {
									recordspanel.setLayout(new GridLayout(GuiData.allVictors(fetchData.allLevels().get(index)).size(), 1));
								} catch (IOException e1) {
									
									e1.printStackTrace();
								}
								recordspanel.setBackground(Color.GRAY);
								
								records.setBounds(0, 302, 185, 300);
								
								recordspanel.removeAll();
								
								try {
								    ArrayList<String> victors = GuiData.allVictors(fetchData.allLevels().get(index));
								    
								    victorcount.setText("Anzahl Victors: " + victors.size());
								    
								    recordspanel.setLayout(new GridLayout(victors.size(), 1));

								    for(String victor : victors) {
								        JPanel contents = new JPanel();
								        contents.setPreferredSize(new Dimension(165, 50));
								        contents.setLayout(null);
								        contents.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

								        JLabel name = new JLabel(victor);
								        name.setBounds(10, 10, 100, 30);
								        contents.add(name);

								        recordspanel.add(contents);
								    }
								} catch (IOException e1) {
								    e1.printStackTrace();
								}

								recordspanel.revalidate();
								
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
			        	
			        	JLabel rank = new JLabel("#" + (i + 1));
			        	rank.setBounds(10, 10, 40, 30);
			        	rank.setName(i + "");
			        	
			        	filtercompleted.setText("nach Geschafft filtern (" + completedcount + ")");
			        	
			        	show.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								levelpanel.add(contents, 0);
								if(show.getSelectedIndex() == 1) {
					        		if(!(Integer.parseInt(rank.getName()) >= 0 && Integer.parseInt(rank.getName()) <= 2)) {
					        			levelpanel.remove(contents);
					        			
					        			levelpanel.repaint();
					        			levelpanel.revalidate();
					        		}
								} else if(show.getSelectedIndex() == 0) {	
					        			levelpanel.add(contents, 0);
					        			levelpanel.repaint();
					        			levelpanel.revalidate();	
					        	} else if(show.getSelectedIndex() == 2) {
					        		if(!(Integer.parseInt(rank.getName()) >= 0 && Integer.parseInt(rank.getName()) <= 49)) {
					        			levelpanel.remove(contents);
					        			levelpanel.repaint();
					        			levelpanel.revalidate();
					        		}
					        	} else if(show.getSelectedIndex() == 3) {
					        		if(!(Integer.parseInt(rank.getName()) >= 0 && Integer.parseInt(rank.getName()) <= 149)) {
					        			levelpanel.remove(contents);
					        			levelpanel.repaint();
					        			levelpanel.revalidate();
					        		}	
					        	} else if(show.getSelectedIndex() == 4) {
					        		if(!(Integer.parseInt(rank.getName()) >= 0 && Integer.parseInt(rank.getName()) <= 199)) {
				        			levelpanel.remove(contents);
				        			levelpanel.repaint();
				        			levelpanel.revalidate();
				        		}
				        	} 
								gridLayout.setRows(levelpanel.getComponentCount());
								gridLayout.setColumns(1);
								levelpanel.revalidate();
	        	                scroll.repaint();
	        	                scroll.revalidate();
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
								gridLayout.setRows(levelpanel.getComponentCount());
								gridLayout.setColumns(1);
								levelpanel.revalidate();
	        	                scroll.repaint();
	        	                scroll.revalidate();
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
			        	                gridLayout.setRows(data.getLocalLength());
			        	                scroll.repaint();
			        	                scroll.revalidate();
			        	            }
			        	        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
			        	            levelpanel.add(contents, 0);
			        	        }
			        	        levelpanel.repaint();
			        	        levelpanel.revalidate();
			        	    }
			        	}); 
  	
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
	        
	        settings.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					SettingsGui gui = new SettingsGui();
					gui.showSettings();
					
				}
	        	
	        });
	    
	    elements.infopanel().add(copyid);
	    elements.infopanel().add(level, SwingConstants.CENTER);
	    elements.infopanel().add(separator);
	    elements.infopanel().add(separator2);
	    elements.infopanel().add(separator3);
	    elements.infopanel().add(creator);
	    elements.infopanel().add(verifier);
	    elements.infopanel().add(records);
	    elements.infopanel().add(victorcount);
	    elements.infopanel().add(idshow);
	    elements.infopanel().add(qualify);
	    elements.infopanel().add(victor);
	    elements.infopanel().add(showinfos);
	               
	    main.add(search);
		main.add(currentLevel);
	    main.add(info);
		main.add(scroll);
		main.add(progress);
		main.add(filtercompleted);
		main.add(show);
		main.add(settings);
		main.add(elements.infopanel());
		main.setVisible(true);
	}
}