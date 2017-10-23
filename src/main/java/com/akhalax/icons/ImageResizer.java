package com.akhalax.icons;

import com.akhalax.errorhandling.AppException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class ImageResizer {

    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImage Stream of the original image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     */
    static BufferedImage resize(BufferedImage inputImage,
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

    public static HashMap<String, BufferedImage> resize(InputStream inputImageReceived, String type)
            throws AppException, IOException {

        BufferedImage inputImage = ImageIO.read(inputImageReceived);
        if (inputImage == null) throw new AppException(400,"Uploaded file is not an image. Please upload an image.", "The file could not be converted, it's not an image.");

        HashMap<String, BufferedImage> imageStorage;
        switch (type) {
            case "ios": {
                IconsIos iconsIos = new IconsIos();
                imageStorage = iconsIos.resize(inputImage);
                break;
            }
            case "android": {
                IconsAndroid iconsAndroid = new IconsAndroid();
                imageStorage = iconsAndroid.resize(inputImage);
                break;
            }
            default:
                throw new AppException(400, "Invalid type of device.", "At the moment there are 2 available types: /ios and /android. Please use one of them.");
        }
        return imageStorage;
    }

}