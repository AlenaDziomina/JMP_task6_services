package com.grouk.services.service;

import com.grouk.services.converter.ConverterProvider;
import com.grouk.services.dao.UserAvatarDao;
import com.grouk.services.dao.UserProfileDao;
import com.grouk.services.factory.UserAccountFactory;
import com.grouk.services.model.UserAccount;
import com.grouk.services.model.UserProfile;
import com.sun.jersey.api.core.InjectParam;

import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * User Account Service
 * Created by Alena_Grouk on 3/22/2017.
 */
@Provider
public class UserAccountService {
    private final UserAvatarDao userAvatarDao;
    private final UserProfileDao userProfileDao;
    private final ConverterProvider converter;
    private final UserAccountFactory userAccountFactory;

    public UserAccountService(@InjectParam UserAvatarDao userAvatarDao, @InjectParam UserProfileDao userProfileDao,
                              @InjectParam ConverterProvider converter, @InjectParam UserAccountFactory userAccountFactory) {
        this.userAvatarDao = userAvatarDao;
        this.userProfileDao = userProfileDao;
        this.converter = converter;
        this.userAccountFactory = userAccountFactory;
    }

    public UserAccount getUserAccount(Long id) {
        UserProfile user = userProfileDao.getUserProfile(id);
        Properties properties = new Properties();
        properties.put("user", user);
        return (UserAccount) converter.convert(properties, UserAccount.class);
    }

    public List<UserAccount> getUserAccountList() {
        List<UserProfile> users = userProfileDao.getUserList();
        Properties properties = new Properties();
        return users.stream().map(u -> properties.put("user", u)).map(p -> (UserAccount) converter.convert
                (properties, UserAccount.class)).collect(Collectors.toList());
    }

    public UserAccount getDefaultUserAccount() {
        return userAccountFactory.makeUserAccount();
    }

    public void updateUserAccount(Long userId, UserAccount userAccount) {
        userAccount.setUserId(userId);
        Properties properties = new Properties();
        properties.put("account", userAccount);
        UserProfile user = (UserProfile) converter.convert(properties, UserProfile.class);
        userProfileDao.updateUserProfile(user);
    }

    public void createUserAccount(UserAccount userAccount) {
        Properties properties = new Properties();
        properties.put("account", userAccount);
        UserProfile user = (UserProfile) converter.convert(properties, UserProfile.class);
        userProfileDao.createUserProfile(user);
    }

    public void deleteUserAccount(Long userId) {
        userProfileDao.deleteUserProfile(userId);
    }
}
