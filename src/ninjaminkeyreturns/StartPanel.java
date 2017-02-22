/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import static ninjaminkeyreturns.CPanel.GAME_SPAN;

/**
 *
 * @author Josh Holland
 */
public class StartPanel extends CPanel{
    
    /**
     * This is an array of boolean values concerning which keys of which 
     *  controls are being pressed and is used for the flow of the game and for
     *  the flow of the menus. 
     */
    boolean[] keysPressed=new boolean[7];
    
    /**
     * This is a boolean value concerning if this class is finished drawing the
     *  visual display for the user. 
     */
    boolean dones=false;
    
    /**
     * The integer values of the max ping of time that the timePing variable 
     *  should get to in order to move on. 
     */
    private int maxIntro=150,maxInstructions=500;
    
    /**
     * The instance of the Prompt that is used for the main menu and the options
     *  menu. 
     */
    NavigablePrompt menu=null;
    
    /**
     * This integer value is a sequence value used to tell when to end the sets
     *  of each image. 
     */
    private int timePing=0;
    
    /**
     * This boolean value concerns if it is showing the initial images of the 
     *  credits or the initial images of the instructions. 
     */
    boolean initialImages=true;
    
    /**
     * This is a boolean value concerning if the StartPanel is currently 
     *  displaying instructions. 
     */
    boolean instructionsRun=false;
    
    /**
     * This is the array of BufferedImage instances that has either the credit
     *  images for startup or the instruction images for the new game, but also
     *  has a background image for the menu as the third image. 
     */
    BufferedImage[] startImages=new BufferedImage[3];//third one is the background for the main menu
    
    /**
     * The CListener instance that is used to return back to the GameFrame class
     *  in order to start the game. 
     */
    CListener done=null;
    
    /**
     * This function sets up the StartPanel.
     * 
     * @param startup this is the boolean value concerning if this panel has
     *      been initialized because the program is starting
     * @param d the CListener used to return to the GameFrame class in order to
     *      start the game
     */
    public StartPanel(boolean startup,CListener d){
        initialImages=startup;
        startImages=GraphicsAssets.importStartupImages();
        
        done=d;
        
        if(!startup){
            //timePing=maxIntro-2;
            setupMenu();
        }
        
        this.repaint();
    }
    
    /**
     * The function that is used to create a loop for the game's calculations
     *  and changes in graphics.
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game.  
     */
    @Override
    public void paintComponent(Graphics g){
        
        if(!dones){
            g.setColor(Color.black);
            g.fillRect(-1,-1,(int)GAME_SPAN.getWidth()+2,(int)GAME_SPAN.getHeight()+2);
            //System.out.println("drawing start panel  "+(instructionsRun?time/75:time/50));
            //System.out.println(startImages[0]+"  "+startImages[1]+" "+startImages[2]);
            if(initialImages){
                //System.out.println(" time oneee :: "+time+" "+GAME_SPAN.getX()+" "+GAME_SPAN.getY()+" "+GAME_SPAN.getWidth()+" "+GAME_SPAN.getHeight());
                g.drawImage(startImages[instructionsRun?timePing/(maxInstructions/2):timePing/(maxIntro/2)],0,0,(int)GAME_SPAN.getWidth(),(int)GAME_SPAN.getHeight(),null);
                if((!instructionsRun&&timePing>maxIntro-2)
                    ||(instructionsRun&&timePing>maxInstructions-2)){
                    //System.out.println("THIS CODE IS AUTISTIC");
                    initialImages=false;
                    timePing=0;
                    if(!instructionsRun)
                        setupMenu();
                }else
                    timePing++;

            } else if(instructionsRun&&!dones){
                dones=true;
                done.actionPerformed();


            }else{

                //draw background
                g.drawImage(startImages[2],(int)GAME_SPAN.getX(),(int)GAME_SPAN.getY(),(int)GAME_SPAN.getWidth(),(int)GAME_SPAN.getHeight(),null);
                if(menu!=null){
                    menu.draw(g);
                    menu.loopCalculate(keysPressed);
                }
            }

            try{
                Thread.sleep(40);
            }catch(Exception e){

            }
            if(!dones)
                this.repaint();
        }
    }
    
    /**
     * This function sets up the main menu. 
     */
    private void setupMenu(){
        menu=new StartupPrompt(false,new CListener(){
                    @Override
                    public void actionPerformed(int choice){
                        switch(choice){
                            case 0://start new
                                startNewGame();
                                //done.actionPerformed();
                                break;
                            case 1://load an old save
                                dones=true;
                                boolean a=false;
                                try{
                                    a=Profile.open();
                                }catch(Exception e){
                                    System.out.println("Error with getting load file:: "+e);
                                }
                                if(!a)
                                    startNewGame();
                                
                                menu=null;
                                dones=true;
                                done.actionPerformed();
                                
                                break;
                            case 2://options
                                menu=new OptionsPrompt(false,new CListener(){
                                    @Override
                                    public void actionPerformed(int c){
                                        if(c==0){
                                            setupMenu();
                                        }
                                    }
                                });
                                break;
                            case 3://quit
                                System.exit(0);
                                break;
                        }
                    }
                    
                });
    }
    
    /**
     * This function includes the flow for starting a new instance of the game,
     *  in which the player has no progress. 
     */
    private void startNewGame(){
        Profile.startNewGame();         
        startImages=GraphicsAssets.importInstructionImages();
        initialImages=true;
        timePing=0;
        instructionsRun=true;
        menu=null;
        //done.actionPerformed();
    }
    
    /**
     * This function is overriden from the KeyListener interface in order to 
     *  control the flow of the user's input. 
     * 
     * @param e the KeyEvent instance that is used to determine which key was
     *      pressed
     */
    @Override
    public void keyPressed(KeyEvent e){
        char key=Character.toUpperCase(e.getKeyChar());
        
        if(key==Profile.controls[2]){//left
            keysPressed[2]=true;
        } else if(key==Profile.controls[3]){//right
            keysPressed[3]=true;
        } else if(key==Profile.controls[4]){//select
            keysPressed[4]=true;
        }
    }
    
    /**
     * This function is overriden from the KeyListener interface in order to 
     *  control the flow of the user's input. 
     * 
     * @param e the KeyEvent instance that is used to determine which key was
     *      pressed
     */
    @Override
    public void keyReleased(KeyEvent e){
        char key=Character.toUpperCase(e.getKeyChar());
        
        if(key==Profile.controls[2]){//left
            keysPressed[2]=false;
        } else if(key==Profile.controls[3]){//right
            keysPressed[3]=false;
        } else if(key==Profile.controls[4]){//select
            keysPressed[4]=false;
        }
    }
    
}
