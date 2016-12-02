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
public class Projectile {
    
    /**
     * This sets up the Projectile object using how much damage it deals on
     *  its target, its initial position, its width, and its initial velocity.
     * 
     * @param dmg the damage that the projectile deals to its target
     * @param position the initial position that it starts at
     * @param wid the width of the side of the span of the Projectile
     * @param vel the initial velocity of the Projectile 
     */
    public Projectile(double dmg,int[] position,int wid,int[] vel){
        location=position;
        damage=dmg;
        velocity=vel;
        width=width;
    }
    
    /**
     * The velocity, in location points and in the x,y format, of the
     *  Projectile. 
     */
    private int[] velocity=new int[2];
    
    /**
     * The length of one side of the Projectile. 
     */
    private int width=0;
    
    /**
     * This is how much damage the Projectile influences on what it hits.
     */
    private double damage=0;
    
    /**
     * This returns how much damage the Projectile influences on its target.
     * 
     * @return how much damage the Projectile influences on its target
     */
    public double getDamage(){
        return damage;        
    }
    
    /**
     * Set how much damage the Projectile influences on its target.
     * 
     * @param a how much damage the Projectile influences on its target
     */
    public void setDamage(double a){
        damage=a;
    }
    
    /**
     * The current location, in location points and in the x,y format, of the
     *  Projectile. 
     */
    private int[] location=new int[2];
    
    /**
     * Get the x location of the Projectile.
     * 
     * @return the x location of the Projectile
     */
    public int getX(){
        return location[0];
    }
    
    /**
     * Get the y location of the Projectile
     * 
     * @return the y location of the Projectile
     */
    public int getY(){
        return location[1];
    }
    
    /**
     *
     * @return
     */
    public int[] getLocation(){
        return location;
    }
    
    /**
     * Get the x velocity of the Projectile
     * 
     * @return the x velocity of the Projectile
     */
    public int getXVelocity(){
        return velocity[0];
    }
    
    /**
     * Get the y velocity of the Projectile
     * 
     * @return the y velocity of the Projectile
     */
    public int getYVelocity(){
        return velocity[1];
    }
    
    /**
     * Get the velocity of the Projectile
     * 
     * @return the velocity of the Projectile
     */
    public int[] getVelocity(){
        return velocity;
    }
    
}
