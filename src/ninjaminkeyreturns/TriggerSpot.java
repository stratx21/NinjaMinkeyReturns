/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

/**
 *
 * @author Josh Holland
 */
public class TriggerSpot {
    
    /**
     * The location of the trigger spot.
     */
    private int[] location=new int[2];
    
    /**
     * The coordinates to go to once triggered (if applicable) 
     */
    private int[] coordsToGoTo=new int[2];
    
    /**
     * The save index to check in order to use this trigger spot. 
     */
    public int saveCheckIndex=-1;
    
    //public int TYPE_ID=0;
    
    /**
     * The index of the AI that was triggered.
     */
    public int AI_Triggered=-1;
    
    /**
     * the width and height, repsectively, of the trigger spot. 
     */
    private int width=0,height=0;
    
    /**
     * This tells if the trigger spot is visible. 
     */
    public boolean visible=true;
    
    /**
     * This tells if the trigger spot goes to another region. 
     */
    public boolean toRegion=false;
    
    /**
     * This integer is the region ID for the region to go to if this
     *  TriggerSpot brings the user to another region. 
     */
    public int regionToGoTo=-1;//if  applicable
    
    /**
     * This sets up the TriggerSpot using the information that was imported
     *  from a text file through the Profile class. 
     * 
     * @param x the x location of the TriggerSpot
     * @param y the y location of the TriggerSpot
     * @param lngth the length of the TriggerSpot
     * @param hght the height of the TriggerSpot
     * @param rgnToGoTo the region to go to once triggered
     * @param vsble
     * @param aiTriggrd 
     */
    public TriggerSpot(int x,int y,int lngth,int hght,int rgnToGoTo,boolean vsble,int aiTriggrd,int xToGoTo,int yToGoTo,int saveCheckINDX){
        //System.out.println("From TriggerSpot:: "+x+" "+y+" "+lngth+" "+hght+" "+rgnToGoTo+" "+vsble+" "+aiTriggrd);
        location=new int[]{x,y};
        saveCheckIndex=saveCheckINDX;
        coordsToGoTo=new int[]{xToGoTo,yToGoTo};
        width=lngth-x;
        height=hght-y;
        if(toRegion=(rgnToGoTo>-1)){
            regionToGoTo=rgnToGoTo;
        }
        visible=vsble;
        if(aiTriggrd>-1)
            AI_Triggered=aiTriggrd;
        
//        System.out.println("trigger at:: "+x+","+y+" :: toRegion=="+toRegion);
    }
    
    /**
     * This function returns the width of the TriggerSpot.
     * 
     * @return the width of the TriggerSpot
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * This function returns the height of the TriggerSpot.
     * 
     * @return the height of the TriggerSpot
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * This function returns the x location of the TriggerSpot.
     * 
     * @return the x location of the TriggerSpot
     */
    public int getX(){
        return location[0];
    }
    
    /**
     * This function returns the y location of the TriggerSpot.
     * 
     * @return the y location of the TriggerSpot
     */
    public int getY(){
        return location[1];
    }
    
    /**
     * This function sets the location of the TriggerSpot object. 
     * 
     * @param x the x location (in tiles)
     * @param y the y location (in tiles)
     */
    public void setLocation(int x,int y){
        location=new int[]{x,y};
    }
    
    /**
     * This function returns the coordinates for the player to go to when going
     *  to another region. 
     * 
     * @return the integer array of x and y coordinates to which the player 
     *      should go to when going to the next region that this TriggerSpot
     *      leads it to. 
     */
    public int[] getCoordsToGoTo(){
        return coordsToGoTo;
    }
    
}
