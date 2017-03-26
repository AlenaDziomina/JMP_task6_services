package com.grouk.services.factory;

import com.grouk.services.model.AvatarImage;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.ext.Provider;

/**
 * AvatarImage prototype factory
 * Created by Alena on 26.03.2017.
 */
@Provider
@Singleton
public class AvatarImageFactory {
    private final AvatarImage prototype;

    public AvatarImageFactory() {
        prototype = new AvatarImage();
        prototype.setImage(new byte[10]);
    }

    public AvatarImage makeAvatarImage() {
        return prototype.clone();
    }
}
