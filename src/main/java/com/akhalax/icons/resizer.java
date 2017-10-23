package com.akhalax.icons;

import com.akhalax.errorhandling.AppException;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public interface resizer {
    HashMap<String, BufferedImage> resize (BufferedImage inputImage) throws AppException;
}
