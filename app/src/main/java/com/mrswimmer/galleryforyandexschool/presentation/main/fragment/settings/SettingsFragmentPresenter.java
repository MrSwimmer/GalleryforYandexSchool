package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.settings;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Global;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SettingsFragmentPresenter extends MvpPresenter<SettingsFragmentView> {
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Inject
    @Local
    Router localRouter;

    @Inject
    FireService fireService;

    @Inject
    SharedPreferences settings;
    public SettingsFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setMark() {
        globalRouter.navigateTo(Screens.SET_MARK_IN_GOOGLE_PLAY);
    }

    public void clearPrefs() {
        fireService.signOut();
        globalRouter.navigateTo(Screens.AUTH_ACTIVITY);
    }

    public void signOut(AlertDialog.Builder builder) {
        builder.setTitle("Выход из аккаунта")
                .setMessage("Вы действительно хотите выйти из аккаунта?")
                .setPositiveButton("Да", (dialog, which) -> {
                    clearPrefs();
                }).setNegativeButton("Нет", (dialog, which) -> dialog.cancel());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void gotoAboutCompany() {
        localRouter.navigateTo(Screens.INFO_SCREEN, R.string.about_company);
    }

    public void gotoInstruction() {
        localRouter.navigateTo(Screens.INFO_SCREEN, R.string.instruction);
    }
}
