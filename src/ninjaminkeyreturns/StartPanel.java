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
 * @author 0001058857
 */
public class StartPanel extends CPanel{
    boolean[] keysPressed=new boolean[7];
    
    boolean dones=false;
    
    NavigablePrompt menu=null;
    
    private int time=0;
    
    boolean initialImages=true;
    
    boolean instructionsRun=false;
    
    BufferedImage[] startImages=new BufferedImage[3];//third one is the background for the main menu
    
    CListener done=null;
    
    public StartPanel(boolean startup,CListener d){
        initialImages=startup;
        startImages=GraphicsAssets.importStartupImages();
        
        done=d;
        
        ErrorLogger.logEvent("Started StartPanel");
        
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(-1,-1,(int)GAME_SPAN.getWidth()+2,(int)GAME_SPAN.getHeight()+2);
        //System.out.println("drawing start panel  "+(instructionsRun?time/75:time/50));
        //System.out.println(startImages[0]+"  "+startImages[1]+" "+startImages[2]);
        if(initialImages){
            //System.out.println(" time oneee :: "+time+" "+GAME_SPAN.getX()+" "+GAME_SPAN.getY()+" "+GAME_SPAN.getWidth()+" "+GAME_SPAN.getHeight());
            g.drawImage(startImages[instructionsRun?time/150:time/75],0,0,(int)GAME_SPAN.getWidth(),(int)GAME_SPAN.getHeight(),null);
            if((!instructionsRun&&time>148)
                ||(instructionsRun&&time>298)){
                //System.out.println("THIS CODE IS AUTISTIC");
                initialImages=false;
                if(!instructionsRun)
                    setupMenu();
            }else
                time++;
                
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
    
    private void startNewGame(){
        Profile.startNewGame();         
        startImages=GraphicsAssets.importInstructionImages();
        initialImages=true;
        time=0;
        instructionsRun=true;
        menu=null;
    }
    
    private void keysFlow(){
        
    }
    
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
