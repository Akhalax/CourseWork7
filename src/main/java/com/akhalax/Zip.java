package com.akhalax;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.System.out;

class Zip {

    static InputStream zip(HashMap<String, BufferedImage> folder) throws IOException {
        //File dest = new File(folder+"icons.zip");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File("icons.zip"))));
        ZipOutputStream out = new ZipOutputStream(bos);


        for (Map.Entry mEntry: folder.entrySet()) {
            String name = (String) mEntry.getKey();
            BufferedImage img = (BufferedImage) mEntry.getValue();
            //FileInputStream fi = new FileInputStream(img);
            ZipEntry entry;
            if (name.contains("android")) {
                entry = new ZipEntry(name.substring(0, name.indexOf("!")) + "/" + name.substring(name.indexOf("!") + 1, name.length()));
            } else {
                entry = new ZipEntry(name);
            }
            out.putNextEntry(entry);
            ImageIO.write(img,".png", out);
            out.closeEntry();
        }
        out.close();

        return new ByteArrayInputStream(bos.toByteArray());
    }
}
