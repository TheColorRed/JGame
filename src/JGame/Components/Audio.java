package JGame.Components;

import JGame.Behavior;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio extends Behavior{

    private Clip clip;
    private AudioInputStream inputStream;

    public void setAudio(String filename){
        try{
            clip = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream(filename));
            clip.open(inputStream);
            clip.addLineListener((LineEvent event) -> {
                LineEvent.Type type = event.getType();
                if(type == LineEvent.Type.STOP){
                    clip.setFramePosition(0);
                }
            });

        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException ex){
            ex.printStackTrace();
        }
    }

    public void update(){
    }

    public void setVolume(){
        //clip.
    }

    public void play(){
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }
}
