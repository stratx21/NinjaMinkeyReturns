/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author 0001058857
 */
public class StartPanel extends CPanel{
    boolean[] keysPressed=new boolean[7];
    
    NavigablePrompt menu=null;
    
    private int time=0;
    
    boolean initialImages=true;
    
    boolean instructionsRun=false;
    
    BufferedImage[] startImages=new BufferedImage[3];//third one is the background for the main menu
    
    CListener done=null;
    
    public StartPanel(boolean startup,CListener d){
        initialImages=startup;
        
        done=d;
        
        ErrorLogger.logEvent("Started StartPanel");
        
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        if(initialImages){
            System.out.println(" time oneee :: "+time);
            g.drawImage(startImages[instructionsRun?time/75:time/50],(int)GAME_SPAN.getX(),(int)GAME_SPAN.getY(),(int)GAME_SPAN.getWidth(),(int)GAME_SPAN.getHeight(),null);
            if((!instructionsRun&&time>98)
                ||(instructionsRun&&time>148)){
                System.out.println("THIS CODE IS AUTISTIC");
                initialImages=false;
                if(!instructionsRun)
                    setupMenu();
            }else
                time++;
                
        } else if(instructionsRun){
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
        this.repaint();
    }
    
    private void setupMenu(){
        menu=new StartupPrompt(false,new CListener(){
                    @Override
                    public void actionPerformed(int choice){
                        switch(choice){
                            case 0://start new
                                Profile.startNewGame();
                                
                                startImages=GraphicsAssets.importInstructionImages();
                                initialImages=true;
                                time=0;
                                instructionsRun=true;
                                //done.actionPerformed();
                                break;
                            case 1://load an old save
                                try{
                                    Profile.open();
                                }catch(Exception e){
                                    System.out.println("Error with getting load file:: "+e);
                                }
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
