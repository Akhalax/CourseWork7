package com.akhalax.errorhandling;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {


    public Response toResponse(Throwable ex) {

        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        errorMessage.setMessage(ex.getMessage());
        StringWriter errorStackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(errorStackTrace));
        errorMessage.setDeveloperMessage(errorStackTrace.toString());

        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
        if(ex instanceof WebApplicationException) { //NICE way to combine both of methods, say it in the blog
            errorMessage.setStatus(((WebApplicationException)ex).getResponse().getStatus());
        } else {
            errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); //defaults to internal server error 500
        }
    }
}