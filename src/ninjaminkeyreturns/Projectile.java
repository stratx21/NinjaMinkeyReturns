/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Josh
 */
public class Projectile extends HitBox{

    /**
     * This constructor sets up the HitBox using the x and y coordinates
     *  in addition to its width, height, and how much damage it deals. 
     * 
     * @param x the starting x coordinate of the hitbox (in location points)
     * @param y the starting y coordinate of the hitbox (in location points)
     * @param width how wide the hitbox is (in location points)
     * @param height how high the hitbox is (in location points)
     * @param dmg how much damage the hitbox deals
     */
    public Projectile(int x, int y, int width, int height, double dmg) {
        super(x, y, width, height, dmg);
    }
    
    /**
     * This constructor sets up the HitBox using the x and y coordinates
     *  in addition to its width, height, how much damage it deals, and the
     *  starting velocity. 
     * 
     * @param x the starting x coordinate of the hitbox (in location points)
     * @param y the starting y coordinate of the hitbox (in location points)
     * @param width how wide the hitbox is (in location points)
     * @param height how high the hitbox is (in location points)
     * @param dmg how much damage the hitbox deals
     */
    public Projectile(int x, int y, int width, int height, double dmg,int[] vel,BufferedImage img) {
        super(x, y, width, height, dmg);
        velocity=vel;
        image=img;
    }
    
    /**
     * This sets up the Projectile object using how much damage it deals on
     *  its target, its initial position, its width, and its initial velocity.
     * 
     */
//    public Projectile(int x,int y,int width,int height,double dmg){

    
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
     * The image that is used to represent the projectile graphically to the
     *  user. 
     */
    private BufferedImage image=null;
    
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height,null);
    }
    
    public void incrementByVelocity(){
        x+=velocity[0];
        y+=velocity[1];
    }
    
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
//    public int getX(){
//        return location[0];
//    }
    
    /**
     * Get the y location of the Projectile
     * 
     * @return the y location of the Projectile
     */
//    public int getY(){
//        return location[1];
//    }
    
    /**
     *
     * @return
     */
//    public int[] getLocation(){
//        return location;
//    }
    
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
