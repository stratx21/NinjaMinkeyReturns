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
 * This class is a Custom-JPanel that includes enhancement for inheritance for
 *  the purpose of this program.
 * 
 * @author Josh Holland
 */
public class CPanel extends JPanel{
//    public int DELAY=40;
    
    /**
     * The current runner object that is being used to calculate what is 
     *  displayed and controls the overall flow of the game. 
     */
    public GameRunner runner=null;
    
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
    public static void calculateTopDownGameSize(int frameWidth,int frameHeight){
        int tempX,tempY;
        boolean xLesY;
        int xPixelMax=(frameWidth-5)/17,
            yPixelMax=(frameHeight-5)/9;// each is the max pixels that could be used going in that direction
        
        GameFrame.GAME_SPAN=Region.GAME_SPAN=AI.GAME_SPAN=Player.GAME_SPAN=Prompt.GAME_SPAN=
            GameRunner.GAME_SPAN=GAME_SPAN=(xLesY=xPixelMax<yPixelMax)?new Rectangle(frameWidth/2-(tempX=xPixelMax*17)/2,frameHeight/2-(tempY=xPixelMax*9)/2,tempX,tempY)
                :new Rectangle(frameWidth/2-(tempX=yPixelMax*17)/2,frameHeight/2-(tempY=yPixelMax*9)/2,tempX,tempY);
        
        Region.SQUARE_SIZE=AI.SQUARE_SIZE=GameRunner.SQUARE_SIZE=Player.SQUARE_SIZE=Prompt.SQUARE_SIZE=
            GameRunner.SQUARE_SIZE=xLesY?xPixelMax:yPixelMax;
        
        System.out.println(GAME_SPAN.getWidth()+","+GAME_SPAN.getHeight());
        
    }
    
}