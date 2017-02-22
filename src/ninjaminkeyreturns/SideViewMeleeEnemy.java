/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Josh Holland
 */
public class SideViewMeleeEnemy extends SideViewAI{
    
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
    public SideViewMeleeEnemy(int startHealth,int damageDealt,int ai_id,int startingX){
        AI_ID=ai_id;
        health=startHealth;
        damage=damageDealt;
        startX=startingX;
        span=new Rectangle(startingX,20,10,30);//in location points
        images=GraphicsAssets.importSideViewMeleeEnemyImages(AI_ID);
        
    }
    
    /**
     * This function draws the visual representation of this object. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     * @param camX the x location of the camera (location points)
     * @param camY the y location of the camera (location points)
     */
    @Override
    public void draw(Graphics g,int camX,int camY){
        
        if(!canAttack){
            if(attackDelay==75){
                attackDelay=0;
                canAttack=true;
            } else
                attackDelay++;       
        }
        
        if(attacking){
            if(wasAttacking){
                if(sequence==20){//7 images, 21 for max
                    sequence=0;
                    endAttack();
                }else
                    sequence++;
            }else{
                sequence=0;
                wasAttacking=true;
            }
            
            g.drawImage(images.get(6+(facingRight?0:7)+sequence/3),
                (int)((span.x-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                (int)((span.y-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                SQUARE_SIZE*2,SQUARE_SIZE*2,null);  
            
        } else{//is travelling
            if(sequence==14)//3 images, 5 for max
                sequence=0;
            else if(velocity[0]!=0)
                sequence++;
            
//            g.setColor(Color.blue);
//            g.fillRect((int)((span.x-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
//                (int)((span.y-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
//                SQUARE_SIZE*2,SQUARE_SIZE*2);
            
            g.drawImage(images.get((facingRight?0:3)+sequence/5),
                (int)((span.x-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                (int)((span.y-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                SQUARE_SIZE*2,SQUARE_SIZE*2,null);
            
            //g.drawImage(images.get(0),0,0,200,200,null);
            
//            System.out.println((int)((span.x-camX))+"  "+
//                (int)((span.y-camY-10))+"   "+
//                SQUARE_SIZE*2+"   "+SQUARE_SIZE*2+"   "+velocity[0]);
        }
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
            if(span.x>playerX-25&&span.x<playerX+25){//extra range of 10 which it detects but cannot reach on each side
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
        //System.out.println(health);
//        System.out.println(attacking);
        
        
        if(span.x<playerX+10//AI is to the left of range area
                ){
            if(velocity[0]<MAX_VELOCITY){//velocity is less than the max allowed velocity
                velocity[0]++;
            }
            facingRight=true;
            
        } else if(span.x>playerX+10//AI is to the right of the range area
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
//        if(velocity[0]!=0)
//            velocity[0]=MAX_VELOCITY*(velocity[0]>0?1:-1);
        
        span.x+=velocity[0];
        span.y+=velocity[1];
        
    }
    
    
//    /**
//     * This function makes the AI attack to the left. 
//     */
//    @Override
//    public void attackLeft(){
//        
//    }
    
    
    /**
     * This function makes the AI start its attack. 
     */
    @Override
    public void startAttack(){
        if(canAttack){
            attacking=true;
            canAttack=false;
            meleeAttack=new HitBox(getX()+(facingRight?-5:-15),
                    getY(),
                    20,
                    40,
                    damage);
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
