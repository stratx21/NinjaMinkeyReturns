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
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Josh
 */
public class Profile {
    
    public static char[] controls=new char[]{ //up down left right attack other attack
        'W','S','A','D','J','K'//DEFAULT
    };
    
    public static boolean soundEffectsOn=true,musicOn=true;
    
    public static boolean[][] completedMissions=new boolean[9][16];//expand on this
    
    public static int[] playerLocation=new int[]{19,20};
    
    public static int currentRegionTopDown=0;
    
    public static double health=50.00;
    
    public static double MAX_HEALTH=50.00;
    
    /**
     * prices for each upgrade; each set includes the prices for the upgrades
     * menu for each type of car, an empty space between each set for type
     * of car
     */
    
    //file used to input the data from a save location
    public static File inputSaveFile=null;
    
    //money the user has; the value here, if not influenced by an import of data
    //by save file, is the default amount of money the user will start with in
    //a new game. 
    public static double money=30000.00;
    
    /**
     * save current progress in a save file, using the JFileChooser to let the
     * user identify a name and where to save it.
     */
    public static void save(){
        JFileChooser sv=new JFileChooser();
        if(sv.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
        try{
            try (FileWriter save = new FileWriter(sv.getSelectedFile()+".txt")) {
                System.out.println(sv.getSelectedFile());//returns file name and the directory location
                //save.write......
                
//                save.write(money+":");
//                for(int k=0;k<5;k++)
//                    for(int i=0;i<3;i++){
//                        for(int j=0;j<6;j++){
//                            save.write(upgrades[k][i][j]+":");
//                        }
//                    }
//                
//                for(int i=0;i<5;i++)
//                    save.write(boughtCars[i]+":");
//                
//                for(int i=0;i<completedMissions.length;i++)
//                    save.write(completedMissions[i]+":");
            } //returns file name and the directory location
            //save.write......
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    }
    
    /**
     * 
     * opens the save file specified by the user in the JFileChooser, and
     * imports the data from it to give the user their progress back. 
     * 
     * @throws Exception Exception thrown if the file is not found for the 
     * Scanner "in" uses to import the data from the save file specified by
     * the user through the JFileChooser
     */
    public static void open() throws Exception{
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                inputSaveFile=fc.getSelectedFile();
            }
        if(inputSaveFile!=null){
            String[] in=new Scanner(inputSaveFile).nextLine().split(":");

            for(int i=0;i<in.length;i++)
                System.out.println(in[i]);

            money=Double.parseDouble(in[0]);

//            int c=1;
//            for(int k=0;k<5;k++)
//                for(int i=0;i<3;i++)
//                    for(int j=0;j<6;j++){
//                        upgrades[k][i][j]=Boolean.parseBoolean(in[c]);
//                        c++;
//                    }
//            for(int i=0;i<5;i++){
//                boughtCars[i]=Boolean.parseBoolean(in[c]);
//                c++;
//            }
//
//            for(int i=0;i<completedMissions.length;i++){
//                completedMissions[i]=Boolean.parseBoolean(in[c]);
//                c++;
//            }
        }
    }
    
     /**
     * 
     * opens 
     * 
     * @throws Exception Exception thrown if the file is not found for the 
     * Scanner "in" uses to import the data from the save file specified by
     * the user through the JFileChooser
     */
    public static int[][] importRegionDataTopDown(int region){
        try{
            int[][] data=null;
            String[][] input;// note:: [x][y]
            int a,b;
            inputSaveFile=new File(Profile.class.getResource("RegionData/TopDown/R"+region+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
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
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importRegionDataTopDown");
        }
        return null;
    }
    
    public static int[][] importRegionDataSideView(int region){
        try{
            int[][] data=null;
            String[][] input;// note:: [x][y]
            int a,b;
            inputSaveFile=new File(Profile.class.getResource("RegionData/SideView/R"+region+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            if(inputSaveFile!=null){
                System.out.println("aaaaaaaaaaaa!gggg");
                String[] in=scan.nextLine().split(",");//import size of array
                
                data=new int[a=Integer.parseInt(in[0])][b=Integer.parseInt(in[1])];//[x][y]
                System.out.println("bbbbbbbbbbbb!gggg");
                input=new String[b][a];//[y][x]
                

                for(int i=0;i<b;i++){
                    input[i]=scan.nextLine().split(":");
                    System.out.println(input[i]);
                }
                
                
                //put into the data array but also reverse the [y][x] to be [x][y]
                for(int y=0;y<input.length;y++)
                    for(int x=0;x<input[y].length;x++){
                        data[x][y]=Integer.parseInt(input[y][x]);
                    }
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importRegionDataTopDown");
        }
        return null;
    }
    
    public static int[] getSideViewMissionData(int region){
        try{
            int[] data=null;
            String[] input;
            inputSaveFile=new File(Profile.class.getResource("RegionData/SideView/R"+region+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            if(inputSaveFile!=null){
                String[] in=scan.nextLine().split(",");//import size of array 
                input=scan.nextLine().split(": ");
                data=new int[input.length];

                for(int i=0;i<data.length;i++){
                    data[i]=Integer.parseInt(input[i]);
                }
                
                
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importRegionDataTopDown");
        }
        return null;
    }
    
    public static ArrayList<TriggerSpot> importTriggerSpotsTopDown(int newRegion){
        try{
//            System.out.println("NEW REGION=="+newRegion);
            
            ArrayList data=new ArrayList<TriggerSpot>();
            int[] a=new int[7];
            inputSaveFile=new File(Profile.class.getResource("RegionData/TopDown/T"+newRegion+".txt").toURI());
            Scanner scan=new Scanner(inputSaveFile);
            if(inputSaveFile!=null){
                int triggers=Integer.parseInt(scan.nextLine());//number of trigger spots in this list
                
                String[] input=new String[0];
                
//                System.out.println("past input creation");
                
                for(int i=0;i<triggers;i++){
                    input=scan.nextLine().split(",");
                    for(int j=0;j<input.length;j++)
                        a[j]=Integer.parseInt(input[j]);
                    data.add(new TriggerSpot(a[1],a[2],a[3],a[4],a[0],a[5]==1,a[6]));
                    
                }
                
//                System.out.println("past create triggers");
                
                
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importTriggerSpotsTopDown");
        }
        return null;
    }
    
    public static ArrayList<TopDownAI> importAIDataTopDown(int newRegion){
        try{
            ArrayList data=new ArrayList<TopDownAI>();
            int[] a=new int[7];
            inputSaveFile=new File(Profile.class.getResource("AIData/TopDown/AI"+newRegion+".txt").toURI());
            
            if(inputSaveFile!=null){
                Scanner scan=new Scanner(inputSaveFile);
                int AIs=Integer.parseInt(scan.nextLine());//number of AI spots in this list
                
                String[] input=new String[0];
                
                for(int i=0;i<AIs;i++){
                    input=scan.nextLine().split(",");
                    
                    for(int j=0;j<5;j++)
                        a[j]=Integer.parseInt(input[j]);
                    a[6]=Integer.parseInt(input[7]);//how is this 8???? it should be 7
                    data.add(new TopDownAI(a[0],a[1],a[2],a[3],a[4],input[5],input[6],a[6]));
                    //for(int y=0;y<input.length;y++)System.out.println("s:"+input[y]);
                }
                
                
                return data;
            }
            return data;
        }catch(Exception e){
            ErrorLogger.logError(e,"Profile.importAIDataTopDown");
        }
        return null;
    }
    
}

