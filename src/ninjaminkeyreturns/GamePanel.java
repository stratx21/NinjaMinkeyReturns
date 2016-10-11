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
import javax.swing.Timer;

/**
 *
 * @author Josh
 */
public class GamePanel extends CPanel{
    
    //public GameRunner runner=null;
    
    public final int FRAME_DELAY=20;
    
    private CListener backToMenu=new CListener();
    
//    private int c=0;
    /**
     * GamePanel will always be initialized from the main menu and will thus start on the top down mode.
     * 
     * 
     */
    public GamePanel(){//initial startup, only from initial call to start the game
//        timer.start();
        this.repaint();
        switchToTopDown();
        this.addKeyListener(runner);
        System.out.println("ADDED KEYLISTENER...");
        
        
    }
    
    public GamePanel(CListener c){
        this();
        backToMenu=c;
    }
    
    private void switchToSideView(){
        runner=new SideViewRunner(new CListener(){
            @Override
            public void actionPerformed(boolean won){
                switchToTopDown();
            }
        });
    }
    
    private void switchToTopDown(){
        runner=new TopDownRunner(new CListener(){
            @Override
            public void actionPerformed(int sideViewRegionID){//now about to go to a side view mission
                switchToSideView();
            }
            },Profile.playerLocation[0],Profile.playerLocation[1],Profile.currentRegion);
    }
    
    int yv=FRAME_SIZE[1]-GAME_SPAN.y-GAME_SPAN.height;
    
    @Override
    public void paintComponent(Graphics g){
        long firstTime=System.currentTimeMillis();
//        System.out.println("drawing.."+c);
        
//        c++;
        
        g.setColor(Color.black);
        g.fillRect(-1,-1,FRAME_SIZE[0]+2,FRAME_SIZE[1]+2);
        
//        g.setColor(Color.red);   //for testing purposes
//        g.fillRect(50,50,600+c,120);
        
        runner.draw(g);
        
        g.setColor(Color.black);
        g.fillRect(0,0,FRAME_SIZE[0],GAME_SPAN.y);
        g.fillRect(0,(GAME_SPAN.y+GAME_SPAN.height),FRAME_SIZE[0],yv);
        
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
    
    public void rpnt(){
        this.repaint();
    }
    
}