/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/**
 *
 * @author Josh Holland
 */
public class GraphicsAssets {

    /**
     *
     */
    public static ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();//unused later?
    
    
    /**
     * This function imports the startup images for the credits when the game
     *  first starts up. 
     * 
     * @return the BufferedImage instances of the images used as the credits
     *       at the beginning of the game
     */
    public static BufferedImage[] importStartupImages(){
        return new BufferedImage[]{
            importFromString("Graphics/Startup/1.png"),
            importFromString("Graphics/Startup/2.png"),
            importFromString("Graphics/Startup/3.png")
        };
    }
    
    /**
     * This function imports the BufferedImage instances that are used as the 
     *  instructions for a new game. 
     * 
     * @return the BufferedImage instance used as the instructions at the   
     *      beginning of a new game
     */
    public static BufferedImage[] importInstructionImages(){
        return new BufferedImage[]{
            importFromString("Graphics/Startup/4.png"),
            importFromString("Graphics/Startup/5.png"),
            importFromString("Graphics/Startup/3.png")
        };
    }
    
    /**
     * This function imports the image specified by the String location
     * 
     * @param loc the file location from src/ of the specified image file
     * @return the BufferedImage file information to use in the code
     * @throws IOException if the image could not be found
     */
    private static BufferedImage importFromString(String loc){
        try{
        InputStream in=GraphicsAssets.class.getResource(loc).openStream();
        if(in!=null)
            return ImageIO.read(in);
        else{
            System.err.println("did not find the image at "+loc);
            return null;
        }
        } catch(Exception e ){
            ErrorLogger.logEvent("GraphicsAssets.importFromString(String) failed to import::  "+loc);
            //e.printStackTrace();
        }
        //(BufferedImage)(ImageIO.read(GraphicsAssets.class.getResourceAsStream(loc)));
        return null;
    }
    
    /**
     * This function imports the image needed for the specified building. 
     * 
     * @param name the ID of the building in the form of a String
     * @return the BufferedImage instance requested for the name
     */
    public static BufferedImage importBuildingImage(String name){
        return importFromString("Graphics/Buildings/"+name+".png");
    }
    
    /**
     * This function imports the images for a specified top down region.
     * 
     * @param regionID the ID of the region to tell which one to import
     * @return an ArrayList of type BufferedImage containing the images for the
     *      specified region
     */
    public static ArrayList<BufferedImage> importRegionImagesTopDown(int regionID){
        ArrayList<BufferedImage> img=new ArrayList<>();
        String id=StringTools.numToDigits(regionID,3);
        
        //System.out.println("region ID:: "+id);
        
        img.add(importFromString("Graphics/TopDown/Terrain/terrain"+id+".png"));
        img.add(importFromString("Graphics/TopDown/Terrain/tree"+id+".png"));
        img.add(importFromString("Graphics/TopDown/Terrain/trigger"+id+".png"));
        return img;
    }
    
