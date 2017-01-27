/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Josh
 */
public class SideViewPlayer extends Player{
    
    /**
     * The span in which the player is. 
     */
    public Rectangle span=new Rectangle();
    
    /**
     * The maximum velocities for their respective types. 
     */
    private final int WALK_VELOCITY=4,JUMP_VELOCITY_START=7;//location points per loop
    
    /**
     * This multiplier is used to translate location points to pixels. 
     */
    private final double POINT_TO_PIXEL_MULTIPLIER=SQUARE_SIZE/20.0;
    
    /**
     * The velocity, in x,y formatting, of the player that is measured in
     *  location points. 
     */
    private int[] velocity=new int[]{0,0};
    
    /**
     * This tells if the player is facing towards the right. 
     */
    public boolean facingRight=true,
            wasFacingRight=true;
    
    /**
     * This is used to tell how much damage is dealt per each melee attack. 
     */
    private double meleeDamage=10;
    
    /**
     * This is used to tell how much damage is dealt per each ranged attack. 
     */
    private double rangedDamage=10;
    
    /**
     * This tells if the player can jump (in case they are falling or disabled).
     */
    private boolean canJump=true;
    
    private boolean falling=false;
    
    /**
     * This tells if the player is currently jumping.
     */
    private boolean jumping=false,
            wasJumping=false; 
    
    /**
     * This tells if the player is currently running. 
     */
    private boolean running=false;
    
    /**
     * These give information about the attacking state.
     */
    private boolean attacking=false,
            shooting=false,
            canAttack=true,
            wasAttacking=false,
            attackingRight=true;
    
    /**
     * This is used for the logic flow of the graphical representation by 
     *  determining the value of the index that is used to paint the proper
     *  image out of the sprite sequence. 
     */
    private int imageSequence=0;
    
    /**
     * This sets up the SideViewPlayer with an initial starting location. 
     * 
     * @param loc the location at which the player starts (in location points)
     */
    public SideViewPlayer(int[] loc){
//        super(loc);//sets the variable location, unless more changes are made
        images=GraphicsAssets.importSideViewPlayerImages();//import the images
        span=new Rectangle(loc[0],loc[1],10,30);//NOTE :: 0.75* 2 tiles for height
    }
    
    /**
     * This sets up the SideViewPlayer with an initial location and a size.
     * 
     * @param loc the starting location of the player (in location points)
     * @param spanX the width of the player (in location points)
     * @param spanY the height of the player (in location points)
     */ 
//    public SideViewPlayer(int[] loc,int spanX,int spanY){
//        this(loc);
//        span=new Rectangle((int)span.getX(),(int)span.getY(),spanX,spanY);
//        
//    }
    
