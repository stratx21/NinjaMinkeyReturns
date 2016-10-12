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
public class Prompt{
    public static Rectangle GAME_SPAN=null;
    public static int SQUARE_SIZE=0;
    
    private java.awt.image.BufferedImage promptImage=null;
    
    private boolean navigable=false,
            showStats=false;
    
    public static int textSpeed=3;
    
    public Prompt(boolean navigable){
        this.navigable=navigable;
        promptImage=GraphicsAssets.getTopDownPromptImage(); 
    }
    
    public Prompt(boolean navigable,boolean showStats){
        this(navigable);
        this.showStats=showStats;
    }
    
    public void draw(Graphics g){
        
    }
    
    public void loopCalculate(boolean cont){
        
    }
    
    public void loopCalculate(boolean[] keysPressed){
        
    }
    
    public boolean getNavigable(){
        return navigable;
    }
    
    public void setNavigable(boolean a){
        navigable=a;
    }
    
}
