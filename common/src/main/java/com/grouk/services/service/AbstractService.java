package com.grouk.services.service;

import com.grouk.services.converter.ConverterProvider;
import com.sun.jersey.api.core.InjectParam;

import java.util.Properties;

/**
 * Abstract Service with initialized ConverterProvider
 * Created by Alena on 26.03.2017.
 */
public class AbstractService {
    protected final ConverterProvider converter;
    protected final Properties context;

    protected AbstractService(@InjectParam ConverterProvider converter) {
        this.converter = converter;
        this.context = new Properties();
    }
}
