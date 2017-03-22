package com.grouk.services.dao;

import javax.ws.rs.ext.Provider;

import com.grouk.services.model.UserProfile;
import com.sun.jersey.spi.resource.Singleton;

/**
 * User Profile DAO
 * Created by Alena_Grouk on 3/22/2017.
 */
@Provider
@Singleton
public class UserProfileDao extends AbstractDao<UserProfile> {

    public UserProfile getUserProfile(Long userId) {
        return null;
    }

    public Long createUserProfile(UserProfile userAvatar) {
        return null;
    }

    public void updateUserProfile(UserProfile userAvatar) {

    }

    public void deleteUserProfile(Long userId) {

    }
}
