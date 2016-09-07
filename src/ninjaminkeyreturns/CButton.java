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
 *
 * @author Josh
 */
public class CButton extends JButton implements MouseListener{
    
    public int xIndex,yIndex;//when applicable 
    
    public int ID;
    
    ImageIcon[] icons=null;
    ImageIcon g;
    public boolean pernamantSelect=false;
    
    ImageIcon disabledIcon=null;
    
    public boolean disabled=false,selected=false;
    
//    @Override
//    public void setIcon(Icon i){
//        System.out.println("changing icon");
//        super.setIcon(i);
//    }
    
    public CButton(int x,int y,int xs,int ys,String a){
        super.setBounds(x,y,xs,ys);
        if(a!=null)
            super.setText(a);
        this.addMouseListener(this);
    }
    
    public CButton(int x,int y,int xs,int ys){
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
    }
    
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
    
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,int id){
        this(x,y,xs,ys,ic);
        ID=id;
    }
    
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,boolean border,int id){
        this(x,y,xs,ys,ic,border);
        ID=id;
    }
    
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

    
    public void disable(boolean dis){
        if(disabled=dis){
            this.setIcon(disabledIcon);
        } else{
            this.setIcon(icons[0]);
        }
    }
    
    public void setPernamantSelect(boolean c){
        if(pernamantSelect=c)
            this.setIcon(icons[1]);
        else if(!selected)
            this.setIcon(icons[0]);
        else 
            this.setIcon(icons[1]);
    }
    
//    public boolean getPernamantSelect(){
//        return pernamantSelect;
//    }
    
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

    @Override
    public void mouseClicked(MouseEvent e) {clicked();}

    @Override
    public void mousePressed(MouseEvent e) {pressed();}

    @Override
    public void mouseReleased(MouseEvent e) {
        if(!disabled&&!pernamantSelect)
            super.setIcon(icons[0]);
//        AudioAssets.play("JoshSheep");
        released();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!disabled&&!pernamantSelect){
            super.setIcon(icons[1]);
        }
        selected=true;
        entered();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!disabled&&!pernamantSelect){
            super.setIcon(icons[0]);
        }
        selected=false;
        exited();
    }
    
    public void clicked(){}
    public void pressed(){}
    public void released(){}
    public void entered(){}
    public void exited(){}
    
    
}
