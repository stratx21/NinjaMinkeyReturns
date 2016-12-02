/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class Prompt{
    
    /**
     * The CListener used to tell when the prompt is done displaying its text.
     */
    public CListener done=null;
    
    /**
     * This ArrayList contains all the String values of the different lines
     *  of text that will be printed on the prompt.
     */
    public ArrayList<String> promptShowing=new ArrayList<>();
    
    /**
     * The common font that is used throughout all Prompts in the game.
     */
    public static Font font=null;
    
    /**
     * The standard java.awt.Rectangle in which the game is represented 
     *  graphically and the user interface is shown. 
     */
    public static Rectangle GAME_SPAN=null;
    
    /**
     * The standard number of pixels that are on each side of each tile. 
     */
    public static int SQUARE_SIZE=0;
    
    /**
     * The image that is used for the Prompt at the bottom of the screen. 
     */
    private java.awt.image.BufferedImage promptImage=null;
    
    /**
     * Two booleans used for the flow of the Prompt to tell if it is waiting for
     *  an input and if it is showing the prompt.
     */
    public boolean showingPrompt=false,waitingForInput=false;
    
    /**
     * The text speed at what rate the text appears; NOTE:: it is inverted so 
     *  the higher that this number is then the slower the text will be. 
     */
    public int textSpeed=3;
    
    /**
     * This boolean determines if the drawing function should include showing
     *  the statistical data for the player or not. 
     */
    public boolean showStats=false;
    
    /**
     * This sets up the prompt and sets the promptImage to the proper image. 
     * 
     * NOTE:: with this constructor the statistical data is not shown by 
     *  default.
     */
    public Prompt(){
        promptImage=GraphicsAssets.getTopDownPromptImage(); 
    }
    
    /**
     * This sets up the prompt and sets the promptImage to the proper image but
     *  also has an option for showing the statistical data to the user. 
     * 
     * @param showStats 
     */
    public Prompt(boolean showStats){ 
        this();
        this.showStats=showStats;
    }
    
    /**
     * This function resets the data for the font in this class; this function 
     *  should be called if there is ever a change in screen resolution since it
     *  is based on the screen size. 
     */
    public static void resetFont(){
        font=new Font(Font.SANS_SERIF,Font.PLAIN,(int)(GAME_SPAN.getWidth()/21));
    }
    
    /**
     * This function is meant to be overriden by subclasses of Prompt in order
     *  to make the graphical representation of the game. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    public void draw(Graphics g){
        
    }
    
    /**
     * This function draws  the promptImage for the prompt String to appear on.
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    public void drawBackImage(Graphics g){
        g.drawImage(promptImage,(int)GAME_SPAN.getX(),(int)(GAME_SPAN.getY()+(int)(GAME_SPAN.getHeight()*0.57)),(int)(GAME_SPAN.getWidth()),(int)(GAME_SPAN.getHeight()*0.4),null);
    }
    
}