/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author Josh
 */
public class StringTools {
    
    /**
     * This function is used for the input of a long String that should be  
     *  condensed into several lines for the user interface, but each line 
     *  should not be longer than maxPixelWidth pixels long. 
     * 
     * @param text the long String to be formatted into multiple lines
     * @param font the font that is being used to draw the String text
     * @param maxPixelWidth the max length of one line in pixels
     * @return an ArrayList of type String that contains each line that should
     *      be displayed based on how long each line is in pixels
     */
    public ArrayList<String> formatString(String text,Font font,int maxPixelWidth){
        ArrayList<String> r=new ArrayList<String>();
        
        return r;
    }
    
    /**
     * This function will return an integer value of how many pixels long a 
     *  String text will be by using Font font
     * 
     * @param text the line of text that is being used with the given Font
     *      font to find the width of
     * @param font the font that is being used to draw the String text
     * @return the integer value of how long in pixels the String will be
     */
    private int getPixelWidth(String text,Font font){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        return (int)(font.getStringBounds(text,frc).getWidth());
    }
    
}
