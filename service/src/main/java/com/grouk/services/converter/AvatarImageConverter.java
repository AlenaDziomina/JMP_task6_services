package com.grouk.services.converter;

import com.grouk.services.model.AvatarImage;
import com.grouk.services.model.UserAvatar;

import javax.ws.rs.ext.Provider;
import java.util.Properties;

/**
 * Converter AvatarImage to UserAvatar
 * Created by Alena on 26.03.2017.
 */
@Provider
@Converter(srcClass = AvatarImage.class, destClass = UserAvatar.class)
public class AvatarImageConverter extends AbstractConverter<AvatarImage, UserAvatar> {
    @Override
    public UserAvatar convert(Properties context, AvatarImage avatarImage) {
        UserAvatar userAvatar = new UserAvatar();
        userAvatar.setAvatar(avatarImage.getImage());
        Long avatarId = (Long) context.get("avatarId");
        userAvatar.setId(avatarId);
        return userAvatar;
    }
}
