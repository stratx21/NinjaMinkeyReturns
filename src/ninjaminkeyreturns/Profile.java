/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Josh
 */
public class Profile {
    
    /**
     * This specifies the certain characters that are used for the controls that
     *  the user can use; they are, by respective index starting at 0: 
     *      up down left right attack other attack.
     * 
     * NOTE:: the default controls are:: 'W','S','A','D','J','K'
     */
    public static char[] controls=new char[]{ 
        'W','S','A','D','J','K','P'//DEFAULT
    };
    
    
    /**
     * This array of booleans hold the information about the game progress
     *  concerning what missions the player has completed. 
     */
    public static ArrayList<Boolean> completedMissions=new ArrayList<>();
    
    /**
     * This array holds the last known location of the user for the sake of
     *  saving the game so that the player will start off wherever they ended
     *  the game. 
     */
    public static int[] playerLocation=new int[]{19,20};
    
    /**
     * This integer holds the last known region that the user was in for the
     *  sake of saving the game so that the player will start off wherever
     *  they ended the game. 
     */
    public static int lastKnownRegionTopDown=0;
    
    public static boolean soundOn=true;
    
    /**
     * This double holds the last known health value that the user had while 
     *  still in top down view for the sake of saving the game so that the
     *  player will keep however much health that they had before they quit or
     *  before they went into a side view mission. 
     */
    public static double health=200.0;
    
    /**
     * The maximum possible health value.
     */
    public final static double MAX_HEALTH=200.0;
    
    /**
     * prices for each upgrade; each set includes the prices for the upgrades
     * menu for each type of car, an empty space between each set for type
     * of car
     */
    
    /**
     * This is the file used to input the data from a save location.
     * 
     */
    public static File inputSaveFile=null;
    
    /**
    * Money the user has; the value here, 51.00, if not influenced by an import of data
    * by save file, is the default amount of money the user will start with in
    * a new game. 
    */
    public static double money=51.00;
    
    public static void startNewGame(){
        health=MAX_HEALTH;
        lastKnownRegionTopDown=0;
        playerLocation=new int[]{19,20};
    }
    
    public static void setSound(boolean a){
        soundOn=a;
        if(!a&&AudioAssets.music!=null)
            AudioAssets.music.stop();
        else if(a)
            AudioAssets.play("MainSong");
    }
    
    public static void setAllMissionsToFalse(){
        Collections.fill(completedMissions,Boolean.FALSE);
    }
    
    public static boolean getCompletedMission(int index){
        if(index>=completedMissions.size()){
            System.err.println("|----ERROR:: the save file index provided, "+index+", was invalid... The size is "+completedMissions.size()+"  ---|");
            return true;
        }
        return completedMissions.get(index);
    }
    
