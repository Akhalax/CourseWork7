package com.akhalax.errorhandling;

import org.apache.commons.beanutils.BeanUtils;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.InvocationTargetException;

@XmlRootElement
class ErrorMessage {

    /** contains the same HTTP Status code returned by the server */
    private int status;

    /** message describing the error*/
    private String message;

    /** extra information that might useful for developers */
    private String developerMessage;

    int getStatus() {
        return status;
    }

    void setStatus(int status) {
        this.status = status;
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    String getDeveloperMessage() {
        return developerMessage;
    }

    void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }


    ErrorMessage(AppException ex){
        try {
            BeanUtils.copyProperties(this, ex);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    ErrorMessage(NotFoundException ex){
        this.status = Response.Status.NOT_FOUND.getStatusCode();
        this.message = ex.getMessage();
    }

    ErrorMessage() {}
}