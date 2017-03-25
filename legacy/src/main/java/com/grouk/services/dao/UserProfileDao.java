package com.grouk.services.dao;

import com.grouk.services.model.UserProfile;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User Profile DAO
 * Created by Alena_Grouk on 3/22/2017.
 */
@Provider
@Singleton
public class UserProfileDao extends AbstractDao<UserProfile> {
    private static final String SQL_GET = "Select * from USER;";
    private static final String SQL_FIND_BY_ID = "Select * from USER where ID_USER = ?;";
    private static final String SQL_CREATE = "Insert into USER (FIRST_NAME, LAST_NAME) values (?, ?);";
    private static final String SQL_DELETE = "Delete from USER WHERE ID_USER = ?;";
    private static final String SQL_UPDATE = "Update USER set FIRST_NAME = ?, LAST_NAME = ? where ID_USER = ?;";

    public List<UserProfile> getUserList() {
        return load(SQL_GET, null, rs -> new UserProfile(rs.getLong("ID_USER"), rs.getString("FIRST_NAME"), rs
                .getString("LAST_NAME")));
    }

    public UserProfile getUserProfile(Long userId) {
        List<Object> parameters = Collections.singletonList(userId);
        return find(SQL_FIND_BY_ID, parameters, rs -> new UserProfile(rs.getLong("ID_USER"), rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME")));
    }

    public Long createUserProfile(UserProfile userAvatar) {
        List<Object> parameters = new ArrayList<>(2);
        parameters.add(userAvatar.getFirstName());
        parameters.add(userAvatar.getLastName());
        return create(SQL_CREATE, parameters);
    }

    public void updateUserProfile(UserProfile userAvatar) {
        List<Object> parameters = new ArrayList<>(3);
        parameters.add(userAvatar.getFirstName());
        parameters.add(userAvatar.getLastName());
        parameters.add(userAvatar.getId());
        update(SQL_UPDATE, parameters);
    }

    public void deleteUserProfile(Long userId) {
        List<Object> parameters = Collections.singletonList(userId);
        delete(SQL_DELETE, parameters);
    }
}
