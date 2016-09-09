/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class AI {
    
    public int AI_ID=0;
    
    public ArrayList<BufferedImage> images=new ArrayList<>();
    
    public int[] location=new int[2];
    
    public int getX(){
        return location[0];
    }
    
    public int getY(){
        return location[1];
    }
    
}
