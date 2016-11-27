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
public class SideViewAirEnemy extends SideViewAI{
    private int delay=0;
//    private boolean delayReady=false;
    
    @Override
    public boolean shouldAttack(int playerX,int playerY){ // never uses these two numbers to calculate... [inheritance complication]
        if(delay>69){
            delay=0;
            return true;
        }
        delay++;
        return false;
    }
    
    @Override
    public void travel(int playerX,int playerY){
        location[0]+=velocity[0];
        location[1]+=velocity[1]; // if the AIs cannot be hit (air ones) then this may not be needed?
    }
    
    @Override
    public void attackLeft(){
        
    }
    
    @Override
    public void attackRight(){
        
    }
    
}
