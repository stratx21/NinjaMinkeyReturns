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
public class NavigablePrompt extends Prompt{//this prompt is used for menus and such. It will have different options that the user can choose. It does not have a KeyListener so it requires functions to be called as the keys are pressed in this situation
    
    /**
     * The choice that the user is currently on (the "cursor" is on).
     */
    private byte cursorLocation=0;
    
    /**
     * The different choices that the user can choose from.
     */
    private String[] choices=new String[4];
    
    /**
     * This sets up the NavigablePrompt while bringing in a CListener for when
     *  the NavigablePrompt is done and a boolean for if the prompt should
     *  show the player's statistical data.
     * 
     * @param showStats the boolean that determines if the Prompt will display
     *      the user's statistical data along with the Prompt
     * @param don the CListener evoked when the Prompt is finished
     */
    public NavigablePrompt(boolean showStats,CListener don){
        
    }
    
    /**
     * This function calculates the effects of what the user does while
     *  using this interface.
     * 
     * @param keysPressed the array of booleans containing what controls were
     *      pressed
     */
    public void loopCalculate(boolean[] keysPressed){
        
    }
    
    /**
     * This function draws the graphical representation of the NavigablePrompt
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
