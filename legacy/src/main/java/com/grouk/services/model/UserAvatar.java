package com.grouk.services.model;

/**
 * User Avatar entity
 * Created by Alena_Grouk on 3/22/2017.
 */
public class UserAvatar {
    private Long id;
    private byte[] avatar;

    public UserAvatar() {
    }

    public UserAvatar(Long id, byte[] avatar) {
        this.id = id;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }
}
