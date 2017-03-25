package com.grouk.services.exception;

import static com.grouk.services.exception.ExceptionConstants.*;

/**
 * Public Exception
 * Created by Alena_Grouk on 7/29/2016.
 */
public class PublicException extends RuntimeException {

    private int errorCode = DEFAULT_EXCEPTION_ERROR_CODE;

    private String severity = SEVERITY_ERROR;

    private String localizedUserMessage;

    public PublicException() {
        super();
        localizedUserMessage = generateUserMessage(errorCode, this.getClass().getName());
    }

    public PublicException(String message) {
        super(message);
        localizedUserMessage = generateUserMessage(errorCode, message);
    }

    public PublicException(String message, Throwable cause) {
        super(message, cause);
        localizedUserMessage = generateUserMessage(errorCode, message);
    }

    public PublicException(int anErrorCode, String errorMsg) {
        super(errorMsg);
        errorCode = anErrorCode;
        localizedUserMessage = generateUserMessage(anErrorCode, errorMsg);
    }

    public PublicException(int anErrorCode, String errorMsg, Throwable cause) {
        super(cause);
        errorCode = anErrorCode;
        localizedUserMessage = generateUserMessage(anErrorCode, errorMsg);
    }

    public PublicException(String message, int anErrorCode, String errorMsg, Throwable cause) {
        super(message, cause);
        errorCode = anErrorCode;
        localizedUserMessage = generateUserMessage(anErrorCode, errorMsg);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getLocalizedUserMessage() {
        return localizedUserMessage;
    }

    private String generateUserMessage(int anErrorCode, String errorMsg) {
        StringBuilder msg;
        if (anErrorCode > 0) {
            msg = new StringBuilder(ERROR_CODE_KEY_SUFFIX).append(anErrorCode);
        } else {
            msg = new StringBuilder(DEFAULT_ERROR_CODE_TEXT);
        }
        msg.append(ERROR_CODE_SEPARATOR).append(errorMsg);
        return msg.toString();
    }
}
