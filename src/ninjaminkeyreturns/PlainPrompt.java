/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import static ninjaminkeyreturns.Prompt.GAME_SPAN;
import static ninjaminkeyreturns.Prompt.font;

/**
 *
 * @author Josh
 */
public class PlainPrompt extends Prompt{//this prompt is used for just showing text on the screen and allowing the player to hit select to continue through it. 
    
    private ArrayList<String> promptShowing=null;
    
    public String toDraw="";
    
    private CListener done=null;
    
    public PlainPrompt(ArrayList<String> showing,CListener don){
        super();
        this.promptShowing=showing;
        done=don;
    }
    
    private int characterNum=0,lineNum=0;
    private boolean secondLine=false,promptEnded=false;
    
    public void loopCalculate(boolean cont){
        if(lineNum>promptShowing.size()-1){
            done.actionPerformed();
        } else{//is not finished
            String currentString=promptShowing.get(lineNum);
            toDraw=currentString;
            if(!waitingForInput){
                characterNum++;
                if(characterNum/textSpeed<currentString.length()){//still drawing the string out
                    toDraw=currentString.substring(0,characterNum/textSpeed+1);
                    characterNum++;
                }else{//the string has been completely drawn and it is now waiting for user input
                    waitingForInput=true;
                }
            }else{//is waiting for player input (done with that line)
                if(cont){
                    lineNum++;
                    characterNum=0;
                    waitingForInput=false;
                }
            }
        }
    }
    
    @Override
    public void draw(Graphics g){
        super.drawBackImage(g);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(toDraw,(int)(0.05*GAME_SPAN.width)+GAME_SPAN.x,(int)(0.8*GAME_SPAN.height)+GAME_SPAN.y);
    }
}
