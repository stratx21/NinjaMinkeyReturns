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
public class StartupPrompt extends NavigablePrompt{

    
    /**
     * This sets up the StartupPrompt while bringing in a CListener for when
     *  the StartupPrompt is done and a boolean for if the prompt should
     *  show the player's statistical data.
     * 
     * @param showStats the boolean that determines if the Prompt will display
     *      the user's statistical data along with the Prompt
     * @param don the CListener evoked when the Prompt is finished
     */
    public StartupPrompt(boolean showStats, CListener don) {
        super(showStats, don);
        
        choices=new Option[]{
            new Option("Start",new CListener(){
                @Override
                public void actionPerformed(){
                    done.actionPerformed(0);
                }
            }),
            new Option("Load",new CListener(){
                @Override
                public void actionPerformed(){
                    chooseAgain=10;
                    done.actionPerformed(1);
                }
            }),
            new Option("Options",new CListener(){
                @Override
                public void actionPerformed(){
                    done.actionPerformed(2);
                }
            }),
            new Option("Quit",new CListener(){
                @Override
                public void actionPerformed(){
                    done.actionPerformed(3);
                }
            })
        
        };
    }
    
}
