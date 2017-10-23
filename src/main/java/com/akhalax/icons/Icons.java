package com.akhalax.icons;

import java.awt.image.BufferedImage;
import java.util.HashMap;

class Icons {
    private int width;
    private int height;
    private String name;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    HashMap<String, BufferedImage> imageStorage;

    Icons(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    Icons (){}


}
