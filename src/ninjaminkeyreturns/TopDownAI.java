/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Josh
 */
public class TopDownAI extends AI{
    
    public static int SQUARE_SIZE=0;
    
    public int REGION_ID=0;
    
    public int MISSION_GIVEN_ID=0;
    
    public static Rectangle GAME_SPAN=new Rectangle();
    
    public boolean travelling=false;
    
    public String promptBefore="",promptAfter="";
    
    public int directionFacing=0;
    
    public int[] toGo=new int[2];
    
    public int[] offCenter=new int[2];
    
    public boolean finishedMoving=false;
    
    public boolean visible=true;
    
    public boolean walkingToPlayer=false;//////
    
    
    private int IMG_SEQUENCE_MAX=4;//the max index of images used for the walking sequence (including index 0). once imageSequence hits this number or goes over it imageSequence will be set to 0
    private int imageSequence=0;
    
    private int[] playerLocApproaching=new int[2];
    
    
    public TopDownAI(int x,int y,int ID,int mssnGivenID,int vsble,String prmptBefore,String prmptAfter){
        images=GraphicsAssets.importTopDownAIImages(AI_ID=ID);
        location=new int[]{x,y};
        MISSION_GIVEN_ID=mssnGivenID;
        promptBefore=prmptBefore;
        promptAfter=prmptAfter;
        visible=vsble==1;
    }
    
    public void draw(Graphics g){
        if(visible){
            if(walkingToPlayer){//flow
                if(toGo[1]<-1){
                    walkDown();
                }else if(toGo[1]>1){
                    walkUp();
                }else if(toGo[0]<-1
                        ||(toGo[0]<0&&(toGo[1]!=0))){//second condition: move under or over if not at the same y
                    walkRight();
                }else if(toGo[0]>1
                        ||(toGo[0]>0&&(toGo[1]!=0))){//second condition: move under or over if not at the same y
                    walkLeft();
                } else//has reached player
                    finishedMoving=true;
            }
            
            if(travelling){
            g.drawImage(images.get(directionFacing*5+imageSequence+4),
                    GAME_SPAN.x+GAME_SPAN.width/2-SQUARE_SIZE*(playerLocApproaching[0]-location[0])-SQUARE_SIZE/2+offCenter[0],
                    GAME_SPAN.y+GAME_SPAN.height/2-SQUARE_SIZE*(playerLocApproaching[1]-location[1])-SQUARE_SIZE/2+offCenter[1],
                    SQUARE_SIZE,SQUARE_SIZE,null);
//            System.out.println("travelling");
            }else{
                g.drawImage(images.get(directionFacing),
                        GAME_SPAN.x+GAME_SPAN.width/2-SQUARE_SIZE*(playerLocApproaching[0]-location[0])-SQUARE_SIZE/2,
                        GAME_SPAN.y+GAME_SPAN.height/2-SQUARE_SIZE*(playerLocApproaching[1]-location[1])-SQUARE_SIZE/2
                        ,SQUARE_SIZE,SQUARE_SIZE,null);
//                System.out.println("NOT    travelling");
            }
//            System.out.println(location[0]+","+location[1]+"togo:: "+toGo[0]+","+toGo[1]);
        }
    }
    
    public void calcToGo(int pX,int pY){
        toGo=new int[]{location[0]-pX,location[1]-pY};
        playerLocApproaching=new int[]{pX,pY};
    }
    
    private final int frameDivide=24;
    
    public void walkUp(){
        offCenter[1]-=SQUARE_SIZE/frameDivide;
        if(offCenter[1]<=-1*SQUARE_SIZE){//should stop moving
            location[1]-=1;
            toGo[1]-=1;
            offCenter[1]=0;
            //travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
        directionFacing=0;
    }
    
    public void walkDown(){
        offCenter[1]+=SQUARE_SIZE/frameDivide;
        if(offCenter[1]>=SQUARE_SIZE){//should stop moving
            finishedMoving=true;
            location[1]+=1;
            toGo[1]+=1;
            offCenter[1]=0;
            //travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
        directionFacing=3;
    }
    
    public void walkLeft(){
        offCenter[0]-=SQUARE_SIZE/frameDivide;
        if(offCenter[0]<=-1*SQUARE_SIZE){//should stop moving
            finishedMoving=true;
            location[0]-=1;
            toGo[0]-=1;
            offCenter[0]=0;
            //travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
        directionFacing=1;
    }
    
    public void walkRight(){
        offCenter[0]+=SQUARE_SIZE/frameDivide;
        if(offCenter[0]>=SQUARE_SIZE){//should stop moving
            finishedMoving=true;
            location[0]+=1;
            toGo[0]+=1;
            offCenter[0]=0;
            //travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
        directionFacing=2;
    }
    
    
    
    
}
