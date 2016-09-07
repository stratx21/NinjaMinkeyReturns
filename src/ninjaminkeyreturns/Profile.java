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
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Josh
 */
public class Profile {
    
    public static char[] controls=new char[]{ 
        'W','S','A','D','J','K'//DEFAULT
    };
    
    public static boolean soundEffectsOn=true,musicOn=true;
    
    public static boolean[] completedMissions=new boolean[16];//expand on this
    
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
    public static int[][] importRegionDataTopDown(int region) throws Exception{
        int[][] data;
        String[][] input;
        int a,b;
        inputSaveFile=new File("src/RegionData/TopDown/R"+region);
        if(inputSaveFile!=null){
            String[] in=new Scanner(inputSaveFile).nextLine().split(":");//import size of array
            data=new int[a=Integer.parseInt(in[0])][b=Integer.parseInt(in[1])];
            input=new String[a][b];
            
            for(int i=0;i<b;i++){
                
            }
        }
    }
    
}

