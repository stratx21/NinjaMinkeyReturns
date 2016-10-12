/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class Prompt{
    public static Rectangle GAME_SPAN=null;
    public static int SQUARE_SIZE=0;
    
    private java.awt.image.BufferedImage promptImage=null;
    
    public boolean showStats=false;
    
    public static int textSpeed=3;
    
    public Prompt(){
        promptImage=GraphicsAssets.getTopDownPromptImage(); 
    }
    
    public Prompt(boolean showStats){ 
        this();
        this.showStats=showStats;
    }
    
    public void draw(Graphics g){
        
    }
    
}