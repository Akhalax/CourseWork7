package com.akhalax;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/file")
public class UploadFileService {
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {

        String uploadedFileLocation = "d://upload/"
                + fileDetail.getFileName();

        // save it
        writeToFile(uploadedInputStream, uploadedFileLocation);
        String iOSIconsFolder = null;
        try {
            iOSIconsFolder = ImageResizer.resize(uploadedFileLocation, "iOS");
        } catch (IOException e) {
            System.err.println("Error resizing the image.");
            e.printStackTrace();
        }
        try {
            Zip.zip(iOSIconsFolder);
        } catch (IOException e) {
            System.err.println("Error archiving the images.");
            e.printStackTrace();
        }

        String output = "File uploaded to : " + uploadedFileLocation + "\nFile converted.";

        File fileToSend = new File("D:/images/ico.zip");

        return Response.status(200).entity(output).build();
        //return Response.ok(fileToSend, "application/zip").build();

    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {

        try {
            new FileOutputStream(new File(
                    uploadedFileLocation));
            OutputStream out;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
