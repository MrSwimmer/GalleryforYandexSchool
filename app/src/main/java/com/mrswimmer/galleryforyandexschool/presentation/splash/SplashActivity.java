package com.mrswimmer.galleryforyandexschool.presentation.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;
import com.mrswimmer.galleryforyandexschool.presentation.auth.activity.AuthActivity;
import com.mrswimmer.galleryforyandexschool.presentation.main.activity.MainActivity;
import com.mrswimmer.galleryforyandexschool.presentation.splash.intro.IntroActivity;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity {

    @Inject
    FireService fireService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        App.getComponent().inject(this);
        new Handler().postDelayed(() -> {
            if (!fireService.checkLogIn()) {
                overridePendingTransition(0, 0);
                TaskStackBuilder.create(getApplicationContext())
                        .addNextIntentWithParentStack(new Intent(getApplicationContext(), AuthActivity.class))
                        .addNextIntent(new Intent(getApplicationContext(), IntroActivity.class))
                        .startActivities();
                finish();
            } else {
                overridePendingTransition(0, 0);
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
