package com.grouk.services.converter;

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

    private Map<Class, AbstractConverter> converterMap;

    public ConverterProvider(@Context Application application) {
        converterMap = new HashMap<>();
        Set<Class<?>> classes = application.getClasses();
        classes.forEach(this::init);
    }

    private void init(Class aClass) {
        if (aClass.isAnnotationPresent(Converter.class)) {
            Converter annotation = (Converter) aClass.getAnnotation(Converter.class);
            Class destClass = annotation.destClass();
            try {
                AbstractConverter converter = (AbstractConverter) aClass.newInstance();
                converterMap.put(destClass, converter);
            } catch (Exception e) {
                throw new RuntimeException("Cannot initiate converter " + aClass);
            }
        }
    }

    public Object convert(Properties context, Class aClass) {
        AbstractConverter converter = converterMap.get(aClass);
        return converter.convert(context);
    }
}
