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
    
    public boolean[] currentKey=new boolean[6];//up, down, left, right, attack, other attack
    
    public static Rectangle GAME_SPAN=new Rectangle();
    public static int SQUARE_SIZE=0;
    
    public static Font font=null;
    
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
        font=new Font(Font.SANS_SERIF,Font.PLAIN,(int)(GAME_SPAN.getWidth()/21));
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
        //        System.out.println("key released:: "+typed);
        char typed=Character.toUpperCase(e.getKeyChar());
        if(typed==controls[0]){
            currentKey[0]=true;
            setOtherKeysFalse(0);//in here to change it only if it is a valid key
        }else if(typed==controls[1]){
            currentKey[1]=true;
            setOtherKeysFalse(1);
        }else if(typed==controls[2]){
            currentKey[2]=true;
            setOtherKeysFalse(2);
        }else if(typed==controls[3]){
            currentKey[3]=true;
            setOtherKeysFalse(3);
        }else if(typed==controls[4]){
            currentKey[4]=true;
            setOtherKeysFalse(4);
        }else if(typed==controls[5]){
            currentKey[5]=true;
            setOtherKeysFalse(5);
        }
        keyPressedFlow(typed);
    }

    @Override
    public void keyReleased(KeyEvent e){
        char typed=Character.toUpperCase(e.getKeyChar());
        if(typed==controls[0]){
            currentKey[0]=false;
        }else if(typed==controls[1]){
            currentKey[1]=false;
        }else if(typed==controls[2]){
            currentKey[2]=false;
        }else if(typed==controls[3]){
            currentKey[3]=false;
        }else if(typed==controls[4]){
            currentKey[4]=false;
        }else if(typed==controls[5]){
            currentKey[5]=false;
        }
        
        keyReleasedFlow(Character.toUpperCase(e.getKeyChar()));
    }
    
    private void setOtherKeysFalse(int doNotChange){
        for(int i=0;i<6;i++)
            if(i!=doNotChange)
                currentKey[i]=false;
    }
    
    //////
    
    public void keyTypedFlow(char typed){}
    
    public void keyPressedFlow(char typed){}
    
    public void keyReleasedFlow(char typed){}
    
    
    public void showPrompt(String prompt){
        
    }
    
    ////////
    
}
