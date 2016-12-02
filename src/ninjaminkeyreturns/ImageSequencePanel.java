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
public class ImageSequencePanel extends CPanel{
    
    /**
     * The CListener instance used to tell when to initialize the main menu
     */
    private CListener done=null;
    
    /**
     * This constructor sets up the ImageSequencePanel using a CListener that
     *  is used to tell when to initialize the main menu.
     * 
     * @param d the CListener that is used to tell when to initialize the main 
     *      menu
     */
    public ImageSequencePanel(CListener d){
        done=d;
    }
    
}
