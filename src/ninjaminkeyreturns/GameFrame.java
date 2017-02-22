/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Josh Holland
 */
public class GameFrame extends JFrame{
    /**
     * The current instance of CPanel that is added on to this container that
     *  is used to display the graphical representation of the game. 
     */
    private CPanel currentPanel;
    
    /**
     * The Rectangle object that represents the area in which the game 
     *  components are being graphically represented; it is calculated using
     *  the user's screen resolution. 
     */
    public static Rectangle GAME_SPAN=new Rectangle();
    
    /**
     * Set up the game's frame in order to give the user a graphical 
     *  representation for understanding and an interface in order to 
     *  influence the components of the game that are meant to be controlled. 
     * 
     */
    public GameFrame(){
//        this.setIconImage(GraphicsAssets.getIcon());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
//        this.setSize(FRAME_SIZE[0],FRAME_SIZE[1]);
        this.setVisible(true);        
        
        //get the screen resolution::
        Dimension s=Toolkit.getDefaultToolkit().getScreenSize();
        
        CPanel.calculateTopDownGameSize((int)s.getWidth(),(int)s.getHeight());
        
        setSIZE();
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        
        //startup flow::
        
        //for full game and intro and stuff:: (also uncomment in MainMenu) ::
//        currentPanel=new OpeningPanel();
//        this.add(currentPanel);
//        super.setVisible(true);//keep this statement always!!
//        
//        while(!currentPanel.done){
//            try{Thread.sleep(200);}
//            catch(Exception e){ErrorLogger.logError(e,"StartGameFlow");}
//        }
//        AudioAssets.play("music");
//        setNewPanelType(new MainMenu(this));
        
        //only here because of testing/developing purposes::
        
        
        switchToMainMenuPanel(true);
//        switchToGamePanel();
        
        
        
    }
    
    /**
     * This function switches the panel type to be the GamePanel in order for 
     *  the player to play the game. 
     */
    private void switchToGamePanel(){
        currentPanel=null;
        currentPanel=new GamePanel(new CListener(){
            @Override
            public void actionPerformed(){
                switchToMainMenuPanel(false);
            }
        });
        this.add(currentPanel);
        this.addKeyListener(currentPanel);
        super.setVisible(true);
        this.repaint();
        currentPanel.repaint();
//        this.setSIZE();
        //this.requestFocus();
        this.setLocation(0,0);
        this.setLocation(30,30);
        this.setLocation(5,5);
        Prompt.resetFont();
        ErrorLogger.logEvent("Finished setting up game CPanel");
    }
    
    /**
     * This function sets up the main menu and removes the panel and flow of 
     *  the game.
     * 
     * @param startup the boolean value concerning if the program is just 
     *      starting
     */
    private void switchToMainMenuPanel(boolean startup){
        currentPanel=null;
        currentPanel=new StartPanel(startup,new CListener(){
            @Override
            public void actionPerformed(){
                System.out.println("switching to main menu panelllllllllllllllllaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                switchToGamePanel();
            }
        });
        currentPanel.setVisible(true);
        this.add(currentPanel);
        this.addKeyListener(currentPanel);
        super.setVisible(true);
        this.repaint();
        currentPanel.repaint();
//        this.setSIZE();
        //this.requestFocus();
        Prompt.resetFont();
        ErrorLogger.logEvent("Finished setting up main menu CPanel");
    }
    
    /**
     * This function sets the size of the static variables concerning the 
     *  frame's size both in this class and in CPanel in order to enhance the
     *  accuracy of the code. 
     * 
     */
    private void setSIZE(){
        
        //CPanel.FRAME_SIZE[0]=FRAME_SIZE[0]=(int)CPanel.GAME_SPAN.getWidth();
        //CPanel.FRAME_SIZE[1]=FRAME_SIZE[1]=(int)CPanel.GAME_SPAN.getHeight();
        
        //CPanel.FRAME_SIZE[0]=FRAME_SIZE[0]=this.getWidth();
        //CPanel.FRAME_SIZE[1]=FRAME_SIZE[1]=this.getHeight();
        //CPanel.FRAME_SIZE=FRAME_SIZE;
        this.setSize(GAME_SPAN.width,GAME_SPAN.height);
    }
}
