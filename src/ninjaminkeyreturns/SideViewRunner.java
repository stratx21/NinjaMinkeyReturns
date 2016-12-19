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
    
    /**
     * The instance of the mission that is used for information for the flow of
     *  the side view. 
     */
    private SideViewMission mission=null;
    
    /**
     * The instance of SideViewPlayer that is used for the player that is in
     *  side view. 
     */
    private SideViewPlayer player=null;//will this have a different location every time? or just stick with one for every start?
    
    /**
     * This is practically the camera in how it is the range in which the 
     *  user is seeing the game; it will follow the player and move along the 
     *  region. 
     */
    private Rectangle camera=new Rectangle(0,0,340,180);//in terms of location points
    
    /**
     * This is the instance of the SideViewRegion that is used to manage the 
     *  region for side view. 
     */
    private SideViewRegion region=null;
    
    /**
     * This tells if the side view is timed. 
     */
    public boolean timed=false; // -duplicate - also in SideViewRegion
    
    /**
     * This is the timer that is used if the side view mission is timed. 
     */
    public Timer timer=null;
    
    /**
     * This sets up the SideViewRunner with no extra options. 
     */
    public SideViewRunner(){
        super();
    }
    
    /**
     * This sets up the SideViewRunner with a CListener that is used to stop
     *  the SideViewRunner and go back. 
     * 
     * @param dn the CListener for when the SideViewRunner is done
     */
    public SideViewRunner(CListener dn){
        super(dn);
        region=new SideViewRegion(0); 
        setup();
    }
    
    /**
     * This sets up the SideViewRunner with a CListener for when the 
     *  SideViewRunner is finished and to go back, and an integer telling
     *  this class what region ID it is running. 
     * 
     * @param dn the CListener instance for when SideViewRunner is done
     * @param currentRegion the ID for the SideViewRegion that is being run
     */
    public SideViewRunner(CListener dn,int currentRegion){
        super(dn);
        region=new SideViewRegion(currentRegion);
        setup();
    }
    
    /**
     * This sets up some of the SideViewRunner's aspects.
     */
    private void setup(){
        player=new SideViewPlayer(new int[]{60,20});
        Prompt.resetFont();
    }
    
    /**
     * This draws the graphical representation of the side view game. 
     *
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    @Override
    public void draw(Graphics g){
        region.draw(g,(int)camera.getX(),(int)camera.getY());
        player.draw(g,(int)camera.getX(),(int)camera.getY());
        
        playerCalcFlow();//keep last
    }
    
    /**
     * The flow for the player calculations.
     */
    private void playerCalcFlow(){
        //check if the palyer should fall::
        if(//!player.getJumping()&&
            region.canMoveToSpace(player.getX(),player.getY()+(int)player.span.getHeight()+player.getJumpVelocity())
                &&region.canMoveToSpace(player.getX()+(int)player.span.getWidth(),player.getY()+(int)player.span.getHeight()+player.getJumpVelocity()))//then should fall since it can go to the space below
            if(player.getYVelocity()<player.getJumpVelocity())
                player.incrementYVelocity(1);
            else 
                player.setYVelocity(player.getJumpVelocity());
        //System.out.println((int)player.span.getHeight());
        //after all velocities are set::
        int movingX=player.getX()+player.getXVelocity(),
                movingY=player.getY()+player.getYVelocity()+(int)player.span.getHeight();
        if(region.canMoveToSpace(movingX,movingY)){//no collision with the current velocity setting
            player.moveByVelocities();
            playerKeysFlow();
        } else{//there is a collision with the current velocity setting
            player.setXVelocity(0);
            player.setYVelocity(0);
            System.out.println("would be a collision with current veloicity");
        }
    }
    
    /**
     * The flow for the player for when each key is pressed. 
     */
    private void playerKeysFlow(){
        if(currentKey[0]){//0-3 could be run by a loop? not necessarily better in this case except for code condensing ?
            player.startJump();
        }else if(currentKey[1]){//down
            
        }else if(currentKey[2]){//left
            //check if can run in this direction!!
        }else if(currentKey[3]){//right
            
        }else if(currentKey[4]){//SETUP   *   future work
            
        }else if(currentKey[5]){//SETUP   *   future work
            
        }
    }
    
    /**
     * This function is used for the overall calculations that are run for the
     *  flow of SideViewRunner. 
     */
    @Override
    public void calculate(){// -------------------------- -  - - is this used?   < ?? ? ? ? ? ? ?   - -- -- -   >?
        
    }
    
    /**
     * This is used to calculate the flow of the AIs. 
     */
    private void AICalculate(){
        
    }
    
    /**
     * This calculates any changes in the visual span, the camera, so that it
     *  follows the player. 
     */
    private void calculateViewSpan(){
        
    }
    
    /**
     * This function is evoked any time a key is typed; it is used for the 
     *  flow whenever a certain key is typed. 
     * 
     * @param typed the character of the key typed
     */
    @Override
    public void keyTypedFlow(char typed){
        
    }
    
    
    /**
     * This function is evoked any time a key is pressed; it is used for the 
     *  flow whenever a certain key is pressed. 
     * 
     * @param typed the character of the key pressed
     */
    @Override
    public void keyPressedFlow(char typed){
        
    }
    
    /**
     * This function is evoked any time a key is released; it is used for the 
     *  flow whenever a certain key is released. 
     * 
     * @param typed the character of the key released
     */
    @Override
    public void keyReleasedFlow(char typed){
        
    }
    
}
