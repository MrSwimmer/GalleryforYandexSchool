package com.mrswimmer.galleryforyandexschool.domain.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.mrswimmer.galleryforyandexschool.data.Screens;
import com.mrswimmer.galleryforyandexschool.presentation.auth.activity.AuthActivity;
import com.mrswimmer.galleryforyandexschool.presentation.splash.intro.IntroActivity;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;

public class GlobalNavigator implements Navigator {
    Activity activity;

    public GlobalNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void applyCommand(Command command) {
        Intent intent;
        if (command instanceof Forward) {
            switch (((Forward) command).getScreenKey()) {
                case Screens.AUTH_ACTIVITY:
                    activity.overridePendingTransition(0,0);
                    TaskStackBuilder.create(activity)
                            .addNextIntentWithParentStack(new Intent(activity, AuthActivity.class))
                            .addNextIntent(new Intent(activity, IntroActivity.class))
                            .startActivities();
                    activity.finish();
                    break;
                case Screens.MAIN_ACTIVITY:
                    activity.overridePendingTransition(0,0);
                    intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                    break;
                case Screens.SET_MARK_IN_GOOGLE_PLAY:
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.mrswimmer.coffeetea"));
                    activity.startActivity(intent);
                    break;
            }
        }
    }
}
