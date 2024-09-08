package errorhandler;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

public class ErrorHandler {
	
	public void newError(Throwable e) {
		e.printStackTrace();
		
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
		
		JOptionPane.showMessageDialog(null, sw.toString() + "", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
	}

}
