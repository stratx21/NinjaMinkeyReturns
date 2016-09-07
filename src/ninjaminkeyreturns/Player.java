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
public class Player {
    
    public double health=0.00;
    
    public int[] location=new int[2];
    
    public ArrayList<BufferedImage> images=new ArrayList<>();
    
    public Player(int[] loc){
        location=loc;
    }
    
}
