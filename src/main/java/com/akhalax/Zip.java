package com.akhalax;

import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class Zip {

    static String zip(String folder) throws IOException {
        File dest = new
                File(folder+"icons.zip");
        ZipOutputStream out = new ZipOutputStream(new
                FileOutputStream(dest));
        // get a list of files from current directory
        File f = new File(folder);
        String files[] = f.list();

        if (files != null) {
            for (String file : files) {
                if (Objects.equals(file, "icons.zip")) continue;
                //file = folder + file;
                FileInputStream fi = new
                        FileInputStream(folder + file);
                ZipEntry entry = null;
                if (folder.contains("Android")) {
                    // TO DO CODE HERE
                    entry = new ZipEntry(file);
                }
                else {
                    entry = new ZipEntry(file);
                }
                out.putNextEntry(entry);
                byte[] buffer = new byte[fi.available()];
                fi.read(buffer);
                out.write(buffer);
                out.closeEntry();
            }
        } else throw new FileNotFoundException();
        out.close();

        return dest.toString();
    }
}
