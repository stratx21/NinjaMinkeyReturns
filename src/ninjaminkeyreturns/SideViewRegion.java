/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class SideViewRegion extends Region{
    public ArrayList<SideViewAI> AIs=new ArrayList<>();

    public SideViewRegion(int regn) {
        super(regn);
    }
    
    public void draw(Graphics g){
        
    }
    
    /**
     * 
     * @param x x value of the block-space the function concerns
     * @param y y value of the block-sppace the function concerns
     * @return 
     */
    public boolean canMoveToSpace(int x,int y){
        
        return true;
    }
}
