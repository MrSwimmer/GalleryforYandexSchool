package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.data.settings.Screens;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;
import com.mrswimmer.galleryforyandexschool.domain.service.SettingsService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class GalleryFragmentPresenter extends MvpPresenter<GalleryFragmentView> {
    @Inject
    @Local
    Router router;
    @Inject
    FireService fireService;
    @Inject
    SettingsService settingsService;

    public GalleryFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setRecyclerData(String key) {
        fireService.getGallery(key, settingsService.getMailKey(), new FireService.GalleryCallback() {
            @Override
            public void onSuccess(List<ImageItem> imageItems) {
                getViewState().initAdapter((ArrayList<ImageItem>) imageItems);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "error recycler " + e.getMessage());
                getViewState().showToast("Ошибка!");
            }
        });
    }

    public void gotoNewImage() {
        router.navigateTo(Screens.NEW_IMAGE_SCREEN);
    }
}
