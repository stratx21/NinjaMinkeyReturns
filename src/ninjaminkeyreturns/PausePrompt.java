/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class PausePrompt extends NavigablePrompt{

    
    /**
     * This sets up the PausePrompt while bringing in a CListener for when
     *  the PausePrompt is done and a boolean for if the prompt should
     *  show the player's statistical data.
     * 
     * @param showStats the boolean that determines if the Prompt will display
     *      the user's statistical data along with the Prompt
     * @param don the CListener evoked when the Prompt is finished
     */
    public PausePrompt(boolean showStats, CListener don) {
        super(showStats, don);
    }
    
    
    
    /**
     * This function draws the graphical representation of the Prompt
     *  interface and will also display the user's statistical data if the
     *  boolean showStats is set to true.
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    @Override
    public void draw(Graphics g){
        
    }
    
}
