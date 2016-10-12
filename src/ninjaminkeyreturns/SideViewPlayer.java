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
    
    public void draw(Graphics g){
        g.drawImage(images.get(1),50,50,null);
    }
    
    public void move(){
        
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
    
}
