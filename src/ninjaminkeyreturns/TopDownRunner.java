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
public class TopDownRunner extends GameRunner{
    
    private TopDownPlayer player=new TopDownPlayer();
    
    private TopDownRegion region=new TopDownRegion();
    
    public TopDownRunner(){
        super();
    }
    
    public TopDownRunner(CListener dn){
        super(dn);
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g){
        
    }
    
    private void moveToNewRegion(int newRegion){
        
    }
    
    /**
     *
     */
    @Override
    public void calculate(){
        
    }
    
    
    
    public void drawBackgroundTiles(Graphics g){
        
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
