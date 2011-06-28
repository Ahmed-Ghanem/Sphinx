package manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Ahmed Ghanem
 */
public class MusicManager {

    InputStream in;
    AudioStream as;

    public MusicManager(String fileName){
        try {
            in = new FileInputStream(fileName);
            as = new AudioStream(in);

            //AudioPlayer.player.stop(as);
        } catch (IOException ex) {
            Logger.getLogger(MusicManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void start() {
        AudioPlayer.player.start(as);
    }
    public void stop(){
        AudioPlayer.player.stop(as);
    }
}
