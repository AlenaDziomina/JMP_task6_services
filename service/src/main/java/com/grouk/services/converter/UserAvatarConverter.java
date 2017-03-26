package com.grouk.services.converter;

import com.grouk.services.model.AvatarImage;
import com.grouk.services.model.UserAvatar;

import javax.ws.rs.ext.Provider;
import java.util.Properties;

/**
 * Converter UserAvatar to AvatarImage
 * Created by Alena on 26.03.2017.
 */
@Provider
@Converter(srcClass = UserAvatar.class, destClass = AvatarImage.class)
public class UserAvatarConverter extends AbstractConverter<UserAvatar, AvatarImage> {
    @Override
    public AvatarImage convert(Properties context, UserAvatar userAvatar) {
        AvatarImage avatarImage = new AvatarImage();
        avatarImage.setImage(userAvatar.getAvatar());
        return avatarImage;
    }
}
