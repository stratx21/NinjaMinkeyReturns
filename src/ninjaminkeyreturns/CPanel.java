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
    
    public GameRunner runner=null;

    public static int[] FRAME_SIZE=new int[2];
    public static Rectangle GAME_SPAN=new Rectangle();
    
    //public static int[] gameStartPoint=new int[2];
    



    public CPanel(){
        this.setLayout(null);
    }
    
    @Override
    public void paintComponent(Graphics g){}
    
    /**
     * take the original measurements used and translate them to the new sizes
     * that should be used instead, depending on the frame's size, which
     * depends on the screen since the game is full screen. 
     * 
     * @param a the original size divided by 1000 since that is the original x
     *      size that was used
     * @return the new x dimension
     */
//    public int getNewSizeX(double a){
//        return (int)(a*FRAME_SIZE[0]);
//    }
    
    /**
     * take the original measurements used and translate them to the new sizes
     * that should be used instead, depending on the frame's size, which
     * depends on the screen since the game is full screen. 
     * 
     * @param a the original size divided by 1000 to make it simpler than dividing
     *      by 700, the original y size; this function formats the number to as
     *      if it was divided by 1000. 
     * @return the new y dimension
     */
//    public int getNewSizeY(double a){
//        return (int)(a*1.42857*FRAME_SIZE[1]);
//    }
    
    /**
     * PRE:: FRAME_SIZE is set to the correct size of the full-screen 
     */
    public static void calculateTopDownGameSize(){
        int tempX,tempY;
        boolean xLesY;
        int xPixelMax=(FRAME_SIZE[0]-5)/17,
            yPixelMax=(FRAME_SIZE[1]-5)/9;// each is the max pixels that could be used going in that direction
        
        Region.GAME_SPAN=AI.GAME_SPAN=Player.GAME_SPAN=
            GameRunner.GAME_SPAN=GAME_SPAN=(xLesY=xPixelMax<yPixelMax)?new Rectangle(FRAME_SIZE[0]/2-(tempX=xPixelMax*17)/2,FRAME_SIZE[1]/2-(tempY=xPixelMax*9)/2,tempX,tempY)
                :new Rectangle(FRAME_SIZE[0]/2-(tempX=yPixelMax*17)/2,FRAME_SIZE[1]/2-(tempY=yPixelMax*9)/2,tempX,tempY);
        
        Region.SQUARE_SIZE=AI.SQUARE_SIZE=GameRunner.SQUARE_SIZE=Player.SQUARE_SIZE=
            GameRunner.SQUARE_SIZE=xLesY?xPixelMax:yPixelMax;
        
    }
    
}
