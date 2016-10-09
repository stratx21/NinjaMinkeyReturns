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
    private static BufferedImage importFromString(String loc){
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
        img.add(importFromString("Graphics/TopDown/grass.png"));
        img.add(importFromString("Graphics/TopDown/ForestTree.png"));
        img.add(importFromString("Graphics/TopDown/redish.png"));
        return img;
    }
    
    public static ArrayList<BufferedImage> importTopDownAIImages(int ai_ID){
        ArrayList<BufferedImage> img=new ArrayList<>();
        String id=StringTools.numToDigits(ai_ID,3);
        
        img.add(importFromString("Graphics/TopDown/AI/"+id+"U"+0+".png"));//idle up
        img.add(importFromString("Graphics/TopDown/AI/"+id+"D"+0+".png"));//idle down
        img.add(importFromString("Graphics/TopDown/AI/"+id+"L"+0+".png"));//idle left
        img.add(importFromString("Graphics/TopDown/AI/"+id+"R"+0+".png"));//idle right
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"U"+i+".png"));//travel up 1 - 5
        img.add(importFromString("Graphics/TopDown/AI/"+id+"U"+0+".png"));//travel up 5
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"L"+i+".png"));//travel 
        img.add(importFromString("Graphics/TopDown/AI/"+id+"L"+0+".png"));//travel 
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"R"+i+".png"));//travel 
        img.add(importFromString("Graphics/TopDown/AI/"+id+"R"+0+".png"));//travel 
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"D"+i+".png"));//travel 
        img.add(importFromString("Graphics/TopDown/AI/"+id+"D"+0+".png"));//travel 
        
        return img;
    }
    
    public static ArrayList<BufferedImage> importTopDownPlayerImages(){
        ArrayList<BufferedImage> img=new ArrayList<>();
        img.add(importFromString("Graphics/TopDown/Player/U0.png"));//idle up
        img.add(importFromString("Graphics/TopDown/Player/L0.png"));//idle left
        img.add(importFromString("Graphics/TopDown/Player/R0.png"));//idle right
        img.add(importFromString("Graphics/TopDown/Player/D0.png"));//idle down
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/Player/U"+i+".png"));//travel up 4-9
        img.add(importFromString("Graphics/TopDown/Player/U0.png"));
        
        for(int i=0;i<5;i++)
            img.add(importFromString("Graphics/TopDown/Player/L"+i+".png"));//travel left - 10-14
        
        for(int i=0;i<5;i++)
            img.add(importFromString("Graphics/TopDown/Player/R"+i+".png"));//travel left - 15-19
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/Player/D"+i+".png"));//
        img.add(importFromString("Graphics/TopDown/Player/D0.png"));//travel down 
        
        
        
        
        return img;
    }    
    
    public static BufferedImage getTopDownPromptImage(){
        return importFromString("Graphics/Prompt.png");
    }
    
    
}
