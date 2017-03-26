package com.grouk.services.dao;

import com.grouk.services.model.UserAvatar;
import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User Avatar DAO
 * Created by Alena_Grouk on 3/22/2017.
 */
@Provider
@Singleton
public class UserAvatarDao extends AbstractDao<UserAvatar> {
    private static final String SQL_FIND_BY_ID = "Select * from USER_AVATAR where ID_AVATAR = ?;";
    private static final String SQL_CREATE = "Insert into USER_AVATAR (IMAGE) values (?);";
    private static final String SQL_DELETE = "Delete from USER_AVATAR WHERE ID_AVATAR = ?;";
    private static final String SQL_UPDATE = "Update USER_AVATAR set IMAGE = ? where ID_AVATAR = ?;";


    public UserAvatar getUserAvatar(Long avatarId) {
        List<Object> parameters = Collections.singletonList(avatarId);
        return find(SQL_FIND_BY_ID, parameters, rs -> new UserAvatar(rs.getLong("ID_AVATAR"), rs.getBytes("IMAGE")));
    }

    public Long createUserAvatar(UserAvatar userAvatar) {
        List<Object> parameters = Collections.singletonList(userAvatar.getAvatar());
        return create(SQL_CREATE, parameters);
    }

    public void updateUserAvatar(UserAvatar userAvatar) {
        List<Object> parameters = new ArrayList<>(2);
        parameters.add(userAvatar.getAvatar());
        parameters.add(userAvatar.getId());
        update(SQL_UPDATE, parameters);
    }

    public void deleteUserAvatar(Long avatarId) {
        List<Object> parameters = Collections.singletonList(avatarId);
        delete(SQL_DELETE, parameters);
    }
}
