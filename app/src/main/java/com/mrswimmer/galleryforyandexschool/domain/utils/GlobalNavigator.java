package com.mrswimmer.galleryforyandexschool.domain.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;

import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.presentation.auth.activity.AuthActivity;
import com.mrswimmer.galleryforyandexschool.presentation.main.activity.MainActivity;
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
    public void applyCommands(Command[] commands) {
        Intent intent;
        Command command = commands[0];
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
                    intent.setData(Uri.parse("market://details?id=com.mrswimmer.galleryforyandexschool"));
                    activity.startActivity(intent);
                    break;
                case Screens.SHARE:
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Присоединяйтесь к Галерее!\nhttps://play.google.com/store/apps/details?id=com.mrswimmer.galleryforyandexschool");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Поделиться");
                    intent = Intent.createChooser(intent, "С помощью");
                    activity.startActivity(intent);
            }
        }
    }

}
