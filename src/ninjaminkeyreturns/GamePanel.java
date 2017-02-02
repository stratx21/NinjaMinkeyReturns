/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

/**
 *
 * @author Josh
 */
public class GamePanel extends CPanel implements KeyListener{
    
    //public GameRunner runner=null;
    /**
     * This is an integer value that tells how many milliseconds each frame 
     *  should be delayed by on a standard run; the code strives to keep each 
     *  delay at this number of milliseconds but is prepared for lag.  
     */
    public final int FRAME_DELAY=20;
    
    /**
     * This is the CListener that is used to return to the main menu from the 
     *  game. 
     */
    private CListener backToMenu=new CListener();
    
//    private int c=0;
    /**
     * This sets up the Game Panel and adds the KeyListener that is used for the
     *  game's interface.
     * 
     * NOTE:: GamePanel will always be initialized from the main menu and will 
     *  start on the top down mode.
     * 
     * 
     */
    public GamePanel(){//initial startup, only from initial call to start the game
//        timer.start();
        this.repaint();
        
        switchToTopDown();
        //switchToSideView();
        
        //this.addKeyListener(runner);
        System.out.println("ADDED KEYLISTENER...");
        
        
    }
    
    /**
     * This initializes the class GamePanel with the CListener for when it is 
     *  done.
     * 
     * @param c the Clistener instance that will be used to return to the main menu
     */
    public GamePanel(CListener c){
        this();
        backToMenu=c;
    }
    
    /**
     * This function manages the change to a side view mode user interface by
     *  controlling the proper changes required for the switch. 
     * 
     */
    private void switchToSideView(){
        runner=new SideViewRunner(new CListener(){
            @Override
            public void actionPerformed(boolean won){
                switchToTopDown();
            }
            
            
        },tempSideID);
    }
    
    /**
     * This integer value is used temporarily throughout the switch between 
     *  top down view mode and side view mode interfaces in order to instruct
     *  the new mode as to which region will be managed. 
     * 
     */
    private int tempSideID=0;
    
    /**
     * This function manages the change to a top down view mode user interface 
     *  by controlling the proper changes required for the switch. 
     * 
     */
    private void switchToTopDown(){
        runner=new TopDownRunner(new CListener(){
            @Override
            public void actionPerformed(int sideViewRegionID){//now about to go to a side view mission
                switchToSideView();
                tempSideID=sideViewRegionID;
            }
            },Profile.playerLocation[0],Profile.playerLocation[1],Profile.lastKnownRegionTopDown);
    }
    /**
     * an integer value that is used for the Y value of the black rectangle
     *  that is used to clear the game span graphical representation. 
     * 
     */
    int yv=(int)GAME_SPAN.getHeight()-GAME_SPAN.y-GAME_SPAN.height;
    
    /**
     * The function that is used to create a loop for the game's calculations
     *  and changes in graphics, while it also manages the Frames Per Second
     *  and repainting delays. 
     * 
     * @param g the java.awt.Graphics object that is used to form the 
     *  graphical representations of the game objects on the frame Container
     *  that holds the game.  
     */
    @Override
    public void paintComponent(Graphics g){
        long firstTime=System.currentTimeMillis();
//        System.out.println("drawing.."+c);
        
//        c++;
        
        g.setColor(Color.black);
        g.fillRect(-1,-1,(int)GAME_SPAN.getWidth()+2,(int)GAME_SPAN.getHeight()+2);
        
//        g.setColor(Color.red);   //for testing purposes
//        g.fillRect(50,50,600+c,120);
        
        runner.draw(g);
        
        //g.setColor(Color.black);
        //g.fillRect(0,0,FRAME_SIZE[0],GAME_SPAN.y);
        //g.fillRect(0,(GAME_SPAN.y+GAME_SPAN.height),FRAME_SIZE[0],yv);
        
//        System.out.println("end draw");
        try{Thread.sleep(FRAME_DELAY-(System.currentTimeMillis()-firstTime));}catch(Exception e){}
        this.repaint();
        
    }
    
//    public Timer timer=new Timer(FRAME_DELAY,new ActionListener(){
//       @Override
//       public void actionPerformed(ActionEvent e){
//           System.out.println("action performed");
//           rpnt();
//           runner.calculate();
//       }
//    });
    
    
    /**
     * This function is used as a local reference to the repaint function that 
     *  is inherited from parent classes and is used to repaint the 
     *  graphical representation of the game. 
     * 
     */
    public void rpnt(){
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        runner.keyTypedFlow(Character.toUpperCase(e.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        runner.keyPressedFlow(Character.toUpperCase(e.getKeyChar()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        runner.keyReleasedFlow(Character.toUpperCase(e.getKeyChar()));
    }
    
}