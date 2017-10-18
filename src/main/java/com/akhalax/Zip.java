package com.akhalax;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class Zip {

    static OutputStream zip(HashMap<String, BufferedImage> folder) throws IOException {
        File dest = new
                File(folder+"icons.zip");
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File("icons.zip"))));
        // get a list of files from current directory
        //File f = new File(folder);
        //String files[] = f.list();

//        if (files != null) {
//            for (String file : files) {
//                if (Objects.equals(file, "icons.zip")) continue;
//                //file = folder + file;
//
//                FileInputStream fi = new
//                        FileInputStream(folder + file);
//                ZipEntry entry;
//                if (folder.contains("android")) {
//                      entry = new ZipEntry(file.substring(0,file.indexOf("!")) + "/" + file.substring(file.indexOf("!")+1, file.length()));
//                }
//                else {
//                    entry = new ZipEntry(file);
//                }
//                out.putNextEntry(entry);
//                byte[] buffer = new byte[fi.available()];
//                fi.read(buffer);
//                out.write(buffer);
//                out.closeEntry();
//            }
//        } else throw new FileNotFoundException();
//        out.close();

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

        return out;
    }
}
