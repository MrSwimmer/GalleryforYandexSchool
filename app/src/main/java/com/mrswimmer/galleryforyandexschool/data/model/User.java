package com.mrswimmer.galleryforyandexschool.data.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String mail;
    String username;
    HashMap<String, ImageItem> likes = new HashMap<>();
    HashMap<String, ImageItem> my = new HashMap<>();

    public User() {
    }

    public User(String mail, String username) {
        this.mail = mail;
        this.username = username;
    }

    public HashMap<String, ImageItem> getLikes() {
        return likes;
    }

    public void setLikes(HashMap<String, ImageItem> likes) {
        this.likes = likes;
    }

    public HashMap<String, ImageItem> getMy() {
        return my;
    }

    public void setMy(HashMap<String, ImageItem> my) {
        this.my = my;
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

}
