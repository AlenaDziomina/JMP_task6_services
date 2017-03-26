package com.grouk.services.exception.factory;

import com.grouk.services.exception.PublicException;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.ext.Provider;

import static com.grouk.services.exception.ExceptionConstants.INTERNAL_ERROR;
import static com.grouk.services.exception.ExceptionConstants.INTERNAL_ERROR_MSG;

/**
 * PublicException Factory
 * Created by Alena_Grouk on 8/2/2016.
 */
@Provider
@Singleton
public class PublicExceptionFactory {

    public PublicException getPublicException(int errorCode, String errorMsg) {
        return new PublicException(errorCode, errorMsg);
    }

    public PublicException getPublicException(int errorCode, String errorMsg, Throwable e) {
        return new PublicException(errorCode, errorMsg, e);
    }

    public PublicException createInternalException(Throwable e) {
        return getPublicException(INTERNAL_ERROR, INTERNAL_ERROR_MSG, e);
    }
}
