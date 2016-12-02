/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;
/**
 * This class is a Custom-JButton that includes enhancements for icons, a 
 *  built-in MouseListener, and disabling functions.
 * 
 * @author Josh Holland
 */
public class CButton extends JButton implements MouseListener{
    
    public int xIndex,

    /**
     *
     */
    yIndex;//when applicable 
    
    /**
     *
     */
    public int ID;
    
    /**
     * An Array of ImageIcon objects that are evoked separately by differentiating
     *  behavior from the user, which includes index 0 for when the user is
     *  not focused on the button with the mouse or after the button has been 
     *  clicked on and the mouse was released, and index 1 for while the button
     *  is being clicked on.
     */
    ImageIcon[] icons=null;
    
    ImageIcon g;

    /**
     *
     */
    public boolean pernamantSelect=false;
    
    ImageIcon disabledIcon=null;
    
    public boolean disabled=false,

    /**
     *
     */
    selected=false;
    
    /**
     * Sets up the CButton object using x and y coordinates, width, height,
     *  and a String value for the CButton to display. 
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     * @param a the String value that is used for the CButton to display
     */
    public CButton(int x,int y,int xs,int ys,String a){
        super.setBounds(x,y,xs,ys);
        if(a!=null)
            super.setText(a);
        this.addMouseListener(this);
    }
    
    /**
     * Sets up the CButton object using x and y coordinates, width, and height.
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     */
    public CButton(int x,int y,int xs,int ys){
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
    }
    
