/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Josh
 */
public class AudioAssets {

    /**
     *The Clip instance used to control the music. 
     */
    private static Clip music=null;
    
    /**
     * This function uses the String input to play a certain requested audio 
     *  file to be found in the given directory src/Audio/ and will use the 
     *  String input provided to play the given sound. 
     * 
     * @param name the name of the audio file that is to be played
     */
    public static void play(final String name){
        if(Profile.soundOn){
        try{
            AudioInputStream audioIn=AudioSystem.getAudioInputStream(AudioAssets.class.getResource("Audio/"+name+".wav"));
            Clip clip=AudioSystem.getClip();
            clip.open(audioIn);
            if((name.equalsIgnoreCase("MainSong")))
                (music=clip).loop(-1);
            else 
                clip.start();
        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.err.println("Error getting audio/playing:: "+e);
            ErrorLogger.logError(e,"AudioAssets.play(String)");
        }
        }
    }
    
    /**
     * This function returns the instance of the Clip that is used for the 
     *  music.
     * 
     * @return 
     */
    public static Clip getMusic(){
        return music;
    }
    
    /**
     * This function stops the music clip that plays throughout the game. 
     */
    public static void stopMusic(){
        music.stop();
    }
}
