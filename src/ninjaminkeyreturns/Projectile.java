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
public class Projectile {
    
    private double damage=0;
    
    public double getDamage(){
        return damage;        
    }
    
    public void setDamage(double a){
        damage=a;
    }
    
    private int[] location=new int[2];
    
    public int getX(){
        return location[0];
    }
    
    public int getY(){
        return location[1];
    }
    
    public void incrementX(int a){
        location[0]+=a;
    }
    
    public void incrementY(int a){
        location[1]+=a;
    }
    
    public void setX(int a){
        location[0]=a;
    }
    
    public void setY(int a){
        location[1]=a;
    }
    
    public int[] getLocation(){
        return location;
    }
    
    private int[] velocity=new int[2];
    
    public int getXVelocity(){
        return velocity[0];
    }
    
    public int getYVelocity(){
        return velocity[1];
    }
    
    public int[] getVelocity(){
        return velocity;
    }
    
    public Projectile(double dmg,int[] position,int[] vel){
        location=position;
        damage=dmg;
        velocity=vel;
    }
    
}
