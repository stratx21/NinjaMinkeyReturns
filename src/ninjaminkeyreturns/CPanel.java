/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Josh
 */
public class CPanel extends JPanel{
//    public int DELAY=40;
    
    /**
     * The current runner object that is being used to calculate what is 
     *  displayed and controls the overall flow of the game. 
     */
    public GameRunner runner=null;

    /**
     * The standard size of the overall frame that is used for the game; this is
     *  the size whose value is set based on the user's screen resolution 
     *  for the sake of the game being full-screen. 
     */
    public static int[] FRAME_SIZE=new int[2];
    
    /**
     * The Rectangle object that represents the area in which the game 
     *  components are being graphically represented; it is calculated using
     *  the user's screen resolution. 
     */
    public static Rectangle GAME_SPAN=new Rectangle();
    
    //public static int[] gameStartPoint=new int[2];
    



    /**
     * sets up the CPanel object. 
     */
    public CPanel(){
        this.setLayout(null);
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
    @Override
    public void paintComponent(Graphics g){}
    
    /**
     * This function calculates the size of the GAME_SPAN rectangle object and
     *  the SQUARE_SIZE, the pixel size of each tile, using the user's screen
     *  resolution.
     * 
     * PRE:: FRAME_SIZE is set to the correct size of the full-screen 
     * 
     */
    public static void calculateTopDownGameSize(){
        int tempX,tempY;
        boolean xLesY;
        int xPixelMax=(FRAME_SIZE[0]-5)/17,
            yPixelMax=(FRAME_SIZE[1]-5)/9;// each is the max pixels that could be used going in that direction
        
        Region.GAME_SPAN=AI.GAME_SPAN=Player.GAME_SPAN=Prompt.GAME_SPAN=
            GameRunner.GAME_SPAN=GAME_SPAN=(xLesY=xPixelMax<yPixelMax)?new Rectangle(FRAME_SIZE[0]/2-(tempX=xPixelMax*17)/2,FRAME_SIZE[1]/2-(tempY=xPixelMax*9)/2,tempX,tempY)
                :new Rectangle(FRAME_SIZE[0]/2-(tempX=yPixelMax*17)/2,FRAME_SIZE[1]/2-(tempY=yPixelMax*9)/2,tempX,tempY);
        
        Region.SQUARE_SIZE=AI.SQUARE_SIZE=GameRunner.SQUARE_SIZE=Player.SQUARE_SIZE=Prompt.SQUARE_SIZE=
            GameRunner.SQUARE_SIZE=xLesY?xPixelMax:yPixelMax;
        
    }
    
}
