/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
     * This tells if the player is currently attacking. 
     */
    private boolean attacking=false;
    
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
     * @param g
     * @param camX the x location of the camera
     * @param camY the y location of the camera
     */
    public void draw(Graphics g,int camX,int camY){
        
        //System.out.println(GAME_SPAN.width+","+GAME_SPAN.height+"  square size:: "+SQUARE_SIZE+"  MULTIPLIER:: "+POINT_TO_PIXEL_MULTIPLIER);
        
        g.setColor(Color.blue);
        g.fillRect((int)((span.getX()-camX+10)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                (int)((span.getY()-camY-5)*(POINT_TO_PIXEL_MULTIPLIER)),
                (int)(span.width*POINT_TO_PIXEL_MULTIPLIER),
                (int)(span.height*POINT_TO_PIXEL_MULTIPLIER));
        
        
        
        if(velocity[0]==0&&velocity[1]==0){
            g.drawImage(images.get(facingRight?0:8),
                    (int)((span.getX()-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                    (int)((span.getY()-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                    SQUARE_SIZE*2,
                    SQUARE_SIZE*2,
                    null); 
            
        }else{ //travelling
            g.drawImage(images.get((facingRight?0:8)+(jumping?16:0)+imageSequence/5),
                (int)((span.getX()-camX-5)*(POINT_TO_PIXEL_MULTIPLIER)-SQUARE_SIZE/2),
                (int)((span.getY()-camY-10)*(POINT_TO_PIXEL_MULTIPLIER)),
                SQUARE_SIZE*2,SQUARE_SIZE*2,null);  
            if(imageSequence==39
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
     * This function contains the flow to walk to the right. 
     */
    private void walkRight(){
        
    }
    
    
    /**
     * This function contains the flow to walk to the left. 
     */
    private void walkLeft(){
        
    }
    
    /**
     * This function contains the flow to start a jump. 
     */
    public void startJump(){
        if(canJump){//       remove later if not needed?  - -- -- - - -- - - - -- -
            setCanJump(false);
            jumping=true;
            velocity[1]=-2*JUMP_VELOCITY_START;
        }
    }
    
    /**
     * This function contains the flow to attack to the left.
     */
    private void attackLeft(){
        
    }
    
    /**
     * This function contains the flow to attack up.
     */
    private void attackUp(){
        
    }
    
    /**
     * This function contains the flow to attack down.
     */
    private void attackDown(){
        
    }
    
    /**
     * This function contains the flow to attack to the right.
     */
    private void attackRight(){
        
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
     * This returns the value of y from the location array to tell where the
     *  player is.
     * 
     * @return the y coordinate of the player's location
     */
    public int getY(){
        return (int)span.getY();
    }
    
    public void setX(int a){
        span.x=a;
    }
    
    public void setY(int a){
        span.y=a;
    }
    
    public void incrementX(int a){
        span.x+=a;
    }
    
    public void incrementY(int a){
        span.y+=a;
    }
    
    public int getWidth(){
        return (int)span.getWidth();
    }
    
    public int getHeight(){
        return (int)span.getHeight();
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
    
    public void setXVelocity(int a){
        velocity[0]=a;
    }
    
    public void setYVelocity(int a){
        velocity[1]=a;
    }
    
    public void incrementXVelocity(int a){
        velocity[0]+=a;
    }
    
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
    
    public void setJumping(boolean a){
        jumping=a;
    }
    
    public boolean getFalling(){
        return falling;
    }
    
    public void setFalling(boolean a){
        falling=a;
    }
    
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
    
}
