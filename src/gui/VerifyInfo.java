package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VerifyInfo {
    
    private static VerifyInfo instance = null;
    
    private JFrame frame;
    private JLabel ytthumbnail;
    
    private VerifyInfo() {
        frame = new JFrame("Verifikationsinfos");
        ytthumbnail = new JLabel();
        
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
    }
    
    public static VerifyInfo getInstance() {
        if (instance == null) {
            instance = new VerifyInfo();
        }
        return instance;
    }
    
    public void showInfo(String url) {
        frame.getContentPane().removeAll(); // Clear previous content
        
        ytthumbnail.setBounds(90, 70, 200, 110);
        
        if (url != null && url.contains("v=")) {
            int startIndex = url.indexOf("v=") + 2;
            int endIndex = url.indexOf('&', startIndex);
            if (endIndex == -1) {
                endIndex = url.length();
            }
            String videoId = url.substring(startIndex, endIndex);
            
            String videoThumb = "https://img.youtube.com/vi/" + videoId +"/0.jpg";
            
            System.out.println(videoThumb);
            
            int labelWidth = ytthumbnail.getWidth();
            int labelHeight = ytthumbnail.getHeight();
            
            try {
                URL imageget = new URL(videoThumb);
                BufferedImage originalImage = ImageIO.read(imageget);
                
                // Das Bild skalieren, um in das JLabel zu passen
                Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                
                // Ein ImageIcon erstellen und dem JLabel zuweisen
                ytthumbnail.setIcon(new ImageIcon(scaledImage));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
        frame.add(ytthumbnail);
        frame.setVisible(true);
    }
}
