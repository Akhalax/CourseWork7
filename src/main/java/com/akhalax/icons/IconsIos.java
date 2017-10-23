package com.akhalax.icons;


import com.akhalax.errorhandling.AppException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class IconsIos extends Icons implements Resizer {
    private IconsIos(int width, int height, String name) {
        super(width, height, name);
    }

    IconsIos() {}

    private static ArrayList<IconsIos> listIOS = new ArrayList<IconsIos>() {{
        add(new IconsIos(29,29,"Icon-Small"));
        add(new IconsIos(58,58,"Icon-Small@2x"));
        add(new IconsIos(87,87,"Icon-Small@3x"));
        add(new IconsIos(40,40,"Icon-40"));
        add(new IconsIos(80,80,"Icon-40@2x"));
        add(new IconsIos(120,120,"Icon-60@2x"));
        add(new IconsIos(180,180,"Icon-60@3x"));
        add(new IconsIos(120,120,"Icon-40@3x"));
        add(new IconsIos(76,76,"Icon-76"));
        add(new IconsIos(120,120,"Icon-120"));
        add(new IconsIos(512, 512, "iTunesArtwork"));
        add(new IconsIos(1024, 1024, "iTunesArtwork@2x"));
    }};

    @Override
    public HashMap<String, BufferedImage> resize(BufferedImage inputImage) throws AppException {

        imageStorage = new HashMap<>();

        for (IconsIos aList : listIOS) {
            BufferedImage img;
            try {
                img = ImageResizer.resize(inputImage, aList.getWidth(), aList.getHeight());
            } catch (IOException e) {
                throw new AppException(500, "Cannot resize an image.", "IOException while resizing an image.");
            }
            imageStorage.put("ios/" + aList.getName() + ".png", img);
        }

        return imageStorage;
    }
}
