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
        int b=0;
        if(getPixelWidth(text,font)<=maxPixelWidth){ //defualt case just works
            r.add(text);
            return r;
        }/////////////
        //PRE [to this point in flow]:: the string is longer than what can be output in one line properly
//        System.out.println("stringTools text: "+text);
        System.out.println(maxPixelWidth+"maxpixelwidth");
        int lastLength=0;
        while(text.length()>0
                &&lastLength!=text.length()){
            lastLength=text.length();
            boolean done=false;
            int tempLength=0;
            String toAdd=text.substring(0,text.indexOf(" ")),
                    temp="";
            System.out.println("toAdd:: "+toAdd+"-d");
            if(toAdd.length()>0)
            text=text.substring(tempLength=(toAdd.length()+1));//   * *  * * take out templegnth was used for the substring at the end but now is the temp variable
            System.out.println("text is now:: -"+text);
            
            while(!done){
//                System.out.println(text.indexOf(" ")+" index of space");
                System.out.println("111111 going thru the loop!"+text+"555"
                        +text.indexOf(" ")
                        +text.substring(0,text.indexOf(" ")));
                if(getPixelWidth(toAdd+" "+(temp=text.substring(0,text.indexOf(" "))),font)<maxPixelWidth
                        /*&&temp.length()>0*/){
                    toAdd+=" "+temp;
                    text=text.substring(temp.length()+1);
                    System.out.println(temp+" was taken out from text");
                }else{
                    System.out.println("done!! "+temp.length());
                    done=true;
                    text+=temp;
                }
            }
//            text=text.substring(temp.length()+1);//will exclude the next space
            System.out.println("after substring: "+text+" toAdd.length(): "+toAdd.length());
            r.add(toAdd);
        }
        for(String a:r)
            System.out.println("array:"+a);
        
        return r;
        
//        while(getPixelWidth(text,font)>maxPixelWidth){
//            if(b!=0){ //first restet of text
//                
//                if(getPixelWidth((text.substring(0,text.lastIndexOf(" "))),font)>maxPixelWidth){ //if removing one letter fails
//                    
//                    b=text.lastIndexOf(" ")-1;
//                    
//                }else{//removing one word works
//                    
//                
//                    r.add(text.substring(0,text.lastIndexOf(" ")));
//                    
//                    if(text.lastIndexOf(" ")+1<=text.length()){// prevents out of bound in cse of the string ending in a space
//                        
//                        text=text.substring(text.lastIndexOf(" ")+1);//restart chain for removed words
//                    
//                    }
//                    else{
//                    
//                        text=text.substring(text.lastIndexOf(" ")); //restart chain for removed words
//                    
//                    }
//                    b=0; 
//                            if(getPixelWidth(text,font)<=maxPixelWidth){//removed section is short enough
//                                r.add(text);
//                                return r;
//                            }
//                }
//                }else{ //if more than one word needs to be removed
//                System.out.println("substring: "+text.indexOf(' ',b)+" text: "+text);
//                if(getPixelWidth((text.substring(0,text.indexOf(" ",b))),font)>maxPixelWidth){
//                    b=text.lastIndexOf(" ",b)-1;
//                    
//                }else{
//                    
//                    r.add(text.substring(0,text.lastIndexOf(" ",b)));
//                    text=text.substring(text.lastIndexOf(" ",b)+1);
//                    b=0;
//                            if(getPixelWidth(text,font)<=maxPixelWidth){
//                                r.add(text);
//                                return r;
//                            }
//            }
//            
//        }
//        }
//        return r;
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
        System.out.println((int)font.getStringBounds(text,frc).getWidth()+" font "+text);
        return (int)(font.getStringBounds(text,frc).getWidth());
    }
    
}
