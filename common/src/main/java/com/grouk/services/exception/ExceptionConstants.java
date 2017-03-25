package com.grouk.services.exception;

/**
 * Exception Constants
 * Created by Alena_Grouk on 7/29/2016.
 */
class ExceptionConstants {
    /**
     * error level for severity
     */
    static final String SEVERITY_ERROR = "ERROR";
    /**
     * warning level for severity
     */
    static final String SEVERITY_WARNING = "WARNING";
    /**
     * info level for severity
     */
    static final String SEVERITY_INFO = "INFO";

    static final int DEFAULT_EXCEPTION_ERROR_CODE = -1;
    static final String ERROR_CODE_KEY_SUFFIX = "ERROR-";
    static final String DEFAULT_ERROR_CODE_TEXT = "ERROR-00000";
    static final String ERROR_CODE_SEPARATOR = ": ";

    static final int INTERNAL_ERROR = 400;
    static final String INTERNAL_ERROR_MSG = "Internal error. Please, contact your administrator.";
}
