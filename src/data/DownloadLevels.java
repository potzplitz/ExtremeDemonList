package data;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import database.DatabaseManager;
import gui.MainGUI;
import settingsfunctions.LoadSettings;
import settingsfunctions.MigrateData;

public class DownloadLevels {
	
	public void download() throws IOException {
		
		
		JFrame main = new JFrame("Updater");
        main.setSize(400, 300);
        main.setLayout(null); 
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JLabel info = new JLabel("Es werden " + ManageFiles.getMissinglevels().size() + " Level heruntergeladen.");
        info.setBounds(80, 1, 500, 30);
        
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        
        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(1, 60, 383, 201);
        
        JProgressBar bar = new JProgressBar();
        bar.setBounds(1, 29, 382, 30);
        bar.setMinimum(0);
        bar.setMaximum(ManageFiles.getMissinglevels().size());
        bar.setStringPainted(true);
        
        main.add(info);
        main.add(scroll);
        main.add(bar);
        main.setVisible(true);
        
        LoadSettings settings = new LoadSettings();
        settings.load();
		
        
        
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
            	String fileURL;
            	for(int i = 0; i < ManageFiles.getMissinglevels().size(); i++) {
        			
            		area.setCaretPosition(area.getDocument().getLength());
            		
        			bar.setValue(i + 1);
        			
        			area.append(i + 1 + "| " + ManageFiles.getMissinglevels().get(i) + " wird heruntergeladen. ");
        			
        			System.out.println("downloading " + ManageFiles.getMissinglevels().get(i));
        			fileURL = "https://raw.githubusercontent.com/All-Rated-Extreme-Demon-List/AREDL/main/data/" + ManageFiles.getMissinglevels().get(i) + ".json";
        			
        		try (BufferedInputStream in = new BufferedInputStream(new URL(fileURL).openStream());
        				
        	             FileOutputStream fileOutputStream = new FileOutputStream("C:\\ExtremeDemonList\\levels\\" + ManageFiles.getMissinglevels().get(i) + ".json")) {
        			
        	            byte dataBuffer[] = new byte[1024];
        	            int bytesRead;
        	            
        	            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
        	            	
        	                fileOutputStream.write(dataBuffer, 0, bytesRead);
        	                
        	            }
        	        } catch (IOException e) {
        	            e.printStackTrace();
        	        }
        			area.append(" >> ERFOLGREICH \n");
        		}

            	JOptionPane.showMessageDialog(null, "Alle " + ManageFiles.getMissinglevels().size() + " Level wurden erfolgreich heruntergeladen.", "Download abgeschlossen", JOptionPane.INFORMATION_MESSAGE);
            	main.dispose();

            		MainGUI gui = new MainGUI();
            		MigrateData migrate = new MigrateData();
            		migrate.migrateData();
            		try {
						gui.build();
					} catch (IOException e) {
						e.printStackTrace();
					}
            		
            }     
        });
        serverThread.start();
	}
}
