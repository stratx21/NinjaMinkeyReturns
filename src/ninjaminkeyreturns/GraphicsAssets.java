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
        img.add(importFromString("Graphics/TopDown/tree.png"));
        img.add(importFromString("Graphics/TopDown/redish.png"));
        return img;
    }
    
    public static ArrayList<BufferedImage> importTopDownPlayerImages(){
        ArrayList<BufferedImage> img=new ArrayList<>();
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//idle up
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//idle down
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//idle left
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//idle right
        
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel up 1 - 5
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel up 2
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel up 3
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel up 4
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel up 5
        
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel down 1 - 10
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel down 2
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel down 3
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel down 4
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel down 5
        
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel left 1 - 15
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel left 2
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel left 3
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel left 4
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel left 5
        
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel right 1 - 20
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel right 2
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel right 3
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel right 4
        img.add(importFromString("Graphics/TopDown/Player/playertesting.png"));//travel right 5
        
        
        return img;
    }    
    
    
}
