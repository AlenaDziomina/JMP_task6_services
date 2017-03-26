package com.grouk.services.service;

import com.grouk.services.converter.ConverterProvider;
import com.grouk.services.dao.UserProfileDao;
import com.grouk.services.factory.UserAccountFactory;
import com.grouk.services.model.UserAccount;
import com.grouk.services.model.UserProfile;
import com.sun.jersey.api.core.InjectParam;

import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User Account Service
 * Created by Alena_Grouk on 3/22/2017.
 */
@Provider
public class UserAccountService extends AbstractService {
    private final UserProfileDao userProfileDao;
    private final UserAccountFactory userAccountFactory;

    public UserAccountService(@InjectParam ConverterProvider converter, @InjectParam UserProfileDao userProfileDao,
                              @InjectParam UserAccountFactory userAccountFactory) {
        super(converter);
        this.userProfileDao = userProfileDao;
        this.userAccountFactory = userAccountFactory;
    }

    public UserAccount getUserAccount(Long id) {
        UserProfile userProfile = userProfileDao.getUserProfile(id);
        return (UserAccount) converter.convert(context, userProfile, UserAccount.class);
    }

    public List<UserAccount> getUserAccountList() {
        List<UserProfile> userProfiles = userProfileDao.getUserList();
        return userProfiles.stream()
                .map(userProfile -> (UserAccount) converter.convert(context, userProfile, UserAccount.class))
                .collect(Collectors.toList());
    }

    public UserAccount getDefaultUserAccount() {
        return userAccountFactory.makeUserAccount();
    }

    public void updateUserAccount(Long userId, UserAccount userAccount) {
        userAccount.setUserId(userId);
        UserProfile userProfile = (UserProfile) converter.convert(context, userAccount, UserProfile.class);
        userProfileDao.updateUserProfile(userProfile);
    }

    public void createUserAccount(UserAccount userAccount) {
        UserProfile userProfile = (UserProfile) converter.convert(context, userAccount, UserProfile.class);
        userProfileDao.createUserProfile(userProfile);
    }

    public void deleteUserAccount(Long userId) {
        userProfileDao.deleteUserProfile(userId);
    }
}
