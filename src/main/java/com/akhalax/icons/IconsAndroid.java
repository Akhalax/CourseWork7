package com.akhalax.icons;

import com.akhalax.errorhandling.AppException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class IconsAndroid extends Icons implements resizer {
    private String type;

    private IconsAndroid(int width, int height, String name, String type) {
        super(width, height, name);
        this.type = type;
    }

    IconsAndroid(){}

    private static ArrayList<IconsAndroid> listAndroid = new ArrayList<IconsAndroid>() {{
        add(new IconsAndroid(48,48, "mdpi", "Launcher icons"));
        add(new IconsAndroid(72,72, "hdpi", "Launcher icons"));
        add(new IconsAndroid(96,96, "xhdpi", "Launcher icons"));
        add(new IconsAndroid(144,144, "xxhdpi", "Launcher icons"));
        add(new IconsAndroid(192,192, "xxxhdpi", "Launcher icons"));
        add(new IconsAndroid(512,512, "GPStore", "Launcher icons"));
        add(new IconsAndroid(16,16, "mdpi", "Small Contextual Icons"));
        add(new IconsAndroid(24,24, "hdpi", "Small Contextual Icons"));
        add(new IconsAndroid(32,32, "xhdpi", "Small Contextual Icons"));
        add(new IconsAndroid(48,48, "xxhdpi", "Small Contextual Icons"));
        add(new IconsAndroid(64,64, "xxxhdpi", "Small Contextual Icons"));
    }};

    @Override
    public HashMap<String, BufferedImage> resize(BufferedImage inputImage) throws AppException {

        imageStorage = new HashMap<>();

        for (IconsAndroid aList : listAndroid) {
            BufferedImage img;
            try {
                img = ImageResizer.resize(inputImage, aList.width, aList.height);
            } catch (IOException e) {
                throw new AppException(500, "Cannot resize an image.", "IOException while resizing an image.");
            }
            imageStorage.put("android/" + aList.type + "!" + aList.name + ".png", img);
        }

        return imageStorage;
    }
}
