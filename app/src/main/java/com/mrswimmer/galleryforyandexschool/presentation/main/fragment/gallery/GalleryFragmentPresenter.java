package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.gallery;

import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

public class GalleryFragmentPresenter extends MvpPresenter<GalleryFragmentView> {
    @Inject
    @Local
    Router router;
    @Inject
    FireService fireService;

    public GalleryFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setRecyclerData() {

    }
}
