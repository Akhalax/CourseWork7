package com.akhalax;

import com.akhalax.errorhandling.AppException;
import com.akhalax.errorhandling.AppExceptionMapper;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;


@Path("/file")
public class UploadFileService {
    @POST
    @Path("/upload/{type}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({"application/zip", "application/json"})
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @PathParam("type") String type) throws AppException {


        type = type.toLowerCase();
        HashMap<String, BufferedImage> IcoF;
        byte[] ZipIco;

        try {
            IcoF = ImageResizer.resize(uploadedInputStream, type);
        } catch (AppException e) {
            throw new AppException(e);
        }
        catch (IOException ioe) {
            throw new WebApplicationException(ioe);
        }

        try {
            ZipIco = Zip.zip(IcoF);
        } catch (Exception e) {
            throw new AppException(500, "Error while zipping images.", "Server error while in zip-method.");
        }

        if (ZipIco == null) {
            throw new AppException(500, "Empty archive", "Something went wrong while zipping images. Archive is empty.");
        }


        ContentDisposition contentDisposition = ContentDisposition.type("attachment")
                .fileName("Icons.zip").creationDate(new Date()).build();


        return Response
                .ok(ZipIco)
                .type("application/zip")
                .header("Content-Disposition",contentDisposition)
                .build();

    }

}
