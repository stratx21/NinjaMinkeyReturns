/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Josh Holland
 */
public class TopDownRunner extends GameRunner{//in top down mode only one key can be noticed as held
    
    public static int lastTriggeredAI=-1;
    
    /**
     * The player instance that is used for the top down view. 
     */
    private TopDownPlayer player=null;//new TopDownPlayer(new int[]{19,20});
    
    /**
     * The region isntance that is used for the top down view. 
     */
    private TopDownRegion region=null;
    
    /**
     * The CListener that is evoked when the AI is done approaching the player. 
     */
    private CListener AIdone=null;
    
    /**
     * The ArrayList used for the prompt that is being displayed. 
     */
    private ArrayList<String> promptShowing=null;
    
    /**
     * This is for if it is showing a prompt. 
     */
    public boolean showingPromptTalking=false,
            showingPromptPause=false;
    
    /**
     * The prompt that is used to show strings when the AI talks to the player
     *  and has no extra options. 
     */
    PlainPrompt talkingPrompt=null;
    
    /**
     * The prompt used for the pause menu. 
     */
    NavigablePrompt pausePrompt=null;
    
    /////////////////
    
    /**
     * The AI that was triggered and is being talked to or intereacted with. 
     */
    private TopDownAI focusedAI=null;
    
    /**
     * This sets up the TopDownRunner with default settings. 
     */
    public TopDownRunner(){
        super();
        setup(30,30);//this is likely to cause errors
    }
    
    /**
     * This sets up the TopDownRunner. 
     * 
     * @param dn the CListener that is evoked when the TopDownRunner is done
     * @param playerStartX the x starting location for the player
     * @param playerStartY the y starting location for the player
     * @param currentRegion the current region ID to be managed
     */
    public TopDownRunner(CListener dn,int playerStartX,int playerStartY,int currentRegion,boolean showAfterPrompt){
        super(dn);
        setup(playerStartX,playerStartY);
        region=new TopDownRegion(currentRegion);
        Profile.lastKnownRegionTopDown=currentRegion;
        if(showAfterPrompt&&lastTriggeredAI>-1)
            comingBackStartPrompt();
        for(TopDownAI ai:region.AIs){//put in player info for all AIs
                ai.calcToGo(player.getX(),player.getY());
            }
    }
    
    /**
     * This function sets up the TopDownRunner.
     * 
     * PRE:: SQUARE_SIZE has been set up in the set up in CPanel
     * 
     * @param playerStartX the player's starting x location
     * @param palyerStartY the player's starting y location
     */
    private void setup(int playerStartX,int playerStartY){
        player=new TopDownPlayer(new int[]{playerStartX,playerStartY});
        Prompt.resetFont();
    }
    
    /**
     * This function starts up the prompt for the AI to speak to the user when
     *  coming back from a side view mission. 
     */
    private void comingBackStartPrompt(){
        player.setDisabled(true);
        showingPromptTalking=true;
        talkingPrompt=new PlainPrompt(StringTools.formatStringForPrompt(region.getAI(lastTriggeredAI).getAfterPrompt(),Prompt.font,(int)(GAME_SPAN.getWidth()*0.9)),
                    new CListener(){
                        @Override
                        public void actionPerformed(){
                            player.setDisabled(false);
                            showingPromptTalking=false;
                        }
                    });
    }
    
    /**
     * This function is used to draw the graphical representation of the game. 
     *
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     */
    @Override
    public void draw(Graphics g){
        
        
        //calculate:: (may be moved into another recursion/timer later)::
        
//        player.calculate();
        
        if(player.finishedMoving){//finished moving to the square
            triggeringFlow(region.getTriggerSpotHit(player.getX(),player.getY()));
            player.finishedMoving=false;
            for(TopDownAI ai:region.AIs){//put in player info for all AIs
                ai.calcToGo(player.getX(),player.getY());
            }
        }
        
        region.draw(g,player.getX(),player.getY(),player.getOffCenterX(),player.getOffCenterY());
        player.draw(g);
        
        if(!player.travelling)
            playerKeysFlow();
        else
            player.continueMove();
            
        if(showingPromptTalking){//loop components for each redraw
            talkingPrompt.loopCalculate(currentKey[4]);
            talkingPrompt.draw(g);
        } else if(showingPromptPause&&pausePrompt!=null){
            pausePrompt.loopCalculate(currentKey);
            if(pausePrompt!=null)
                pausePrompt.draw(g);
        }
        if(sequencePauseAgain>0)
            sequencePauseAgain--;
        
//      
    }
    
    
    
