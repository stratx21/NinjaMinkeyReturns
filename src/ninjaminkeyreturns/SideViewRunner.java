/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import static ninjaminkeyreturns.GameRunner.controls;
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
        
        
        
        playerFallingCalc();
        
        //after all velocities are set::
        int movingX=player.getX()+player.getXVelocity(),
                movingY=player.getY()+player.getYVelocity();
        byte t;
        if(-1==(t=region.mapCollision(new Rectangle(movingX,movingY,player.getWidth(),player.getHeight())))){//no collision with the current velocity setting
            
            player.moveByVelocities();
            playerKeysFlow();
        } else{//there is a collision with the current velocity setting
//     *                    0   1
//     *                    2   3  
//     *                    4   5
            
            switch(t){
                case 0: //if(player.getXVelocity()>0)
                    break;
                case 1: 
                    break;
                case 2: 
                    break;
                case 3: 
                    break;
                case 4: if(player.getYVelocity()>0) player.incrementY(19-player.getY()%20);
                    break;
                case 5: if(player.getYVelocity()>0) player.incrementY(19-player.getY()%20);
                    break;
            }
//            zeroPlayerVelocity();
            
            player.setXVelocity(0);
            player.setYVelocity(0);
            System.out.println("would be a collision with current veloicity");
        }
    }
    
//    private void zeroPlayerVelocity(){
//        player.setXVelocity(0);
//        player.setYVelocity(0);
//    }
    
    
    
    private void playerFallingCalc(){
        //check if the palyer should fall::
        if(//!player.getJumping()&&
            region.canMoveToSpace(player.getX(),player.getY()+(int)player.span.getHeight()+2)
                &&region.canMoveToSpace(player.getX()+(int)player.span.getWidth(),player.getY()+(int)player.span.getHeight()+2)){//then should fall since it can go to the space below
            if(player.getYVelocity()<player.getJumpVelocity())
                player.incrementYVelocity(1);
            else 
                player.setYVelocity(player.getJumpVelocity());
            player.setFalling(true);
        } else{
            player.endFall();
        }
        //System.out.println((int)player.span.getHeight());
    }
    
    private byte sequence=0;
    
    /**
     * The flow for the player for when each key is pressed. 
     */
    private void playerKeysFlow(){
        if(currentKey[0]){//0-3 could be run by a loop? not necessarily better in this case except for code condensing ?
            player.startJump();
        }else if(currentKey[1]){//down
            
        }else if(currentKey[2]){//left
            if(player.getXVelocity()>-1*player.getWalkVeloctiy())
                player.incrementXVelocity(-1);
            player.facingRight=false;
        }else if(currentKey[3]){//right
            if(player.getXVelocity()<player.getWalkVeloctiy())
                player.incrementXVelocity(1);
            player.facingRight=true;
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
     * This function is called whenever a key is pressed and will use the 
     *  information given about which key was pressed to determine what to do.
     * 
     * @param e the KeyEvent instance used to determine which key was pressed
     */
    @Override
    public void keyPressed(KeyEvent e){
        //        System.out.println("key released:: "+typed);
        char typed=Character.toUpperCase(e.getKeyChar());
        if(typed==controls[0]){
            currentKey[0]=true;
        }else if(typed==controls[1]){
            currentKey[1]=true;
        }else if(typed==controls[2]){
            currentKey[2]=true;
        }else if(typed==controls[3]){
            currentKey[3]=true;
        }else if(typed==controls[4]){
            currentKey[4]=true;
        }else if(typed==controls[5]){
            currentKey[5]=true;
        }
        keyPressedFlow(typed);
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
        player.setXVelocity(0);
    }
    
}
