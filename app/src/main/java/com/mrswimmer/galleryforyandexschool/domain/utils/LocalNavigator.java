package com.mrswimmer.galleryforyandexschool.domain.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_in.SignInFragment;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_up.SignUpFragment;

import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class LocalNavigator extends SupportFragmentNavigator {

    int currentContainer;

    public LocalNavigator(FragmentManager fragmentManager, int containerId) {
        super(fragmentManager, containerId);
        currentContainer = containerId;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (currentContainer) {
            case Screens.CONTAINER_MAIN:
                return mainFragments(screenKey, data);
            case Screens.CONTAINER_AUTH:
                return authFragments(screenKey);
            default:
                return authFragments(screenKey);
        }
    }

    private Fragment mainFragments(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.GALLERY_SCREEN:
                return new
            default:

        }
    }

    private Fragment authFragments(String screenKey) {
        switch (screenKey) {
            case Screens.SIGN_IN_SCREEN:
                return new SignInFragment();
            case Screens.SIGN_UP_SCREEN:
                return new SignUpFragment();
            default:
                return new SignInFragment();
        }
    }

    @Override
    protected void showSystemMessage(String message) {
    }

    @Override
    protected void exit() {
    }
}


