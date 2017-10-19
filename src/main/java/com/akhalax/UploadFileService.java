package com.akhalax;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;


@Path("/file")
public class UploadFileService {
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({"application/zip"})
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @FormDataParam("device") String type) {



        String IconsFolder = null;
        String IconsZip = null;
        type = type.toLowerCase();
        String uploadedFileLocation = "c://upload/"
                + fileDetail.getFileName();
        HashMap<String, BufferedImage> IcoF = null;
        InputStream ZipIco = null;
        // save it
        //writeToFile(uploadedInputStream, uploadedFileLocation);

        try {
            IcoF = ImageResizer.resize(uploadedInputStream, type);
        } catch (IOException e) {
            System.err.println("Error resizing the image.");
            e.printStackTrace();
        }
        try {
            ZipIco = Zip.zip(IcoF);
        } catch (IOException e) {
            System.err.println("Error archiving the images.");
            e.printStackTrace();
        }

        if (ZipIco == null) {
            return Response.serverError().entity("Empty zip archive").build();
        }

        //File fileToSend = new File(IconsZip);


        ContentDisposition contentDisposition = ContentDisposition.type("attachment")
                .fileName("Icons.zip").creationDate(new Date()).build();

        //return Response.status(200).entity(output).build();
        return Response
                .ok(ZipIco)
                .type("application/zip")
                .header("Content-Disposition",contentDisposition)
                .build();

    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {

        try {
            new FileOutputStream(new File(
                    uploadedFileLocation));
            OutputStream out;
            int read;
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
