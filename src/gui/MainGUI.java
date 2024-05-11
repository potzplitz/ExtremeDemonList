package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

import api.GetApiData;
import data.SortData;
import database.Sqlite;

public class MainGUI {
	
	public JFrame main = new JFrame("Extreme Demon List");
	public JProgressBar progress = new JProgressBar();
	public JLabel info = new JLabel("Die Liste wird geladen");
	public JLabel currentLevel = new JLabel();
	public JPanel levelpanel = new JPanel();
	public JScrollPane scroll = new JScrollPane(levelpanel);
	public JTextField search = new JTextField();
	public JLabel levelname;
	public JLabel percent;
	public JLabel level = new JLabel("Liste");
	public JLabel creator = new JLabel("Creator");
	public JLabel separator = new JLabel("_______________________________________________________________________________________________________________");
	public JLabel separator2 = new JLabel("_______________________________________________________________________________________________________________");
	public JLabel verifier = new JLabel("Verifier");
	public JLabel victorcount = new JLabel("Anzahl Victors");
	public JLabel idshow = new JLabel("ID");
	public JLabel qualify = new JLabel("Qualifikation");
	public JLabel attemptslabel = new JLabel("Attempts");
	public JLabel lengthLabel = new JLabel("Länge");
	public JCheckBox filtercompleted = new JCheckBox("Nach geschafft filtern");
	public Button copyid = new Button("Level ID kopieren");
	public Button showinfos = new Button("Mehr Infos anzeigen");
	public Button startgame = new Button("Geometry Dash starten");
	public Button showcalc = new Button("Abstand Berechnen");
	public JButton settings = new JButton("⚙");
	GridLayout gridLayout = new GridLayout(3, 1);
	private String[] showing = {"Alle anzeigen", "Top 3", "Top 50", "Top 150", "Top 200"};
	private String[] sortlist = {"Nach Plazierung filtern", "Absteigend", "Aufsteigend"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JComboBox show = new JComboBox(showing);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JComboBox sortbox = new JComboBox(sortlist);
	private Elements elements = new Elements();
	private SortData sort = new SortData();
	
	private int completedcount = 0;
	
	 
	public void build() throws IOException {

		Sqlite data = new Sqlite("levels");
		data.queryData("levels");
		
		GetApiData api = new GetApiData();
		
		//sort.sort();
		
		gridLayout.setRows(data.getLevelname().size());
		
			
		main.setSize(900, 700);
		main.setLayout(null);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		main.setIconImage(new ImageIcon("rsc/demon-6.png").getImage());
		
		
		level.setBounds(10, 10, 200, 30);
		level.setFont(level.getFont().deriveFont(15f));

		filtercompleted.setBounds(710, 15, 200, 30);
		
		progress.setBounds(200, 300, 500, 30);
		progress.setStringPainted(true);
		progress.setMaximum(data.getLevelname().size());
			
		info.setBounds(380, 270, 300, 30);
	
		currentLevel.setBounds(200, 330, 200, 30);

		levelpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		levelpanel.setLayout(gridLayout);

		victorcount.setBounds(10, 90, 164, 30);
		
		idshow.setBounds(10, 110, 164, 30);
		
		copyid.setBounds(10, 210, 164, 30);
		
		qualify.setBounds(10, 130, 164, 30);
		
		attemptslabel.setBounds(10, 150, 164, 30);
		
		lengthLabel.setBounds(10, 170, 164, 30);
		
		settings.setBounds(1, 1, 60, 60);
		settings.setFont(settings.getFont().deriveFont(30f));
		settings.setBackground(Color.LIGHT_GRAY);
		
		showinfos.setBounds(12, 257, 160, 30);
		
		showcalc.setBounds(12, 303, 160, 30);
		
		startgame.setBounds(12, 560, 160, 30);
		 
	     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	     scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     scroll.setBounds(0, 61, 700, 600);
	     scroll.getVerticalScrollBar().setUnitIncrement(16);
	     scroll.setVisible(false);
	     
	     elements.infopanel().setBounds(700, 61, 184, 600);
	     elements.infopanel().setVisible(false);
	     
	     separator.setBounds(0, 30, 300, 30);
	     separator2.setBounds(0 ,180, 400, 30);
	     
	     creator.setBounds(10, 50, 164, 30);
	     
	     verifier.setBounds(10, 70, 164, 30);
	     
	     search.setBounds(60, 1, 440, 60);
	     
	     show.setBounds(500, 1, 200, 30);
	     
	     sortbox.setBounds(500, 31, 200, 30);
	     
	     
	      
	        
	        Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					boolean[] comp = new boolean[data.getLevelname().size()];
					boolean[] lockbool = new boolean[data.getLevelname().size()];
					for(int i = 0; i < data.getLevelname().size(); i++) {
						 final int index = i;
						progress.setValue(i + 1);
						
						currentLevel.setText(data.getLevelname().get(i));
						
			        	JPanel contents = new JPanel();
			        	contents.setName(data.getLevelname().get(i));
			        	contents.setPreferredSize(new Dimension(600, 50));
			        	contents.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			        	contents.setLayout(null);
			        	
			        	JPanel lockind = new JPanel();
			        	lockind.setBounds(678, 0, 2, 50);
			        	
			        	JButton completed = new JButton("x");
			        	completed.setBounds(610, 17, 18, 18);
			        	completed.setMargin(new Insets(0,0,0,0));
			        	completed.setVisible(false);
			        	
			        	JButton uncompleted = new JButton("\u2713");
			        	uncompleted.setBounds(610, 17, 18, 18);
			        	uncompleted.setMargin(new Insets(0,0,0,0));
			        	uncompleted.setVisible(false);
			        	
			        	percent = new JLabel("0%");
			        	percent.setBounds(130, 10, 100, 30);
			        	
			        	JTextField attempts = new JTextField("0");
			        	attempts.setBounds(500, 17, 70, 18);
			        	attempts.setVisible(false);

			        	JButton confirm = new JButton("\uD83D\uDDAB");
			        	confirm.setBounds(640, 17, 18, 18);
			        	confirm.setMargin(new Insets(0, 0, 0, 0));
			        	
			        	JButton anpassen = new JButton("⚙");
			        	anpassen.setBounds(640, 17, 18, 18);
			        	anpassen.setMargin(new Insets(0, 0, 0, 0));
			        	
			        	JButton sperren = new JButton("\uD83D\uDD12"); // Schlüssel-Symbol
			            sperren.setBounds(580, 17, 18, 18);
			            sperren.setMargin(new Insets(0, 0, 0, 0));
			            sperren.setVisible(false);
			        	
			        	if(data.getAttempts().get(i) != null) {
			        		attempts.setText(data.getAttempts().get(i) + "");
			        	}
			        	
			        	if(data.getLocked().get(index)) {
			        		lockbool[index] = true;
			        		lockind.setBackground(Color.RED);
			        	}
			        	
			        	sperren.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {    
								
								if(lockbool[index]) {
									lockbool[index] = false;
									lockind.setBackground(Color.WHITE);
								} else {
									lockbool[index] = true;
									
									lockind.setBackground(Color.RED);
								}
								
							}
			        		
			        	});
			        	
			        	
			        	completed.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
									
									completed.setVisible(false);
									uncompleted.setVisible(true);
									comp[index] = true;
									
								}	
			        		});
			        	
			        	uncompleted.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {		
									uncompleted.setVisible(false);
									completed.setVisible(true);		
									comp[index] = false;
							}        		
			        	});
			        	
			        	anpassen.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								anpassen.setVisible(false);
								confirm.setVisible(true);
								attempts.setVisible(true);
								sperren.setVisible(true);
								if(Boolean.parseBoolean(data.getCompleted().get(index))) {
									uncompleted.setVisible(true);
									completed.setVisible(false);
								
								} else {
									uncompleted.setVisible(false);
									completed.setVisible(true);
								}	
								
							}
			        	});
			        	
			        	confirm.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								
								if(!(comp[index] == Boolean.parseBoolean(data.getCompleted().get(index))) || !(attempts.getText().equals(data.getAttempts().get(index) + "")) || !(data.getLocked().get(index) == lockbool[index])) {
									data.modifyData(data.getLevelname().get(index), comp[index], Integer.parseInt(attempts.getText()), lockbool[index], data.getPbarr().get(index), data.getLevelLength().get(index));
								}
								
								
								
								if(!comp[index]) {
									contents.setBackground(Color.WHITE);
								} else {
									contents.setBackground(Color.decode("#cbffbf"));	
								}
								
								anpassen.setVisible(true);
								confirm.setVisible(false);
								uncompleted.setVisible(false);
								completed.setVisible(false);		
								attempts.setVisible(false);
								sperren.setVisible(false);
							}	
			        	});
			        	
						if(Boolean.parseBoolean(data.getCompleted().get(index))) {
							contents.setBackground(Color.decode("#cbffbf"));
							comp[index] = true;
							completedcount++;
						} else {
							comp[index] = false;
						}
			        	
			        	contents.addMouseListener(new MouseListener() {
							@Override
							public void mouseClicked(MouseEvent e) {
								showinfos.addActionListener(new ActionListener() {
								    @Override
								    public void actionPerformed(ActionEvent e) {
								        String url = data.getVerificationLink().get(index);
								        VerifyInfo ver = VerifyInfo.getInstance();
								        ver.showInfo(url, Integer.parseInt(data.getLevelID().get(index)));
								    }
								});

								level.setText(data.getLevelname().get(index));
								verifier.setText("Verifier: " + data.getVerifier().get(index));
								creator.setText("Creator: " + data.getAuthor().get(index));
								idshow.setText("ID: " + data.getLevelID().get(index));
								qualify.setText("Qualifikation: " + data.getPercenttoqualify().get(index) + "%");
								attemptslabel.setText("Attempts: " + data.getAttempts().get(index));
								level.setVerticalAlignment(SwingConstants.CENTER);
								
								String levellength = data.getLevelLength().get(index);
								lengthLabel.setText("Länge: lädt...");
								
								if(data.getLevelLength().get(index).equals("N/A")) {
									levellength = api.getLevelLength(Integer.parseInt(data.getLevelID().get(index)));
									data.modifyData(data.getLevelname().get(index), comp[index], Integer.parseInt(attempts.getText()), lockbool[index], data.getPbarr().get(index), levellength);
									System.out.println("request");
								}
								
								lengthLabel.setText("Länge: " + levellength);
								
								copyid.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										StringSelection stringSelection = new StringSelection(data.getLevelID().get(index));
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
			        	
			        	if(data.getPbarr().get(i) == null) {
			        		percent.setText("0%");
			        	} else {
			        		percent.setText(data.getPbarr().get(i) + "%");
			        	}
			        	
			        	if(Boolean.parseBoolean(data.getCompleted().get(i))) {
			        		percent.setText("100%");
			        		percent.setForeground(Color.decode("#008000"));
			        	}
			        	
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
			        	levelname.setText(data.getLevelname().get(i));
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
			        	        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
			        	        scroll.setVisible(false);
			        	        if (isSelected && !contents.getBackground().equals(Color.decode("#cbffbf"))) {
			        	            levelpanel.remove(contents);
			        	            gridLayout.setRows(data.getLevelname().size());
			        	            scroll.repaint();
			        	            scroll.revalidate();
			        	        } else if (!isSelected) {
			        	            levelpanel.add(contents, 0);
			        	        }
			        	        levelpanel.repaint();
			        	        levelpanel.revalidate();
			        	        scroll.setVisible(true);
			        	    }
			        	});

			        	
  	
			        	contents.add(levelname);
			        	contents.add(rank);
			        	contents.add(completed);
			        	contents.add(uncompleted);
			        	contents.add(anpassen);
			        	contents.add(confirm);
			        	contents.add(attempts);
			        	contents.add(sperren);
			        	contents.add(lockind);
			        	contents.add(percent);
			        	levelpanel.add(contents);

			        	
			        }
					
					sortbox.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(sortbox.getSelectedIndex() == 1) {
								//levelpanel.removeAll();
								for(int k = 0; k < sort.getSort().size(); k++) {
									//levelpanel.add();
								}
							}
							
						}
						
					});

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
	        
	        startgame.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					try {
						Process process = Runtime.getRuntime().exec("cmd /c start steam://rungameid/322170");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
							
					
				}
	        	
	        });
	        
	        showcalc.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					DifferenceCaluclator calc = new DifferenceCaluclator();
					calc.calculate();
				}
	        	
	        });
	    
	    elements.infopanel().add(copyid);
	    elements.infopanel().add(level, SwingConstants.CENTER);
	    elements.infopanel().add(separator);
	    elements.infopanel().add(separator2);
	    elements.infopanel().add(creator);
	    elements.infopanel().add(verifier);
	    elements.infopanel().add(victorcount);
	    elements.infopanel().add(idshow);
	    elements.infopanel().add(qualify);
	    elements.infopanel().add(showinfos);
	    elements.infopanel().add(attemptslabel);
	    elements.infopanel().add(showcalc);
	    elements.infopanel().add(lengthLabel);
	  //  elements.infopanel().add(startgame);
	               
	    main.add(search);
		main.add(currentLevel);
	    main.add(info);
		main.add(scroll);
		main.add(progress);
		main.add(filtercompleted);
		main.add(show);
		main.add(settings);
		main.add(sortbox);
		main.add(elements.infopanel());
		main.setVisible(true);
	}
}