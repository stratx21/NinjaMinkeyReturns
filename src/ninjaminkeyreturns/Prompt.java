/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class Prompt{
    public static Font font=null;
    
    public static Rectangle GAME_SPAN=null;
    public static int SQUARE_SIZE=0;
    
    private java.awt.image.BufferedImage promptImage=null;
    
    private ArrayList<String> promptShowing=new ArrayList<>();
    
    public boolean showingPrompt=false,waitingForInput=false;
    
    public int textSpeed=3;//inversed; the higher the number the slower it will be
    
    public boolean showStats=false;
    
    public Prompt(){
        promptImage=GraphicsAssets.getTopDownPromptImage(); 
    }
    
    public Prompt(boolean showStats){ 
        this();
        this.showStats=showStats;
    }
    
    public void draw(Graphics g){
        
    }
    
    public void drawBackImage(Graphics g){
        g.drawImage(promptImage,(int)GAME_SPAN.getX(),(int)(GAME_SPAN.getY()+(int)(GAME_SPAN.getHeight()*0.57)),(int)(GAME_SPAN.getWidth()),(int)(GAME_SPAN.getHeight()*0.4),null);
    }
    
}