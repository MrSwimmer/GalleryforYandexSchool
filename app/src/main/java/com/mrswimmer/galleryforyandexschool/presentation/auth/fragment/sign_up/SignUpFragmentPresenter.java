package com.mrswimmer.galleryforyandexschool.presentation.auth.fragment.sign_up;

import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.data.Screens;
import com.mrswimmer.galleryforyandexschool.data.Settings;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Global;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SignUpFragmentPresenter extends MvpPresenter<SignUpFragmentView> {
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Inject
    @Global
    Router globalRouter;

    @Inject
    SharedPreferences settings;

    @Inject
    FireService fireService;

    public SignUpFragmentPresenter() {
        App.getComponent().inject(this);
    }

    void signUp(String email, String pass, String username) {

        fireService.signUp(email, pass, new FireService.AuthCallBack() {
            @Override
            public void onSuccess(boolean success) {
                getViewState().getUserData();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }

}
