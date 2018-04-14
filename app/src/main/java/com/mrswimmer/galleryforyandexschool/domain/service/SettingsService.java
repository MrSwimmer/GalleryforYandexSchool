package com.mrswimmer.galleryforyandexschool.domain.service;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SettingsService {
    private final String MAIL = "mail";
    private final String USERNAME = "username";
    private final String USERNAME_DEFAULT = "Гость";

    @Inject
    SharedPreferences sharedPreferences;

    public void saveUsernameAndMail(String mail, String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MAIL, mail);
        editor.putString(USERNAME, username);
        editor.apply();
    }


    public String getUsername() {
        return sharedPreferences.getString(USERNAME, USERNAME_DEFAULT);
    }
}
