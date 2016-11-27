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
public class HitBox extends Rectangle{
    
    private int damage=0;
    
    public HitBox(int x,int y,int width,int height,int dmg){
        super(x,y,width,height);
        damage=dmg;
    }
    
    public void setDamage(int d){
        damage=d;
    }
    
    public int getDamage(){
        return damage;
    }
    
}
