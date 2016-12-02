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
public class SideViewMission {
    
    /**
     *
     * @param id
     */
    public SideViewMission(int id){
        ID=id;
    }
    
    /**
     * The ID of this mission.
     */
    private int ID=0;
    
    /**
     * The prompt String values for before and after the mission. 
     */
    private String beforePrompt,afterPrompt;
    
    /**
     * Get the ID for this mission.
     * 
     * @return the ID for this mission
     */
    public int getID(){
        return ID;
    }
    
    /**
     * Set the ID for this mission.
     * 
     * @return the ID for this mission
     */
    public int setID(){
        return ID;
    }
    
    /**
     * Get the prompt for before the mission. 
     * 
     * @return the prompt for before the mission
     */
    public String getBeforePrompt(){
        return beforePrompt;
    }
    
    /**
     * Get the prompt for after the mission. 
     * 
     * @return the prompt for after the mission
     */
    public String getAfterPrompt(){
        return afterPrompt;
    }
    
}
