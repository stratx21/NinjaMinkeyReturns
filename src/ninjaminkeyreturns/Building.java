/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.image.BufferedImage;

/**
 *
 * @author 0001058857
 */
public class Building {//all buildings are 3x3 tiles
    
    public BufferedImage image=null;
    
    public int[] location=new int[2];
    
    public Building(BufferedImage img,int startX,int startY){
        location=new int[]{startX,startY};
        image=img;
    }
    
    public int getX(){
        return location[0];
    }
    
    public int getY(){
        return location[1];
    }
        
    
}
