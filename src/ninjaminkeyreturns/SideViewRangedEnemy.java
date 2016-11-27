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
public class SideViewRangedEnemy extends SideViewAI{
    private int delay=0;
    
    @Override
    public boolean shouldAttack(int playerX,int playerY){ // never uses these two number to calculate...
        if(delay>69){//range of 3 tiles to start shooting
            if(location[0]>playerX-60&&location[0]<playerX+60){
                delay=0;
                return true;
            } else
                return false;
        }
        delay++;
        return false;
    }
    
    @Override
    public void travel(int playerX,int playerY){
        if(location[0]<playerX-60//AI is to the left of range area
                ){
            if(velocity[0]<MAX_VELOCITY){//velocity is less than the max allowed velocity
                velocity[0]++;
            }
            facingRight=true;
            
        } else if(location[0]>playerX+60//AI is to the right of the range area
                ){
            if(velocity[0]>-1*MAX_VELOCITY){//velocity is greater than the least allowed velocity
                velocity[0]--;
            }
            facingRight=false;
        } else{ // target velocity is zero; it is in range
            if(velocity[0]>0)
                velocity[0]--;
            else if(velocity[0]<0)
                velocity[0]++;
        }
        
        velocity[0]=MAX_VELOCITY*(velocity[0]>0?1:-1);
        
        location[0]+=velocity[0];
        location[1]+=velocity[1];
    }
    
    @Override
    public void attackLeft(){
        
    }
    
    @Override
    public void attackRight(){
        
    }
    
}
