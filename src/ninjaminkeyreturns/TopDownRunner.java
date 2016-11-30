/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class TopDownRunner extends GameRunner{//in top down mode only one key can be noticed as held
    
    private TopDownPlayer player=null;//new TopDownPlayer(new int[]{19,20});
    
    private TopDownRegion region=null;
    
    private CListener AIdone=null;
    
    private ArrayList<String> promptShowing=null;
    
    public boolean showingPrompt=false;
    
    PlainPrompt talkingPrompt=null;
    
    /////////////////
    
    private TopDownAI focusedAI=null;
    
    public TopDownRunner(){
        super();
        setup(30,30);//this is likely to cause errors
    }
    
    public TopDownRunner(CListener dn,int playerStartX,int playerStartY,int currentRegion){
        super(dn);
        setup(playerStartX,playerStartY);
        region=new TopDownRegion(currentRegion);
    }
    
    /**
     * PRE:: SQUARE_SIZE has been set up in the set up in CPanel
     */
    private void setup(int playerStartX,int playerStartY){
        player=new TopDownPlayer(new int[]{playerStartX,playerStartY});
        Prompt.resetFont();
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g){
//        System.out.println("reached top down draw");
        region.draw(g,player.getX(),player.getY(),player.getOffCenterX(),player.getOffCenterY());
        player.draw(g);
        
        //calculate:: (may be moved into another recursion/timer later)::
        
//        player.calculate();
        
        if(player.finishedMoving){//finished moving to the square
            int a=region.getTriggerSpotHit(player.getX(),player.getY());
            if(a!=-1){
                player.setDisabled(true);
                TriggerSpot hit=region.getTriggerSpot(a);
                if(hit.toRegion){//is going to a different region
//                    System.out.println("to region????");
                    //move to another region....
                    
                } else if(hit.AI_Triggered>-1){
                    //call on the triggered AI
                    System.out.println("triggering AI from runner.... ");
                    focusedAI=region.triggerAI(hit.AI_Triggered,player.getX(),player.getY(),AIdone=new CListener(){
                        @Override
                        public void actionPerformed(byte facing){//after the AI has approached the player::
                            player.setDirectionFacing(facing);
                            showingPrompt=true;
                            
                        }
                    });
                    talkingPrompt=new PlainPrompt(StringTools.formatString(focusedAI.getBeforePrompt(),Prompt.font,(int)(GAME_SPAN.getWidth()*0.9)),
                    new CListener(){
                        @Override
                        public void actionPerformed(){
                            if(focusedAI.instantSideView){
                                Profile.playerLocation=new int[]{player.getX(),player.getY()};//set the coordinates so that the player can come back to them
                                done.actionPerformed(focusedAI.MISSION_GIVEN_ID);
                            } else{//the AI did not confront the player in a side view manner
                                player.setDisabled(false);
                                //other code.... . . .. .. ........................................................<<<<<<<<
                            }
                        }
                    });
                }
            }
            player.finishedMoving=false;
        }
        
        if(!player.getDisabled()){
            if(!player.travelling)
                playerKeysFlow();
            else
                player.continueMove();
            
        }else if(showingPrompt){//loop components for each redraw
            talkingPrompt.loopCalculate(currentKey[4]);
            talkingPrompt.draw(g);
        }
        
//      
    }
    
    //PRE: player.travelling is false (player is not already moving between tiles)
    //up, down, left, right, attack, other attack
    private void playerKeysFlow(){//may have other options soon for an alternate menu? may just use another panel for that though..
        //System.out.println("to playerkeysflow");
        if(currentKey[0]){//0-3 could be run by a loop? not necessarily better in this case except for code condensing ?
            if(region.canMoveToSpace(player.getX(),player.getY()-1))
                player.moveStart(0);
        }else if(currentKey[1]){//NOTE:: (KEEP) ALL of these are for STARTING moving (1 square)
            if(region.canMoveToSpace(player.getX(),player.getY()+1))
                player.moveStart(3);
        }else if(currentKey[2]){
            if(region.canMoveToSpace(player.getX()-1,player.getY())){
                player.moveStart(1);
//                System.out.println("move");
            }
        }else if(currentKey[3]){
            if(region.canMoveToSpace(player.getX()+1,player.getY()))
                player.moveStart(2);
        }//else if(currentKey[4]){//SETUP   *   future work
            
        //}else if(currentKey[5]){//SETUP   *   future work
            
        //}
        
        //***** NOTE:: would it be more efficient to pass the array of booleans thru, then
        // carry on the above process for keys inside the player class? (depends on future
        // changes)
    }
    
    private void moveToNewRegion(int newRegion){
        region.changeRegion(newRegion);
        
        //** code for fading out, then showing the next biome/region pic, then going back....
    }
    
    /**
     *
     */
    @Override
    public void calculate(){
        
    }
    
    
    //////////////////
    
//    @Override
//    public void keyTypedFlow(char typed){
//        
//    }
//    
//    /**
//     * PRE::
//     * @param typed 
//     */
//    @Override
//    public void keyPressedFlow(char typed){
////        System.out.println("key pressed:: "+typed);
//    }
//    
//    @Override
//    public void keyReleasedFlow(char typed){
//    }
    
}
