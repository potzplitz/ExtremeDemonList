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

public class DownloadLevels {
	
	private ManageFiles data = new ManageFiles();
	
	
	public void download() throws IOException {
		
		
		JFrame main = new JFrame("Updater");
        main.setSize(400, 300);
        main.setLayout(null); 
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JLabel info = new JLabel("Es werden " + data.missingLevels().size() + " Level heruntergeladen.");
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
        bar.setMaximum(data.missingLevels().size());
        bar.setStringPainted(true);
        
        main.add(info);
        main.add(scroll);
        main.add(bar);
        main.setVisible(true);
		
        
        
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
            	String fileURL;
            	for(int i = 0; i < data.missingLevels().size(); i++) {
        			
            		area.setCaretPosition(area.getDocument().getLength());
            		
        			bar.setValue(i + 1);
        			
        			area.append(i + 1 + "| " + data.missingLevels().get(i) + " wird heruntergeladen. ");
        			
        			System.out.println("downloading " + data.missingLevels().get(i));
        			fileURL = "https://raw.githubusercontent.com/All-Rated-Extreme-Demon-List/AREDL/main/data/" + data.missingLevels().get(i) + ".json";
        			
        		try (BufferedInputStream in = new BufferedInputStream(new URL(fileURL).openStream());
        				
        	             FileOutputStream fileOutputStream = new FileOutputStream("C:\\ExtremeDemonList\\levels\\" + data.missingLevels().get(i) + ".json")) {
        			
        	            byte dataBuffer[] = new byte[1024];
        	            int bytesRead;
        	            
        	            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
        	            	
        	                fileOutputStream.write(dataBuffer, 0, bytesRead);
        	                
        	            }
        	        } catch (IOException e) {
        	            // Handle exceptions
        	            e.printStackTrace();
        	        }
        			area.append(" >> ERFOLGREICH \n");
        		}
            	JOptionPane.showMessageDialog(null, "Alle " + data.missingLevels().size() + " Level wurden erfolgreich heruntergeladen.", "Download abgeschlossen", JOptionPane.INFORMATION_MESSAGE);
            	main.dispose();
            	
            	System.out.println("main gui starten IN DOWNLOADLEVELS NICHT VERGESSEN HIER EINFÜGEN");
            	
            }
            
        });
        serverThread.start();
        

	}

}
