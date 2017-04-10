package com.grouk.services.manager;

import com.grouk.services.model.AvatarImage;
import com.grouk.services.model.UserAccount;
import com.grouk.services.service.UserAccountService;
import com.grouk.services.service.UserAvatarService;
import com.sun.jersey.api.core.InjectParam;

import javax.annotation.Resource;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * UserAccount Manager for UserAccount Rest Service
 * Created by Alena on 26.03.2017.
 */
@Provider
public class UserAccountManager {
    private final UserAccountService userAccountService;
    private final UserAvatarService userAvatarService;

    public UserAccountManager(@InjectParam UserAccountService userAccountService, @InjectParam UserAvatarService
            userAvatarService) {
        this.userAccountService = userAccountService;
        this.userAvatarService = userAvatarService;
    }

    public List<UserAccount> getUserAccountList() {
        return userAccountService.getUserAccountList();
    }

    public UserAccount getUserAccount(Long userId) {
        return userAccountService.getUserAccount(userId);
    }

    public UserAccount getDefaultUserAccount() {
        return userAccountService.getDefaultUserAccount();
    }

    public void updateUserAccount(Long userId, UserAccount userAccount) {
        userAccountService.updateUserAccount(userId, userAccount);
    }

    public void updateUserAccountList(List<UserAccount> userAccountList) {
        userAccountService.updateUserAccountList(userAccountList);
    }

    public void createUserAccount(UserAccount userAccount) {
        userAccountService.createUserAccount(userAccount);
    }

    public void createUserAccount(List<UserAccount> userAccountList) {
        userAccountService.createUserAccountList(userAccountList);
    }

    public void deleteUserAccount(Long userId) {
        userAccountService.deleteUserAccount(userId);
    }

    public AvatarImage getUserAvatar(Long userId) {
        UserAccount userAccount = userAccountService.getUserAccount(userId);
        Long avatarId = userAccount.getAvatarId();
        if (avatarId == null) {
            return userAvatarService.getDefaultAvatarImage();
        } else {
            return userAvatarService.getAvatarImage(avatarId);
        }
    }

    public void saveUserAvatar(Long userId, AvatarImage avatarImage) {
        UserAccount userAccount = userAccountService.getUserAccount(userId);
        Long avatarId = userAccount.getAvatarId();
        if ( avatarId == null) {
            avatarId = userAvatarService.createAvatarImage(avatarImage);
            userAccount.setAvatarId(avatarId);
            userAccountService.updateUserAccount(userId, userAccount);
        } else {
            userAvatarService.updateAvatarImage(avatarId, avatarImage);
        }
    }

    public void deleteUserAvatar(Long userId) {
        UserAccount userAccount = userAccountService.getUserAccount(userId);
        Long avatarId = userAccount.getAvatarId();
        if (avatarId != null) {
            userAvatarService.deleteAvatarImage(avatarId);
        }
    }
}
