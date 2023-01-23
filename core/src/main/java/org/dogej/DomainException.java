package org.dogej;

/**
 * Please pass message-keys here from system-errors.properties
 *
 * @author s.kosik
 */
public class DomainException extends RuntimeException {
    private ErrorCodes errorCode;
    private String message;

    public DomainException(final ErrorCodes code, final String message){
        this.errorCode = code;
        this.message = message;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    @Override public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
