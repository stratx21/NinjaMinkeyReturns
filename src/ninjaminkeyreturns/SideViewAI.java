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
public class SideViewAI extends AI{
    
    /**
     * The maximum velocity that the AI can reach. 
     */
    public int MAX_VELOCITY=4;
    
    /**
     * This tells if the AI is facing to the right. 
     */
    public boolean facingRight=false;
    
    /**
     * This represents the health of the AI. 
     */
    public double health=100.0;
    
    /**
     * This is the velocity of the AI in x,y formatting and is used to 
     *  move the AI. 
     */
    public int[] velocity=new int[]{0,0};
    
    /**
     * This goes through the calculations for the AI in order to run it in the
     *  way that it is supposed to run. 
     * 
     * @param playerX the player's x location
     * @param playerY the player's y location
     */
    public void calculate(int playerX,int playerY){
        travel(playerX,playerY);
        if(shouldAttack(playerX,playerY)){
            if(location[0]<playerX)
                attackRight();
            else
                attackLeft();
        }
    }
    
//    public void calculateLeftOrRight(int playerX){
//        facingRight=location[0]<playerX;   
//    }
    
    /**
     * This function is meant to be overriden and is for calculations for  
     *  the AI's travelling. 
     * 
     * @param playerX the player's x location
     * @param playerY the player's y location
     */
    public void travel(int playerX,int playerY){
        
    }
    
    /**
     * This function is meant to be overriden and is for the flow of the AI
     *  to attack to the left. 
     */
    public void attackLeft(){
        
    }
    
    /**
     * This function is meant to be overriden and is for the flow of the AI
     *  to attack to the right. 
     */
    public void attackRight(){
        
    }
    
    /**
     * This function is meant to be overriden and is used to tell if the AI
     *  is in range of the player so that if it should attack or not. 
     * 
     * @param playerX the player's x location
     * @param playerY the player's y location
     * @return the boolean telling if the AI should attack
     */
    public boolean shouldAttack(int playerX,int playerY){
        return false;
    }
    
//    public byte shouldMoveTo(int playerX, int playerY){
//        return 0;
//    }
    
//    public boolean inRange(int playerX,int playerY){
//        return false;
//    }
    
    /**
     * This function spawns a projectile of a certain type at a certain 
     *  location.
     * 
     * @param type the type of projectile
     * @param startX the starting x location for the projectile
     * @param startY the starting y location for the projectile
     * @param xVelocity the starting x velocity for the projectile
     * @param yVelocity the starting y velocity for the projectile 
     */
    public void spawnProjectile(int type,int startX,int startY,int xVelocity,int yVelocity){
        //use the damage in this class to determine the projectile damage!
    }
    
    /**
     * This function spawns a hit box of a certain type, location, and size,
     *  primarily for the Melee-attack AI. 
     * 
     * @param x the x coordinate of the hitbox
     * @param y the y coordinate of the hitbox
     * @param width the width of the hitbox
     * @param height the height of the hitbox
     */
    public void spawnHitBox(int x,int y,int width,int height){
        
    }
    
    /**
     * This function tells if the AI is facing towards the right. 
     * 
     * @return if the AI is facing towards the right 
     */
    public boolean getFacingRight(){
        return facingRight;
    }
    
    /**
     * This function defines if the AI is facing towards the right.
     * 
     * @param a the boolean to determine if the AI is facing towards the right
     */
    public void setFacingRight(boolean a){
        facingRight=a;
    }
    
    /**
     * This function gets the health of the AI.
     * 
     * @return the health of the AI
     */
    public double getHealth(){
        return health;
    }
    
    /**
     * This function sets the health of the AI. 
     * 
     * @param a how much health the AI should have
     */
    public void setHealth(double a){
        health=a;
    }
    
    /**
     * This function decreases the damage of a certain hit from the AI's health.
     * 
     * @param dmg the damage done to the AI
     */
    public void hit(double dmg){
        health-=dmg;
    }
    
}
