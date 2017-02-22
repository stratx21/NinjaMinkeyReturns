/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class NavigablePrompt extends Prompt{//this prompt is used for menus and such. It will have different options that the user can choose. It does not have a KeyListener so it requires functions to be called as the keys are pressed in this situation
    
    
    /**
     * The CListener instance used when the NavigablePrompt is finished. 
     */
    protected CListener done=null;
    
    /**
     * The integer value concerning the sequence that decreases to zero so that
     *  the user may choose another option on the menu; this is used so that 
     *  options cannot be spammed and so that the controls do not move too 
     *  quickly for the user. 
     */
    protected static int chooseAgain=10;
    
    /**
     * The integer value concerning the sequence that decreases to zero so that
     *  the user may move to another option on the menu. 
     */
    private int moveAgainSequence=0;
    
    /**
     * The boolean value concerning if the prompt should draw the health bar. 
     */
    private boolean drawStats=false;
    
    
    /**
     * The choice that the user is currently on (the "cursor" is on).
     */
    protected byte cursorLocation=0;
    
    /**
     * The different choices that the user can choose from.
     */
    public Option[] choices=new Option[4];
    
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
        drawStats=showStats;
        done=don;
    }
    
    /**
     * This function calculates the effects of what the user does while
     *  using this interface.
     * 
     * @param keysPressed the array of booleans containing what controls were
     *      pressed
     */
    public void loopCalculate(boolean[] keysPressed){
        
        if(keysPressed[4]&&chooseAgain==0){//selected one
            choices[cursorLocation].activate.actionPerformed();
            ErrorLogger.logEvent("User chose option #"+cursorLocation);
            chooseAgain=10;
        }
        
        if(chooseAgain>0)
            chooseAgain--;
        
        
        
        if(moveAgainSequence==0){
            if(keysPressed[2]){//left
                cursorLocation-=1;
                moveAgainSequence=10;
            } else if(keysPressed[3]){//right
                cursorLocation+=1;
                moveAgainSequence=10;
            }
            
        }
        
        if(moveAgainSequence>0)
            moveAgainSequence--;
        
        if(cursorLocation>choices.length-1){
            cursorLocation-=choices.length;//if 4 and length 4, 4-4=0
        }else if(cursorLocation<0){
            cursorLocation+=choices.length;//if -1 and length 4, -1+4=3
        }
        
        choices[cursorLocation].setSelected(true);
        for(int i=0;i<choices.length;i++)
            if(i!=cursorLocation)
                choices[i].setSelected(false);
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
        String toDraw="";
        for(int i=0;i<choices.length;i++){
            if(choices[i].getSelected())
                toDraw+=">";
            else
                toDraw+="  ";
            toDraw+=" "+choices[i].getText();
        }
        
        super.drawBackImage(g);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(toDraw,(int)(0.05*GAME_SPAN.width)/**+indent*/,(int)(0.8*GAME_SPAN.height));
        
        if(drawStats)
            Player.drawHealthBar(g);
    }
    
}
