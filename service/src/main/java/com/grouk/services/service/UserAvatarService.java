package com.grouk.services.service;

import com.grouk.services.converter.ConverterProvider;
import com.grouk.services.dao.UserAvatarDao;
import com.grouk.services.factory.AvatarImageFactory;
import com.grouk.services.model.AvatarImage;
import com.grouk.services.model.UserAvatar;
import com.grouk.services.model.UserProfile;
import com.sun.jersey.api.core.InjectParam;

import javax.ws.rs.ext.Provider;

/**
 * User Avatar Service
 * Created by Alena on 26.03.2017.
 */
@Provider
public class UserAvatarService extends AbstractService {
    private final UserAvatarDao userAvatarDao;
    private final AvatarImageFactory avatarImageFactory;

    public UserAvatarService(@InjectParam ConverterProvider converter, @InjectParam UserAvatarDao userAvatarDao,
                             @InjectParam AvatarImageFactory avatarImageFactory) {
        super(converter);
        this.userAvatarDao = userAvatarDao;
        this.avatarImageFactory = avatarImageFactory;
    }

    public AvatarImage getAvatarImage(Long avatarId) {
        UserAvatar userAvatar = userAvatarDao.getUserAvatar(avatarId);
        return (AvatarImage) converter.convert(context, userAvatar, AvatarImage.class);
    }

    public AvatarImage getDefaultAvatarImage() {
        return avatarImageFactory.makeAvatarImage();
    }

    public void updateAvatarImage(Long avatarId, AvatarImage avatarImage) {
        context.put("avatarId", avatarId);
        UserAvatar userAvatar = (UserAvatar) converter.convert(context, avatarImage, UserAvatar.class);
        userAvatarDao.updateUserAvatar(userAvatar);
    }

    public Long createAvatarImage(AvatarImage avatarImage) {
        UserAvatar userAvatar = (UserAvatar) converter.convert(context, avatarImage, UserAvatar.class);
        return userAvatarDao.createUserAvatar(userAvatar);
    }

    public void deleteAvatarImage(Long avatarId) {
        userAvatarDao.deleteUserAvatar(avatarId);
    }
}
