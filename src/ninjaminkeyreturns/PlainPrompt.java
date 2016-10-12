/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class PlainPrompt extends Prompt{//this prompt is used for just showing text on the screen and allowing the player to hit select to continue through it. 
    
    private ArrayList<String> showing=null;
    
    
    
    public PlainPrompt(ArrayList<String> showing){
        super();
        this.showing=showing;
    }
    
    
    public void loopCalculate(boolean cont){
        
    }
}
