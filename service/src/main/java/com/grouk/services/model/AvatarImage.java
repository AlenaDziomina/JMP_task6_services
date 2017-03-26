package com.grouk.services.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Avatar Image
 * Created by Alena on 26.03.2017.
 */
@XmlRootElement
public class AvatarImage {
    private byte[] image;

    public AvatarImage() {
    }

    public AvatarImage(AvatarImage avatarImage) {
        this.image = avatarImage.getImage();
    }

    public AvatarImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public AvatarImage clone() {
        return new AvatarImage(this);
    }
}
