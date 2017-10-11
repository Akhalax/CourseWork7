package com.akhalax;

import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class Zip {
    private static final int BUFFER = 2048;

    static String zip(String folder) throws IOException {

        BufferedInputStream origin = null;
        FileOutputStream dest = new
                FileOutputStream(folder+"icons.zip");
        ZipOutputStream out = new ZipOutputStream(new
                BufferedOutputStream(dest));
        out.setMethod(ZipOutputStream.DEFLATED);
        byte data[] = new byte[BUFFER];
        // get a list of files from current directory
//        File f = new File(".");
        File f = new File(folder);
        String files[] = f.list();

        if (files != null) {
            for (String file : files) {
                if (Objects.equals(file, "icons.zip")) continue;
                file = folder + file;
                FileInputStream fi = new
                        FileInputStream(file);
                origin = new
                        BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(file);
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }
        } else throw new FileNotFoundException();
        out.close();

        return dest.toString();
    }
}
