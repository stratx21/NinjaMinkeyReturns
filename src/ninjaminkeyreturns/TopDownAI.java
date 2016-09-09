/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

/**
 *
 * @author Josh
 */
public class TopDownAI extends AI{
    
    public int REGION_ID=0;
    
    public int MISSION_GIVEN_ID=0;
    
    public String promptBefore="",promptAfter="";
    
    
    
    public TopDownAI(int x,int y,int ID,int mssnGivenID,String prmptBefore,String prmptAfter){
        images=GraphicsAssets.importTopDownAIImages(AI_ID=ID);
        location=new int[]{x,y};
        MISSION_GIVEN_ID=mssnGivenID;
        promptBefore=prmptBefore;
        promptAfter=prmptAfter;
    }
    
    public void walkUp(){
        
    }
    
    public void walkDown(){
        
    }
    
    public void walkLeft(){
        
    }
    
    public void walkRight(){
        
    }
    
    
    
    
}
