package com.grouk.services.service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.grouk.services.dao.UserAvatarDao;
import com.grouk.services.dao.UserProfileDao;
import com.sun.jersey.api.core.InjectParam;

/**
 * User Account Service
 * Created by Alena_Grouk on 3/22/2017.
 */
@Provider
public class UserAccountService {
    public final UserAvatarDao userAvatarDao;
    public final UserProfileDao userProfileDao;

    @Context
    public UriInfo uriInfo;

    public UserAccountService(@InjectParam UserAvatarDao userAvatarDao, @InjectParam UserProfileDao userProfileDao) {
        this.userAvatarDao = userAvatarDao;
        this.userProfileDao = userProfileDao;
    }
}
