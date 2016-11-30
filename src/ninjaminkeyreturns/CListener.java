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
    public void actionPerformed(){}
    public void actionPerformed(boolean a){}
    public void actionPerformed(int a){}
    public void actionPerformed(byte a){}
    public void actionPerformed(BufferedImage player,BufferedImage AI,String text){}
    public void actionPerformed(BufferedImage player,BufferedImage AI,String text,int id){}
}
