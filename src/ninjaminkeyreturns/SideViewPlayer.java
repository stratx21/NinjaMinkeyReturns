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
        g.drawImage(images.get(2),50,50,null);
    }
    
}
