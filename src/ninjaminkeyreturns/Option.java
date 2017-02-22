/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

/**
 *
 * @author Josh Holland
 */
public class Option {
    
    /**
     * The boolean value concerning if this option is selected. 
     */
    private boolean selected=false;
    
    /**
     * The String value of the text that is displayed to represent this
     *  option. 
     */
    public String text="";
    
    /**
     * The CListener instance that is used to activate the effects of this
     *  option. 
     */
    public CListener activate=new CListener();
    
    /**
     * The setup of the Option object using the text to be displayed and the
     *  CListener instance for when or if it is chosen. 
     * 
     * @param txt the text for it to be represented by
     * @param actvat the CListener instance used to activate this option
     */
    public Option(String txt,CListener actvat){
        activate=actvat;
        text=txt;
    }
    
    /**
     * This function sets the value of the boolean value concerning if this
     *  option is currently selected. 
     * 
     * @param a the boolean value concerning if this option is currently
     *      selected. 
     */
    public void setSelected(boolean a){
        selected=a;
    }
    
    /**
     * This function returns the value of the selected boolean value concerning
     *  if this option is currently selected. 
     * 
     * @return the boolean value concerning if this option is currently selected
     */
    public boolean getSelected(){
        return selected;
    }
    
    /**
     * This function gets the text that represents this option. 
     * 
     * @return the String value with the text of the option
     */
    public String getText(){
        return text;
    }
    
}
