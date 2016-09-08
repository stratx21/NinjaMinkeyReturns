/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class TopDownRunner extends GameRunner{//in top down mode only one key can be noticed as held
    
    public static int SQUARE_SIZE=0;
    
    private TopDownPlayer player=new TopDownPlayer(new int[]{19,20});
    
    private TopDownRegion region=new TopDownRegion(0);
    
    private boolean[] currentKey=new boolean[6];//up, down, left, right, attack, other attack
    
    public TopDownRunner(){
        super();
        setup();
    }
    
    public TopDownRunner(CListener dn){
        super(dn);
        setup();
    }
    
    /**
     * PRE:: SQUARE_SIZE has been set up in the set up in CPanel
     */
    private void setup(){
        Region.SQUARE_SIZE=GameRunner.SQUARE_SIZE=player.SQUARE_SIZE=SQUARE_SIZE;//
        Region.GAME_SPAN=CPanel.GAME_SPAN;
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void draw(Graphics g){
//        System.out.println("reached top down draw");
        region.draw(g,player.getX(),player.getY(),player.getOffCenterX(),player.getOffCenterY());
        player.Draw(g);
        
        //calculate:: (may be moved into another recursion/timer later)::
        
//        player.calculate();
        playerKeysFlow();
    }
    
    private void playerKeysFlow(){//may have other options soon for an alternate menu? may just use another panel for that though..
        if(currentKey[0]){
            
        }else if(currentKey[1]){
        
        }else if(currentKey[2]){
            
        }else if(currentKey[3]){
            
        }else if(currentKey[4]){
            
        }else if(currentKey[5]){
            
        }
    }
    
    private void moveToNewRegion(int newRegion){
        region.changeRegion(newRegion);
        
        //** code for fading out, then showing the next biome/region pic, then going back....
    }
    
    /**
     *
     */
    @Override
    public void calculate(){
        
    }
    
    
    
    public void drawBackgroundTiles(Graphics g){
        
    }
    
    
    @Override
    public void keyTypedFlow(char typed){
        
    }
    
    /**
     * PRE::
     * @param typed 
     */
    @Override
    public void keyPressedFlow(char typed){
        if(typed==controls[0]){
            currentKey[0]=true;
            setOtherKeysFalse(0);//in here to change it only if it is a valid key
        }else if(typed==controls[1]){
            currentKey[1]=true;
            setOtherKeysFalse(1);
        }else if(typed==controls[2]){
            currentKey[2]=true;
            setOtherKeysFalse(2);
        }else if(typed==controls[3]){
            currentKey[3]=true;
            setOtherKeysFalse(3);
        }else if(typed==controls[4]){
            currentKey[4]=true;
            setOtherKeysFalse(4);
        }else if(typed==controls[5]){
            currentKey[5]=true;
            setOtherKeysFalse(5);
        }
    }
    
    @Override
    public void keyReleasedFlow(char typed){
        if(typed==controls[0]){
            currentKey[0]=false;
        }else if(typed==controls[1]){
            currentKey[1]=false;
        }else if(typed==controls[2]){
            currentKey[2]=false;
        }else if(typed==controls[3]){
            currentKey[3]=false;
        }else if(typed==controls[4]){
            currentKey[4]=false;
        }else if(typed==controls[5]){
            currentKey[5]=false;
        }
    }
    
    private void setOtherKeysFalse(int doNotChange){
        for(int i=0;i<6;i++)
            if(i!=doNotChange)
                currentKey[i]=false;
    }
    
}