    /**
     * This draws the SideViewPlayer.
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game. 
     * @param camX the x location of the camera
     * @param camY the y location of the camera
     */
    public void draw(Graphics g,int camX,int camY){
        //System.out.println("at player drawWWWWwwWWWWwWWwwW!");
        //System.out.println(GAME_SPAN.width+","+GAME_SPAN.height+"  square size:: "+SQUARE_SIZE+"  MULTIPLIER:: "+POINT_TO_PIXEL_MULTIPLIER);
        
//        g.setColor(Color.blue);
//        g.fillRect((int)((span.getX()-camX+10)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
//                (int)((span.getY()-camY-5)*(POINT_TO_PIXEL_MULTIPLIER)),
//                (int)(span.width*POINT_TO_PIXEL_MULTIPLIER),
//                (int)(span.height*POINT_TO_PIXEL_MULTIPLIER));
        
        
        if(attacking||shooting){
            if(attackingRight)//becuase on the images the player is farther to the left on the facing right images but farther to the right on the facing left images
                g.drawImage(images.get((32+((shooting?30:0)+imageSequence/3))),//40 cuz it compensates for the facing right already being incorperated
                        (int)((span.getX()-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                        (int)((span.getY()-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                        (int)(SQUARE_SIZE*2.5),SQUARE_SIZE*2,null); 
            else
                g.drawImage(images.get(47+((shooting?30:0)+imageSequence/3)),//30 cuz two sets of left AND right images for the melee attacks
                        (int)((span.getX()-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE),
                        (int)((span.getY()-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                        (int)(SQUARE_SIZE*2.5),SQUARE_SIZE*2,null);
            
//            System.out.println(t+" "+imageSequence);
            
            if(imageSequence==44){
                imageSequence=0;
                endAttack();
            }else
                imageSequence++;
            
            //System.out.println(imageSequence);
                
            
        }else if(velocity[0]==0&&velocity[1]==0){
            g.drawImage(images.get(facingRight?0:8),
                    (int)((span.getX()-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                    (int)((span.getY()-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                    SQUARE_SIZE*2,
                    SQUARE_SIZE*2,
                    null); 
            
        } else{ //travelling
            g.drawImage(images.get((facingRight?0:8)+(jumping?16:0)+imageSequence/5),
                (int)((span.getX()-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                (int)((span.getY()-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                SQUARE_SIZE*2,SQUARE_SIZE*2,null);  
            if(imageSequence==39//just before 40; just before 8th 
                    ||(wasFacingRight!=facingRight)
                    ||(wasJumping!=jumping))
                imageSequence=0;
            else
                imageSequence++;
            wasFacingRight=facingRight;
            wasJumping=jumping;
        }
            
        
        //System.out.println("PLAYER:: x: "+(span.getX())+"  y: "+(span.getY())+"  xV: "+velocity[0]+"  yV: "+velocity[1]+"  jumping: "+jumping+" PLAYEER VELOCITY:: "+velocity[0]);
        
    }
    
    /**
     * This function adds the velocity to the location of the player. 
     */
    public void moveByVelocities(){
        span.x+=velocity[0]/2;
        span.y+=velocity[1]/2;
    }
    
    /**
     * This function contains the flow to start a jump. 
     */
    public void startJump(){
        if(canJump){//       remove later if not needed?  - -- -- - - -- - - - -- -
            setCanJump(false);
            jumping=true;
            velocity[1]=-2*JUMP_VELOCITY_START;
            if(!attacking&&!shooting)
                imageSequence=0;
        }
    }
    
    /**
     * This function will set the according variables for the start of an
     *  attack, either melee or ranged. 
     * 
     * DEV NOTE:: the hitbox information could be moved to the SideViewPlayer 
     *  class, then it could be returned from a function or accessed from an 
     *  external call.
     * 
     * PRE:: canAttack is checked to be true
     * 
     * @param isMelee if the attack is a melee type (with the swords); if it is
     *      false then it is a ranged attack (with the banana)
     */
    public void startAttack(boolean isMelee){
        if(isMelee)
            attacking=true;
        else
            shooting=true;

        //for both melee and ranged:: 
        canAttack=false;
        attackingRight=facingRight;
        imageSequence=0;
        
        System.out.println(facingRight+" = facingRight");
        
    }
    
    /**
     * This function will set the according variables for the end of an
     *  attack, either melee or ranged. 
     */
    public void endAttack(){
        canAttack=true;
        shooting=false;
        attacking=false;
        wasAttacking=false;
        //attackingRight does not affect the start or end flow; it is just used, thus it is not changed here. 
    }
    
    ////////////////////
    
    /**
     * This function returns the max walking velocity.
     * 
     * @return the max walking velocity
     */
    public int getWalkVeloctiy(){
        return WALK_VELOCITY;
    }
    
    /**
     * This function returns the starting jumping velocity.
     * 
     * @return the starting jump velocity
     */
    public int getJumpVelocity(){
        return JUMP_VELOCITY_START;
    }
    
    /**
     * This returns the value of x from the location array to tell where the 
     *  player is.
     * 
     * @return the x coordinate of the player's location
     */
    public int getX(){
        return (int)span.getX();
    }
    
    /**
     * This function returns the boolean concerning if the player is attacking. 
     * 
     * @return the boolean concerning if the player is attacking
     */
    public boolean getAttacking(){
        return attacking;
    }
    
    /**
     * This function sets the boolean value for if the player is attacking using
     *  the melee attack. 
     * 
     * @param a if the player is attacking
     */
    public void setAttacking(boolean a){
        attacking=a;
    }
    
    /**
     * This function will return the boolean value concerning if the player
     *  is shooting. 
     * 
     * @return if the player is shooting
     */
    public boolean getshooting(){
        return shooting;
    }
    
    /**
     * This function sets the boolean concerning if the player is shooting. 
     * 
     * @param a if the variable should be set to true
     */
    public void setshooting(boolean a){
        shooting=a;
    }
    
    /**
     * This function returns the boolean value concerning if the player can
     *  attack. 
     * 
     * @return if the player can attack
     */
    public boolean getCanAttack(){
        return canAttack;
    }
    
    /**
     * This function sets the boolean value concerning if the player can
     *  attack. 
     * 
     * @param a if the player can attack
     */
    public void setCanAttack(boolean a){
        canAttack=a;
    }
    
    /**
     * This returns the value of y from the location array to tell where the
     *  player is.
     * 
     * @return the y coordinate of the player's location
     */
    public int getY(){
        return (int)span.getY();
    }
    
    /**
     * This function sets the x location of the player. 
     * 
     * @param a the new x location of the player
     */
    public void setX(int a){
        span.x=a;
    }
    
    /**
     * This function sets the y location of the player. 
     * 
     * @param a the new y location of the player
     */
    public void setY(int a){
        span.y=a;
    }
    
    /**
     * This function increments the x value of the player's location by the
     *  amount specified; if it is negative then the number will decrease the
     *  player's x location. 
     * 
     * @param a how much to increment the player's x location by
     */
    public void incrementX(int a){
        span.x+=a;
    }
    
    /**
     * This function increments the y value of the player's location by the 
     *  amount specified; if it is negative then the number will decrease the 
     *  player's y location.
     * 
     * @param a 
     */
    public void incrementY(int a){
        span.y+=a;
    }
    
    /**
     * This function returns the width of the player in location points. 
     * 
     * @return the width of the player in location points
     */
    public int getWidth(){
        return (int)span.getWidth();
    }
    
    /**
     * This function returns the height of the player in location points. 
     * 
     * @return the height of the player in location points
     */
    public int getHeight(){
        return (int)span.getHeight();
    }
    
    /**
     * This function returns how much damage the player deals in a melee attack. 
     * 
     * @return how much damage the player deals in a melee attack
     */
    public double getMeleeDamage(){
        return meleeDamage;
    }
    
    /**
     * This function sets how much damage the player deals in a melee attack. 
     * 
     * @param a how much damage the player should deal per melee attack 
     */
    public void setMeleeDamage(double a){
        meleeDamage=a;
    }
            
    /**
     * This function returns how much damage the player deals in a melee attack. 
     * 
     * @return how much damage the player deals in a melee attack
     */
    public double getRangedDamage(){
        return rangedDamage;
    }
    
    /**
     * This function sets how much damage the player deals in a melee attack. 
     * 
     * @param a how much damage the player should deal per melee attack 
     */
    public void setRangedDamage(double a){
        rangedDamage=a;
    }
    
    /**
     * This function returns the boolean value concerning if the player is 
     *  facing to the right. 
     * 
     * @return if the player is facing towards the right 
     */
    public boolean getFacingRight(){
        return facingRight;
    }
    
    /**
     * This function sets the boolean value concerning if the player is facing
     *  towards the right. 
     * 
     * @param a if the player should face towards the right
     */
    public void setFacingRight(boolean a){
        facingRight=a;
    }
    
    /**
     * This returns the x component of the player's velocity.
     * 
     * @return the x component of the player's velocity
     */
    public int getXVelocity(){
        return velocity[0];
    }
    
    /**
     * This returns y component of the player's velocity.
     * 
     * @return the y component of the player's velocity
     */
    public int getYVelocity(){
        return velocity[1];
    }
    
    /**
     * This function sets the player's velocity in the x direction. 
     * 
     * @param a what to set the velocity in the x direction to
     */
    public void setXVelocity(int a){
        velocity[0]=a;
    }
    
    /**
     * This function sets the player's velocity in the y direction.
     * 
     * @param a what to set the velocity in the y direciton to
     */
    public void setYVelocity(int a){
        velocity[1]=a;
    }
    
    /**
     * This function increments the x component of the player's velocity. 
     * 
     * @param a how much to increment the x component of the player's velocity
     *      by 
     */
    public void incrementXVelocity(int a){
        velocity[0]+=a;
    }
    
    /**
     * This function increments the y component of the player's velocity. 
     * 
     * @param a how much to increment the y component of the player's velocity
     *      by 
     */
    public void incrementYVelocity(int a){
        velocity[1]+=a;
    }
    
    
    /**
     * This tells if the player is jumping.
     * 
     * @return if the player is jumping
     */
    public boolean getJumping(){
        return jumping;
    }
    
    /**
     * This function sets the boolean value concerning if the player is jumping;
     *  this function is not for the purpose of making the player jump.
     * 
     * @param a if the player is jumping
     */
    public void setJumping(boolean a){
        jumping=a;
    }
    
    /**
     * This function returns the boolean value concerning if the player is 
     *  falling. 
     * 
     * @return if the player is falling. 
     */
    public boolean getFalling(){
        return falling;
    }
    
    /**
     * This function sets the value for the boolean concerning if the player is
     *  falling; this function is not for the purpose of making the player jump.
     * 
     * @param a if the player should be falling. 
     */
    public void setFalling(boolean a){
        falling=a;
    }
    
    /**
     * This function is used to set the variables for after the player is done
     *  falling. 
     * 
     */
    public void endFall(){
        setFalling(false);
        setCanJump(true);
        setJumping(false);
    }
    
    /**
     * This function tells if the player can jump. 
     * 
     * @return if the player can jump
     */
    public boolean getCanJump(){
        return canJump;
    }
    
    /**
     * This sets if the player can jump.
     * 
     * @param j if the player can jump
     */
    public void setCanJump(boolean j){
        canJump=j;
    }
    
    
    /**
     * This tells if the player is running. 
     * 
     * @return if the player is running
     */
    public boolean getrunning(){
        return running;
    }
    
    /**
     * This function returns the image used for the banana's projectile. 
     * 
     * @return the image for the banana's projectile
     */
    public BufferedImage getProjectileImage(){
        return images.get(92);
    }
    
}
