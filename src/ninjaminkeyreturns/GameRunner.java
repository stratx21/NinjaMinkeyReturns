/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.Rectangle;
/**
 *
 * @author Josh
 */
public class GameRunner{
    
    /**
     * An array of characters that are used to control the player object; they 
     *  are ordered by up, down, left, right, attack, and other attack. 
     * 
     * NOTE:: to change controls change them in Profile then evoke the 
     *  resetControls function
     */
    public static char[] controls=new char[7]; 
    
    /**
     * A set of booleans that are used to tell what keys are currently being
     *  pressed. 
     */
    protected boolean[] currentKey=new boolean[7];//up, down, left, right, attack, other attack
    
    /**
     * The standard java.awt.Rectangle in which the game is represented 
     *  graphically and the user interface is shown. 
     */
    public static Rectangle GAME_SPAN=new Rectangle();
    
    /**
     * The standard number of pixels that are on each side of each tile. 
     */
    public static int SQUARE_SIZE=0;
    
    /**
     * CListener evoked to exit the game mode or to finish gameplay
     */
    protected CListener done=null;
    
    /**
     * This sets up the GameRunner. 
     */
    public GameRunner(){
        resetControls();
    }
    
    /**
     * This sets up the GameRunner using a CListener for when the GameRunner 
     *  instance is finished and needs to return to another flow of code. 
     * 
     * @param dn The CListener that will be evoked when the GameRunner is
     *      finished
     */
    public GameRunner(CListener dn){
        this();
        done=dn;
    }
    
    /**
     * This function sets the controls that are used for the game directly 
     *  through the KeyListener that this class implements to what the
     *  controls are in the Profile class. 
     * 
     * NOTE:: to change controls change them in Profile then evoke the 
     *  resetControls function
     */
    public void resetControls(){
        controls=Profile.controls;
    }
    
    /**
     * This function overrides the original JPanel function that is called 
     *  through every repaint sequence that the JPanel manages, and is meant to 
     *  be overriden by subclasses of CPanel. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    public void draw(Graphics g){}
    
    /**
     * the flow for calculating aspects of the game; this function is intended
     *  to be overriden by subclasses. 
     */
    public void calculate(){}
    
    /////////
    /**
     * This function is meant to be overriden for inheritance purposes for 
     *  subclasses to control the flow of the game more easily. 
     * 
     * @param typed the character of the  key that was typed
     */
    public void keyTypedFlow(char typed){
        
    }

    /**
     * This function is meant to be overriden for inheritance purposes for 
     *  subclasses to control the flow of the game more easily. 
     * 
     * @param typed the character of the  key that was pressed
     */
    public void keyPressedFlow(char typed){
        if(typed==controls[0]){
            currentKey[0]=true;
            //setOtherKeysFalse(0);//in here to change it only if it is a valid key
        }else if(typed==controls[1]){
            currentKey[1]=true;
            //setOtherKeysFalse(1);
        }else if(typed==controls[2]){
            currentKey[2]=true;
            //setOtherKeysFalse(2);
        }else if(typed==controls[3]){
            currentKey[3]=true;
            //setOtherKeysFalse(3);
        }else if(typed==controls[4]){
            currentKey[4]=true;
            //setOtherKeysFalse(4);
        }else if(typed==controls[5]){
            currentKey[5]=true;
            //setOtherKeysFalse(5);
        }else if(typed==controls[6]){
            currentKey[6]=true;
        }
    }

    /**
     * This function is meant to be overriden for inheritance purposes for 
     *  subclasses to control the flow of the game more easily. 
     * 
     * @param typed the character of the  key that was released
     */
    public void keyReleasedFlow(char typed){
        //replace with a loop? ::
        if(typed==controls[0]){
            currentKey[0]=false;
        }else if(typed==controls[1]){
            currentKey[1]=false;
        }else if(typed==controls[2]){
            currentKey[2]=false;
        }else if(typed==controls[3]){
            currentKey[3]=false;
        }else if(typed==controls[4]){
            currentKey[4]=false;
        }else if(typed==controls[5]){
            currentKey[5]=false;
        }else if(typed==controls[6]){
            currentKey[6]=false;
        }
    }
    
    /**
     * This function sets all the boolean values in the currentKey array
     *  to false except for the one in the index specified. 
     * 
     * @param doNotChange the integer value of the index of the one boolean that
     *      should still be true
     */
    public void setOtherKeysFalse(int doNotChange){
        for(int i=0;i<6;i++)
            if(i!=doNotChange)
                currentKey[i]=false;
    }
    
    ////////
    
}
