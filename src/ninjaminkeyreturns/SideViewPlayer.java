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
public class SideViewPlayer extends Player{
    
    public Rectangle span=new Rectangle();
    
    private final int WALK_VELOCITY=2;//location points per loop
    
    private int[] velocity=new int[]{0,0};
    
    public boolean facingRight=true;
    
    private boolean jumping=false; 
    
    private boolean running=false;
    
    private int imageSequence=0;
    
    public SideViewPlayer(int[] loc){
        super(loc);
        images=GraphicsAssets.importSideViewPlayerImages();
    }
    
    /**
     * @param loc
     * @param spanX pixels
     * @param spanY pixels
     */ 
    public SideViewPlayer(int[] loc,int spanX,int spanY){
        this(loc);
        span=new Rectangle(location[0],location[1],spanX,spanY);
    }
    
    /**
     * 
     * @param g
     * @param xIncrement The integer value used for the value of x in which the 
     *  player should appear to move in relative to the graphical representation
     *  of all other objects
     * @param yIncrement The integer value used for the value of in which the 
     *  player should appear to move in relative to the graphical representation
     *  of all other objects
     */
    public void draw(Graphics g,int xIncrement,int yIncrement){
        if(travelling){
            g.drawImage(images.get((facingRight?0:8)+(jumping?16:0)+imageSequence/5),
                GAME_SPAN.x+GAME_SPAN.width/2-SQUARE_SIZE/2+xIncrement,
                GAME_SPAN.y+GAME_SPAN.height/2-SQUARE_SIZE+yIncrement,
                SQUARE_SIZE,SQUARE_SIZE*2,null);  
        }else //not travelling
            g.drawImage(images.get(1),50,50,null);    }
    
    public void move(){
        
    }
    
    private void walkRight(){
        
    }
    
    private void walkLeft(){
        
    }
    
    private void jump(){
        
    }
    
    private void attackLeft(){
        
    }
    
    private void attackUp(){
        
    }
    
    private void attackDown(){
        
    }
    
    ////////////////////
    
    public int getWalkVeloctiy(){
        return WALK_VELOCITY;
    }
    
    public int getXVelocity(){
        return velocity[0];
    }
    
    public int getYVelocity(){
        return velocity[1];
    }
    
    public boolean getJumping(){
        return jumping;
    }
    
    public void setJumping(boolean j){/// ---- probably not used? - -- -- - -- - -- - -
        jumping=j;
    }
    
    public boolean getrunning(){
        return running;
    }
    
    public void setrunning(boolean j){/// ---- probably not used? - -- -- - -- - -- - -
        running=j;
    }
    
}
