package com.akhalax;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


class Zip {

    static byte[] zip(HashMap<String, BufferedImage> folder) throws IOException {
        //File dest = new File(folder+"icons.zip");
        //        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File("icons.zip"))));

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream out = new ZipOutputStream(baos))
        {

            for (Map.Entry mEntry : folder.entrySet()) {
                String name = (String) mEntry.getKey();
                BufferedImage img = (BufferedImage) mEntry.getValue();
                //FileInputStream fi = new FileInputStream(img);
                ZipEntry entry;
                if (name.contains("android")) {
                    entry = new ZipEntry(name.substring(0, name.indexOf("!")) + "/" + name.substring(name.indexOf("!") + 1, name.length()));
                } else {
                    entry = new ZipEntry(name.substring(name.indexOf("/") + 1, name.length()));
                }
                out.putNextEntry(entry);
                ImageIO.write(img, ".png", out);
                //ImageIO.write(img, "png", new File("D:\\images\\ios\\test\\" + name.substring(name.indexOf("/") + 1, name.length()) + ".png"));
                out.flush();
                out.closeEntry();
            }
            out.flush();
            out.close();
            FileOutputStream fos = new FileOutputStream("D:\\images\\icons.zip");
            fos.write(baos.toByteArray());
            fos.close();
            return baos.toByteArray();
        }
    }
}
