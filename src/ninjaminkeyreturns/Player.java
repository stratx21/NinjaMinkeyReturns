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
    
    public double health=50.00;
    
    public int[] location=new int[2];
    
    public static Rectangle GAME_SPAN=new Rectangle();
    public static int SQUARE_SIZE=0;
    
    public ArrayList<BufferedImage> images=new ArrayList<>();
    
    public Player(int[] loc){
        location=loc;
    }
    
    public int getX(){
        return location[0];
    }
    
    public int getY(){
        return location[1];
    }
    
}
