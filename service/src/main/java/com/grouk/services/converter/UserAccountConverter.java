package com.grouk.services.converter;

import com.grouk.services.model.UserAccount;
import com.grouk.services.model.UserProfile;

import javax.ws.rs.ext.Provider;
import java.util.Properties;

/**
 * Converter UserAccount to UserProfile
 * Created by Alena on 25.03.2017.
 */
@Provider
@Converter(srcClass = UserAccount.class, destClass = UserProfile.class)
public class UserAccountConverter extends AbstractConverter<UserAccount, UserProfile> {

    @Override
    public UserProfile convert(Properties context, UserAccount userAccount) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userAccount.getUserId());
        userProfile.setFirstName(userAccount.getFirstName());
        userProfile.setLastName(userAccount.getLastName());
        userProfile.setAvatarId(userAccount.getAvatarId());
        return userProfile;
    }
}
