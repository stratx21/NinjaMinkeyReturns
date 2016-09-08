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
     public void draw(Graphics g,int x,int y){
        drawBackRegion(g,x,y);
        System.out.println("drew back");
     }
     
     private void drawBackRegion(Graphics g,int xs,int ys){
         for(int x=0;x<17;x++){
             for(int y=0;y<9;y++){
//                 System.out.println(types[x][y]);
                 g.drawImage(images.get(types[x+xs-8][y+ys-4]),GAME_SPAN.x+x*SQUARE_SIZE,GAME_SPAN.y+y*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE,null);
             }
         }
             
     }
     
     public int getType(int getx,int gety){
         return types[getx][gety];
     }
    
    
    
    
}
