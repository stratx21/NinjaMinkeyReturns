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
public class Region {
    
    public int REGION_ID=0;
    
    public ArrayList<BufferedImage> images=new ArrayList<>();
    
    public ArrayList<int> type=new ArrayList<>();//type of terrain/etc
    
    //public ArrayList<  - - -- - -arraylist for houses ?
    
    public Region(int regn){
        REGION_ID=regn;
    }
    
}
