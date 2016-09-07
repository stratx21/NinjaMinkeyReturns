/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.util.ArrayList;
import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class TopDownRegion extends Region{
    
    public ArrayList<TopDownAI> AIs=new ArrayList<>();
    
    public int[][] types=new int[0][0];
    
    public TopDownRegion(int regn){
        super(regn);
        changeRegion(regn);
    }
    
    /**
     * process to change to another region in top-down mode::
     */ 
    public void changeRegion(int newRegion){
        images=GraphicsAssets.importRegionImagesTopDown(newRegion);
    }
    
    /**
     * 
     */
     public void draw(Graphics g){
        
     }
    
    
    
    
}
