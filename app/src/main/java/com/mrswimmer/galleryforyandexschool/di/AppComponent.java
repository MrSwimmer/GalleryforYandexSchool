package com.mrswimmer.galleryforyandexschool.di;

import com.mrswimmer.galleryforyandexschool.di.module.FireModule;
import com.mrswimmer.galleryforyandexschool.di.module.NavigatorModule;
import com.mrswimmer.galleryforyandexschool.di.module.SharedPreferencesModule;
import com.mrswimmer.galleryforyandexschool.presentation.auth.activity.AuthActivity;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_in.SignInFragmentPresenter;
import com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_up.SignUpFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPreferencesModule.class, NavigatorModule.class, FireModule.class})
public interface AppComponent {

    void inject(AuthActivity authActivity);

    void inject(SignInFragmentPresenter signInFragmentPresenter);

    void inject(SignUpFragmentPresenter signUpFragmentPresenter);
}
