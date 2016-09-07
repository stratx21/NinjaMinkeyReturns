/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

/**
 *
 * @author Josh
 */
public class TopDownPlayer extends Player{
    
    public byte directionFacing=0;//   0
    //                               1   2
    //                                 3
    
    public boolean disabled=false;//for AI purposes , but may be used for while the player is moving one square in a direction to keep the player from moving more/glitching the game
    
    
    
    public void calculate(){
        
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
    
    public void moveUp(){
        
    }
    
    public void moveDown(){
        
    }
    
    public void moveLeft(){
        
    }
    
    public void moveRight(){
        
    }
    
    
}
