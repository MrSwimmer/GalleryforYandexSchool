package com.mrswimmer.galleryforyandexschool.domain.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_in.SignInFragment;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_up.SignUpFragment;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery.GalleryFragment;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.new_image.NewImageFragment;

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
        Bundle bundle = new Bundle();
        bundle.putString(Settings.GALLERY_BUNDLE_KEY, String.valueOf(data));
        switch (screenKey) {
            case Screens.GALLERY_SCREEN:
                GalleryFragment galleryFragment = new GalleryFragment();
                galleryFragment.setArguments(bundle);
                return galleryFragment;
            case Screens.NEW_IMAGE_SCREEN:
                return new NewImageFragment();
            default:
                return new GalleryFragment();
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


