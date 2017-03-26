package com.grouk.services.converter;

import java.util.Properties;

/**
 * Abstract Converter
 * Created by Alena on 25.03.2017.
 */
abstract class AbstractConverter<S, T> {
    abstract public T convert(Properties context, S source);
}
