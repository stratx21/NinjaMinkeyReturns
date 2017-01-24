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
    
    /**
     * This integer is used for the delay until the AI can attack again.
     */
    private int delay=0;
//    private boolean delayReady=false;
    
    public SideViewAirEnemy(int startX,int startY,int ai_id){
        AI_ID=ai_id;
        images=GraphicsAssets.importSideViewAirEnemyImages(AI_ID);
        location=new int[]{startX,startY};
    }
    
    /**
     * This function tells if the AI should attack. 
     * 
     * @param playerX the player's x location
     * @param playerY the player's y location
     * @return if the AI should attack
     */
    @Override
    public boolean shouldAttack(int playerX,int playerY){ // never uses these two numbers to calculate... [inheritance complication]
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
        location[0]+=velocity[0];
        location[1]+=velocity[1]; // if the AIs cannot be hit (air ones) then this may not be needed?
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
    
}
