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
    
    private GameRunner runner=new GameRunner();
    
    public final int FRAME_DELAY=2000;
    
    private CListener backToMenu=new CListener();
    
    public GamePanel(){
        timer.start();
//        this.repaint();
    }
    
    public GamePanel(CListener c){
        this();
        backToMenu=c;
        this.addKeyListener(runner);
    }
    
    @Override
    public void paint(Graphics g){
//        System.out.println("drawing..");
        
        g.setColor(Color.black);
        g.fillRect(-1,-1,FRAME_SIZE[0]+2,FRAME_SIZE[1]+2);
        
//        g.setColor(Color.red);   //for testing purposes
//        g.fillRect(50,50,600,120);
        
        runner.draw(g);
        
//        System.out.println("end draw");
        
        
    }
    
    public Timer timer=new Timer(FRAME_DELAY,new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e){
           System.out.println("action performed");
           rpnt();
           runner.calculate();
       }
    });
    
    public void rpnt(){
        this.repaint();
    }
    
}