package com.mrswimmer.galleryforyandexschool.presentation.main.activity;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.R;
import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.data.settings.Settings;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Global;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;

import java.util.Set;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    public MainActivityPresenter() {
        App.getComponent().inject(this);
    }
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.newRootScreen(Screens.CATALOG_SCREEN);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_gallery:
                router.replaceScreen(Screens.GALLERY_SCREEN, Settings.GALLERY_GALLERY);
                break;
            case R.id.nav_favor:
                router.replaceScreen(Screens.GALLERY_SCREEN, Settings.GALLERY_FAVORITE);
                break;
            case R.id.nav_my_gallery:
                router.replaceScreen(Screens.GALLERY_SCREEN, Settings.GALLERY_MY_GALLERY);
                break;
            case R.id.nav_settings:
                router.replaceScreen(Screens.SETTINGS_SCREEN);
                break;

            default:
        }
        menuItem.setChecked(true);
        getViewState().checkDrawerItem(menuItem);
    }

    public void share() {
        globalRouter.navigateTo(Screens.SHARE);
    }
}
