package com.grouk.services.exception.factory;

import com.grouk.services.exception.ConverterException;

/**
 * ConverterException Factory
 * Created by Alena on 26.03.2017.
 */
public class ConverterExceptionFactory {
    private final static String CONVERTER_NOT_FOUND_MSG = "Converter not found for class ";
    private final static String SOURCE_IS_NULL = "Cannot convert NULL source.";
    private final static String DESTINATION_CLASS_IS_NULL = "Cannot convert to NULL destination class.";

    public static ConverterException converterNotFoundException(Class aClass) {
        return new ConverterException(CONVERTER_NOT_FOUND_MSG + aClass.getCanonicalName());
    }

    public static ConverterException sourceIsNullException() {
        return new ConverterException(SOURCE_IS_NULL);
    }

    public static ConverterException destinationClassIsNullException() {
        return new ConverterException(DESTINATION_CLASS_IS_NULL);
    }
}
