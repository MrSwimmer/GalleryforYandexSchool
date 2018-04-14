package com.mrswimmer.galleryforyandexschool;

import android.app.Application;

import com.mrswimmer.galleryforyandexschool.di.AppComponent;
import com.mrswimmer.galleryforyandexschool.di.module.SharedPreferencesModule;

public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
                .build();
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
