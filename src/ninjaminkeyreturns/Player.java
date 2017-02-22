/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class Player {
    /**
     * The value of health that the player has.
     */
    public static double health=200.0;
    
    
    
    /**
     * This tells if the player is travelling, which could be walking or jumping
     *  or falling. 
     */
    protected boolean travelling=false;
    
    /**
     * The java.awt.Rectangle object used for the sake of collisions and 
     *  damage from external influences. 
     */
    public static Rectangle GAME_SPAN=new Rectangle();
    
    /**
     * The static integer that aids calculations that require this standard
     *  number of pixels per tile (17x9 tiles in the game screen); it is based 
     *  on the setup of the frame in which the game span coordinates and size
     *  and other values are calculated based on the user's screen resolution.
     */
    public static int SQUARE_SIZE=0;
    
    /**
     * An ArrayList containing all the images that are used to draw the 
     *  graphical representation of the player. 
     */
    protected ArrayList<BufferedImage> images=new ArrayList<>();
    
    
    /**
     * This function draws the player's health bar to display to the user how 
     *  much health is remaining. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game.  
     */
    public static void drawHealthBar(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(SQUARE_SIZE/4, SQUARE_SIZE/4, SQUARE_SIZE*2, SQUARE_SIZE/2);
        g.setColor(Color.red);
        g.fillRect(SQUARE_SIZE/4, SQUARE_SIZE/4, (int)((SQUARE_SIZE*2)*(health/Profile.MAX_HEALTH)), SQUARE_SIZE/2);
    }
    
    
    
}
