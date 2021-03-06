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
 * @author Josh Holland
 */
public class Projectile extends HitBox{
    
    public final double POINT_TO_PIXEL_MULTIPLIER=Region.SQUARE_SIZE/20.0;
    
    

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
     * @param width how wide the hitbox is (in locationPoints)
     * @param height how high the hitbox is (in location points)
     * @param dmg how much damage the hitbox deals
     */
    public Projectile(int x, int y, int width, int height, double dmg,int[] vel,BufferedImage img) {
        super(x,y,width,height,dmg);
        velocity=new double[]{vel[0],vel[1]};
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
    private double[] velocity=new double[2];//is a double for the sake of gravity affecting it less
    
    /**
     * The image that is used to represent the projectile graphically to the
     *  user. 
     */
    private BufferedImage image=null;
    
    /**
     * This function draws the visual representation of this object. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     * @param camX the x location of the camera (location points)
     * @param camY the y location of the camera (location points)
     */
    @Override
    public void draw(Graphics g,int camX,int camY){
        g.drawImage(image,
                (int)((x-camX)*POINT_TO_PIXEL_MULTIPLIER),
                (int)((y-camY)*POINT_TO_PIXEL_MULTIPLIER),
                (int)(width*POINT_TO_PIXEL_MULTIPLIER),
                (int)(height*POINT_TO_PIXEL_MULTIPLIER),
                null);
        
        //System.out.println("drawing the projectileeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+x+"   "+y+"   "+(int)(width*POINT_TO_PIXEL_MULTIPLIER)+"    "+(int)(height*POINT_TO_PIXEL_MULTIPLIER));
        
    }
    
    /**
     * This function is used as a loop to calculate the location of the 
     *  Projectile. 
     */
    public void calculate(){
        if(velocity[1]<5)
            velocity[1]+=0.5;
        incrementByVelocity();
    }
    
    /**
     * This function increments the location of the projectile by the velocity 
     *  that it has. 
     */
    public void incrementByVelocity(){
        x+=velocity[0];
        y+=velocity[1];
    }
    
    /**
     * Get the x velocity of the Projectile
     * 
     * @return the x velocity of the Projectile
     */
    public int getXVelocity(){
        return (int)velocity[0];
    }
    
    /**
     * Get the y velocity of the Projectile
     * 
     * @return the y velocity of the Projectile
     */
    public int getYVelocity(){
        return (int)velocity[1];
    }
    
    /**
     * Get the velocity of the Projectile
     * 
     * @return the velocity of the Projectile
     */
    public int[] getVelocity(){
        return new int[]{(int)velocity[0],(int)velocity[1]};
    }
    
}
