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
public class SideViewRegion extends Region{
    /**
     * The ArrayList of AIs; this is used to manage the AIs. 
     */
    public ArrayList<SideViewAI> AIs=new ArrayList<>();
    
    /**
     * The imported data about the region that is used for collisions and for
     *  drawing the region. 
     */
    public int[][] regionData=null;
    
    /**
     * This rectangle is for if there is a portal at the end of the side view
     *  in order to end the mission. 
     */
    public Rectangle portal=null;
    
    /**
     * This specifies if the mission is timed. 
     */
    public boolean timed=false;// duplicate - also in SideViewRunner

    /**
     * This sets up the SideViewRegion using the id of the region. 
     * 
     * @param regn the ID of the region
     */
    public SideViewRegion(int regn) {
        super(regn);
        int[] a=Profile.getSideViewMissionData(regn);
        regionData=Profile.importRegionDataSideView(regn);
        timed=a[0]!=0?true:false;
        if(a[1]!=0)//has a portal
            portal=new Rectangle(a[2],a[3],a[4],a[5]);
    }
    
    /**
     * This draws the graphical representation of the region. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    public void draw(Graphics g){
        
    }
    
    /**
     * This tells if the space provided is clear in terms of the region. 
     * 
     * @param x x value of the block-space the function concerns
     * @param y y value of the block-space the function concerns
     * @return if the spot is clear
     */
    public boolean canMoveToSpace(int x,int y){
        
        return true;
    }
}
