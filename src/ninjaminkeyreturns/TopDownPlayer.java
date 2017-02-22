/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;

/**
 *
 * @author Josh Holland
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
     * This function sets the value of which direction the player is facing. 
     *
     * @param n which direction the player is facing 
     */
    public void setDirectionFacing(byte n){
        directionFacing=n;
    }
    
    /**
     * This function returns the value of which direction the player is facing. 
     * 
     * @return the direction that the player is facing
     */
    public byte getDirectionFacing(){
        return directionFacing;
    }
    
    /**
     * This boolean value tells if the player is disabled. 
     */
    private boolean disabled=false;//for AI purposes, but may be used for while the player is moving one square in a direction to keep the player from moving more/glitching the game
    
    /**
     *This boolean tells if the player is finished moving to a certain space. 
     */
    public boolean finishedMoving=false;
    
    /**
     * This is used for image calculations to tell what the max is that the 
     *  sequence can reach. 
     */
    private int IMG_SEQUENCE_MAX=20;//the max index of images used for the walking sequence (including index 0). once imageSequence hits this number or goes over it imageSequence will be set to 0
    
    /**
     * This is used for the image calculations to tell which image to use in  a
     *  certain sequence. 
     */
    private int imageSequence=0;
    
    /**
     * This holds two integers in the form of x and y coordinates to tell how 
     *  far off of the center tile for the current focus point the player is. 
     */
    public int[] offCenter=new int[2];
    
    /**
     * This sets up the player using only the location. 
     * 
     * @param loc the location of the player
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
     * This function returns a boolean value concerning if the player is 
     *  disabled.
     *  
     * @return the boolean concerning if the player is disabled
     */
        
    public boolean getDisabled(){
        return disabled;
    }
    
    /**
     * This function sets the boolean value for if the player is disabled. 
     * 
     * @param a if the player should be disabled
     */
    public void setDisabled(boolean a){
        disabled=a;
    }
    
    ///////////////////////////////
    
    /**
     * This function returns a boolean value concerning if the player is 
     *  travelling. 
     *
     * @return if the player is travelling
     */
        
    public boolean getTravelling(){
        return travelling;
    }
    
    /**
     * This function sets if the player is travelling. 
     *
     * @param a if the player should be travelling
     */
    public void setTravelling(boolean a){
        travelling=a;
    }
    
    ////////////////////////////////
    
    /**
     *
     * This function draws the visual representation of the player. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    public void draw(Graphics g){
        if(travelling){
            g.drawImage(images.get(directionFacing*5+imageSequence/5+4),
                    GAME_SPAN.width/2-SQUARE_SIZE/2,
                    GAME_SPAN.height/2-SQUARE_SIZE/2,
                    SQUARE_SIZE,SQUARE_SIZE,null);
        }else{
            g.drawImage(images.get(directionFacing),GAME_SPAN.width/2-SQUARE_SIZE/2,GAME_SPAN.height/2-SQUARE_SIZE/2,SQUARE_SIZE,SQUARE_SIZE,null);
        }
    }
    
    /**
     * This function starts a move in a certain direction. 
     *
     * @param direction the direction to move in
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
     * This function continues the flow of the move. 
     * 
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
    
    
    ///////////////to move in different directions (one square at a time)
    //^note:: PRE:: (?) not disabled
    
    //                                 0
    //                               1   2
    //                                 3
    
    private final int frameDivide=24;
    
    /**
     * This function starts the flow for the player to move north. 
     */
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
    
    /**
     * This function starts the flow for the player to move south. 
     */
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
    
    /**
     * This function starts the flow for the player to move west. 
     */
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
    
    /**
     * This function starts the flow for the player to move east. 
     */
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
    
    /**
     * This function forces the player to look north. 
     */
    private void lookUp(){
        directionFacing=0;
    }
    
    /**
     * This function forces the player to look south. 
     */
    private void lookDown(){
        directionFacing=3;
    }
    
    /**
     * This function forces the player to look west. 
     */
    private void lookLeft(){
        directionFacing=1;
    }
    
   /**
    * This function forces the player to look east. 
    */ 
    private void lookRight(){
        directionFacing=2;
    }
    
    
}
