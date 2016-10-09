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
     * ERR:: the function will not work if single words are longer than the
     *      maxPixelWidth
     * 
     * @param text the long String to be formatted into multiple lines
     * @param font the font that is being used to draw the String text
     * @param maxPixelWidth the max length of one line in pixels
     * @return an ArrayList of type String that contains each line that should
     *      be displayed based on how long each line is in pixels
     */
    public static ArrayList<String> formatString(String text,Font font,int maxPixelWidth){
        ArrayList<String> r=new ArrayList<String>();
        int b=0;
        if(getPixelWidth(text,font)<=maxPixelWidth){ //defualt case just works
            r.add(text);
            return r;
        }/////////////
        //PRE [to this point in flow]:: the string is longer than what can be output in one line properly
//        System.out.println("stringTools text: "+text);
//        System.out.println(maxPixelWidth+"maxpixelwidth");
        int lastLength=0;
        boolean totalDone=false;
        while(!totalDone
                &&text.length()>0
                &&lastLength!=text.length()){
            lastLength=text.length();
            boolean done=false,alreadyAdded=false;
            int tempLength=0,tmp=0;
            String toAdd=text.substring(0,text.indexOf(" ")),
                    temp="";
//            System.out.println("toAdd:: "+toAdd+"-d");
            if(toAdd.length()>0)
            text=text.substring(tempLength=(toAdd.length()+1));//   * *  * * take out templegnth was used for the substring at the end but now is the temp variable
//            System.out.println("text is now:: -"+text);
            
            while(!done){
//                System.out.println(text.indexOf(" ")+" index of space");wwwwwww
//                System.out.println("111111 going thru the loop!"+text+"555"
//                        +text.indexOf(" ")
//                        +text.substring(0,tmp=text.indexOf(" ")));
                if(text.indexOf(" ")==-1){//out of spaces and is done
                    if(getPixelWidth(toAdd+" "+text,font)<maxPixelWidth){//can fit in the current line
                        toAdd+=" "+text;
                    }else{//cannot fit on the current line; must start on a new one
                        r.add(toAdd);//must call this here so that it is in the proper order
                        r.add(text);
                        alreadyAdded=true;
                    }
                    done=totalDone=true;
                }else if(text.indexOf(" ")==0){//there is a space on the first character of the String text; will be deleted
                    text=text.substring(1,text.length());
                }else if(getPixelWidth(toAdd+" "+(temp=text.substring(0,text.indexOf(" "))),font)<maxPixelWidth
                        /*&&temp.length()>0*/){
                    toAdd+=" "+temp;//toAdd will not have a space at the end of it ** ** - is this necessary? if not, add in +" " where toAdd is defined for each loop
                    text=text.substring(temp.length()+1);
//                    System.out.println(temp+" was taken out from text");
                }else{
//                    System.out.println("done!! "+temp.length());
                    done=true;
//                    text+=temp;
                }
            }
//            text=text.substring(temp.length()+1);//will exclude the next space
//            System.out.println("after substring: "+text+" toAdd.length(): "+toAdd.length());
            if(!alreadyAdded)
                r.add(toAdd);
        }
        System.out.print("text split::");
        for(String a:r)
            System.out.print(a+",,, ");
        System.out.println();
        
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
    
    /**
     * 
     * @param number
     * @param digits
     * @return 
     */
    public static String numToDigits(int number,int digits){
        String r=number+"";
        while(r.length()<digits)
            r="0"+r;
        return r;
    }
    
}
