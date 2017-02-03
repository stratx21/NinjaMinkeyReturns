/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
    
    private ArrayList<HitBox> playerAttacks=new ArrayList<>();
    
    private ArrayList<Projectile> AIProjectiles=new ArrayList<>();
    
    private ArrayList<SideViewAI> enemies=new ArrayList<>();
    
    
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
    private SideViewRegion region=new SideViewRegion(0);
    
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
    public SideViewRunner(CListener dn,int currentRegion){//////////////////////
        super(dn);
        region=new SideViewRegion(currentRegion);
        setup();
    }
    
    /**
     * This sets up some of the SideViewRunner's aspects.
     */
    private void setup(){
        player=new SideViewPlayer(new int[]{60,20});
        player.makeHitBox=new CListener(){
            @Override
            public void actionPerformed(HitBox a){
                playerAttacks.add(a);
            }
        };
        
        SideViewAI.spawnProjectile=new CListener(){
            @Override
            public void actionPerformed(Projectile b){
                AIProjectiles.add(b);
            }
        };
        
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
        
        //draw projectiles (player) ::
        for(int i=0;i<playerAttacks.size();i++){
            HitBox a=playerAttacks.get(i);
            if(a instanceof Projectile){
                a.draw(g,(int)camera.getX(),(int)camera.getY());
                if(a.getX()<0
                 ||a.getX()>region.getMapLength()
                 ||a.getY()>200
                 ||a.getY()<-10){
                    playerAttacks.remove(a);//remove player projectile if not on the screen anymore
                    i--;
                }
            }else{//is a hitbox also; but not a projectile
                if(!player.getAttacking()) //if player is not attacking then no hitbox
                    playerAttacks.remove(a);
                else{ // player is attacking
                    a.x+=player.getXVelocity();
                    a.y+=player.getYVelocity();
                }
            }
        }
        
        calculateAI(g);
        
        calculate();
    }
    
    private void calculateAI(Graphics g){
        if(region.AIs!=null)
        for(int i=0;i<region.AIs.size();i++){
            SideViewAI a=region.AIs.get(i);
            if(a!=null){
                //System.out.println(a.active+a.startX);
                if(a.active){
                    a.draw(g,(int)camera.getX(),(int)camera.getY());
                    a.calculate(player.getX(),player.getY());
                    AIDamagedCalc(a);
                    AICalcFlow(a);
                    
                    if(a.getHealth()<0){
                        if(a.getY()>-30){
                            a.setYVelocity(-7);
                            a.setXVelocity(a.facingRight?-6:6);
                        }
                        else{
                            removeAI(a);
                        }
                    } else if(a.getY()>200){
                        removeAI(a);
                    }
                    
                    if(a.meleeAttack!=null)
                        if(player.span.intersects(a.meleeAttack)){
                            player.hit(a.damage);
                            player.incrementYVelocity(-1);
                            player.incrementXVelocity((a.facingRight?1:-1));
                        }
                    
                    a.moveByVelocities();
                } else{
                    if(player.getX()>a.startX-100)
                        a.active=true;
                }
            }
        }
    }
    
    private void removeAI(SideViewAI a){
        region.AIs.remove(a);
        if(region.AIs.size()==0)
            done.actionPerformed(true);
    }
    
    private int stuck=0;
    
    private byte last=0;
    
    /**
     * The flow for the player calculations.
     */
    private void playerCalcFlow(){
        
        if(player.getHealth()<=0)
            done.actionPerformed(false);
        
        if(player.getXVelocity()!=0&&!currentKey[2]&&!currentKey[3]&&!player.getFalling())
            player.incrementXVelocity((player.getXVelocity()>0?-1:1));
        
        playerFallingCalc();
        
        //after all velocities are set::
        int movingX=player.getX()+player.getXVelocity(),
                movingY=player.getY()+player.getYVelocity();
        byte t;
        if(-1==(t=region.mapCollision(new Rectangle(movingX,movingY,player.getWidth(),player.getHeight())))){//no collision with the current velocity setting
            
            player.moveByVelocities();
            playerKeysFlow();
        } else{//there is a collision with the current velocity setting
//                         0   1
//                         2   3
//                         4   5
            //System.out.println("t == "+t+" stuck == "+stuck);
            switch(t){
                case 0: 
                    if(!region.canMoveToSpace(player.getX(),player.getY()+player.getYVelocity())){//if there is an obstacle above
                        if(player.getYVelocity()<0){ 
                            player.incrementY(-1*((player.getY()-10)%20+1));
                        }
                        player.setYVelocity(0);
                        while(!region.canMoveToSpace(player.getX(),player.getY()-1))
                            player.incrementY(1);
                        
                        if(last!=0)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>20){
                            player.incrementX(10);
                            player.incrementY(10);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        
                    } else{// is a side collision
                        //System.out.println("setting velocity[0] of player to 0 from a side collision");
                        player.setXVelocity(0);
                    }
                    break;
                case 1: 
                    if(!region.canMoveToSpace(player.getX()+player.getWidth(),player.getY()+player.getYVelocity())){//if there is an obstacle above
                        //System.out.println("is a vertical collision");
                        if(player.getYVelocity()<0){
                            player.incrementY(-1*((player.getY()-10)%20+1));
                        }
                        player.setYVelocity(0);
                        
                        if(last!=1)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>20){
                            player.incrementX(-10);
                            player.incrementY(10);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        
                        
//                        while(!region.canMoveToSpace(player.getX()+player.getWidth(),player.getY()-1))
//                            player.incrementY(1);
                    } else{// is a side collision
                        //System.out.println("is a side collision");
                        //System.out.println("setting velocity[0] of player to 0 from a side collision");
                        player.setXVelocity(-2);
                    }
                    break;
                case 2://left
                    player.setXVelocity(0);
                    if(region.canMoveToSpace(player.getX()+10,player.getY()+5+player.getYVelocity()))
                        player.incrementYVelocity(2);
                    
                    if(last!=2)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>15){
                            player.incrementX(4);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                //System.out.println("setting velocity[0] of player to 0 from a side collision");
                    break;
                case 3: //right
                    player.setXVelocity(0);
                    if(region.canMoveToSpace(player.getX()-10,player.getY()+5+player.getYVelocity()))
                        player.incrementYVelocity(2);
                    
                    if(last!=3)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>15){
                            player.incrementX(-4);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                //System.out.println("setting velocity[0] of player to 0 from a side collision");
                    break;
                case 4: 
                    if(!region.canMoveToSpace(player.getX(),player.getY()+player.getHeight()+player.getYVelocity())){//if there is an obstacle below that it would hit in this case (not a side collision)
                        if(player.getYVelocity()>0) player.incrementY(19-(player.getY()+10)%20);
                        player.setYVelocity(0);
//                        while(!region.canMoveToSpace(player.getX(),player.getY()+1))
//                            player.incrementY(-1);
                    } else{
                        player.setXVelocity(0);
                    }
                    break;
                case 5: 
                    if(!region.canMoveToSpace(player.getX()+player.getWidth(),player.getY()+player.getHeight()+player.getYVelocity())){//if there is an obstacle below that it would hit in this case (not a side collision)
                        if(player.getYVelocity()>0) player.incrementY(19-(player.getY()+10)%20);
                        player.setYVelocity(0);
                        while(!region.canMoveToSpace(player.getX()+player.getWidth(),player.getY()+1))
                            player.incrementY(-1);
                    } else{
                        player.setXVelocity(0);
                    }
                    break;
            }
            last=t;
//            zeroPlayerVelocity();
            
//            player.setXVelocity(0);
//            player.setYVelocity(0);
            //System.out.println("would be a collision with current veloicity");
        }
    }
    
    
    
    private void playerFallingCalc(){
        //check if the palyer should fall::
        if(//!player.getJumping()&&
            region.canMoveToSpace(player.getX(),player.getY()+(int)player.span.getHeight()+2)
                &&region.canMoveToSpace(player.getX()+(int)player.span.getWidth(),player.getY()+(int)player.span.getHeight()+2)){
                    //then should fall since it can go to the space below
            
            if(player.getYVelocity()<player.getJumpVelocity())
                player.incrementYVelocity(1);
            else 
                player.setYVelocity(player.getJumpVelocity());
            
            if(player.getYVelocity()==0)
                player.setJumping(false);
            
            player.setFalling(true);
        } else{
            player.endFall();
        }
        //System.out.println((int)player.span.getHeight());
    }
    
    
    private void AIDamagedCalc(SideViewAI AI){
        for(HitBox a:playerAttacks){
            if(AI.span.intersects(a)){//AI was hit by this hitbox
                AI.hit(a.getDamage());
                if(a instanceof Projectile){
                    AI.setYVelocity(-2);
                    AI.incrementXVelocity(((Projectile)a).getXVelocity()/2);
                } else{//is a plain hitbox; not a Projectile
                    AI.setYVelocity(-2);
                    AI.setXVelocity(6*(player.getAttackingRight()?1:-1));
                }
            }
        }
    }
    
    private void AICalcFlow(SideViewAI AI){
        
        
        AIFallingCalc(AI);
        
        //after all velocities are set::
        int movingX=AI.getX()+AI.getXVelocity(),
                movingY=AI.getY()+AI.getYVelocity();
        byte t;
        if(-1==(t=region.mapCollision(new Rectangle(movingX,movingY,AI.getWidth(),AI.getHeight())))){//no collision with the current velocity setting
            
            //AI.moveByVelocities();
        } else{//there is a collision with the current velocity setting
//                         0   1
//                         2   3
//                         4   5
            //System.out.println("t == "+t+" stuck == "+stuck);
            
            System.out.println(" t = "+t+" stuck = "+stuck+" AI:: "+AI+" v:: "+AI.getXVelocity()+","+AI.getYVelocity());
            switch(t){
                case 0: 
                    if(!region.canMoveToSpace(AI.getX(),AI.getY()+AI.getYVelocity())){//if there is an obstacle above
                        if(AI.getYVelocity()<0){ 
                            AI.incrementY(-1*((AI.getY()-10)%20+1));
                        }
                        AI.setYVelocity(0);
                        while(!region.canMoveToSpace(AI.getX(),AI.getY()-1))
                            AI.incrementY(1);
                        
                        if(last!=0)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>20){
                            AI.incrementX(10);
                            AI.incrementY(10);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        
                    } else{// is a side collision
                        //System.out.println("setting velocity[0] of player to 0 from a side collision");
                        System.out.println("set to 0 from 0");
                        AI.setXVelocity(1);
                    }
                    break;
                case 1: 
                    if(!region.canMoveToSpace(AI.getX()+AI.getWidth(),AI.getY()+AI.getYVelocity())){//if there is an obstacle above
                        //System.out.println("is a vertical collision");
                        if(AI.getYVelocity()<0){
                            AI.incrementY(-1*((AI.getY()-10)%20+1));
                        }
                        AI.setYVelocity(0);
                        
                        if(last!=1)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>20){
                            AI.incrementX(-10);
                            AI.incrementY(10);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        
                        
//                        while(!region.canMoveToSpace(player.getX()+player.getWidth(),player.getY()-1))
//                            player.incrementY(1);
                    } else{// is a side collision
                        //System.out.println("is a side collision");
                        //System.out.println("setting velocity[0] of player to 0 from a side collision");
                        AI.setXVelocity(-1);
                    }
                    break;
                case 2: AI.setXVelocity(1);
                
                if(last!=2&&last!=3)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>15){
                            AI.incrementX(4);
                            AI.incrementY(-4);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                //System.out.println("setting velocity[0] of player to 0 from a side collision");
                    break;
                case 3: AI.setXVelocity(-1);
                
                if(last!=2&&last!=3)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>15){
                            AI.incrementX(-4);
                            AI.incrementY(-4);
                            stuck=0;
                            //System.out.println("fixed stuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                //System.out.println("setting velocity[0] of player to 0 from a side collision");
                    break;
                case 4: 
                    if(!region.canMoveToSpace(AI.getX(),AI.getY()+AI.getHeight()+AI.getYVelocity())){//if there is an obstacle below that it would hit in this case (not a side collision)
                        if(AI.getYVelocity()>0) AI.incrementY(19-(AI.getY()+10)%20);
                        AI.setYVelocity(0);
//                        while(!region.canMoveToSpace(player.getX(),player.getY()+1))
//                            player.incrementY(-1);
                    } else{
                        AI.setXVelocity(0);
                    }
                    
                     if(last!=4&&last!=5)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>20){
                            AI.incrementX(10);
                            AI.incrementY(-10);
                            stuck=0;
                            System.out.println("12458072365#@$#^%#@$%#@^$#@^% fixed stuck!!!!! -4 ");
                        }
                    
                    break;
                case 5: 
                    if(!region.canMoveToSpace(AI.getX()+AI.getWidth(),AI.getY()+AI.getHeight()+AI.getYVelocity())){//if there is an obstacle below that it would hit in this case (not a side collision)
                        if(AI.getYVelocity()>0) AI.incrementY(19-(AI.getY()+10)%20);
                        AI.setYVelocity(0);
                        while(!region.canMoveToSpace(AI.getX()+AI.getWidth(),AI.getY()+1))
                            AI.incrementY(-1);
                    } else{
                        AI.setXVelocity(0);
                    }
                    
                    
                    if(last!=4&&last!=5)
                            stuck=0;
                        
                        stuck++;
                        
                        if(stuck>20){
                            AI.incrementX(-10);
                            AI.incrementY(-10);
                            stuck=0;
                            System.out.println("12458072365#@$#^%#@$%#@^$#@^% fixed stuck!!!!! -5 ");
                        }
                        
                        
                    break;
            }
            
            last=t;
//            zeroPlayerVelocity();
            
//            player.setXVelocity(0);
//            player.setYVelocity(0);
            //System.out.println("would be a collision with current veloicity");
        }
    }
    
    private void AIFallingCalc(SideViewAI AI){
        //check if the palyer should fall::
        if(//!player.getJumping()&&
            region.canMoveToSpace(AI.getX(),AI.getY()+(int)AI.getHeight()+2)
                &&region.canMoveToSpace(AI.getX()+(int)AI.getWidth(),AI.getY()+(int)AI.getHeight()+2)){
                    //then should fall since it can go to the space below
            
            if(AI.getYVelocity()<AI.getJumpVelocity())
                AI.incrementYVelocity(1);
            else 
                AI.setYVelocity(AI.getJumpVelocity());
            
//            if(AI.getYVelocity()==0)
//                AI.setJumping(false);
            
            //AI.setFalling(true);
        } else{
            //AI.endFall();
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
            if(player.getXVelocity()>-1*player.getWalkVeloctiy()){
                player.incrementXVelocity(-1);
                //System.out.println("starting to left.................");
            }
            player.facingRight=false;
        }else if(currentKey[3]){//right
            if(player.getXVelocity()<player.getWalkVeloctiy()){
                player.incrementXVelocity(1);
                //System.out.println("starting to right.................");
            }
            player.facingRight=true;
        }else if(currentKey[4]){//attack (melee with swords)                        playerAttacks
            if(player.getCanAttack()){
                player.startAttack(true);
                playerAttacks.add(new HitBox(//all in location points
                        player.getX()+(player.getFacingRight()?player.getWidth():0),
                        player.getY()+player.getHeight(),
                        15,
                        40,
                        player.getMeleeDamage())
                    );
            }
        }else if(currentKey[5]){//attack (ranged with banana)
            if(player.getCanAttack()){
                player.startAttack(false);
                playerAttacks.add(new Projectile(
                        player.getX()+player.getWidth(),     //locaiton points
                        player.getY()+player.getHeight()*3/4,//locaiton points
                        5,
                        5,
                        player.getRangedDamage(),
                        new int[]{player.getFacingRight()?5:-5,-5},
                        player.getProjectileImage()
                ));
            }
        }
    }
    
    /**
     * This function is used for the overall calculations that are run for the
     *  flow of SideViewRunner. 
     */
    @Override
    public void calculate(){// -------------------------- -  - - is this used?   < ?? ? ? ? ? ? ?   - -- -- -   >?
//        calculateAI();
        
        playerCalcFlow();
        
        cameraPlacementCalculations();
        
        
        
    }
    
    /**
     * This is used to calculate the flow of the AIs. 
     */
    private void AICalculate(){
        for(SideViewAI a:enemies){//add in also hitbox collisions
            
        }
    }
    
    /**
     * This calculates any changes in the visual span, the camera, so that it
     *  follows the player. 
     * 
     * PRE:: this is called after the player calculations for collisions and 
     *  other interactions are made
     */
    private void cameraPlacementCalculations(){
        //assuming that the perspective concerning the y-axis does not change
        //thus this only works with the x component 
        int t;
        if((t=(player.getX()-(int)camera.getX()))<80&&player.getXVelocity()<0
                ||t>240&&player.getXVelocity()>0){//then there should be a change
            
        
            if(camera.x+player.getXVelocity()<0)
                camera.x=0;
            else if(camera.x+camera.getWidth()+player.getXVelocity()>region.getX_TILES()*20){//number of tiles in the x direction times location points per tile
                camera.x=region.getX_TILES()*20-(int)camera.getWidth();
            } else{//the camera movement will work
                camera.x+=player.getXVelocity()/2;
            }
        }
        
        //System.out.println("CAMERA:: x: "+camera.x+"  y: "+camera.y+"  PLAYER VELOCITY:: "+player.getXVelocity());
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
    }
    
    /**
     * This function is evoked any time a key is released; it is used for the 
     *  flow whenever a certain key is released. 
     * 
     * @param typed the character of the key released
     */
    @Override
    public void keyReleasedFlow(char typed){
        super.keyReleasedFlow(typed);
        if(typed==controls[2]||typed==controls[3])
            player.setXVelocity(0);
    }
    
}