    /**
     * This function imports the images for the AI in a top down mode specified.
     * 
     * @param ai_ID the ID of the AI to import 
     * @return an ArrayList of type BufferedImage containing the images for the
     *      specified AI
     */
    public static ArrayList<BufferedImage> importTopDownAIImages(int ai_ID){
        ArrayList<BufferedImage> img=new ArrayList<>();
        String id=StringTools.numToDigits(ai_ID,4);
        
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/U"+0+".png"));//idle up
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/D"+0+".png"));//idle down
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/L"+0+".png"));//idle left
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/R"+0+".png"));//idle right
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"/U"+i+".png"));//travel up 1 - 5
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/U"+0+".png"));//travel up 5
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"/L"+i+".png"));//travel 
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/L"+0+".png"));//travel 
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"/R"+i+".png"));//travel 
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/R"+0+".png"));//travel 
        
        for(int i=0;i<4;i++)
            img.add(importFromString("Graphics/TopDown/AI/"+id+"/D"+i+".png"));//travel 
        img.add(importFromString("Graphics/TopDown/AI/"+id+"/D"+0+".png"));//travel 
        
        return img;
    }
    
    /**
     * This function imports the images for the player in a top down mode specified.
     * 
     * @return an ArrayList of type BufferedImage containing the images for the
     *      specified player
     */
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
    
    /**
     * This function gets the outline image that is used for the prompts.
     * 
     * @return the outline image in the form of a BufferedImage
     */
    public static BufferedImage getTopDownPromptImage(){
        return importFromString("Graphics/Prompt.png");
    }
    
    /**
     * This function imports the images for the player in side view.
     * 
     * @return an ArrayList of type  BufferedImage for the images of the 
     *      player in side view
     */
    public static ArrayList<BufferedImage> importSideViewPlayerImages(){
        ArrayList<BufferedImage> img=new ArrayList<>();
        
        //Walking::
        for(int i=0;i<8;i++)
            img.add(importFromString("Graphics/SideView/Player/R"+i+".png"));
        
        for(int i=0;i<8;i++)
            img.add(importFromString("Graphics/SideView/Player/L"+i+".png"));
        
        //Jumping::
        for(int i=0;i<8;i++)
            img.add(importFromString("Graphics/SideView/Player/JR"+i+".png"));
        
        for(int i=0;i<8;i++)
            img.add(importFromString("Graphics/SideView/Player/JL"+i+".png"));
        
        //Attacking:: (melee with swords)
        for(int i=0;i<15;i++)
            img.add(importFromString("Graphics/SideView/Player/AR"+i+".png"));
        
        for(int i=0;i<15;i++)
            img.add(importFromString("Graphics/SideView/Player/AL"+i+".png"));
        
        //Attacking:: (ranged with the banana)
        
        for(int i=0;i<15;i++)
            img.add(importFromString("Graphics/SideView/Player/BR"+i+".png"));
        
        for(int i=0;i<15;i++)
            img.add(importFromString("Graphics/SideView/Player/BL"+i+".png"));
        
        img.add(importFromString("Graphics/SideView/Player/Projectile.png"));//index # 92
        
        return img;
    }
    
    /**
     * This function imports the images for a specified side view region.
     * 
     * @param regionID the ID of the region to tell which one to import
     * @return an ArrayList of type BufferedImage containing the images for the
     *      specified region
     */
    public static ArrayList<BufferedImage> importRegionImagesSideView(int regionID){
        ArrayList<BufferedImage> img=new ArrayList<>();
        String id=StringTools.numToDigits(regionID,3);
        
        System.out.println("region ID:: "+id);
        
        img.add(importFromString("Graphics/SideView/Terrain/upperLayer"+id+".png"));
        img.add(importFromString("Graphics/SideView/Terrain/lowerLayer"+id+".png"));
        return img;
    }
    
    /**
     * This function imports the BufferedImage used for the background in side
     *  view game play mode. 
     * 
     * @param regionID the ID of the side view region for which the image is
     *      being imported
     * @return the BuffferedImage used as the background for the specified side
     *      view region
     */
    public static BufferedImage importSideViewBackground(int regionID){
        return importFromString("Graphics/SideView/Background/"+StringTools.numToDigits(regionID,3)+".png");
    }
    
    /**
     * This function imports the images for the specified Melee Enemy in side 
     *  view.
     * 
     * @param AI_ID the ID of the AI for which the images are being imported
     * @return an ArrayList of type  BufferedImage for the images of the 
     *      AI in side view
     */
    public static ArrayList<BufferedImage> importSideViewMeleeEnemyImages(int AI_ID){
        ArrayList<BufferedImage> img=new ArrayList<>();
        
        String id=StringTools.numToDigits(AI_ID,4);
        
        //Walking::
        for(int i=0;i<3;i++)
            img.add(importFromString("Graphics/SideView/AI/"+id+"/WR"+i+".png"));
        
        for(int i=0;i<3;i++)
            img.add(importFromString("Graphics/SideView/AI/"+id+"/WL"+i+".png"));
        
        //attacking::
        for(int i=0;i<7;i++)
            img.add(importFromString("Graphics/SideView/AI/"+id+"/AR"+i+".png"));
        
        for(int i=0;i<7;i++)
            img.add(importFromString("Graphics/SideView/AI/"+id+"/AL"+i+".png"));
        
        
        return img;
    }
    
    /**
     * This function imports the images for the specified Ranged Enemy in side 
     *  view.
     * 
     * @param AI_ID the ID of the AI for which the images are being imported
     * @return an ArrayList of type  BufferedImage for the images of the 
     *      AI in side view
     */
    public static ArrayList<BufferedImage> importSideViewRangedEnemyImages(int AI_ID){
        return null;
    }
    
    /**
     * This function imports the images for the specified Air Enemy in side 
     *  view.
     * 
     * @param AI_ID the ID of the AI for which the images are being imported
     * @return an ArrayList of type  BufferedImage for the images of the 
     *      AI in side view
     */
    public static ArrayList<BufferedImage> importSideViewAirEnemyImages(int AI_ID){
        return null;
    }
    
    
    
}
