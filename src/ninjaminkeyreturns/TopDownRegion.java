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
    
    public CListener AIApproachDone=null;
    
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
        AIs=Profile.importAIDataTopDown(newRegion);
        for(int i=0;i<AIs.size();i++)
            System.out.println(AIs.get(i));
    }
    
    /**
     * 
     */
     public void draw(Graphics g,int x,int y,int offX,int offY){
        drawBackRegion(g,x,y,offX*-1,offY*-1);//make sure to invert offX and offY !!!
//        System.out.println("drew back");
//        System.out.println("offX:"+offX+" offY:"+offY);
        
//        System.out.println("bout to draw AIs......");
        for(TopDownAI ai:AIs){//draw all AIs
//            System.out.println("drawing AIs...");
            ai.draw(g);
        }
     }
     
     private void drawBackRegion(Graphics g,int xs,int ys,int offX,int offY){
         if(offX==0&&offY==0){//player is still
            for(int x=0;x<17;x++){
                for(int y=0;y<9;y++){
                    g.drawImage(images.get(types[x+xs-8][y+ys-4]),GAME_SPAN.x+x*SQUARE_SIZE,GAME_SPAN.y+y*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE,null);
                }
            }
         } else{ //player is not still
            for(int x=0;x<17;x++){//draw images for original rectangle, but modified with the off-setting::
                for(int y=0;y<9;y++){
                    g.drawImage(images.get(types[x+xs-8][y+ys-4]),GAME_SPAN.x+x*SQUARE_SIZE+offX,GAME_SPAN.y+y*SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
                }
            }
            
            //drawing parts coming into the visibility range::
            if(offX>0){//player is going left
                for(int y=0;y<9;y++)//          (V)-1 more because of the x left 1 more,(V)similar here for SQUARE_SIZE
                    g.drawImage(images.get(types[xs-9][y+ys-4]),GAME_SPAN.x-SQUARE_SIZE+offX,GAME_SPAN.y+y*SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            } else if(offX<0){//player is going right
                for(int y=0;y<9;y++)//remember to put in height***********
                    g.drawImage(images.get(types[xs+9][y+ys-4]),GAME_SPAN.x+GAME_SPAN.width+offX,GAME_SPAN.y+y*SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            } else if(offY>0){//player is going up
                for(int x=0;x<17;x++)
                    g.drawImage(images.get(types[x+xs-8][ys-5]),GAME_SPAN.x+x*SQUARE_SIZE+offX,GAME_SPAN.y-SQUARE_SIZE+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            } else if(offY<0){//player is going down
                for(int x=0;x<17;x++)
                    g.drawImage(images.get(types[x+xs-8][ys+5]),GAME_SPAN.x+x*SQUARE_SIZE+offX,GAME_SPAN.y+GAME_SPAN.height+offY,SQUARE_SIZE,SQUARE_SIZE,null);
            }
         }
             
     }
     
     public int getType(int getx,int gety){
         return types[getx][gety];
     }
     
     public boolean canMoveToSpace(int x,int y){
         int t;
         return !hasAI(x,y)&&((t=getType(x,y))==00||t==02);
     }
     
     public boolean hasAI(int x,int y){
         boolean h=false;
         for(int i=0;i<AIs.size()&&!h;i++){
             TopDownAI ai=AIs.get(i);
             h=ai.getX()==x&&ai.getY()==y;
         }
         return h;
     }
     
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
         return r;
     }
     
     public TriggerSpot getTriggerSpot(int index){
         return triggerSpotsData.get(index);
     }
     
     
     public TopDownAI triggerAI(int ai,int playerX,int playerY,CListener c){
         TopDownAI a=AIs.get(ai);
         a.visible=true;
         a.walkingToPlayer=true;
         a.travelling=true;
         a.calcToGo(playerX,playerY);
         AIApproachDone=a.done=new CListener(){
             @Override
             public void actionPerformed(){
                 c.actionPerformed();
             }
         };
         return a;
     }
     
    
    
    
    
}
