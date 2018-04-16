package com.mrswimmer.galleryforyandexschool.di;

import com.mrswimmer.galleryforyandexschool.di.module.FireModule;
import com.mrswimmer.galleryforyandexschool.di.module.NavigatorModule;
import com.mrswimmer.galleryforyandexschool.di.module.SharedPreferencesModule;
import com.mrswimmer.galleryforyandexschool.domain.service.SettingsService;
import com.mrswimmer.galleryforyandexschool.presentation.auth.activity.AuthActivity;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_in.SignInFragmentPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_up.SignUpFragmentPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.main.activity.MainActivity;
import com.mrswimmer.galleryforyandexschool.presentation.main.activity.MainActivityPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.detail.DetailFragmentPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery.GalleryFragmentPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery.recycler.ImagesAdapter;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.new_image.NewImageFragmentPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.main.fragment.settings.SettingsFragmentPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPreferencesModule.class, NavigatorModule.class, FireModule.class})
public interface AppComponent {

    void inject(AuthActivity authActivity);

    void inject(SignInFragmentPresenter signInFragmentPresenter);

    void inject(SignUpFragmentPresenter signUpFragmentPresenter);

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    void inject(GalleryFragmentPresenter galleryFragmentPresenter);

    void inject(ImagesAdapter imagesAdapter);

    void inject(NewImageFragmentPresenter newImageFragmentPresenter);

    void inject(DetailFragmentPresenter detailFragmentPresenter);

    void inject(SettingsFragmentPresenter settingsFragmentPresenter);
}
