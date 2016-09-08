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
    
    public GamePanel(){
//        timer.start();
        this.repaint();
        runner=new TopDownRunner(new CListener(){
            @Override
            public void actionPerformed(){
                
            }
        });
        this.addKeyListener(runner);
        System.out.println("ADDED KEYLISTENER...");
    }
    
    public GamePanel(CListener c){
        this();
        backToMenu=c;
    }
    
    @Override
    public void paintComponent(Graphics g){
//        System.out.println("drawing.."+c);
        
//        c++;
        
        g.setColor(Color.black);
        g.fillRect(-1,-1,FRAME_SIZE[0]+2,FRAME_SIZE[1]+2);
        
//        g.setColor(Color.red);   //for testing purposes
//        g.fillRect(50,50,600+c,120);
        
        runner.draw(g);
        
//        System.out.println("end draw");
        try{Thread.sleep(FRAME_DELAY);}catch(Exception e){}
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