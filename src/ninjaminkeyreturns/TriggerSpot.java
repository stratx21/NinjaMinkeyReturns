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
    
    public int[] location=new int[2];
    
    public int length=0,height=0;
    
    public boolean toRegion=false;
    
    public int regionToGoTo=-1;//if  applicable
    
    public TriggerSpot(int x,int y,int lngth,int hght,int rgnToGoTo){
        location=new int[]{x,y};
        length=lngth;
        height=hght;
        if(toRegion=(rgnToGoTo>-1))
            regionToGoTo=rgnToGoTo;
        
    }
    
}
