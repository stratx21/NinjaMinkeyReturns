/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.image.BufferedImage;

/**
 *
 * @author Josh
 */
public class Building {//all buildings are 3x3 tiles
    
    /**
     * The BufferedImage instance used for drawing the building.
     */
    public BufferedImage image=null;
    
    /**
     * The dimensions, in tiles, of the building.
     */
    public int width=3,height=3;//tiles
    
    /**
     * The array of two integer values containing the x and y coordinates of
     *  the location of the building. 
     */
    public int[] location=new int[2];
    
    /**
     * This sets up the Building object using the location and the image to draw
     *  it with. 
     * 
     * @param img the BufferedImage instance used to draw the building
     * @param startX the x location of the building
     * @param startY the y location of the building
     */
    public Building(BufferedImage img,int startX,int startY){
        location=new int[]{startX,startY};
        image=img;
    }
    
    /**
     * This sets up the Building object using the location and the image to draw
     *  it with. 
     * 
     * @param img the BufferedImage instance used to draw the building
     * @param startX the x location of the building
     * @param startY the y location of the building
     * @param wid the width of the building
     * @param hei the height of the building
     */
    public Building(BufferedImage img,int startX,int startY,int wid,int hei){
        location=new int[]{startX,startY};
        width=wid;
        height=hei;
        image=img;
    }
    
    /**
     * This function returns the value of the x location of the building. 
     * 
     * @return the x location of the building as an integer
     */
    public int getX(){
        return location[0];
    }
    
    /**
     * This function returns the value of the y location of the building. 
     * 
     * @return the y location of the building as an integer
     */
    public int getY(){
        return location[1];
    }
    
    /**
     * This function returns the value of the width of the building. 
     * 
     * @return the width location of the building as an integer
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * This function returns the value of the height of the building. 
     * 
     * @return the height location of the building as an integer
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * This function returns the BufferedImage instance used to draw the 
     *  building.
     * 
     * @return the BufferedImage instance used to draw the building
     */
    public BufferedImage getImage(){
        return image;
    }
        
    
}
