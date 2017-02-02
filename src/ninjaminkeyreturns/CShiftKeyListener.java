/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Josh
 */
public class CShiftKeyListener implements KeyListener{
    
    /**
     * This function is not used but is required by the interface implemented,
     *  KeyListener. 
     * 
     * @param e the KeyEvent instance used to determine which key was pressed
     */
    @Override
    public void keyTyped(KeyEvent e){
        keyTypedFlow(Character.toUpperCase(e.getKeyChar()));
    }

    /**
     * This function is called whenever a key is pressed and will use the 
     *  information given about which key was pressed to determine what to do.
     * 
     * @param e the KeyEvent instance used to determine which key was pressed
     */
    @Override
    public void keyPressed(KeyEvent e){
        keyPressedFlow(Character.toUpperCase(e.getKeyChar()));
    }

    /**
     * This function is called whenever a key is released and will use the 
     *  information given about which key was pressed to determine what to do.
     * 
     * @param e the KeyEvent instance used to determine which key was pressed
     */
    @Override
    public void keyReleased(KeyEvent e){       
        keyReleasedFlow(Character.toUpperCase(e.getKeyChar()));
    }
    
    public void keyTypedFlow(char c){}
    
    public void keyPressedFlow(char c){}
    
    public void keyReleasedFlow(char c){}
    
}
