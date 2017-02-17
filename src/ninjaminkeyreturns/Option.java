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
public class Option {
    
    public boolean selected=false;
    
    public String text="";
    
    public CListener activate=new CListener();
    
    public Option(String txt,CListener actvat){
        activate=actvat;
        text=txt;
    }
    
    public void setSelected(boolean a){
        selected=a;
    }
    
    public boolean getSelected(){
        return selected;
    }
    
    public String getText(){
        return text;
    }
    
}
