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
public class TopDownPlayer extends Player{
    
    private byte directionFacing=0;//  0
    //                               1   2
    //                                 3
    
    /**
     * The location, in pixels for top down view and in location points for side
     *  view, of the player.
     */
    public int[] location=new int[2];
    
    /**
     *
     * @param n
     */
    public void setDirectionFacing(byte n){
        directionFacing=n;
    }
    
    /**
     *
     * @return
     */
    public byte getDirectionFacing(){
        return directionFacing;
    }
    
    private boolean disabled=false;//for AI purposes, but may be used for while the player is moving one square in a direction to keep the player from moving more/glitching the game
    
    /**
     *
     */
    public boolean finishedMoving=false;
    
    private int IMG_SEQUENCE_MAX=20;//the max index of images used for the walking sequence (including index 0). once imageSequence hits this number or goes over it imageSequence will be set to 0
    private int imageSequence=0;
    
    /**
     * This holds two integers in the form of x and y coordinates to tell how 
     *  far off of the center tile for the current focus point the player is. 
     */
    public int[] offCenter=new int[2];
    
    /**
     *
     * @param loc
     */
    public TopDownPlayer(int[] loc) {
        location=loc;
        images=GraphicsAssets.importTopDownPlayerImages();
    }
    //////////////////////////////
    
    /**
     * This returns the x value of how far the player is from the center tile
     *  for the current focus point.
     * 
     * @return the x value of how far the player is from the center tile for the
     *  current focus point
     */
    public int getOffCenterX(){
        return offCenter[0];
    }
    
    /**
     * This returns the y value of how far the player is from the center tile
     *  for the current focus point.
     * 
     * @return the y value of how far the player is from the center tile for the
     *  current focus point
     */
    public int getOffCenterY(){
        return offCenter[1];
    }
    
    
    /**
     * This returns the value of x from the location array to tell where the 
     *  player is.
     * 
     * @return the x coordinate of the player's location
     */
    public int getX(){
        return location[0];
    }
    
    /**
     * This returns the value of y from the location array to tell where the
     *  player is.
     * 
     * @return the y coordinate of the player's location
     */
    public int getY(){
        return location[1];
    }
    ///////////////////////////////
    
    /**
     *
     * @return
     */
        
    public boolean getDisabled(){
        return disabled;
    }
    
    /**
     *
     * @param a
     */
    public void setDisabled(boolean a){
        disabled=a;
    }
    
    ///////////////////////////////
    
    /**
     *
     * @return
     */
        
    public boolean getTravelling(){
        return travelling;
    }
    
    /**
     *
     * @param a
     */
    public void setTravelling(boolean a){
        travelling=a;
    }
    
    ////////////////////////////////
    
    /**
     *
     */
        
    public void calculate(){
        
    }
    
    /**
     *
     * @param g
     */
    public void draw(Graphics g){
        if(travelling){
            g.drawImage(images.get(directionFacing*5+imageSequence/5+4),
                    GAME_SPAN.x+GAME_SPAN.width/2-SQUARE_SIZE/2,
                    GAME_SPAN.y+GAME_SPAN.height/2-SQUARE_SIZE/2,
                    SQUARE_SIZE,SQUARE_SIZE,null);
        }else{
            g.drawImage(images.get(directionFacing),GAME_SPAN.x+GAME_SPAN.width/2-SQUARE_SIZE/2,GAME_SPAN.y+GAME_SPAN.height/2-SQUARE_SIZE/2,SQUARE_SIZE,SQUARE_SIZE,null);
        }
    }
    
    /**
     *
     * @param direction
     */
    public void moveStart(int direction){
        if(!travelling&&!disabled){
            if(directionFacing!=direction){//is not facing the direction it is about to move in, so face it before moving...
                directionFacing=(byte)direction;
//                System.out.println("changed direction");
            }else{
                travelling=true;
//                System.out.println("started travel");
                        
                switch(direction){//start moving
                    case 0: moveUp();
                        break;
                    case 1: moveLeft();
                        break;
                    case 2: moveRight();
                        break;
                    case 3: moveDown();
                        break;
                }
            }
        }
    }
    /**
     * PRE:: the boolean travelling has been checked as true
     */
    public void continueMove(){
        switch(directionFacing){
            case 0: moveUp();
                break;
            case 1: moveLeft();
                break;
            case 2: moveRight();
                break;
            case 3: moveDown();
                break;
        }
    }
        
    
    
    
    /////////////force looks:: (for AI purposes primarily)
    
    /**
     *
     */
        
    public void forceLookUp(){
        
    }
    
    /**
     *
     */
    public void forceLookDown(){
        
    }
    
    /**
     *
     */
    public void forceLookLeft(){
        
    }
    
    /**
     *
     */
    public void forceLookRight(){
        
    }
    ///////////////to move in different directions (one square at a time)
    //^note:: PRE:: (?) not disabled
    
    //                                 0
    //                               1   2
    //                                 3
    
    private final int frameDivide=24;
    
    private void moveUp(){
        offCenter[1]-=SQUARE_SIZE/frameDivide;
        if(offCenter[1]<=-1*SQUARE_SIZE){//should stop moving
            finishedMoving=true;
            location[1]-=1;
            offCenter[1]=0;
            travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
    }
    
    private void moveDown(){
        offCenter[1]+=SQUARE_SIZE/frameDivide;
        if(offCenter[1]>=SQUARE_SIZE){//should stop moving
            finishedMoving=true;
            location[1]+=1;
            offCenter[1]=0;
            travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
    }
    
    private void moveLeft(){
        offCenter[0]-=SQUARE_SIZE/frameDivide;
        if(offCenter[0]<=-1*SQUARE_SIZE){//should stop moving
            finishedMoving=true;
            location[0]-=1;
            offCenter[0]=0;
            travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
    }
    
    private void moveRight(){
        offCenter[0]+=SQUARE_SIZE/frameDivide;
        if(offCenter[0]>=SQUARE_SIZE){//should stop moving
            finishedMoving=true;
            location[0]+=1;
            offCenter[0]=0;
            travelling=false;
            imageSequence=0;
        }else{
            imageSequence++;
            if(imageSequence>IMG_SEQUENCE_MAX)
                imageSequence=0;
        }
    }
    
    ////////////////
    //to look in a direction only::
    
    //                                 0
    //                               1   2
    //                                 3
    
    //add more?
    
    private void lookUp(){
        directionFacing=0;
    }
    
    private void lookDown(){
        directionFacing=3;
    }
    
    private void lookLeft(){
        directionFacing=1;
    }
    
    private void lookRight(){
        directionFacing=2;
    }
    
    
}
