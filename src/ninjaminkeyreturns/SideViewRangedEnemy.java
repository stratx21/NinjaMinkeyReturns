/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Rectangle;

/**
 *
 * @author Josh Holland
 */
public class SideViewRangedEnemy extends SideViewAI{
    
    /**
     * This integer is used for the delay until the AI can attack again.
     */
    private int attackDelay=0;
    
    /**
     * This sets up the enemy for use in the code. 
     * 
     * 
     * @param startHealth 
     * @param damageDealt how much damage the AI deals per attack
     * @param ai_id the ID of the AI that this class represents
     * @param startingX the x location, in location points, where the player 
     *      will reach in order for this AI to spawn
     */
    public SideViewRangedEnemy(int startHealth,int damageDealt,int ai_id,int startingX){
        AI_ID=ai_id;
        health=startHealth;
        damage=damageDealt;
        span=new Rectangle(startingX,20,10,30);//in location points
        images=GraphicsAssets.importSideViewRangedEnemyImages(AI_ID);
    }
    
    /**
     * This function tells if the AI should attack. 
     * 
     * @param playerX the player's x location
     * @param playerY the player's y location
     * @return if the AI should attack
     */
    @Override
    public boolean shouldAttack(int playerX,int playerY){ 
            //range of 3 tiles to start shooting
            if(span.x>playerX-60&&span.x<playerX+60){
                return true;
            } else
                return false;
    }
    
    
    /**
     * This function contains the flow for the AI's travel.
     * 
     * @param playerX the player's x location
     * @param playerY the player's y location
     */
    @Override
    public void travel(int playerX,int playerY){
        if(span.x<playerX-60//AI is to the left of range area
                ){
            if(velocity[0]<MAX_VELOCITY){//velocity is less than the max allowed velocity
                velocity[0]++;
            }
            facingRight=true;
            
        } else if(span.x>playerX+60//AI is to the right of the range area
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
        
        span.x+=velocity[0];
        span.y+=velocity[1];
    }
    
    /**
     * This function makes the AI start its attack. 
     */
    @Override
    public void startAttack(){
        if(canAttack){
            attacking=true;
            canAttack=false;
            attackDelay=0;
        }
    }
    
    /**
     * This function is for the flow of the end of the AI's attacking. 
     */
    @Override
    public void endAttack(){
        wasAttacking=false;
        attacking=false;
        meleeAttack=null;
    }
    
}
