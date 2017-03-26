package com.grouk.services.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User Account
 * Created by Alena on 25.03.2017.
 */
@XmlRootElement
public class UserAccount implements Cloneable {
    private Long userId;
    private String firstName;
    private String lastName;
    private Long avatarId;

    public UserAccount() {
    }

    public UserAccount(UserAccount userAccount) {
        this.setUserId(userAccount.getUserId());
        this.setFirstName(userAccount.getFirstName());
        this.setLastName(userAccount.getLastName());
        this.setAvatarId(userAccount.getAvatarId());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public UserAccount clone() {
        return new UserAccount(this);
    }
}
