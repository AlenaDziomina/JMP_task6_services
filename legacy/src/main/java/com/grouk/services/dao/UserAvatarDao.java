package com.grouk.services.dao;

import javax.ws.rs.ext.Provider;

import com.grouk.services.model.UserAvatar;
import com.sun.jersey.spi.resource.Singleton;

/**
 * User Avatar DAO
 * Created by Alena_Grouk on 3/22/2017.
 */
@Provider
@Singleton
public class UserAvatarDao extends AbstractDao<UserAvatar> {

    public UserAvatar getUserAvatar(Long avatarId) {
        return null;
    }

    public Long createUserAvatar(UserAvatar userAvatar) {
        return null;
    }

    public void updateUserAvatar(UserAvatar userAvatar) {

    }

    public void deleteUserAvatar(Long avatarId) {

    }
}
