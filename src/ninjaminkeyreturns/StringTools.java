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
    public static ArrayList<String> formatString(String text,Font font,int maxPixelWidth){
        ////
        //// Note progamer doesn't work if any single words are londer than the maxPixelWidth
        ////
        
        ////
        //// Note untested removde this upon testing
        ////
        
        ////
        //// Note FAIL DANGEROUS
        ////
        
        ////
        //// JOSH DELETE WHEN YOU READ THIS 9/19
        ////
        ArrayList<String> r=new ArrayList<String>();
        int ll=0;
        if(getPixelWidth(text,font)<=maxPixelWidth){ //defualt case just works
            r.add(text);
            return r;
        }
        while(getPixelWidth(text,font)>maxPixelWidth){
            
            if(ll!=0){ //first restet of text
                
                if(getPixelWidth((text.substring(0,text.lastIndexOf(" "))),font)>maxPixelWidth){ //if removing one letter fails
                    
                    ll=text.lastIndexOf(" ")-1;
                    
                }else{//removing one word works
                    
                
                    r.add(text.substring(0,text.lastIndexOf(" ")));
                    
                    if(text.lastIndexOf(" ")+1<=text.length()){// prevents out of bound in cse of the string ending in a space
                        
                        text=text.substring(text.lastIndexOf(" ")+1);//restart chain for removed words
                    
                    }
                    else{
                    
                        text=text.substring(text.lastIndexOf(" ")); //restart chain for removed words
                    
                    }
                    ll=0; 
                            if(getPixelWidth(text,font)<=maxPixelWidth){//removed section is short enough
                                r.add(text);
                                return r;
                            }
                }
                }else{ //if more than one word needs to be removed
                
                
                if(getPixelWidth(
                        (text.substring(0,text.lastIndexOf(" ",ll))),font)>maxPixelWidth){
                    ll=text.lastIndexOf(" ",ll)-1;
                    
                }else{
                    
                    r.add(text.substring(0,text.lastIndexOf(" ",ll)));
                    text=text.substring(text.lastIndexOf(" ",ll)+1);
                    ll=0;
                            if(getPixelWidth(text,font)<=maxPixelWidth){
                                r.add(text);
                                return r;
                            }
            }
            
        }
        }
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
    private static int getPixelWidth(String text,Font font){
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
        return (int)(font.getStringBounds(text,frc).getWidth());
    }
    
}
