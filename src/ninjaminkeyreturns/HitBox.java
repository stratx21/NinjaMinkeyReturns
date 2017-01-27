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
public class HitBox extends Rectangle{
    /**
     * The amount of damage that the hitbox causes
     */
    private double damage=0.0;
    
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
    public HitBox(int x,int y,int width,int height,double dmg){
        super(x,y,width,height);
        damage=dmg;
    }
    
    /**
     * This function sets the amount of damage that the hitbox is intended
     *  to give.
     * 
     * @param d how much damage the hitbox should give
     */
    public void setDamage(double d){
        damage=d;
    }
    
    /**
     * This function returns the amount of damage that the hitbox is meant to
     *  give.
     * 
     * @return the value of damage that the hitbox is meant to give
     */
    public double getDamage(){
        return damage;
    }
    
    public void draw(Graphics g, int camX,int camY){}
    
}
