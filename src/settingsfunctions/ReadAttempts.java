package settingsfunctions;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import readsafefile.SafeFileManager;

public class ReadAttempts {
	
	public void readAttempts() throws IOException {
		int response = JOptionPane.showConfirmDialog(null, "Es werden nun von allen Extreme Demons die Attempts in die Datenbank geschrieben. Dies kann eine Weile dauern. Trotzdem fortfahren?", "Attempts Einlesen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            SafeFileManager mgr = new SafeFileManager();
            
            File file = new File("C:\\ExtremeDemonList\\userdata\\CCGameManager.dat.xml");
            
            if(!file.exists()) {
            	mgr.DecryptSafeFile();
            }
            
            	mgr.ReadIndexAttempts();
         
        }
	}

}
