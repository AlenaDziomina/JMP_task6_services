package com.grouk.services.dao;

import com.grouk.services.model.UserProfile;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.ext.Provider;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final String SQL_GET = "Select * from USER_PROFILE;";
    private static final String SQL_FIND_BY_ID = "Select * from USER_PROFILE where ID_USER = ?;";
    private static final String SQL_CREATE = "Insert into USER_PROFILE (FIRST_NAME, LAST_NAME, AVATAR_ID) values " +
            "(?, ?, ?);";
    private static final String SQL_DELETE = "Delete from USER_PROFILE WHERE ID_USER = ?;";
    private static final String SQL_UPDATE = "Update USER_PROFILE set FIRST_NAME = ?, LAST_NAME = ?, AVATAR_ID = ? " +
            "where ID_USER = ?;";

    public List<UserProfile> getUserList() {
        return load(SQL_GET, null, this::getUserProfile);
    }

    public UserProfile getUserProfile(Long userId) {
        List<Object> parameters = Collections.singletonList(userId);
        return find(SQL_FIND_BY_ID, parameters, this::getUserProfile);
    }

    public Long createUserProfile(UserProfile userProfile) {
        List<Object> parameters = new ArrayList<>(3);
        parameters.add(userProfile.getFirstName());
        parameters.add(userProfile.getLastName());
        parameters.add(userProfile.getAvatarId());
        return create(SQL_CREATE, parameters);
    }

    public void updateUserProfile(UserProfile userProfile) {
        List<Object> parameters = new ArrayList<>(4);
        parameters.add(userProfile.getFirstName());
        parameters.add(userProfile.getLastName());
        parameters.add(userProfile.getAvatarId());
        parameters.add(userProfile.getId());
        update(SQL_UPDATE, parameters);
    }

    public void deleteUserProfile(Long userId) {
        List<Object> parameters = Collections.singletonList(userId);
        delete(SQL_DELETE, parameters);
    }

    private UserProfile getUserProfile(ResultSet rs) throws SQLException {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(rs.getLong("ID_USER"));
        userProfile.setFirstName(rs.getString("FIRST_NAME"));
        userProfile.setLastName(rs.getString("LAST_NAME"));
        Long avatarId = rs.getLong("AVATAR_ID");
        userProfile.setAvatarId(rs.wasNull() ? null : avatarId);
        return userProfile;
    }
}
