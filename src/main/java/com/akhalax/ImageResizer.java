package com.akhalax;

import com.akhalax.errorhandling.AppException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


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

    private static HashMap<String, BufferedImage> imgs = new HashMap<>();

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

    private static ArrayList<Icons> listAndroid = new ArrayList<Icons>() {{
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
     * @param inputImage Stream of the original image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     */
    private static BufferedImage resize(BufferedImage inputImage,
                                        int scaledWidth, int scaledHeight)
            throws IOException {

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        return outputImage;
    }

    static HashMap<String, BufferedImage> resize(InputStream inputImageReceived, String type)
            throws AppException, IOException {

        BufferedImage inputImage = ImageIO.read(inputImageReceived);

        switch (type) {
            case "ios": {
                for (Icons aList : listIOS) {
                    BufferedImage img = ImageResizer.resize(inputImage, aList.width, aList.height);
                    imgs.put("ios/" + aList.name + ".png", img);
                    //ImageIO.write(img, "png", new File("D:\\images\\ios" + "/" + aList.name + ".png"));
                }
                break;
            }
            case "android": {
                for (Icons aList : listAndroid) {
                    BufferedImage img = ImageResizer.resize(inputImage, aList.width, aList.height);
                    imgs.put("android/" + aList.type + "!" + aList.name + ".png", img);
                    //ImageIO.write(img, "png", new File(file + "/" + aList.type + "!" + aList.name + ".png"));
                }
            }
            break;
            default:
                throw new AppException(400, "Invalid type of device.", "At the moment there are 2 available types: /ios and /android. Please use one of them.");
        }
        return imgs;
    }

}