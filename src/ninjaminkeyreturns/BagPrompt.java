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
public class BagPrompt extends Prompt{
    
    /**
     * This integer tells how far down in the current section list of items
     *  that the user is focused on.
     */
    public int cursorLocation=0;
    
    /**
     * This byte tells what section of the bag the user is in.
     * 
     * NOTE:: it is static so that as long as the game has not been restarted,
     *      it still goes back to the same section that it was at before. 
     */
    public static byte bagGroup=0;
    
    /**
     * This initializes the BagPrompt with a CListener to evoke when it is done.
     * 
     * @param c the CListener that is evoked when the BagPrompt is done
     */
    public BagPrompt(CListener c){
        done=c;
    }
    
    
    
}
