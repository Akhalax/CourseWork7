package com.akhalax;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Date;


class ImageResizer {

    public static class Icons {
        int width;
        int height;
        String name;
        String type;

        Icons(int width, int height, String name, String type) {
            this.width = width;
            this.height = height;
            this.name = name;
            this.type = type;
        }
    }

    private static ArrayList<Icons> listIOS = new ArrayList<Icons>() {{
        add(new Icons(29,29,"Icon-Small", "Icons for apps"));
        add(new Icons(58,58,"Icon-Small@2x", "Icons for apps"));
        add(new Icons(87,87,"Icon-Small@3x", "Icons for apps"));
        add(new Icons(40,40,"Icon-40", "Icons for apps"));
        add(new Icons(80,80,"Icon-40@2x", "Icons for apps"));
        add(new Icons(120,120,"Icon-60@2x", "Icons for apps"));
        add(new Icons(180,180,"Icon-60@3x", "Icons for apps"));
        add(new Icons(120,120,"Icon-40@3x", "Icons for apps"));
        add(new Icons(76,76,"Icon-76", "Icons for apps"));
        add(new Icons(120,120,"Icon-120", "Icons for apps"));
        add(new Icons(512, 512, "iTunesArtwork", "Icons for apps"));
        add(new Icons(1024, 1024, "iTunesArtwork@2x", "Icons for apps"));
    }};

    public static ArrayList<Icons> listAndroid = new ArrayList<Icons>() {{
        add(new Icons(48,48, "mdpi", "Launcher icons"));
        add(new Icons(72,72, "hdpi", "Launcher icons"));
        add(new Icons(96,96, "xhdpi", "Launcher icons"));
        add(new Icons(144,144, "xxhdpi", "Launcher icons"));
        add(new Icons(192,192, "xxxhdpi", "Launcher icons"));
        add(new Icons(512,512, "GPStore", "Launcher icons"));
        add(new Icons(16,16, "mdpi", "Small Contextual Icons"));
        add(new Icons(24,24, "hdpi", "Small Contextual Icons"));
        add(new Icons(32,32, "xhdpi", "Small Contextual Icons"));
        add(new Icons(48,48, "xxhdpi", "Small Contextual Icons"));
        add(new Icons(64,64, "xxxhdpi", "Small Contextual Icons"));
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
        File pathAndroidType;
        File file = new File("D:/images/"+type);
        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new FileNotFoundException();
            }
        }
        file = new File(file +"/" + dateFormat.format(date));
        if (!file.exists()) {
            if (!file.mkdir()) {
                throw new FileNotFoundException();
            }
        }

        switch (type) {
            case "iOS": {
                for (Icons aList : listIOS) {
                    BufferedImage img = ImageResizer.resize(inputImagePath, aList.width, aList.height);
                    ImageIO.write(img, "png", new File(file + "/" + aList.name + ".png"));
                }
                break;
            }
            case "Android": {
                for (Icons aList : listAndroid) {
                    BufferedImage img = ImageResizer.resize(inputImagePath, aList.width, aList.height);
                    if(!(pathAndroidType = new File(file + "/" + aList.type)).exists())
                    {
                        if (!pathAndroidType.mkdir()) {
                            throw new FileNotFoundException("Failed to create Android type dir");
                        }
                    }
                    ImageIO.write(img, "png", new File(pathAndroidType + "/" + aList.name + ".png"));
                }
            }
            break;
            default: throw new IOException("invalid type");
        }


        return file + "/";
    }


}