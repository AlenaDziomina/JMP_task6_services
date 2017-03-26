package com.grouk.services.converter;

import com.grouk.services.model.UserAccount;
import com.grouk.services.model.UserProfile;

import javax.ws.rs.ext.Provider;
import java.util.Properties;

/**
 * Converter UserProfile to UserAccount
 * Created by Alena on 25.03.2017.
 */
@Provider
@Converter(srcClass = UserProfile.class, destClass = UserAccount.class)
public class UserProfileConverter extends AbstractConverter<UserProfile, UserAccount> {

    @Override
    public UserAccount convert(Properties context, UserProfile userProfile) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userProfile.getId());
        userAccount.setFirstName(userProfile.getFirstName());
        userAccount.setLastName(userProfile.getLastName());
        userAccount.setAvatarId(userProfile.getAvatarId());
        return userAccount;
    }
}