    /**
     * Sets up the CButton object using x and y coordinates, width, height,
     *  and an array of ImageIcon objects for the CButton to display when 
     *  the user evokes certain behavior from the CButton.
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     * @param ic the array of ImageIcon objects that are used to display images
     *      on the CButton for the sake of the graphical representation.
     */
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic){
        icons=ic;
        
        //////
        //////Method rescaling buton label
        //////
        //ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
        if(xs!=0&&ys!=0){
            Image image = icons[0].getImage(); // transform it
            Image newimg = image.getScaledInstance(xs, ys,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
            g = new ImageIcon(newimg);
            ic[0] =g;


             image = icons[1].getImage(); // transform it
             newimg = image.getScaledInstance(xs, ys,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
            g = new ImageIcon(newimg); 
            ic[1]=g;
        }
        
       
        super.setBounds(x,y,xs,ys);
        this.setIcon(ic[0]);
        this.addMouseListener(this);
        
        setContentAreaFilled(true);
    }
    
    /**
     * Sets up the CButton object using x and y coordinates, width, height,
     *  an array of ImageIcon objects for the CButton to display when 
     *  the user evokes certain behavior from the CButton, and an ID number
     *  used to identify the CButton instance.
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     * @param ic the array of ImageIcon objects that are used to display images
     *      on the CButton for the sake of the graphical representation.
     * @param id adds ID information to this CButton object for the case in 
     *  which a certain numeric value should be assigned to it in order to 
     *  organize comparatively and/or identify this CButton instance
     */
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,int id){
        this(x,y,xs,ys,ic);
        ID=id;
    }
    
    /**
     * Sets up the CButton object using x and y coordinates, width, height,
     *  an array of ImageIcon objects for the CButton to display when 
     *  the user evokes certain behavior from the CButton, a choice concerning 
     *  if the instance of CButton should have a border, and an ID number used 
     *  to identify the CButton instance.
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     * @param ic the array of ImageIcon objects that are used to display images
     *      on the CButton for the sake of the graphical representation.
     * @param border a boolean value that determines if there is a border around
     *      the CButton
     * @param id adds ID information to this CButton object for the case in 
     *  which a certain numeric value should be assigned to it in order to 
     *  organize comparatively and/or identify this CButton instance
     */
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,boolean border,int id){
        this(x,y,xs,ys,ic,border);
        ID=id;
    }
    
    /**
     * This function rescales the icon array's images that it contains in order
     *  to fit the CButton's width and height properly.
     * 
     */
    public void changeIconSizes(){
        Image image = icons[0].getImage(); // transform it
        Image newimg = image.getScaledInstance(getWidth(), getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        g = new ImageIcon(newimg);
        icons[0] =g;
        
        
             image = icons[1].getImage(); // transform it
         newimg = image.getScaledInstance(getWidth(), getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        g = new ImageIcon(newimg); 
        icons[1]=g;
    }

    /**
     * This function disables or re eneables the CButton's actions of changing 
     *  images when clicked or focused and disables or re enables the action
     *  that the button performs. 
     * 
     * @param dis the boolean value that determines if the CButton is being
     *      disabled or enabled
     */
    public void disable(boolean dis){
        if(disabled=dis){
            this.setIcon(disabledIcon);
        } else{
            this.setIcon(icons[0]);
        }
    }
    /**
     * If c is true then this function sets the current icon to icon index 1, 
     *  as if in focus, and disables any changes of the icon; if it is false
     *  then it does the opposite and re-enables normal functionality as long
     *  as the other requirements for these actions are met. 
     *      
     * 
     * @param c the boolean value that determines if the CButton will be set 
     *      under the PernamantSelect restriction or not 
     */
    public void setPernamantSelect(boolean c){
        if(pernamantSelect=c)
            this.setIcon(icons[1]);
        else if(!selected)
            this.setIcon(icons[0]);
        else 
            this.setIcon(icons[1]);
    }
    
    
    /**
     * Sets up the CButton object using x and y coordinates, width, height,
     *  an array of ImageIcon objects for the CButton to display when 
     *  the user evokes certain behavior from the CButton, an extra ImageIcon 
     *  object for when the CButton is disabled, and  a choice concerning if the
     *  instance of CButton should have a border.
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     * @param ic the array of ImageIcon objects that are used to display images
     *      on the CButton for the sake of the graphical representation.
     * @param dis an ImageIcon object for the CButton to use if this object
     *      is disabled
     * @param border a boolean value that determines if there is a border around
     *      the CButton 
     */
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,ImageIcon dis,boolean border){
        icons=ic;
        disabledIcon=dis;
        
        
                //////
        //////Method rescaling buton label
        //////
        //ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
        
        Image image = icons[0].getImage(); // transform it
        Image newimg = image.getScaledInstance(xs, ys,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        g = new ImageIcon(newimg);
        ic[0] =g;
        
        
             image = icons[1].getImage(); // transform it
         newimg = image.getScaledInstance(xs, ys,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        g = new ImageIcon(newimg); 
        ic[1]=g;
        
               image = disabledIcon.getImage(); // transform it
         newimg = image.getScaledInstance(xs, ys,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        g = new ImageIcon(newimg); 
        disabledIcon=g;
        
        this.setIcon(ic[0]);
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
        
        setContentAreaFilled(true);
        
        if(!border)
            setBorder(BorderFactory.createEmptyBorder());
    }
    
    /**
     * Sets up the CButton object using x and y coordinates, width, height,
     *  an array of ImageIcon objects for the CButton to display when 
     *  the user evokes certain behavior from the CButton, and  a choice 
     *  concerning if the instance of CButton should have a border.
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     * @param ic the array of ImageIcon objects that are used to display images
     *      on the CButton for the sake of the graphical representation.
     * @param border a boolean value that determines if there is a border around
     *      the CButton 
     */
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,boolean border){
        icons=ic;
        
        //ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
        ic[0] = new ImageIcon(icons[0].getImage().getScaledInstance(xs, ys,  java.awt.Image.SCALE_FAST));
        
        ic[1] = new ImageIcon(icons[1].getImage().getScaledInstance(xs, ys,  java.awt.Image.SCALE_FAST));
        
        
        this.setIcon(ic[0]);
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
        
        setContentAreaFilled(true);
        
        if(!border)
            setBorder(BorderFactory.createEmptyBorder());
    }
    
    /**
     * Sets up the CButton object using x and y coordinates, width, height,
     *  an array of ImageIcon objects for the CButton to display when 
     *  the user evokes certain behavior from the CButton, a choice 
     *  concerning if the instance of CButton should have a border, an ID number
     *  used to identify the CButton instance, and a boolean used to decide
     *  if the icons will be scaled to fit the CButton.
     * 
     * @param x the x coordinate at which the CButton starts (upper left corner)
     * @param y the y coordinate at which the CButton starts (upper left corner)
     * @param xs the x size, or width, of the CButton
     * @param ys the y size, or height, of the CButton
     * @param ic the array of ImageIcon objects that are used to display images
     *      on the CButton for the sake of the graphical representation.
     * @param border a boolean value that determines if there is a border around
     *      the CButton 
     * @param id adds ID information to this CButton object for the case in 
     *  which a certain numeric value should be assigned to it in order to 
     *  organize comparatively and/or identify this CButton instance
     * @param scaled a boolean used to decide if the icons will be scaled to fit
     *  the CButton
     */
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,boolean border,int id,boolean scaled){
        ID=id;
        icons=ic;
        
        if(scaled){
            //ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
            ic[0] = new ImageIcon(icons[0].getImage().getScaledInstance(xs, ys,  java.awt.Image.SCALE_FAST));

            ic[1] = new ImageIcon(icons[1].getImage().getScaledInstance(xs, ys,  java.awt.Image.SCALE_FAST));
        }
        
        
        this.setIcon(ic[0]);
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
        
        setContentAreaFilled(true);
        
        if(!border)
            setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * This function is evoked by the MouseListener that is implemented any time
     *  the CButton is clicked on by the mouse.
     * 
     * @param e the MouseEvent object that is passed in by default parameters
     *      by the overriden function
     */
    @Override
    public void mouseClicked(MouseEvent e) {clicked();}

    /**
     * This function is evoked by the MouseListener that is implemented any time
     *  the CButton is pressed on by the mouse.
     * 
     * @param e the MouseEvent object that is passed in by default parameters
     *      by the overriden function
     */
    @Override
    public void mousePressed(MouseEvent e) {pressed();}

    /**
     * This function is evoked by the MouseListener that is implemented any time
     *  the CButton is released on by the mouse.
     * 
     * @param e the MouseEvent object that is passed in by default parameters
     *      by the overriden function
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(!disabled&&!pernamantSelect)
            super.setIcon(icons[0]);
//        AudioAssets.play("JoshSheep");
        released();
    }

    /**
     * This function is evoked by the MouseListener that is implemented any time
     *  the CButton is focused on by the mouse.
     * 
     * @param e the MouseEvent object that is passed in by default parameters
     *      by the overriden function
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if(!disabled&&!pernamantSelect){
            super.setIcon(icons[1]);
        }
        selected=true;
        entered();
    }

    /**
     * This function is evoked by the MouseListener that is implemented any time
     *  the CButton becomes un-focused by the mouse.
     * 
     * @param e the MouseEvent object that is passed in by default parameters
     *      by the overriden function
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if(!disabled&&!pernamantSelect){
            super.setIcon(icons[0]);
        }
        selected=false;
        exited();
    }
    
    /**
     * This function is meant to be overriden in order to control what will
     *  happen when the user clicks on this CButton object.
     */
    public void clicked(){}
    
    /**
     * This function is meant to be overriden in order to control what will
     *  happen when the user presses on this CButton object.
     */
    public void pressed(){}
    
    /**
     * This function is meant to be overriden in order to control what will
     *  happen when the user releases on this CButton object.
     */
    public void released(){}
    
    /**
     * This function is meant to be overriden in order to control what will
     *  happen when the user focuses the mouse on this CButton object.
     */
    public void entered(){}
    
    /**
     * This function is meant to be overriden in order to control what will
     *  happen when the user un-focuses the mouse on this CButton object.
     */
    public void exited(){}
    
    
}
