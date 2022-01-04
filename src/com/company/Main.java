package com.company;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException {
        File img = new File(args[0]);
        File scaledImage = new File(args[0] + "_scaled.jpg");
        File stichedImage = new File(args[0] + "_stitched.png");
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);

        Iterator<ImageWriter> imgWriter = ImageIO.getImageWritersByFormatName("jpeg");


        ImageIO.write(Resize(img, width, height), "jpg", scaledImage);
        ImageIO.write(Stitch(img, scaledImage, width, height), "jpg", stichedImage);;
    }

    public static BufferedImage Resize(File imgFile, int x, int y) {
        BufferedImage imgBuff;
        try {
            imgBuff = ImageIO.read(imgFile);
            Image newImage = imgBuff.getScaledInstance(x, y, Image.SCALE_DEFAULT);
            BufferedImage scaledImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
            scaledImage.createGraphics().drawImage(newImage, 0, 0, Color.WHITE, null);
            return scaledImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage Stitch(File img1, File img2, int x, int y) {
        BufferedImage imgBuff1, imgBuff2;
        try {

            imgBuff1 = Resize(img1, x, y);
            imgBuff2 = Resize(img2, x, y);

            BufferedImage stitchedImg = new BufferedImage(x, imgBuff1.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2D = stitchedImg.createGraphics();
            g2D.drawImage(imgBuff1, 0, 0, null);
            g2D.drawImage(imgBuff2, 0, imgBuff1.getHeight(), null);
            g2D.dispose();
            return stitchedImg;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
