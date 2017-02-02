/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

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
    public boolean travelling=false;
    
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
    public ArrayList<BufferedImage> images=new ArrayList<>();
    
//    /**
//     * This sets up the player using a certain location with x and y 
//     *  coordinates.
//     * 
//     * @param loc 
//     */
//    public Player(int[] loc){
//        location=loc;
//    }
    
    
    
    
    
}
