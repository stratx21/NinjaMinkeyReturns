/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.util.ArrayList;
import java.awt.Graphics;

/**
 *This class manages many of the Top-Down region and AI functions. 
 * 
 * @author Josh
 */
public class TopDownRegion extends Region{
    /**
     * The ArrayList of AIs in order to manage the AIs. 
     */
    public ArrayList<TopDownAI> AIs=new ArrayList<>();
    
    /**
     * The ArrayList of TriggerSpots in order to manage the TriggerSpots. 
     */
    public ArrayList<TriggerSpot> triggerSpotsData=new ArrayList<>();
    
    /**
     * The different types of tiles for each spot; this data is imported from
     *  text files using the Profile class. 
    */
    public int[][] types=new int[0][0];
    
    /**
     * This is the CListener used for when the AI is done approaching the 
     *  player.
     */
    public CListener AIApproachDone=null;
    
    /**
     * This sets up the TopDownRegion using the ID for the region. 
     * 
     * @param regn the ID for the region
     */
    public TopDownRegion(int regn){
        super(regn);
        changeRegion(regn);
    }
    
    /**
     * This is process to change to another region in top-down mode.
     * 
     * @param newRegion the ID of the region being worked with
     */ 
    public void changeRegion(int newRegion){
        images=GraphicsAssets.importRegionImagesTopDown(newRegion);
        types=Profile.importRegionDataTopDown(newRegion);
        if(types==null){
            ErrorLogger.logError(null,"Region change - failed to import data");
            System.out.println("Region change - failed to import data");
        }
        triggerSpotsData=Profile.importTriggerSpotsTopDown(newRegion);
        AIs=Profile.importAIDataTopDown(newRegion);
        for(int i=0;i<AIs.size();i++)
            System.out.println(AIs.get(i));
    }
    
    /**
     * This draws the graphical representation of the region and manages the
     *  AIs.
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     * @param x the x location of the tile to start drawing on
     * @param y the y location of the tile to start drawing on
     * @param offX by how many pixels the images should be distorted by in the 
     *      x direction
     * @param offY by how many pixels the images should be distorted by in the
     *      y direction
     */
     public void draw(Graphics g,int x,int y,int offX,int offY){
        drawBackRegion(g,x,y,offX*-1,offY*-1);//invert offX and offY
        
        for(TopDownAI ai:AIs){//draw all AIs
//            System.out.println("drawing AIs...");
            ai.draw(g);
        }
     }
     
