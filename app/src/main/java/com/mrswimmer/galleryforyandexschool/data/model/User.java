package com.mrswimmer.galleryforyandexschool.data.model;

import java.util.ArrayList;

public class User {
    String mail;
    String username;
    ArrayList<ImageItem> images = new ArrayList<>();

    public User(String mail, String username, ArrayList<ImageItem> images) {
        this.mail = mail;
        this.username = username;
        this.images = images;
    }

    public User() {
    }

    public User(String mail, String username) {
        this.mail = mail;
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<ImageItem> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageItem> images) {
        this.images = images;
    }
}
