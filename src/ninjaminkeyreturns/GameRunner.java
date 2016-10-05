/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 * @author Josh
 */
public class GameRunner implements KeyListener{
    
    public static char[] controls=new char[6]; //to change controls change them in Profile then evoke the resetControls function
    
    public static Rectangle GAME_SPAN=new Rectangle();
    public static int SQUARE_SIZE=0;
    
    public static Font font=new Font(Font.SANS_SERIF,Font.PLAIN,(int)(GAME_SPAN.getX()*0.01));
    
    /**
     * CListener evoked to exit the game mode or to finish gameplay
     */
    public CListener done=null;
    
    public GameRunner(){
        resetControls();
    }
    
    public GameRunner(CListener dn){
        this();
        done=dn;
    }
    
    public void resetFont(){
        font=new Font(Font.SANS_SERIF,Font.PLAIN,(int)(GAME_SPAN.getX()*0.01));
    }
    
    public void resetControls(){
        controls=Profile.controls;
    }
    
    public void draw(Graphics g){}
    
    public void calculate(){}
    
    /////////
    
    @Override
    public void keyTyped(KeyEvent e){
        keyTypedFlow(Character.toUpperCase(e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e){
        keyPressedFlow(Character.toUpperCase(e.getKeyChar()));
    }

    @Override
    public void keyReleased(KeyEvent e){
        keyReleasedFlow(Character.toUpperCase(e.getKeyChar()));
    }
    
    //////
    
    public void keyTypedFlow(char typed){}
    
    public void keyPressedFlow(char typed){}
    
    public void keyReleasedFlow(char typed){}
    
    
    public void showPrompt(String prompt){
        
    }
    
    ////////
    
}