    /**
     * This function includes the flow for triggering a given AI. 
     * 
     * @param a the index of the AI to trigger 
     */
    private void triggeringFlow(int a){
        if(a!=-1){
                player.setDisabled(true);
                TriggerSpot hit=region.getTriggerSpot(a);
                if(hit.toRegion){//is going to a different region
                    //move to another region....
                    Profile.playerLocation=hit.getCoordsToGoTo();//set the new coordinates
                    done.actionPerformed(hit.regionToGoTo,true);
                    
                } else if(hit.AI_Triggered>-1){
                    //call on the triggered AI
                    lastTriggeredAI=hit.AI_Triggered;
                    ErrorLogger.logEvent("triggering AI from runner.... ");
                    focusedAI=region.triggerAI(hit.AI_Triggered,player.getX(),player.getY(),AIdone=new CListener(){
                        @Override
                        public void actionPerformed(byte facing){//after the AI has approached the player::
                            player.setDirectionFacing(facing);
                            showingPromptTalking=true;
                            
                        }
                    });
                    talkingPrompt=new PlainPrompt(StringTools.formatStringForPrompt(focusedAI.getBeforePrompt(),Prompt.font,(int)(GAME_SPAN.getWidth()*0.9)),
                    new CListener(){
                        @Override
                        public void actionPerformed(){
                            if(focusedAI.instantSideView){ //the top down view ends
                                Profile.playerLocation=new int[]{player.getX(),player.getY()};//set the coordinates so that the player can come back to them
                                done.actionPerformed(focusedAI.SIDEVIEW_REGION_TO_GO_TO_ID);
                            } else{//the AI did not confront the player in a side view manner
                                player.setDisabled(false);
                                //other code.... . . .. .. ........................................................<<<<<<<<
                            }
                        }
                    });
                }
            }
        
        
    }
    
    /**
     * This is used for when the user can pause again. 
     */
    private int sequencePauseAgain=0;
    
    /**
     * This function conducts the flow for the player based on what keys are
     *  being pressed. 
     * 
     * PRE: player.travelling is false (player is not already moving between tiles)
     * 
     */
    private void playerKeysFlow(){
        //note:: controls index order - up, down, left, right, attack, other attack
        
        if(currentKey[0]&&!player.getDisabled()){//0-3 could be run by a loop? not necessarily better in this case except for code condensing ?
            if(region.canMoveToSpace(player.getX(),player.getY()-1))
                player.moveStart(0);
        }else if(currentKey[1]&&!player.getDisabled()){//NOTE:: (KEEP) ALL of these are for STARTING moving (1 square)
            if(region.canMoveToSpace(player.getX(),player.getY()+1))
                player.moveStart(3);
        }else if(currentKey[2]&&!player.getDisabled()){
            if(region.canMoveToSpace(player.getX()-1,player.getY())){
                player.moveStart(1);
            }
        }else if(currentKey[3]&&!player.getDisabled()){
            if(region.canMoveToSpace(player.getX()+1,player.getY()))
                player.moveStart(2);
        }else if(currentKey[4]&&!player.getDisabled()){//select
            boolean a=false,b=false;
            switch(player.getDirectionFacing()){
                case 0: b=triggerAIFromKey(player.getX(),player.getY()-1);
                if(b){
                    player.setDisabled(true);
                    a=true;
                }
                    break;
                case 1: b=triggerAIFromKey(player.getX()-1,player.getY());
                if(b){
                    player.setDisabled(true);
                    a=true;
                }
                    break;
                case 2: b=triggerAIFromKey(player.getX()+1,player.getY());
                if(b){
                    player.setDisabled(true);
                    a=true;
                }
                    break;
                case 3: b=triggerAIFromKey(player.getX(),player.getY()+1);
                if(b){
                    player.setDisabled(true);
                    a=true;
                }
                    break;
            }
            
            
            if(!a){
                AudioAssets.play("Error");
            }
        }else if(currentKey[5]){//SETUP   *   future work
            
        } else if(currentKey[6]&&sequencePauseAgain==0){//pause
            if(!showingPromptPause){
                ErrorLogger.logEvent("Paused");
                sequencePauseAgain=10;
                currentKey[6]=false;
                showingPromptPause=true;
                player.setDisabled(true);
                setupPausePrompt();
            } else{// is showing the prompt already
                showingPromptPause=false;
                player.setDisabled(false);
                pausePrompt=null;
                sequencePauseAgain=10;
            }
        }
    }
    
