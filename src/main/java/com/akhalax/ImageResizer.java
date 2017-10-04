package com.akhalax;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class ImageResizer {

    public static class iOSIcons {
        int width;
        int height;
        String name;

        public iOSIcons(int width, int height, String name) {
            this.width = width;
            this.height = height;
            this.name = name;
        }
    }

    static ArrayList<iOSIcons> listIOSIcons = new  ArrayList<iOSIcons> (12);
    public static void addIcons(int width, int height, String name){
        iOSIcons newIcon = new iOSIcons(width,height, name);
        listIOSIcons.add(newIcon);
    }

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static BufferedImage resize(String inputImagePath,
                               int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        return outputImage;
        // extracts extension of output file

//        String formatName = outputImagePath.substring(outputImagePath
//                .lastIndexOf(".") + 1);

        // writes to output file
//        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
    /**
     * Test resizing images
     */
    public static void main(String[] args) {

        addIcons(29,29,"Icon-Small");
        addIcons(58,58,"Icon-Small@2x");
        addIcons(87,87,"Icon-Small@3x");
        addIcons(40,40,"Icon-40");
        addIcons(80,80,"Icon-40@2x");
        addIcons(120,120,"Icon-40@3x");
        addIcons(120,120,"Icon-60@2x");
        addIcons(180,180,"Icon-60@3x");
        addIcons(76,76,"Icon-76");
        addIcons(120,120,"Icon-120");
        addIcons(512, 512, "iTunesArtwork");
        addIcons(1024, 1024, "iTunesArtwork@2x");

        String inputImagePath = "D:/images/img.jpg";

        try {
            for (int i =0; i < listIOSIcons.size(); i++) {
                BufferedImage img = ImageResizer.resize(inputImagePath, listIOSIcons.get(i).width, listIOSIcons.get(i).height);
                ImageIO.write(img, "png", new File("D:/images/" + listIOSIcons.get(i).name +".png"));
            }

        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }

}