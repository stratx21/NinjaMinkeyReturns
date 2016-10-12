/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.Timer;
/**
 *
 * @author Josh
 */
public class SideViewRunner extends GameRunner{
    
    private SideViewPlayer player=null;//will this have a different location every time? or just stick with one for every start?
    
    private Rectangle viewSpan=new Rectangle(0,0,340,180);//in terms of location points
    
    private SideViewRegion region=null;
    
    public boolean timed=false;
    
    public Timer timer=null;
    
    public SideViewRunner(){
        super();
    }
    
    public SideViewRunner(CListener dn){
        super(dn);
        region=new SideViewRegion(0); 
        setup();
    }
    
    public SideViewRunner(CListener dn,int currentRegion){
        super(dn);
        region=new SideViewRegion(currentRegion);
        setup();
    }
    
    private void setup(){
        player=new SideViewPlayer(new int[]{60,160}); 
        resetFont();
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g){
        region.draw(g);
        player.draw(g);
        
        playerKeysFlow();
    }
    
    private void playerKeysFlow(){
        if(currentKey[0]){//0-3 could be run by a loop? not necessarily better in this case except for code condensing ?
//            if(player.getX()
//                    &&region.canMoveToSpace(player.getX()/20,player.getY()/20-1)){
//                player.moveStart(0);
//            }
        }else if(currentKey[1]){//NOTE:: (KEEP) ALL of these are for STARTING moving (1 square)
//            if(region.canMoveToSpace(player.getX(),player.getY()+1))
//                player.moveStart(3);
        }else if(currentKey[2]){
//            if(region.canMoveToSpace(player.getX()-1,player.getY())){
//                player.moveStart(1);
//                System.out.println("move");
//            }
        }else if(currentKey[3]){
//            if(region.canMoveToSpace(player.getX()+1,player.getY()))
//                player.moveStart(2);
        }else if(currentKey[4]){//SETUP   *   future work
            
        }else if(currentKey[5]){//SETUP   *   future work
            
        }
    }
    
    private void checkPlayerCollisions(){
        int movingX=player.getX()+player.getXVelocity(),
                movingY=player.getY()+player.getYVelocity();
        if(region.canMoveToSpace(movingX,movingY)){//no collision with the current velocity setting
            player.move();
            playerKeysFlow();
        } else{//there is a collision with the current velocity setting
            
        }
    }
    
    /**
     *
     */
    @Override
    public void calculate(){
        
    }
    
    
    private void calculateViewSpan(){
        
    }
    
    
    @Override
    public void keyTypedFlow(char typed){
        
    }
    
    @Override
    public void keyPressedFlow(char typed){
        
    }
    
    @Override
    public void keyReleasedFlow(char typed){
        
    }
    
}