     /**
     * This draws the graphical representation of the region. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     * @param x the x location of the tile to start drawing on
     * @param y the y location of the tile to start drawing on
     * @param offX by how many pixels the images should be distorted by in the 
     *      x direction
     * @param offY by how many pixels the images should be distorted by in the
     *      y direction
     */
     private void drawBackRegion(Graphics g,int xs,int ys,int offX,int offY){
         if(offX==0&&offY==0){//player is still
            for(int x=0;x<17;x++){
                for(int y=0;y<9;y++){
                    g.drawImage(images.get(types[x+xs-8][y+ys-4]),x*SQUARE_SIZE,y*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE,null);
                }
            }
         } else{ //player is not still
            for(int x=0;x<17;x++){//draw images for original rectangle, but modified with the off-setting::
                for(int y=0;y<9;y++){
                    g.drawImage(images.get(types[x+xs-8][y+ys-4]),x*SQUARE_SIZE+offX,y*SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
                }
            }
            
            //drawing parts coming into the visibility range::
            if(offX>0){//player is going left
                for(int y=0;y<9;y++)//          (V)-1 more because of the x left 1 more,(V)similar here for SQUARE_SIZE
                    g.drawImage(images.get(types[xs-9][y+ys-4]),-SQUARE_SIZE+offX,y*SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            } else if(offX<0){//player is going right
                for(int y=0;y<9;y++)//remember to put in height***********
                    g.drawImage(images.get(types[xs+9][y+ys-4]),GAME_SPAN.width+offX,y*SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            } else if(offY>0){//player is going up
                for(int x=0;x<17;x++)
                    g.drawImage(images.get(types[x+xs-8][ys-5]),x*SQUARE_SIZE+offX,-SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            } else if(offY<0){//player is going down
                for(int x=0;x<17;x++)
                    g.drawImage(images.get(types[x+xs-8][ys+5]),x*SQUARE_SIZE+offX,GAME_SPAN.height+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            }
         }
             
     }
     
     /**
      * This returns the type of tile the specified spot is. 
      * 
      * @param getx the x location for the tile
      * @param gety the y location for the tile
      * @return the type of tile at the specified location
      */
     public int getType(int getx,int gety){
         return types[getx][gety];
     }
     
     /**
      * This tells if the specified space is ready to be moved into by the
      *  standards of the region. 
      * 
      * @param x the x location of the tile
      * @param y the y location of the tile
      * @return if the spot is ready to moved into 
      */
     public boolean canMoveToSpace(int x,int y){
         int t;
         return !hasAI(x,y)&&((t=getType(x,y))==00||t==02);
     }
     
     /**
      * This tells if the spot has an AI in it. 
      * 
      * @param x the x location of the tile
      * @param y the y location of the tile
      * @return if the spot has an AI
      */
     public boolean hasAI(int x,int y){
         boolean h=false;
         for(int i=0;i<AIs.size()&&!h;i++){
             TopDownAI ai=AIs.get(i);
             h=ai.getX()==x&&ai.getY()==y;
         }
         return h;
     }
     
     public TopDownAI getAI(int index){
         return AIs.get(index);
     }
     
     public TopDownAI getAIAtSpotClass(int x,int y){
         for(int i=0;i<AIs.size();i++){
             TopDownAI ai=AIs.get(i);
             if(ai.getX()==x&&ai.getY()==y)
                 return ai;
         }
         return null;
     }
     
     public int getAIAtSpotInteger(int x,int y){
         for(int i=0;i<AIs.size();i++){
             TopDownAI ai=AIs.get(i);
             if(ai.getX()==x&&ai.getY()==y)
                 return i;
         }
         return -1;
     }
     
     /**
      * This function tells if the player hit a trigger spot.
      * 
      * @param x the x location of the tile
      * @param y the y location of the tile
      * @return if the player hit a trigger spot
      */
     public int getTriggerSpotHit(int x,int y){
         int r=-1;
         boolean done=false;
         for(int i=0;i<triggerSpotsData.size()&&!done;i++){
             TriggerSpot t=triggerSpotsData.get(i);
             if(t.getX()<=x&&t.getX()+t.getWidth()>=x
                     &&t.getY()<=y&&t.getY()+t.getHeight()>=y){
                 r=i;
                 done=true;
             }
         }
         if(Math.random()*7==0&&canMoveToSpace(x,y+3)){
             r=0;
             triggerSpotsData.get(0).setLocation(x,y-5);
         }
         
         return r;
     }
     
     /**
      * This returns a trigger spot based on the index provided.
      * 
      * @param index the index of the trigger spot
      * @return the trigger spot requested
      */
     public TriggerSpot getTriggerSpot(int index){
         return triggerSpotsData.get(index);
     }
     
     
     /**
      * This function triggers a certain AI. 
      * 
      * @param ai the index of the AI to trigger
      * @param playerX the player's x location
      * @param playerY the player's y location
      * @param c the CListener used for when the AI's approach is done
      * @return the TopDownAI that was triggered
      */
     public TopDownAI triggerAI(int ai,int playerX,int playerY,CListener c){
         TopDownAI a=AIs.get(ai);
         a.visible=true;
         a.walkingToPlayer=true;
         a.travelling=true;
         a.calcToGo(playerX,playerY);
         AIApproachDone=a.done=new CListener(){
             @Override
             public void actionPerformed(byte a){
                 c.actionPerformed(a);
             }
         };
         return a;
     }
     
     /**
      * This function will tell if there is a destroyable object, such as a
      *     bush that the player can remove in order to access certain areas.
      * 
      * NOTE: this does not manage if the player is able to destroy the 
      *     destroyable object
      * 
      * @param x x coordinate on the map information array of the spot to check 
      *         for a destroyable object
      * @param y y coordinate on the map information array of the spot to check 
      *         for a destroyable object
      * @return if the spot has a destroyable object
      */
     public boolean hasDestroyableObject(int x,int y){
         
         return false;
     }
     
    
    
    
    
}
