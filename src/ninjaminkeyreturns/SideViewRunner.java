/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
/**
 *
 * @author Josh
 */
public class SideViewRunner extends GameRunner{
    
    private SideViewPlayer player=new SideViewPlayer(new int[]{60,160});//will this have a different location every time? or just stick with one for every start?
    
    public SideViewRunner(){
        super();
    }
    
    public SideViewRunner(CListener dn){
        super(dn);
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g){
        
    }
    
    /**
     *
     */
    @Override
    public void calculate(){
        
    }
    
    
    @Override
    public void keyTypedFlow(char typed){
        
    }
    
    @Override
    public void keyPressedFlow(char typed){
        
    }
    
    @Override
    public void keyReleasedFlow(char typed){
        
    }
    
}
