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
    	String filePath = path; 

    	try {
            FileInputStream fis = new FileInputStream(filePath);
            AdvancedPlayer player = new AdvancedPlayer(fis);


            Thread playerThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });

            playerThread.start();


            Thread.sleep(10000); 


            player.close();
            fis.close();
        } catch (IOException | JavaLayerException | InterruptedException e) {
            e.printStackTrace();

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
