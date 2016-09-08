/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class TopDownPlayer extends Player{
    
    public byte directionFacing=0;//   0
    //                               1   2
    //                                 3
    
    public int distanceMoved=0;
    
    private boolean disabled=false;//for AI purposes, but may be used for while the player is moving one square in a direction to keep the player from moving more/glitching the game
    
    public boolean travelling=false;
    
    public int[] offCenter=new int[2];
    
    public static int SQUARE_SIZE=0;
    
    public TopDownPlayer(int[] loc) {
        super(loc);
    }
    //////////////////////////////
    
    public int getOffCenterX(){
        return offCenter[0];
    }
    
    public int getOffCenterY(){
        return offCenter[1];
    }
    
    ///////////////////////////////
    
    public boolean getDisabled(){
        return disabled;
    }
    
    public void setDisabled(boolean a){
        disabled=a;
    }
    
    ///////////////////////////////
    
    public boolean getTravelling(){
        return travelling;
    }
    
    public void setTravelling(boolean a){
        travelling=a;
    }
    
    ////////////////////////////////////
    
    public int getX(){
        return location[0];
    }
    
    public int getY(){
        return location[1];
    }
    
    ////////////////////////////////
    
    public void calculate(){
        
    }
    
    public void Draw(Graphics g){
        
    }
    
    public void move(int direction){
        
    }
    
    
    /////////////force looks:: (for AI purposes primarily)
    
    public void forceLookUp(){
        
    }
    
    public void forceLookDown(){
        
    }
    
    public void forceLookLeft(){
        
    }
    
    public void forceLookRight(){
        
    }
    ///////////////to move in different directions (one square at a time)
    //^note:: PRE:: (?) not disabled
    
    //                                 0
    //                               1   2
    //                                 3
    
    private void moveUp(){
        
    }
    
    private void moveDown(){
        
    }
    
    private void moveLeft(){
        
    }
    
    private void moveRight(){
        
    }
    
    ////////////////
    //to look in a direction only::
    
    //                                 0
    //                               1   2
    //                                 3
    
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
