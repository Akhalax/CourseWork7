package com.akhalax.icons;

import java.awt.image.BufferedImage;
import java.util.HashMap;

class Icons {
    int width;
    int height;
    String name;

    HashMap<String, BufferedImage> imageStorage;

    Icons(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    Icons (){}


}
