/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Josh
 */
public class CPanel extends JPanel{
//    public int DELAY=40;

    public static int[] FRAME_SIZE=new int[2];
    public static int[] GAME_SIZE=new int[2];



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
    
}
