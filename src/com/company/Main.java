package com.company;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        File img = new File(args[0]);
        File newImg = new File(args[0] + "_scaled.jpg");
        File stichedImage = new File(args[0] + "_stitched.png");
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);

        Iterator<ImageWriter> imgWriter = ImageIO.getImageWritersByFormatName("jpeg");

        Resize(img, newImg, width, height);
        Stitch(img, newImg, width, height, stichedImage);
    }

    public static void Resize(File imgFile, File newImg, int x, int y) {
        BufferedImage imgBuff;
        try {
            imgBuff = ImageIO.read(imgFile);
            BufferedImage newImageBuff = Scalr.resize(imgBuff, Scalr.Method.BALANCED, Scalr.Mode.AUTOMATIC, x, y);
            newImageBuff.createGraphics().drawImage(newImageBuff, 0, 0, Color.WHITE, null);
            var bool0 = ImageIO.write(newImageBuff, "png", newImg);
            newImageBuff.flush();
            var foo = "blah";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Stitch(File img1, File img2, int x, int y, File stitchImg) {
        BufferedImage imgBuff1, imgBuff2;
        try {
            imgBuff1 = Scalr.resize(ImageIO.read(img1), Scalr.Method.BALANCED, Scalr.Mode.AUTOMATIC, x, y);
            imgBuff2 = Scalr.resize(ImageIO.read(img2), Scalr.Method.BALANCED, Scalr.Mode.AUTOMATIC, x, y);
            BufferedImage stitchedImgBuff = new BufferedImage(x, y * 2, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2D = stitchedImgBuff.createGraphics();
            var bool1 = g2D.drawImage(imgBuff1, 0, 0, null);
            var bool2 = g2D.drawImage(imgBuff2, 0, imgBuff1.getHeight(), null);
            var bool3 = ImageIO.write(stitchedImgBuff, "JPEG", stitchImg);
            g2D.dispose();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
