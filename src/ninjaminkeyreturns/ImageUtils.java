/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninjaminkeyreturns;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Josh
 */
public class ImageUtils {
    
    /**
     * This function rotates a BufferedImage by the degrees specified.
     * 
     * NOTE:: the image should have invisible space around it for the rotation.
     * 
     * @param img the BufferedImage to rotate
     * @param degrees how many degrees to rotate the image by
     * @return the new rotated BufferedImage
     */
    public static BufferedImage rotateImage(BufferedImage img,double degrees){
        ImageIcon icon=new ImageIcon(img);
        BufferedImage blankCanvas=new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2= (Graphics2D)blankCanvas.getGraphics();
        g2.rotate(Math.toRadians(degrees),icon.getIconWidth()/2,icon.getIconHeight()/2);
        g2.drawImage(img,0,0,null);
        return blankCanvas;
    }
    
    /**
     * This function flips a given image vertically.
     * 
     * @param image the BufferedImage to flip
     * @return the flipped image in the form of a BufferedImage
     */
    public static BufferedImage flipVertical(BufferedImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(1,-1);
        tx.translate(0,-1*image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }

    /**
     * This function flips a given image horizontally.
     * 
     * @param image the BufferedImage to flip
     * @return the flipped image in the form of a BufferedImage
     */
    public static BufferedImage flipHorizontal(BufferedImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(-1,1);
        tx.translate(-1*image.getWidth(null),0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(image, null);
    }
}