    /**
     * This function sets up the pause prompt. 
     */
    private void setupPausePrompt(){
        pausePrompt=new PausePrompt(true,new CListener(){
                    @Override
                    public void actionPerformed(int choice){//when done
                        switch(choice){
                            case 0://resume
                                showingPromptPause=false;
                                pausePrompt=null;
                                sequencePauseAgain=0;
                                player.setDisabled(false);
                                break;
                            case 1://options
                                pausePrompt=new OptionsPrompt(false,new CListener(){
                                    @Override
                                    public void actionPerformed(int c){
                                        if(c==0){
                                            setupPausePrompt();
                                        }
                                    }
                                });
                                break;
                            case 2://quit
                                done.actionPerformed();
                                break;
                            case 3://save
                                showingPromptPause=false;
                                pausePrompt=null;
                                sequencePauseAgain=0;
                                Profile.save();
                                break;
                        }
                    }
                });
    }
    
    /**
     * This function triggers an AI based on the player hitting Select on a 
     *  certain spot. 
     * 
     * @param detectX the x position to detect at
     * @param detectY the y position to detect at
     * @return if an AI was triggered
     */
    private boolean triggerAIFromKey(int detectX,int detectY){
        int triggeredAI=region.getAIAtSpotInteger(detectX,detectY);
        
        if(triggeredAI!=-1&&triggeredAI<region.AIs.size()){
            TopDownAI ai=region.getAI(triggeredAI);
            //call on the triggered AI
            ErrorLogger.logEvent("triggering AI from runner (selected with SELECT function) .... ");
            focusedAI=region.triggerAI(triggeredAI,player.getX(),player.getY(),AIdone=new CListener(){
                @Override
                public void actionPerformed(byte facing){//after the AI has approached the player::
                    player.setDirectionFacing(facing);
                    showingPromptTalking=true;
                    
                }
            });
            talkingPrompt=new PlainPrompt(StringTools.formatStringForPrompt(focusedAI.getBeforePrompt(),Prompt.font,(int)(GAME_SPAN.getWidth()*0.9)),
                    new CListener(){
                        @Override
                        public void actionPerformed(){
                            if(focusedAI.instantSideView){ //the top down view ends
                                Profile.playerLocation=new int[]{player.getX(),player.getY()+2};//set the coordinates so that the player can come back to them
                                done.actionPerformed((int)focusedAI.SIDEVIEW_REGION_TO_GO_TO_ID);
                            } else{//the AI did not confront the player in a side view manner
                                player.setDisabled(false);
                                //other code.... . . .. .. ........................................................<<<<<<<<
                            }
                        }
                    });
            return true;
        } //no ai was triggered
        return false;
    }
    
    
    /**
     * This function gives the TopDownRunner to a new region to run. 
     * 
     * @param newRegion the Id for the new region
     */
    private void moveToNewRegion(int newRegion){//  - -- - - -- may be against using this
        region.changeRegion(newRegion);
        
        //** code for fading out, then showing the next biome/region pic, then going back....
    }
    
}
