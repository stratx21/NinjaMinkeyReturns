/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/**
 *
 * @author Josh
 */
public class GraphicsAssets {
    public static ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();//unused later?
    
    /**
     * 
     */
    public static void importImages(){
        try{
            
        } catch(Exception e){
            ErrorLogger.logError(e,"GraphicsAssets.importImages");
        }
    }
    
    /**
     * 
     * @param loc
     * @return
     * @throws IOException 
     */
    private static BufferedImage importFromString(String loc) throws IOException{
        try{
        InputStream in=GraphicsAssets.class.getResource(loc).openStream();
        if(in!=null)
            return ImageIO.read(in);
        else
            return null;
        } catch(Exception e ){System.out.println("Graphics image failed to be read by the ImageIO::    "+loc);}
        //(BufferedImage)(ImageIO.read(GraphicsAssets.class.getResourceAsStream(loc)));
        return null;
    }
    
    public static BufferedImage getImage(int index){
        return images.get(index);
    }
    
    public static BufferedImage getIcon(){
        BufferedImage i=null;
//        try{
//            i=importFromString("Graphics/frameIcon.png");
//        } catch(IOException e){
//            ErrorLogger.logIOError(e,"getIcon() - GraphicsAssets");
//        }
        return i;
    }
    
    
    public static ArrayList<BufferedImage> importRegionImagesTopDown(int regionID){
        ArrayList<BufferedImage> img=new ArrayList<>();
        img.add(importFromString(""));
        return null;
    }
    
    
    
}
