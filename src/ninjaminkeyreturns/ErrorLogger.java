/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;


import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Josh
 */
public class ErrorLogger {  
    private static String error,o;
    
    //---------Log error of type [Exception]::

    /**
     * This function is used to log an error. 
     *
     * @param e the error that occurred
     * @param f the name of the function, the location, from where the error
     *      originated.
     */
    protected static void logError(Exception e,String f){ //f= function name
        System.err.println("├┬┴┬┴ Error [Exception type used] in function<"+(o=f)
                +"> ┬┴┬┴┤ Error :: "+e);
        error=e+"";
        promptDeveloper(""+e+"\n*You can email the crash report to Ethan Dickey"
                + " at etdickey@gmail.com*");
    }
    
    /**
     *This function logs an event given by the String provided. 
     * 
     * @param f the explanation of the event to log 
     */
    protected static void logEvent(String f){ //f= function name
        System.out.println("=-Event-= ::  "+f);
        
        ArrayList<String> prv=new ArrayList<String>();
        int lns=0;
        try{
            Scanner in = new Scanner(new FileInputStream("Events.txt"));
            while(in.hasNextLine()){
                lns++;
                prv.add(in.nextLine());
            }
            
            in.close();
        }catch(IOException ioex){
            System.err.println("error using Scanner in ErrorLogger");
        }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Events."
                    +"txt"))) {
                Date date = new Date();
                
                for(int a=0;a<lns;a++){
                    writer.write(prv.get(a));
                    writer.newLine();
                }
                writer.newLine();
                
                writer.write("--------------------------------------------------------"
                        +"------------------------------------------");
                writer.newLine();
                writer.write("Event Occured at [YYYY/MM/DD HH:MM:SS]:: "+date);
                writer.newLine();
                writer.write("=-Event-= :: "+f);
                
                writer.newLine();
                writer.write("------------------------------------------------"
                        +"--------------------------------------------------");
                writer.newLine();
                writer.newLine();
            }catch(IOException ioex){
            System.err.println("Error in writing to LoggerErrors.txt file:: "
                    +ioex);
        }
    }
    
    //--------Log error fo type [IOException]::

    /**
     * This function logs an I/O Error.
     * 
     * @param e the IOException that was caused
     * @param f the location at which this error originated
     */
        protected static void logIOError(IOException e,String f){
        System.err.println("├┬┴┬┴ Error [IOException type used] in function<"+(o=f)
                +"> ┬┴┬┴┤ IO Error :: "+e);
        error=e+"";
        promptDeveloper(""+e+"\n*Emailing information can be found in the "
                + "text document*");
    }
    
    /**
     * This function writes the errors that have been recognized and caught
     *  by the system and adds the user/developer further information or 
     *  comments concerning the topic. 
     * 
     * @param err the error that evoked this log
     * @param comnt the user/developer comment or further information
     */
    private static void writeToFile(String err,String comnt){
        ArrayList<String> prv=new ArrayList<String>();
        int lns=0;
        try{
            Scanner in = new Scanner(new FileInputStream("LoggedErrors.txt"));
            while(in.hasNextLine()){
                lns++;
                prv.add(in.nextLine());
            }
            
            in.close();
        }catch(IOException ioex){
            System.err.println("error using Scanner in ErrorLogger");
        }
            try (BufferedWriter f = new BufferedWriter(new FileWriter("LoggedErrors."
                    +"txt"))) {
                Date date = new Date();
                
                for(int a=0;a<lns;a++){
                    f.write(prv.get(a));
                    f.newLine();
                }
                f.newLine();
                
                f.write("--------------------------------------------------------"
                        +"------------------------------------------");
                if(o!=null){
                    f.newLine();
                    f.write(o);
                }
                f.newLine();
                f.write("Error Occured at [YYYY/MM/DD HH:MM:SS]:: "+date);
                f.newLine();
                f.write("Error:: "+err);
                f.newLine();
                f.write("**Developer Comments:: \"");
                int a=0;
                while(a<comnt.length()){
                    for(int i=0;i<50&&a<comnt.length();i++){
                        f.write(comnt.charAt(a));
                        a++;
                    }
                    if(a<comnt.length()){
                        f.newLine();
                        f.write("     ");
                    }
                }
                f.write("\"");
                
                f.newLine();
                f.write("\n├┬┴┬┴ Note:: You can copy this crash report and "
                + "email it to the developers at etdickey@gmail.com ┬┴┬┴┤");
                f.newLine();
                f.write("------------------------------------------------"
                        +"--------------------------------------------------");
                f.newLine();
                f.newLine();
            }catch(IOException ioex){
            System.err.println("Error in writing to LoggerErrors.txt file:: "
                    +ioex);
        }
    }
    
    /**
     * This function displays an error message; this is used in the case that 
     *  a certain feature is not developed yet or is under progress. 
     * 
     * @param s the String to show the user to describe the issue
     */
    public void tempErrorMessage(String s){
        JOptionPane.showMessageDialog(null, s, "PROMPT", 
                                JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Prompt the developer or user about the error to make comments or 
     *  additional information for the documentation. 
     * 
     * @param err the error that caused this log
     * @return the String that was given by the user or developer containing
     *      comments or further information concerning the issue
     */
    private static String promptDeveloper(String err){
        String aa=null;
        
        JFrame f=new JFrame();
        JPanel p=new JPanel();
        
        JTextField cmt=new JTextField(10);
        
        f.add(p);
        f.setVisible(true);
        f.setSize(250,200);
        f.setTitle("Developer Message");
        f.setResizable(true);
        
        JButton k=new JButton("OK");
        k.setBounds(200,200,50,50);
        
        p.add(new JLabel("Add comments to the error:: ["+err+"]"));
        
        p.add(cmt);
        
        p.add(k);
        
        k.addActionListener((ActionEvent e) -> {
            System.out.println("-OK-");
            writeToFile(error,cmt.getText());
            f.dispose();
//            System.exit(0);
        });
        
        if(aa==null||aa.equals(""))
            return "No comment added";
        else return aa;

    }
    
    /**
     * This function displays an error message; this is used in the case that 
     *  a certain feature is not developed yet or is under progress. 
     * 
     */
    public void unavailable(){
        JOptionPane.showMessageDialog(null, "Route currently unavailable.", "PROMPT", 
                                JOptionPane.ERROR_MESSAGE);
    }
    
    
}
