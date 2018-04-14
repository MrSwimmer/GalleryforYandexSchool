package com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_in;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.data.model.User;
import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Global;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;
import com.mrswimmer.galleryforyandexschool.domain.service.SettingsService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SignInFragmentPresenter extends MvpPresenter<SignInFragmentView> {
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Inject
    SettingsService settingsService;

    @Inject
    FireService fireService;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public SignInFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void enter(String email, String pass) {
        fireService.signIn(email, pass, new FireService.AuthCallBack() {
            @Override
            public void onSuccess(boolean success) {
                fireService.getUser(email, new FireService.UserCallback() {
                    @Override
                    public void onSuccess(User user) {
                        settingsService.saveUsernameAndMail(user.getMail(), user.getUsername());
                        globalRouter.navigateTo(Screens.MAIN_ACTIVITY);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast("Ошибка");
                    }
                });
            }
            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }

    void gotoReg() {
        router.navigateTo(Screens.SIGN_UP_SCREEN);
    }
}
