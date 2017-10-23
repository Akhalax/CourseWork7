package com.akhalax.errorhandling;

public class AppException extends Exception {

    private static final long serialVersionUID = -8999932578270387947L;
    /**
     * contains redundantly the HTTP status of the response sent back to the client in case of error, so that
     * the developer does not have to look into the response headers. If null a default
     */
    private Integer status;

    /** detailed error description for developers*/
    private String developerMessage;

    public AppException(int status, String message,
                        String developerMessage) {
        super(message);
        this.status = status;
        this.developerMessage = developerMessage;
    }

    public AppException(AppException ae) {
        super(ae.getMessage());
        this.status = ae.getStatus();
        this.developerMessage = ae.getDeveloperMessage();
    }

    AppException() { }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

}
