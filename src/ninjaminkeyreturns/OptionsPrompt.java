package ninjaminkeyreturns;

import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josh
 */
public class OptionsPrompt extends NavigablePrompt{

    /**
     * This sets up the OptionsPrompt while bringing in a CListener for when
     *  the OptionsPrompt is done and a boolean for if the prompt should
     *  show the player's statistical data.
     * 
     * @param showStats the boolean that determines if the Prompt will display
     *      the user's statistical data along with the Prompt
     * @param don the CListener evoked when the Prompt is finished
     */
    public OptionsPrompt(boolean showStats, CListener don) {
        super(showStats, don);
        choices=new Option[]{
            new Option("Back",new CListener(){
               @Override
               public void actionPerformed(){
                   chooseAgain=10;
                   done.actionPerformed(0);
               }
            }),
            new Option("SoundOn",new CListener(){
                @Override
                public void actionPerformed(){
                    Profile.setSound(false);
                    Profile.setSound(true);
                }
            }),
            new Option("SoundOff",new CListener(){
                @Override
                public void actionPerformed(){
                    Profile.setSound(false);
                }
            })//,
//            new Option("Quit",new CListener(){
//                @Override
//                public void actionPerformed(){
//                    done.actionPerformed(2);
//                }
//            }),
//            new Option("Save",new CListener(){
//                @Override
//                public void actionPerformed(){
//                    done.actionPerformed(3);
//                }
//            })
        
        };
        //calculateIndent();
    }
    

    
}
