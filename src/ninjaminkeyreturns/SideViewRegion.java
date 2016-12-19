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
        System.out.println("initializing SideViewRegion...");
        int[] a=Profile.getSideViewMissionData(regn);
        regionData=Profile.importRegionDataSideView(regn);
        images=GraphicsAssets.importRegionImagesSideView(regn);
        timed=a[0]!=0?true:false;
        if(a[1]!=0)//has a portal
            portal=new Rectangle(a[2],a[3],a[4],a[5]);
        System.out.println("initialization of SideViewRegion finished");
    }
    
    /**
     * This draws the graphical representation of the region. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    public void draw(Graphics g,int cameraX,int cameraY){
        int extraLocationPoints=cameraX%20,
            extraSpaces=extraLocationPoints==0?0:2;//if some of the tile goes off the screen then 2 extra columns on each side will be drawn
        //cameraX/=20;//convert to index from location points
        //cameraY/=20;//convert to index from location points
        
        for(int y=0;y<9;y++)
            for(int x=0-extraSpaces/2;x<17+extraSpaces;x++){
                int temp;
                if((temp=regionData[x][y])!=0)
                    g.drawImage(images.get(temp-1),
                            x*SQUARE_SIZE+(20-extraLocationPoints)/SQUARE_SIZE,
                            y*SQUARE_SIZE,
                            SQUARE_SIZE,
                            SQUARE_SIZE,
                            null);
            }
            //g.drawImage(images.get(0),20+extraLocationPoints,20,SQUARE_SIZE,SQUARE_SIZE,null);
    }
    
    /**
     * This tells if the space provided is clear in terms of the region. 
     * 
     * @param x x value of the block-space the function concerns in location points
     * @param y y value of the block-space the function concerns in location points
     * @return if the spot is clear
     */
    public boolean canMoveToSpace(int x,int y){
        x/=20;//translate from location points to index 
        y/=20;//translate from location points to index 
        if(regionData!=null){
            int type=regionData[x][y];
            return type==0;
        }
        return true;
    }
}
