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
    private CPanel currentPanel;
    public static int[] FRAME_SIZE=new int[2];
    
    
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
        super.setVisible(true);
        this.repaint();
    }
    
    private void setSIZE(){
        CPanel.FRAME_SIZE[0]=FRAME_SIZE[0]=this.getWidth();
        CPanel.FRAME_SIZE[1]=FRAME_SIZE[1]=this.getHeight();
        CPanel.FRAME_SIZE=FRAME_SIZE;
        this.setSize(FRAME_SIZE[0],FRAME_SIZE[1]);
    }
    
    private void setNewPanelType(CPanel cp){    
//        this.setVisible(false);
        this.remove(currentPanel);
        currentPanel=cp;
        this.add(currentPanel);
        this.setVisible(true);
    }
}
