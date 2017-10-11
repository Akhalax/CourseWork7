package com.akhalax;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;

class ImageResizer {

    public static class iOSIcons {
        int width;
        int height;
        String name;

        iOSIcons(int width, int height, String name) {
            this.width = width;
            this.height = height;
            this.name = name;
        }
    }

    private static ArrayList<iOSIcons> list = new ArrayList<iOSIcons>() {{
        add(new iOSIcons(29,29,"Icon-Small"));
        add(new iOSIcons(58,58,"Icon-Small@2x"));
        add(new iOSIcons(87,87,"Icon-Small@3x"));
        add(new iOSIcons(40,40,"Icon-40"));
        add(new iOSIcons(80,80,"Icon-40@2x"));
        add(new iOSIcons(120,120,"Icon-40@3x"));
        add(new iOSIcons(120,120,"Icon-60@2x"));
        add(new iOSIcons(180,180,"Icon-60@3x"));
        add(new iOSIcons(76,76,"Icon-76"));
        add(new iOSIcons(120,120,"Icon-120"));
        add(new iOSIcons(512, 512, "iTunesArtwork"));
        add(new iOSIcons(1024, 1024, "iTunesArtwork@2x"));
    }};

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     */
    private static BufferedImage resize(String inputImagePath,
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
    }

    static String resize(String inputImagePath, String type)
            throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss-SS");
        Date date = new Date();
        File file = new File("D:/images/"+ dateFormat.format(date));
        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new FileNotFoundException();
            }
        }
        for (iOSIcons aList : list) {
            BufferedImage img = ImageResizer.resize(inputImagePath, aList.width, aList.height);
            ImageIO.write(img, "png", new File(file + "/" + aList.name + ".png"));
        }
        return file + "/";
    }

    //    public static void main(String[] args) {
//
//        String inputImagePath = "D:/images/img.jpg";
//        try {
//            resize(inputImagePath, "iOS");
//        } catch (IOException e) {
//            System.out.println("Error resizing the image.");
//            e.printStackTrace();
//        }
//    }

}