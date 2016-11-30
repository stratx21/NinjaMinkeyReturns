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
public class AI {
    
    /**
     * The static integer that aids calculations that require this standard
     *  number of pixels per tile (17x9 tiles in the game screen); it is based 
     *  on the setup of the frame in which the game span coordinates and size
     *  and other values are calculated based on the user's screen resolution.
     */
    public static int SQUARE_SIZE=0;
    
    /**
     * The java.awt.Rectangle object used for the sake of collisions and 
     *  damage from external influences. 
     */
    public static Rectangle GAME_SPAN=new Rectangle();
    
    /**
     * The ID used to identify which AI object this is in order to continue
     *  the flow of the game; it aids in mission completion and progress
     *  throughout the Role Play system. 
     */
    public int AI_ID=0;
    
    /**
     * The images that are used to represent the AI object; these will be 
     *  imported in the setup through the system of constructors. 
     */
    public ArrayList<BufferedImage> images=new ArrayList<>();
    
    /**
     *  Location of the AI object; either in pixels or in Location Points.
     */
    public int[] location=new int[2];
    
    /**
     * retrieves the X value of the current position of the AI object
     * 
     * @return the x location value of the AI object
     */
    public int getX(){
        return location[0];
    }
    
    /**
     * retrieves the Y value of the current position of the AI object
     * 
     * @return the y location value of the AI object
     */
    public int getY(){
        return location[1];
    }
    
}
