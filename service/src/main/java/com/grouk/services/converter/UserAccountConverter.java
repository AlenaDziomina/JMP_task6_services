package com.grouk.services.converter;

import com.grouk.services.model.UserAccount;
import com.grouk.services.model.UserProfile;

import javax.ws.rs.ext.Provider;
import java.util.Properties;

/**
 * Converter to UserAccount
 * Created by Alena on 25.03.2017.
 */
@Provider
@Converter(destClass = UserAccount.class)
public class UserAccountConverter extends AbstractConverter<UserAccount> {

    @Override
    public UserAccount convert(Properties context) {
        UserAccount userAccount = new UserAccount();
        if (context == null) {
            return userAccount;
        }

        UserProfile user = (UserProfile) context.get("user");
        if (user != null) {
            userAccount.setUserId(user.getId());
            userAccount.setFirstName(user.getFirstName());
            userAccount.setLastName(user.getLastName());
        }

        UserProfile avatar = (UserProfile) context.get("avatar");
        if (avatar != null) {

        }
        return userAccount;
    }
}
