package gui;

import java.awt.Button;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import api.GetApiData;
import media.PlaySong;

public class VerifyInfo {
    
    private static VerifyInfo instance = null;
    
    private JFrame frame;
    private JLabel ytthumbnail;
    private Button playsong;
    private int currentSongId; // Instanzvariable für die ID
    
    private PlaySong player;
    private GetApiData data;
    
    private VerifyInfo() {
        frame = new JFrame("Mehr Infos");
        ytthumbnail = new JLabel();
        playsong = new Button("Songpreview abspielen");
        
        playsong.setBounds(90, 200, 200 ,30);
        
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        
        player = new PlaySong();
        data = new GetApiData();
        
        playsong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSongAction();	
            } 	
        });
    }
    
    private void playSongAction() {
        player.play(data.getSongURL(currentSongId), data.getSongID(currentSongId)); // Verwende die Instanzvariable für die ID
    }
    
    public static VerifyInfo getInstance() {
        if (instance == null) {
            instance = new VerifyInfo();
        }
        return instance;
    }
    
    public void showInfo(String url, int id) {
        frame.getContentPane().removeAll(); // Clear previous content
        
        ytthumbnail.setBounds(90, 70, 200, 110);
        currentSongId = id; // Setze die Instanzvariable auf die aktuelle ID
        
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
        frame.add(playsong);
        frame.setVisible(true);
    }
}
