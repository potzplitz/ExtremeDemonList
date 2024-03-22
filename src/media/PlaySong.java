package media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class PlaySong {
    
    public void play(String link, String id) {
        checkExisting(id, link);
    }
    
    private synchronized void checkExisting(String songId, String link) {
        String path = "C:/ExtremeDemonList/songs/" + songId + ".mp3";
        File file = new File(path);
        
        if(file.exists()) {
            playSong(path);
        } else {
            downloadSong(link, songId);
            playSong(path);
            System.out.println("nein");
        }
    }
    
    private void playSong(String path) {
    	String filePath = path; // Passe den Pfad zu deiner MP3-Datei an

    	try {
            FileInputStream fis = new FileInputStream(filePath);
            AdvancedPlayer player = new AdvancedPlayer(fis);

            // Starte das Abspielen in einem neuen Thread, damit der Hauptthread nicht blockiert wird
            Thread playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });

            playerThread.start();

            // Gib dem Player Zeit, die MP3-Datei abzuspielen
            Thread.sleep(10000); // Zum Beispiel 10 Sekunden abwarten

            // Beende den Player und schließe die FileInputStream
            player.close();
            fis.close();
        } catch (FileNotFoundException | JavaLayerException | InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    private void downloadSong(String link, String id) {
        try {
            URL url = new URL(link);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = "C:/ExtremeDemonList/songs/" + id + ".mp3"; 

                FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();

            } else {
                System.out.println("Der Server hat mit dem Statuscode " + responseCode + " geantwortet.");
            }
            httpConn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
