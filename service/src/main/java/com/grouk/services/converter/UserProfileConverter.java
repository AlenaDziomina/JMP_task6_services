package com.grouk.services.converter;

import com.grouk.services.model.UserAccount;
import com.grouk.services.model.UserProfile;

import javax.ws.rs.ext.Provider;
import java.util.Properties;

/**
 * Converter to UserProfile
 * Created by Alena on 25.03.2017.
 */
@Provider
@Converter(destClass = UserProfile.class)
public class UserProfileConverter extends AbstractConverter<UserProfile> {

    @Override
    public UserProfile convert(Properties context) {
        UserProfile user = new UserProfile();
        if (context == null) {
            return user;
        }

        UserAccount account = (UserAccount) context.get("account");
        if (account != null) {
            user.setId(account.getUserId());
            user.setFirstName(account.getFirstName());
            user.setLastName(account.getLastName());
        }
        return user;
    }
}
