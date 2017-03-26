package com.grouk.services.converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation determines converter, its destination and source classes
 * Created by Alena on 25.03.2017.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Converter {
    Class<?> srcClass();

    Class<?> destClass();
}
