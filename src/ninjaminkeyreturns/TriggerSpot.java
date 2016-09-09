/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

/**
 *
 * @author Josh
 */
public class TriggerSpot {
    
    private int[] location=new int[2];
    
    //public int TYPE_ID=0;
    
    public int AI_Triggered=-1;
    
    public int length=0,height=0;
    
    public boolean visible=true;
    
    public boolean toRegion=false;
    
    public int regionToGoTo=-1;//if  applicable
    
    public TriggerSpot(int x,int y,int lngth,int hght,int rgnToGoTo,boolean vsble,int aiTriggrd){
        location=new int[]{x,y};
        length=lngth;
        height=hght;
        if(toRegion=(rgnToGoTo>-1)){
            regionToGoTo=rgnToGoTo;
        }
        visible=vsble;
        if(aiTriggrd>-1)
            AI_Triggered=aiTriggrd;
    }
    
    public int getX(){
        return location[0];
    }
    
    public int getY(){
        return location[1];
    }
    
}
