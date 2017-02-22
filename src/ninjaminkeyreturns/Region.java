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
public class Region {
    
    /**
     * The ID of the current region that this class contains data for and runs.
     */
    protected int REGION_ID=0;
    
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
     * The ArrayList of images that the region uses to draw the graphical 
     *  representation of the region. 
     */
    protected ArrayList<BufferedImage> images=new ArrayList<>();
    
    
    /**
     * This sets the ID of the region that this class contains data for and
     *  runs.
     * 
     * @param regn the ID of the region that this class contains data for and
     *      runs
     */
    public Region(int regn){
        REGION_ID=regn;
    }
    
    /**
     * This function retrieves the integer value of the ID of this region. 
     * 
     * @return the ID of this region
     */
    public int getRegionID(){
        return REGION_ID;
    }
    
}
