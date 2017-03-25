package com.grouk.services.factory;

import com.grouk.services.model.UserAccount;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.ext.Provider;

/**
 * UserAccount Prototype Factory
 * Created by Alena on 25.03.2017.
 */
@Provider
@Singleton
public class UserAccountFactory {
    private final UserAccount prototype;

    public UserAccountFactory(){
        prototype = new UserAccount();
        prototype.setUserId(0L);
        prototype.setFirstName("");
        prototype.setLastName("");
    }

    public UserAccount makeUserAccount(){
        return prototype.clone();
    }
}
