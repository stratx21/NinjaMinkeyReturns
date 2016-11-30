/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Josh
 */
public class GameFrame extends JFrame{
    /**
     * The current instance of CPanel that is added on to this container that
     *  is used to display the graphical representation of the game. 
     */
    private CPanel currentPanel;
    
    /**
     * The standard size of the overall frame that is used for the game; this is
     *  the size whose value is set based on the user's screen resolution 
     *  for the sake of the game being full-screen. 
     */
    public static int[] FRAME_SIZE=new int[2];
    
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
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        
        CPanel.calculateTopDownGameSize();
        
        currentPanel=new GamePanel(new CListener(){
            @Override
            public void actionPerformed(){
                //back to main menu...  (if keeping this exact code...)
            }
        });
        
        this.add(currentPanel);
        this.addKeyListener(currentPanel.runner);
        super.setVisible(true);
        this.repaint();
    }
    
    /**
     * This function sets the size of the static variables concerning the 
     *  frame's size both in this class and in CPanel in order to enhance the
     *  accuracy of the code. 
     * 
     */
    
    private void setSIZE(){
        CPanel.FRAME_SIZE[0]=FRAME_SIZE[0]=this.getWidth();
        CPanel.FRAME_SIZE[1]=FRAME_SIZE[1]=this.getHeight();
        CPanel.FRAME_SIZE=FRAME_SIZE;
        this.setSize(FRAME_SIZE[0],FRAME_SIZE[1]);
    }
    
    /**
     * This removes the old CPanel instance from this Container and adds a new
     *  one that is specified by the parameter instance of CPanel. 
     * 
     * @param cp the instance of CPanel that is used to replace the current 
     *      instance of CPanel that is added on to this Container
     */
    private void setNewPanelType(CPanel cp){    
//        this.setVisible(false);
        this.remove(currentPanel);
        currentPanel=cp;
        this.add(currentPanel);
        this.setVisible(true);
    }
}
