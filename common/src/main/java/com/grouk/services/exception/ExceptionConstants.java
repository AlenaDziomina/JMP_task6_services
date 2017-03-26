package com.grouk.services.exception;

/**
 * Exception Constants
 * Created by Alena_Grouk on 7/29/2016.
 */
public class ExceptionConstants {
    /**
     * error level for severity
     */
    public static final String SEVERITY_ERROR = "ERROR";
    /**
     * warning level for severity
     */
    public static final String SEVERITY_WARNING = "WARNING";
    /**
     * info level for severity
     */
    public static final String SEVERITY_INFO = "INFO";

    public static final int DEFAULT_EXCEPTION_ERROR_CODE = -1;
    public static final String ERROR_CODE_KEY_SUFFIX = "ERROR-";
    public static final String DEFAULT_ERROR_CODE_TEXT = "ERROR-00000";
    public static final String ERROR_CODE_SEPARATOR = ": ";

    public static final int INTERNAL_ERROR = 400;
    public static final String INTERNAL_ERROR_MSG = "Internal error. Please, contact your administrator.";
}
