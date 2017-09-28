/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

/**
 *
 * @author Josh Holland, Ethan Dickey, Sawyer Hill, and Kevin Chen
 */
public class NinjaMinkeyReturns{

    /**
     * Initializes the project.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args){
        ErrorLogger.logEvent("<<<< - (!) - NEW INSTANCE OF THE GAME IS NOW "
                +"RUNNING - (!) - >>>>");
        AudioAssets.play("MainSong");
        new GameFrame();
    }
    
}
