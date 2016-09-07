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
    public static Clip music=null;
    
    public static void play(final String name){
        try{
            AudioInputStream audioIn=AudioSystem.getAudioInputStream(AudioAssets.class.getResource("Audio/"+name+".wav"));
            Clip clip=AudioSystem.getClip();
            clip.open(audioIn);
            if((name.equalsIgnoreCase("music")||name.equals("ylvis_the_fox"))&&Profile.musicOn)
                (music=clip).loop(-1);
            else if(Profile.soundEffectsOn)
                clip.start();
        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.err.println("Error getting audio/playing:: "+e);
            ErrorLogger.logError(e,"AudioAssets.play(String)");
        }
    }
}
