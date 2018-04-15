package com.mrswimmer.galleryforyandexschool.presentation.main.fragment.detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.galleryforyandexschool.App;
import com.mrswimmer.galleryforyandexschool.data.model.ImageItem;
import com.mrswimmer.galleryforyandexschool.di.qualifier.Local;
import com.mrswimmer.galleryforyandexschool.domain.service.FireService;
import com.mrswimmer.galleryforyandexschool.domain.service.SettingsService;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class DetailFragmentPresenter extends MvpPresenter<DetailFragmentView> {
    @Inject
    @Local
    Router router;
    @Inject
    FireService fireService;
    @Inject
    SettingsService settingsService;

    public DetailFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setImageDetail(String id) {
        fireService.getImage(id, new FireService.ImageDetailCallback() {
            @Override
            public void onSuccess(ImageItem imageItem) {
                getViewState().initDetail(imageItem, settingsService.getMailKey());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
