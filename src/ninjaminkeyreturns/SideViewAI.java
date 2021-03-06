/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Rectangle;

/**
 *
 * @author Josh Holland
 */
public class SideViewAI extends AI{
    
    /**
     * The maximum velocity that the AI can reach. 
     */
    public int MAX_VELOCITY=1;
    
    /**
     * The x value, in tiles, at which the user hits in order to spawn this AI.
     */
    public int startX=2000;
    
    /**
     * If this AI is currently active and visible. 
     */
    public boolean active=false;
    
    //public CListener dead=null;
    
    /**
     * This multiplier is used to translate location points to pixels. 
     */
    public final double POINT_TO_PIXEL_MULTIPLIER=SQUARE_SIZE/20.0;
    
    /**
     * This tells if the AI is facing to the right. 
     */
    public boolean facingRight=false;
    
    /**
     * The span of the AI; used for collisions and general drawing. 
     */
    public Rectangle span=new Rectangle();
    
    /**
     * These boolean values concern the AI's attacking status. 
     */
    public boolean attacking=false,wasAttacking=false,canAttack=true; 
    
    /**
     * These boolean values concern the AI's jumping status. 
     */
    public boolean jumping=false,wasJumping=false;
    
    /**
     * An integer used to control the flow of the AI's drawing. 
     */
    public int sequence=0;
    
    /**
     * How much damage the AI deals per attack. 
     */
    public double damage=2;
    
    /**
     * If this AI is defeated. 
     */
    public boolean defeated=false;
    
    /**
     * The CListener instance used to spawn a Projectile object. 
     */
    public static CListener spawnProjectile=null;
    
    /**
     * The HitBox object used for melee attacks in order to attack the player. 
     */
    public HitBox meleeAttack=null;
    
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
     * This function draws the visual representation of this object. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     * @param camX the x location of the camera (location points)
     * @param camY the y location of the camera (location points)
     */
    public void draw(java.awt.Graphics g,int camX,int camY){}
    
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
            //System.out.println("should attackkkk!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            startAttack();
        }
        if(health<0)
            defeated=true;
    }
    
//    public void calculateLeftOrRight(int playerX){
//        facingRight=span.x<playerX;   
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
    
//    /**
//     * This function spawns a hit box of a certain type, location, and size,
//     *  primarily for the Melee-attack AI. 
//     * 
//     * @param x the x coordinate of the hitbox
//     * @param y the y coordinate of the hitbox
//     * @param span.width the span.width of the hitbox
//     * @param span.height the span.height of the hitbox
//     */
//    public void spawnHitBox(int x,int y,int span.width,int span.height){
//        
//    }
    
    /**
     * This function is used for the flow of the start of an attack. 
     */
    public void startAttack(){
        
    }
    
    /**
     * This function is used for the flow of the end of an attack. 
     */
    public void endAttack(){
        wasAttacking=false;
        attacking=false;
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
    
    /**
     * This returns the x component of the AI's velocity.
     * 
     * @return the x component of the AI's velocity
     */
    public int getXVelocity(){
        return velocity[0];
    }
    
    /**
     * This returns y component of the AI's velocity.
     * 
     * @return the y component of the AI's velocity
     */
    public int getYVelocity(){
        return velocity[1];
    }
    
    /**
     * This function sets the AI's velocity in the x direction. 
     * 
     * @param a what to set the velocity in the x direction to
     */
    public void setXVelocity(int a){
        velocity[0]=a;
    }
    
    /**
     * This function sets the AI's velocity in the y direction.
     * 
     * @param a what to set the velocity in the y direction to
     */
    public void setYVelocity(int a){
        velocity[1]=a;
    }
    
    /**
     * This function increments the x component of the AI's velocity. 
     * 
     * @param a how much to increment the x component of the AI's velocity
     *      by 
     */
    public void incrementXVelocity(int a){
        velocity[0]+=a;
    }
    
    /**
     * This function increments the y component of the AI's velocity. 
     * 
     * @param a how much to increment the y component of the AI's velocity
     *      by 
     */
    public void incrementYVelocity(int a){
        velocity[1]+=a;
    }
    
    /**
     * This function is used to retrieve the integer value of the height of the 
     *  SideViewAI object. 
     * 
     * @return the height of this SideViewAI object
     */
    public int getHeight(){
        return span.height;
    }
    
    /**
     * This function is used to retrieve the integer value of the width of the 
     *  SideViewAI object. 
     * 
     * @return the width of this SideViewAI object
     */
    public int getWidth(){
        return span.width;
    }
    
    /**
     * This function returns the jump velocity of this AI object. 
     * 
     * @return the jump velocity of this AI object
     */
    public int getJumpVelocity(){
        return 3;
    }
    
    /**
     * This function adds the velocity to the location of the player. 
     */
    public void moveByVelocities(){
        span.x+=velocity[0]/2;
        span.y+=velocity[1]/2;
    }
    
        /**
     * This function increments the x value of the AI's location by the
     *  amount specified; if it is negative then the number will decrease the
     *  player's x location. 
     * 
     * @param a how much to increment the AI's x location by
     */
    public void incrementX(int a){
        span.x+=a;
    }
    
    /**
     * This function increments the y value of the AI's location by the 
     *  amount specified; if it is negative then the number will decrease the 
     *  AI's y location.
     * 
     * @param a how much to increment the AI's y location by
     */
    public void incrementY(int a){
        span.y+=a;
    }
    
    /**
     * retrieves the X value of the current position of the AI object
     * 
     * @return the x location value of the AI object
     */
    public int getX(){
        return span.x;
    }
    
    /**
     * retrieves the Y value of the current position of the AI object
     * 
     * @return the y location value of the AI object
     */
    public int getY(){
        return span.y;
    }
    
    
}
