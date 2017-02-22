/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This prompt is used for just showing text on the screen and allowing the 
 *  player to hit select to continue through it. 
 * 
 * @author Josh
 */
public class PlainPrompt extends Prompt{//
    
    /**
     * the String that is currently being displayed.
     */
    public String toDraw="";
    
    /**
     * This sets up the PlainPrompt object using the String values by line
     *  that it should show and the CListener instance for when it is finished. 
     * 
     * @param showing
     * @param don
     */
    public PlainPrompt(ArrayList<String> showing,CListener don){
        super();
        this.promptShowing=showing;
        done=don;
    }
    
    /**
     * These values are used to calculate what characters should be displayed
     *  by the PlainPrompt. 
     */
    private int characterNum=0,lineNum=0;
    
    /**
     * These values are used to calculate what characters should be displayed
     *  by the PlainPrompt. 
     */
    private boolean secondLine=false,promptEnded=false;
    
    /**
     * This function is used for the loop that is used to calculate aspects of
     *  the game. 
     * 
     * @param cont the boolean value concerning if the user is ordering the 
     *      prompt to move on
     */
    public void loopCalculate(boolean cont){
        if(lineNum>promptShowing.size()-1){
            done.actionPerformed();
        } else{//is not finished
            String currentString=promptShowing.get(lineNum);
            toDraw=currentString;
            if(!waitingForInput){
                characterNum++;
                if(characterNum/textSpeed<currentString.length()){//still drawing the string out
                    toDraw=currentString.substring(0,characterNum/textSpeed+1);
                    characterNum++;
                }else{//the string has been completely drawn and it is now waiting for user input
                    waitingForInput=true;
                }
            }else{//is waiting for player input (done with that line)
                if(cont){
                    lineNum++;
                    characterNum=0;
                    waitingForInput=false;
                }
            }
        }
    }
    
    /**
     * This function draws the visual representation of the PlainPrompt object. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    @Override
    public void draw(Graphics g){
        super.drawBackImage(g);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(toDraw,(int)(0.05*GAME_SPAN.width),(int)(0.8*GAME_SPAN.height));
    }
}