    /**
     * This function saves the current progress in a save file, using the 
     *  JFileChooser to let the user identify a name and where to save it.
     */
    public static void save(){
        JFileChooser sv=new JFileChooser();
        if(sv.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
        try{
            try (FileWriter save = new FileWriter(sv.getSelectedFile()+".txt")) {
                System.out.println(sv.getSelectedFile());//returns file name and the directory location
                //save.write......
                
                
                
                save.write((int)health+":"+lastKnownRegionTopDown+":"+playerLocation[0]+":"+playerLocation[1]+":");
                
            } //returns file name and the directory location
            //save.write......
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    }
    
    /**
     * This opens the save file specified by the user in the JFileChooser, and
     * imports the data from it to give the user their progress back. 
     * 
     * @return a boolean value concerning if the file import worked properly
     * @throws Exception Exception thrown if the file is not found for the 
     * Scanner "in" uses to import the data from the save file specified by
     * the user through the JFileChooser
     */
    public static boolean open() throws Exception{
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                inputSaveFile=fc.getSelectedFile();
            }
        if(inputSaveFile!=null){
            String[] in=new Scanner(inputSaveFile).nextLine().split(":");
            health=Integer.parseInt(in[0]);
            lastKnownRegionTopDown=Integer.parseInt(in[1]);
            playerLocation=new int[]{Integer.parseInt(in[2]),Integer.parseInt(in[3])};
//            for(int i=0;i<in.length;i++)
//                System.out.println(in[i]);
            for(int i=0;i<in.length;i++)
                System.out.print("input["+i+"] == "+in[i]);
            
            System.out.println();

        return true;
        } else return false;
    }
    
     /**
     * 
     * This function imports the data needed for the definition of the 
     *  information that is needed about the top down region specified by the
     *  integer region.
     * 
     * @param region the region ID for which the information is being imported
     * @return a 2-dimensional array of integers containing the imported data
     */
    public static int[][] importRegionDataTopDown(int region){
        try{
            int[][] data=null;
            String[][] input;// note:: [x][y]
            int a,b;
            inputSaveFile=new File(Profile.class.getResource("RegionData/TopDown/R"+StringTools.numToDigits(region,3)+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            ErrorLogger.logEvent("importing the data for top down region...");
            if(inputSaveFile!=null){
                String[] in=scan.nextLine().split(",");//import size of array
                data=new int[a=Integer.parseInt(in[0])][b=Integer.parseInt(in[1])];//[x][y]
                input=new String[b][a];//[y][x]

                for(int i=0;i<b;i++){
                    input[i]=scan.nextLine().split(":");
                }
                
                //put into the data array but also reverse the [y][x] to be [x][y]
                for(int y=0;y<input.length;y++)
                    for(int x=0;x<input[y].length;x++){
                        data[x][y]=Integer.parseInt(input[y][x]);
                    }
                
                ErrorLogger.logEvent("finished import of top down region successfully.");
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importRegionDataTopDown");
        }
        return null;
    }
    
    public static ArrayList<Building> importTopDownBuildings(int region){
        try{
            ArrayList<Building> data=new ArrayList<>();
            String[][] input;// note:: [x][y]
            int a,b;
            inputSaveFile=new File(Profile.class.getResource("RegionData/Buildings/"+StringTools.numToDigits(region,3)+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            ErrorLogger.logEvent("importing the data for top down buildings...");
            if(inputSaveFile!=null){
                
                while(scan.hasNextLine()){
                    String[] in=scan.nextLine().split(",");
                    data.add(new Building(GraphicsAssets.importBuildingImage(in[0]),Integer.parseInt(in[1]),Integer.parseInt(in[2])));
                }
                
                System.out.println("finished import of top down region successfully.");
                return data;
            }
            return data;
        }catch(Exception e){
            e.printStackTrace();
            ErrorLogger.logEvent("Buildings failed to import for region "+region);
        }
        return null;
    }
    
    
    
    /**
     * 
     * This function imports the data needed for the definition of the 
     *  information that is needed about the side view region specified by the
     *  integer region.
     * 
     * @param region the region ID for which the information is being imported
     * @return a 2-dimensional array of integers containing the imported data
     */
    public static int[][] importRegionDataSideView(int region){
        try{
            int[][] data=null;
            String[][] input;// note:: [x][y]
            int a,b;
            inputSaveFile=new File(Profile.class.getResource("RegionData/SideView/R"+StringTools.numToDigits(region,2)+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            if(inputSaveFile!=null){
                String[] in=scan.nextLine().split(",");//import size of array
                
                data=new int[a=Integer.parseInt(in[0])][b=Integer.parseInt(in[1])];//[x][y]
                input=new String[b][a];//[y][x]
                

                for(int i=0;i<b;i++){
                    input[i]=scan.nextLine().split(":");
                    //System.out.println(input[i]);
                }
                
                
                //put into the data array but also reverse the [y][x] to be [x][y]
                for(int y=0;y<input.length;y++)
                    for(int x=0;x<input[y].length;x++){
                        data[x][y]=Integer.parseInt(input[y][x]);
                    }
                
                //System.out.println("data :: "+data);
                return data;
            }
            //System.out.println("data :: "+data);
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importRegionDataSideView");
        }
        System.out.println("RETURNING NULL in Profile.importRegionDataSideView");
        return null;
    }
    
    /**
     * 
     * This function imports the data needed for the definition of the 
     *  information that is needed about the side view mission specified by the
     *  integer region.
     * 
     * @param region the mission ID for which the information is being imported
     * @return a 2-dimensional array of integers containing the imported data
     */
    public static int[] getSideViewMissionData(int region){
        try{
            int[] data=null;
            String[] input;
            inputSaveFile=new File(Profile.class.getResource("RegionData/SideView/R"+region+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            if(inputSaveFile!=null){
                String[] in=scan.nextLine().split(",");//import size of array 
                input=scan.nextLine().split(":");
                data=new int[input.length];

                for(int i=0;i<data.length;i++){
                    data[i]=Integer.parseInt(input[i]);
                }
                
                
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.getSideViewMissionData");
        }
        return null;
    }
    
    /**
     * 
     * This function imports the data needed for the definition of the 
     *  information that is needed about the top down trigger spots specified 
     *  by the integer region.
     * 
     * @param newRegion the region ID for which the information is being 
     *  imported
     * @return a 2-dimensional array of integers containing the imported data
     */
    public static ArrayList<TriggerSpot> importTriggerSpotsTopDown(int newRegion){
        try{
            ArrayList<TriggerSpot> data=new ArrayList<>();
            int[] opp=new int[10];
            inputSaveFile=new File(Profile.class.getResource("RegionData/TopDown/T"+StringTools.numToDigits(newRegion,3)+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            if(inputSaveFile!=null){
                int triggers=Integer.parseInt(scan.nextLine());//number of trigger spots in this list
                
                String[] input=new String[0];
                
                for(int i=0;i<triggers;i++){
                    input=scan.nextLine().split(",");
                    for(int j=0;j<input.length;j++){
                        opp[j]=Integer.parseInt(input[j]);
                    }
                    System.out.println("For current top down region "+StringTools.numToDigits(newRegion,3)+" trigger spot #"+i+" :: "+opp[0]+" 1d "+opp[1]+" 2d "+opp[2]+" 3d "+opp[3]+" 4d "+opp[4]+" 5d "+opp[5]+" 6d "+opp[6]+" 7d "+opp[7]+" 8d "+opp[8]+" 9d "+opp[9]+" 10d ");
                    data.add(new TriggerSpot(opp[1],opp[2],opp[3],opp[4],opp[0],opp[5]==1,opp[6],opp[7],opp[8],opp[9]));
                }
                
                
                
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importTriggerSpotsTopDown");
            System.out.println(e);
        }
        return null;
    }
    
    /**
     * 
     * This function imports the data needed for the definition of the 
     *  information that is needed about the top down AIs specified by the
     *  integer region.
     * 
     * @param newRegion the region ID for which the information is being 
     *  imported
     * @return a 2-dimensional array of integers containing the imported data
     */
    public static ArrayList<TopDownAI> importAIDataTopDown(int newRegion){
        ArrayList<TopDownAI> data=new ArrayList<>();
        try{
            
            int[] a=new int[12];
            inputSaveFile=new File(Profile.class.getResource("AIData/TopDown/"+StringTools.numToDigits(newRegion,3)+".txt").toURI());
            
            if(inputSaveFile!=null){
                Scanner scan=new Scanner(inputSaveFile);
                int AIs=Integer.parseInt(scan.nextLine());//number of AI spots in this list
                
                String[] input=new String[0];
                
                for(int i=0;i<AIs;i++){
                    input=scan.nextLine().split(",");
                    
                    for(int j=0;j<9;j++)
                        a[j]=Integer.parseInt(input[j]);
                    a[7]=Integer.parseInt(input[11]);
                    System.out.println("aaa 7 ====="+a[7]);
                    data.add(new TopDownAI(a[0],a[1],a[2],a[3],input[9],input[10],a[6],a[4],a[5],a[7],a[8],a[7]));
                }
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importAIDataTopDown");
        }
        return data;
    }
    
    /**
     * 
     * This function imports the data needed for the definition of the 
     *  information that is needed about the side view AI specified by the
     *  integer AI_ID.
     * 
     * @param regionID the AI ID for which the information is being 
     *  imported
     * @return a 2-dimensional array of integers containing the imported data
     */
    public static ArrayList<SideViewAI> importAISideView(int regionID){
        try{
            ArrayList<SideViewAI> data=new ArrayList<>();
            
            inputSaveFile=new File(Profile.class.getResource("AIData/SideView/"+StringTools.numToDigits(regionID,2)+".txt").toURI());
            
            if(inputSaveFile!=null){
                Scanner scan=new Scanner(inputSaveFile);
                
                String[] input=new String[0];
                input=scan.nextLine().split(",");
                
                int[] a=new int[input.length];  
                
                for(int j=0;j<input.length;j++)
                    a[j]=Integer.parseInt(input[j]);
                
                switch(a[0]){
                    case 1: //melee
                        for(int i=4;i<input.length;i++)
                            data.add(new SideViewMeleeEnemy(a[1],a[2],a[3],a[i]*20));
                        break;
                    case 2:
                        for(int i=4;i<input.length;i++)
                            data.add(new SideViewRangedEnemy(a[1],a[2],a[3],a[i]*20));
                        break;
                    case 3:
                        for(int i=4;i<input.length;i++)
                            data.add(new SideViewAirEnemy(a[1],a[2],a[3],a[i]*20));
                        break;
                }                
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importAIDataSideView");
        }
        return null;
    }
    
}

