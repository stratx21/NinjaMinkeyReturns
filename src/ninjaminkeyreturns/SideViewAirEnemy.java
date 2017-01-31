/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Rectangle;

/**
 *
 * @author Josh
 */
public class SideViewAirEnemy extends SideViewAI{
    
    /**
     * This integer is used for the delay until the AI can attack again.
     */
    private int delay=0;
//    private boolean delayReady=false;
    
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
    public SideViewAirEnemy(int startHealth,int damageDealt,int ai_id,int startingX){
        AI_ID=ai_id;
        health=startHealth;
        damage=damageDealt;
        span=new Rectangle(startingX,20,10,30);//in location points   < - span.width and height may need to be changed since these may be different sizes
        images=GraphicsAssets.importSideViewAirEnemyImages(AI_ID);
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
        if(delay>69){
            delay=0;
            return true;
        }
        delay++;
        return false;
    }
    
    /**
     * This function adds the velocity to the AI's location in order for the
     *  AI to travel.
     * 
     * @param playerX the player's x location
     * @param playerY the player's y location
     */
    @Override
    public void travel(int playerX,int playerY){
        span.x+=velocity[0];
        span.y+=velocity[1]; // if the AIs cannot be hit (air ones) then this may not be needed?
    }
    
    /**
     * This function makes the AI attack to the left. 
     */
    @Override
    public void attackLeft(){
        
    }
    
    /**
     * This function makes the AI attack to the right. 
     */
    @Override
    public void attackRight(){
        
    }
    
    @Override
    public int getJumpVelocity(){//so that the enemy does not fall from gravity
        return 0;
    }
    
}
