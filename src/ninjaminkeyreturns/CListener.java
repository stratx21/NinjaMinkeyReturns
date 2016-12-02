/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.image.BufferedImage;

/**
 * This class is designed to have functions overriden in order to control the
 *  flow of code.
 *
 * @author Josh
 */
public class CListener {

    /**
     *
     */
    public void actionPerformed(){}

    /**
     *
     * @param a
     */
    public void actionPerformed(boolean a){}

    /**
     *
     * @param a
     */
    public void actionPerformed(int a){}

    /**
     *
     * @param a
     */
    public void actionPerformed(byte a){}

    /**
     *
     * @param player
     * @param AI
     * @param text
     */
    public void actionPerformed(BufferedImage player,BufferedImage AI,String text){}

    /**
     *
     * @param player
     * @param AI
     * @param text
     * @param id
     */
    public void actionPerformed(BufferedImage player,BufferedImage AI,String text,int id){}
}
