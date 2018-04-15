package com.mrswimmer.galleryforyandexschool.presentation.auth.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.presentation.base.BaseActivity;

import ru.terrakok.cicerone.commands.Command;

public class AuthActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int getContainerId() {
        return Screens.CONTAINER_AUTH;
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

}
