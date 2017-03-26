package com.grouk.services.model;

/**
 * User Profile entity
 * Created by Alena_Grouk on 3/22/2017.
 */
public class UserProfile {
    private Long id;
    private String firstName;
    private String lastName;
    private Long avatarId;

    public UserProfile() {
    }

    public UserProfile(Long id, String firstName, String lastName, Long avatarId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarId = avatarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
