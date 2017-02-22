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
    
    
    /**
     * The ID of the mission that this AI gives. 
     */
    public int SIDEVIEW_REGION_TO_GO_TO_ID=0;
    
    /**
     *  Location of the AI object; either in pixels or in Location Points.
     */
    public int[] location=new int[2];
    
    /**
     * This tells if the AI is travelling. 
     */
    public boolean travelling=false;
    
    /**
     * This tells if the AI's mission forces the user into side view. 
     */
    public boolean instantSideView=false;
    
    /**
     * These String values are for the prompt that the AI gives before and after
     *  the mission is given (promptAfter is blank if it does not force the user
     *  into a side view mode).
     */
    private String promptBefore="",promptAfter="";
    
    /**
     * This tells what direction the AI is facing. 
     */
    public int directionFacing=0;
    
    /**
     * The CListener instance that is used for after the AI approaches the 
     *  player or is triggered by the player talking to this AI.
     */
    public CListener done=null;
    
    /**
     * This is how many tiles the AI has to go, in x,y format, to get to the
     *  player, when this AI is walking towards the player. 
     */
    public int[] toGo=new int[2];
    
    /**
     * This tells how much off of the current tile the AI is in pixels. 
     */
    public int[] offCenter=new int[2];
    
    /**
     * This tells if the AI has finished moving. 
     */
    public boolean finishedMoving=false;// - is this variable actually ever used? besides just set? - - -- - - -- - -- - -(has 4 places where it is set i think)
    
    /**
     * This tells if the AI is visible to the user.
     */
    public boolean visible=true;
    
    /**
     * This tells if the AI is walking towards the player. 
     */
    public boolean walkingToPlayer=false;//////
    
    /**
     * This tells if the AI is defeated, AKA dead.
     */
    public boolean defeated=false;
    
    /**
     * This is the max index of images used for the walking sequence 
     *  (including index 0); once imageSequence hits this number or 
     *  goes over it imageSequence will be set to 0.
     */
    private int IMG_SEQUENCE_MAX=20;
    
    /**
     * This is used for the logic flow of the graphical representation by 
     *  determining the value of the index that is used to paint the proper
     *  image out of the sprite sequence. 
     */
    private int imageSequence=0;
    
    /**
     * This is the x and y coordinates of the player and is used for when the
     *  AI is walking towards the player.
     */
    private int[] playerLocApproaching=new int[2];
    
    public int saveIndexForWin=-1,saveIndexToNotTalk=-1,saveIndexToTalk=-1;
    
    public int itemIDNeeded=-1;
    
    /**
     * This sets up the TopDownAI to prepare it for what it may need in order
     *  to enhance the flow of the game. 
     * 
     * @param x the x coordinate at which it starts
     * @param y the y coordinate at which it starts
     * @param ID the ID of the AI
     * @param mssnGivenID the ID of the mission that this AI gives
     * @param vsble if the AI is visible
     * @param prmptBefore the prompt for before the mission (if one at all; if 
     *      there is not one then it is the prompt that will be shown to the 
     *      user when this AI is talked to)
     * @param prmptAfter the prompt for after the mission (if applicable)
     * @param instSideView if the AI forces the user into a side view mission
     */
    public TopDownAI(int x,int y,int ID,int vsble,String prmptBefore,String prmptAfter,int instSideView,int saveIndex,int itemNeeded,int saveIndexNotTalk,int saveIndex2Talk,int sideViewGoTo){
        images=GraphicsAssets.importTopDownAIImages(AI_ID=ID);
        location=new int[]{x,y};
        promptBefore=prmptBefore;
        promptAfter="*"+prmptAfter+"*";
        visible=(vsble==1);
        instantSideView=instSideView==1;
        saveIndexForWin=saveIndex;
        itemIDNeeded=itemNeeded;
        saveIndexToNotTalk=saveIndexNotTalk;
        saveIndexToTalk=saveIndex2Talk;
        SIDEVIEW_REGION_TO_GO_TO_ID=sideViewGoTo;
        ErrorLogger.logEvent("side view region to goo to is "+sideViewGoTo);
    }
    
    /**
     * This returns the prompt for before the mission. 
     * 
     * @return the prompt for before the mission
     */
    public String getBeforePrompt(){
        return promptBefore;
    }
    
    /**
     * This returns the prompt for after the mission. 
     * 
     * @return the prompt for after the mission
     */
    public String getAfterPrompt(){
        return promptAfter;
    }
    
    /**
     * This draws the visual representation of the game for the AI.
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    public void draw(Graphics g,int offX,int offY){
        //if(visible){
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
                } else{//has reached player
                    finishedMoving=true;
                    byte directionToFace=0;
                    if(playerLocApproaching[0]>location[0]){//player needs to face right to face the AI
                        directionToFace=1;
                        directionFacing=3; //< - always 3 to compensate for graphics
                    }else if(playerLocApproaching[0]<location[0]){//player needs to face left
                        directionToFace=2;
                        directionFacing=3;//< - always 3 to compensate for graphics
                    }if(playerLocApproaching[1]<location[1]){//player needs to face down
                        directionToFace=3;
                        directionFacing=3;//< - always 3 to compensate for graphics
                    }else if(playerLocApproaching[1]>location[1]){//player needs to face up
                        directionToFace=0;
                        directionFacing=3;//< - always 3 to compensate for graphics
                    }
                    done.actionPerformed(directionToFace);
                }
            }
            
            if(travelling){
                g.drawImage(images.get(directionFacing*5+imageSequence/5+4),
                        GAME_SPAN.width/2-SQUARE_SIZE*(playerLocApproaching[0]-location[0])-SQUARE_SIZE/2+offCenter[0],
                        GAME_SPAN.height/2-SQUARE_SIZE*(playerLocApproaching[1]-location[1])-SQUARE_SIZE/2+offCenter[1],
                        SQUARE_SIZE,SQUARE_SIZE,null);
            }else{
                g.drawImage(images.get(directionFacing*5),
                        GAME_SPAN.width/2-SQUARE_SIZE*(playerLocApproaching[0]-location[0])-SQUARE_SIZE/2-offX,
                        +GAME_SPAN.height/2-SQUARE_SIZE*(playerLocApproaching[1]-location[1])-SQUARE_SIZE/2-offY
                        ,SQUARE_SIZE,SQUARE_SIZE,null);
            }
        //}
    }
    
    /**
     * This calculates how far the AI has to go in order to reach the player. 
     * 
     * @param pX the player x location
     * @param pY the player y location
     */
    public void calcToGo(int pX,int pY){
        toGo=new int[]{location[0]-pX,location[1]-pY};
        playerLocApproaching=new int[]{pX,pY};
    }
    
    /**
     * an integer used to tell how far off-centered from the tile the AI is. 
     */
    private final int frameDivide=24;
    
    /**
     * The flow in order to walk up (north)
    */
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
    
    /**
     * The flow in order to walk down (south). 
     */
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
    
    /**
     * the flow in order to walk to the left (west).
     */
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
    
    /**
     * The flow in order to walk to the right (east).
     */
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
    
    /**
     * retrieves the X value of the current position of the AI object
     * 
     * @return the x location value of the AI object
     */
    public int getX(){
        return location[0];
    }
    
    /**
     * retrieves the Y value of the current position of the AI object
     * 
     * @return the y location value of the AI object
     */
    public int getY(){
        return location[1];
    }
    
    public void setLocation(int x,int y){
        location=new int[]{x,y};
    }
    
    
    
    
}
