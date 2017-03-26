package com.grouk.services.exception;

/**
 * Converter Exception
 * Created by Alena on 26.03.2017.
 */
public class ConverterException extends RuntimeException {
    public ConverterException() {
        super();
    }

    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
