package com.grouk.services.converter;

import com.grouk.services.exception.factory.ConverterExceptionFactory;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Converter Provides with converter HashMap.
 * Converter should extend AbstractConverter and be determined by annotations @Provider and @Converter
 * Created by Alena on 25.03.2017.
 */
@Provider
@Singleton
public class ConverterProvider {

    private Map<Class, HashMap<Class, AbstractConverter>> converterMap;

    public ConverterProvider(@Context Application application) {
        converterMap = new HashMap<>();
        Set<Class<?>> classes = application.getClasses();
        classes.forEach(this::init);
    }

    @SuppressWarnings("unchecked")
    public Object convert(Properties context, Object src, Class destClass) {
        if (src == null) {
            throw ConverterExceptionFactory.sourceIsNullException();
        }

        if (destClass == null) {
            throw ConverterExceptionFactory.destinationClassIsNullException();
        }

        Class srcClass = src.getClass();
        AbstractConverter converter = getConverter(srcClass, destClass);
        return converter.convert(context, src);
    }

    private void init(Class aClass) {
        if (aClass.isAnnotationPresent(Converter.class)) {
            Converter annotation = (Converter) aClass.getAnnotation(Converter.class);
            Class destClass = annotation.destClass();
            Class srcClass = annotation.srcClass();
            try {
                AbstractConverter converter = (AbstractConverter) aClass.newInstance();
                addConverter(converter, srcClass, destClass);
            } catch (Exception e) {
                throw new RuntimeException("Cannot initiate converter " + aClass);
            }
        }
    }

    private void addConverter(AbstractConverter converter, Class srcClass, Class destClass) {
        HashMap<Class, AbstractConverter> converters = converterMap.get(srcClass);
        if (converters == null) {
            converters = new HashMap<>();
            converterMap.put(srcClass, converters);
        }
        converters.put(destClass, converter);
    }

    private AbstractConverter getConverter(Class srcClass, Class destClass) {
        HashMap<Class, AbstractConverter> converters = converterMap.get(srcClass);
        if (converters == null) {
            throw ConverterExceptionFactory.converterNotFoundException(srcClass);
        }

        AbstractConverter converter = converters.get(destClass);
        if (converter == null) {
            throw ConverterExceptionFactory.converterNotFoundException(srcClass);
        }
        return converter;
    }
}
