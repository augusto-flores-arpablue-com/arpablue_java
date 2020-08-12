/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arpablue.image;

import com.arpablue.xsystemfile.LogFile;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author augusto
 */
public class ImageX 
{
    
    public final static int IMAGE_TYPE_PNG=0;
    public final static int IMAGE_TYPE_JPG=1;
    public final static int IMAGE_TYPE_GIF=2;
    
    BufferedImage mImg = null;
    //Load a image from a specific path, if the image bnot exist then return null;
    public static BufferedImage loadImage(String path)
    {
        BufferedImage res = null;
        try{
            File f = new File(path);
            if((!f.exists())||(!f.isFile()) )
                return null;
            res = ImageIO.read(f);
            return res;
        }catch(Exception e){
            LogFile.error("(ImageX - loagImage): "+e);
        }
        return res;
    }
    //load a image in the current object
    public boolean load(String path)
    {
        mImg = ImageX.loadImage(path);
        return mImg!=null;
    }
    //save the image in a file
    public boolean save(String path)
    {
        return save(path,ImageX.IMAGE_TYPE_JPG);
    }
    public boolean save(String path,int extension)
    {
        try{
            File outputfile = new File(path);
            if(outputfile.exists())
                outputfile.delete();
            String type = "jpg";
            if(extension==IMAGE_TYPE_PNG)
                type="png";
            else if(extension==IMAGE_TYPE_GIF)
                type="gif";
            if(mImg==null)
                return false;
            ImageIO.write(mImg, type, outputfile); // Write the Buffered Image into an output file
            return true;
        }catch(Exception e){
            LogFile.error("(ImageX-save): "+e);
        }
        return false;
    }
    //////setters and getters
    public void setImage(Image img) 
    { 
        if (img == null)
        {
            mImg = null;
            return;
        }
        mImg = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
        mImg.getGraphics().drawImage(img, 0, 0, null);
    }
    //Return the image in a buffered image 
    public BufferedImage getImage()
    { 
        return mImg ;
    }
}
