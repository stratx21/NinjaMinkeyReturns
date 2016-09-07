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
    
    public ArrayList<TriggerSpot> triggerSpotsData=new ArrayList<>();
    
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
        types=Profile.importRegionDataTopDown(newRegion);
        if(types==null){
            ErrorLogger.logError(null,"Region change - failed to import data");
            System.out.println("Region change - failed to import data");
        }
        triggerSpotsData=Profile.importTriggerSpotsTopDown(newRegion);
    }
    
    /**
     * 
     */
     public void draw(Graphics g){
        
     }
     
     private void drawBackRegion(){
         
     }
     
     public int getType(int getx,int gety){
         return types[getx][gety];
     }
    
    
    
    
}
