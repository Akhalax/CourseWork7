package com.akhalax;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;


@Path("/file")
public class UploadFileService {
    @POST
    @Path("/upload/{type}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({"application/zip"})
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @PathParam("type") String type) {


        type = type.toLowerCase();
        HashMap<String, BufferedImage> IcoF = null;
        byte[] ZipIco = null;

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


        ContentDisposition contentDisposition = ContentDisposition.type("attachment")
                .fileName("Icons.zip").creationDate(new Date()).build();


        //return Response.status(200).entity(output).build();
        return Response
                .ok(ZipIco)
                .type("application/zip")
                .header("Content-Disposition",contentDisposition)
                .build();

    }



}
