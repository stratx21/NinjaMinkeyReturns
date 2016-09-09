/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Josh
 */
public class TopDownRunner extends GameRunner{//in top down mode only one key can be noticed as held
    
    
    public static int SQUARE_SIZE=0;
    
    private TopDownPlayer player=new TopDownPlayer(new int[]{19,20});
    
    private TopDownRegion region=new TopDownRegion(0);
    
    private CListener AIdone=null;
    
    private boolean[] currentKey=new boolean[6];//up, down, left, right, attack, other attack
    
    public TopDownRunner(){
        super();
        setup();
    }
    
    public TopDownRunner(CListener dn){
        super(dn);
        setup();
    }
    
    /**
     * PRE:: SQUARE_SIZE has been set up in the set up in CPanel
     */
    private void setup(){
        Region.SQUARE_SIZE=TopDownAI.SQUARE_SIZE=GameRunner.SQUARE_SIZE=player.SQUARE_SIZE=SQUARE_SIZE;//
        Region.GAME_SPAN=TopDownAI.GAME_SPAN=player.GAME_SPAN=CPanel.GAME_SPAN;
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
//                    System.out.println("triggering AI from runner.... ");
                    region.triggerAI(hit.AI_Triggered,player.getX(),player.getY(),AIdone=new CListener(){
                        @Override
                        public void actionPerformed(){
                            
                        }
                    });
//                    player.forceLookUp();
                }
            }
            player.finishedMoving=false;
        }
        
        if(!player.getDisabled()){
            if(!player.travelling)
                playerKeysFlow();
            else
                player.continueMove();
        }
        
//        System.out.println(player.getX()+","+player.getY());
        
        //System.out.println("painting!");
    }
    //PRE:not travelling
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
            if(region.canMoveToSpace(player.getX()-1,player.getY()))
                player.moveStart(1);
        }else if(currentKey[3]){
            if(region.canMoveToSpace(player.getX()+1,player.getY()))
                player.moveStart(2);
        }else if(currentKey[4]){//SETUP   *   future work
            
        }else if(currentKey[5]){//SETUP   *   future work
            
        }
        
        //***** NOTE:: would it be more efficient to pass the array of booleans thru, then
        // carry on the above process for keys inside the player class? (depends on future
        // changes)
    }
    
    private void moveToNewRegion(int newRegion){
        region.changeRegion(newRegion);
        
        //** code for fading out, then showing the next biome/region pic, then going back....
    }
    
    
    ///////////////////
    
    private void AIApproachPlayer(){
        
    }
    
    /**
     *
     */
    @Override
    public void calculate(){
        
    }
    
    
    //////////////////
    
    @Override
    public void keyTypedFlow(char typed){
        
    }
    
    /**
     * PRE::
     * @param typed 
     */
    @Override
    public void keyPressedFlow(char typed){
//        System.out.println("key pressed:: "+typed);
        if(typed==controls[0]){
            currentKey[0]=true;
            setOtherKeysFalse(0);//in here to change it only if it is a valid key
        }else if(typed==controls[1]){
            currentKey[1]=true;
            setOtherKeysFalse(1);
        }else if(typed==controls[2]){
            currentKey[2]=true;
            setOtherKeysFalse(2);
        }else if(typed==controls[3]){
            currentKey[3]=true;
            setOtherKeysFalse(3);
        }else if(typed==controls[4]){
            currentKey[4]=true;
            setOtherKeysFalse(4);
        }else if(typed==controls[5]){
            currentKey[5]=true;
            setOtherKeysFalse(5);
        }
    }
    
    @Override
    public void keyReleasedFlow(char typed){
//        System.out.println("key released:: "+typed);
        if(typed==controls[0]){
            currentKey[0]=false;
        }else if(typed==controls[1]){
            currentKey[1]=false;
        }else if(typed==controls[2]){
            currentKey[2]=false;
        }else if(typed==controls[3]){
            currentKey[3]=false;
        }else if(typed==controls[4]){
            currentKey[4]=false;
        }else if(typed==controls[5]){
            currentKey[5]=false;
        }
    }
    
    private void setOtherKeysFalse(int doNotChange){
        for(int i=0;i<6;i++)
            if(i!=doNotChange)
                currentKey[i]=false;
    }
    
}
