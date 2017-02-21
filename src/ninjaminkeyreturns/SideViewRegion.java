/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
    
    private final int POINT_TO_PIXEL_MULTIPLIER=SQUARE_SIZE/20;
    
    private BufferedImage background=null;
    
    private int X_TILES=0;
    
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

    
    public int getX_TILES(){
        return X_TILES;
    }
    
    public int getMapLength(){//in location points
        return X_TILES*20;
    }
    
    /**
     * This sets up the SideViewRegion using the id of the region. 
     * 
     * @param regn the ID of the region
     */
    public SideViewRegion(int regn) {
        super(regn);
        ErrorLogger.logEvent("initializing SideViewRegion "+regn+"...");
        
        int[] a=Profile.getSideViewMissionData(regn);
        regionData=Profile.importRegionDataSideView(regn);
        
        images=GraphicsAssets.importRegionImagesSideView(regn);
        background=GraphicsAssets.importSideViewBackground(regn);
        timed=a[0]!=0?true:false;
        
        if(a[1]!=0)//has a portal
            portal=new Rectangle(a[2],a[3],a[4],a[5]);
        
        X_TILES=regionData.length;
        
        AIs=Profile.importAISideView(regn);
        
        
        System.out.println("AIs:: "+AIs);
        
        //System.out.println("initialization of SideViewRegion finished : xtiles :: "+X_TILES);
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
            extraSpaces=extraLocationPoints==0?2:2;//if some of the tile goes off the screen then 2 extra columns on each side will be drawn
        //cameraX/=20;//convert to index from location points
        //cameraY/=20;//convert to index from location points
        
        
        //draw background::
        g.drawImage(background,0,0,(int)GAME_SPAN.getWidth(),(int)GAME_SPAN.getHeight(), null);
        
        for(int y=0;y<10;y++)
            for(int x=0-extraSpaces/2;x<17+extraSpaces;x++){
                int temp;
                if((x+cameraX/20-1>-1&&x+cameraX/20-1<X_TILES)&&((temp=regionData[x+cameraX/20-1][y])!=0)){
                    g.drawImage(images.get(temp-1),
                            x*SQUARE_SIZE+(20-extraLocationPoints)*POINT_TO_PIXEL_MULTIPLIER-SQUARE_SIZE*2,
                            y*SQUARE_SIZE,
                            SQUARE_SIZE,
                            SQUARE_SIZE,
                            null);
//                    g.setColor(Color.YELLOW);
//                    g.fillRect(x*SQUARE_SIZE+(20-extraLocationPoints)*POINT_TO_PIXEL_MULTIPLIER-SQUARE_SIZE*2,
//                            y*SQUARE_SIZE,
//                            SQUARE_SIZE,
//                            SQUARE_SIZE);
                }
            }
            //g.drawImage(images.get(0),20+extraLocationPoints,20,SQUARE_SIZE,SQUARE_SIZE,null);
//        g.setColor(java.awt.Color.red);
//        g.fillRect(temp.x*4,temp.y*4,temp.width*4,temp.height*4);
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
        
        if(x<1||x>regionData.length-1)
            return false;
        if(y<0)
            return true;
        
        int type=regionData[x][y];
        return type==0;
        
    }
    
    /**
     * 
     * @param playerSpan the Rectangle object for the player after the current
     *      velocity values have been added
     * @return the byte containing what direction the collision is from
     *                                    0    1
     *                                    2    3  
     *                                    4    5
     * and -1 for no collision
     */
    public byte mapCollision(Rectangle playerSpan){
        int x=(int)playerSpan.getX(),
                y=(int)playerSpan.getY(),
                width=(int)playerSpan.getWidth(),
                height=(int)playerSpan.getHeight();
        //temp=playerSpan;
        
        if(!canMoveToSpace(x,y+height/2))//side ones first
            return 2;
        if(!canMoveToSpace(x+width,y+height/2))
            return 3;
        if(!canMoveToSpace(x,y))
            return 0;
        if(!canMoveToSpace(x+width,y))
            return 1;
        if(!canMoveToSpace(x,y+height))
            return 4;
        if(!canMoveToSpace(x+width,y+height))
            return 5;
        
        
        return -1;
    }
    
    //Rectangle temp=new Rectangle();
}
