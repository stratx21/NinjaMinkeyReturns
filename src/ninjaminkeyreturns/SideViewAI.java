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
public class SideViewAI extends AI{
    
    public int MAX_VELOCITY=4;
    
    public boolean facingRight=false;
    
    public double health=100.0;
    
    public int[] velocity=new int[]{0,0};
    
    
    public void calculate(int playerX,int playerY){
        travel(playerX,playerY);
        if(shouldAttack(playerX,playerY)){
            if(location[0]<playerX)
                attackRight();
            else
                attackLeft();
        }
    }
    
//    public void calculateLeftOrRight(int playerX){
//        facingRight=location[0]<playerX;   
//    }
    
    public void travel(int playerX,int playerY){
        
    }
    
    public void attackLeft(){
        
    }
    
    public void attackRight(){
        
    }
    
    public boolean shouldAttack(int playerX,int playerY){
        return false;
    }
    
//    public byte shouldMoveTo(int playerX, int playerY){
//        return 0;
//    }
    
//    public boolean inRange(int playerX,int playerY){
//        return false;
//    }
    
    public void spawnProjectile(int startX,int startY,int xVelocity,int yVelocity){
        
    }
    
    public void spawnHitBox(int x,int y,int width,int height){
        
    }
    
    public boolean getFacingRight(){
        return facingRight;
    }
    
    public void setFacingRight(boolean a){
        facingRight=a;
    }
    
    public double getHealth(){
        return health;
    }
    
    public void setHealth(double a){
        health=a;
    }
    
}
